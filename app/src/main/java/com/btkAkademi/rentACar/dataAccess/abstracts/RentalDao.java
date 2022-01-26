package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer>{
	Rental findByCarId (int carId);	
	
	Rental findByCarIdAndReturnDateIsNull(int id);
	
	Rental findByCarIdAndReturnedDateIsNull(int carId);
	
	@Query(value = "select * from rentals\r\n"
			+ "where customer_id = ?1\r\n"
			+ "order by id desc\r\n"
			+ "limit 1",
			nativeQuery = true)
	Rental getLastRentalByCustomerId(int customerId);
}	
