package com.retailmanager.shop.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.domain.ShopVO;
import com.retailmanager.shop.service.ShopService;
import com.retailmanager.shop.util.ShopUtil;

import ch.qos.logback.classic.Logger;


@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

    @Mock
    private ShopService shopService;
    
    @Mock
    private ShopService shopService1;


    private ShopController shopController;

    @Before
    public void setUp() throws Exception {
    	shopController = new ShopController(shopService);
    }
    
    
    
    public static Double[][] testLatAndLng() {
        return new Double[][] {
            { 22.00000, 87.9463 },
            { 24.00000,83.09866}
        };
    }
    
    

    @Test
    public void shouldCreateShop() throws Exception {
        final Shop savedShop = stubServiceToReturnStoredShop();
        final ShopVO shop = ShopUtil.addShop();
        Shop returnedShopVO = shopController.addShop(shop);
        // verify shop was passed to ShopService
       // verify(shopService, times(1)).save(shop);
        assertEquals("Returned shop should come from the service", savedShop, returnedShopVO);
    }

    private ShopVO stubServiceToReturnStoredShop() {
        final ShopVO shop = ShopUtil.addShop();
        when(shopService.save(any(Shop.class))).thenReturn(shop);
        return shop;
    }
    
    private Shop stubServiceToReturnNearestStoredShop(double lat,double lng) {
        final Shop shop = ShopUtil.addShop();
        shop.setLongitude(lng);
        shop.setLatitute(lat);
        when(shopService.getNearestShop(lat, lng)).thenReturn(shop);
        return shop;
    }
    
    private Shop stubServiceToReturnStoredShopWithPostalCode(String name,String postalCode) {
        final Shop shop = ShopUtil.addShop(name,postalCode);
        when(shopService.save(any(Shop.class))).thenReturn(shop);
        return shop;
    }


    @Test
    public void shouldListAllShops() throws Exception {
        stubServiceToReturnExistingShops(10);
        Collection<Shop> shops = shopController.listShop();
        assertNotNull(shops);
        assertEquals(10, shops.size());
        // verify shop was passed to ShopService
       // verify(shopService, times(1)).getList();
    }

    private void stubServiceToReturnExistingShops(int howMany) {
        when(shopService.getList()).thenReturn(ShopUtil.createShopList(howMany));
    }
    
    
    @Test
    public void shouldFindNearestShop() throws Exception {
    	stubServiceToReturnStoredShopWithPostalCode("shop1","41105");
        final Shop shop1 = ShopUtil.addShop("shop1","41105");
        Shop returnedShop1 = shopService.save(shop1);
        
        
        
        stubServiceToReturnStoredShopWithPostalCode("shop2","700047");
        final ShopVO shop2 = ShopUtil.addShop("shop2","700047");
        Shop returnedShop2 = shopController.addShop(shop2);
       
        
        stubServiceToReturnStoredShopWithPostalCode("shop3","560001");
        final ShopVO shop3 = ShopUtil.addShop("shop3","560001");
        Shop returnedShop3 = shopController.addShop(shop3);
        
        
        stubServiceToReturnStoredShopWithPostalCode("shop4","713103");
        final ShopVO shop4 = ShopUtil.addShop("shop4","713103");
        Shop returnedShop4 = shopController.addShop(shop4);
        
        
        
        stubServiceToReturnNearestStoredShop(22.663699, 87.746805);
        Shop shop = shopController.nearestShop(22.663699, 87.746805);
       
        
        
        assertNotNull(shop);
     
        assertEquals("Returned nearest shop should come from the service", returnedShop2.getShopAddress().getPostCode(), shop.getShopAddress().getPostCode());
    }

    
    
    

}
