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

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.mini2Dx.yarn.types.YarnBoolean;
import org.mini2Dx.yarn.types.YarnNumber;
import org.mini2Dx.yarn.types.YarnString;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;
import org.mini2Dx.yarn.variable.BooleanVariable;
import org.mini2Dx.yarn.variable.NumberVariable;
import org.mini2Dx.yarn.variable.StringVariable;
import org.mini2Dx.yarn.variable.YarnVariable;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 * Stores the current state of execution through a {@link YarnTree}
 */
public class YarnState {
	private final Map<String, YarnVariable> variables = new HashMap<String, YarnVariable>();

	private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();
	private final Map<String, Mustache> mustacheCache = new HashMap<String, Mustache>();
	private final Map<String, Object> variablesMustache = new HashMap<String, Object>();

	private String currentNode;
	private int currentOperation;

	/**
	 * Returns all variables stored in the current state
	 * 
	 * @return An empty {@link Collection} if none are stored
	 */
	public Collection<YarnVariable> getVariables() {
		return variables.values();
	}

	/**
	 * Returns a {@link YarnVariable} with a given name
	 * 
	 * @param variableName
	 *            The name of the variable
	 * @return Null if there is no such variable
	 */
	public YarnVariable get(String variableName) {
		return variables.get(variableName);
	}

	/**
	 * Assigns a double value to a variable.<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was previously a boolean or string then a new
	 * {@link YarnVariable} instance is created and the previous one is
	 * discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @param value
	 *            The value to assign
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable put(String variableName, double value) {
		if (!variables.containsKey(variableName)) {
			NumberVariable result = new NumberVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if (variable.getType() != YarnType.NUMBER) {
			variable = new NumberVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			NumberVariable numberVariable = (NumberVariable) variable;
			numberVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}

	/**
	 * Assigns a boolean value to a variable.<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was previously a number or string then a new
	 * {@link YarnVariable} instance is created and the previous one is
	 * discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @param value
	 *            The value to assign
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable put(String variableName, boolean value) {
		if (!variables.containsKey(variableName)) {
			BooleanVariable result = new BooleanVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if (variable.getType() != YarnType.BOOLEAN) {
			variable = new BooleanVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			BooleanVariable booleanVariable = (BooleanVariable) variable;
			booleanVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}

	/**
	 * Assigns a String value to a variable.<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was previously a number or boolean then a new
	 * {@link YarnVariable} instance is created and the previous one is
	 * discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @param value
	 *            The value to assign
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable put(String variableName, String value) {
		if (!variables.containsKey(variableName)) {
			StringVariable result = new StringVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if (variable.getType() != YarnType.STRING) {
			variable = new StringVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			StringVariable stringVariable = (StringVariable) variable;
			stringVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}

	/**
	 * Assigns the value of another {@link YarnVariable} to a variable<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was a different type then a new {@link YarnVariable} instance is
	 * created and the previous one is discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @param value
	 *            The value to assign
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable put(String variableName, YarnVariable value) {
		switch (value.getType()) {
		case BOOLEAN:
			return put(variableName, ((BooleanVariable) value).getValue());
		case NUMBER:
			return put(variableName, ((NumberVariable) value).getValue());
		case STRING:
		default:
			return put(variableName, ((StringVariable) value).getValue());
		}
	}

	/**
	 * Assigns null to a variable.<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was previously a boolean or number then a new
	 * {@link YarnVariable} instance is created and the previous one is
	 * discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable putNull(String variableName) {
		String value = null;
		return put(variableName, value);
	}

	/**
	 * Assigns a {@link YarnValue} to a variable<br>
	 * <br>
	 * Note: This overwrites any existing value assigned to the variable. If the
	 * variable was a different type then a new {@link YarnVariable} instance is
	 * created and the previous one is discarded.
	 * 
	 * @param variableName
	 *            The variable name
	 * @param value
	 *            The value to assign
	 * @return The existing or new {@link YarnVariable} for the variable
	 */
	public YarnVariable put(String variableName, YarnValue value) {
		switch (value.getType()) {
		case BOOLEAN:
			return put(variableName, ((YarnBoolean) value).getValue());
		case NUMBER:
			return put(variableName, ((YarnNumber) value).getValue());
		case STRING:
		default:
			return put(variableName, ((YarnString) value).getValue());
		}
	}

	/**
	 * Returns the current node to execute against
	 * 
	 * @return
	 */
	public String getCurrentNode() {
		return currentNode;
	}

	/**
	 * Sets the current {@link YarnNode} to execute against<br>
	 * <br>
	 * Note: This will reset the operation number to 0
	 * 
	 * @param currentNode
	 *            The name of the {@link YarnNode}
	 */
	public void setCurrentNode(String currentNode) {
		if (currentNode == null) {
			return;
		}
		if (currentNode.equals(this.currentNode)) {
			return;
		}
		this.currentNode = currentNode;
		this.currentOperation = 0;
	}

	/**
	 * Returns the current operation index within the current {@link YarnNode}
	 * 
	 * @return
	 */
	public int getCurrentOperation() {
		return currentOperation;
	}

	/**
	 * Sets the current operation index within the current {@link YarnNode}
	 * 
	 * @param currentOperation
	 */
	public void setCurrentOperation(int currentOperation) {
		if (currentOperation < 0) {
			return;
		}
		this.currentOperation = currentOperation;
	}

	/**
	 * Replaces any {{ }} wrapped variable placeholders in a {@link String} with
	 * the current value in this {@link YarnState}
	 * 
	 * @param text
	 *            The {@link String} to replace variable placeholders in
	 * @return The {@link String} with variable values inserted
	 */
	public String applyVariables(String text) {
		if (!mustacheCache.containsKey(text)) {
			mustacheCache.put(text, mustacheFactory.compile(new StringReader(text), text));
		}
		Mustache mustache = mustacheCache.get(text);

		StringWriter result = new StringWriter();
		mustache.execute(result, variablesMustache);
		return result.toString();
	}
}
