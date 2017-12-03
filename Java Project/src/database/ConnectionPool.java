package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import couponclientfacade.SystemException;

/**
 * singleTone type class provide connection to the DB and also close all connection.
 */
public class ConnectionPool {
	
	private static ConnectionPool INSTANCE;
	private static int MAX_CONNECTIONS = 5; 
	private Set<Connection> MyConnection;
	private Object key = new Object();
	
	/**
	 * private Constructor for initialize the ConnectionPool
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private ConnectionPool () throws SQLException, ClassNotFoundException 
	{
		MyConnection = new HashSet<>();
		for (int i = 0; i < MAX_CONNECTIONS; i++) 
		{
			Class.forName("com.mysql.fabric.jdbc.FabricMySQLDriver");
			Connection RawConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/data base?autoReconnect=true&useSSL=false","root","");
			MyConnection.add(RawConnection);
		}
		//System.out.println(MyConnection.size()+"ConnectionPool");
	}
	
	/**
	 * getInstance synchronized static method returning the instance of this class
	 * @return INSTANCE
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized static ConnectionPool getInstance() throws ClassNotFoundException, SQLException 
	{
		if (INSTANCE == null)
		{
			INSTANCE = new ConnectionPool();
		}
		return INSTANCE;
	}
	
	/**
	 * getConnection method synchronized by a key object to supply connection to the DB
	 * @return Connection
	 * @throws InterruptedException
	 */
	public Connection getConnection() throws InterruptedException 
	{
		synchronized(key)
		{
			while (MyConnection.size() == 0) {
				key.wait();
				System.out.println(MyConnection.size()+"Connection Full");
			}
			
			Connection result = MyConnection.iterator().next();
			MyConnection.remove(result);
			//System.out.println(MyConnection.size()+"getConnection");
			return result;
		}
	}
	
	/**
	 * ReturnConnection Method to receive a connection and set it back to the connections and notify key object.
	 * @param conn
	 */
	public void returnConnection(Connection conn)
	{
		synchronized(key)
		{
			MyConnection.add(conn);
			key.notifyAll();
			//System.out.println(MyConnection.size()+"returnConnection");
		}
	}
	
	/**
	 * closeConnection method closing all the connections and remove them from the ConnectionPool.
	 * @throws SystemException
	 */
	public void closeConnection() throws SystemException
	{
		for (Connection connection : MyConnection) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new  SystemException("Connection stuck");
			}
		}
		MyConnection.removeAll(MyConnection);
		//System.out.println(MyConnection.size()+"closeConnection  removeAll");
	}
	
}
