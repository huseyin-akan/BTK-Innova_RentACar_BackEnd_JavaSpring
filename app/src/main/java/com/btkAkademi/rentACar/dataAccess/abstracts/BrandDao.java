package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	Optional<Brand> findByName(String name);
}	
