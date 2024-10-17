package com.truck.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truck.main.model.Nodal;
import com.truck.main.repository.NodalRepository;

@Service
public class NodalService {

	@Autowired
	private NodalRepository repo;

	public List<Nodal> getAllNodes() {
		return repo.findAll();
	}

	public Nodal findNodeById(long id) {
		return repo.findById(id).orElse(null);
	}

	public Nodal saveNode(Nodal nodal) {
		return repo.save(nodal);
	}

	public void deleteNodeById(long id) {
		repo.delete(findNodeById(id));
	}

}
