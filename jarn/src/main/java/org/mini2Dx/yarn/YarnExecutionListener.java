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
package org.mini2Dx.yarn;

import org.mini2Dx.yarn.operation.YarnOptionGroup;

/**
 * Interface for handling Yarn execution events
 */
public interface YarnExecutionListener {

	/**
	 * Called when a command is reached
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param command
	 *            The command to run
	 */
	public void onYarnCommand(YarnState state, String command);

	/**
	 * Called when a variable is assigned a new value
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param variableName
	 *            The variable that was assigned
	 */
	public void onYarnVariableAssigned(YarnState state, String variableName);

	/**
	 * Called when a line is reached
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param character
	 *            The character name or null if there is no character for this
	 *            line
	 * @param text
	 *            The speech or text for this line
	 */
	public void onYarnLine(YarnState state, String character, String text);

	/**
	 * Called when the player must select an option<br>
	 * <br>
	 * Note: There may be only one option.
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param optionGroup
	 *            The {@link YarnOptionGroup} containing the options
	 */
	public void onYarnOptionGroup(YarnState state, YarnOptionGroup optionGroup);

	/**
	 * Called when execution has reached the end of a tree
	 * 
	 * @param state
	 *            The current {@link YarnState}
	 * @param yarnNode
	 *            The {@link YarnNode} that the tree finished on
	 */
	public void onYarnTreeEnd(YarnState state, YarnNode yarnNode);
}
