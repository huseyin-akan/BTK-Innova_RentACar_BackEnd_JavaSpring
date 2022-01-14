package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface RentalService {
	
	Result addRental(CreateRentalRequest request);
	Result checkIfCarIsRented(int carId);
	
	DataResult<Car> getCarByRentalId(int id);
}
