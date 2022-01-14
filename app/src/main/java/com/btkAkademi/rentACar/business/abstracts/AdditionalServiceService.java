package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

public interface AdditionalServiceService {
	Result add(CreateAdditionalServiceRequest request);
	
	DataResult<List<AdditionalService>> getAdditionalServicesByRentalId(int rentalId);
}
