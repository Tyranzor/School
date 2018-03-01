package com.ceg.ext;
import java.sql.*;
import java.util.*;
/**
 * 
 * @author Nathan Pittman
 * with assistance from Jeremy Thorne
 * @version 1
 * 
 *
 */
public class InvoiceGetter {
	//ArrayLists for data from arrays
	private static ArrayList<Integer> ids = new ArrayList<Integer>();
	private static ArrayList<Integer> quantities = new ArrayList<Integer>();
	private static ArrayList<String> invoiceCodes = new ArrayList<String>();
	private static   ArrayList<String> dates = new ArrayList<String>();
	private static  ArrayList<String> customers = new ArrayList<String>();
	private static  ArrayList<String> salespersons = new ArrayList<String>();
	private static  ArrayList<String> products = new ArrayList<String>();
	private static  ArrayList<String> pTypes= new ArrayList<String>();
	//ArrayList of finished invoices
	private static ArrayList<Invoice> invoices=new ArrayList<Invoice>();
	private static InvoiceList l1=  new InvoiceList();
	/**
	 * Retrieves Invoices from Database and adds them to the List
	 * Prints list to ensure proper functionality
	 * @param args
	 */
	public static void main(String[] args) {
		getInvoices(invoices);
		for (int i=0; i<invoices.size();i++){
			invoices.get(i).calculateTotal();
			}
		for (int i=0;i<invoices.size();i++){
		l1.add(invoices.get(i));}
		l1.print();}
		
		/**
		 * Parses all Invoices from the SQL database into an InvoiceArray
		 */
public static  ArrayList<Invoice> getInvoices(ArrayList<Invoice> al){
	 
	 // JDBC driver name and database URL
   @SuppressWarnings("unused")
final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jthorne";

   //  Database credentials
   final String USER = "jthorne";
   final String PASS = "mSnhz4";
	Statement stmt=null;
	Connection conn=null;
	try{
		
		
	    //STEP 2: Register JDBC driver 
	    Class.forName("com.mysql.jdbc.Driver");

	    //STEP 3: Open a connection
	    conn = DriverManager.getConnection(DB_URL,USER,PASS);

	    //STEP 4: Execute a query
	    stmt = conn.createStatement();
	    
	//retrieves customer type from customer code
	    String sql="SELECT * FROM Invoice";
	    ResultSet rs = stmt.executeQuery(sql);

	    //sorts ids
	    while(rs.next()){
		       while (rs.next()){
		      int temp= rs.getInt("id");
		      ids.add(temp);}
		       
	    }
	    //sorts InvoiceCodes
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("invoiceCode");
		      invoiceCodes.add(temp);}
	    }
	  //sorts dates
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("date");
		      dates.add(temp);}
	    }
	  //sorts customers
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("customer");
		      customers.add(temp);}
	    }
	    //sorts salesperson
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("salesperson");
		      salespersons.add(temp);}
	    }
	    //sorts product
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("product");
		      products.add(temp);}
	    }
	    //sorts quantity
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      int temp= rs.getInt("quantity");
		      quantities.add(temp);}
	    }
	    //sorts product types
	    sql="SELECT * FROM Invoice";
	     rs = stmt.executeQuery(sql);
	     
	    while(rs.next()){
		       while (rs.next()){
		      String temp= rs.getString("pType");
		      pTypes.add(temp);}
	    }   
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
	//Constructs and adds Invoices into an array of Invoices to be added to Invoice List
	 for (int i=0; i<ids.size();i++){
		 Invoice j =new Invoice(ids.get(i),invoiceCodes.get(i), dates.get(i), customers.get(i), salespersons.get(i), quantities.get(i), products.get(i), pTypes.get(i));
	 al.add(j);}
	return al;
}
}
