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
		
	
	int getFindexScoreById(int carId);
	
	int getMinAgeById(int carıd);
	
//	@Query("From Car c left join Rental r on c.id =r.car.id" 
//			+ " where r.returnDate is not null and c.carClass.id=:classId")
	
	@Query(value= "select \r\n"
			+ "	c.id\r\n"
			+ "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.return_date is null or r.return_date>NOW())\r\n"
			+ "where c.car_class_id = ?1 and r.id is null\r\n"
			+ "limit 1",
			nativeQuery=true)
	int getAnAvailableCarIdByClassId(int classId);
	
}
