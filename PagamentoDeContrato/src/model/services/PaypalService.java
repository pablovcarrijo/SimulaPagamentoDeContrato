package model.services;

public class PaypalService implements OnlinePaymentService{


	public Double paymentFree(Double amount) {
		return amount + (amount * 0.02);
	}
	public Double interest(Double amount, Integer months) {
		return amount + (amount * 0.01 * months);
	}
	
}
