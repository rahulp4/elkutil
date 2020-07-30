package com.mymm.elk.searchmodel;

public class Source {
	private String desc;
	private Name name;
	@Override
	public String toString() {
		return "Source {desc:" + desc + ", name:" + name + "}";
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

}
