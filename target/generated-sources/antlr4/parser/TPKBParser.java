// Generated from TPKB.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TPKBParser extends Parser {
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
	public static final String[] tokenNames = {
		"<INVALID>", "'IDB'", "'ICSV'", "','", "SEMI", "'Class'", "'||'", "'&&'", 
		"'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'+'", "'-'", "'*'", "'/'", 
		"'%'", "'^'", "'!'", "SCOL", "'='", "'('", "')'", "'{'", "'}'", "'true'", 
		"'false'", "'nil'", "'attributes'", "'subclasses'", "'parts'", "ID", "INT", 
		"FLOAT", "STRING", "COMMENT", "SPACE", "OTHER"
	};
	public static final int
		RULE_tpkb = 0, RULE_tpkb_class = 1, RULE_tpkb_class_subclass = 2, RULE_tpkb_class_subpart = 3, 
		RULE_tpkb_class_attribute = 4;
	public static final String[] ruleNames = {
		"tpkb", "tpkb_class", "tpkb_class_subclass", "tpkb_class_subpart", "tpkb_class_attribute"
	};

	@Override
	public String getGrammarFileName() { return "TPKB.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TPKBParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TpkbContext extends ParserRuleContext {
		public Tpkb_classContext tpkb_class(int i) {
			return getRuleContext(Tpkb_classContext.class,i);
		}
		public List<Tpkb_classContext> tpkb_class() {
			return getRuleContexts(Tpkb_classContext.class);
		}
		public TpkbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tpkb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).enterTpkb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).exitTpkb(this);
		}
	}

	public final TpkbContext tpkb() throws RecognitionException {
		TpkbContext _localctx = new TpkbContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tpkb);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(10); tpkb_class();
				}
				}
				setState(13); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tpkb_classContext extends ParserRuleContext {
		public Tpkb_class_subpartContext tpkb_class_subpart() {
			return getRuleContext(Tpkb_class_subpartContext.class,0);
		}
		public TerminalNode CBRACE() { return getToken(TPKBParser.CBRACE, 0); }
		public TerminalNode ID() { return getToken(TPKBParser.ID, 0); }
		public Tpkb_class_subclassContext tpkb_class_subclass() {
			return getRuleContext(Tpkb_class_subclassContext.class,0);
		}
		public TerminalNode OBRACE() { return getToken(TPKBParser.OBRACE, 0); }
		public Tpkb_class_attributeContext tpkb_class_attribute() {
			return getRuleContext(Tpkb_class_attributeContext.class,0);
		}
		public TerminalNode CLASS() { return getToken(TPKBParser.CLASS, 0); }
		public Tpkb_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tpkb_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).enterTpkb_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).exitTpkb_class(this);
		}
	}

	public final Tpkb_classContext tpkb_class() throws RecognitionException {
		Tpkb_classContext _localctx = new Tpkb_classContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tpkb_class);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); match(CLASS);
			setState(16); match(ID);
			setState(17); match(OBRACE);
			setState(18); tpkb_class_subclass();
			setState(19); tpkb_class_subpart();
			setState(20); tpkb_class_attribute();
			setState(21); match(CBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tpkb_class_subclassContext extends ParserRuleContext {
		public List<TerminalNode> FLOAT() { return getTokens(TPKBParser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(TPKBParser.FLOAT, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TPKBParser.COMMA); }
		public TerminalNode SEMI() { return getToken(TPKBParser.SEMI, 0); }
		public List<TerminalNode> ID() { return getTokens(TPKBParser.ID); }
		public TerminalNode SUBCLASSES() { return getToken(TPKBParser.SUBCLASSES, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(TPKBParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(TPKBParser.ID, i);
		}
		public Tpkb_class_subclassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tpkb_class_subclass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).enterTpkb_class_subclass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).exitTpkb_class_subclass(this);
		}
	}

	public final Tpkb_class_subclassContext tpkb_class_subclass() throws RecognitionException {
		Tpkb_class_subclassContext _localctx = new Tpkb_class_subclassContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tpkb_class_subclass);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			switch (_input.LA(1)) {
			case SUBCLASSES:
				{
				setState(23); match(SUBCLASSES);
				setState(29);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(24); match(ID);
						setState(25); match(FLOAT);
						setState(26); match(COMMA);
						}
						} 
					}
					setState(31);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(32); match(ID);
				setState(33); match(FLOAT);
				setState(34); match(SEMI);
				}
				break;
			case CBRACE:
			case ATTRIBUTES:
			case PARTS:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tpkb_class_subpartContext extends ParserRuleContext {
		public TerminalNode PARTS() { return getToken(TPKBParser.PARTS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(TPKBParser.COMMA); }
		public TerminalNode SEMI() { return getToken(TPKBParser.SEMI, 0); }
		public List<TerminalNode> ID() { return getTokens(TPKBParser.ID); }
		public TerminalNode COMMA(int i) {
			return getToken(TPKBParser.COMMA, i);
		}
		public TerminalNode ID(int i) {
			return getToken(TPKBParser.ID, i);
		}
		public Tpkb_class_subpartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tpkb_class_subpart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).enterTpkb_class_subpart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).exitTpkb_class_subpart(this);
		}
	}

	public final Tpkb_class_subpartContext tpkb_class_subpart() throws RecognitionException {
		Tpkb_class_subpartContext _localctx = new Tpkb_class_subpartContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tpkb_class_subpart);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			switch (_input.LA(1)) {
			case PARTS:
				{
				setState(38); match(PARTS);
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(39); match(ID);
						setState(40); match(ID);
						setState(41); match(COMMA);
						}
						} 
					}
					setState(46);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(47); match(ID);
				setState(48); match(ID);
				setState(49); match(SEMI);
				}
				break;
			case CBRACE:
			case ATTRIBUTES:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tpkb_class_attributeContext extends ParserRuleContext {
		public TerminalNode DBTOKEN(int i) {
			return getToken(TPKBParser.DBTOKEN, i);
		}
		public TerminalNode CSVTOKEN(int i) {
			return getToken(TPKBParser.CSVTOKEN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TPKBParser.COMMA); }
		public TerminalNode SEMI() { return getToken(TPKBParser.SEMI, 0); }
		public List<TerminalNode> ID() { return getTokens(TPKBParser.ID); }
		public List<TerminalNode> DBTOKEN() { return getTokens(TPKBParser.DBTOKEN); }
		public TerminalNode COMMA(int i) {
			return getToken(TPKBParser.COMMA, i);
		}
		public List<TerminalNode> CSVTOKEN() { return getTokens(TPKBParser.CSVTOKEN); }
		public TerminalNode ID(int i) {
			return getToken(TPKBParser.ID, i);
		}
		public TerminalNode ATTRIBUTES() { return getToken(TPKBParser.ATTRIBUTES, 0); }
		public Tpkb_class_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tpkb_class_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).enterTpkb_class_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TPKBListener ) ((TPKBListener)listener).exitTpkb_class_attribute(this);
		}
	}

	public final Tpkb_class_attributeContext tpkb_class_attribute() throws RecognitionException {
		Tpkb_class_attributeContext _localctx = new Tpkb_class_attributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tpkb_class_attribute);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			switch (_input.LA(1)) {
			case ATTRIBUTES:
				{
				setState(53); match(ATTRIBUTES);
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(54); match(ID);
						setState(55);
						_la = _input.LA(1);
						if ( !(_la==DBTOKEN || _la==CSVTOKEN) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(56); match(COMMA);
						}
						} 
					}
					setState(61);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				setState(62); match(ID);
				setState(63);
				_la = _input.LA(1);
				if ( !(_la==DBTOKEN || _la==CSVTOKEN) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(64); match(SEMI);
				}
				break;
			case CBRACE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3)G\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\16\n\2\r\2\16\2\17\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\36\n\4\f\4\16\4!\13\4\3\4\3\4\3\4\3"+
		"\4\5\4\'\n\4\3\5\3\5\3\5\3\5\7\5-\n\5\f\5\16\5\60\13\5\3\5\3\5\3\5\3\5"+
		"\5\5\66\n\5\3\6\3\6\3\6\3\6\7\6<\n\6\f\6\16\6?\13\6\3\6\3\6\3\6\3\6\5"+
		"\6E\n\6\3\6\2\2\7\2\4\6\b\n\2\3\3\2\3\4H\2\r\3\2\2\2\4\21\3\2\2\2\6&\3"+
		"\2\2\2\b\65\3\2\2\2\nD\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\17\3\2\2\2"+
		"\17\r\3\2\2\2\17\20\3\2\2\2\20\3\3\2\2\2\21\22\7\7\2\2\22\23\7#\2\2\23"+
		"\24\7\33\2\2\24\25\5\6\4\2\25\26\5\b\5\2\26\27\5\n\6\2\27\30\7\34\2\2"+
		"\30\5\3\2\2\2\31\37\7!\2\2\32\33\7#\2\2\33\34\7%\2\2\34\36\7\5\2\2\35"+
		"\32\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2"+
		"\2\"#\7#\2\2#$\7%\2\2$\'\7\6\2\2%\'\3\2\2\2&\31\3\2\2\2&%\3\2\2\2\'\7"+
		"\3\2\2\2(.\7\"\2\2)*\7#\2\2*+\7#\2\2+-\7\5\2\2,)\3\2\2\2-\60\3\2\2\2."+
		",\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7#\2\2\62\63\7#\2\2"+
		"\63\66\7\6\2\2\64\66\3\2\2\2\65(\3\2\2\2\65\64\3\2\2\2\66\t\3\2\2\2\67"+
		"=\7 \2\289\7#\2\29:\t\2\2\2:<\7\5\2\2;8\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>"+
		"\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7#\2\2AB\t\2\2\2BE\7\6\2\2CE\3\2\2\2D\67"+
		"\3\2\2\2DC\3\2\2\2E\13\3\2\2\2\t\17\37&.\65=D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}