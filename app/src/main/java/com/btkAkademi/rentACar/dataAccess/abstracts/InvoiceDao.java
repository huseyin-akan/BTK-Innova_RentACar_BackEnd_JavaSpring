package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.btkAkademi.rentACar.business.dtos.InvoiceListDtoProj;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer>{
	
	@Query(value="select c.first_name as firstName, c.last_name as lastName, u.email as email, r.rent_date as rentDate, r.return_date as returnDate, \r\n"
			+ "r.rented_kilometer as rentedKilometer, ca.daily_price as dailyPrice, ca.model_year as modelYear, b.name as brandName, m.model_name as modelName, c.image_url as imageUrl, co.name as color,\r\n"
			+ "ci.city_name as rentCity, ci2.city_name as returnCity, p.payment_date as paymentDate, p.total_sum as totalSum\r\n"
			+ "from rentals r\r\n"
			+ "left join individual_customers c on c.customer_id = r.customer_id\r\n"
			+ "left join users u on u.id = r.customer_id\r\n"
			+ "left join cars ca on ca.id = r.car_id\r\n"
			+ "left join brands b on ca.brand_id = b.id\r\n"
			+ "left join models m on m.id = c.model_id\r\n"
			+ "left join colors co on ca.color_id = co.id\r\n"
			+ "left join cities ci on ci.id = r.rented_city_id\r\n"
			+ "left join cities ci2 on ci2.id = r.returned_city_id\r\n"
			+ "left join payments p on r.id = p.rental_id\r\n"
			+ "where r.id = ?1",
			nativeQuery = true)
	Optional<InvoiceListDtoProj> getInvoiceByRentalId(int rentalId);
}
