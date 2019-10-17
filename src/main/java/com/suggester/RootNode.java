package com.suggester;

public class RootNode {

	private String yearmakemodel_desc;
	private String output;
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	private Suggester suggest;
	@Override
	public String toString() {
		return "RootNode {yearmakemodel_desc:" + yearmakemodel_desc + ", output:" + output + ", suggest:" + suggest
				+ "}";
	}
	public String getYearmakemodel_desc() {
		return yearmakemodel_desc;
	}
	public void setYearmakemodel_desc(String yearmakemodel_desc) {
		this.yearmakemodel_desc = yearmakemodel_desc;
	}
	public Suggester getSuggest() {
		return suggest;
	}
	public void setSuggest(Suggester suggest) {
		this.suggest = suggest;
	}
	
	
}
