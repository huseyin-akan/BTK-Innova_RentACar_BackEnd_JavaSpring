package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class Rental {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="rentDate")
	private LocalDate rentDate;
	
	@Column(name="returnDate")
	private LocalDate returnDate;
	
	@Column(name="rentedKilometer")
	private int rentedKilometer;
	
	@Column(name="returnedKilometer")
	private int returnedKilometer;
	
	@ManyToOne
	@JoinColumn(name="rented_city_id")
	private City rentedCity;
	
	@ManyToOne
	@JoinColumn(name="returned_city_id")
	private City returnedCity;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
    @JoinColumn(name ="car_id")
    private Car car;
	
}
