package com.suggester;

import java.util.List;

public class Contexts {

	private List<String> yearmakemodel;

	@Override
	public String toString() {
		return "Contexts {yearmakemodel:" + yearmakemodel + "}";
	}

	public List<String> getYearmakemodel() {
		return yearmakemodel;
	}

	public void setYearmakemodel(List<String> yearmakemodel) {
		this.yearmakemodel = yearmakemodel;
	}

}
