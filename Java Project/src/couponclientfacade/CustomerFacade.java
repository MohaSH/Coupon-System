package couponclientfacade;

import dbdao.CouponDBDAO;
import dbdao.CustomerDBDAO;
import javabeans.ClientType;
import javabeans.Coupon;
import javabeans.CouponType;

/**
 * CustomerFacade class Giving access for Customer for the System
 */
public class CustomerFacade implements CouponClientFacade{
	
	/**Fields */
	private CustomerDBDAO customerDBDAO ;
	private CouponDBDAO couponDBDAO ;
	String CustName;
	
	/**
	 * Constructor for initialize couponDBDAO and customerDBDAO
	 * @param CustName
	 */
	public CustomerFacade(String CustName) {
		this.CustName=CustName;
		customerDBDAO = new CustomerDBDAO();
		couponDBDAO = new CouponDBDAO();
	}

	/**
	 * login Access for Customer
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		this.CustName=name;
		try {
			if (customerDBDAO.Login(name, password))
			{
				this.CustName=name;
				return new CustomerFacade(CustName);
			}else   return null;
			}catch (SystemException e) {
                   e.printStackTrace();
		           }
		return null;
	}
	
	/**
	 * PurchaseCoupon
	 * @param Coup
	 * @throws SystemException
	 */
	public void PurchaseCoupon(Coupon Coup) throws SystemException
	{
		if (Coup.getAmount() != 0) {
			Coup.setAmount(Coup.getAmount() - 1);
			couponDBDAO.UpdateCoupon(Coup);
		} else {
			throw new SystemException("The coupon is out of stock or coupon's date expired");
		}
		couponDBDAO.PurchaseCoupon(Coup, CustName);
	}
	
	/**
	 * getAllPurchasedCoupons
	 * @throws SystemException
	 */
	public void getAllPurchasedCoupons() throws SystemException
	{
		customerDBDAO.getCoupons(CustName);
	}
	
	/**
	 * getAllPurchasedCouponsByType
	 * @param Coupontype
	 * @throws SystemException
	 */
	public void getAllPurchasedCouponsByType(CouponType Coupontype) throws SystemException
	{
		customerDBDAO.getCouponsByType(CustName,Coupontype);
	}
	
	/**
	 * getAllPurchasedCouponsByPrice
	 * @param Price
	 * @throws SystemException
	 */
	public void getAllPurchasedCouponsByPrice(double Price) throws SystemException
	{
		customerDBDAO.getCouponsByPrice(CustName,Price);
	}

}