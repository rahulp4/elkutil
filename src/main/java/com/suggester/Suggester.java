package com.suggester;

import java.util.Arrays;
import java.util.List;

public class Suggester {
	private List<String> input;
	@Override
	public String toString() {
		return "Suggester {input:" + input + ", contexts:" + contexts + "}";
	}

	public List<String> getInput() {
		return input;
	}

	public void setInput(List<String> input) {
		this.input = input;
	}

	private Contexts contexts;
	
	public Contexts getContexts() {
		return contexts;
	}

	public void setContexts(Contexts contexts) {
		this.contexts = contexts;
	}

}
