package dao;

import java.util.Collection;
import couponclientfacade.SystemException;
import javabeans.Company;
import javabeans.Coupon;

/**
 * interface class CompanyDAO for design implements class methods for access DB.
 */
public interface CompanyDAO  {
	
	/**CreateCompany
	 *@param Comp
	 *@throws SystemException
	 */
	void CreateCompany(Company Comp) throws SystemException;
	
	/**RemoveCompany
	 *@param Comp
	 *@throws SystemException
	 */
	void RemoveCompany(Company Comp) throws SystemException;
	
	/**UpdateCompany
	 *@param Comp
	 *@throws SystemException
	 */
	void UpdateCompany(Company Comp) throws SystemException;
	
	/**getCompany
	 *@param id
	 *@throws SystemException
	 */
	Company getCompany(long id) throws SystemException;
	
	/**getAllCompanies
	 *@throws SystemException
	 */
	Collection<Company> getAllCompanies() throws SystemException;
	
	/**getCoupons 	 
	 *@param compName
	 *@throws SystemException
	 */
	Collection<Coupon> getCoupons(String compName) throws SystemException;
	
	/**Login
	 *@param compName
	 *@param password
	 *@throws SystemException
	 */
	boolean Login(String compName ,String password) throws SystemException;

}