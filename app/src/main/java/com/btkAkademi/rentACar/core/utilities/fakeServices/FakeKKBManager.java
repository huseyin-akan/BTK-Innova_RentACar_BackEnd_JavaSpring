package com.btkAkademi.rentACar.core.utilities.fakeServices;

import java.util.Random;

public class FakeKKBManager {
	
	public int getFindexScore() {
		Random rand = new Random();
		return rand.nextInt(10000); 
	}	
}
