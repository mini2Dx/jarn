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
package org.mini2Dx.yarn.types;

/**
 * Base interface for Yarn numbers
 */
public interface YarnNumber extends YarnValue {
	/**
	 * Returns the underlying double value
	 * @return
	 */
	public double getValue();
	
	/**
	 * Returns the result of addition with another value
	 * @param value The value to add
	 * @return A new {@link YarnNumber} containing the result
	 */
	public YarnNumber add(double value);
	
	/**
	 * Returns the result of subtraction with another value
	 * @param value The value to subtract
	 * @return A new {@link YarnNumber} containing the result
	 */
	public YarnNumber subtract(double value);
	
	/**
	 * Returns the result of multiplication with another value
	 * @param value The value to multiply by
	 * @return A new {@link YarnNumber} containing the result
	 */
	public YarnNumber multiply(double value);
	
	/**
	 * Returns the result of division with another value
	 * @param value The value to divide by
	 * @return A new {@link YarnNumber} containing the result
	 */
	public YarnNumber divide(double value);
	
	/**
	 * Returns the result of modulus with another value
	 * @param value The value to modulus by
	 * @return A new {@link YarnNumber} containing the result
	 */
	public YarnNumber modulus(double value);
}
