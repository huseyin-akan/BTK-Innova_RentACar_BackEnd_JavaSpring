package com.btkAkademi.rentACar.core.utilities.fakeManagers;


public class FakePosSystemManager {
	public boolean makePayment(String password, String creditCard, String validDate, String cVC) {
		if(creditCard.length() == 16 && cVC.length() == 3 && password.startsWith("3")) {
			return true;
		}else {
			return false;
		}
	}
}
