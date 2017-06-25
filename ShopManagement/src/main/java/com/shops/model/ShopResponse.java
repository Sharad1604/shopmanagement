package com.shops.model;

/**
 * 
 * ShopResponse Model
 * 
 * @author Sharad Raut
 *
 */


public class ShopResponse {
	
	private Address currentAddress;
	
	private Address previousAddress;

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Address getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(Address previousAddress) {
		this.previousAddress = previousAddress;
	}

	

}
