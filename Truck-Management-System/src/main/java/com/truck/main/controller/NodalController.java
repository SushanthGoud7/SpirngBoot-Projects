package com.truck.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truck.main.model.Nodal;
import com.truck.main.service.NodalService;

@RestController
@CrossOrigin("*")
@RequestMapping("/node")
public class NodalController {

	@Value("${my.name}")

	public String myName;

	@Autowired
	private NodalService service;

	@GetMapping(path = "/get-all")
	public ResponseEntity<List<Nodal>> getAllNodes() {
		return new ResponseEntity<List<Nodal>>(service.getAllNodes(), HttpStatus.OK);
	}

	@GetMapping(path = "/get-node/{id}")
	public ResponseEntity<Nodal> fetchTruckByID(@PathVariable("id") long id) {

		return new ResponseEntity<Nodal>(service.findNodeById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/add-node")
	public ResponseEntity<Nodal> addTruck(@RequestBody Nodal nodal) {
		return new ResponseEntity<Nodal>(service.saveNode(nodal), HttpStatus.CREATED);
	}

	@PutMapping(path = "/update-node")
	public ResponseEntity<Nodal> upgradeTruck(@RequestBody Nodal nodal) {

		return new ResponseEntity<Nodal>(service.saveNode(nodal), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/delete-node/{id}")
	public ResponseEntity<String> removetruck(@PathVariable("id") long id) {
		service.deleteNodeById(id);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
