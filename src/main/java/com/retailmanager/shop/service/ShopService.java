package com.retailmanager.shop.service;

import java.util.List;

import com.retailmanager.shop.domain.Shop;

public interface ShopService {

    Shop save(Shop shop);

    List<Shop> getList();

}
