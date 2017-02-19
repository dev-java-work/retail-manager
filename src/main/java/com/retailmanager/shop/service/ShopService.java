package com.retailmanager.shop.service;

import java.util.List;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.service.exception.NoShopExistsException;

public interface ShopService {

    Shop save(Shop shop);

    List<Shop> getList();
    
    public Shop getNearestShop(double lat,double lng)  throws NoShopExistsException;

}
