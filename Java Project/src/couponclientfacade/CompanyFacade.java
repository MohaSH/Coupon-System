package couponclientfacade;

import java.util.Collection;
import dbdao.CompanyDBDAO;
import dbdao.CouponDBDAO;
import javabeans.ClientType;
import javabeans.Coupon;
import javabeans.CouponType;

/**
 * AdminFacade class Giving access for admin for the System
 */
public class CompanyFacade implements CouponClientFacade {
	
	/**Fields */
	private CompanyDBDAO companyDBDAO ;
	private CouponDBDAO couponDBDAO ;
	private String CompName;

	/**
	 * Constructor for initialize companyDBDAO and customerDBDAO
	 * @param CompName
	 */
	public CompanyFacade(String CompName) {
		this.CompName=CompName;
		companyDBDAO = new CompanyDBDAO();
		couponDBDAO = new CouponDBDAO();
	}
	
	/**
	 * login Access for Company
	 */
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {

		this.CompName=name;

		try {
			if (companyDBDAO.Login(name, password))
			{
				this.CompName=name;
				return new CompanyFacade(CompName);
			}
			else
			return null;
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * CreateCoupon
	 * @param Coup
	 * @throws SystemException
	 */
	public void CreateCoupon(Coupon Coup) throws SystemException 
	{
		couponDBDAO.CreateCoupon(Coup,this.CompName);
	}
	
	/**
	 * RemoveCoupon
	 * @param Coup
	 * @throws SystemException
	 */
	public void RemoveCoupon(Coupon Coup) throws SystemException
	{
		couponDBDAO.RemoveCoupon(Coup);
	}
	
	/**
	 * UpdateCoupon
	 * @param Coup
	 * @throws SystemException
	 */
	public void UpdateCoupon(Coupon Coup) throws SystemException
	{
		couponDBDAO.UpdateCoupon(Coup);
	}
	
	/**
	 * getCoupon
	 * @param ID
	 * @return
	 * @throws SystemException
	 */
	public Coupon getCoupon(int ID) throws SystemException
	{
		return couponDBDAO.getCoupon(ID);
	}
	
	/**
	 * getAllCoupon
	 * @return
	 * @throws SystemException
	 */
	public Collection<Coupon> getAllCoupon() throws SystemException

	{
		return companyDBDAO.getCoupons(CompName);
	}
	
	/**
	 * getCouponByType
	 * @param CoupType
	 * @return
	 * @throws SystemException
	 */
	public Collection<Coupon> getCouponByType(CouponType CoupType) throws SystemException
	{
		return couponDBDAO.getCouponByType(CoupType,CompName);
	}
	
}