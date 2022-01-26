package com.btkAkademi.rentACar.core.utilities.fakeServices;

public class FakePosSystemManager {
	public boolean makePayment(String creditCard, String cardHolder, String cvc, String validityDate) {
		if(creditCard.length() == 16) {
			System.out.println(creditCard.length() );
			return true;
		}else {
			System.out.println(creditCard.length() );
			return false;
		}
	}
}
