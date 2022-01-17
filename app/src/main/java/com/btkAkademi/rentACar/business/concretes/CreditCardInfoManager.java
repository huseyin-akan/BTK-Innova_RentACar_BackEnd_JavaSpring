package com.btkAkademi.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CreditCardInfoService;
import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CreditCardInfoDao;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;

@Service
public class CreditCardInfoManager implements CreditCardInfoService{

	private final CreditCardInfoDao creditCardInfoDao;
	private final ModelMapperService modelMapperService;
	
	public CreditCardInfoManager(CreditCardInfoDao creditCardInfoDao, ModelMapperService modelMapperService) {

		this.creditCardInfoDao = creditCardInfoDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result saveCard(CreateCreditCardInfoRequest creditCardInfoRequest) {
		var result= BusinessRules.run(

				);
		
		if(result != null) {
			return result;
		}
		
		var cardToAdd = this.modelMapperService.forRequest().map(creditCardInfoRequest, CreditCardInfo.class);
		this.creditCardInfoDao.save(cardToAdd);
		return new SuccessResult(Messages.CARDADDED);		
	}
	
}
