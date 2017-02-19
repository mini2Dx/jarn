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

import java.util.List;

import org.mini2Dx.yarn.YarnExecutionListener;
import org.mini2Dx.yarn.YarnState;
import org.mini2Dx.yarn.execution.YarnExecutionException;

/**
 * Base class for Yarn operations
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
	 * @param yarnState The current {@link YarnState}
	 * @param listeners A list {@link YarnExecutionException} to be notified of events
	 * @return The index of the next operation to execute, -1 if this operation is on-going
	 * @throws YarnExecutionException Thrown if an error occurs during execution
	 */
	public abstract int resume(YarnState yarnState, List<YarnExecutionListener> listeners) throws YarnExecutionException;
	
	/**
	 * The index for this operation
	 * @return The index of the operation in the node's list of operations
	 */
	public int getOperationIndex() {
		return operationIndex;
	}
	
	/**
	 * The line number in the node corresponding to this operation
	 * @return The line number (0 = first line)
	 */
	public int getLineNumber() {
		return lineNumber;
	}
}
