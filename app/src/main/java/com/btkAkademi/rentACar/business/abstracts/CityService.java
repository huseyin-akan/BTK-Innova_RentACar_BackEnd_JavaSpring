package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface CityService {
	DataResult<List<CityListDto>> getAllCities();
}
