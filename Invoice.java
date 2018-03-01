package com.ceg.ext;
import java.sql.*;

/**
 * 
 * @author Nathan Pittman
 * with assistance from Jeremy Thorne
 * @version 1
 * 
 *
 */

public class Invoice implements Comparable<Invoice>{
	private int id;
	private String InvoiceCode;
	private String Date;
	private String Customer;
	private String SalesPerson;
	private String Product;
	private int Quantity;
	private String pType;
	private double Total;


	

	
	
	 // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/jthorne";

    //  Database credentials
    static final String USER = "jthorne";
    static final String PASS = "mSnhz4";


	/**
	 * @param id Auto-Incremented in SQL
	 * @param invoiceCode - multiple invoice entries per code
	 * @param date- date of transaction
	 * @param customer- customer code
	 * @param salesPerson - salesperson code
	 * @param product - product code, only 1 product code per invoice entry in SQL
	 * @param quantity- quantity of product sold
	 * @param pType- type of product R,M,S,P
	 */
	public Invoice(int id, String invoiceCode, String date, String customer, String salesPerson, int quantity,String product,
			 String pType) {
		this.id = id;
		InvoiceCode = invoiceCode;
		Date = date;
		Customer = customer;
		SalesPerson = salesPerson;
		Product = product;
		Quantity = quantity;
		this.pType = pType;
	}
	
	
	/**
	 * @param i- invoice to be passed in
	 * @return returns the total of the invoice after tax and fees have been applied
	 */
	public double calculateTotal(){
		String code=this.getCustomerType();
		double cost= this.getCost();
	
		Double total=0.00;
		if (code.equalsIgnoreCase("s")){
			total=cost*this.Quantity;
			this.Total=total;
			return total;
		}
		else {
			if (this.pType.equalsIgnoreCase("M")){
				double taxes= (cost*this.Quantity)*.06;
				total=taxes+(cost*this.Quantity);
				this.Total=total;
				return total;
			}
			else if(this.pType.equalsIgnoreCase("s")){
					double taxes= (cost*this.Quantity)*.06;
					total=taxes+(cost*this.Quantity);
					this.Total=total;
					return total;
			}
			else if (this.pType.equalsIgnoreCase("p")){
				double taxes= (cost*this.Quantity)*.04;
				total=taxes+(cost*this.Quantity);
				this.Total=total;
				return total;
			}
			else if(this.pType.equalsIgnoreCase("r")){
				double taxes= (cost*this.Quantity)*.04;
				total=taxes+(cost*this.Quantity);
				this.Total=total;
				return total;
			}
		}
		
	this.Total=0;
	System.out.println("Error in calculation of total");
	return 0.00;
		
		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the invoiceCode
	 */
	public String getInvoiceCode() {
		return InvoiceCode;
	}


	/**
	 * @param invoiceCode the invoiceCode to set
	 */
	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		Date = date;
	}


	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return Customer;
	}


	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		Customer = customer;
	}


	/**
	 * @return the salesPerson
	 */
	public String getSalesPerson() {
		return SalesPerson;
	}


	/**
	 * @param salesPerson the salesPerson to set
	 */
	public void setSalesPerson(String salesPerson) {
		SalesPerson = salesPerson;
	}


	/**
	 * @return the product
	 */
	public String getProduct() {
		return Product;
	}


	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		Product = product;
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return Quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}


	/**
	 * @return the pType
	 */
	public String getpType() {
		return pType;
	}


	/**
	 * @param pType the pType to set
	 */
	public void setpType(String pType) {
		this.pType = pType;
	}


	/**
	 * @return the total
	 */
	public double getTotal() {
		return Total;
	}
public void setTotal(Double total){
	this.Total=total;
}
/**
 * @param CustomerCode Customer code to retrieve the customer type for
 * @return returns the customer type G or S
 */
public String getCustomerType(){
Statement stmt=null;
Connection conn=null;
String cType=null;
try{
	
	
    //STEP 2: Register JDBC driver 
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
//    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

//    System.out.println("Connected to database...");

    //STEP 4: Execute a query
//    System.out.println("Creating statement...");
    stmt = conn.createStatement();
    
//retrieves customer type from customer code
    String sql="SELECT Customer.type FROM Customer WHERE Customer.customerCode= '"+this.Customer+"'";
//    System.out.println(sql);
    ResultSet rs = stmt.executeQuery(sql);

    
    while(rs.next()){
	       //Retrieve by column name
	       cType  = rs.getString("type");
//	      System.out.println(""+cType);
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
// System.out.println("Goodbye!");
return cType;
}

/**
 * @param ProductCode the product code of the product to retrieve the cost of
 * @return returns the cost of the product as a double
 */
public double getCost(){
Statement stmt=null;
Connection conn=null;
double cost=0;
try{
	
	
    //STEP 2: Register JDBC driver 
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
   // System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

   // System.out.println("Connected to database...");

    //STEP 4: Execute a query
   // System.out.println("Creating statement...");
    stmt = conn.createStatement();
    
//retrieves customer type from customer code
    String sql="SELECT Product.cost FROM Product WHERE Product.pCode= '"+this.Product+"'";
   // System.out.println(sql);
    ResultSet rs = stmt.executeQuery(sql);

    
    while(rs.next()){
	       //Retrieve by column name
	       cost  = rs.getDouble("cost");
	    //  System.out.println(""+cost);
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
 //System.out.println("Goodbye!");
return cost;
}
public  void print (){
	System.out.println("InvoiceCode: "+this.getInvoiceCode()+" ID: "+this.getId()+" Date: "+this.getDate()+" Customer: "+this.getCustomer()
	+" SalesPerson: "+this.getSalesPerson()+" Product: "+this.getProduct()+" ProductType: "+this.getpType()+" Quantity "+this.getQuantity()
	+" Total: "+this.getTotal());
}

@Override
public int compareTo(Invoice i1) {
	if (this.getTotal()==i1.getTotal()){
		return 0;
	}
	else if (this.getTotal()>i1.getTotal()){
		return 1;
	}
	else
		return -1;


}
	
}

