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
package org.mini2Dx.yarn.variable;

import org.mini2Dx.yarn.execution.YarnTypeMismatchException;
import org.mini2Dx.yarn.types.YarnString;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;

/**
 * Represents a Yarn variable of type string
 */
public class StringVariable extends YarnVariable implements YarnString {
	private String value;

	public StringVariable(String name, String value) {
		super(name, YarnType.STRING);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(YarnValue o) {
		switch(o.getType()) {
		case STRING:
			YarnString oString = (YarnString) o;
			if(value == null) {
				if(oString.getValue() != null) {
					return -1;
				}
				return 0;
			} else if(oString.getValue() == null) {
				return 1;
			}
			return value.compareTo(oString.getValue());
		case BOOLEAN:
		case NUMBER:
		default:
			throw new YarnTypeMismatchException("compare", getType(), o.getType());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringVariable other = (StringVariable) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
