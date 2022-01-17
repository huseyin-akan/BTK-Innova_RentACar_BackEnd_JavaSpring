package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.promotionCode.CreatePromotionCodeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.PromotionCode;

public interface PromotionCodeService {
	DataResult<PromotionCode> getPromotionCodeByCode(String code);
	Result addPromotionCode(CreatePromotionCodeRequest request);
}
