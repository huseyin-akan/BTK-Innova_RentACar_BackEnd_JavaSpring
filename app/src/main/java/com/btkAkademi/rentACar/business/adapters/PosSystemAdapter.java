package com.btkAkademi.rentACar.business.adapters;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PosSystemService;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.fakeManagers.FakePosSystemManager;
import com.btkAkademi.rentACar.core.utilities.helpers.CreditCardInfo;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class PosSystemAdapter implements PosSystemService{
	FakePosSystemManager manager = new FakePosSystemManager();

	@Override
	public Result checkIfCreditCardIsValid(CreditCardInfo cardInfo) {
		if(manager.makePayment(cardInfo.getPassword(), cardInfo.getCreditCard(), cardInfo.getValidDate(), cardInfo.getCVC() )) {
			return new SuccessResult();
		}
			return new ErrorResult(Messages.WRONGCARDINFO);
	}
}
