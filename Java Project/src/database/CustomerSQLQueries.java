package database;

/**
 * CustomerSQLQueries
 */
public class CustomerSQLQueries 
{	
	/**
	 * String represents SQL statement To CREATE_CUSTOMER.
	 */
	public static String CREATE_CUSTOMER = "INSERT INTO customer" + "(CUST_NAME, PASSWORD) " + "VALUES" + "(?,?)";
	
	/**
	 * String represents SQL statement To REMOVE_CUSTOMER.
	 */
	public static String REMOVE_CUSTOMER = "DELETE FROM customer"+" WHERE "+"ID = ?  AND CUST_NAME = ? ";
	
	/**
	 * String represents SQL statement To REMOVE_CUSTOMER_COUP.
	 */
	public static String REMOVE_CUSTOMER_COUP = "DELETE FROM customer_coupon WHERE CUST_ID =?;";
	
	/**
	 * String represents SQL statement To UPDATE_CUSTOMER.
	 */
	public static String UPDATE_CUSTOMER = "UPDATE customer" + " SET "+ "ID=?,PASSWORD=?"+ " WHERE "+ "CUST_NAME =?";
	
	/**
	 * String represents SQL statement To GET_CUSTOMER.
	 */
	public static String GET_CUSTOMER = "SELECT * FROM customer WHERE ID=?";
	
	/**
	 * String represents SQL statement To SELECT_ALL_CUSTOMERS.
	 */
	public static String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
	
	/**
	 * String represents SQL statement To SELECT_ALL_Coupons.
	 */
	public static String SELECT_ALL_Coupons = "SELECT * FROM coupon WHERE ID IN ( SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = ( SELECT ID FROM customer WHERE CUST_NAME = ? ))";
	
	/**
	 * String represents SQL statement To SELECT_ALL_Coupons_BY_TYPE.
	 */
	public static String SELECT_ALL_Coupons_BY_TYPE = "SELECT * FROM coupon WHERE ID IN ( SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = ( SELECT ID FROM customer WHERE CUST_NAME = ? )) AND `TYPE` = ? ";
	
	/**
	 * String represents SQL statement To SELECT_ALL_Coupons_BY_PRICE.
	 */
	public static String SELECT_ALL_Coupons_BY_PRICE = "SELECT * FROM coupon WHERE ID IN ( SELECT COUPON_ID FROM customer_coupon WHERE CUST_ID = ( SELECT ID FROM customer WHERE CUST_NAME = ? )) AND `PRICE` = ? ";
	
	/**
	 * String represents SQL statement To LOGIN.
	 */
	public static String LOGIN = " SELECT * FROM customer WHERE CUST_NAME = ? AND PASSWORD = ? " ;
	
}