package com.retailmanager.shop.util;

import java.util.ArrayList;
import java.util.List;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.domain.ShopAddress;

public class ShopUtil {

    
	private static final String NAME = "name";
    private static final double LONGITUDE = 1234.34;
    private static final double LATITUDE = 67.76;
    private static final String shopNumber = "123";
    private static final String postCode = "700047";
    
    

    private ShopUtil() {
    }

    public static Shop addShop() {
    	ShopAddress address = new ShopAddress(0, shopNumber, postCode);
        return new Shop(0,NAME,address,LONGITUDE,LATITUDE) {
		};
    }

    public static List<Shop> createShopList(int howMany) {
        List<Shop> shopList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
        	ShopAddress address = new ShopAddress(0, shopNumber, postCode);
        	shopList.add(new Shop(i, NAME,address,LONGITUDE,LATITUDE));
        }
        return shopList;
    }

}
