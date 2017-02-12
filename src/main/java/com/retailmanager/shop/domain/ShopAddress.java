package com.retailmanager.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ShopAddress {
	@JsonIgnore
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id=0;
	
	@NotNull
	@Column(name = "shop_number", nullable = false)
	private String shopNumber;
	@NotNull
	@Column(name = "post_code", nullable = false)
	private String postCode;
	
	public ShopAddress(){
		
	}
	
	public ShopAddress(Integer id, String shopNumber, String postCode) {
		super();
		this.id = id;
		this.shopNumber = shopNumber;
		this.postCode = postCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopNumber() {
		return shopNumber;
	}
	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	

}
