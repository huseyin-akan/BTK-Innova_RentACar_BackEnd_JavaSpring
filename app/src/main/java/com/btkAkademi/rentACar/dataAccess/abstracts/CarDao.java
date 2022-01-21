package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.business.dtos.CarListDtoProj;
import com.btkAkademi.rentACar.entities.concretes.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Integer> {
	
	
	int getFindexScoreById(int carId);
	
	int getMinAgeById(int carıd);
	
	@Query(value = "select \r\n"
			+ "	c.id \r\n"
			+ "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.returned_date is null or r.returned_date>NOW())\r\n"
			+ "where r.id is null and m.id is null and c.car_class_id = ?1\r\n"
			+ "order by RANDOM()\r\n"
			+ "limit 1",
			nativeQuery = true)
	Optional<Integer> getAnAvailableCarIdByClassId(int classId);
	
	@Query(value= "select \r\n"
			+ "	c.id as id, c.model_year as modelYear, c.description as description, c.kilometer as kilometer, c.daily_price as dailyPrice, \r\n"
			+ "	b.name as brand, co.name as color, c.findex_score as findexScore, c.min_age as minAge, cc.class_type as carClass\r\n"
			+ "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.returned_date is null or r.returned_date>NOW())\r\n"
			+ "left join brands b on b.id = c.brand_id\r\n"
			+ "left join colors co on co.id = c.color_id\r\n"
			+ "left join car_classes cc on c.car_class_id = cc.id\r\n"
			+ "where r.id is null and m.id is null",
			nativeQuery=true)
	Optional<List<CarListDtoProj>> getAllAvailableCars();
	
	@Query(value= "select \r\n"
			+ "	c.id as id, c.model_year as modelYear, c.description as description, c.kilometer as kilometer, c.daily_price as dailyPrice, \r\n"
			+ "	b.name as brand, co.name as color, c.findex_score as findexScore, c.min_age as minAge, cc.class_type as carClass\r\n"
			+ "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.returned_date is null or r.returned_date>NOW())\r\n"
			+ "left join brands b on b.id = c.brand_id\r\n"
			+ "left join colors co on co.id = c.color_id\r\n"
			+ "left join car_classes cc on c.car_class_id = cc.id\r\n"
			+ "where r.id is null and m.id is null",
			nativeQuery=true)
	Optional<List<CarListDtoProj>> getAllAvailableCars(Pageable pageable);
	
	@Query(value= "select \r\n"
			+ "	c.id as id, c.model_year as modelYear, c.description as description, c.kilometer as kilometer, c.daily_price as dailyPrice, \r\n"
			+ "	b.name as brand, co.name as color, c.findex_score as findexScore, c.min_age as minAge, cc.class_type as carClass\r\n"
			+ "from cars c\r\n"
			+ "left join car_maintenance m on c.id = m.car_id and m.end_date is null\r\n"
			+ "left join rentals r on c.id = r.car_id and (r.returned_date is null or r.returned_date>NOW())\r\n"
			+ "left join brands b on b.id = c.brand_id\r\n"
			+ "left join colors co on co.id = c.color_id\r\n"
			+ "left join car_classes cc on c.car_class_id = cc.id\r\n"
			+ "where r.id is null and m.id is null and c.id = ?1",
			nativeQuery=true)
	Optional<CarListDtoProj> getCarByCarId(int carId);
	
}
