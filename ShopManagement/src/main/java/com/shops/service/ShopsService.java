package com.shops.service;

import java.util.List;

import com.google.maps.model.LatLng;
import com.shops.model.Shop;

/**
 * 
 * ShopsService Interface
 * 
 * @author Sharad Raut
 *
 */

public interface ShopsService {

	public Object add(Shop shop);

	public List<Shop> getAll();

	public Shop getShop(LatLng geocode);

}
