// Generated from Yarn.g4 by ANTLR 4.6
package org.mini2Dx.yarn.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YarnParser}.
 */
public interface YarnListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YarnParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(YarnParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(YarnParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(YarnParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(YarnParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(YarnParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(YarnParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#lineStatement}.
	 * @param ctx the parse tree
	 */
	void enterLineStatement(YarnParser.LineStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#lineStatement}.
	 * @param ctx the parse tree
	 */
	void exitLineStatement(YarnParser.LineStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#characterExpression}.
	 * @param ctx the parse tree
	 */
	void enterCharacterExpression(YarnParser.CharacterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#characterExpression}.
	 * @param ctx the parse tree
	 */
	void exitCharacterExpression(YarnParser.CharacterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#textExpression}.
	 * @param ctx the parse tree
	 */
	void enterTextExpression(YarnParser.TextExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#textExpression}.
	 * @param ctx the parse tree
	 */
	void exitTextExpression(YarnParser.TextExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(YarnParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(YarnParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#elseifStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseifStatement(YarnParser.ElseifStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#elseifStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseifStatement(YarnParser.ElseifStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(YarnParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(YarnParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#endifStatement}.
	 * @param ctx the parse tree
	 */
	void enterEndifStatement(YarnParser.EndifStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#endifStatement}.
	 * @param ctx the parse tree
	 */
	void exitEndifStatement(YarnParser.EndifStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void enterIfExpression(YarnParser.IfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#ifExpression}.
	 * @param ctx the parse tree
	 */
	void exitIfExpression(YarnParser.IfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#elseifExpression}.
	 * @param ctx the parse tree
	 */
	void enterElseifExpression(YarnParser.ElseifExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#elseifExpression}.
	 * @param ctx the parse tree
	 */
	void exitElseifExpression(YarnParser.ElseifExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#conditionExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionExpression(YarnParser.ConditionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#conditionExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionExpression(YarnParser.ConditionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void enterBoolOperator(YarnParser.BoolOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#boolOperator}.
	 * @param ctx the parse tree
	 */
	void exitBoolOperator(YarnParser.BoolOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#comparatorExpression}.
	 * @param ctx the parse tree
	 */
	void enterComparatorExpression(YarnParser.ComparatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#comparatorExpression}.
	 * @param ctx the parse tree
	 */
	void exitComparatorExpression(YarnParser.ComparatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#commandStatement}.
	 * @param ctx the parse tree
	 */
	void enterCommandStatement(YarnParser.CommandStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#commandStatement}.
	 * @param ctx the parse tree
	 */
	void exitCommandStatement(YarnParser.CommandStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpression(YarnParser.AssignExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#assignExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpression(YarnParser.AssignExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#numericOperationExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericOperationExpression(YarnParser.NumericOperationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#numericOperationExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericOperationExpression(YarnParser.NumericOperationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpression(YarnParser.ValueExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#valueExpression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpression(YarnParser.ValueExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#numericOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumericOperator(YarnParser.NumericOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#numericOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumericOperator(YarnParser.NumericOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#optionGroup}.
	 * @param ctx the parse tree
	 */
	void enterOptionGroup(YarnParser.OptionGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#optionGroup}.
	 * @param ctx the parse tree
	 */
	void exitOptionGroup(YarnParser.OptionGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link YarnParser#optionStatement}.
	 * @param ctx the parse tree
	 */
	void enterOptionStatement(YarnParser.OptionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link YarnParser#optionStatement}.
	 * @param ctx the parse tree
	 */
	void exitOptionStatement(YarnParser.OptionStatementContext ctx);
}