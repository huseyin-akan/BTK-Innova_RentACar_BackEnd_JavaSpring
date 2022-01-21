package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarClassService;
import com.btkAkademi.rentACar.business.dtos.CarClassListDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarClassDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarClassManager implements CarClassService {
	
	private final CarClassDao carClassDao;
	private final ModelMapperService modelMapperService;
	
	
	@Override
	public DataResult<List<CarClassListDto>> getAllCarClasses() {
	
		var response = this.carClassDao.findAll().stream().map(car -> modelMapperService.forDto().map(car, CarClassListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarClassListDto>>(response);
	}

}
