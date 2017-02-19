package com.retailmanager.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.retailmanager.shop.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, String> {
	
	@Query("SELECT s FROM Shop s where s.latitute between ?1 +20 AND ?1 -20 and s.longitude between ?2 +20 AND ?2 -20")
    public List<Shop> findByLatAndLng(double lat, double lng);
	
	
}
