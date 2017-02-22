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
import org.mini2Dx.yarn.types.YarnValue;
import org.mini2Dx.yarn.variable.BooleanVariable;
import org.mini2Dx.yarn.variable.NumberVariable;
import org.mini2Dx.yarn.variable.StringVariable;
import org.mini2Dx.yarn.variable.YarnType;
import org.mini2Dx.yarn.variable.YarnVariable;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

/**
 *
 */
public class YarnState {
	private final Map<String, YarnVariable> variables = new HashMap<String, YarnVariable>();
	
	private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();
	private final Map<String, Mustache> mustacheCache = new HashMap<String, Mustache>();
	private final Map<String, Object> variablesMustache = new HashMap<String, Object>();
	
	private String currentNode;
	private int currentOperation;
	
	public Collection<YarnVariable> getVariables() {
		return variables.values();
	}
	
	public YarnVariable get(String variableName) {
		return variables.get(variableName);
	}
	
	public YarnVariable put(String variableName, double value) {
		if(!variables.containsKey(variableName)) {
			NumberVariable result = new NumberVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if(variable.getType() != YarnType.NUMBER) {
			variable = new NumberVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			NumberVariable numberVariable = (NumberVariable) variable;
			numberVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}
	
	public YarnVariable put(String variableName, boolean value) {
		if(!variables.containsKey(variableName)) {
			BooleanVariable result = new BooleanVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if(variable.getType() != YarnType.BOOLEAN) {
			variable = new BooleanVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			BooleanVariable booleanVariable = (BooleanVariable) variable;
			booleanVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}
	
	public YarnVariable put(String variableName, String value) {
		if(!variables.containsKey(variableName)) {
			StringVariable result = new StringVariable(variableName, value);
			variables.put(variableName, result);
			variablesMustache.put(variableName, value);
			return result;
		}
		YarnVariable variable = variables.get(variableName);
		if(variable.getType() != YarnType.STRING) {
			variable = new StringVariable(variableName, value);
			variables.put(variableName, variable);
		} else {
			StringVariable stringVariable = (StringVariable) variable;
			stringVariable.setValue(value);
		}
		variablesMustache.put(variableName, value);
		return variable;
	}
	
	public YarnVariable put(String variableName, YarnVariable value) {
		switch(value.getType()) {
		case BOOLEAN:
			return put(variableName, ((BooleanVariable) value).getValue());
		case NUMBER:
			return put(variableName, ((NumberVariable) value).getValue());
		case STRING:
		default:
			return put(variableName, ((StringVariable) value).getValue());
		}
	}
	
	public YarnVariable putNull(String variableName) {
		String value = null;
		return put(variableName, value);
	}
	
	public YarnVariable put(String variableName, YarnValue value) {
		switch(value.getType()) {
		case BOOLEAN:
			return put(variableName, ((YarnBoolean) value).getValue());
		case NUMBER:
			return put(variableName, ((YarnNumber) value).getValue());
		case STRING:
		default:
			return put(variableName, ((YarnString) value).getValue());
		}
	}
	
	public String getCurrentNode() {
		return currentNode;
	}
	
	public void setCurrentNode(String currentNode) {
		if(currentNode == null) {
			return;
		}
		if(currentNode.equals(this.currentNode)) {
			return;
		}
		this.currentNode = currentNode;
		this.currentOperation = 0;
	}
	
	public int getCurrentOperation() {
		return currentOperation;
	}
	
	public void setCurrentOperation(int currentOperation) {
		if(currentOperation < 0) {
			return;
		}
		this.currentOperation = currentOperation;
	}
	
	public String applyVariables(String text) {
		if(!mustacheCache.containsKey(text)) {
			mustacheCache.put(text, mustacheFactory.compile(new StringReader(text), text));
		}
		Mustache mustache = mustacheCache.get(text);
		
		StringWriter result = new StringWriter();
		mustache.execute(result, variablesMustache);
		return result.toString();
	}
}
