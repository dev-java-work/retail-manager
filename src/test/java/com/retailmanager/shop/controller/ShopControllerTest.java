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
import com.retailmanager.shop.service.ShopService;
import com.retailmanager.shop.util.ShopUtil;


@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {

    @Mock
    private ShopService shopService;

    private ShopController shopController;

    @Before
    public void setUp() throws Exception {
    	shopController = new ShopController(shopService);
    }

    @Test
    public void shouldCreateShop() throws Exception {
        final Shop savedShop = stubServiceToReturnStoredShop();
        final Shop shop = ShopUtil.addShop();
        Shop returnedShop = shopController.addShop(shop);
        // verify shop was passed to ShopService
        verify(shopService, times(1)).save(shop);
        assertEquals("Returned shop should come from the service", savedShop, returnedShop);
    }

    private Shop stubServiceToReturnStoredShop() {
        final Shop shop = ShopUtil.addShop();
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
        verify(shopService, times(1)).getList();
    }

    private void stubServiceToReturnExistingShops(int howMany) {
        when(shopService.getList()).thenReturn(ShopUtil.createShopList(howMany));
    }

}
