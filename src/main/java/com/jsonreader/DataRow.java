package com.jsonreader;

public class DataRow {
	  @Override
	public String toString() {
		return "DataRow {category:" + category + ", number:" + number + ", interchange:" + interchange
				+ ", sheetYearStr:" + sheetYearStr + ", sheetMakeStr:" + sheetMakeStr + ", sheetModelStr:"
				+ sheetModelStr + ", sheetCategory:" + sheetCategory + ", sheetYearmakemodel_desc:"
				+ sheetYearmakemodel_desc + ", sheetYearmakemodel:" + sheetYearmakemodel + ", sheetInput:" + sheetInput
				+ ", sheetSubCategory:" + sheetSubCategory + ", sheetSubCat2:" + sheetSubCat2 + ", sheetPart:"
				+ sheetPart + ", input:" + input + ", type:" + type + ", corePrice:" + corePrice + ", price:" + price
				+ "}";
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
	public String getInterchange() {
		return interchange;
	}
	public void setInterchange(String interchange) {
		this.interchange = interchange;
	}
	public String getSheetYearStr() {
		return sheetYearStr;
	}
	public void setSheetYearStr(String sheetYearStr) {
		this.sheetYearStr = sheetYearStr;
	}
	public String getSheetMakeStr() {
		return sheetMakeStr;
	}
	public void setSheetMakeStr(String sheetMakeStr) {
		this.sheetMakeStr = sheetMakeStr;
	}
	public String getSheetModelStr() {
		return sheetModelStr;
	}
	public void setSheetModelStr(String sheetModelStr) {
		this.sheetModelStr = sheetModelStr;
	}
	public String getSheetCategory() {
		return sheetCategory;
	}
	public void setSheetCategory(String sheetCategory) {
		this.sheetCategory = sheetCategory;
	}
	public String getSheetYearmakemodel_desc() {
		return sheetYearmakemodel_desc;
	}
	public void setSheetYearmakemodel_desc(String sheetYearmakemodel_desc) {
		this.sheetYearmakemodel_desc = sheetYearmakemodel_desc;
	}
	public String getSheetYearmakemodel() {
		return sheetYearmakemodel;
	}
	public void setSheetYearmakemodel(String sheetYearmakemodel) {
		this.sheetYearmakemodel = sheetYearmakemodel;
	}
	public String getSheetInput() {
		return sheetInput;
	}
	public void setSheetInput(String sheetInput) {
		this.sheetInput = sheetInput;
	}
	public String getSheetSubCategory() {
		return sheetSubCategory;
	}
	public void setSheetSubCategory(String sheetSubCategory) {
		this.sheetSubCategory = sheetSubCategory;
	}
	public String getSheetSubCat2() {
		return sheetSubCat2;
	}
	public void setSheetSubCat2(String sheetSubCat2) {
		this.sheetSubCat2 = sheetSubCat2;
	}
	public String getSheetPart() {
		return sheetPart;
	}
	public void setSheetPart(String sheetPart) {
		this.sheetPart = sheetPart;
	}
	String category	=	null;
	  String number	=	null;
	  String interchange	=	null;

	  
	  String sheetYearStr	=	null;
	  String sheetMakeStr	=	null;
	  String sheetModelStr	=	null;
	  String sheetCategory	=	null;
	  String sheetYearmakemodel_desc	=	null;
	  String sheetYearmakemodel	=	null;
	  String sheetInput	=	null;
	  String sheetSubCategory	=	null;
	  String sheetSubCat2	=	null;
	  String sheetPart	=	null;
	  String input	=	null;
	  
	  
	  String type=	null;
	  String corePrice	=	null;
	  String price=	null;
	  
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCorePrice() {
		return corePrice;
	}
	public void setCorePrice(String corePrice) {
		this.corePrice = corePrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
}
