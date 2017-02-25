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
package org.mini2Dx.yarn.literal;

import org.mini2Dx.yarn.execution.YarnTypeMismatchException;
import org.mini2Dx.yarn.types.YarnBoolean;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;

/**
 * Represents a Yarn literal boolean value in expressions
 */
public class BooleanLiteral implements YarnBoolean {
	private final boolean value;

	public BooleanLiteral(boolean value) {
		super();
		this.value = value;
	}

	@Override
	public boolean getValue() {
		return value;
	}

	@Override
	public YarnBoolean and(boolean value) {
		return new BooleanLiteral(this.value && value);
	}

	@Override
	public YarnBoolean or(boolean value) {
		return new BooleanLiteral(this.value || value);
	}
	
	@Override
	public int compareTo(YarnValue o) {
		switch(o.getType()) {
		case BOOLEAN:
			return Boolean.compare(value, ((YarnBoolean) o).getValue());
		case NUMBER:
		case STRING:
		default:
			throw new YarnTypeMismatchException("compare", getType(), o.getType());
		}
	}

	@Override
	public YarnType getType() {
		return YarnType.BOOLEAN;
	}
}
