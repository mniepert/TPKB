package main;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;

import parser.TPKBBaseListener;
import parser.TPKBParser;

public class TPKBClassListener extends TPKBBaseListener {

	final static String topObjectID = "T";
	

	MySQLEngine engine;
	
	TPKBClassListener() {
		
		engine = new MySQLEngine();
		engine.truncate();
		engine.writeTIsEntry("T", "TopClass", "TopClass");
	}
	

	
	@Override
	public void enterTpkb_class_subclass(@NotNull TPKBParser.Tpkb_class_subclassContext ctx) {
		
		// get the parent (superclass) context
		ParserRuleContext prc = ctx.getParent();
		
		// retrieve the name of the parent class
		String parentClassName = ((TPKBParser.Tpkb_classContext)prc).ID().toString();
		
		engine.writeClassEntry(parentClassName);
		
		// iterate through all of the parent class's children
		for (int i = 0; i < ctx.ID().size(); i++) {
						
			// retrieve the class name for this child (subclass)
			String childClassName = ctx.ID(i).toString();
			
			// add the class to the class table TClass
			engine.writeClassEntry(childClassName);
			
			// insert the entry into the raw table
			engine.writeSubclassEntry(childClassName, parentClassName, Double.valueOf(ctx.FLOAT(i).getText()));
		}
	}
	
	@Override
	public void enterTpkb_class_subpart(@NotNull TPKBParser.Tpkb_class_subpartContext ctx) {
		
		// get the parent (superclass) context
		ParserRuleContext prc = ctx.getParent(); 
		// retrieve the name of the parent class
		String parentClassName = ((TPKBParser.Tpkb_classContext)prc).ID().toString();
				
		// iterate through all of the class's parts
		for (int i = 0; i < ctx.ID().size(); i = i + 2) {
				
			//the name of the part
			String partName = ctx.ID(i).toString();
			//the class of the part
			String partClassName = ctx.ID(i+1).toString();

			// insert the data into te raw TIn table
			engine.writeTIsEntry(partName, partClassName, parentClassName);
		}
	}
	
	@Override
	public void enterTpkb_class_attribute(@NotNull TPKBParser.Tpkb_class_attributeContext ctx) {
		
		// get the parent (superclass) context
		ParserRuleContext prc = ctx.getParent(); 
		// retrieve the name of the parent class
		String parentClassName = ((TPKBParser.Tpkb_classContext)prc).ID().toString();
				
		// iterate through all of the class's attributes
		for (int i = 0; i < ctx.ID().size(); i = i + 2) {			
			//the name of the part
			String attributeName = ctx.ID(i).toString();
			//change behavior depending on the loading method (DB or CSV)
			if (ctx.CSVTOKEN(i) != null) {
				// we load the content of a CSV file to create the attribute table
				engine.writeAttributeTableEntry(attributeName, parentClassName, true);
			} 
		}
	}
}
