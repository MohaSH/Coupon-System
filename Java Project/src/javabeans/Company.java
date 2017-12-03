package javabeans;

import java.util.Collection;

/**Company Class*/
public class Company {
	
	private long id;
	private String compName;
	private String password;
	private String email;
	private Collection<Coupon> coupons;
	
	/**Default Constructor Company*/
	public Company(){
		
	}

	public Company(long id, String compName, String password, String email/*, Collection<Coupon> coupons*/) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		//this.coupons = coupons;
	}

	/**Getters & Setters*/
	
	/**
	 * This Getter For Company id.
	 * @return the id
	 */
	public long getID() {
		return id;
	}

	/**
	 * this setter for receiving a Long value and update the id.
	 * @param id
	 */
	public void setID(long id) {
		this.id = id;
	}
	
	/**
	 * This Getter For Company Name.
	 * @return the compName
	 */
	public String getCompName() {
		return compName;
	}
	
	/**
	 * this setter for receiving a String value and update the Name.
	 * @param compName
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	/**
	 * This Getter For Company Password.
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * this setter for receiving a String value and update the Password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This Getter For Company Email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * this setter for receiving a String value and update the email.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * This Getter For Company Coupons.
	 * @return the coupons
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	
	/**
	 * this setter for receiving a Collection of Coupon value and update the coupons.
	 * @param coupons
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 *  this override from toString method for get company details.
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}

}