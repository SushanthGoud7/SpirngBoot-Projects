package com.truck.main.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckDetails {
	
	private long truckId;
	private String truckName;
	private double truckVolume;
	
	private List<ShipmentData> shipmentList;

}
