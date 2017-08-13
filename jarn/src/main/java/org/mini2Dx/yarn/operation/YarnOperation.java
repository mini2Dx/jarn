/**
 * MIT License
 *
 * Copyright (c) 2017 Thomas Cashman
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.mini2Dx.yarn.operation;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.yarn.YarnExecutionListener;
import org.mini2Dx.yarn.YarnState;
import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.literal.BooleanLiteral;
import org.mini2Dx.yarn.literal.NumberLiteral;
import org.mini2Dx.yarn.literal.StringLiteral;
import org.mini2Dx.yarn.parser.YarnParser.NumericOperationExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.NumericOperatorContext;
import org.mini2Dx.yarn.parser.YarnParser.ValueExpressionContext;
import org.mini2Dx.yarn.types.YarnNumber;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;
import org.mini2Dx.yarn.variable.NumberVariable;
import org.mini2Dx.yarn.variable.YarnVariable;

/**
 * Base class for operations in the Yarn interpreter
 */
public abstract class YarnOperation {
	protected final int operationIndex;
	private final int lineNumber;

	public YarnOperation(int operationIndex, int lineNumber) {
		super();
		this.operationIndex = operationIndex;
		this.lineNumber = lineNumber;
	}

	/**
	 * Resumes this operation
	 * 
	 * @param yarnState
	 *            The current {@link YarnState}
	 * @param listeners
	 *            A list {@link YarnExecutionException} to be notified of events
	 * @return The index of the next operation to execute, -1 if this operation
	 *         is on-going
	 * @throws YarnExecutionException
	 *             Thrown if an error occurs during execution
	 */
	public abstract int resume(YarnState yarnState, List<YarnExecutionListener> listeners)
			throws YarnExecutionException;

	/**
	 * Returns a {@link List} for all Yarn operations of a given type. Note:
	 * This will flatten the node and include all possible branches.
	 * 
	 * @param clazz
	 *            The {@link YarnOperation} implementation to search for
	 * @return An empty {@link List} if there are no results
	 */
	public <T extends YarnOperation> List<T> getOperationsOfType(Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		getOperationsOfType(clazz, result);
		return result;
	}
	
	protected abstract <T extends YarnOperation> void getOperationsOfType(Class<T> clazz, List<T> result);

	/**
	 * The index for this operation
	 * 
	 * @return The index of the operation in the node's list of operations
	 */
	public int getOperationIndex() {
		return operationIndex;
	}

	/**
	 * The line number in the node corresponding to this operation
	 * 
	 * @return The line number (0 = first line)
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	public static YarnValue resolve(YarnState state, ValueExpressionContext ctx) throws YarnExecutionException {
		if (ctx.NullLiteral() != null) {
			return null;
		}
		if (ctx.BooleanLiteral() != null) {
			return new BooleanLiteral(Boolean.parseBoolean(ctx.BooleanLiteral().getText().trim()));
		}
		if (ctx.NumberLiteral() != null) {
			return new NumberLiteral(Double.parseDouble(ctx.NumberLiteral().getText().trim()));
		}
		if (ctx.StringLiteral() != null) {
			return new StringLiteral(ctx.StringLiteral().getText().trim());
		}
		if (ctx.VariableLiteral() != null) {
			return state.get(ctx.VariableLiteral().getText().trim());
		}
		if (ctx.numericOperationExpression() != null) {
			return YarnOperation.resolve(state, ctx.numericOperationExpression());
		}
		throw new YarnExecutionException(
				"Could not resolve expression " + ctx.getText().trim() + " @ line " + ctx.getStart().getLine());
	}

	/**
	 * Resolves a numeric operation
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param ctx
	 *            The parsed {@link NumericOperationExpressionContext}
	 * @return A {@link YarnNumber} of the result
	 * @throws YarnExecutionException
	 *             Thrown if the operation does not have numeric parameters
	 *             (e.g. a variable resolves as a string)
	 */
	private static YarnNumber resolve(YarnState state, NumericOperationExpressionContext ctx)
			throws YarnExecutionException {
		for (int i = 0; i < ctx.VariableLiteral().size(); i++) {
			YarnVariable variable = state.get(ctx.VariableLiteral(i).getText().trim());
			if (variable.getType() != YarnType.NUMBER) {
				throw new YarnExecutionException("Variable '" + variable.getName() + "' is not a number");
			}
		}

		YarnNumber result = ctx.NumberLiteral(0) != null
				? new NumberLiteral(Double.parseDouble(ctx.NumberLiteral(0).getText().trim()))
				: (NumberVariable) state.get(ctx.VariableLiteral(0).getText().trim());

		if (ctx.valueExpression() != null) {
			if (ctx.valueExpression().NumberLiteral() != null) {
				result = resolve(result,
						new NumberLiteral(Double.valueOf(ctx.valueExpression().NumberLiteral().getText().trim())),
						ctx.numericOperator());
			} else if (ctx.VariableLiteral() != null) {
				YarnNumber variableNumber = (NumberVariable) state
						.get(ctx.valueExpression().VariableLiteral().getText().trim());
				result = resolve(result, variableNumber, ctx.numericOperator());
			} else if (ctx.valueExpression().numericOperationExpression() != null) {
				result = resolve(result, resolve(state, ctx.valueExpression().numericOperationExpression()),
						ctx.numericOperator());
			} else {
				throw new YarnExecutionException(
						"Expression '" + ctx.valueExpression().getText().trim() + "' is not numeric");
			}
		} else if (ctx.NumberLiteral().size() > 1) {
			// Adding 2 numbers
			result = resolve(result, new NumberLiteral(Double.parseDouble(ctx.NumberLiteral(1).getText().trim())),
					ctx.numericOperator());
		} else if (ctx.VariableLiteral().size() > 1) {
			// Adding 2 variables
			result = resolve(result, ((NumberVariable) state.get(ctx.VariableLiteral(0).getText().trim())),
					ctx.numericOperator());
		} else {
			// Adding a number to a variable
			result = resolve(result, ((NumberVariable) state.get(ctx.VariableLiteral(0).getText().trim())),
					ctx.numericOperator());
		}
		return result;
	}

	private static YarnNumber resolve(YarnNumber a, YarnNumber b, NumericOperatorContext ctx) {
		if (ctx.PLUS() != null) {
			return a.add(b.getValue());
		}
		if (ctx.MINUS() != null) {
			return a.subtract(b.getValue());
		}
		if (ctx.MULTIPLY() != null) {
			return a.multiply(b.getValue());
		}
		if (ctx.DIVIDE() != null) {
			return a.divide(b.getValue());
		}
		if (ctx.MODULUS() != null) {
			return a.modulus(b.getValue());
		}
		return a;
	}
}
