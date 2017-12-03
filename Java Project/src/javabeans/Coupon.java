package javabeans;

import java.sql.Date;

/**Coupon Class*/
public class Coupon {
	
	private long ID;
	private String Title;
	private Date Start_Date;
	private Date End_Date;
	private int Amount;
	private CouponType Type;
	private String Message;
	private double Price;
	private String Image;
	
	/**Default Constructor Coupon*/
	public Coupon() 
	{

	}

	public Coupon(long iD, String title, Date start_Date, Date end_Date, int amount, CouponType type, String message, double price, String image)
	{
		super();
		this.ID = iD;
		this.Title = title;
		this.Start_Date = start_Date;
		this.End_Date = end_Date;
		this.Amount = amount;
		this.Type = type;
		this.Message = message;
		this.Price = price;
		this.Image = image;
	}

	/**Getters & Setters*/
	
	/**
	 * This Getter For Coupon id.
	 * @return the id
	 */
	public long getID() {
		return ID;
	}

	/**
	 * this setter for receiving a Long value and update the id.
	 * @param id
	 */
	public void setID(long iD) {
		this.ID = iD;
	}

	/**
	 * This Getter For Coupon Title.
	 * @return the Title
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * this setter for receiving a String value and update the Title.
	 * @param Title
	 */
	public void setTitle(String title) {
		this.Title = title;
	}

	/**
	 * This Getter For Coupon Start_Date.
	 * @return the Start_Date
	 */
	public Date getStart_Date() {
		return Start_Date;
	}

	/**
	 * this setter for receiving a Date value and update the Start_Date.
	 * @param start_Date
	 */
	public void setStart_Date(Date start_Date) {
		this.Start_Date = start_Date;
	}

	/**
	 * This Getter For Coupon End_Date.
	 * @return the End_Date
	 */
	public Date getEnd_Date() {
		return End_Date;
	}

	/**
	 * this setter for receiving a Date value and update the end_Date.
	 * @param end_Date
	 */
	public void setEnd_Date(Date end_Date) {
		this.End_Date = end_Date;
	}

	/**
	 * This Getter For Coupon Amount.
	 * @return the Amount
	 */
	public int getAmount() {
		return Amount;
	}

	/**
	 * this setter for receiving a int value and update the amount.
	 * @param amount
	 */
	public void setAmount(int amount) {
		this.Amount = amount;
	}

	/**
	 * This Getter For Coupon Type.
	 * @return the Type
	 */
	public CouponType getType() {
		return Type;
	}

	/**
	 * this setter for receiving a CouponType value and update the type.
	 * @param type
	 */
	public void setType(CouponType type) {
		this.Type = type;
	}

	/**
	 * This Getter For Coupon Message.
	 * @return the Message
	 */
	public String getMessage() {
		return Message;
	}

	/**
	 * this setter for receiving a String value and update the message.
	 * @param message
	 */
	public void setMessage(String message) {
		this.Message = message;
	}

	/**
	 * This Getter For Coupon Price.
	 * @return the Price
	 */
	public double getPrice() {
		return Price;
	}

	/**
	 * this setter for receiving a double value and update the price.
	 * @param price
	 */
	public void setPrice(double price) {
		this.Price = price;
	}

	/**
	 * This Getter For Coupon Image.
	 * @return the Image
	 */
	public String getImage() {
		return Image;
	}

	/**
	 * this setter for receiving a String value and update the image.
	 * @param image
	 */
	public void setImage(String image) {
		this.Image = image;
	}

	/**
	 *  this override from toString method for get Coupon details.
	 */
	@Override
	public String toString() {
		return "Coupon [ID=" + ID + ", Title=" + Title + ", Start_Date=" + Start_Date + ", End_Date=" + End_Date
				+ ", Amount=" + Amount + ", Type=" + Type + ", Message=" + Message + ", Price=" + Price + ", Image="
				+ Image + "]";
	}
	
}