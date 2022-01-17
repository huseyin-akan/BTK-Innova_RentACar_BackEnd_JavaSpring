package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "car" })
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
	
	@Column(name="total_sum")
	private double totalSum;
	
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
	
	@OneToOne
	@JoinColumn(name="invoice_id")
	private Invoice invoice;
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> additionalServices;
}
