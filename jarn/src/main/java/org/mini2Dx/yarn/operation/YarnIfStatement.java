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
import org.mini2Dx.yarn.parser.YarnParser.BoolOperatorContext;

/**
 * Represents an if/elseif/else expression in Yarn
 */
public class YarnIfStatement extends YarnOperation {
	private final List<YarnCondition> conditions = new ArrayList<YarnCondition>();
	private final List<BoolOperatorContext> operators = new ArrayList<BoolOperatorContext>();
	
	private final IfStatementType statementType;
	
	private int failureOperationIndex;
	private int endBlockOperationindex;

	public YarnIfStatement(int operationIndex, int lineNumber, IfStatementType statementType) {
		super(operationIndex, lineNumber);
		this.statementType = statementType;
	}

	@Override
	public int resume(YarnState yarnState, List<YarnExecutionListener> listeners) throws YarnExecutionException {
		if(eval(yarnState)) {
			return operationIndex + 1;
		}
		return failureOperationIndex;
	}

	public boolean eval(YarnState yarnState) throws YarnExecutionException {
		boolean result = true;
		for(int i = 0; i < conditions.size(); i++) {
			boolean nextResult = conditions.get(i).eval(yarnState);
			if(i > 0) {
				BoolOperatorContext operatorContext = operators.get(i - 1);
				if(operatorContext.AND() != null) {
					result = result && nextResult;
				} else if(operatorContext.OR() != null) {
					result = result || nextResult;
					
					if(result) {
						return true;
					}
				}
			} else {
				result = nextResult;
			}
		}
		return result;
	}
	
	public void appendCondition(YarnCondition condition) {
		conditions.add(condition);
	}
	
	public void appendOperator(BoolOperatorContext ctx) {
		operators.add(ctx);
	}

	public IfStatementType getStatementType() {
		return statementType;
	}

	public void setFailureOperationIndex(int failureOperationIndex) {
		this.failureOperationIndex = failureOperationIndex;
	}

	public int getEndBlockOperationindex() {
		return endBlockOperationindex;
	}

	public void setEndBlockOperationindex(int endBlockOperationindex) {
		this.endBlockOperationindex = endBlockOperationindex;
	}
	
	public enum IfStatementType {
		IF,
		ELSEIF,
		ELSE
	}
}
