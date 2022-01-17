package com.btkAkademi.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private final CorporateCustomerDao corporateCustomerDao;
	private final ModelMapperService modelMapperService;	
	
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result addCustomer(CreateCorporateCustomerRequest corporateCustomerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<CorporateCustomer> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<String> getTaxNumberById(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
