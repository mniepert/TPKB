// Generated from TPKB.g4 by ANTLR 4.4
package parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TPKBParser}.
 */
public interface TPKBListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TPKBParser#tpkb}.
	 * @param ctx the parse tree
	 */
	void enterTpkb(@NotNull TPKBParser.TpkbContext ctx);
	/**
	 * Exit a parse tree produced by {@link TPKBParser#tpkb}.
	 * @param ctx the parse tree
	 */
	void exitTpkb(@NotNull TPKBParser.TpkbContext ctx);
	/**
	 * Enter a parse tree produced by {@link TPKBParser#tpkb_class_subpart}.
	 * @param ctx the parse tree
	 */
	void enterTpkb_class_subpart(@NotNull TPKBParser.Tpkb_class_subpartContext ctx);
	/**
	 * Exit a parse tree produced by {@link TPKBParser#tpkb_class_subpart}.
	 * @param ctx the parse tree
	 */
	void exitTpkb_class_subpart(@NotNull TPKBParser.Tpkb_class_subpartContext ctx);
	/**
	 * Enter a parse tree produced by {@link TPKBParser#tpkb_class_subclass}.
	 * @param ctx the parse tree
	 */
	void enterTpkb_class_subclass(@NotNull TPKBParser.Tpkb_class_subclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link TPKBParser#tpkb_class_subclass}.
	 * @param ctx the parse tree
	 */
	void exitTpkb_class_subclass(@NotNull TPKBParser.Tpkb_class_subclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link TPKBParser#tpkb_class}.
	 * @param ctx the parse tree
	 */
	void enterTpkb_class(@NotNull TPKBParser.Tpkb_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link TPKBParser#tpkb_class}.
	 * @param ctx the parse tree
	 */
	void exitTpkb_class(@NotNull TPKBParser.Tpkb_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link TPKBParser#tpkb_class_attribute}.
	 * @param ctx the parse tree
	 */
	void enterTpkb_class_attribute(@NotNull TPKBParser.Tpkb_class_attributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TPKBParser#tpkb_class_attribute}.
	 * @param ctx the parse tree
	 */
	void exitTpkb_class_attribute(@NotNull TPKBParser.Tpkb_class_attributeContext ctx);
}