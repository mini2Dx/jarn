//
// MIT License
//
// Copyright (c) 2017 Thomas Cashman
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//

grammar Yarn;

// ----------------------------
// Nodes
// ----------------------------

node
	:	( block )* EOF
	;
	
block
	:	statement (statement)*
	;
	
statement
	:	WHITESPACE* ifStatement WHITESPACE* NEWLINE
	|	WHITESPACE* elseifStatement WHITESPACE* NEWLINE
	|	WHITESPACE* elseStatement WHITESPACE* NEWLINE
	|	WHITESPACE* elseStatement WHITESPACE* NEWLINE
	|	WHITESPACE* endifStatement WHITESPACE* NEWLINE
	|	WHITESPACE* commandStatement WHITESPACE* NEWLINE
	|	optionGroup
	|	WHITESPACE* lineStatement WHITESPACE* NEWLINE
	|	NEWLINE
	;
	
// ----------------------------
// Lines
// ----------------------------

lineStatement
	:	(characterExpression)? textExpression
	;

characterExpression
	:	TARGETNAME COLON
	;

textExpression
	:	(SpeechChar | ~NEWLINE)+
	;
	
fragment
SpeechChar
	:	TYPOGRAPHY
	|	PUNCTUATION
	|	MINUS
	;
	
// ----------------------------
// Conditionals
// ----------------------------

ifStatement
	:	LEFTCHEVRON WHITESPACE* ifExpression WHITESPACE* RIGHTCHEVRON
	;
	
elseifStatement
	:	LEFTCHEVRON WHITESPACE* elseifExpression WHITESPACE* RIGHTCHEVRON
	;
	
elseStatement
	:	LEFTCHEVRON WHITESPACE* ELSE WHITESPACE* RIGHTCHEVRON
	;
	
endifStatement
	:	LEFTCHEVRON WHITESPACE* ENDIF WHITESPACE* RIGHTCHEVRON
	;

ifExpression
	:	IF WHITESPACE+ conditionExpression (WHITESPACE+ boolOperator WHITESPACE+ conditionExpression)* 
	;
	
elseifExpression
	:	ELSEIF WHITESPACE+ conditionExpression (WHITESPACE+ boolOperator WHITESPACE+ conditionExpression)* 
	;
	
conditionExpression
	:	valueExpression WHITESPACE+ IS WHITESPACE+ valueExpression
	|	valueExpression WHITESPACE+ comparatorExpression WHITESPACE+ valueExpression
	|	VariableLiteral
	;
	
boolOperator
	:	AND
	|	OR
	;
	
comparatorExpression
	:	EQUALS
	|	NOTEQUALS
	| 	GTE
	|	LTE
	|	LT
	|	GT
	;
	
// ----------------------------
// Commands
// ----------------------------

commandStatement
	:	LEFTCHEVRON WHITESPACE* assignExpression WHITESPACE* RIGHTCHEVRON
	|	LEFTCHEVRON WHITESPACE* textExpression WHITESPACE* RIGHTCHEVRON
	;
	
assignExpression
	:	SET WHITESPACE+ VariableLiteral WHITESPACE+ TO WHITESPACE+ valueExpression
	;
	
numericOperationExpression
	:	VariableLiteral WHITESPACE* numericOperator WHITESPACE* valueExpression
	|	VariableLiteral WHITESPACE* numericOperator WHITESPACE* VariableLiteral
	|	VariableLiteral WHITESPACE* numericOperator WHITESPACE* NumberLiteral
	|	NumberLiteral WHITESPACE* numericOperator WHITESPACE* NumberLiteral
	;
	
valueExpression
	:	numericOperationExpression
	|	VariableLiteral
	|	NullLiteral
	|	BooleanLiteral
	|	NumberLiteral
	|	StringLiteral
	;
	
numericOperator
	:	PLUS
	|	MINUS
	|	MULTIPLY
	|	DIVIDE
	|	MODULUS
	;
	
// ----------------------------
// Options
// ----------------------------
	
optionGroup
	: 	optionStatement+
	;

optionStatement
	:	WHITESPACE* LEFTOPTION textExpression LABELNODESEPERATOR TARGETNAME RIGHTOPTION WHITESPACE* NEWLINE
	|	WHITESPACE* LEFTOPTION TARGETNAME RIGHTOPTION WHITESPACE* NEWLINE
	;
	
// ----------------------------
// Variable Literals
// ----------------------------

VariableLiteral
	:	'$' VariableNameChar+
	;
	
fragment
VariableNameChar
	: [a-z]
	| [A-Z]
	| [0-9]
	| '_'
	;

// ----------------------------
// Null Literals
// ----------------------------

NullLiteral
	:	'null'
	;

// ----------------------------
// Boolean Literals
// ----------------------------

BooleanLiteral
	:	'true'
	|	'false'
	;

// ----------------------------
// String Literals
// ----------------------------

StringLiteral
	:	'"' StringCharacters? '"'
	;
fragment
StringCharacters
	:	StringCharacter+
	;
fragment
StringCharacter
	:	~["\\]
	|	EscapeSequence
	;
fragment
EscapeSequence
	:	'\\' [btnfr"'\\]
	;
	
// ----------------------------
// Number Literals
// ----------------------------

NumberLiteral
	:	Digits '.' Digits? ExponentPart?
	|	'.' Digits ExponentPart?
	|	Digits ExponentPart
	|	Digits
	;

fragment
ExponentPart
	:	ExponentIndicator SignedInteger
	;
	
fragment
ExponentIndicator
	:	[eE]
	;

fragment
SignedInteger
	:	Sign? Digits
	;

fragment
Sign
	:	[+-]
	;

fragment
Digits
	:	Digit (DigitsAndUnderscores? Digit)?
	;

fragment
Digit
	:	'0'
	|	NonZeroDigit
	;

fragment
NonZeroDigit
	:	[1-9]
	;

fragment
DigitsAndUnderscores
	:	DigitOrUnderscore+
	;

fragment
DigitOrUnderscore
	:	Digit
	|	'_'
	;

fragment
Underscores
	:	'_'+
	;
	
// ----------------------------
// Keywords
// ----------------------------

IF : 'if';
ELSEIF : 'elseif';
ELSE : 'else';
ENDIF : 'endif';
SET : 'set';
TO : 'to';
IS : 'is';
LEFTCHEVRON : '<<';
RIGHTCHEVRON : '>>';
LEFTOPTION : '[[';
RIGHTOPTION : ']]';
LABELNODESEPERATOR: '|';

// ----------------------------
// Comparator Symbols
// ---------------------------- 

EQUALS: '==';
NOTEQUALS: '!=';
GTE: '>=';
LTE: '<=';
GT: '>';
LT: '<';

// ----------------------------
// Operators
// ----------------------------

PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
MODULUS: '%';
AND
	: '&&'
	| 'and'
	;
OR
	: '||'
	| 'or'
	;
	
// ----------------------------
// Text Characters
// ----------------------------
	
TARGETNAME: [a-zA-Z0-9]+;
PUNCTUATION: '!' | '?' | '.' | ',' | '"' | '\'' | '(' | ')' | '{' | '}' | ';';
TYPOGRAPHY: '_' | '&' | '@' | '\\';
COLON: ':';

WHITESPACE: [ \t];
NEWLINE : ( '\r'? '\n' | '\r' );