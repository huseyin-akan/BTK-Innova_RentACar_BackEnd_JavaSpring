package com.btkAkademi.rentACar.core.utilities.business.adapters;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.FindexScoreService;
import com.btkAkademi.rentACar.core.utilities.fakeServices.FakeKKBManager;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;

@Service
public class FakeKKBManagerAdapter implements FindexScoreService{

	FakeKKBManager kkbManager = new FakeKKBManager();

	@Override
	public DataResult<Integer> getCorporateFindexScore(String taxNumber) {
		return new SuccessDataResult<Integer>(kkbManager.getFindexScore() );
	}

	@Override
	public DataResult<Integer> getIndividualFindexScore(String tcNO) {
		return new SuccessDataResult<Integer>(kkbManager.getFindexScore() );
	}
}
