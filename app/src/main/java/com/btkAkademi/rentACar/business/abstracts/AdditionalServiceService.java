package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	Result add(CreateAdditionalServiceRequest request);
	
	double getSumByAdditionalServiceId(int id);
}
