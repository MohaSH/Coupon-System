package couponclientfacade;

import dbdao.CouponDBDAO;

/**
 * DailyCouponExpirationTask class for deleting expired 
 */
public class DailyCouponExpirationTask implements Runnable {
	
	/**Field*/
	private boolean Quit;
	private CouponDBDAO couponDBDAO = new CouponDBDAO();
	
	/**
	 * DailyCouponExpirationTask
	 */
	public DailyCouponExpirationTask() {
		
		Quit = true;
	}

	/**
	 * Override method runnable super class method for deleting coupons reaches endDate.
	 */
	@Override
	public void run() {
		
		while(Quit)
		{  
		   System.out.println("DailyCouponExpirationTask Start");
		   try {
			   couponDBDAO.CouponExpiration();
		       } catch (SystemException e) {
			           e.printStackTrace();
			           System.out.println("DailyCouponExpirationTask Error ");
		       }
		   try {
			   System.out.println("DailyCouponExpirationTask Thread Sleep ");
			   Thread.sleep(1000 * 60 * 60 * 6);
		       } catch (InterruptedException e) {
			           e.printStackTrace();
			           System.out.println(" DailyCouponExpirationTask Thread Sleep ERROR ");
		               }
		}
		
    }
	
	/**
	 * stopTask
	 */
	public void stopTask() {
		
		Quit = false;
		
	}
	
}