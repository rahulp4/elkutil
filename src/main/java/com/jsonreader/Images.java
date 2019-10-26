package com.jsonreader;

public class Images {
	private String size;
	private String url;
	private String type;

	public Images() {
		
	}
	@Override
	public String toString() {
		return "Images {size:" + size + ", url:" + url + ", type:" + type + "}";
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
