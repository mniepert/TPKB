package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class MySQLEngine {

	static String attributeTablePrefix = "TAttr_";
	
	Connection conn;
	
	MySQLEngine() {

		conn = null;
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/tpkb?" +
		                                   "user=root&password=seattle");

		    // Do something with the Connection

		   
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/**
	 * Computes the partition function of the TPKB. This might take a while depending on the TPKB's size.
	 * @return
	 * @throws SQLException
	 */
	public double computeZ() throws SQLException {
		
		PreparedStatement pst;
		ResultSet rs = null;		
		
		//////////// BEGIN ATTRIBUTE TABLE AGGREGATION /////////////////////////////////////////////////////////
		// aggregate all attribute tables by (className) and store in new tables
		// this computes the local partition functions corresponding to the attributes
		
		// get all table names that have the attribute table prefix (note: underscores must be escaped in mysql LIKE statements :-()
		pst = conn.prepareStatement("SELECT table_name FROM information_schema.tables "
									+ "WHERE table_schema='tpkb' AND table_name LIKE 'TAttr\\_%'");
		rs = pst.executeQuery();
		while (rs.next()) {
			String tableName = rs.getString(1);
			// create the aggregated table from the structure of the original attribute table
			pst = conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+tableName+"_Aggr LIKE "+tableName);
			pst.executeUpdate();
			// aggregate the attribute table and insert the aggregated data into the aggregated table
			pst = conn.prepareStatement("INSERT INTO "+tableName+"_Aggr "
										+ "SELECT className, 0, SUM(Dweight) "
										+ "FROM "+tableName+" "
										+ "GROUP BY (className)");
			pst.executeUpdate();
		}
		//////////// END ATTRIBUTE TABLE AGGREGATION //////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//compute the maximum depth of the part decomposition
		pst = conn.prepareStatement("SELECT MAX(objectDepth) FROM TIs");
		rs = pst.executeQuery();
		
		//the maximum depth of the class hierarchy
		int maxObjectDepth = 0;
		// retrieve the mysql result
		if (rs.next()) {
		     maxObjectDepth = rs.getInt(1);
		}
		int od = maxObjectDepth;
	
		// This is the part of the algorithm that moves up the class hierarchy for all objects
		// at level od
		do {
			//compute the maximum depth of the class hierarchy, restricted to the objects of depth od
			pst = conn.prepareStatement("SELECT MAX(classDepth) FROM TIs WHERE objectDepth=?");
			pst.setInt(1, od);
			rs = pst.executeQuery();
			
			//the maximum depth of the class hierarchy restricted to objects of depth od
			int maxClassDepth = 0;
			// retrieve the mysql result
			if (rs.next()) {
			     maxClassDepth = rs.getInt(1);
			}
			int cd = maxClassDepth;
			
			do {
				///////////////////  MULTIPLICATION of SUB-PARTITION FUNCTIONS ////////////////////////////////////////
				//for each (O, C) pair at the current object and class level, check whether there is a 
				// part of object O that was declared in C. If so, multiply the sub-partition function of the part
				// to the sub-partition function of (O,C)
				pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight) "
						+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, TIs.weight*EXP(SUM(LOG(TIs2.weight))) "
						+ "FROM   TIs, TIs as TIs2 "
						+ "WHERE  TIs.objectDepth=? AND TIs.classDepth=? "
						+    "AND TIs2.objectDepth=? AND TIs2.class=TIs2.declAsClass "
						+    "AND TIs.object=TIs2.declObject AND TIs.class=TIs2.declInClass "
						+ "GROUP BY TIs.object, TIs.class, TIs.declInClass, TIs.declObject");
				
				// the object O must have objectDepth od
				pst.setInt(1, od);
				// the class C must have class depth cd
				pst.setInt(2, cd);
				// the part of object O must have objectDepth od+1
				pst.setInt(3, od+1);
				// execute the query
				pst.executeUpdate();
				
				
				////////////////////////////////////////////////////////////////////////////
				/////////////// MULTIPLICATION OF ATTRIBUTES ///////////////////////////////
				
				//////////// determine all attribute tables that need to be joined /////////
				pst = conn.prepareStatement("SELECT DISTINCT attributeName "
										  + "FROM   TAttributeList, TIs "
										  + "WHERE  TIs.objectDepth=? AND TIs.classDepth=? AND TIs.class=TAttributeList.className");
				// the object O must have objectDepth od
				pst.setInt(1, od);
				// the class C must have class depth cd
				pst.setInt(2, cd);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					String attributeTableName = rs.getString(1);
					// Multiplication queries for attributes
					pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight) "
							+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, TIs.weight*Attr.Dweight "
							+ "FROM   TIs, TAttr_"+attributeTableName+"_Aggr as Attr "
							+ "WHERE  TIs.objectDepth=? AND TIs.classDepth=? AND TIs.class=Attr.className "
							+ "GROUP BY TIs.object, TIs.class, TIs.declInClass, TIs.declObject");
					
					// the object O must have objectDepth od
					pst.setInt(1, od);
					// the class C must have class depth cd
					pst.setInt(2, cd);
					// execute the query
					pst.executeUpdate();
				}
				
				////////////// END MULTIPLICATION OF ATTRIBUTES ////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////
				
				// TODO
				// Multiplication queries for relations 
				
				
				//////////////////////  EVALUATE SUM NODES /////////////////////////////////////////////
				pst = conn.prepareStatement(""
						+ "INSERT INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight) "
						+ "SELECT TIs.object, TIs.objectDepth, TSub.superclassName, (TIs.classDepth-1), TIs.declAsClass, TIs.declInClass, TIs.declObject, SUM(TIs.weight*TSub.weight) "
						+ "FROM   TIs, TSub "
						+ "WHERE  TIs.objectDepth = ? AND TIs.classDepth = ? "
						+    "AND TSub.classDepth = ? AND TIs.class<>TIs.declAsClass AND TIs.class=TSub.className "
						+ "GROUP BY TIs.object, TSub.superclassName, TIs.declInClass, TIs.declObject");
				
				// the depth of the object must be the current object depth
				pst.setInt(1, od);
				// the depth of the class in the class hierarchy must be the current class depth
				pst.setInt(2, cd);
				// the depth of the current class must be the current class depth
				pst.setInt(3, cd);				
				// execute the query
				pst.executeUpdate();
				
				// decrement the class depth
				cd--;
			} while (cd >= 0);
			// decrement the object depth
			od--;
		} while (od >= 0);
		
		// retrieve ther weight of object T at class TopCass = the partition function Z
		pst = conn.prepareStatement("SELECT weight FROM TIs WHERE object='T' AND class='TopClass'");
		rs = pst.executeQuery();
		
		// retrieve the mysql result and return the value of the partition function
		if (rs.next()) {
			return rs.getDouble(1);
		}
			
		return -1;
	}
	
	
	/**
	 * Computes the partition function of the TPKB. This might take a while depending on the TPKB's size.
	 * @return
	 * @throws SQLException
	 */
	public double computeZWithEvidence() throws SQLException {
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		////////////////// EVIDENCE INCLUSION ///////////////////////////////
		// before we start to compute the partition function with evidence, we 
		// need to compute the nodes that are relevant to the computation
		// Given evidence (O,C)
		// there is a column that indicates that the row was recently updated
		// all operations are now performed only on and with rows that have this update flag set
		pst = conn.prepareStatement("UPDATE TIs "
								  + "JOIN TIsEvidence as TIsE ON TIs.object = TIsE.objectName AND TIs.class = TIsE.className "
								  + "SET TIs.updated=1");
		// execute the queryE
		pst.executeUpdate();
		
		/////////////// END EVIDENCE INCLUSION //////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////////////////////////////
		/////////// Standard part, but only with rows where updated=1 ///////////////////////////
		// compute the maximum depth of the part decomposition
		pst = conn.prepareStatement("SELECT MAX(objectDepth) FROM TIs "); //this can be optimized
		rs = pst.executeQuery();
		
		//the maximum depth of the class hierarchy
		int maxObjectDepth = 0;
		// retrieve the mysql result
		if (rs.next()) {
		     maxObjectDepth = rs.getInt(1);
		}
		int od = maxObjectDepth;
	
		// This is the part of the algorithm that moves up the class hierarchy for all objects
		// at level od
		do {
			//compute the maximum depth of the class hierarchy, restricted to the objects of depth od
			pst = conn.prepareStatement("SELECT MAX(classDepth) FROM TIs WHERE objectDepth=?");
			pst.setInt(1, od);
			rs = pst.executeQuery();
			
			//the maximum depth of the class hierarchy restricted to objects of depth od
			int maxClassDepth = 0;
			// retrieve the mysql result
			if (rs.next()) {
			     maxClassDepth = rs.getInt(1);
			}
			int cd = maxClassDepth;
			
			do {
				
				///////////////////  MULTIPLICATION of SUB-PARTITION FUNCTIONS ////////////////////////////////////////
				//for each (O, C) pair at the current object and class level, check whether there is a 
				// part of object O that was declared in C. If so, multiply the sub-partition function of the part
				// to the sub-partition function of (O,C)
				pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight, updated) "
						+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, TIs.weight*EXP(SUM(LOG(TIs2.weight))), 1 "
						+ "FROM   TIs, TIs as TIs2 "
						+ "WHERE  TIs.objectDepth=? AND TIs.classDepth=? " // object of TIs must have current depth, class of current TIs must have current depth
						+    "AND TIs2.updated=1 AND TIs2.weight>0 AND TIs2.objectDepth=? " // the sub-partition function to be multiplied must be updated, have weight>0, and must have current object depth -1
						+    "AND TIs2.class=TIs2.declAsClass " // the sub-partition function must be computed up to the top class
						+    "AND TIs.object=TIs2.declObject AND TIs.class=TIs2.declInClass " //(O,C) of TIs2 must be a sub-partition function (via a subpart) of (O,C) of TIs
						+ "GROUP BY TIs.object, TIs.class, TIs.declInClass, TIs.declObject");
				////// required key for TIs:   object, class, objectDepth, classDepth [OK]
				////// required key for TIs2:  objectDepth, class, declAsClass, declInClass, declObject, updated, weight [OK]
				
				// the object O must have objectDepth od
				pst.setInt(1, od);
				// the class C must have class depth cd
				pst.setInt(2, cd);
				// the part of object O must have objectDepth od+1
				pst.setInt(3, od+1);
				// execute the query
				pst.executeUpdate();
				////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				///////// BEGIN EVIDENCE INCLUSION  ////////////////////////////////////////////////////////////////////////
				
				//////// Set sub-partition function to 0 if one of its sub-partition functions (via a sub-part) are zero due to evidence
				pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight, updated) "
						+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, 0.0, 1 "
						+ "FROM   TIs, TIs as TIs2 "
						+ "WHERE  TIs.objectDepth=? AND TIs.classDepth=? AND TIs.updated=1 " // object of TIs have current depth and it is updated
						+    "AND TIs2.objectDepth=? AND TIs2.updated=0 " // object of TIs2 has depth one more (has child depth of object from TIs)
						+    "AND TIs2.class=TIs2.declAsClass AND TIs.object=TIs2.declObject AND TIs.class=TIs2.declInClass " // the (O,C) row for TIs2 represents a sub-partition function of a part of the object of TIs 
						+    "AND (TIs2.object IN (SELECT objectName FROM TIsEvidence))"); //object in TIs2 is part of evidence
				////// required key for TIs:   object, class, objectDepth, classDepth, updated [OK]
				////// required key for TIs2:  objectDepth, class, declAsClass, declInClass, declObject, updated [OK]
				
				// the object O must have objectDepth od
				pst.setInt(1, od);
				// the class C must have class depth cd
				pst.setInt(2, cd);
				// the part of object O must have objectDepth od+1
				pst.setInt(3, od+1);
				// execute the query
				pst.executeUpdate();
				
				////////  if evidence is (O,C) then the weight of (O,C')=0 for all classes C' of the same depth as C 
				pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight, updated) "
						+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, 0.0, 1 "
						+ "FROM   TIs, TIs as TIs2, TIsEvidence as TIsE "
						+ "WHERE  TIs.objectDepth=? AND TIs2.objectDepth=? " //both objects have the same depth
						+    "AND TIs2.classDepth=? AND TIs.classDepth=? " // both classes have the same depth
						+    "AND TIs.updated=1 AND TIs2.updated=1 " // both objects are updated
						+    "AND TIsE.objectName=TIs2.object AND TIsE.className=TIs2.class " // object and class of TIs2 constitute evidence
						+    "AND TIs.class<>TIs2.class AND TIs.object=TIs2.object"); // the classes of the objects are different, the objects are identical
				////// required key for TIs:   object, class, objectDepth, classDepth, updated [OK]
				////// required key for TIs2:  object, class, objectDepth, classDepth, updated [OK] 
				
				// the objects must have objectDepth od
				pst.setInt(1, od);
				pst.setInt(2, od);
				// the classes must have class depth cd
				pst.setInt(3, cd);
				pst.setInt(4, cd);
				// execute the query
				pst.executeUpdate();
				
				/////////////// END EVIDENCE INCORPORATION /////////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				////////////////////////////////////////////////////////////////////////////
				/////////////// MULTIPLICATION OF ATTRIBUTES ///////////////////////////////
				
				// computes the updates of the weight caused by the provided evidence //
				pst = conn.prepareStatement("REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight, updated) "
				+ "SELECT TIs.object, TIs.objectDepth, TIs.class, TIs.classDepth, TIs.declAsClass, TIs.declInClass, TIs.declObject, TIs.weight*TAEv.Dweight, 1 "
				+ "FROM   TIs, TAttributeList as TAL, TAttrEvidence as TAEv "
				+ "WHERE  TIs.objectDepth=? AND TIs.classDepth=? "
					+ "AND TIs.object=TAEv.objectName AND TIs.class=TAL.className "
					+ "AND TAL.attributeName=TAEv.attributeName ");
				
				// the object O must have objectDepth od
				pst.setInt(1, od);
				// the class C must have class depth cd
				pst.setInt(2, cd);
				// execute the query
				//System.out.println(pst.toString());
				pst.executeUpdate();
				
				
				////////////// END MULTIPLICATION OF ATTRIBUTES ////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////
				
				// TODO
				// Multiplication queries for relations 
				
					
				////////////////////// BEGIN EVALUATE SUM NODES ////////////////////////////////////////////////////////////////////////
				pst = conn.prepareStatement(""
						+ "REPLACE INTO TIs (object, objectDepth, class, classDepth, declAsClass, declInClass, declObject, weight, updated) "
						+ "SELECT TIs.object, TIs.objectDepth, TSub.superclassName, (TIs.classDepth-1), TIs.declAsClass, TIs.declInClass, TIs.declObject, SUM(TIs.weight*TSub.weight), 1 "
						+ "FROM   TIs, TSub "
						+ "WHERE  TIs.updated=1 " // the row in TIs must be updated
						+    "AND TIs.objectDepth = ? AND TIs.classDepth = ? " // (O,C) pair must have depth values of current iteration
						+    "AND TIs.class<>TIs.declAsClass " // computation for this (O,C) pair must not be finished (reached fixed point)
						+    "AND TIs.class=TSub.className AND TSub.classDepth = ? " // select the subclass relation that apply
						+ "GROUP BY TIs.object, TSub.superclassName, TIs.declInClass, TIs.declObject");
				////// required key for TIs:   objectDepth, class, classDepth, updated, declAsClass [OK]
				////// required key for TSub:  className, classDepth 
								
				// the depth of the object must be the current object depth
				pst.setInt(1, od);
				// the depth of the class in the class hierarchy must be the current class depth
				pst.setInt(2, cd);
				// the depth of the current class must be the current class depth
				pst.setInt(3, cd);				
				// execute the query
				pst.executeUpdate();
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				// decrement the class depth
				cd--;
			} while (cd >= 0);
			// decrement the object depth
			od--;
		} while (od >= 0);
		
		// retrieve ther weight of object T at class TopCass = the partition function Z
		pst = conn.prepareStatement("SELECT weight FROM TIs WHERE object='T' AND class='TopClass'");
		rs = pst.executeQuery();
		
		// retrieve the mysql result and return the value of the partition function
		if (rs.next()) {
			return rs.getDouble(1);
		}
			
		return -1;
	}
	
	
	/**
	 * Writes an entry into the TClass table
	 * @param subclass
	 * @param superclass
	 * @param weight
	 */
	public void writeClassEntry(String className) {
		
		PreparedStatement pst;
		
		try {
			// class names can be encountered several time, hence the ignore statement
			String mysqlQuery = "INSERT IGNORE INTO TClass(className) VALUES(?)";
			pst = conn.prepareStatement(mysqlQuery);
		   	pst.setString(1, className);
	       	pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes an entry into the raw subclass table
	 * @param subclass
	 * @param superclass
	 * @param weight
	 */
	public void writeSubclassEntry(String subclass, String superclass, double weight) {
		
		PreparedStatement pst;
		
		try {
			// inserts the subclasses data into the table 
			String mysqlQuery = "INSERT INTO TSubRaw(className, superclassName, weight) VALUES(?,?,?)";
			pst = conn.prepareStatement(mysqlQuery);
					    	
	    	pst.setString(1, subclass);
	    	pst.setString(2, superclass);
	    	pst.setDouble(3, weight);
						    	
		   	pst.executeUpdate();
		   	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Writes an entry into the TIsRaw table
	 * @param subclass
	 * @param superclass
	 * @param weight
	 */
	public void writeTIsEntry(String objectName, String declAsClass, String declInClass) {
		
		PreparedStatement pst;
		
		try {
			// inserts the part declaration data into the table
			String mysqlQuery = "INSERT INTO TIsRaw(object, class, declAsClass, declInClass) VALUES(?,?,?,?)";
			pst = conn.prepareStatement(mysqlQuery);
		   	pst.setString(1, objectName);
			pst.setString(2, declAsClass);
			pst.setString(3, declAsClass);
			pst.setString(4, declInClass);
	       	pst.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * Based on the raw input, the table TIs is created
	 */
	public void processTIsRaw() {
		
		PreparedStatement pst, pstSubQuery;
		ResultSet rs = null;
		
		int depthCounter = 0;
		int numAffextedRows = 0;
		
		do {
			
			// join the raw table with the subclass table to iteratively fill the table
			// the column objectDepth is used for book keeping 
			String mysqlQuery = "INSERT INTO TIsRaw "
							  + "(SELECT tir.object, ?, tsr.className, tsr.classDepth, tir.declAsClass, tir.declInClass, tir.declObject, tir.weight, tir.updated "
					          + "FROM TIsRaw tir, TSubRaw tsr "
							  + "WHERE tir.class=tsr.superclassName AND tir.objectDepth=?)";
			try {
				pst = conn.prepareStatement(mysqlQuery);
				pst.setInt(1, depthCounter+1);
				pst.setInt(2, depthCounter);
				numAffextedRows = pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			depthCounter++;
			
		} while (numAffextedRows > 0);
				
		
		/////////////////  COMPUTE DEPTH OF OBJECTS /////////////////////////////		
		try {
			// set the object depth of all rows to -1 except for the top object rows
			String mysqlQuery = "UPDATE TIsRaw SET objectDepth=-1";
			pst = conn.prepareStatement(mysqlQuery);
			pst.executeUpdate();
			mysqlQuery = "UPDATE TIsRaw SET objectDepth=0 WHERE object='T'";
			pst = conn.prepareStatement(mysqlQuery);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// a counter keeping track of the current object depth
		depthCounter = 0;
		
		// stores the number of affected rows
		int numberofAffectedRows = 0;
		
		do {
			
			// compute all the (object,class) pairs where an object of depth ? is an instance of class
			String mysqlQuery = "SELECT DISTINCT object, class FROM TIsRaw WHERE objectDepth=?";
			try {
				pst = conn.prepareStatement(mysqlQuery);
				pst.setInt(1, depthCounter);
				rs = pst.executeQuery();
				
				// iterate over all of these classes and check whether there is a part declared where the part class is the current class
				// this case implies that the part is a subpart of the current object
				while (rs.next()) {
					// the name of the parent object
					String objectName = rs.getString(1);
					String subQuery = "UPDATE TIsRaw SET object=CONCAT(?,'.',TIsRaw.object),declObject=?,objectDepth=? WHERE declInClass=? AND objectDepth=-1";
					pstSubQuery = conn.prepareStatement(subQuery);
					pstSubQuery.setString(1, objectName);
					pstSubQuery.setString(2, objectName);
					pstSubQuery.setInt(3, depthCounter+1);
					pstSubQuery.setString(4, rs.getString(2));
					pstSubQuery.executeUpdate();
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

			depthCounter++;
		} while (numberofAffectedRows > 0);
		
		/////////////////// END COMPUTE DEPTH OF OBJECTS /////////////////////////////////////////////
		
		///////////////// REMOVE NON-LEAF ENTRIES ////////////////////////////////////////////////////
		try {
			// select all classes that are a parent of another class
			String mysqlQuery = "DELETE FROM TIsRaw WHERE class NOT IN "
					+ "(SELECT DISTINCT className FROM TClass WHERE className NOT IN (SELECT superclassName FROM TSubRaw))";
			pst = conn.prepareStatement(mysqlQuery);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		///////////////// COPY RAW TABLE  ////////////////////////////////////////////////////
		try {
			// Select all children from the root nodes of the class hierarchy
			String mysqlQuery = "INSERT INTO TIs (SELECT * FROM TIsRaw)";
			pst = conn.prepareStatement(mysqlQuery);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Based on the raw input, the table TSub is created
	 */
	public void processTSubRaw() {
		
		PreparedStatement pst, pstSubQuery;
		ResultSet rs = null;
		
		try {
			// Select all children of the root nodes of the class hierarchy
			String mysqlQuery = "SELECT DISTINCT className FROM TSubRaw WHERE superclassName NOT IN (SELECT className FROM TSubRaw)";
			pst = conn.prepareStatement(mysqlQuery);
			rs = pst.executeQuery();
			
			// iterate over all children of the class hierarchy's roots and set the depth to 1
			while (rs.next()) {
				String subQuery = "UPDATE TSubRaw SET classDepth=1 WHERE className=?";
				pstSubQuery = conn.prepareStatement(subQuery);
				pstSubQuery.setString(1, rs.getString(1));
				pstSubQuery.executeUpdate();
			}
		   	
			boolean isNonEmptyResult = true;
			int depthCounter = 1;
			
			do {
			
				// select all classes with a depth of 'depthCounter'
				mysqlQuery = "SELECT DISTINCT tsr1.className FROM TSubRaw tsr1, TSubRaw tsr2 WHERE tsr1.superclassName=tsr2.className AND tsr2.classDepth=?";
				pst = conn.prepareStatement(mysqlQuery);
				pst.setInt(1, depthCounter);
				rs = pst.executeQuery();
				
				// return if there are no classes with this depth
				isNonEmptyResult = rs.next();
				if (isNonEmptyResult) {
								
					do {
						// set the depth to the corresponding value
						String subQuery = "UPDATE TSubRaw SET classDepth=? WHERE className=?";
						pstSubQuery = conn.prepareStatement(subQuery);
						pstSubQuery.setInt(1, depthCounter+1);
						pstSubQuery.setString(2, rs.getString(1));
						pstSubQuery.executeUpdate();
					} while (rs.next());
					
					depthCounter++;
				}
			} while (isNonEmptyResult);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		///////////////// CHECK IF THERE ARE -1 VALUES --> class hierarchy invalid
		try {
			// Select all children from the root nodes of the class hierarchy
			String mysqlQuery = "DELETE FROM TSubRaw WHERE classDepth<0";
			pst = conn.prepareStatement(mysqlQuery);
			if (pst.executeUpdate() > 0) {
				System.err.println("The class hierarchy is invalid. The class hierarchy must be a forrest.");
				System.exit(0);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		///////////////// COPY RAW TABLE  ////////////////////////////////////////////////////
		try {
			// Select all children from the root nodes of the class hierarchy
			String mysqlQuery = "INSERT INTO TSub (SELECT * FROM TSubRaw)";
			pst = conn.prepareStatement(mysqlQuery);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Truncate all tables
	 */
	public void truncate() {
		
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst = conn.prepareStatement("TRUNCATE TClass");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TSubRaw");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TClass");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TIsRaw");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TSub");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TIs");
			pst.executeUpdate();
			pst = conn.prepareStatement("TRUNCATE TAttributeList");
			pst.executeUpdate();
			
			// group all attribute tables by (className, Dvalue) and store in new tables
			pst = conn.prepareStatement("SELECT table_name FROM information_schema.tables "
										+ "WHERE table_schema='tpkb' AND table_name LIKE 'TAttr\\_%'");
			rs = pst.executeQuery();
			while (rs.next()) {
				String tableName = rs.getString(1);
				pst = conn.prepareStatement("DROP TABLE "+tableName);
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Creates or updates an attribute table  
	 * @param attributeName
	 * @param type if true, then the data is loaded from a CSV fie
	 */
	public void writeAttributeTableEntry(String attributeName, String className, boolean type) {
		
		PreparedStatement pst;
		String mySQLQuery;
		
		// if type=true, then we create the attribute table and load the data from a CSV file 
		// if type=false, then there exists an attribute table in the DB
		if (type) {
			
			try
	        {
	        	// create the attribute table if it does not exist
	        	pst = conn.prepareStatement(""
	        			+ "CREATE TABLE IF NOT EXISTS "+attributeTablePrefix+attributeName+" ("
	        			+ "className varchar(20) NOT NULL,"
	        			+ "Dvalue varchar(20) NOT NULL,"
	        			+ "Dweight double NOT NULL,"
	        			+ "PRIMARY KEY (`className`,`Dvalue`)," // index is important for aggregation queries etc.
	        			+ "INDEX (`Dvalue`)"
	        			+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1");
	        	pst.executeUpdate();
	        	
	        	// if the table was newly created, then 
		        // insert the data into the table via a local infile statement (very efficient!)
		        mySQLQuery = "LOAD DATA LOCAL INFILE '"+attributeName+".csv"+"' IGNORE INTO TABLE "+attributeTablePrefix+attributeName+" FIELDS TERMINATED BY ','";
		        //System.out.println(mySQLQuery);
		        pst = conn.prepareStatement(mySQLQuery);
		        pst.executeUpdate();
	        	
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
		}
		
		// update table that stores mapping from class names to attribute names
    	mySQLQuery = "INSERT TAttributeList (className, attributeName) VALUES (?,?)";
    	//System.out.println(mySQLQuery);
    	try {
			pst = conn.prepareStatement(mySQLQuery);
			pst.setString(1, className);
	    	pst.setString(2, attributeName);
	    	pst.executeUpdate();
		} catch (SQLException e) {
			System.err.println("There's something wrong with the declaration of attributes.");
			e.printStackTrace();
		}
    	
	}
}
