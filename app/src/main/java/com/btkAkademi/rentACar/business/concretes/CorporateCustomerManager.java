package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	private final CorporateCustomerDao corporateCustomerDao;
	private final ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	public Result addCustomer(CreateCorporateCustomerRequest corporateCustomerRequest) {
		
		var result = BusinessRules.run(
				checkIfCompanyNameExists(corporateCustomerRequest.getCompanyName()),
				checkIfEmailExists(corporateCustomerRequest.getEmail())
				);
		
		if(result != null) {
			return result;
		}
		
		var customer = this.modelMapperService.forRequest().map(corporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(customer);
		return new SuccessResult(Messages.CUSTOMERADDED);
	}
	
	public DataResult<CorporateCustomer> getById(int id) {
		
		var customer = this.corporateCustomerDao.findById(id);
		
		if(customer.isEmpty()) {
			return new ErrorDataResult<CorporateCustomer>(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessDataResult<CorporateCustomer>(customer.get() );
	}
	
	private Result checkIfCompanyNameExists(String companyName) {
		if(!this.corporateCustomerDao.findByCompanyName(companyName).isEmpty()) {
			return new ErrorResult(Messages.COMPANYNAMETAKEN);
		}
		return new SuccessResult();
	}
	
	//TODO individual hesapların emaillerine karşı tarama yapmıyor.
	private Result checkIfEmailExists(String email) {
		if(this.corporateCustomerDao.findByEmail(email).isEmpty()) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.EMAILTAKEN);
	}
	
}
