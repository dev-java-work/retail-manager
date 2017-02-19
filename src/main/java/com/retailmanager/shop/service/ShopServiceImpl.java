package com.retailmanager.shop.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.repository.ShopRepository;
import com.retailmanager.shop.service.exception.NoShopExistsException;


@Service
@Validated
public class ShopServiceImpl implements ShopService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);
    private final ShopRepository repository;
 
    private Properties properties = null;

    
    @Value("${shop.google_api_key}")
    private String google_api_key;

    
    @Inject
    public ShopServiceImpl(final ShopRepository repository) {
        this.repository = repository;
        
        //Reading the API Key from properties file
        InputStream is = null;
        try {
            this.properties = new Properties();
            is = this.getClass().getResourceAsStream("/shop.properties");
            properties.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    @Transactional
    public Shop save(@NotNull @Valid Shop shop) {
        LOGGER.debug("Creating {}", shop);
        GeoApiContext context = new GeoApiContext().setApiKey(properties.getProperty("google_api_key"));
        
        GeocodingResult[] results = null;
		try {
			results = GeocodingApi.geocode(context,
			    shop.getShopAddress().getPostCode()).await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		LOGGER.debug("Lat { }",results[0].geometry.location.lat);
        shop.setLatitute(results[0].geometry.location.lat);
        shop.setLongitude(results[0].geometry.location.lng);
        LOGGER.debug("Final shop before save {}",shop);
        return repository.save(shop);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Shop> getList() {
        LOGGER.debug("Retrieving the list of all shops");
        return repository.findAll();
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Shop getNearestShop(double lat,double lng) throws NoShopExistsException{
        LOGGER.debug("Retrieving the nearest shop");
        List<Shop> shopLst = repository.findAll();
        TreeMap<Double,Shop> distanceMap = new TreeMap<Double,Shop>();
        if(shopLst.size()==0){
        	
        	throw new NoShopExistsException("No Shop Exists");
        }
        for(Shop shop :shopLst ){
        	double distance = distance(shop.getLatitute(),shop.getLongitude(),lat,lng,"K");
        	distanceMap.put(distance,shop);
        }
        
        LOGGER.debug("MAP"+distanceMap);
        
       Double firstKey = distanceMap.firstKey();
       Shop selectedShop = distanceMap.get(firstKey);
        
        return selectedShop;
    }
    
    
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
    
    
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	
}
