package com.shops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.shops.geocode.GeocodeService;
import com.shops.model.Shop;
import com.shops.service.ShopsService;

/**
 * the controller for all the REST enpoints
 * 
 * @author Sharad Raut
 *
 */
@RestController
public class ShopsController {

	/**
	 * the google geocoding service
	 */
	@Autowired
	@Qualifier("geocodeService")
	private GeocodeService geocodeService;

	/**
	 * the shopsService 
	 */

	@Autowired
	private ShopsService shopsService;
	
	/**
	 * REST endpoint for adding a shop
	 * 
	 * @param shop
	 */
	
	
	
	@RequestMapping(path = "/shop", method = RequestMethod.POST)
	public Object addShop(@RequestBody Shop shop) {
		LatLng geocode = geocodeService.getGeocode(shop);
		if (null != geocode) {
			shop.setShopLatitude(geocode.lat);
			shop.setShopLongitude(geocode.lng);
		}
		return shopsService.add(shop);
	}

	/**
	 * REST endpoint to find the nearest shop from a location marked with
	 * latitude and longitude
	 * 
	 * @param latitude,
	 *            the latitude of the customer
	 * @param longitude,
	 *            the longitude of the customer
	 * @return the nearest shop
	 */
	@RequestMapping(path = "/shop/{latitude}/{longitude}", method = RequestMethod.GET)
	public Shop getShop(@PathVariable double latitude, @PathVariable double longitude) {
		LatLng geocode = new LatLng(latitude, longitude);
		Shop shop = shopsService.getShop(geocode);
		return shop;
	}

	/**
	 * REST endpoint for a default request without the customer latitude and
	 * longitude
	 * 
	 * @return returns all the registered shops
	 */
	@RequestMapping(path = "/shop", method = RequestMethod.GET)
	public List<Shop> getShops() {
		return shopsService.getAll();
	}

}
