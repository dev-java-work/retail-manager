package com.retailmanager.shop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shop {
	
	@JsonIgnore
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id=0;
	

	@NotNull
	@Column(name = "shop_name", nullable = false)
	private String shopName;

	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="id")
	private ShopAddress shopAddress;
	

	@NotNull
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
	@NotNull
	@Column(name = "latitude", nullable = false)
	private double latitute;

	public Shop(){
		
	}
	public Shop(Integer id, String shopName, ShopAddress shopAddress, double longitude, double latitute) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.longitude = longitude;
		this.latitute = latitute;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

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
	@Override
	public String toString() {
		return "Shop [id=" + id + ", shopName=" + shopName + ", shopAddress=" + shopAddress + ", longitude=" + longitude
				+ ", latitute=" + latitute + "]";
	}
	
	

}
