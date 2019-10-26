package com.jsonreader;

public class YearMakeModelXLSDTO {

	private int year;
	private String make;
	@Override
	public String toString() {
		return "YearMakeModelXLSDTO {year:" + year + ", make:" + make + ", model:" + model + "}";
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private String model;
}
