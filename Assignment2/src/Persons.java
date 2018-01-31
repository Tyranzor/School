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

public class Persons extends Person {
	// persons class for generating persons in database, not to be confused with person superclass
	private String personcode;
	private String emailaddress;
	public Persons(String firstname, String lastname, String address,String personcode,String emailaddress) {
		super(firstname, lastname, address);
		this.personcode=personcode;
		this.emailaddress=emailaddress;
	}

	public String getPersoncode() {
		return personcode;
	}

	public void setPersoncode(String personcode) {
		this.personcode = personcode;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
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
	        e = dom.createElement("FirstName");
	        e.appendChild(dom.createTextNode(this.getFirstname()));
	        rootEle.appendChild(e);

	        e = dom.createElement("LastName");
	        e.appendChild(dom.createTextNode(this.getLastname()));
	        rootEle.appendChild(e);

	        e = dom.createElement("Address");
	        e.appendChild(dom.createTextNode(this.getAddress()));
	        rootEle.appendChild(e);

	        e = dom.createElement("Personcode");
	        e.appendChild(dom.createTextNode(this.personcode));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("EmailAddress");
	        e.appendChild(dom.createTextNode(this.emailaddress));
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
	            System.out.println(te.getMessage()+"hii");
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage()+"hi");
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
}
