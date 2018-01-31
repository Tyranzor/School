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

public class Customer extends Person {
	//generates the specific type of person customer
	private String type;
	private String code;
	private Persons contact;
	private String id;
	
	
	public Customer(String firstname, String lastname, String address,String type,String code,String id) {
		super(firstname, lastname, address);
		this.type=type;
		this.code=code;
		this.id=id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Persons getContact() {
		return contact;
	}
	public void setContact(Persons contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	        e = dom.createElement("Code");
	        e.appendChild(dom.createTextNode(this.code));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("ID");
	        e.appendChild(dom.createTextNode(this.id));
	        rootEle.appendChild(e);
	        
	        e = dom.createElement("Type");
	        e.appendChild(dom.createTextNode(this.type));
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
	            System.out.println(te.getMessage()+"helloo");
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage()+"hellooo");
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
	}
}
