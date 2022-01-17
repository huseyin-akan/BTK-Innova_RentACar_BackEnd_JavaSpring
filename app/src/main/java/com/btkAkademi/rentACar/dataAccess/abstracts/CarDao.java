package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
	
	@Query("select new com.btkAkademi.rentACar.business.dtos.CarListDto(ca.id, ca.modelYear, ca.description, ca.kilometer) "
			+ " from Car ca left join Rental re on ca.id =re.car.id" 
			+ " where re.returnDate is null")
	List<CarListDto> getAllRentableCars();
	
	
	@Query("select new com.btkAkademi.rentACar.business.dtos.CarListDto(ca.id, ca.modelYear, ca.description, ca.kilometer) "
			+ " from Car ca left join Rental re on ca.id =re.car.id" 
			+ " where re.returnDate is null")
	List<CarListDto> getAllRentableCars(Pageable pageable);
	
	
	
//	@Query("Select new com.btkAkademi.rentACar.business.dtos.CarListDto(c.id, c.modelYear, c.description, c.kilometer) "
//			+ " From Car c Inner Join c.rentals r"
//			+ " where r.returnDate is null")
	
	
	int getFindexScoreById(int carId);
	
	int getMinAgeById(int carıd);
	
}
