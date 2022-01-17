package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CreditCardInfo;

public interface PosSystemService {
	Result checkIfCreditCardIsValid(CreditCardInfo cardInfo);
}
