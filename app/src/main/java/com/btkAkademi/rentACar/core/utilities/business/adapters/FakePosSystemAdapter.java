package com.btkAkademi.rentACar.core.utilities.business.adapters;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.fakeServices.FakePosSystemManager;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class FakePosSystemAdapter implements PosSystemService{

	@Override
	public Result checkIfCreditCardIsValid(CreateCreditCardInfoRequest cardInfo) {
		
		FakePosSystemManager manager = new FakePosSystemManager();
		
		if(manager.makePayment(cardInfo.getCreditCard(), cardInfo.getCardHolder(), cardInfo.getCvc(), cardInfo.getValidDate()  )) {
			return new SuccessResult();
		}
			return new ErrorResult(Messages.WRONGCARDINFO);
	}
}
