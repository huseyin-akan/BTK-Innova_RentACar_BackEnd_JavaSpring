package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface FindexScoreService {
	DataResult<Integer> getCorporateFindexScore(String taxNumber);
	DataResult<Integer> getIndividualFindexScore(String tcNO);
}
