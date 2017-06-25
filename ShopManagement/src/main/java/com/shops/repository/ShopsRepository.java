package com.shops.repository;

import org.springframework.data.repository.CrudRepository;

import com.shops.model.Shop;

/**
 * 
 * ShopsRepository
 * 
 * @author Sharad Raut
 *
 */
public interface ShopsRepository  extends CrudRepository<Shop,String>{

	Shop findByshopName(String shopName);
}
