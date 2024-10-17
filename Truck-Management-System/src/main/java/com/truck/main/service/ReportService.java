package com.truck.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.main.model.Shipment;
import com.truck.main.model.TruckDetails;
import com.truck.main.repository.ShipmentRepository;
import com.truck.main.util.TruckManagementUtil;

@Service
public class ReportService {
	
	@Autowired
	private TruckManagementUtil util;
	
	@Autowired
	private ShipmentRepository repository;
	
	
	
	
	public Map<String,List<TruckDetails>> generateReport(){
		
		List<Shipment> shipmentList= repository.findAll();
		return util.generate(shipmentList);
	}

}
