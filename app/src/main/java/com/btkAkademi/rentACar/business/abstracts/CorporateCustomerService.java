package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerService {
	
	Result addCustomer(CreateCorporateCustomerRequest corporateCustomerRequest);
	
	DataResult<CorporateCustomer> getById(int id);
	
	DataResult<String> getTaxNumberById(int customerId);
}
