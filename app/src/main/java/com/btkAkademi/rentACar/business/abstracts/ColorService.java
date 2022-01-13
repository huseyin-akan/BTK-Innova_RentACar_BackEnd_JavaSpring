package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequests.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Color;

public interface ColorService {
	
	DataResult<List<ColorListDto>> getAll();
	
	Result addColor(CreateColorRequest createColorRequest);
	
	Result updateColor(UpdateColorRequest updateColorRequest);
	
	DataResult<Color> getColorById(int id);	
}
