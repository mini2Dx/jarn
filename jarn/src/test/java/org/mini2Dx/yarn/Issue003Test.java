/**
 * MIT License
 *
 * Copyright (c) 2018 Thomas Cashman
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.operation.YarnOptionGroup;
import org.mini2Dx.yarn.parser.YarnParserException;
import org.mini2Dx.yarn.parser.YarnParserTest;
import org.mini2Dx.yarn.parser.YarnTreeParser;
import org.mini2Dx.yarn.variable.NumberVariable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for reproducing issue #3 reported by <a href="https://github.com/tymerica">tymerica</a>
 */
public class Issue003Test implements YarnExecutionListener {
    private final YarnTreeParser treeParser = new YarnTreeParser();
    private final List<YarnExecutionListener> listeners = new ArrayList<YarnExecutionListener>();

    private YarnNode yarnNode;
    private boolean nodeComplete = false;

    @Before
    public void setUp() throws IOException {
        listeners.add(this);
    }

    @Test(expected = YarnParserException.class)
    public void testIssue003Original() throws Exception {
        treeParser.read(new InputStreamReader(YarnParserTest.class.getResourceAsStream("/issue-003-node-original.txt")));
    }

    @Test
    public void testIssue003Modified() throws Exception {
        List<YarnNode> nodes = treeParser.read(new InputStreamReader(YarnParserTest.class.getResourceAsStream("/issue-003-node-modified.txt")));
        yarnNode = nodes.get(0);

        final YarnState yarnState = new YarnState();
        runTest(yarnState);

        Assert.assertEquals(1, ((NumberVariable) yarnState.get("$gold")).getValue(), 0f);
    }

    @Override
    public void onYarnCommand(YarnState state, String command) {
    }

    @Override
    public void onYarnVariableAssigned(YarnState state, String variableName) {

    }

    @Override
    public void onYarnLine(YarnState state, String character, String text) {

    }

    @Override
    public void onYarnOptionGroup(YarnState state, YarnOptionGroup optionGroup) {

    }

    @Override
    public void onYarnTreeEnd(YarnState state, YarnNode yarnNode) {
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


}
