package dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import couponclientfacade.SystemException;
import dao.CustomerDAO;
import database.ConnectionPool;
import database.CustomerSQLQueries;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;

/**
 *  CustomerDBDAO implements CustomerDAO to apply methods using Sql Queries.
 */
public class CustomerDBDAO implements CustomerDAO {
	
	private ConnectionPool connectionPool;
	
	public CustomerDBDAO()
	{
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void CreateCustomer(Customer Cust) throws SystemException
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.CREATE_CUSTOMER);
			preparedStatement.setString(1, Cust.getCustName());
			preparedStatement.setString(2, Cust.getPassword());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Customer Has Been Add ");
			
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer or CustomerId already exists");
		}
		
		finally 
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
	}

	@Override
	public void RemoveCustomer(Customer Cust) throws SystemException 
	{
        Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.REMOVE_CUSTOMER);
			preparedStatement.setLong(1, Cust.getID());
			preparedStatement.setString(2, Cust.getCustName());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Customer Has Been Removed ");
			
			if(rowsAffected != 0) 
			{
			preparedStatement = conn.prepareStatement(CustomerSQLQueries.REMOVE_CUSTOMER_COUP);
			preparedStatement.setLong(1, Cust.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Customer Join Table Has Been Removed ");
			}else {
				System.out.println(rowsAffected + " Error:Customer Has Not Been Removed Wrong ID OR NAME ");
		     }
		} 
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer or CustomerId doesn't exists");
		}
		
		finally
		{
			if (conn != null)   connectionPool.returnConnection(conn);
		}
		
	}

	@Override
	public void UpdateCustomer(Customer Cust) throws SystemException 
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.UPDATE_CUSTOMER);
			preparedStatement.setLong(1, Cust.getID());
			preparedStatement.setString(3, Cust.getCustName());
			preparedStatement.setString(2, Cust.getPassword());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Customer Has Been Updated ");
			
			if(rowsAffected == 0) 
			{
			System.out.println("Cannot Change CUSTOMER NAME/CUSTOMER ID does exist/Name doesn't exist");
			}
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Cannot Change CUSTOMER NAME/CUSTOMER ID does exist/Name doesn't exist");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
	}

	@Override
	public Customer getCustomer(long id) throws SystemException 
	{
		Connection conn = null;
		Customer result = new Customer();
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet Result=null;
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.GET_CUSTOMER);
			preparedStatement.setLong(1,id);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Customer ID Doesn't Exist ");
			while (Result.next()) 
			{
				String CUST_NAME = Result.getString("CUST_NAME");
				String PASSWORD = Result.getString("PASSWORD");
				System.out.println(id + " , " + CUST_NAME + " , " + PASSWORD);
				
				result.setID(id);
				result.setCustName(CUST_NAME);
				result.setPassword(PASSWORD);
             }
		}
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer id doesn't exist");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public Collection<Customer> getAllCustomer() throws SystemException 
	{
		Connection conn = null;
		ArrayList<Customer> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement Statement = conn.createStatement();
			ResultSet Result=null;

			Result = Statement.executeQuery(CustomerSQLQueries.SELECT_ALL_CUSTOMERS);
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Customer Doesn't Exist ");
			while (Result.next()) {
				int ID = Result.getInt("ID");
				String CUST = Result.getString("CUST_NAME");
				String PASSWORD = Result.getString("PASSWORD");
				System.out.println(ID + " , " + CUST + " , " + PASSWORD);
				
				Customer c = new Customer();
				c.setID(ID);
				c.setCustName(CUST);
				c.setPassword(PASSWORD);
				result.add(c);
			}			
					
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Parameters are invalid, please try again");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}
	
	@Override
	public Collection<Coupon> getCoupons(String CustName) throws SystemException {
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			
			conn = connectionPool.getConnection();
			ResultSet Result=null;
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.SELECT_ALL_Coupons);
			preparedStatement.setString(1,CustName);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Coupons Doesn't Exist ");
			while (Result.next()) {
				int ID = Result.getInt("ID");
				String TITLE = Result.getString("TITLE");
				Date START_DATE = Result.getDate("START_DATE");
				Date END_DATE = Result.getDate("END_DATE");
				int AMOUNT = Result.getInt("AMOUNT");
				String TYPE = Result.getString("TYPE");
				String MESSAGE = Result.getString("MESSAGE");
				Double PRICE = Result.getDouble("PRICE");
				String IMAGE = Result.getString("IMAGE");
				System.out.println(ID + " , " + TITLE + " , " + START_DATE+ " , " +END_DATE+ " , " +AMOUNT+ " , " +TYPE+ " , " +MESSAGE+ " , " +PRICE+ " , " +IMAGE);
				
				Coupon c = new Coupon();
				c.setID(ID);
				c.setTitle(TITLE);
				c.setStart_Date(START_DATE);
				c.setEnd_Date(END_DATE);
				c.setAmount(AMOUNT);
				c.setType(CouponType.valueOf(TYPE));
				c.setMessage(MESSAGE);
				c.setPrice(PRICE);
				c.setImage(IMAGE);
				result.add(c);
			}			
					
		}
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer id doesn't exist");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public Collection<Coupon> getCouponsByType(String CustName,CouponType Coupontype) throws SystemException {
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			
			conn = connectionPool.getConnection();
			ResultSet Result=null;
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.SELECT_ALL_Coupons_BY_TYPE);
			preparedStatement.setString(1,CustName);
			preparedStatement.setString(2,String.valueOf(Coupontype));
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : coupons with type Doesn't Exist ");
			while (Result.next()) {
				int ID = Result.getInt("ID");
				String TITLE = Result.getString("TITLE");
				Date START_DATE = Result.getDate("START_DATE");
				Date END_DATE = Result.getDate("END_DATE");
				int AMOUNT = Result.getInt("AMOUNT");
				String TYPE = Result.getString("TYPE");
				String MESSAGE = Result.getString("MESSAGE");
				Double PRICE = Result.getDouble("PRICE");
				String IMAGE = Result.getString("IMAGE");
				System.out.println(ID + " , " + TITLE + " , " + START_DATE+ " , " +END_DATE+ " , " +AMOUNT+ " , " +TYPE+ " , " +MESSAGE+ " , " +PRICE+ " , " +IMAGE);
				
				Coupon c = new Coupon();
				c.setID(ID);
				c.setTitle(TITLE);
				c.setStart_Date(START_DATE);
				c.setEnd_Date(END_DATE);
				c.setAmount(AMOUNT);
				c.setType(CouponType.valueOf(TYPE));
				c.setMessage(MESSAGE);
				c.setPrice(PRICE);
				c.setImage(IMAGE);
				result.add(c);
			}			
					
		}
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer id doesn't exist Or There's no Coupon");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public Collection<Coupon> getCouponsByPrice(String CustName,double Price) throws SystemException {
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			
			conn = connectionPool.getConnection();
			ResultSet Result=null;
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.SELECT_ALL_Coupons_BY_PRICE);
			preparedStatement.setString(1,CustName);
			preparedStatement.setDouble(2,Price);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Coupons Doesn't Exist ByPrice");
			while (Result.next()) {
				int ID = Result.getInt("ID");
				String TITLE = Result.getString("TITLE");
				Date START_DATE = Result.getDate("START_DATE");
				Date END_DATE = Result.getDate("END_DATE");
				int AMOUNT = Result.getInt("AMOUNT");
				String TYPE = Result.getString("TYPE");
				String MESSAGE = Result.getString("MESSAGE");
				Double PRICE = Result.getDouble("PRICE");
				String IMAGE = Result.getString("IMAGE");
				System.out.println(ID + " , " + TITLE + " , " + START_DATE+ " , " +END_DATE+ " , " +AMOUNT+ " , " +TYPE+ " , " +MESSAGE+ " , " +PRICE+ " , " +IMAGE);
				
				Coupon c = new Coupon();
				c.setID(ID);
				c.setTitle(TITLE);
				c.setStart_Date(START_DATE);
				c.setEnd_Date(END_DATE);
				c.setAmount(AMOUNT);
				c.setType(CouponType.valueOf(TYPE));
				c.setMessage(MESSAGE);
				c.setPrice(PRICE);
				c.setImage(IMAGE);
				result.add(c);
			}			
					
		}
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Customer id doesn't exist Or There's no Coupon");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}
	
	@Override
	public boolean Login(String CustName, String Password) throws SystemException {
		
		Connection conn = null;
		boolean login = false;
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet Result;
			PreparedStatement preparedStatement = conn.prepareStatement(CustomerSQLQueries.LOGIN);
			preparedStatement.setString(1, CustName);
			preparedStatement.setString(2, Password);

			Result = preparedStatement.executeQuery();
			if(Result.next()) {
				
				login=true;
			    System.out.println("ERROR : Login succeed As Customer");	
			
			} else {
				
				login=false;
			    System.out.println("ERROR : Login Failed As Customer");	
			  
			}
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("ERROR : Login Failed As Customer");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
		return login;
	}	

}