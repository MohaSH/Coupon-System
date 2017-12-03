package couponclientfacade;

import java.sql.SQLException;
import database.ConnectionPool;
import javabeans.ClientType;

/**
 * This singleton class its the system engine controller ;
 */
public class CouponSystem implements CouponClientFacade{
	
	/** Fields */
    private static CouponSystem INSTANCE;
    private DailyCouponExpirationTask task ;
    private Thread thread;

    /**
     * private constructor
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	private CouponSystem() throws ClassNotFoundException, SQLException
	{
		task = new DailyCouponExpirationTask();
		ConnectionPool.getInstance();
		thread = new Thread(task);
		thread.start();
	}
	
	/**
	 * getInstance
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized static CouponSystem getInstance() throws ClassNotFoundException, SQLException
	{
		if (INSTANCE == null)
		{
			INSTANCE = new CouponSystem();
		}
		return INSTANCE;
	}
	
	/**
	 * Controller login System
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) 
	{
		CouponClientFacade result = null;
		switch (clientType)
		{
			case ADMINISTRATOR:
				result = new AdminFacade().login(name, password, clientType);
				break;
			case COMPANY:
				result = new CompanyFacade(name).login(name, password, clientType);
			break;
			case CUSTOMER:
				result = new CustomerFacade(name).login(name, password, clientType);
			break;				
				
		}
		return result;
	}

	/**
	 * shutdown
	 * @throws SystemException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void shutdown() throws SystemException, ClassNotFoundException, SQLException {
		
		thread.interrupt();
		task.stopTask();
		ConnectionPool.getInstance().closeConnection();
		
	}
}