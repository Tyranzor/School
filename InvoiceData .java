package readersWriters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

import containers.DayMembership;
import containers.Invoice;
import containers.Member;
import containers.ParkingPass;
import containers.Person;
import containers.Asset;
import containers.EquipmentRental;
import containers.YearLongMembership;

public class InvoiceData {

	static Connection conn = null;
	static Statement stmt = null;
	
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//
	static final String DB_URL = "cse.unl.edu";
	
	//Database login info
	static final String USER = "jtrost";
	static final String PASS = "YGVud5";
	
	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		
		try{
	        //STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();

	       
	        String sql;
	        sql = "UPDATE Invoice SET personalTrainer = null";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE Member SET person = null";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql="DELETE FROM Person";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	      
		    //STEP 6: Clean-up environment
		    stmt.close();
		    conn.close();
	      
	      }catch(SQLException se){
	         
	    	  //Handle errors for JDBC
	    	  se.printStackTrace();
	    	  
	      }catch(Exception e){
	    	
	    	  //Handle errors for Class.forName
	    	  e.printStackTrace();
	    	  
	      }finally{
	    	  
	        //finally block used to close resources
	        try{
	        	
	           if(stmt != null)
	              stmt.close();
	           
	        }catch(SQLException se2){
	        }// nothing we can do
	        
	        try{
	           
	        	if(conn != null)
	              conn.close();
	        	
	        }catch(SQLException se){
	        	
	            se.printStackTrace();
	            
	        }//end finally try
	     }//end try
		
