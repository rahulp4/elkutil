package com.mymm.elk.searchmodel;

import java.util.List;

public class Name {

	private List<String> input;

	@Override
	public String toString() {
		return "Name {input:" + input + "}";
	}

	public List<String> getInput() {
		return input;
	}

	public void setInput(List<String> input) {
		this.input = input;
	}
}
