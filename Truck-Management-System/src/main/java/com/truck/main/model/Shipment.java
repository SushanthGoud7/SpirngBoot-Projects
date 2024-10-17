package com.truck.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name="SHIPMENT")
public class Shipment {
	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHIPMENT_ID")
	private long id;
	
	@Column(name = "PACKAGE_NAME")
	private String packageName;
	
	@Column(name = "SENDER_NAME")
	private String senderName;
	
	@Column(name = "RECEIVER_NAME")
	private String recieverName;
	
	@Column(name = "PACKAGE_HEIGHT")
	private double pkgHeight;
	
	@Column(name = "PACKAGE_LENGTH")
	private double pkgLength;
	
	@Column(name = "PACKAGE_BREADTH")
	private double pkgBreadth;
	
	@Column(name = "PACKAGE_WEIGHT")
	private int pkgWeight;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(
			name="PK_NODAL_ID_FK",
			referencedColumnName = "NODAL_ID"
			
			)
	private Nodal pickupNode;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(
			name="DL_NODAL_ID_FK",
			referencedColumnName = "NODAL_ID"
			
			)
	private Nodal deliveryNode;
	
	@Transient
	private Long pickupNodeFk;
	
	@Transient
	private Long deliveryNodeFk;
	
	public double getVolume() {
		return pkgLength*pkgBreadth*pkgHeight;
	}
	

}