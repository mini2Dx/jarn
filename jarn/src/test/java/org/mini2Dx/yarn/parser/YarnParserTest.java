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

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.mini2Dx.yarn.parser.YarnParser.AssignExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.CommandStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.ElseStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.ElseifExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.EndifStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.IfExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.LineStatementContext;
import org.mini2Dx.yarn.parser.YarnParser.NodeContext;
import org.mini2Dx.yarn.parser.YarnParser.NumericOperationExpressionContext;
import org.mini2Dx.yarn.parser.YarnParser.OptionGroupContext;
import org.mini2Dx.yarn.parser.YarnParser.OptionStatementContext;

import junit.framework.Assert;

/**
 * Unit tests for {@link YarnParser}
 */
public class YarnParserTest extends YarnBaseListener{
	private int totalLines, totalCharacters, totalSpeech;
	private int totalCommands, totalAssignments;
	private int totalIfs, totalElseIfs, totalElses, totalEndIfs, totalOperators;
	private int totalNumericOperations, totalPlus, totalMinus, totalMultiply, totalDivide;
	private int totalOptionGroups, totalOptions, totalOptionLabels, totalOptionNodes;

	@Test
	public void testBasicNode() throws Exception {
		runTest("basic-node.txt");
		
		Assert.assertEquals(2, totalLines);
		Assert.assertEquals(2, totalCharacters);
		Assert.assertEquals(2, totalSpeech);
	}
	
	@Test
	public void testCommandsNode() throws Exception {
		runTest("command-node.txt");
		
		Assert.assertEquals(1, totalLines);
		Assert.assertEquals(1, totalCharacters);
		Assert.assertEquals(1, totalSpeech);
		
		Assert.assertEquals(1, totalCommands);
	}
	
	@Test
	public void testExpressionsNode() throws Exception {
		runTest("expressions-node.txt");
		
		Assert.assertEquals(3, totalLines);
		Assert.assertEquals(1, totalCharacters);
		Assert.assertEquals(3, totalSpeech);
		
		Assert.assertEquals(1, totalCommands);
		Assert.assertEquals(2, totalAssignments);
		
		Assert.assertEquals(1, totalIfs);
		Assert.assertEquals(1, totalElseIfs);
		Assert.assertEquals(1, totalElses);
		Assert.assertEquals(totalIfs, totalEndIfs);
		Assert.assertEquals(1, totalOperators);
	}
	
	@Test
	public void testNumericsNode() throws Exception {
		runTest("numeric-node.txt");
		
		Assert.assertEquals(1, totalLines);
		
		Assert.assertEquals(1, totalIfs);
		Assert.assertEquals(totalIfs, totalEndIfs);
		Assert.assertEquals(3, totalNumericOperations);
		Assert.assertEquals(1, totalPlus);
		Assert.assertEquals(1, totalMinus);
		Assert.assertEquals(1, totalMultiply);
	}
	
	@Test
	public void testBranchingNode() throws Exception {
		runTest("branching-node.txt");
		
		Assert.assertEquals(4, totalLines);
		Assert.assertEquals(4, totalCharacters);
		Assert.assertEquals(4, totalSpeech);
		
		Assert.assertEquals(2, totalOptionGroups);
		Assert.assertEquals(5, totalOptions);
		Assert.assertEquals(5, totalOptionNodes);
		Assert.assertEquals(4, totalOptionLabels);
	}
	
	@Override
	public void exitIfExpression(IfExpressionContext ctx) {
		totalIfs++;
		if(ctx.boolOperator() != null) {
			totalOperators += ctx.boolOperator().size();
		}
	}
	
	@Override
	public void exitElseifExpression(ElseifExpressionContext ctx) {
		totalElseIfs++;
		if(ctx.boolOperator() != null) {
			totalOperators += ctx.boolOperator().size();
		}
	}
	
	@Override
	public void exitElseStatement(ElseStatementContext ctx) {
		totalElses++;
	}
	
	@Override
	public void exitEndifStatement(EndifStatementContext ctx) {
		totalEndIfs++;
	}
	
	@Override
	public void exitLineStatement(LineStatementContext ctx) {
		totalLines++;
		
		if(ctx.characterExpression() != null) {
			totalCharacters++;
		}
		if(ctx.textExpression() != null) {
			totalSpeech++;
		}
	}
	
	@Override
	public void exitAssignExpression(AssignExpressionContext ctx) {
		totalAssignments++;
	}
	
	@Override
	public void exitCommandStatement(CommandStatementContext ctx) {
		if(ctx.textExpression() != null) {
			totalCommands++;
		}
	}
	
	@Override
	public void exitNumericOperationExpression(NumericOperationExpressionContext ctx) {
		totalNumericOperations++;
		
		if(ctx.numericOperator().PLUS() != null) {
			totalPlus++;
		} else if(ctx.numericOperator().MINUS() != null) {
			totalMinus++;
		} else if(ctx.numericOperator().MULTIPLY() != null) {
			totalMultiply++;
		} else if(ctx.numericOperator().DIVIDE() != null) {
			totalDivide++;
		}
	}
	
	@Override
	public void exitOptionGroup(OptionGroupContext ctx) {
		totalOptionGroups++;
	}
	
	@Override
	public void exitOptionStatement(OptionStatementContext ctx) {
		totalOptions++;
		
		if(ctx.textExpression() != null) {
			totalOptionLabels++;
		}
		if(ctx.TARGETNAME() != null) {
			totalOptionNodes++;
		}
	}
	
	private void runTest(String filename) throws Exception {
		System.out.println("------------- " + filename + " -------------");
		YarnLexer yarnLexer = new YarnLexer(new ANTLRInputStream(YarnParserTest.class.getResourceAsStream("/" + filename)));
		YarnParser yarnParser = new YarnParser(new BufferedTokenStream(yarnLexer));
		yarnParser.setTrace(true);
		
		NodeContext nodeContext = yarnParser.node();
		
		ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
		parseTreeWalker.walk(this, nodeContext);
	}
}
