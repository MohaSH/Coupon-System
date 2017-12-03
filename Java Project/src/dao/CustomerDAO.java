package dao;

import java.util.Collection;

import couponclientfacade.SystemException;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;

public interface CustomerDAO {
	
	/**CreateCustomer
	 *@param Cust
	 *@throws SystemException
	 */
	void CreateCustomer(Customer Cust) throws SystemException;
	
	/**RemoveCustomer
	 *@param Cust
	 *@throws SystemException
	 */
	void RemoveCustomer(Customer Cust) throws SystemException;
	
	/**UpdateCustomer
	 *@param Cust
	 *@throws SystemException
	 */
	void UpdateCustomer(Customer Cust) throws SystemException;
	
	/**getCustomer
	 *@param id
	 *@throws SystemException
	 */
	Customer getCustomer(long id) throws SystemException;
	
	/**getAllCustomer
	 *@throws SystemException
	 */
	Collection<Customer> getAllCustomer() throws SystemException;
	
	/**getCoupons
	 *@param CustName
	 *@throws SystemException
	 */
	Collection<Coupon> getCoupons(String CustName) throws SystemException;
	
	/**getCouponsByType
	 *@param CustName
	 *@param Coupontype
	 *@throws SystemException
	 */
	Collection<Coupon> getCouponsByType(String CustName,CouponType Coupontype) throws SystemException;
	
	/**getCouponsByPrice
	 *@param CustName
	 *@param Price
	 *@throws SystemException
	 */
	Collection<Coupon> getCouponsByPrice(String CustName,double Price) throws SystemException;
	
	/**Login
	 *@param CustName
	 *@param Password
	 *@throws SystemException
	 */
	boolean Login(String CustName ,String Password) throws SystemException;

}