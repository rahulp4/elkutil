package com.mymm.elk.searchmodel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MakeModel {
	 private Name name;
	 private String desc;
	

	
	public Name getName() {
		return name;
	}



	public void setName(Name name) {
		this.name = name;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public static void main(String args[]) {
		Name name	=	new Name();
		List<String> input	=	new ArrayList<String>();
		input.add("HONDA CIVIC 2000");
		name.setInput(input);
		
		Source source	=	new Source();
		source.setDesc("HONDA CIVIC 2000");
		source.setName(name);
		
		MakeModel makeModel	=	new MakeModel();
//		makeModel.set_id("id11");
//		makeModel.set_index("2");
//		makeModel.set_score(1);
//		makeModel.set_source(source);
//		makeModel.set_type("yearmakemodel");
		ObjectMapper mapper	=	new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(makeModel);
			System.out.println(jsonInString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
