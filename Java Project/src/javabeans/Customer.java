package javabeans;

import java.util.Collection;

/**Customer Class*/
public class Customer {
	
	private long id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;
	
	/**Default Constructor Customer*/
	public Customer()
	{
		
	}

	public Customer(long id, String custName, String password/*, Collection<Coupon> coupons*/) {
		super();
		this.id = id;
		this.custName = custName;
		this.password = password;
		//this.coupons = coupons;
	}

	/**Getters & Setters*/
	
	/**
	 * This Getter For Customer id.
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
	 * This Getter For Customer Name.
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * this setter for receiving a String value and update the Name.
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * This Getter For Customer Password.
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
	 * This Getter For Customer Coupons.
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
	 *  this override from toString method for get Customer details.
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}
	
}