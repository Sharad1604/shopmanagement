package com.shops.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.model.LatLng;
import com.shops.model.Address;
import com.shops.model.Shop;
import com.shops.model.ShopResponse;
import com.shops.repository.ShopsRepository;
import com.shops.util.Util;

/**
 * 
 * ShopsServiceImpl 
 * 
 * @author Sharad Raut
 *
 */

@Service
public class ShopsServiceImpl implements ShopsService{

	private static final Logger LOG = Logger.getLogger(ShopsServiceImpl.class.getName());
	
	@Autowired
	ShopsRepository shopsRepository;
	
	
	/**
	 * Adding or Updating shop
	 * 
	 * 
	 */
	@Override
	@Transactional
	public Object add(Shop shop) {
		LOG.log(Level.INFO, "Adding Shop");
		Shop previousShop=shopsRepository.findByshopName(shop.getShopName());
		if(previousShop!=null){
			
			
			Shop obj=new Shop();
			obj.setShopName(previousShop.getShopName());
			obj.setShopAddress(previousShop.getShopAddress());
			obj.setShopPostCode(previousShop.getShopPostCode());
			obj.setShopLongitude(previousShop.getShopLongitude());
			obj.setShopLatitude(previousShop.getShopLatitude());
			
			ShopResponse shopResponse=new ShopResponse();
			Address previousAddress=new Address();
			previousAddress.setShop(obj);
			Address currentAddress=new Address();
			currentAddress.setShop(shop);
			shopResponse.setCurrentAddress(currentAddress);
			shopResponse.setPreviousAddress(previousAddress);
			shopsRepository.save(shop);
			return  shopResponse;
		}
		else{
			shopsRepository.save(shop);
			return "new shop added";
			
		}
		
		
	}

	
	/**
	 * Get All shops
	 * 
	 * 
	 */
	
	@Override
	public List<Shop> getAll() {
		// TODO Auto-generated method stub
		
		List<Shop> shopList=new ArrayList<Shop>();
		for (Iterator iterator = shopsRepository.findAll().iterator(); iterator.hasNext();) {
			Shop shop = (Shop) iterator.next();
			shopList.add(shop);
		}
		return shopList;
	}

	/**
	 * Logic to find  nearest shop
	 * 
	 * 
	 */
	private Shop findNearest(LatLng geocode,List<Shop> shops) {
		// customer latitude and longitude
		double lat1 = geocode.lat;
		double lon1 = geocode.lng;
		// hold the nearest distance found till now
		double nearestDist = -1;
		// hold the reference to the nearest shop found till now
		Shop nearestShop = null;
		for (Shop shop : shops) {
			// latitude and longitude of the shop to compare
			double lat2 = shop.getShopLatitude();
			double lon2 = shop.getShopLongitude();
			// distance to the shop in comparison
			double dist = Util.haversine(lat1, lon1, lat2, lon2);
			// if the shop in comparison is nearer than the previous shop or if
			// it is the first shop
			if (dist < nearestDist || nearestDist == -1) {
				nearestShop = shop;
				nearestDist = dist;
				LOG.log(Level.INFO, " Shop " + nearestShop.getShopName() + " found at " + nearestDist + " KM");
			}
		}
		return nearestShop;
	}

	
	/**
	 *  find  nearest shop
	 * 
	 * 
	 */
	@Override
	public Shop getShop(LatLng geocode) {
		List<Shop> shopList=new ArrayList<Shop>();
		for (Iterator iterator = shopsRepository.findAll().iterator(); iterator.hasNext();) {
			Shop shop = (Shop) iterator.next();
			shopList.add(shop);
		}
		return findNearest(geocode,shopList);
	}
	
	
}
