package com.jsonreader;

import java.util.Arrays;

public class Root {

	private Data data[];

	@Override
	public String toString() {
		return "Root {data:" + Arrays.toString(data) + "}";
	}

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}
	
}
