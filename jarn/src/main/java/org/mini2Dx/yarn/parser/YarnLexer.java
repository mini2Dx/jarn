// Generated from Yarn.g4 by ANTLR 4.6
package org.mini2Dx.yarn.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class YarnLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VariableLiteral=1, NullLiteral=2, BooleanLiteral=3, StringLiteral=4, NumberLiteral=5, 
		IF=6, ELSEIF=7, ELSE=8, ENDIF=9, SET=10, TO=11, IS=12, LEFTCHEVRON=13, 
		RIGHTCHEVRON=14, LEFTOPTION=15, RIGHTOPTION=16, LABELNODESEPERATOR=17, 
		EQUALS=18, NOTEQUALS=19, GTE=20, LTE=21, GT=22, LT=23, PLUS=24, MINUS=25, 
		MULTIPLY=26, DIVIDE=27, MODULUS=28, AND=29, OR=30, TARGETNAME=31, PUNCTUATION=32, 
		TYPOGRAPHY=33, COLON=34, WHITESPACE=35, NEWLINE=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SpeechChar", "VariableLiteral", "VariableNameChar", "NullLiteral", "BooleanLiteral", 
		"StringLiteral", "StringCharacters", "StringCharacter", "EscapeSequence", 
		"NumberLiteral", "ExponentPart", "ExponentIndicator", "SignedInteger", 
		"Sign", "Digits", "Digit", "NonZeroDigit", "DigitsAndUnderscores", "DigitOrUnderscore", 
		"Underscores", "IF", "ELSEIF", "ELSE", "ENDIF", "SET", "TO", "IS", "LEFTCHEVRON", 
		"RIGHTCHEVRON", "LEFTOPTION", "RIGHTOPTION", "LABELNODESEPERATOR", "EQUALS", 
		"NOTEQUALS", "GTE", "LTE", "GT", "LT", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
		"MODULUS", "AND", "OR", "TARGETNAME", "PUNCTUATION", "TYPOGRAPHY", "COLON", 
		"WHITESPACE", "NEWLINE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'null'", null, null, null, "'if'", "'elseif'", "'else'", 
		"'endif'", "'set'", "'to'", "'is'", "'<<'", "'>>'", "'[['", "']]'", "'|'", 
		"'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", 
		"'%'", null, null, null, null, null, "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VariableLiteral", "NullLiteral", "BooleanLiteral", "StringLiteral", 
		"NumberLiteral", "IF", "ELSEIF", "ELSE", "ENDIF", "SET", "TO", "IS", "LEFTCHEVRON", 
		"RIGHTCHEVRON", "LEFTOPTION", "RIGHTOPTION", "LABELNODESEPERATOR", "EQUALS", 
		"NOTEQUALS", "GTE", "LTE", "GT", "LT", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", 
		"MODULUS", "AND", "OR", "TARGETNAME", "PUNCTUATION", "TYPOGRAPHY", "COLON", 
		"WHITESPACE", "NEWLINE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public YarnLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Yarn.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u013b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\3\2\3\2\3\2\5\2m\n\2\3\3\3\3\6\3q\n\3\r\3\16\3r\3\4\5\4v\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0086\n\6\3\7\3"+
		"\7\5\7\u008a\n\7\3\7\3\7\3\b\6\b\u008f\n\b\r\b\16\b\u0090\3\t\3\t\5\t"+
		"\u0095\n\t\3\n\3\n\3\n\3\13\3\13\3\13\5\13\u009d\n\13\3\13\5\13\u00a0"+
		"\n\13\3\13\3\13\3\13\5\13\u00a5\n\13\3\13\3\13\3\13\3\13\5\13\u00ab\n"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\16\5\16\u00b3\n\16\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\5\20\u00bb\n\20\3\20\5\20\u00be\n\20\3\21\3\21\5\21\u00c2\n\21\3"+
		"\22\3\22\3\23\6\23\u00c7\n\23\r\23\16\23\u00c8\3\24\3\24\5\24\u00cd\n"+
		"\24\3\25\6\25\u00d0\n\25\r\25\16\25\u00d1\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3#\3$"+
		"\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3-\3"+
		"-\3-\5-\u0120\n-\3.\3.\3.\3.\5.\u0126\n.\3/\6/\u0129\n/\r/\16/\u012a\3"+
		"\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\5\64\u0136\n\64\3\64\3\64"+
		"\5\64\u013a\n\64\2\2\65\3\2\5\3\7\2\t\4\13\5\r\6\17\2\21\2\23\2\25\7\27"+
		"\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\b-\t/\n\61\13\63\f\65\r\67\16"+
		"9\17;\20=\21?\22A\23C\24E\25G\26I\27K\30M\31O\32Q\33S\34U\35W\36Y\37["+
		" ]!_\"a#c$e%g&\3\2\f\6\2\62;C\\aac|\4\2$$^^\n\2$$))^^ddhhppttvv\4\2GG"+
		"gg\4\2--//\3\2\63;\5\2\62;C\\c|\n\2#$)+..\60\60==AA}}\177\177\6\2((BB"+
		"^^aa\4\2\13\13\"\"\u0144\2\5\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\25\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3"+
		"\2\2\2\2g\3\2\2\2\3l\3\2\2\2\5n\3\2\2\2\7u\3\2\2\2\tw\3\2\2\2\13\u0085"+
		"\3\2\2\2\r\u0087\3\2\2\2\17\u008e\3\2\2\2\21\u0094\3\2\2\2\23\u0096\3"+
		"\2\2\2\25\u00aa\3\2\2\2\27\u00ac\3\2\2\2\31\u00af\3\2\2\2\33\u00b2\3\2"+
		"\2\2\35\u00b6\3\2\2\2\37\u00b8\3\2\2\2!\u00c1\3\2\2\2#\u00c3\3\2\2\2%"+
		"\u00c6\3\2\2\2\'\u00cc\3\2\2\2)\u00cf\3\2\2\2+\u00d3\3\2\2\2-\u00d6\3"+
		"\2\2\2/\u00dd\3\2\2\2\61\u00e2\3\2\2\2\63\u00e8\3\2\2\2\65\u00ec\3\2\2"+
		"\2\67\u00ef\3\2\2\29\u00f2\3\2\2\2;\u00f5\3\2\2\2=\u00f8\3\2\2\2?\u00fb"+
		"\3\2\2\2A\u00fe\3\2\2\2C\u0100\3\2\2\2E\u0103\3\2\2\2G\u0106\3\2\2\2I"+
		"\u0109\3\2\2\2K\u010c\3\2\2\2M\u010e\3\2\2\2O\u0110\3\2\2\2Q\u0112\3\2"+
		"\2\2S\u0114\3\2\2\2U\u0116\3\2\2\2W\u0118\3\2\2\2Y\u011f\3\2\2\2[\u0125"+
		"\3\2\2\2]\u0128\3\2\2\2_\u012c\3\2\2\2a\u012e\3\2\2\2c\u0130\3\2\2\2e"+
		"\u0132\3\2\2\2g\u0139\3\2\2\2im\5a\61\2jm\5_\60\2km\5Q)\2li\3\2\2\2lj"+
		"\3\2\2\2lk\3\2\2\2m\4\3\2\2\2np\7&\2\2oq\5\7\4\2po\3\2\2\2qr\3\2\2\2r"+
		"p\3\2\2\2rs\3\2\2\2s\6\3\2\2\2tv\t\2\2\2ut\3\2\2\2v\b\3\2\2\2wx\7p\2\2"+
		"xy\7w\2\2yz\7n\2\2z{\7n\2\2{\n\3\2\2\2|}\7v\2\2}~\7t\2\2~\177\7w\2\2\177"+
		"\u0086\7g\2\2\u0080\u0081\7h\2\2\u0081\u0082\7c\2\2\u0082\u0083\7n\2\2"+
		"\u0083\u0084\7u\2\2\u0084\u0086\7g\2\2\u0085|\3\2\2\2\u0085\u0080\3\2"+
		"\2\2\u0086\f\3\2\2\2\u0087\u0089\7$\2\2\u0088\u008a\5\17\b\2\u0089\u0088"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7$\2\2\u008c"+
		"\16\3\2\2\2\u008d\u008f\5\21\t\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2"+
		"\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\20\3\2\2\2\u0092\u0095"+
		"\n\3\2\2\u0093\u0095\5\23\n\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2"+
		"\u0095\22\3\2\2\2\u0096\u0097\7^\2\2\u0097\u0098\t\4\2\2\u0098\24\3\2"+
		"\2\2\u0099\u009a\5\37\20\2\u009a\u009c\7\60\2\2\u009b\u009d\5\37\20\2"+
		"\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e\u00a0"+
		"\5\27\f\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00ab\3\2\2\2"+
		"\u00a1\u00a2\7\60\2\2\u00a2\u00a4\5\37\20\2\u00a3\u00a5\5\27\f\2\u00a4"+
		"\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00ab\3\2\2\2\u00a6\u00a7\5\37"+
		"\20\2\u00a7\u00a8\5\27\f\2\u00a8\u00ab\3\2\2\2\u00a9\u00ab\5\37\20\2\u00aa"+
		"\u0099\3\2\2\2\u00aa\u00a1\3\2\2\2\u00aa\u00a6\3\2\2\2\u00aa\u00a9\3\2"+
		"\2\2\u00ab\26\3\2\2\2\u00ac\u00ad\5\31\r\2\u00ad\u00ae\5\33\16\2\u00ae"+
		"\30\3\2\2\2\u00af\u00b0\t\5\2\2\u00b0\32\3\2\2\2\u00b1\u00b3\5\35\17\2"+
		"\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5"+
		"\5\37\20\2\u00b5\34\3\2\2\2\u00b6\u00b7\t\6\2\2\u00b7\36\3\2\2\2\u00b8"+
		"\u00bd\5!\21\2\u00b9\u00bb\5%\23\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\5!\21\2\u00bd\u00ba\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be \3\2\2\2\u00bf\u00c2\7\62\2\2\u00c0\u00c2\5#\22\2"+
		"\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\"\3\2\2\2\u00c3\u00c4\t"+
		"\7\2\2\u00c4$\3\2\2\2\u00c5\u00c7\5\'\24\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9&\3\2\2\2\u00ca"+
		"\u00cd\5!\21\2\u00cb\u00cd\7a\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2"+
		"\2\2\u00cd(\3\2\2\2\u00ce\u00d0\7a\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1"+
		"\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2*\3\2\2\2\u00d3"+
		"\u00d4\7k\2\2\u00d4\u00d5\7h\2\2\u00d5,\3\2\2\2\u00d6\u00d7\7g\2\2\u00d7"+
		"\u00d8\7n\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7k\2\2"+
		"\u00db\u00dc\7h\2\2\u00dc.\3\2\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7n\2"+
		"\2\u00df\u00e0\7u\2\2\u00e0\u00e1\7g\2\2\u00e1\60\3\2\2\2\u00e2\u00e3"+
		"\7g\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7f\2\2\u00e5\u00e6\7k\2\2\u00e6"+
		"\u00e7\7h\2\2\u00e7\62\3\2\2\2\u00e8\u00e9\7u\2\2\u00e9\u00ea\7g\2\2\u00ea"+
		"\u00eb\7v\2\2\u00eb\64\3\2\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7q\2\2\u00ee"+
		"\66\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1\7u\2\2\u00f18\3\2\2\2\u00f2"+
		"\u00f3\7>\2\2\u00f3\u00f4\7>\2\2\u00f4:\3\2\2\2\u00f5\u00f6\7@\2\2\u00f6"+
		"\u00f7\7@\2\2\u00f7<\3\2\2\2\u00f8\u00f9\7]\2\2\u00f9\u00fa\7]\2\2\u00fa"+
		">\3\2\2\2\u00fb\u00fc\7_\2\2\u00fc\u00fd\7_\2\2\u00fd@\3\2\2\2\u00fe\u00ff"+
		"\7~\2\2\u00ffB\3\2\2\2\u0100\u0101\7?\2\2\u0101\u0102\7?\2\2\u0102D\3"+
		"\2\2\2\u0103\u0104\7#\2\2\u0104\u0105\7?\2\2\u0105F\3\2\2\2\u0106\u0107"+
		"\7@\2\2\u0107\u0108\7?\2\2\u0108H\3\2\2\2\u0109\u010a\7>\2\2\u010a\u010b"+
		"\7?\2\2\u010bJ\3\2\2\2\u010c\u010d\7@\2\2\u010dL\3\2\2\2\u010e\u010f\7"+
		">\2\2\u010fN\3\2\2\2\u0110\u0111\7-\2\2\u0111P\3\2\2\2\u0112\u0113\7/"+
		"\2\2\u0113R\3\2\2\2\u0114\u0115\7,\2\2\u0115T\3\2\2\2\u0116\u0117\7\61"+
		"\2\2\u0117V\3\2\2\2\u0118\u0119\7\'\2\2\u0119X\3\2\2\2\u011a\u011b\7("+
		"\2\2\u011b\u0120\7(\2\2\u011c\u011d\7c\2\2\u011d\u011e\7p\2\2\u011e\u0120"+
		"\7f\2\2\u011f\u011a\3\2\2\2\u011f\u011c\3\2\2\2\u0120Z\3\2\2\2\u0121\u0122"+
		"\7~\2\2\u0122\u0126\7~\2\2\u0123\u0124\7q\2\2\u0124\u0126\7t\2\2\u0125"+
		"\u0121\3\2\2\2\u0125\u0123\3\2\2\2\u0126\\\3\2\2\2\u0127\u0129\t\b\2\2"+
		"\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b"+
		"\3\2\2\2\u012b^\3\2\2\2\u012c\u012d\t\t\2\2\u012d`\3\2\2\2\u012e\u012f"+
		"\t\n\2\2\u012fb\3\2\2\2\u0130\u0131\7<\2\2\u0131d\3\2\2\2\u0132\u0133"+
		"\t\13\2\2\u0133f\3\2\2\2\u0134\u0136\7\17\2\2\u0135\u0134\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013a\7\f\2\2\u0138\u013a\7\17"+
		"\2\2\u0139\u0135\3\2\2\2\u0139\u0138\3\2\2\2\u013ah\3\2\2\2\32\2lru\u0085"+
		"\u0089\u0090\u0094\u009c\u009f\u00a4\u00aa\u00b2\u00ba\u00bd\u00c1\u00c8"+
		"\u00cc\u00d1\u011f\u0125\u012a\u0135\u0139\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}