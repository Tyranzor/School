
public class Person {
	// all persons and customers superclass, NOTE: do not confuse person and persons class.
private String firstname;
private String lastname;
private String address;
public Person (String firstname, String lastname, String address){
	this.firstname=firstname; this.lastname=lastname; this.address=address;}

public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
}
