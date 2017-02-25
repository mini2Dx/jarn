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

/**
 * Represents a command operation in Yarn<br>
 * <br>
 * Example: &lt;&lt;move bob to bridge&gt;&gt;
 */
public class YarnCommand extends YarnOperation {
	private final String command;

	public YarnCommand(int operationIndex, int lineNumber, String command) {
		super(operationIndex, lineNumber);
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	@Override
	public int resume(YarnState yarnState, List<YarnExecutionListener> listeners) {
		notifyListeners(yarnState, listeners);
		return operationIndex + 1;
	}
	
	private void notifyListeners(YarnState yarnState, List<YarnExecutionListener> listeners) {
		for(int i = listeners.size() - 1; i >= 0; i--) {
			listeners.get(i).onYarnCommand(yarnState.applyVariables(command));
		}
	}
}
