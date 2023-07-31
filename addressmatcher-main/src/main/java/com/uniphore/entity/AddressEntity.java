package com.uniphore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addressdata")
public class AddressEntity {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false)
	 private Integer id;
	
	  @Column(name = "addresslineone", nullable = false)
	    private String addresslineone;
	  
	  @Column(name = "addresslinetwo", nullable = false)
	    private String addresslinetwo;
	  
	  @Column(name = "addresslinethree", nullable = false)
	    private String addresslinethree;
	  
	  @Column(name = "region", nullable = false)
	    private String region;
	  
	  @Column(name = "pincode", nullable = false)
	    private String pincode;
	  
	  @Column(name = "object_id", nullable = false)
	    private String object_id;
	  
	  

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddresslineone() {
		return addresslineone;
	}

	public void setAddresslineone(String addresslineone) {
		this.addresslineone = addresslineone;
	}

	public String getAddresslinetwo() {
		return addresslinetwo;
	}

	public void setAddresslinetwo(String addresslinetwo) {
		this.addresslinetwo = addresslinetwo;
	}

	public String getAddresslinethree() {
		return addresslinethree;
	}

	public void setAddresslinethree(String addresslinethree) {
		this.addresslinethree = addresslinethree;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	
	  
	  
}
