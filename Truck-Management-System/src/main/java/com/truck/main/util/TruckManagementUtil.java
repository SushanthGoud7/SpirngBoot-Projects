package com.truck.main.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.truck.main.model.Shipment;
import com.truck.main.model.ShipmentData;
import com.truck.main.model.TruckDetails;

@Repository
public class TruckManagementUtil {
	

	private static double small = 1000;
	private static double medium= 1500;
	private static double large = 2000; 
//	public static void main(String str[]) {
//		List<Shipment> shipmentList = populateShipments();
//		generate(shipmentList);
//	}
	public static Map<String, List<TruckDetails>> generate(List<Shipment> shipmentList) {
		List<String> routeList = getRoutes(shipmentList);
		System.out.println(routeList);
		Map<String, List<TruckDetails>> truckMap = new HashMap<>();
		Map<String, List<ShipmentData>> shipmentMap = new HashMap<>();
		for(String route:routeList) {
			truckMap.put(route, new ArrayList<>());
			shipmentMap.put(route, new ArrayList<>());
		}
		for (Shipment s : shipmentList) {
			ShipmentData shipmentData = ShipmentData.builder().deliveryNode(s.getDeliveryNode())
					.packageName(s.getPackageName()).pickupNode(s.getPickupNode()).pkgHeight(s.getPkgHeight())
					.pkgBreadth(s.getPkgBreadth()).pkgLength(s.getPkgLength()).id(s.getId()).pkgWeight(s.getPkgWeight())
					.recieverName(s.getRecieverName()).senderName(s.getSenderName()).build();

			shipmentMap.get(s.getPickupNode().getName() + "->" + s.getDeliveryNode().getName()).add(shipmentData);
		}
		
		for(String route:routeList) {
			List<ShipmentData> remainingShipmentList = shipmentMap.get(route).stream()
					.sorted((o1,o2)->(int)(o2.getVolume()-o1.getVolume())).collect(Collectors.toList());
			
			double totalVolume = getVolume(remainingShipmentList);
			System.out.println(totalVolume);
			
			
			for(ShipmentData shipment:remainingShipmentList) {
				boolean added = false;
				for(TruckDetails truck:truckMap.get(route)) {
					double availableSpace = truck.getTruckVolume()-
					truck.getShipmentList().stream().map(s->s.getVolume()).reduce(0D,(a,b)->a+b);
					if(availableSpace>shipment.getVolume()) {
						List<ShipmentData> sList= new ArrayList<>();
						
						
						sList.addAll(truck.getShipmentList());
						sList.add(shipment);
						truck.setShipmentList(sList);
						added=true;
						break;
					}
				}
				if(added){continue;}
				if(shipment.getVolume()<=small ){ 
					truckMap.get(route).add(
							TruckDetails.builder().truckVolume(small).shipmentList(Collections.singletonList(shipment)).
							truckName("Truck Small").build());
					continue;
				}
				if(shipment.getVolume()<=medium ){ 
					truckMap.get(route).add(
							TruckDetails.builder().truckVolume(medium).shipmentList(Collections.singletonList(shipment)).
							truckName("Truck medium").build());
					continue;
				}
				if(shipment.getVolume()<=large ){ 
					truckMap.get(route).add(
							TruckDetails.builder().truckVolume(large).shipmentList(Collections.singletonList(shipment)).
							truckName("Truck Large").build());
					continue;
				}
			}

		}
		print(truckMap);
		return truckMap;
	}
	
	public static List<String> getRoutes(List<Shipment> shipmentList){
		return shipmentList.stream().map(s->s.getPickupNode().getName()+"->"+s.getDeliveryNode().getName())
		.collect(Collectors.toSet()).stream().sorted().collect(Collectors.toList());
	}

	/*
	 * public static List<Shipment> populateShipments() { List<Shipment>
	 * shipmentList = new ArrayList<>();
	 * 
	 * shipmentList.add(Shipment.builder().pickupNode(Nodal.builder().name("Node-A")
	 * .build()) .deliveryNode(Nodal.builder().name("Node-B").build())
	 * .packageName("package-1").pkgBreadth(5).pkgHeight(5).pkgLength(50) .build());
	 * shipmentList.add(Shipment.builder().pickupNode(Nodal.builder().name("Node-A")
	 * .build()) .deliveryNode(Nodal.builder().name("Node-B").build())
	 * .packageName("package-2").pkgBreadth(2).pkgHeight(5).pkgLength(5) .build());
	 * shipmentList.add(Shipment.builder().pickupNode(Nodal.builder().name("Node-A")
	 * .build()) .deliveryNode(Nodal.builder().name("Node-C").build())
	 * .packageName("package-3").pkgBreadth(2).pkgHeight(5).pkgLength(20) .build());
	 * shipmentList.add(Shipment.builder().pickupNode(Nodal.builder().name("Node-A")
	 * .build()) .deliveryNode(Nodal.builder().name("Node-C").build())
	 * .packageName("package-4").pkgBreadth(2).pkgHeight(5).pkgLength(2) .build());
	 * shipmentList.add(Shipment.builder().pickupNode(Nodal.builder().name("Node-B")
	 * .build()) .deliveryNode(Nodal.builder().name("Node-A").build())
	 * .packageName("package-5").pkgBreadth(2).pkgHeight(5).pkgLength(2) .build());
	 * 
	 * return shipmentList; }
	 */
	
	public static double getVolume(List<ShipmentData> shipmentList) {
		return shipmentList.stream().map(s->getVolume(s)).reduce(0D, (a,b)->a+b);
	}
	
	public static double getVolume(ShipmentData shipment) {
		return shipment.getPkgLength()*shipment.getPkgBreadth()*shipment.getPkgHeight();
	}
	
	public static void print(Map<String,List<TruckDetails>> truckMap) {
		for(Map.Entry<String,List<TruckDetails>> es: truckMap.entrySet()) {
			System.out.println(es.getKey());
			for(TruckDetails truck:es.getValue()) {
				System.out.println("\t"+truck.getTruckVolume());
				for(ShipmentData shipment:truck.getShipmentList()) {
					System.out.println("\t\t"+shipment.getVolume());
				}
			}
			
		}
	}
	
}
