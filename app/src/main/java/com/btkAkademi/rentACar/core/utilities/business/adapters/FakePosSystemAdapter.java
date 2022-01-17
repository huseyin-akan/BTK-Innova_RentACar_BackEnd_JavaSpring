package com.btkAkademi.rentACar.core.utilities.business.adapters;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.fakeServices.FakePosSystemManager;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;

@Service
public class FakePosSystemAdapter implements PosSystemService{

	@Override
	public Result checkIfCreditCardIsValid(CreditCardInfo cardInfo) {
		
		FakePosSystemManager manager = new FakePosSystemManager();
		
		if(manager.makePayment(cardInfo.getCardHolder(), cardInfo.getCreditCard(), cardInfo.getValidDate(), cardInfo.getCVC() )) {
			return new SuccessResult();
		}
			return new ErrorResult(Messages.WRONGCARDINFO);
	}
}
