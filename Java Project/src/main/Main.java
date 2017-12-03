package main;

import java.sql.Date;
import java.sql.SQLException;
import couponclientfacade.AdminFacade;
import couponclientfacade.CompanyFacade;
import couponclientfacade.CouponSystem;
import couponclientfacade.CustomerFacade;
import couponclientfacade.SystemException;
import javabeans.ClientType;
import javabeans.Coupon;
import javabeans.CouponType;
import javabeans.Customer;


public class Main {

	public static void main(String[] args) throws SystemException, ClassNotFoundException, SQLException {

	    	AdminFacade adminFacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMINISTRATOR);
		
	    	   if (adminFacade != null)
				{
	    		    //Company a=new Company(364,"DEll","ELaB","123");
	    		    Customer b=new Customer(233,"HP","password");
	    		    //if(!a.getCompName().equals("")) 
					//adminFacade.CreateCompany(a);
					//adminFacade.RemoveCompany(a);
					//adminFacade.UpdateCompany(a);
					//adminFacade.getCompany(32);
					//adminFacade.getAllCompanies();
					//adminFacade.CreateCustomer(b);
					//adminFacade.RemoveCustomer(b);
					//adminFacade.UpdateCustomer(b);
					adminFacade.getCustomer(233);
					//adminFacade.getAllCustomer();
					
				}
				
			CompanyFacade companyFacade = (CompanyFacade) CouponSystem.getInstance().login("m", "b", ClientType.COMPANY);
			
				if (companyFacade != null)
				{
					@SuppressWarnings("deprecation")
					Coupon d=new Coupon (22,"2",new Date(2011-1900,10-1,10),new Date(2020-1900,10-1,10),54,CouponType.CAMPING,"sdf",45.2,"sdf");
					
					//companyFacade.CreateCoupon(d);
					//companyFacade.RemoveCoupon(d);
					//companyFacade.UpdateCoupon(d);
					//companyFacade.getCoupon(22);
					//companyFacade.getAllCoupon();
					//companyFacade.getCouponByType(CouponType.CAMPING);
					
				}
				
			CustomerFacade customerFacade = (CustomerFacade) CouponSystem.getInstance().login("54", "a", ClientType.CUSTOMER);
			
				if (customerFacade != null)
				{
					
					@SuppressWarnings("deprecation")
					Coupon d=new Coupon (22,"2",new Date(2011-1900,10-1,10),new Date(2020-1900,10-1,10),54,CouponType.CAMPING,"sdf",45.2,"sdf");
					//customerFacade.PurchaseCoupon(d);
					//customerFacade.getAllPurchasedCoupons();
					//customerFacade.getAllPurchasedCouponsByType(CouponType.CAMPING);
					//customerFacade.getAllPurchasedCouponsByPrice(10);
					
				}	
				CouponSystem.getInstance().shutdown();
				

	}

}
