// Generated from TPKB.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TPKBLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DBTOKEN=1, CSVTOKEN=2, COMMA=3, SEMI=4, CLASS=5, OR=6, AND=7, EQ=8, NEQ=9, 
		GT=10, LT=11, GTEQ=12, LTEQ=13, PLUS=14, MINUS=15, MULT=16, DIV=17, MOD=18, 
		POW=19, NOT=20, SCOL=21, ASSIGN=22, OPAR=23, CPAR=24, OBRACE=25, CBRACE=26, 
		TRUE=27, FALSE=28, NIL=29, ATTRIBUTES=30, SUBCLASSES=31, PARTS=32, ID=33, 
		INT=34, FLOAT=35, STRING=36, COMMENT=37, SPACE=38, OTHER=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''"
	};
	public static final String[] ruleNames = {
		"DBTOKEN", "CSVTOKEN", "COMMA", "SEMI", "CLASS", "OR", "AND", "EQ", "NEQ", 
		"GT", "LT", "GTEQ", "LTEQ", "PLUS", "MINUS", "MULT", "DIV", "MOD", "POW", 
		"NOT", "SCOL", "ASSIGN", "OPAR", "CPAR", "OBRACE", "CBRACE", "TRUE", "FALSE", 
		"NIL", "ATTRIBUTES", "SUBCLASSES", "PARTS", "ID", "INT", "FLOAT", "STRING", 
		"COMMENT", "SPACE", "OTHER"
	};


	public TPKBLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TPKB.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2)\u00f9\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\7\"\u00c2\n\"\f\"\16"+
		"\"\u00c5\13\"\3#\6#\u00c8\n#\r#\16#\u00c9\3$\6$\u00cd\n$\r$\16$\u00ce"+
		"\3$\3$\7$\u00d3\n$\f$\16$\u00d6\13$\3$\3$\6$\u00da\n$\r$\16$\u00db\5$"+
		"\u00de\n$\3%\3%\3%\3%\7%\u00e4\n%\f%\16%\u00e7\13%\3%\3%\3&\3&\7&\u00ed"+
		"\n&\f&\16&\u00f0\13&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\2\2)\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)\3\2\b\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\5\2\f\f\17\17$$\4\2\f\f"+
		"\17\17\5\2\13\f\17\17\"\"\u0101\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\3Q\3\2\2\2\5U\3\2\2\2\7Z\3\2\2\2\t\\\3\2\2\2\13^\3\2\2\2\rd\3"+
		"\2\2\2\17g\3\2\2\2\21j\3\2\2\2\23m\3\2\2\2\25p\3\2\2\2\27r\3\2\2\2\31"+
		"t\3\2\2\2\33w\3\2\2\2\35z\3\2\2\2\37|\3\2\2\2!~\3\2\2\2#\u0080\3\2\2\2"+
		"%\u0082\3\2\2\2\'\u0084\3\2\2\2)\u0086\3\2\2\2+\u0088\3\2\2\2-\u008a\3"+
		"\2\2\2/\u008c\3\2\2\2\61\u008e\3\2\2\2\63\u0090\3\2\2\2\65\u0092\3\2\2"+
		"\2\67\u0094\3\2\2\29\u0099\3\2\2\2;\u009f\3\2\2\2=\u00a3\3\2\2\2?\u00ae"+
		"\3\2\2\2A\u00b9\3\2\2\2C\u00bf\3\2\2\2E\u00c7\3\2\2\2G\u00dd\3\2\2\2I"+
		"\u00df\3\2\2\2K\u00ea\3\2\2\2M\u00f3\3\2\2\2O\u00f7\3\2\2\2QR\7K\2\2R"+
		"S\7F\2\2ST\7D\2\2T\4\3\2\2\2UV\7K\2\2VW\7E\2\2WX\7U\2\2XY\7X\2\2Y\6\3"+
		"\2\2\2Z[\7.\2\2[\b\3\2\2\2\\]\7=\2\2]\n\3\2\2\2^_\7E\2\2_`\7n\2\2`a\7"+
		"c\2\2ab\7u\2\2bc\7u\2\2c\f\3\2\2\2de\7~\2\2ef\7~\2\2f\16\3\2\2\2gh\7("+
		"\2\2hi\7(\2\2i\20\3\2\2\2jk\7?\2\2kl\7?\2\2l\22\3\2\2\2mn\7#\2\2no\7?"+
		"\2\2o\24\3\2\2\2pq\7@\2\2q\26\3\2\2\2rs\7>\2\2s\30\3\2\2\2tu\7@\2\2uv"+
		"\7?\2\2v\32\3\2\2\2wx\7>\2\2xy\7?\2\2y\34\3\2\2\2z{\7-\2\2{\36\3\2\2\2"+
		"|}\7/\2\2} \3\2\2\2~\177\7,\2\2\177\"\3\2\2\2\u0080\u0081\7\61\2\2\u0081"+
		"$\3\2\2\2\u0082\u0083\7\'\2\2\u0083&\3\2\2\2\u0084\u0085\7`\2\2\u0085"+
		"(\3\2\2\2\u0086\u0087\7#\2\2\u0087*\3\2\2\2\u0088\u0089\7=\2\2\u0089,"+
		"\3\2\2\2\u008a\u008b\7?\2\2\u008b.\3\2\2\2\u008c\u008d\7*\2\2\u008d\60"+
		"\3\2\2\2\u008e\u008f\7+\2\2\u008f\62\3\2\2\2\u0090\u0091\7}\2\2\u0091"+
		"\64\3\2\2\2\u0092\u0093\7\177\2\2\u0093\66\3\2\2\2\u0094\u0095\7v\2\2"+
		"\u0095\u0096\7t\2\2\u0096\u0097\7w\2\2\u0097\u0098\7g\2\2\u00988\3\2\2"+
		"\2\u0099\u009a\7h\2\2\u009a\u009b\7c\2\2\u009b\u009c\7n\2\2\u009c\u009d"+
		"\7u\2\2\u009d\u009e\7g\2\2\u009e:\3\2\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1"+
		"\7k\2\2\u00a1\u00a2\7n\2\2\u00a2<\3\2\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5"+
		"\7v\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7t\2\2\u00a7\u00a8\7k\2\2\u00a8"+
		"\u00a9\7d\2\2\u00a9\u00aa\7w\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7g\2\2"+
		"\u00ac\u00ad\7u\2\2\u00ad>\3\2\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7w\2"+
		"\2\u00b0\u00b1\7d\2\2\u00b1\u00b2\7e\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4"+
		"\7c\2\2\u00b4\u00b5\7u\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7u\2\2\u00b8@\3\2\2\2\u00b9\u00ba\7r\2\2\u00ba\u00bb\7c\2\2\u00bb"+
		"\u00bc\7t\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7u\2\2\u00beB\3\2\2\2\u00bf"+
		"\u00c3\t\2\2\2\u00c0\u00c2\t\3\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2"+
		"\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4D\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c6\u00c8\t\4\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00caF\3\2\2\2\u00cb\u00cd\t\4\2\2"+
		"\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d4\7\60\2\2\u00d1\u00d3\t\4\2\2"+
		"\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5"+
		"\3\2\2\2\u00d5\u00de\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d9\7\60\2\2"+
		"\u00d8\u00da\t\4\2\2\u00d9\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00d9"+
		"\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00cc\3\2\2\2\u00dd"+
		"\u00d7\3\2\2\2\u00deH\3\2\2\2\u00df\u00e5\7$\2\2\u00e0\u00e4\n\5\2\2\u00e1"+
		"\u00e2\7$\2\2\u00e2\u00e4\7$\2\2\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2"+
		"\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\7$\2\2\u00e9J\3\2\2\2\u00ea\u00ee"+
		"\7%\2\2\u00eb\u00ed\n\6\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f2\b&\2\2\u00f2L\3\2\2\2\u00f3\u00f4\t\7\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\b\'\2\2\u00f6N\3\2\2\2\u00f7\u00f8\13\2\2\2\u00f8"+
		"P\3\2\2\2\f\2\u00c3\u00c9\u00ce\u00d4\u00db\u00dd\u00e3\u00e5\u00ee\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}