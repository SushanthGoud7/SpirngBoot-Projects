package com.truck.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="NODAL")
public class Nodal {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NODAL_ID")
	private long id;
	
	@Column(name = "NODAL_NAME")
	private String name;
	
	@Column(name = "ADDRESSL1")
	private String addl1;
	
	@Column(name = "ADDRESSL2")
	private String addl2;
	
	@Column(name = "DISTRICT")
	private String district;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;

}
