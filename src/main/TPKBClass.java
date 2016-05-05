package main;

import java.util.HashSet;
import java.util.Set;


public class TPKBClass implements Comparable<TPKBClass> {
	
	//name of the class
	String className;
	
	// true if a leaf class
	boolean isLeaf;
	
	// the super classes
	String superclass;
	
	// the instances of the class
	Set<String> instances;
	
	//the weight of the superclass relationship
	double superClassWeight;
	
	//constructor for a non root class
	TPKBClass(String cn, String sc, double w) {
		
		className = cn;
		isLeaf = true;
		superclass = sc;
		superClassWeight = w;
		instances = new HashSet<String>();
	}
	
	//constructor for a non root class
	TPKBClass(String cn) {
		
		className = cn;
		isLeaf = true;
		superclass = null;
		superClassWeight = 0.0;
		instances = new HashSet<String>();
	}
	
	public void setLeafClass(boolean il) {
		isLeaf = il;
	}
	
	public boolean isLeafClass() {
		return isLeaf;
	}
	
	public void addInstance(String o) {
		instances.add(o);
	}
	
	public void addInstances(Set<String> ins) {
		instances.addAll(ins);
	}

	
	@Override
	public int compareTo(TPKBClass o) {
		return className.compareToIgnoreCase( o.className );
	}

}
