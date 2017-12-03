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
import dao.CouponDAO;
import database.ConnectionPool;
import database.CouponSQLQueries;
import javabeans.Coupon;
import javabeans.CouponType;

/**
 *  CouponDBDAO implements CouponDAO to apply methods using Sql Queries.
 */
public class CouponDBDAO implements CouponDAO {
	
private ConnectionPool connectionPool;
	
	public CouponDBDAO()
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
	public void CreateCoupon(Coupon Coup, String CompName) throws SystemException 
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.CREATE_COUPON);
			preparedStatement.setString(1, Coup.getTitle());
			preparedStatement.setDate(2, Coup.getStart_Date());
			preparedStatement.setDate(3, Coup.getEnd_Date());
			preparedStatement.setInt(4, Coup.getAmount());
			preparedStatement.setString(5, Coup.getType().toString());
			preparedStatement.setString(6, Coup.getMessage());
			preparedStatement.setDouble(7, Coup.getPrice());
			preparedStatement.setString(8, Coup.getImage());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Has Been Created ");
			if(rowsAffected != 0) 
			{
			preparedStatement = conn.prepareStatement(CouponSQLQueries.INSERT_IDS);
			preparedStatement.setString(1,CompName);
			preparedStatement.setString(2,Coup.getTitle());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company && Coupon ID's Has Been Add ");
			}
			else {
				System.out.println(rowsAffected + " Error:Company && Coupon ID's Has Not Been Add ");
			     }
				
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Coupon already exists");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
		
	}

	@Override
	public void RemoveCoupon(Coupon Coup) throws SystemException 
	{
        Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON);
			preparedStatement.setLong(1, Coup.getID());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Has Been Removed ");
			if(rowsAffected != 0) 
			{
			preparedStatement = conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_COMP);
			preparedStatement.setLong(1, Coup.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Join Table Comp Has Been Removed ");
			preparedStatement = conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON_CUST);
			preparedStatement.setLong(1, Coup.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Join Table Cust Has Been Removed ");
			}
			else {
				System.out.println(rowsAffected + " Error:Coupon Has Not Been Removed ");
			     }
		} 
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Coupon doesn't exists");
		}
		
		finally
		{
			if (conn != null)  connectionPool.returnConnection(conn);
		}
		
	}

	@Override
	public void UpdateCoupon(Coupon Coup) throws SystemException 
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.UPDATE_COUPON);
			preparedStatement.setLong(1, Coup.getID());
			preparedStatement.setString(9, Coup.getTitle());
			preparedStatement.setDate(2, Coup.getStart_Date());
			preparedStatement.setDate(3, Coup.getEnd_Date());
			preparedStatement.setInt(4, Coup.getAmount());
			preparedStatement.setString(5, Coup.getType().toString());
			preparedStatement.setString(6, Coup.getMessage());
			preparedStatement.setDouble(7, Coup.getPrice());
			preparedStatement.setString(8, Coup.getImage());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Has Been Updated ");
			if(rowsAffected == 0) 
			{
				System.out.println(rowsAffected + " Coupon Has Been not Updated ");
			}
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Cannot change Coupon doesn't exist ");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
	}

	@Override
	public Coupon getCoupon(long id) throws SystemException 
	{
		Connection conn = null;
		Coupon result = new Coupon();
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet Result;
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.GET_COUPON);
			preparedStatement.setLong(1,id);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Coupon Doesn't Exist ");
			while (Result.next()) 
			{
				String TITLE = Result.getString("TITLE");
				Date START_DATE = Result.getDate("START_DATE");
				Date END_DATE = Result.getDate("END_DATE");
				int AMOUNT = Result.getInt("AMOUNT");
				String TYPE = Result.getString("TYPE");
				String MESSAGE = Result.getString("MESSAGE");
				Double PRICE = Result.getDouble("PRICE");
				String IMAGE = Result.getString("IMAGE");
				System.out.println(id + " , " + TITLE + " , " + START_DATE+ " , " +END_DATE+ " , " +AMOUNT+ " , " +TYPE+ " , " +MESSAGE+ " , " +PRICE+ " , " +IMAGE);
				
				result.setID(id);
				result.setTitle(TITLE);
				result.setStart_Date(START_DATE);
				result.setEnd_Date(END_DATE);
				result.setAmount(AMOUNT);
				result.setType(CouponType.valueOf(TYPE));
				result.setMessage(MESSAGE);
				result.setPrice(PRICE);
				result.setImage(IMAGE);
             }
		}
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Coupon id doesn't exist");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public Collection<Coupon> getAllCoupon() throws SystemException 
	{
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement Statement = conn.createStatement();
			ResultSet Result;

			Result = Statement.executeQuery(CouponSQLQueries.SELECT_ALL_COUPON);
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
			throw new SystemException("Parameters are invalid, please try again");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
		
	}

	@Override
	public Collection<Coupon> getCouponByType(CouponType CoupType, String CompName) throws SystemException 
	{
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			
			conn = connectionPool.getConnection();
			ResultSet Result;
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.SELECT_ALL_Coupons_BY_TYPE);
			preparedStatement.setString(1,CompName);
			preparedStatement.setString(2,String.valueOf(CoupType));
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
			throw new SystemException("Parameters are invalid, please try again");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public void PurchaseCoupon(Coupon Coup, String CustName) throws SystemException {
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.PURCHASE_COUPON);
			preparedStatement.setString(1,CustName);
			preparedStatement.setString(2,Coup.getTitle());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Customer && Coupon ID's Has Been Add ");
			if(rowsAffected == 0) 
			{System.out.println(rowsAffected + " Customer && Coupon ID's Has not Been Add ");}

		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("PurchaseCoupon are invalid, please try again");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
	
	}

	@Override
	public void CouponExpiration() throws SystemException {
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.Coupon_Expiration_Company_Customer);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Company Customer Expired ");
			
			preparedStatement = conn.prepareStatement(CouponSQLQueries.Coupon_Expiration);
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Coupon Expired ");

		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("CouponExpiration Not Working");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		

		
	}

}