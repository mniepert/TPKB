package main;

import java.io.IOException;
import java.sql.SQLException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import parser.TPKBLexer;
import parser.TPKBParser;


public class TPKB {

	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		
	       TPKBLexer lexer = new TPKBLexer(new ANTLRFileStream("test.kb"));
	       TPKBParser parser = new TPKBParser(new CommonTokenStream(lexer));
	       
	       TPKBClassListener listener = new TPKBClassListener();
	       
	       ParseTree tree = parser.tpkb();
	       ParseTreeWalker walker = new ParseTreeWalker();
	       walker.walk(listener, tree);
	       
	       MySQLEngine engine = new MySQLEngine();
	       engine.processTSubRaw();
	       engine.processTIsRaw();
	       
	       double Z = engine.computeZ();
	       System.out.println("Z = "+Z);
	       
	       Z = engine.computeZWithEvidence();
	       System.out.println("Z(E) = "+Z);     
	}

}
