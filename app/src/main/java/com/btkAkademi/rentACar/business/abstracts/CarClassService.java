package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface CarClassService {
	DataResult<List<CarClassListDto>> getAllCarClasses();
}
