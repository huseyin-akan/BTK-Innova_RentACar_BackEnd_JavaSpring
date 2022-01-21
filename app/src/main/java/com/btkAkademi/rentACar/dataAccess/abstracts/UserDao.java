package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.stereotype.Repository;

import com.btkAkademi.rentACar.entities.concretes.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
