package dao;

import java.util.Collection;

import couponclientfacade.SystemException;
import javabeans.Coupon;
import javabeans.CouponType;

public interface CouponDAO {
	
	/**CreateCoupon
	 * @param Coup
	 * @param CompName
	 * @throws SystemException
	 */
	void CreateCoupon(Coupon Coup , String CompName) throws SystemException;
	
	/**RemoveCoupon
	 * @param Coup 
	 * @throws SystemException
	 */
	void RemoveCoupon(Coupon Coup) throws SystemException;
	
	/**UpdateCoupon
	 * @param Coup 
	 * @throws SystemException
	 */
	void UpdateCoupon(Coupon Coup) throws SystemException;
	
	/**getCoupon
	 * @param id 
	 * @throws SystemException
	 */
	Coupon getCoupon(long id) throws SystemException;
	
	/**getAllCoupon
	 * @throws SystemException
	 */
	Collection<Coupon> getAllCoupon() throws SystemException;
	
	/**getCouponByType
	 * @param CoupType 
	 * @param CompName 
	 * @throws SystemException
	 */
	Collection<Coupon> getCouponByType(CouponType CoupType,String CompName) throws SystemException;
	
	/**PurchaseCoupon 
	 * @param Coup
	 * @param CustName
	 * @throws SystemException
	 */
	void PurchaseCoupon(Coupon Coup , String CustName) throws SystemException;
	
	/**CouponExpiration
	 * @throws SystemException
	 */
	void CouponExpiration() throws SystemException;

}