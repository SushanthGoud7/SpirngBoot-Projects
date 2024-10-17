package com.truck.main.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.truck.main.model.Shipment;
import com.truck.main.repository.NodalRepository;
import com.truck.main.repository.ShipmentRepository;

@Service
public class ShipmentService {

	

	@Autowired
	ShipmentRepository shipmentRepository;

	@Autowired
	NodalRepository nodalRepository;

	public List<Shipment> getAll() {
		return shipmentRepository.findAll();
	}

	public Shipment getShipmentById(long id) {
		return shipmentRepository.findById(id);
	}

	public Shipment saveShipment(Shipment shipment) {
		shipment.setPickupNode(nodalRepository.findById(shipment.getPickupNodeFk()).orElse(null));
		shipment.setDeliveryNode(nodalRepository.findById(shipment.getDeliveryNodeFk()).orElse(null));
		return shipmentRepository.save(shipment);
	}

	public void deleteShipment(long id) {
		shipmentRepository.deleteById(id);
	}
	
}
