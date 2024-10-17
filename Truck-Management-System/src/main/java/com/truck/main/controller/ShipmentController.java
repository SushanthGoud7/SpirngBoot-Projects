package com.truck.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.truck.main.model.Shipment;
import com.truck.main.service.ShipmentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/shipment")
public class ShipmentController {
	
	@Autowired
	ShipmentService shipmentService;
	
	@GetMapping("/get-all")
	public List<Shipment> getAll(){
		return shipmentService.getAll();
    }
	
	
	
	/*
	 * @GetMapping("/report") public ResultData getAllResultData(){ return
	 * shipmentService.generateResultData(); }
	 */
	 
	
	@GetMapping (path="/get-shipment/{id}")
    public Shipment fetchShipmentByID(@PathVariable("id") long id){
        return  shipmentService.getShipmentById(id);
    }
	@PostMapping("/add-shipment")
	public Shipment saveShipment(@RequestBody Shipment shipment) {
		return shipmentService.saveShipment(shipment);
	}
	
	@PutMapping("/update-shipment")
	public Shipment updateShipment(@RequestBody Shipment shipment){
		return shipmentService.saveShipment(shipment);
	}
	
	@DeleteMapping("/delete-shipment/{id}")
	public ResponseEntity<String> deleteShipment(@PathVariable("id") long id) {
		shipmentService.deleteShipment(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
