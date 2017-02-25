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

import org.mini2Dx.yarn.YarnState;
import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.parser.YarnParser.ComparatorExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.ConditionExpressionContext;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;
import org.mini2Dx.yarn.types.YarnValueComparator;
import org.mini2Dx.yarn.variable.BooleanVariable;
import org.mini2Dx.yarn.variable.YarnVariable;

/**
 * Represents a condition expression in Yarn
 */
public class YarnCondition {
	private final int lineNumber;
	private final ConditionExpressionContext conditionExpression;

	public YarnCondition(int lineNumber, ConditionExpressionContext conditionExpression) {
		super();
		this.lineNumber = lineNumber;
		this.conditionExpression = conditionExpression;
	}

	public boolean eval(YarnState yarnState) throws YarnExecutionException {
		if (conditionExpression.VariableLiteral() != null) {
			return evalBooleanVariable(conditionExpression.VariableLiteral().getText().trim(), yarnState);
		}
		YarnValue leftSide = YarnOperation.resolve(yarnState, conditionExpression.valueExpression(0));
		YarnValue rightSide = YarnOperation.resolve(yarnState, conditionExpression.valueExpression(1));
		
		if(conditionExpression.IS() != null) {
			return YarnValueComparator.equals(leftSide, rightSide);
		}
		ComparatorExpressionContext comparatorCtx = conditionExpression.comparatorExpression();
		if(comparatorCtx.EQUALS() != null) {
			return YarnValueComparator.equals(leftSide, rightSide);
		}
		if(comparatorCtx.NOTEQUALS() != null) {
			return YarnValueComparator.notEquals(leftSide, rightSide);
		}
		if(comparatorCtx.LT() != null) {
			return YarnValueComparator.lessThan(leftSide, rightSide);
		}
		if(comparatorCtx.GT() != null) {
			return YarnValueComparator.greaterThan(leftSide, rightSide);
		}
		if(comparatorCtx.LTE() != null) {
			return YarnValueComparator.lessThanOrEqualTo(leftSide, rightSide);
		}
		if(comparatorCtx.GTE() != null) {
			return YarnValueComparator.greaterThanOrEqualTo(leftSide, rightSide);
		}
		return false;
	}

	private boolean evalBooleanVariable(String variableName, YarnState yarnState) throws YarnExecutionException {
		YarnVariable variable = yarnState.get(variableName);
		if(variable == null) {
			throw new YarnExecutionException("Could not evaluate condition at line " + lineNumber + ". Variable '" + variableName + "' does not exist");
		}
		if(variable.getType() != YarnType.BOOLEAN) {
			throw new YarnExecutionException("Could not evaluate condition at line " + lineNumber + ". Variable '" + variableName + "' is not a boolean");
		}
		return ((BooleanVariable) variable).getValue();
	}
}
