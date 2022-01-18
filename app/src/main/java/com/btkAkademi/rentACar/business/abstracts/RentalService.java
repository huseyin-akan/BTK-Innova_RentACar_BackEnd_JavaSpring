package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateCorporateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateIndividualRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.EndCCRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.EndICRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {
	
	Result rentForIndividualCustomer(CreateIndividualRentalRequest request);
	Result rentForCorporateCustomer(CreateCorporateRentalRequest request);
	Result endRentalProcessForIC(EndICRentalRequest request);
	Result endRentalProcessForCC(EndCCRentalRequest request);
	
	Result checkIfCarIsRented(Car car);
	
	//TODO car'ı get etme işlemini car service yapmalı.
	DataResult<Car> getCarByRentalId(int id);
	DataResult<Rental> getRentalById(int id);
}
