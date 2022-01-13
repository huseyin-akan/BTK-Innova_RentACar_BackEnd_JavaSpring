package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Brand;

public interface BrandService {
	
	DataResult<List<BrandListDto>> getAll();
	
	Result add(CreateBrandRequest brandCreateDto);
	
	Result update(UpdateBrandRequest updateBrandRequest);
	
	DataResult<Brand> getBrandById(int id);
}
