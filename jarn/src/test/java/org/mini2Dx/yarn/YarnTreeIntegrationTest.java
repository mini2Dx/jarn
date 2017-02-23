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
import org.junit.Test;
import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.operation.YarnOption;
import org.mini2Dx.yarn.operation.YarnOptionGroup;
import org.mini2Dx.yarn.parser.YarnParserTest;
import org.mini2Dx.yarn.types.YarnNumber;

import junit.framework.Assert;

/**
 * Integration tests for {@link YarnTree}
 */
public class YarnTreeIntegrationTest implements YarnExecutionListener {
	private final YarnTree yarnTree = new YarnTree();
	private final YarnState yarnState = new YarnState();
	
	private final Queue<String> expectedCommands = new LinkedList<String>();
	private final Queue<String> expectedLines = new LinkedList<String>();
	private final Queue<String> expectedOptions = new LinkedList<String>();
	
	private boolean treeComplete = false;
	
	@Before
	public void setUp() throws IOException {
		yarnTree.load(new InputStreamReader(YarnParserTest.class.getResourceAsStream("/tree-integration-test.txt")));
		yarnTree.addListener(this);
		yarnState.setCurrentNode("Start");
	}
	
	@Test
	public void testDirectPathToNode2() throws YarnExecutionException {
		expectedCommands.offer("start game");
		expectedLines.offer("The game begins.");
		expectedOptions.offer("Node2");
		expectedLines.offer("The game ends.");
		expectedCommands.offer("end game");
		
		runTest();
		
		Assert.assertEquals(true, treeComplete);
		Assert.assertNull(yarnState.get("$keys"));
		
		Assert.assertEquals(true, expectedCommands.isEmpty());
		Assert.assertEquals(true, expectedLines.isEmpty());
		Assert.assertEquals(true, expectedOptions.isEmpty());
	}
	
	@Test
	public void testPathToNode2ViaNode1() throws YarnExecutionException {
		expectedCommands.offer("start game");
		expectedLines.offer("The game begins.");
		expectedOptions.offer("Node1");
		expectedLines.offer("Bob: This is exciting! I have 1.0 keys!");
		expectedCommands.offer("alter game");
		expectedOptions.offer("Node2");
		expectedLines.offer("The game ends.");
		expectedCommands.offer("end game");
		
		runTest();
		
		Assert.assertEquals(true, treeComplete);
		Assert.assertEquals(1.0, ((YarnNumber) yarnState.get("$keys")).getValue());
		
		Assert.assertEquals(true, expectedCommands.isEmpty());
		Assert.assertEquals(true, expectedLines.isEmpty());
		Assert.assertEquals(true, expectedOptions.isEmpty());
	}

	@Override
	public void onYarnCommand(String command) {
		Assert.assertEquals(expectedCommands.poll(), command);
	}

	@Override
	public void onYarnLine(String character, String text) {
		if(character != null) {
			Assert.assertEquals(expectedLines.poll(), character + " " + text);
		} else {
			Assert.assertEquals(expectedLines.poll(), text);
		}
	}

	@Override
	public void onYarnOptionGroup(YarnOptionGroup optionGroup) {
		String expectedOption = expectedOptions.poll();
		
		StringBuilder returnedOptions = new StringBuilder();
		for(int i = 0; i < optionGroup.getOptions().size(); i++) {
			YarnOption option = optionGroup.getOptions().get(i);
			returnedOptions.append(option.getLabel() + "|" + option.getNode() + "\n");
			if(option.getLabel().equals(expectedOption)) {
				yarnState.setCurrentNode(option.getNode());
				return;
			}
			if(option.getNode().equals(expectedOption)) {
				yarnState.setCurrentNode(option.getNode());
				return;
			}
		}
		
		Assert.fail("Could not find option " + expectedOption + ":\n" + returnedOptions.toString());
	}

	@Override
	public void onYarnTreeEnd(YarnNode yarnNode) {
		Assert.assertEquals("Node2", yarnNode.getTitle());
		treeComplete = true;
	}

	private void runTest() throws YarnExecutionException {
		while(!treeComplete) {
			yarnTree.resume(yarnState);
		}
	}
}
