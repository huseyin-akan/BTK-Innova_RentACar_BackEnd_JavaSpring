package com.btkAkademi.rentACar.business.concretes;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromotionCodeService;
import com.btkAkademi.rentACar.business.requests.promotionCode.CreatePromotionCodeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromotionCodeDao;
import com.btkAkademi.rentACar.entities.concretes.PromotionCode;

@Service
public class PromotionCodeManager implements PromotionCodeService{
	private final PromotionCodeDao promotionCodeDao;
	private final ModelMapperService modelMapperService;
	
	public PromotionCodeManager(PromotionCodeDao promotionCodeDao, ModelMapperService modelMapperService) {
		this.promotionCodeDao = promotionCodeDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<PromotionCode> getPromotionCodeByCode(String code) {
		var codeToReturn = this.promotionCodeDao.findByCode(code);
		return codeToReturn.isEmpty() ? 
				new ErrorDataResult<PromotionCode>(Messages.CODENOTFOUND) :
					new SuccessDataResult<PromotionCode>(codeToReturn.get());
	}

	@Override
	public Result addPromotionCode(CreatePromotionCodeRequest request) {
		var result= BusinessRules.run(
				
				);
		
		if(result != null) {
			return result;
		}
		
		var promotionCode = this.modelMapperService.forRequest().map(request, PromotionCode.class);
		this.promotionCodeDao.save(promotionCode);
		return new SuccessResult(Messages.PROMOTIONCODEADDED);
	}
	
}
