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

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.mini2Dx.yarn.operation.YarnOptionGroup;
import org.mini2Dx.yarn.parser.YarnParserTest;

/**
 * Integration tests for {@link YarnTree}
 */
public class YarnTreeIntegrationTest implements YarnExecutionListener {
	private final YarnTree yarnTree = new YarnTree();
	private final Queue<String> expectedCommands = new LinkedList<String>();
	private final Queue<String> expectedLines = new LinkedList<String>();
	
	@Before
	public void setUp() throws IOException {
		yarnTree.load(new InputStreamReader(YarnParserTest.class.getResourceAsStream("/tree-integration-test.txt")));
	}

	@Override
	public void onYarnCommand(String command) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onYarnLine(String character, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onYarnOptionGroup(YarnOptionGroup optionGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onYarnTreeEnd(YarnNode yarnNode) {
		// TODO Auto-generated method stub
		
	}

	
}
