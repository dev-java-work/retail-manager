package com.retailmanager.shop.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retailmanager.shop.domain.Shop;
import com.retailmanager.shop.domain.ShopAddress;
import com.retailmanager.shop.domain.ShopVO;
import com.retailmanager.shop.service.ShopService;
import com.retailmanager.shop.service.exception.NoShopExistsException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/*
 * This controller is for creating and searching Shop related operation
 */

@RestController
public class ShopController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);
	private final ShopService shopService;

	@Inject
	public ShopController(final ShopService shopService) {
		this.shopService = shopService;
	}

	@ApiOperation(value = "Add new Shop", notes = "Add Shops to the existing list of shops")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "/shop", method = RequestMethod.POST)
	public Shop addShop(@RequestBody final ShopVO shop) {
		LOGGER.debug("Received request to create the {}", shop);

		// Transform VO to entity as the latitude and longitude is not as a part
		// of post data
		Shop shopNew = new Shop();
		shopNew.setShopName(shop.getShopName());
		ShopAddress address = new ShopAddress();
		address.setPostCode(shop.getShopAddress().getPostCode());
		address.setShopNumber(shop.getShopAddress().getShopNumber());
		shopNew.setShopAddress(address);

		return shopService.save(shopNew);
	}

	@ApiOperation(value = "List Shops", notes = "get list of all shops")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public List<Shop> listShop() {
		LOGGER.debug("Received request to list all shop");

		return shopService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNoShopExistsException(NoShopExistsException e) {
		return e.getMessage();
	}

	/*
	 * Return the nearest Shop from the provided latitude and longitude
	 */
	@ApiOperation(value = "Search the nearest shop", notes = "Search the nearest shop from the provided latitude and longitude")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@RequestMapping(value = "/shop/nearest/{lat}/{lng}", method = RequestMethod.GET)
	public Shop nearestShop(@PathVariable double lat, @PathVariable double lng)  throws NoShopExistsException{
		LOGGER.debug("Received request for nearest Shop");
		

		return shopService.getNearestShop(lat, lng);
	}

}
