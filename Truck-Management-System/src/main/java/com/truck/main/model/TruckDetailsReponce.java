package com.truck.main.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
public class TruckDetailsReponce {
	 public String routeName;
	 public List<TruckDetails> truckDetails;
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public List<TruckDetails> getTruckDetails() {
		return truckDetails;
	}
	public void setTruckDetails(List<TruckDetails> truckDetails) {
		this.truckDetails = truckDetails;
	}
	
	

}
