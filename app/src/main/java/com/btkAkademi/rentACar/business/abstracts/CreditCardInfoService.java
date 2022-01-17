package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.creditCardInfoRequest.CreateCreditCardInfoRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CreditCardInfoService {
	Result saveCard(CreateCreditCardInfoRequest creditCardInfoRequest);
}
