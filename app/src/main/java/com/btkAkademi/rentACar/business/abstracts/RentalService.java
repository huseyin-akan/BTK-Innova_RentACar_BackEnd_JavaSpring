package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface RentalService {
	
	Result addRental(CreateRentalRequest request);
	Result checkIfCarIsRented(int carId);
}
