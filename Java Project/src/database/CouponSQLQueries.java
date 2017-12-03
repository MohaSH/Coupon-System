package database;

/**
 * CouponSQLQueries
 */
public class CouponSQLQueries 
{	
	/**
	 * String represents SQL statement To CREATE_COUPON.
	 */
	public static String CREATE_COUPON = "INSERT INTO coupon" + "(TITLE, START_DATE ,END_DATE ,AMOUNT ,TYPE ,MESSAGE ,PRICE ,IMAGE ) " + "VALUES" + "(?,?,?,?,?,?,?,?)";
	
	/**
	 * String represents SQL statement To REMOVE_COUPON.
	 */
	public static String REMOVE_COUPON = "DELETE FROM coupon"+" WHERE "+"ID = ?";
	
	/**
	 * String represents SQL statement To REMOVE_COUPON_COMP.
	 */
	public static String REMOVE_COUPON_COMP = "DELETE FROM company_coupon WHERE COUPON_ID = ?;";
	
	/**
	 * String represents SQL statement To REMOVE_COUPON_CUST.
	 */
	public static String REMOVE_COUPON_CUST = "DELETE FROM customer_coupon WHERE COUPON_ID= ?;";
	
	/**
	 * String represents SQL statement To UPDATE_COUPON.
	 */
	public static String UPDATE_COUPON = "UPDATE coupon" + " SET "+ "ID=? ,START_DATE=? ,END_DATE=? ,AMOUNT=? ,TYPE=? ,MESSAGE=? ,PRICE=? ,IMAGE=? "+ " WHERE "+ "TITLE=?";
	
	/**
	 * String represents SQL statement To GET_COUPON.
	 */
	public static String GET_COUPON = "SELECT * FROM coupon WHERE ID=?";
	
	/**
	 * String represents SQL statement To SELECT_ALL_COUPON.
	 */
	public static String SELECT_ALL_COUPON = "SELECT * FROM coupon";
	
	/**
	 * String represents SQL statement To SELECT_ALL_Coupons_BY_TYPE.
	 */
	public static String SELECT_ALL_Coupons_BY_TYPE = "SELECT * FROM coupon WHERE ID IN (SELECT COUPON_ID FROM company_coupon WHERE COMP_ID =(SELECT ID FROM company WHERE COMP_NAME = ?)) AND `TYPE` = ?";
	
	/**
	 * String represents SQL statement To INSERT_IDS.
	 */
	public static String INSERT_IDS = "INSERT INTO company_coupon( COMP_ID , COUPON_ID ) VALUES (( SELECT ID FROM company WHERE company.COMP_NAME = ? ),( SELECT ID FROM coupon WHERE coupon.TITLE = ? ))";
	
	/**
	 * String represents SQL statement To PURCHASE_COUPON.
	 */
	public static String PURCHASE_COUPON = " INSERT INTO customer_coupon (CUST_ID, COUPON_ID) VALUES ( ( SELECT ID FROM customer WHERE customer.CUST_NAME = ? ),( SELECT ID FROM coupon WHERE coupon.TITLE = ? ) ) ";
	
	/**
	 * String represents SQL statement To Coupon_Expiration.
	 */
	public static String Coupon_Expiration = " DELETE FROM coupon WHERE END_DATE <= NOW() ";
	
	/**
	 * String represents SQL statement To Coupon_Expiration_Company_Customer.
	 */
	public static String Coupon_Expiration_Company_Customer = " DELETE company_coupon,customer_coupon FROM company_coupon,customer_coupon WHERE company_coupon.COUPON_ID IN (SELECT ID FROM coupon WHERE END_DATE <= NOW()) AND customer_coupon.COUPON_ID IN (SELECT ID FROM coupon WHERE END_DATE <= NOW()) ";
	
}