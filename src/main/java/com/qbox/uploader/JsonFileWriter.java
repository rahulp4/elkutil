package com.qbox.uploader;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
	FileWriter fw = null;
	
	public void openFileWriter(String filePathName) throws IOException {
		try {
			fw = new FileWriter(filePathName);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void writeFile2(String data) throws IOException {
		
	 
		for (int i = 0; i < 10; i++) {
			fw.write(data);
			fw.write('\n');
		}
	 
	}

	public void closFileWriter() throws Exception {
		try {
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		String filePathName	=	"";
		JsonFileWriter file	=	new JsonFileWriter();
		try {
			file.openFileWriter(filePathName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
