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
import org.mini2Dx.yarn.literal.NumberLiteral;
import org.mini2Dx.yarn.types.YarnNumber;
import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;

/**
 * Represents a Yarn variable of type number
 */
public class NumberVariable extends YarnVariable implements YarnNumber {
	private double value;
	
	public NumberVariable(String name, double value) {
		super(name, YarnType.NUMBER);
		this.value = value;
	}

	@Override
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public YarnNumber add(double value) {
		return new NumberLiteral(this.value + value);
	}

	@Override
	public YarnNumber subtract(double value) {
		return new NumberLiteral(this.value - value);
	}

	@Override
	public YarnNumber multiply(double value) {
		return new NumberLiteral(this.value * value);
	}

	@Override
	public YarnNumber divide(double value) {
		return new NumberLiteral(this.value / value);
	}
	
	@Override
	public YarnNumber modulus(double value) {
		return new NumberLiteral(this.value % value);
	}

	@Override
	public int compareTo(YarnValue o) {
		switch(o.getType()) {
		case NUMBER:
			return Double.compare(value, ((YarnNumber) o).getValue());
		case BOOLEAN:
		case STRING:
		default:
			throw new YarnTypeMismatchException("compare", getType(), o.getType());
		}
	}
}
