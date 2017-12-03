package database;

/**
 * CompanySQLQueries
 */
public class CompanySQLQueries 
{
	/**
	 * String represents SQL statement To CREATE_COMPANY.
	 */
	public static String CREATE_COMPANY = "INSERT INTO company" + "(COMP_NAME, PASSWORD, EMAIL) " + "VALUES" + "(?,?,?)";
	
	/**
	 * String represents SQL statement To REMOVE_COMPANY.
	 */
	public static String REMOVE_COMPANY = "DELETE FROM company WHERE ID = ? AND COMP_NAME = ? ";
	
	/**
	 * String represents SQL statement To REMOVE_COMPANY_COUPON.
	 */
	public static String REMOVE_COMPANY_COUPON = "DELETE FROM coupon WHERE ID IN (SELECT COUPON_ID FROM company_coupon WHERE COMP_ID = ? );";
	
	/**
	 * String represents SQL statement To REMOVE_COMPANY_CUSTCOUP.
	 */
	public static String REMOVE_COMPANY_CUSTCOUP = "DELETE FROM customer_coupon WHERE COUPON_ID IN (SELECT COUPON_ID FROM company_coupon WHERE COMP_ID = ?);";
	
	/**
	 * String represents SQL statement To REMOVE_COMPANY_COMPCOUP.
	 */
	public static String REMOVE_COMPANY_COMPCOUP = "DELETE FROM company_coupon WHERE COMP_ID= ?";
	
	/**
	 * String represents SQL statement To UPDATE_COMPANY.
	 */
	public static String UPDATE_COMPANY = "UPDATE company SET ID=?,PASSWORD=?,EMAIL=?  WHERE COMP_NAME=?;";
	
	/**
	 * String represents SQL statement To GET_COMPANY.
	 */
	public static String GET_COMPANY = "SELECT * FROM company WHERE ID=?";
	
	/**
	 * String represents SQL statement To SELECT_ALL_COMPANIES.
	 */
	public static String SELECT_ALL_COMPANIES = "SELECT * FROM company";
	
	/**
	 * String represents SQL statement To SELECT_ALL_Coupons.
	 */
	public static String SELECT_ALL_Coupons = "SELECT * FROM coupon WHERE ID IN (SELECT COUPON_ID FROM company_coupon WHERE COMP_ID =(SELECT ID FROM company WHERE COMP_NAME = ? ))";
	
	/**
	 * String represents SQL statement To LOGIN.
	 */
	public static String LOGIN = " SELECT * FROM company WHERE COMP_NAME = ? AND PASSWORD = ? " ;
	
}