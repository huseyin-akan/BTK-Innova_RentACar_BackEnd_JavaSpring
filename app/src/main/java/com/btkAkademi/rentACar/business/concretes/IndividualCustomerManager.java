package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private final IndividualCustomerDao individualCustomerDao;
	private final ModelMapperService modelMapperService;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	public Result addIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomer) {
		var result = BusinessRules.run(checkIfEmailExists(createIndividualCustomer.getEmail()),
				checkIfUserAgeIsValid(createIndividualCustomer.getBirthDate()));

		if (result != null) {
			return result;
		}

		var customer = this.modelMapperService.forRequest().map(createIndividualCustomer, IndividualCustomer.class);
		this.individualCustomerDao.save(customer);
		return new SuccessResult(Messages.CUSTOMERADDED);
	}

	public DataResult<IndividualCustomer> getById(int id) {
		var customer = this.individualCustomerDao.findById(id);

		if (customer.isEmpty()) {
			return new ErrorDataResult<IndividualCustomer>(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessDataResult<IndividualCustomer>(customer.get());
	}
	
	private Result checkIfEmailExists(String email) {
		if (this.individualCustomerDao.findByEmail(email).isEmpty()) {
			return new SuccessResult();
		}
		return new ErrorResult(Messages.EMAILTAKEN);
	}

	private Result checkIfUserAgeIsValid(LocalDate birthDate) {
		Period period = Period.between(birthDate, LocalDate.now() );

		if (period.getYears() <= 18) {
			return new ErrorResult(Messages.AGENOTVALID);
		}
		return new SuccessResult();
	}

	@Override
	public String getNationalIdById(int customerId) {
		var customer = this.individualCustomerDao.getById(customerId);
		
		return customer.getNationalId();
	}

	@Override
	public DataResult<LocalDate> getBirthDateById(int customerId) {
		return new SuccessDataResult<LocalDate>( this.individualCustomerDao.findBirthDateById(customerId) );
	}

}
