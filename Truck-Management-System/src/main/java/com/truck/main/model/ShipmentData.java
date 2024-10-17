package com.truck.main.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Builder
@Data
@ToString
public class ShipmentData {
	
	
	
	private long id;
	
	private String packageName;
	
	private String senderName;
	
	private String recieverName;
	
	private double pkgHeight;
	
	private double pkgLength;
	
	private double pkgBreadth;
	
	private int pkgWeight;
	
	
	private Nodal pickupNode;
	
	
	private Nodal deliveryNode;
	
	
	public double getVolume() {
		return pkgLength*pkgBreadth*pkgHeight;
	}
	
	
}