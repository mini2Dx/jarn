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
package org.mini2Dx.yarn.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.mini2Dx.yarn.YarnNode;
import org.mini2Dx.yarn.operation.YarnCommand;
import org.mini2Dx.yarn.operation.YarnLine;
import org.mini2Dx.yarn.operation.YarnOption;
import org.mini2Dx.yarn.operation.YarnOptionGroup;
import org.mini2Dx.yarn.parser.YarnParser.CommandStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.ElseStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.ElseifExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.EndifStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.IfExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.LineStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.NodeContext;
import org.mini2Dx.yarn.parser.YarnParser.OptionGroupContext;
import org.mini2Dx.yarn.parser.YarnParser.OptionStatementContext;

/**
 *
 */
public class YarnTreeParser extends YarnBaseListener {
	private YarnNode currentNode;

	public List<YarnNode> read(Reader reader) throws IOException {
		List<YarnNode> result = new ArrayList<YarnNode>();

		Scanner scanner = new Scanner(reader);
		while (scanner.hasNextLine()) {
			currentNode = readNodeHeader(scanner);

			StringBuilder nodeContent = new StringBuilder();
			while (scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if (nextLine.startsWith("===")) {
					break;
				}
				nodeContent.append(nextLine);
				nodeContent.append('\n');
			}
			parseNodeContent(nodeContent.toString());
			result.add(currentNode);
		}
		scanner.close();
		return result;
	}

	private YarnNode readNodeHeader(Scanner scanner) {
		String title = scanner.nextLine().replace("title:", "").trim();
		String[] tags = scanner.nextLine().replace("tags:", "").split(" ");
		// Skip color
		scanner.nextLine();
		// Skip position
		scanner.nextLine();
		// Skip header end
		scanner.nextLine();
		return new YarnNode(title, tags);
	}

	private void parseNodeContent(String nodeContent) throws IOException {
		YarnLexer yarnLexer = new YarnLexer(new ANTLRInputStream(nodeContent));
		YarnParser yarnParser = new YarnParser(new BufferedTokenStream(yarnLexer));
		NodeContext nodeContext = yarnParser.node();

		ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
		parseTreeWalker.walk(this, nodeContext);
	}

	@Override
	public void exitLineStatement(LineStatementContext ctx) {
		YarnLine line = null;
		if (ctx.characterExpression() != null) {
			line = new YarnLine(currentNode.getTotalOperations(), ctx.getStart().getLine(),
					ctx.characterExpression().getText().trim(), ctx.textExpression().getText().trim());
		} else {
			line = new YarnLine(currentNode.getTotalOperations(), ctx.getStart().getLine(),
					ctx.textExpression().getText().trim());
		}
		currentNode.appendOperation(line);
	}

	@Override
	public void exitIfExpression(IfExpressionContext ctx) {

	}

	@Override
	public void exitElseifExpression(ElseifExpressionContext ctx) {

	}

	@Override
	public void exitElseStatement(ElseStatementContext ctx) {

	}

	@Override
	public void exitEndifStatement(EndifStatementContext ctx) {

	}

	@Override
	public void exitCommandStatement(CommandStatementContext ctx) {
		if (ctx.assignExpression() != null) {

		} else {
			YarnCommand command = new YarnCommand(currentNode.getTotalOperations(), ctx.getStart().getLine(),
					ctx.textExpression().getText().trim());
			currentNode.appendOperation(command);
		}
	}

	@Override
	public void exitOptionGroup(OptionGroupContext ctx) {
		YarnOptionGroup optionGroup = new YarnOptionGroup(currentNode.getTotalOperations(), ctx.getStart().getLine());

		for (int i = 0; i < ctx.optionStatement().size(); i++) {
			OptionStatementContext optionContext = ctx.optionStatement().get(i);

			YarnOption option = null;
			if (optionContext.textExpression() != null) {
				option = new YarnOption(i, optionContext.textExpression().getText().trim(),
						optionContext.TARGETNAME().getText().trim());
			} else {
				option = new YarnOption(i, optionContext.TARGETNAME().getText().trim());
			}
			optionGroup.getOptions().add(option);
		}
		currentNode.appendOperation(optionGroup);
	}
}