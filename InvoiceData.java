package com.sf.ext;

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
	
	
	//Create the connect and statement variables
	static Connection conn = null;
	static Statement stmt = null;
	
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jtrost";
	
	//Database login info
	static final String USER = "jtrost";
	static final String PASS = "YGVud5";
	
	
	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		
		try{
	        
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();

	        String sql;
	        
	        //Remove all foreign key dependencies
	        sql = "UPDATE Invoice SET personalTrainer = null";
	        stmt.executeUpdate(sql);
	        
	        sql = "UPDATE Invoice SET member = null";
	        stmt.executeUpdate(sql);
	        
	        //Members must be deleted when deleting all Persons due to the dependency on persons
	        sql="DELETE FROM Member";
	        stmt.executeUpdate(sql);
	        
	        //Delete all Persons
	        sql="DELETE FROM Person";
	        stmt.executeUpdate(sql);
	      
		    //Clean-up environment
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
				
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
        
	        String sql;
       
	        //Strings to pass the values to the query properly formatted
	        String values= "("+"'"+personCode+"'"+","+"'"+firstName+"'"+","+"'"+lastName+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	        
	        //Create the Address for the Person
	        sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently updated address
	        int id = 0;
	        sql = "SELECT Address.id FROM Address ORDER BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);
        
	        while(rs.next()){
		       //Retrieve by column name
		      id  = rs.getInt("id");
		    }
	        
	        //Create the Person
	        sql = "INSERT INTO Person (personCode,lastName,firstName) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Person and set the Address to the ID of the Address added in the first step
	        sql = "UPDATE Person SET address = "+id+" WHERE Person.personCode = "+"'"+personCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();

	        String sql;
	        
	        //Add the email(s) to the Person indicated
	        sql = "UPDATE Person SET emails = "+"'"+email+"'"+" WHERE personCode = "+"'"+personCode+"'";
	        stmt.executeUpdate(sql);
	     
	        //Clean-up environment
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
		
	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	       
	        String sql;
	        
	        //Remove foreign key dependency
	        sql = "UPDATE Invoice SET member = null";
	        stmt.executeUpdate(sql);
	        
	        //Delete all members
	        sql="DELETE FROM Member";
	        stmt.executeUpdate(sql);
	      
	        //Clean-up environment
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

			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        String sql;
	 
	        //Format the values for the queries
	        String values= "("+"'"+memberCode+"'"+","+"'"+memberType+"'"+","+"'"+primaryContactPersonCode+"'"+","+"'"+name+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	        
	        //Create a new Address with the values given
	        sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently updated Address
	        int id = 0;
	        sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()){
	        	//Retrieve by column name
	        	id  = rs.getInt("id");
		    }
	        
	        //Create a new Member with the values given
	        sql = "INSERT INTO Member (memberCode,memType,person,memName) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Member and set the Address to the ID of the Address added earlier
	        sql = "UPDATE Member SET address = "+id+" WHERE Member.memberCode="+"'"+memberCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}
	
	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
		try{
	       
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        String sql;
	        
	        //Delete all Year Memberships
	        sql="DELETE FROM YearMembership";
	        stmt.executeUpdate(sql);
	        
	        //Delete all Day Memberships
	        sql="DELETE FROM DayMembership";
	        stmt.executeUpdate(sql);
	        
	        //Delete all Parking Passes
	        sql="DELETE FROM ParkingPass";
	        stmt.executeUpdate(sql);
	        
	        //Delete all Equipment Rentals
	        sql="DELETE FROM EquipmentRental";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, int quantity,
			double discount, double tax, double subtotal, double total) {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        //Create formatted strings with values for the queries
	        String values= "("+"'"+productCode+"'"+","+"'"+"D"+"'"+","+"'"+dateTime+"'"+","+"'"+quantity+"'"+","+"'"+discount+"'"+","+"'"+tax+"'"+","+"'"+subtotal+"'"+","+"'"+total+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	        
	        //Create a new address
	       	String sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently created address
	        int id = 0;
	        sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()){
	        	//Retrieve by column name
	        	id  = rs.getInt("id");
		    }
	        
	        //Create new Day Membership
	        sql = "INSERT INTO DayMembership (productCode,identifier,timeDate,quantity,discount,tax,subtotal,total ) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Day Membership and set the Address to the ID of the Address just created
	        sql = "UPDATE DayMembership SET address = "+id+" WHERE DayMembership.productCode="+"'"+productCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, int quantity,
			double discount, double tax, double subtotal, double total) {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        //Create formatted strings with the values for queries
	        String values= "("+"'"+productCode+"'"+","+"'"+"Y"+"'"+","+"'"+StartDate+"'"+","+"'"+EndDate+"'"+","+"'"+name+"'"+","+"'"+quantity+"'"+","+"'"+discount+"'"+","+"'"+tax+"'"+","+"'"+subtotal+"'"+","+"'"+total+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	        
	        //Create a new Address
	        String sql = "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently updated Address
	        int id = 0;
	        sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()){
	        	//Retrieve by column name
	        	id  = rs.getInt("id");
		    }
	       
	        //Create a new Year Membership
	        sql = "INSERT INTO YearMembership (productCode,identifier,timeDateStart,timeDateEnd,membershipName,quantity,"
	        		+ "discount,tax,subtotal,total) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the new Year Membership and set the Address to the Address ID created previously
	        sql = "UPDATE YearMembership SET address = "+id+" WHERE YearMembership.productCode="+"'"+productCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, int quantity,
			double discount, double tax, double subtotal, double total) {
        
		try{
		
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
        
	        //Create a formatted string with the values for the query
	        String values= "("+"'"+productCode+"'"+","+"'"+"P"+"'"+","+quantity+","+discount+","+tax+","+subtotal+","+total+")";
	        
	        //Create a new Parking Pass
	        String sql = "INSERT INTO ParkingPass (productCode,identifier,quantity, discount,tax,subtotal,total) VALUES "+values;
	        stmt.executeUpdate(sql);
        
	        //Clean-up environment
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
		
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double quantity,
			double discount, double tax, double subtotal, double total) {
        
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        
	        //Create formatted strings with the values for the queries
			String values= "("+"'"+productCode+"'"+","+"'"+"R"+"'"+","+"'"+name+"'"+","+quantity+","+discount+","+tax+","+subtotal+","+total+")";
	        
			//Create a new Equipment Rental
			String sql = "INSERT INTO EquipmentRental (productCode,identifier,rentalName,quantity,discount,"
					+ "tax,subtotal,total) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
        
		try{
	       
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();

	        String sql;
	        
	        //Remove foreign key dependency
	        sql = "UPDATE DayMembership SET invoiceCode = null";
	        stmt.executeUpdate(sql);
	        
	        //Remove foreign key dependency
	        sql = "UPDATE YearMembership SET invoiceCode = null";
	        stmt.executeUpdate(sql);
	        
	        //Remove foreign key dependency
	        sql = "UPDATE ParkingPass SET invoiceCode = null";
	        stmt.executeUpdate(sql);
	        
	        //Remove foreign key dependency
	        sql = "UPDATE EquipmentRental SET invoiceCode = null";
	        stmt.executeUpdate(sql);
	        
	        //Delete the Invoices
	        sql="DELETE FROM Invoice";
	        stmt.executeUpdate(sql);
	      
	        //Clean-up environment
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
		
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate,
			double discount, double tax, double subtotal, double total) {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	        
	        String sql;
	        
	        //Create formatted strings with the values for the query
	        String values= "("+"'"+invoiceCode+"'"+","+"'"+invoiceDate+"'"+","+"'"+
	        discount+"'"+","+"'"+tax+"'"+","+"'"+subtotal+"'"+","+"'"+total+"'"+")";
	      
	        //Create a new Invoice
	        sql = "INSERT INTO Invoice (invoiceCode,timeDate,discount,tax,subtotal,total)  VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Invoice and set the Member to the Member Code 
	        sql = "UPDATE Invoice SET member = "+"'"+memberCode+"'"+"WHERE Invoice.invoiceCode= "+"'"+invoiceCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Update the Invoice and set the Personal Trainer to the Person Code
	        sql = "UPDATE Invoice SET personalTrainer = "+"'"+personalTrainerCode+"'"+"WHERE Invoice.invoiceCode= "+"'"+invoiceCode+"'";
	        stmt.executeUpdate(sql);
	       
	        //Clean-up environment
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
		
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String productCode, String dateTime, String street, String city, String state, String zip, String country, int quantity,
			double discount, double tax, double subtotal, double total,String InvoiceCode) {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	       
	        //Create formatted strings with the values for the queries
	        String values= "("+"'"+productCode+"'"+","+"'"+"D"+"'"+","+"'"+dateTime+"'"+","+"'"+quantity+"'"+","+"'"+discount+"'"+","+"'"+tax+"'"+","+"'"+subtotal+"'"+","+"'"+total+"'"+","+"'"+InvoiceCode+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	       
	        //Create a new Address
	        String sql= "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently created Address
	        int id = 0;
	        sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()){
	        	//Retrieve by column name
	        	id  = rs.getInt("id");
		    }
	        
	        //Create new Day Membership
	        sql = "INSERT INTO DayMembership (productCode,identifier,timeDate,quantity,discount,tax,subtotal,total,invoiceCode) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Day Membership and set the Address to the Address ID previously created
	        sql = "UPDATE DayMembership SET address = "+id+" WHERE DayMembership.productCode="+"'"+productCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, int quantity,
			double discount, double tax, double subtotal, double total, String InvoiceCode) {
		
		try{
			
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
	   
	        
	        //Create formatted strings with the values for the queries
	        String values= "("+"'"+productCode+"'"+","+"'"+"Y"+"'"+","+"'"+StartDate+"'"+","+"'"+EndDate+"'"+","+"'"+quantity+"'"+","+"'"+discount+"'"+","+"'"+tax+"'"+","+"'"+subtotal+"'"+","+"'"+total+"'"+","+"'"+InvoiceCode+"'"+","+"'"+name+"'"+")";
	        String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
	       
	        //Create a new Address
	        String sql = "INSERT INTO Address (street,city,state,zip,country) VALUES "+advalues;
	        stmt.executeUpdate(sql);
	        
	        //Select the recently created Address
	        int id = 0;
	        sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";
	        ResultSet rs = stmt.executeQuery(sql);

	        while(rs.next()){
	        	//Retrieve by column name
	        	id  = rs.getInt("id");
		    }
	       
	        //Create a new Year Membership
	        sql = "INSERT INTO YearMembership (productCode,identifier,timeDateStart,timeDateEnd,quantity, discount,tax, subtotal, total, invoiceCode, membershipName) VALUES "+values;
	        stmt.executeUpdate(sql);
	        
	        //Update the Year Membership and set the Address to the Address ID previously created
	        sql = "UPDATE YearMembership SET address = "+id+" WHERE YearMembership.productCode="+"'"+productCode+"'";
	        stmt.executeUpdate(sql);
	        
	        //Clean-up environment
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
		
	}

     /**
     * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: membershipCode may be null
     */
	public static void addParkingPassToInvoice(String productCode, int quantity,
			double discount, double tax, double subtotal, double total,String InvoiceCode) {
        
		try{
		
			//Register JDBC driver
	        Class.forName(JDBC_DRIVER);
	       
	        //Open a connection
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

	        //Execute a query
	        stmt = conn.createStatement();
        
	        //Create a formatted string of values for the query
	        String values= "("+"'"+productCode+"'"+","+"'"+"P"+"'"+","+quantity+","+discount+","+tax+","+subtotal+","+total+","+"'"+InvoiceCode+"'"+")";
	        
	        //Create a new Parking Pass
	        String sql = "INSERT INTO ParkingPass (productCode,identifier,quantity,"
	        		+ "discount,tax,subtotal,total,invoiceCode) VALUES "+values;
	        stmt.executeUpdate(sql);
        
	        //Clean-up environment
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
		
	}
	
    /**
     * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     * NOTE: membershipCode may be null
     */
    	public static void addRentalToInvoice(String productCode, String name, double quantity,
    			double discount, double tax, double subtotal, double total,String InvoiceCode) {
            
    		try{
    			
    			//Register JDBC driver
    	        Class.forName(JDBC_DRIVER);
    	       
    	        //Open a connection
    	        conn = DriverManager.getConnection(DB_URL,USER,PASS);

    	        //Execute a query
    	        stmt = conn.createStatement();
    	        
    	        //Create a formatted string with the values for the query
    			String values= "("+"'"+productCode+"'"+","+"'"+"R"+"'"+","+quantity+","+discount+","+tax+","+subtotal+","+total+","
    					+"'"+InvoiceCode+"'"+","+"'"+name+"'"+")";
    			
    			//Create a new Equipment Rental
    	        String sql = "INSERT INTO EquipmentRental (productCode,identifier,quantity,discount,tax,subtotal,"
    	        		+ "total,invoiceCode,rentalName) VALUES "+values;
    	        stmt.executeUpdate(sql);     
    	        
    	        //Clean-up environment
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
    		
    	}
  
}