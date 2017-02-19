package com.retailmanager.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class ShopVO extends Shop {
	
	public ShopVO(int i, String shopName, ShopAddress address) {
		super(i, shopName,  address, 0.0,  0.0);
	}
	
	public ShopVO(int i, String shopName, ShopAddress address,double lat,double lng) {
		super(i, shopName,  address,lat,  lng);
	}
	
	public ShopVO() {
		super();
	}

	@JsonIgnore
	private double longitude;
	
	@JsonIgnore
	private double latitute;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitute() {
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}
	
	
	
	

}
