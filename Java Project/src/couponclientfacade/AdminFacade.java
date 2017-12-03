package couponclientfacade;

import java.sql.SQLException;
import java.util.Collection;
import dbdao.CompanyDBDAO;
import dbdao.CustomerDBDAO;
import javabeans.ClientType;
import javabeans.Company;
import javabeans.Customer;

/**
 * AdminFacade class Giving access for admin for the System
 */
public class AdminFacade implements CouponClientFacade{
	
	/**Fields */
	private CompanyDBDAO companyDBDAO ;
	private CustomerDBDAO customerDBDAO ;

	/**
	 * Constructor for initialize companyDBDAO and customerDBDAO
	 */
	public AdminFacade(){
		
		companyDBDAO = new CompanyDBDAO();
		customerDBDAO = new CustomerDBDAO();
	}
	
	/**
	 * login Access for Admin
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) 
	{
		if (name.equals("admin") && password.equals("1234"))
		{
			System.out.println("Login Success As Admin");
			return new AdminFacade();
		}
		else{
			System.out.println("ERROR : Login Failed As Admin");	
		}
		return null;
	}
	
	/**
	 * CreateCompany
	 * @param Comp
	 * @throws SystemException
	 */
	public void CreateCompany(Company Comp) throws SystemException
	{
		companyDBDAO.CreateCompany(Comp);
	}
	
	/**
	 * RemoveCompany
	 * @param Comp
	 * @throws SystemException
	 */
	public void RemoveCompany(Company Comp) throws SystemException
	{
		companyDBDAO.RemoveCompany(Comp);
	}
	
	/**
	 * UpdateCompany
	 * @param Comp
	 * @throws SystemException
	 */
	public void UpdateCompany(Company Comp) throws SystemException
	{
		companyDBDAO.UpdateCompany(Comp);
	}
	
	/**
	 * getCompany
	 * @param ID
	 * @return
	 * @throws SystemException
	 * @throws SQLException 
	 */
	public Company getCompany(int ID) throws SystemException, SQLException
	{
		return companyDBDAO.getCompany(ID);
	}
	
	/**
	 * getAllCompanies
	 * @return
	 * @throws SystemException
	 */
	public Collection<Company> getAllCompanies() throws SystemException
	{
		return companyDBDAO.getAllCompanies();
	}
	
	/**
	 * CreateCustomer
	 * @param Cust
	 * @throws SystemException
	 */
	public void CreateCustomer(Customer Cust) throws SystemException 
	{
		customerDBDAO.CreateCustomer(Cust);
	}
	
	/**
	 * RemoveCustomer
	 * @param Cust
	 * @throws SystemException
	 */
	public void RemoveCustomer(Customer Cust) throws SystemException 
	{
		customerDBDAO.RemoveCustomer(Cust);
	}
	
	/**
	 * UpdateCustomer
	 * @param Cust
	 * @throws SystemException
	 */
	public void UpdateCustomer(Customer Cust) throws SystemException 
	{
		customerDBDAO.UpdateCustomer(Cust);
	}
	
	/**
	 * getCustomer
	 * @param ID
	 * @return
	 * @throws SystemException
	 */
	public Customer getCustomer(int ID) throws SystemException 
	{
		return customerDBDAO.getCustomer(ID);
	}
	
	/**
	 * getAllCustomer
	 * @return
	 * @throws SystemException
	 */
	public Collection<Customer> getAllCustomer() throws SystemException
	{
		return customerDBDAO.getAllCustomer();
	}

}