	     System.out.println("Goodbye!");
		
		
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		
		
		
		try{
			
			
        //STEP 2: Register JDBC driver 
        Class.forName("com.mysql.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        System.out.println("Connected to database...");

        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        
        
        String sql;
	      
       
// inserts person and address into appropriate columns and places
        String values= "("+"'"+personCode+"'"+","+"'"+firstName+"'"+","+"'"+lastName+"'"+")";
        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
        
        sql= "INSERT INTO Address (Street,City,State,Zip,Country) VALUES "+advalues;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
        sql = "INSERT INTO Person (personCode,lastName,firstName) VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
        sql = "UPDATE Person SET address = "+"'"+street+"'"+"WHERE Person.personCode="+"'"+personCode+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
      }catch(SQLException se){
    	  
         //Handle errors for JDBC
         se.printStackTrace();
         
      }catch(Exception e){
    	  
        //Handle errors for Class.forName
        e.printStackTrace();
        
      }finally{
    	  
        //finally block used to close resources
        try{
        	
           if(stmt != null)
              stmt.close();
           
        }catch(SQLException se2){
        }// nothing we can do
        
        try{
        	
           if(conn != null)
              conn.close();
           
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
     }//end try
	
     System.out.println("Goodbye!");
		
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		
		try{
	        //STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();

	        String sql;
	        sql = "UPDATE Person SET emails = "+"'"+email+"'"+"WHERE personCode= "+"'"+personCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	     
	      
	      //STEP 6: Clean-up environment
	      stmt.close();
	      conn.close();
	      
	      }catch(SQLException se){
	    	  
	         //Handle errors for JDBC
	         se.printStackTrace();
	         
	      }catch(Exception e){
	    	  
	        //Handle errors for Class.forName
	        e.printStackTrace();
	        
	      }finally{
	    	  
	        //finally block used to close resources
	        try{
	        	
	           if(stmt != null)
	              stmt.close();
	           
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	        	
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	        	
	            se.printStackTrace();
	            
	        }//end finally try
	     }//end try
		
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		
		try{
			
	        //STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	       
	        String sql;
	        sql = "UPDATE Invoice SET member = null";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql="DELETE FROM Member";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	      
	        //STEP 6: Clean-up environment
	        stmt.close();
	        conn.close();
	        
	        }catch(SQLException se){
	        	
	        //Handle errors for JDBC
	        se.printStackTrace();
	        
	        }catch(Exception e){
	        	
	        //Handle errors for Class.forName
	        e.printStackTrace();
	        
	        }finally{
	        //finally block used to close resources
	        try{
	        	
	            if(stmt != null)
	            stmt.close();
	            
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	        	
	        	if(conn != null)
	            conn.close();
	        	
	        }catch(SQLException se){
	        	
	            se.printStackTrace();
	            
	        }//end finally try
	     }//end try
		
	     System.out.println("Goodbye!");
		
	}
	/**
	 * 5. Method to add a member record to the database with the provided data
	 * @param memberCode
	 * @param memberType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addMember(String memberCode, String memberType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		
		try{

	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        
	        String sql;

	      
	      
	        
	// inserts member and address into appropriate columns and places
	       String values= "("+"'"+memberCode+"'"+","+"'"+memberType+"'"+","+"'"+primaryContactPersonCode+"'"+","+"'"+name+"'"+","+"'"+street+"'"+")";
	       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	       sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        sql = "INSERT INTO Member (memberCode,type,person,name,address) VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        sql = "UPDATE Member SET address = "+"'"+street+"'"+"WHERE Member.memberCode="+"'"+memberCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}
	
	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
		try{
	        //STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        String sql;
	        /*
	        sql = "UPDATE Invoice SET product = null";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        */
	        
	        sql="DELETE FROM YearMembership";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql="DELETE FROM DayMembership";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	      
	        sql="DELETE FROM ParkingPass";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql="DELETE FROM EquipmentRental";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	      //STEP 6: Clean-up environment
	      stmt.close();
	      conn.close();
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, double pricePerUnit) {
		
		try{
			
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        
	        
	// inserts customer and address into appropriate columns and places
	       String values= "("+"'"+productCode+"'"+","+"'"+"D"+"'"+","+"'"+dateTime+"'"+","+"'"+pricePerUnit+"'"+")";
	       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	       
	       String sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "INSERT INTO DayMembership (productCode,identifier,timeDate,subtotal) VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE DayMembership SET address = "+"'"+street+"'"+"WHERE DayMembership.productCode="+"'"+productCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
		
		try{
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        
	   
	        
	// inserts customer and address into appropriate columns and places
	       String values= "("+"'"+productCode+"'"+","+"'"+"S"+"'"+","+"'"+StartDate+"'"+","+"'"+EndDate+"'"+","+"'"+pricePerUnit+"'"+")";
	       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	       
	       String sql = "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	       
	        sql = "INSERT INTO YearMembership (productCode,identifier,timeDateStart,timeDateEnd,subtotal) VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE YearMembership SET address = "+"'"+street+"'"+"WHERE YearMembership.productCode="+"'"+productCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
        
		try{
		
        //STEP 2: Register JDBC driver 
        Class.forName("com.mysql.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        System.out.println("Connected to database...");

        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        
// inserts info into appropriate columns and places
       String values= "("+"'"+productCode+"'"+","+"'P',"+"'"+parkingFee+"'"+")";
    
       String sql = "INSERT INTO ParkingPass (productCode,identifier,subtotal) VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
      }finally{
        //finally block used to close resources 
        try{
           if(stmt != null)
              stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
           if(conn != null)
              conn.close();
           
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
     }//end try
     System.out.println("Goodbye!");
		
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
        
		try{
			
			
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        
	        
	// inserts customer and address into appropriate columns and places
	       String values= "("+"'"+productCode+"'"+","+"'"+"R"+"'"+","+"'"+name+"'"+","+"'"+cost+"'"+")";
	        String sql = "INSERT INTO Product (productCode,identifier,name,subtotal)  VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
        
		try{
	        //STEP 2: Register JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();

	       
	        String sql;
//	        sql = "UPDATE Invoice SET product=null";
//	        System.out.println(sql);
//	        stmt.executeUpdate(sql);
	        
	        sql="DELETE FROM Invoice";
	        System.out.println(sql);
	      stmt.executeUpdate(sql);
	      
	      //STEP 6: Clean-up environment
	      stmt.close();
	      conn.close();
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate) {
		
		try{
			
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	        String sql;
	       String values= "("+"'"+invoiceCode+"'"+","+"'"+invoiceDate+"'"+")";
	       sql = "INSERT INTO Invoice (invoiceCode,timeDate)  VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE Invoice SET member = "+"'"+memberCode+"'"+"WHERE Invoice.invoiceCode= "+"'"+invoiceCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE Invoice SET personalTrainer = "+"'"+personalTrainerCode+"'"+"WHERE Invoice.invoiceCode= "+"'"+invoiceCode+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	        /*
	        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.id= "+"'"+id+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        */
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
		
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		
			try{
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	// inserts customer and address into appropriate columns and places
	       //String values= "("+"'"+invoiceCode+"'"+")";
	       String memvalues = "("+"'"+invoiceCode+"'"+","+"'"+productCode+"'"+","+"'"+quantity+"'"+","+"'"+"D"+"'"+")";
	       	String sql = "INSERT INTO DayMembership (invoiceCode, productCode, quantity, identifier) VALUES "+memvalues;
	       
//	       	sql = "INSERT INTO Invoice (invoiceCode)  VALUES "+values;
//	        System.out.println(sql);
//	        stmt.executeUpdate(sql);
	        
	        //sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"M"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
	        //System.out.println(sql);
	        //stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		
		try{
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	// inserts customer and address into appropriate columns and places
	      // String values= "("+"'"+invoiceCode+"'"+")";
	       String memvalues = "("+"'"+invoiceCode+"'"+","+"'"+productCode+"'"+","+"'"+quantity+"'"+","+"'"+"Y"+"'"+")";
	       	String sql = "INSERT INTO YearMembership (invoiceCode, productCode, quantity, identifier) VALUES "+memvalues;
	       
//	       	sql = "INSERT INTO Invoice (invoiceCode)  VALUES "+values;
//	        System.out.println(sql);
//	        stmt.executeUpdate(sql);
	        
	        //sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"M"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
	        //System.out.println(sql);
	        //stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
		
	}

     /**
     * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: membershipCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	
try{
			
	        //STEP 2: Register JDBC driver 
	        Class.forName("com.mysql.jdbc.Driver");

	        //STEP 3: Open a connection
	        System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        System.out.println("Connected to database...");

	        //STEP 4: Execute a query
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        
	// inserts customer and address into appropriate columns and places
	      String values= "("+"'"+membershipCode+"'"+")";
	       String memvalues = "("+"'"+invoiceCode+"'"+","+"'"+productCode+"'"+","+"'"+quantity+"'"+","+"'"+"P"+"'"+")";
	       	String sql = "INSERT INTO ParkingPass (invoiceCode, productCode, quantity, identifier, ) VALUES "+memvalues;
	       
//	       	sql = "INSERT INTO Invoice (invoiceCode)  VALUES "+values;
//	        System.out.println(sql);
//	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE Invoice SET member = "+"'"+membershipCode+"'"+"WHERE Invoice.quantity="+"'"+quantity+"'";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        
	      }catch(SQLException se){
	         //Handle errors for JDBC
	         se.printStackTrace();
	      }catch(Exception e){
	        //Handle errors for Class.forName
	        e.printStackTrace();
	      }finally{
	        //finally block used to close resources 
	        try{
	           if(stmt != null)
	              stmt.close();
	        }catch(SQLException se2){
	        }// nothing we can do
	        try{
	           if(conn != null)
	              conn.close();
	           
	        }catch(SQLException se){
	            se.printStackTrace();
	        }//end finally try
	     }//end try
	     System.out.println("Goodbye!");
		
    	
    }
	
    /**
     * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     * NOTE: membershipCode may be null
     */
    public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
    	/** TODO*/
    }

   
}
