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

import org.mini2Dx.yarn.types.YarnType;
import org.mini2Dx.yarn.types.YarnValue;

/**
 * Base class for Yarn variables
 */
public abstract class YarnVariable implements YarnValue {
	private final String name;
	private final YarnType type;
	
	/**
	 * Constructor
	 * @param name The variable name
	 * @param type The variable type
	 */
	public YarnVariable(String name, YarnType type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * Returns the variable name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the variable type
	 * @return
	 */
	public YarnType getType() {
		return type;
	}
}
