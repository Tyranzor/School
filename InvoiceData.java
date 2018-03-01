package com.ceg.ext;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */
import java.sql.*;

public class InvoiceData {
	static Connection conn = null;
    static Statement stmt = null;
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jthorne";

    //  Database credentials
    static final String USER = "jthorne";
    static final String PASS = "mSnhz4";
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
        sql = "UPDATE Invoice SET salesperson=null";
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

	
	
		
        
	
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
	int id=0;
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
        
        String sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";

        ResultSet rs = stmt.executeQuery(sql);

        
       while(rs.next()){
	       //Retrieve by column name
	      id  = rs.getInt("id");
	      System.out.println(""+id);
	      }
       
// inserts person and address into appropriate columns and places
       String values= "("+"'"+personCode+"'"+","+"'"+firstName+"'"+","+"'"+lastName+"'"+")";
       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
        sql= "INSERT INTO Address (Street,City,State,Zip,Country) VALUES "+advalues;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Person(personCode,lastName,firstName)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Person SET address = "+"'"+id+"'"+"WHERE Person.personCode="+"'"+personCode+"'";
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
	        sql = "UPDATE Person SET emailList = "+"'"+email+"'"+"WHERE personCode= "+"'"+personCode+"'";
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
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
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
	        sql = "UPDATE Invoice SET customer=null";
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        sql="DELETE FROM Customer";
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
	

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
	
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
        
        
        String sql = "SELECT  Address.id FROM Address ORDER  BY Address.id DESC LIMIT 1";

        ResultSet rs = stmt.executeQuery(sql);

        int id=0;
       while(rs.next()){
	       //Retrieve by column name
	       id  = rs.getInt("id");
	      System.out.println(""+id);
	      }
        
// inserts customer and address into appropriate columns and places
       String values= "("+"'"+customerCode+"'"+","+"'"+customerType+"'"+","+"'"+primaryContactPersonCode+"'"+","+"'"+name+"'"+","+"'"+id+"'"+")";
       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
         sql= "INSERT INTO Address (Street,City,State,Zip,Country) VALUES "+advalues;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Customer (customerCode,type,primaryContact,name,address)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Customer SET address = "+"'"+id+"'"+"WHERE Customer.customerCode="+"'"+customerCode+"'";
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
        sql = "UPDATE Invoice SET product=null";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
        sql="DELETE FROM Product";
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
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
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
       String values= "("+"'"+productCode+"'"+","+"'"+"M"+"'"+","+"'"+dateTime+"'"+","+"'"+movieName+"'"+","+"'"+screenNo+"'"+","+"'"+pricePerUnit+"'"+")";
       String advalues= "("+"'"+street+"'"+","+"'"+city+"'"+","+"'"+state+"'"+","+"'"+zip+"'"+","+"'"+country+"'"+")";
        String sql= "INSERT INTO Address (Street,City,State,Zip,Country) VALUES "+advalues;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Product (pCode,pType,dateTime,movieName,screenNo,cost)  VALUES "+values;
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
	
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {try{
		
		
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
       String values= "("+"'"+productCode+"'"+","+"'"+"S"+"'"+","+"'"+name+"'"+","+"'"+seasonStartDate+"'"+","+"'"+seasonEndDate+"'"+","+"'"+cost+"'"+")";
    
    String    sql = "INSERT INTO Product (pCode,pType,name,startDate,endDate,cost)  VALUES "+values;
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
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {try{
		
		
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
    
    String    sql = "INSERT INTO Product (pCode,pType,cost)  VALUES "+values;
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
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
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
        String sql = "INSERT INTO Product (pCode,pType,name,cost)  VALUES "+values;
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
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {try{
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
//        sql = "UPDATE Invoice SET product=null";
//        System.out.println(sql);
//        stmt.executeUpdate(sql);
        
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
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate, String productCode) {
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
        int id=0;
        String sql="SELECT  Invoice.id FROM Invoice ORDER BY Invoice.id DESC LIMIT 1";
        ResultSet rs = stmt.executeQuery(sql);

        
        while(rs.next()){
 	       //Retrieve by column name
 	      id  = rs.getInt("id")+1;
 	      System.out.println(""+id);}
        
        
       String values= "("+"'"+invoiceCode+"'"+","+"'"+invoiceDate+"'"+","+"'9', 'M'"+")";
       sql = "INSERT INTO Invoice (invoiceCode,date,quantity,pType)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET customer = "+"'"+customerCode+"'"+"WHERE Invoice.id= "+"'"+id+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET salesperson = "+"'"+salesPersonCode+"'"+"WHERE Invoice.id= "+"'"+id+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.id= "+"'"+id+"'";
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
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
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
	       String values= "("+"'"+invoiceCode+"'"+","+"'"+quantity+"'"+","+"'"+"M"+"'"+")";
	        String sql = "INSERT INTO Invoice (invoiceCode,quantity,pType)  VALUES "+values;
	        System.out.println(sql);
	        stmt.executeUpdate(sql);
	        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"M"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
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
	

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {
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
       String values= "("+"'"+invoiceCode+"'"+","+"'"+quantity+"'"+","+"'"+"S"+"'"+")";
        String sql = "INSERT INTO Invoice (invoiceCode,quantity,pType)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"S"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
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
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: ticketCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {try{
		
		
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
       String values= "("+"'"+invoiceCode+"'"+","+"'"+quantity+"'"+","+"'"+"P"+"'"+")";
        String sql = "INSERT INTO Invoice (invoiceCode,quantity,pType)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"P"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
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
     * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {try{
		
		
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
       String values= "("+"'"+invoiceCode+"'"+","+"'"+quantity+"'"+","+"'"+"R"+"'"+")";
        String sql = "INSERT INTO Invoice (invoiceCode,quantity,pType)  VALUES "+values;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        sql = "UPDATE Invoice SET product = "+"'"+productCode+"'"+"WHERE Invoice.pType= "+"'"+"R"+"'"+" AND Invoice.quantity="+"'"+quantity+"'";
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

}
