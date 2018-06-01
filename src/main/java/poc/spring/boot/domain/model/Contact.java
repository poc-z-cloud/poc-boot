package poc.spring.boot.domain.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Contact extends AbstractDomainClass{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Date birthday;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getName(){
		return this.getFirstName() + " " + this.getLastName();
	}
}
