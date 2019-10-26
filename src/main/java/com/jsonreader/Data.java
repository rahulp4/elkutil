package com.jsonreader;

import java.util.Arrays;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Data {

	  private String mileage;
	  
	  private String location;
	  private String id;
	  private String category;
	  private String number;
	  private String description;
	  private String descriptionRetail;
	  private Catalog catalog;
	  private Images[] images;
	  public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	private Fit[] fitments_list;
	
	
	private String fitments;
	
@Override
public String toString() {
	return "Data {mileage:" + mileage + ", location:" + location + ", id:" + id + ", category:" + category + ", number:"
			+ number + ", description:" + description + ", descriptionRetail:" + descriptionRetail + ", catalog:"
			+ catalog + ", images:" + Arrays.toString(images) + ", fitments_list:" + Arrays.toString(fitments_list)
			+ ", fitments:" + fitments + ", unitOfMeasureCode:" + unitOfMeasureCode + ", unitOfMeasure:" + unitOfMeasure
			+ ", interchange:" + interchange + ", type:" + type + ", freeShippingEligible:" + freeShippingEligible
			+ ", price:" + price + ", listPrice:" + listPrice + ", corePrice:" + corePrice + ", availability:"
			+ availability + "}";
}
public Fit[] getFitments_list() {
	return fitments_list;
}
public void setFitments_list(Fit[] fitments_list) {
	this.fitments_list = fitments_list;
}
public String getFitments() {
		return fitments;
	}
	public void setFitments(String fitments) {
		this.fitments = fitments;
	}
public Images[] getImages() {
		return images;
	}

	public void setImages(Images[] images) {
		this.images = images;
	}

	//	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)

	
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionRetail() {
		return descriptionRetail;
	}
	public void setDescriptionRetail(String descriptionRetail) {
		this.descriptionRetail = descriptionRetail;
	}
	
	

	

	public String getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}
	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public String getInterchange() {
		return interchange;
	}
	public void setInterchange(String interchange) {
		this.interchange = interchange;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFreeShippingEligible() {
		return freeShippingEligible;
	}
	public void setFreeShippingEligible(String freeShippingEligible) {
		this.freeShippingEligible = freeShippingEligible;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getCorePrice() {
		return corePrice;
	}
	public void setCorePrice(String corePrice) {
		this.corePrice = corePrice;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	private String unitOfMeasureCode;
	  private String unitOfMeasure;
	  private String interchange;
	  private String type;
	  private String freeShippingEligible;
	  private String price;
	  private String listPrice;
	  private String corePrice;
	  private String availability;
}
