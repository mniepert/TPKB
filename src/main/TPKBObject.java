package main;

import java.util.ArrayList;

public class TPKBObject {

	// the name of the object
	String name;
	
	//the possible leaf classes in the class hierarchy
	ArrayList<String> leafClasses;
	
	//the class this object was declared with
	String classDeclaredWith;
	
	//the level of this object (0 = leaf, etc.)
	int level;
	
	TPKBObject (String n) {
		name = n;
		classDeclaredWith = "TopClass";
		level = 0;
	}
}
