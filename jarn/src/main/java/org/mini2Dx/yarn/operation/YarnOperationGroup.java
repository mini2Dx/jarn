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

/**
 * Groups several {@link YarnOperation}
 */
public abstract class YarnOperationGroup extends YarnOperation {
	protected final List<YarnOperation> operations = new ArrayList<YarnOperation>(2);

	public YarnOperationGroup(int operationIndex, int lineNumber) {
		super(operationIndex, lineNumber);
	}
	
	@Override
	protected <T extends YarnOperation> void getOperationsOfType(Class<T> clazz, List<T> result) {
		for(YarnOperation operation : operations) {
			operation.getOperationsOfType(clazz, result);
		}
	}

	public void appendOperation(YarnOperation operation) {
		operations.add(operation);
	}
	
	public int getTotalOperations() {
		return operations.size();
	}
}
