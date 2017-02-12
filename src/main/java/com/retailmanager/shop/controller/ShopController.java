package com.retailmanager.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.domain.ShopAddress;
import com.retailmanager.shop.service.ShopService;
import com.retailmanager.shop.service.exception.ShopAlreadyExistsException;

@RestController
public class ShopController {
	
	private static final String NAME = "name";
    private static final double LONGITUDE = 1234.34;
    private static final double LATITUDE = 67.76;
    private static final String shopNumber = "123";
    private static final String postCode = "700047";
    

    private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);
    private final ShopService shopService;

    @Inject
    public ShopController(final ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    public Shop addShop(@RequestBody final Shop shop) {
        LOGGER.debug("Received request to create the {}", shop);
        return shopService.save(shop);
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public List<Shop> listShop() {
        LOGGER.debug("Received request to list all shop");
      /*  
        List<Shop> shoplst = new ArrayList<>();
        ShopAddress address = new ShopAddress(0, shopNumber, postCode);
        Shop shp = new Shop(0,NAME,address,LONGITUDE,LATITUDE);
        shoplst.add(shp);
        return shoplst;*/
        
       return shopService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleShopAlreadyExistsException(ShopAlreadyExistsException e) {
        return e.getMessage();
    }

}
