package com.btkAkademi.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	
	private final AdditionalServiceDao additionalServiceDao;
	private final ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	public Result add(CreateAdditionalServiceRequest request) {
//		var result= BusinessRules.run(
//				
//				);
//		
//		if(result != null) {
//			return result;
//		}
		
		var additionalServiceToAdd = this.modelMapperService.forRequest().map(request, AdditionalService.class);
		this.additionalServiceDao.save(additionalServiceToAdd);
		return new SuccessResult(Messages.ADDITIONALSERVICEADDED);
	}

	@Override
	public DataResult<List<AdditionalService>> getAdditionalServicesByRentalId(int rentalId) {
		return new SuccessDataResult<List<AdditionalService>>(this.additionalServiceDao.findAllByRental_Id(rentalId));
	}

}
