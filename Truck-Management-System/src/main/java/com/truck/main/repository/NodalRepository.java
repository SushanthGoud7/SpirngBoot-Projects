package com.truck.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.truck.main.model.Nodal;

@Repository
public interface NodalRepository extends JpaRepository<Nodal ,Long>   {


}
