import java.util.*;

import java.io.File;

public class DataConverter {
	private static List <SeasonPass>seasonpasses=new ArrayList<SeasonPass>();
	 private static List <Refreshment>refreshments=new ArrayList<Refreshment>();
	private static List <ParkingPass>parkingpasses=new ArrayList<ParkingPass>();
	 private static List <MovieTicket>movietickets=new ArrayList<MovieTicket>();
	 
	public static void main(String args[]) {
		
		
		List<Customer> customer = customerConverter();
		List<Persons> persons = personsConverter();
		productConverter();
		
		for (Persons p:persons){
			p.saveToXML("data/Persons.xml");
			System.out.println(p.getAddress()+p.getEmailaddress()+p.getFirstname()+p.getLastname()+p.getPersoncode());
		}
		for (Customer c:customer){
			c.saveToXML("data/Customer.xml");
			System.out.println(c.getAddress()+c.getCode()+c.getFirstname()+c.getId()+c.getLastname()+c.getType());
		}
		for (SeasonPass sp:seasonpasses){
			sp.saveToXML("data/SeasonPass.xml");
			System.out.println(sp.getCode()+sp.getEnddate()+sp.getName()+sp.getPrice()+sp.getStartdate());
		}
		for (Refreshment r:refreshments){
			r.saveToXML("data/Refreshments.xml");
			System.out.println(r.getCode()+r.getName()+r.getPrice());
		}
		for (ParkingPass p:parkingpasses){
			p.saveToXML("data/ParkingPasses.xml");
			System.out.println(p.getCode()+p.getName()+p.getPrice());
		}
		for (MovieTicket mt:movietickets){
			mt.saveToXML("data/MovieTickets.xml");
			System.out.println(mt.getAddress()+mt.getCode()+mt.getDatetime()+mt.getName()+mt.getPrice()+mt.getScrnmbr());
		}
	}
		public static List<Customer> customerConverter() {
			List<Customer> result = new ArrayList<Customer>();
			try {
				File f = new File("data/Customer.dat");
				Scanner s = new Scanner(f);
				while(s.hasNext()) {
					String line = s.nextLine();
					if(!line.trim().isEmpty()) {
						Customer e = null;
						String tokens[] = line.split(";");
						String code=tokens [0];
						String id = tokens[1];
						String nameTokens[] = tokens[2].split(",");
						String lastName = nameTokens[0];
						String firstName = nameTokens[1];
						String address = tokens[3];
						String emailaddress=tokens[4];
						e=new Customer(firstName,lastName,address,code,emailaddress,id);
						e.setCode(tokens[0]);
						e.setId(tokens[1]);
						result.add(e);
					
					}
					
				}s.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
	
		


public static List<Persons> personsConverter() {
	List<Persons> result = new ArrayList<Persons>();
	try {
		File f = new File("data/persons.dat");
		Scanner s = new Scanner(f);
		while(s.hasNext()) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Persons e = null;
				String tokens[] = line.split(";");
				String personcode=tokens [0];
				String nameTokens[] = tokens[1].split(",");
				String lastName = nameTokens[0];
				String firstName = nameTokens[1];
				String address = tokens[2];
				String emailaddress=tokens[3];
				System.out.println(emailaddress);
				e=new Persons(firstName,lastName,address,personcode,emailaddress);
				e.setPersoncode(personcode);
				result.add(e);
			
			}
			
		}s.close();
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	return result;
}



public static void productConverter() {
	List<Product> result = new ArrayList<Product>();
	try {
		File f = new File("data/Products.dat");
		Scanner s = new Scanner(f);
		while(s.hasNext()) {
			String line = s.nextLine();
			if(!line.trim().isEmpty()) {
				Product e = null;
				String tokens[] = line.split(";");
				String code=tokens[0];
				String id=tokens[1];
				if(tokens[1].equalsIgnoreCase("S")) {
					e = new SeasonPass(tokens[2],Double.parseDouble(tokens[5]), code, tokens[3], tokens[4]);
					seasonpasses.add((SeasonPass) e);
				} else if(tokens[1].equalsIgnoreCase("R")) {
					e = new Refreshment(tokens[2], Double.parseDouble(tokens[3]), code);
					refreshments.add((Refreshment) e);
				} else if(tokens[1].equalsIgnoreCase("P")) {
					e = new ParkingPass("Parking Pass",Double.parseDouble(tokens[2]), code);
					parkingpasses.add((ParkingPass) e);
				} else if(tokens[1].equalsIgnoreCase("M")) {
					e = new MovieTicket(tokens[3], Double.parseDouble(tokens[6]), code, tokens[2], tokens[4],Integer.parseInt(tokens[5]) );
					movietickets.add((MovieTicket) e);
				}
			}
			}
			
	} catch(Exception e) {
		e.printStackTrace();
		
	}
	
	


}
}

