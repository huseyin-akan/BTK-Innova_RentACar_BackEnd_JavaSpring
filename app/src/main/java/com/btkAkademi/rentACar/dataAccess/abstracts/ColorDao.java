package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.Color;

@Repository
public interface ColorDao  extends JpaRepository<Color, Integer>{
	Optional<Color> findByName(String name);
}
