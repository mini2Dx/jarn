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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;
import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.operation.YarnCommand;
import org.mini2Dx.yarn.operation.YarnIfStatement;
import org.mini2Dx.yarn.operation.YarnLine;
import org.mini2Dx.yarn.operation.YarnOptionGroup;
import org.mini2Dx.yarn.parser.YarnParserTest;
import org.mini2Dx.yarn.parser.YarnTreeParser;

import junit.framework.Assert;

/**
 * Integration tests for {@link YarnNode}
 */
public class YarnNodeIntegrationTest implements YarnExecutionListener {
	private final YarnTreeParser treeParser = new YarnTreeParser();
	private final List<YarnExecutionListener> listeners = new ArrayList<YarnExecutionListener>();
	
	private final Queue<String> expectedCommands = new LinkedList<String>();
	private final Queue<String> expectedLines = new LinkedList<String>();
	
	private YarnNode yarnNode;
	private boolean nodeComplete = false;
	
	@Before
	public void setUp() throws IOException {
		List<YarnNode> nodes = treeParser.read(new InputStreamReader(YarnParserTest.class.getResourceAsStream("/node-integration-test.txt")));
		yarnNode = nodes.get(0);
		listeners.add(this);
	}
	
	@Test
	public void testGetOperationsOfType() {
		Assert.assertEquals(1, yarnNode.getOperationsOfType(YarnCommand.class).size());
		Assert.assertEquals(4, yarnNode.getOperationsOfType(YarnLine.class).size());
		Assert.assertEquals(3, yarnNode.getOperationsOfType(YarnIfStatement.class).size());
	}

	@Test
	public void testIfBlock() throws YarnExecutionException {
		YarnState yarnState = new YarnState();
		yarnState.put("$keys", 8);
		yarnState.put("$door_unlocked", false);
		
		expectedCommands.offer("move bob to wall");
		expectedLines.offer("Bob: You win the game with 8.0 keys!");
		expectedLines.offer("End of node!");
		
		runTest(yarnState);
		
		Assert.assertTrue(expectedCommands.isEmpty());
		Assert.assertTrue(expectedLines.isEmpty());
		Assert.assertTrue(nodeComplete);
	}
	
	@Test
	public void testElseIfBlockWithFirstConditionTrue() throws YarnExecutionException {
		YarnState yarnState = new YarnState();
		yarnState.put("$keys", 0);
		yarnState.put("$door_unlocked", true);
		
		expectedCommands.offer("move bob to wall");
		expectedLines.offer("You lose the game!");
		expectedLines.offer("End of node!");
		
		runTest(yarnState);
		
		Assert.assertTrue(expectedCommands.isEmpty());
		Assert.assertTrue(expectedLines.isEmpty());
		Assert.assertTrue(nodeComplete);
	}
	
	@Test
	public void testElseIfBlockWithFirstConditionFalse() throws YarnExecutionException {
		YarnState yarnState = new YarnState();
		yarnState.put("$keys", 0);
		yarnState.put("$door_unlocked", false);
		
		expectedCommands.offer("move bob to wall");
		expectedLines.offer("You lose the game!");
		expectedLines.offer("End of node!");
		
		runTest(yarnState);
		
		Assert.assertTrue(expectedCommands.isEmpty());
		Assert.assertTrue(expectedLines.isEmpty());
		Assert.assertTrue(nodeComplete);
	}
	
	@Test(expected=YarnExecutionException.class)
	public void testElseIfBlockWithMissingVariable() throws YarnExecutionException {
		YarnState yarnState = new YarnState();
		yarnState.put("$keys", 0);
		
		expectedCommands.offer("move bob to wall");
		expectedLines.offer("You lose the game!");
		expectedLines.offer("End of node!");
		
		runTest(yarnState);
	}
	
	@Test
	public void testElse() throws YarnExecutionException {
		YarnState yarnState = new YarnState();
		yarnState.put("$keys", 1);
		yarnState.put("$door_unlocked", false);
		
		expectedCommands.offer("move bob to wall");
		expectedLines.offer("You failed!");
		expectedLines.offer("End of node!");
		
		runTest(yarnState);
		
		Assert.assertTrue(expectedCommands.isEmpty());
		Assert.assertTrue(expectedLines.isEmpty());
		Assert.assertTrue(nodeComplete);
	}

	@Override
	public void onYarnCommand(YarnState state,String command) {
		Assert.assertEquals(expectedCommands.poll(), command);
	}

	@Override
	public void onYarnLine(YarnState state, String character, String text) {
		if(character != null) {
			Assert.assertEquals(expectedLines.poll(), character + " " + text);
		} else {
			Assert.assertEquals(expectedLines.poll(), text);
		}
	}

	@Override
	public void onYarnOptionGroup(YarnState state,YarnOptionGroup optionGroup) {
	}

	@Override
	public void onYarnTreeEnd(YarnState state, YarnNode yarnNode) {
		Assert.assertEquals(this.yarnNode.getTitle(), yarnNode.getTitle());
		nodeComplete = true;
	}
	
	private void runTest(YarnState yarnState) throws YarnExecutionException {
		while(!nodeComplete) {
			int result = yarnNode.resume(yarnState, listeners);
			if(result < 0) {
				continue;
			}
			yarnState.setCurrentOperation(result);
		}
	}

	@Override
	public void onYarnVariableAssigned(YarnState state, String variableName) {
	}
}
