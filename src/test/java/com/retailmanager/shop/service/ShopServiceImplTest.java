package com.retailmanager.shop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import org.springframework.beans.factory.annotation.Autowired;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.repository.ShopRepository;

import com.retailmanager.shop.util.ShopUtil;



@RunWith(MockitoJUnitRunner.class)
public class ShopServiceImplTest {

    @Mock
    private ShopRepository shopRepository;
    
   

    private ShopService ShopService;

    @Before
    public void setUp() throws Exception {
    	
        ShopService = new ShopServiceImpl(shopRepository);
    }

    @Test
    public void shouldSaveNewShop_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedShopShouldBeReturned() throws Exception {
        final Shop savedShop = stubRepositoryToReturnShopOnSave();
        final Shop shop = ShopUtil.addShop();
        final Shop returnedShop = ShopService.save(shop);
        // verify repository was called with shop
        verify(shopRepository, times(1)).save(shop);
        assertEquals("Returned Shop should come from the repository", savedShop, returnedShop);
    }

    private Shop stubRepositoryToReturnShopOnSave() {
        Shop shop = ShopUtil.addShop();
        when(shopRepository.save(any(Shop.class))).thenReturn(shop);
        return shop;
    }

    /*@Test
    public void shouldSaveNewShop_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingShop();
        try {
            ShopService.save(ShopUtil.addShop());
            fail("Expected exception");
        } catch (ShopAlreadyExistsException ignored) {
        }
        verify(shopRepository, never()).save(any(Shop.class));
    }

    private void stubRepositoryToReturnExistingShop() {
        final Shop shop = ShopUtil.addShop();
        when(shopRepository.findOne(shop.getId().toString())).thenReturn(shop);
    }*/

    @Test
    public void shouldListAllShops_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingShops(10);
        Collection<Shop> list = ShopService.getList();
        assertNotNull(list);
        assertEquals(10, list.size());
        verify(shopRepository, times(1)).findAll();
    }

    private void stubRepositoryToReturnExistingShops(int howMany) {
        when(shopRepository.findAll()).thenReturn(ShopUtil.createShopList(howMany));
    }

    @Test
    public void shouldListAllShops_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        stubRepositoryToReturnExistingShops(0);
        Collection<Shop> list = ShopService.getList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(shopRepository, times(1)).findAll();
    }
    
    
}
