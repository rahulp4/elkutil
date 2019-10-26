package com.jsonreader;

public class Fit {

	public String Year;
	public String Make;
	public String Model;
	public String Description;
	
	public String SystemYear;
	public String SystemMake;
	public String SystemModel;
	public String WebDescription;
	
	public Fit() {
		
	}

	@Override
	public String toString() {
		return "Fit {Year:" + Year + ", Make:" + Make + ", Model:" + Model + ", Description:" + Description
				+ ", SystemYear:" + SystemYear + ", SystemMake:" + SystemMake + ", SystemModel:" + SystemModel
				+ ", WebDescription:" + WebDescription + "}";
	}

}