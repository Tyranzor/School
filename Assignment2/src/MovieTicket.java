import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MovieTicket extends Product{
	
private String datetime;

private String address;
private int scrnmbr;
	
	
	public MovieTicket(String name, double price, String code, String datetime, String address, int scrnmbr) {
		super(name, price, code);
		this.datetime=datetime; this.address=address; this.scrnmbr=scrnmbr;
		// TODO Auto-generated constructor stub
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDate (String datetime) {
		this.datetime = datetime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getScrnmbr() {
		return scrnmbr;
	}
	public void setScrnmbr(int scrnmbr) {
		this.scrnmbr = scrnmbr;
	}
	public void saveToXML(String xml) {
	    Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("roles");

	        // create data elements and place them under root
	        e = dom.createElement("Name");
	        e.appendChild(dom.createTextNode(this.getName()));
	        rootEle.appendChild(e);

	        e = dom.createElement("Price");
	        e.appendChild(dom.createTextNode(Double.toString(this.getPrice())));
	        rootEle.appendChild(e);

	        e = dom.createElement("Code");
	        e.appendChild(dom.createTextNode(this.getCode()));
	        rootEle.appendChild(e);

	        e = dom.createElement("DateTime");
	        e.appendChild(dom.createTextNode(this.datetime));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("Address");
	        e.appendChild(dom.createTextNode(this.address));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("ScreenNumber");
	        e.appendChild(dom.createTextNode(Integer.toString(this.scrnmbr)));
	        rootEle.appendChild(e);
	        
	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "6");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(xml)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage()+"I hate this");
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage()+"I hate this");
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
}
