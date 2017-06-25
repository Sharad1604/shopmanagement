package com.shops.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * Shop Model
 * 
 * @author Sharad Raut
 *
 */

@Entity
public class Shop {

	@Id
	private String shopName;
	
	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", shopAddress=" + shopAddress
				+ ", shopPostCode=" + shopPostCode + ", shopLatitude="
				+ shopLatitude + ", shopLongitude=" + shopLongitude + "]";
	}

	private String shopAddress;
	
	private String shopPostCode;

    private double shopLatitude;

	private double shopLongitude;

	public double getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	public double getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopPostCode() {
		return shopPostCode;
	}

	public void setShopPostCode(String shopPostCode) {
		this.shopPostCode = shopPostCode;
	}

	

}
