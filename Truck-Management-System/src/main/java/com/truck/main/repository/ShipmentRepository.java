package com.truck.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truck.main.model.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>{
	Shipment findById(long id);
}
