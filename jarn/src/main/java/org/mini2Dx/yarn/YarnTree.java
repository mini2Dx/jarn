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
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mini2Dx.yarn.execution.YarnExecutionException;
import org.mini2Dx.yarn.parser.YarnTreeParser;

/**
 * A dialogue tree built in Yarn
 */
public class YarnTree {
	private final Map<String, YarnNode> nodes = new HashMap<String, YarnNode>();
	private final List<YarnExecutionListener> listeners = new ArrayList<YarnExecutionListener>(1);
	private YarnTreeParser treeParser = new YarnTreeParser();

	private boolean noListenersWarningIssued = false;

	/**
	 * Reads a .yarn.txt file add its nodes to this tree. Note that nodes are
	 * mapped by their title meaning nodes with the same title will overwrite
	 * each other.
	 * 
	 * @param reader
	 *            The {@link Reader} to read the file from
	 * @throws IOException
	 *             Thrown if there is an error deserialising the file
	 */
	public void load(Reader reader) throws IOException {
		List<YarnNode> nodes = treeParser.read(reader);
		for (YarnNode node : nodes) {
			this.nodes.put(node.getTitle(), node);
		}
	}
	
	/**
	 * Returns if this tree contains a specific {@link YarnNode}
	 * @param nodeName The node name to check for
	 * @return True if the tree contains a {@link YarnNode} with the specified name
	 */
	public boolean containsNode(String nodeName) {
		return nodes.containsKey(nodeName);
	}

	/**
	 * Removes all {@link YarnNode} instances from this tree
	 */
	public void clearNodes() {
		nodes.clear();
	}

	/**
	 * Resumes the execution the of the dialogue tree
	 * @param state The current {@link YarnState}
	 * @throws YarnExecutionException Thrown if Yarn expressions could not be resolved
	 */
	public void resume(YarnState state) throws YarnExecutionException {
		if (listeners.isEmpty() && !noListenersWarningIssued) {
			System.err.println("WARNING: YarnTree instance has no listeners attached.");
			noListenersWarningIssued = true;
		}
		if (state.getCurrentNode() == null) {
			throw new YarnExecutionException("No current node is set in the YarnState");
		}
		YarnNode currentNode = nodes.get(state.getCurrentNode());
		if(currentNode == null) {
			throw new YarnExecutionException("No such node '" + state.getCurrentNode() + "'");
		}
		int nextOperation = currentNode.resume(state, listeners);
		if (nextOperation < 0) {
			return;
		}
		state.setCurrentOperation(nextOperation);
	}

	/**
	 * Adds a {@link YarnExecutionListener} to this tree
	 * 
	 * @param listener
	 *            The {@link YarnExecutionListener} to add
	 */
	public void addListener(YarnExecutionListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes a {@link YarnExecutionListener} from this tree
	 * 
	 * @param listener
	 *            The {@link YarnExecutionListener} to remove
	 */
	public void removeListener(YarnExecutionListener listener) {
		listeners.remove(listener);
		noListenersWarningIssued = false;
	}

	/**
	 * Removes all {@link YarnExecutionListener} instances from this tree
	 */
	public void clearListeners() {
		listeners.clear();
	}
}
