package com.retailmanager.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailmanager.shop.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
