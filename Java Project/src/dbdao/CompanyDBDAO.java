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
import dao.CompanyDAO;
import database.CompanySQLQueries;
import database.ConnectionPool;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.CouponType;

/**
 *  CompanyDBDAO implements CompanyDAO to apply methods using Sql Queries.
 */
public class CompanyDBDAO implements CompanyDAO {
	
	private ConnectionPool connectionPool;
	
	/**
	 * CompanyDBDAO constructor creates new instance of connection pool
	 */
	public CompanyDBDAO()
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
	public void CreateCompany(Company Comp) throws SystemException
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.CREATE_COMPANY);
			preparedStatement.setString(1, Comp.getCompName());
			preparedStatement.setString(2, Comp.getPassword());
			preparedStatement.setString(3, Comp.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Has Been Add ");
		} 
		catch (SQLException | InterruptedException e) 
		{
			
			e.printStackTrace();
			throw new SystemException("Company Name Or Email Already Exists");
		
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
	}

	@Override
	public void RemoveCompany(Company Comp) throws SystemException 
	{
        Connection conn = null;
    	
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY);
			preparedStatement.setLong(1, Comp.getID());
			preparedStatement.setString(2, Comp.getCompName());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Has Been Removed ");
			
			if(rowsAffected != 0) 
			{
			preparedStatement = conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY_COUPON);
			preparedStatement.setLong(1, Comp.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Coupons Has Been Removed ");
			
			preparedStatement = conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY_CUSTCOUP);
			preparedStatement.setLong(1, Comp.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Customers Has Been Removed ");
			
			preparedStatement = conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY_COMPCOUP);
			preparedStatement.setLong(1, Comp.getID());
			rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Join Table Has Been Removed ");
			}
			else {
				System.out.println(rowsAffected + " Error:Company Has Not Been Removed Wrong ID OR NAME ");
			     }
		} 
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Company or companyId doesn't exists");
		}
		
		finally
		{
			if (conn != null)   connectionPool.returnConnection(conn);
		}
	}

	@Override
	public void UpdateCompany(Company Comp) throws SystemException 
	{
		Connection conn = null;
		
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.UPDATE_COMPANY);
			preparedStatement.setLong(1, Comp.getID());
			preparedStatement.setString(4, Comp.getCompName());
			preparedStatement.setString(2, Comp.getPassword());
			preparedStatement.setString(3, Comp.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Company Has Been Updated ");
			if(rowsAffected == 0) 
			{
			System.out.println("Cannot Change Company NAME/Company ID does exist/Name doesn't exist");
			}
			
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Cannot Change Company NAME/Company ID does exist/Name doesn't exist ");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
	}

	@Override
	public Company getCompany(long id) throws SystemException
	{
		Connection conn = null;
		Company result = new Company();
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet Result = null;
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.GET_COMPANY);
			preparedStatement.setLong(1,id);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : Company ID Doesn't Exist ");
			while (Result.next()) 
			{
				String COMP_NAME = Result.getString("COMP_NAME");
				String PASSWORD = Result.getString("PASSWORD");
				String EMAIL = Result.getString("EMAIL");
				System.out.println("ID " +id+ " ,COMP NAME " + COMP_NAME + " ,PASSWORD " + PASSWORD+ " ,EMAIL " + EMAIL);

				result.setID(id);
				result.setCompName(COMP_NAME);
				result.setPassword(PASSWORD);
				result.setEmail(EMAIL);
             }
		}
		
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("Company id doesn't exist");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public Collection<Company> getAllCompanies() throws SystemException 
	{
		Connection conn = null;
		ArrayList<Company> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement Statement = conn.createStatement();
			ResultSet Result = null;

			Result = Statement.executeQuery(CompanySQLQueries.SELECT_ALL_COMPANIES);
			if(!Result.isBeforeFirst()) System.out.println("ERROR : There is No Companies ");
			while (Result.next()) {
				int ID = Result.getInt("ID");
				String COMP = Result.getString("COMP_NAME");
				String PASSWORD = Result.getString("PASSWORD");
				String EMAIL = Result.getString("EMAIL");
				System.out.println("ID " +ID+ " ,COMP NAME " + COMP + " ,PASSWORD " + PASSWORD+ " ,EMAIL " + EMAIL);
				
				Company c = new Company();
				c.setID(ID);
				c.setCompName(COMP);
				c.setPassword(PASSWORD);
				c.setEmail(EMAIL);
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
	public Collection<Coupon> getCoupons(String CompName) throws SystemException
	{
		Connection conn = null;
		ArrayList<Coupon> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			
			conn = connectionPool.getConnection();
			ResultSet Result=null;
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.SELECT_ALL_Coupons);
			preparedStatement.setString(1,CompName);
			Result = preparedStatement.executeQuery();
			if(!Result.isBeforeFirst()) System.out.println("ERROR : There is No Coupons ");
			
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
			throw new SystemException("Company id doesn't exist");
	    }
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		return result;
	}

	@Override
	public boolean Login(String CompName, String Password) throws SystemException 
	{
		Connection conn = null;
		boolean login = false;
		try 
		{
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet Result;
			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.LOGIN);
			preparedStatement.setString(1, CompName);
			preparedStatement.setString(2, Password);

			Result = preparedStatement.executeQuery();
			if(Result.next()) {
				
				login=true;
			    System.out.println("ERROR : Login succeed As Company");	
			
			} else {
				
				login=false;
			    System.out.println("ERROR : Login Failed As Company");	
			  
			}
		} 
		catch (SQLException | InterruptedException e) 
		{
			e.printStackTrace();
			throw new SystemException("ERROR : Login Failed As Company");
		}
		
		finally
		{
			if (conn != null) connectionPool.returnConnection(conn);
		}
		
		return login;
	}
	
}