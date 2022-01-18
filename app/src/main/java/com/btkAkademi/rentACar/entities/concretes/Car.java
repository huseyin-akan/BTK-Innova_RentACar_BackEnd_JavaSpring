package com.btkAkademi.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "model_year")
	private int modelYear;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "findex_score")
	private int findexScore;
	
	@Column(name = "kilometer")
	private int kilometer;
	
	@Column(name = "minAge")
	private int minAge;	
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name="car_class_id")
	private CarClass carClass;
	
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;
	
	@OneToMany(mappedBy= "car")
	private List<CarDamage> carDamages;
	
	@OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances;
	
}
