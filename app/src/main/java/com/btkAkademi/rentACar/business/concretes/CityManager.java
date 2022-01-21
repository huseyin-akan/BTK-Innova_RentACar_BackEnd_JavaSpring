package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityManager implements CityService{
	
	private final ModelMapperService modelMapperService; 
	private final CityDao cityDao;	
	
	public DataResult<List<CityListDto>> getAllCities() {
		var citiesList = this.cityDao.findAll();
		
		List<CityListDto> response = citiesList.stream()
				.map(city -> modelMapperService.forDto().map(city, CityListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityListDto>>(response);
	}

}
