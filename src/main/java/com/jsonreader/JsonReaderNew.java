package com.jsonreader;
//https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=&make=ACURA&model=
/**
 * Year = blank
 * Model = blank
 * Make	=	"ACURA"
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.MongoClt;
import com.mongodb.DuplicateKeyException;
import com.util.ReSTUtil;

public class JsonReaderNew {
	final static Logger logger = Logger.getLogger(JsonReaderNew.class);

	private static long GAP_YEARMAKEMODEL	=	3000;//10 Seconds
	private static long GAP_PARTDETAILS	=	3000;//10 Seconds
	
	
	public MongoClt clt	=	new MongoClt();
	//public String baseFileDir	=	"D:\\knowledgebase\\myproject\\twilio\\documents\\DataFull\\TestPulledData\\";
	public String baseFileDir	=	"/var/config/DataPul/vdata/";	  
  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    //InputStream is = new URL(url).openStream();
    String jsonText = null;
    try {
    	try {
    		Thread.sleep(GAP_YEARMAKEMODEL);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	jsonText = ReSTUtil.sendGet(url);
//    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//      jsonText = readAll(rd);
      if(jsonText.equals("")) {
    	return null;  
      } else {
    	logger.info(jsonText);
      JSONObject json = new JSONObject(jsonText);
      return json;
      }
    } finally {
      //is.close();
    }
  }
 
  public static String readJsonFromUrlReturnStr(String url) throws IOException, JSONException {
   //InputStream is = new URL(url).openStream();
   String jsonText = null;
   try {
   	try {
		Thread.sleep(GAP_PARTDETAILS);
	} catch (Exception e) {
		e.printStackTrace();
	}

     //BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
     //jsonText = readAll(rd);
     jsonText = ReSTUtil.sendGet(url);
     //logger.info(jsonText);
    return jsonText;
   } catch (Exception e){
	   e.printStackTrace();
	   logger.info("Error reading for given url..Continue");
   }finally {
     //is.close();
   }
   return jsonText;
 }
  
  public static void main(String[] args) throws IOException, JSONException {
	  if(true) {
		  String fileName	=	"2010LAND ROVERLR2_11";
		  String finalFileName	=	StringUtils.remove(fileName, ' ');
		  String t	=	"2010LEXUSLS600/LS600H/LS600HL";
		  String newFileName	=	StringUtils.remove(t, '/');
		  System.out.println(newFileName);
//		  JsonReaderNew reader	=	new JsonReaderNew();
//		  reader.searchLocal("", "", "", "");
//		  String make="HONDA";
//		  String year="2010";
//		  String model="SANTA FE";
//		  String tobeEncoded	=	year+"&make="+make+"&model="+model;
//
//		  String encoded	=	encodeURI(tobeEncoded);
//		  //String url	=	"https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2010&make=HYUNDAI&model=SANTA FE";;
//		   String url = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year="+encoded;
//
//		 
//
//
//		  logger.info("Testing "+url);
		  System.out.println("Testing "+finalFileName);
	  } else {
	 
	  String make="HONDA";
	  String year="2016";
	  String model="PILOT";
	  List<YearMakeModelXLSDTO> yearmakemodelList	=	null;
	  JsonReaderNew reader	=	new JsonReaderNew();
	  reader.clt.getConnection("");
	  logger.info("Initilizing the XLS for year make model");
	  YearMakeModelReaderFromXLS yearMakeModel	=	new YearMakeModelReaderFromXLS();
	  int intCount	=	0;
	  try {
		  
		  yearmakemodelList	=	yearMakeModel.getYearMakeModelList();
		  for (YearMakeModelXLSDTO yearMakeModelXLSDTO : yearmakemodelList) {
				make	=	yearMakeModelXLSDTO.getMake();
				model	=	yearMakeModelXLSDTO.getModel();
				year	=	yearMakeModelXLSDTO.getYear()+"";
				logger.info("Current Year MAke MOdel "+year+"-"+make+"-"+model);
				reader.startMain(make, year, model);	
				intCount++;
		  }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  }
  }
  public void startMain(String make,String year,String model) throws IOException, JSONException {
 //62445
	  String tobeEncoded	=	year+"&make="+make+"&model="+model;

	  String encoded	=	encodeURI(tobeEncoded);

	  String url = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year="+encoded;
	  //String url = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2010&make=LAND%20ROVER&model=LR2";
 
	  
 boolean islocal	=	false;
 if(islocal) {
	 searchLocal("", "", "", "");
 } else {
	 int i=0;
	 for(String str:fetchPartType(url) ) {
		 if(str==null) {
			 
		 } else {
			 
			 i++;
			 //if(str.contains("Axl") && i==3) {
			 logger.info("before "+str);
			 str=str.replaceAll("\\|", "%7C");
			 logger.info("After "+str);
			 logger.info("Searching for YEAR MAKE MODEL AND OPTION "+year+"-"+make+" "+model+" "+str);
			 search(replaceSpaces(str.toCharArray()), make,model,year);
		 }
	 //}
	 }
 }
  }
 
  private String [] search(String partType,String make,String model,String year) throws IOException, JSONException  {
 
	  
	  //String tobeEncoded	=	year+"&make="+make+"&model="+model;
	  String tobeEncoded	=	"year="+year+"&make="+make+"&model="+model+"&category="+partType+"&skip=0&take=30";
	  		
	  String encoded	=	encodeURI(tobeEncoded);
	  //String url	=	"https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2010&make=HYUNDAI&model=SANTA FE";;
	   String srcURL = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year="+encoded;
	  
		  
		  String url_WORK = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";
		  
		  String url_1 = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";

		  String uri	=	"year="+encodeValue(year)+"&make="+encodeValue(make)+"&model="+encodeValue(model)+"&partType="+partType;

		  String srcURL2 ="https://www.lkqonline.com/api/catalog/0/ProductOption?"+uri;
		  srcURL	=	srcURL2;
	   logger.info("URL for OPtion search is "+srcURL);
	   
//	  String encodedModel	=	encodeValue(model);
	   //String srcURL ="https://www.lkqonline.com/api/catalog/0/product?catalogId=0&year="+year+"&make="+make+"&model="+model+"&category="+partType+"&skip=0&take=30";
	   //String srcURL ="https://www.lkqonline.com/api/catalog/0/product?catalogId=0&"+
	   
 
 
 String fileNameForSave	=	year+make+model;
 
 String data = readJsonFromUrlReturnStr(srcURL);
 
 logger.info("Read Data for the given option is "+data);
 if(data==null) {
	 return null;
 }
   //logger.info(data);
   Root jsondata	=	new Root();
   ObjectMapper mapper	=	new ObjectMapper();
   //mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   try {
	   jsondata	= mapper.readValue(data, Root.class);
	   getFitmens(jsondata);
	   processAndSaveInFile(jsondata,fileNameForSave,partType);
   } catch (Exception e) {
	   e.printStackTrace();
	   logger.info("There was error in reading the option for "+year+" "+make+" "+model+" "+partType+"  Continue for next part type");
   }
   //logger.info(((Fit)((Data)(jsondata.getData()[0])).getFitments()[0]).Model);
   logger.info(jsondata);
   
  // JSONArray s= (JSONArray)json.get("data");
   return null;
 }

  private String [] searchLocal(String partType,String make,String model,String year) throws IOException, JSONException  {
	  
	  //String srcURL ="https://www.lkqonline.com/api/catalog/0/product?catalogId=0&year="+year+"&make="+make+"&model="+model+"&category="+partType+"&skip=0&take=30";
	  String srcURL ="https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2010&make=LAND%20ROVER&model=LR2";
	  
	  String url_WORK = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";
	  
	  String url_1 = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";

	  String uri	=	"year="+year+"&make="+make+"&model="+model+"&partType="+partType;

	  String srcURL2 ="https://www.lkqonline.com/api/catalog/0/ProductOption?"+encodeURI(uri);
	  srcURL	=	srcURL2;
	  logger.info(srcURL);
	  
	  String fileNameForSave	=	year+make+model;

	  logger.info("\n*******************NEW******************");

	    Root jsondata	=	new Root();
	    ObjectMapper mapper	=	new ObjectMapper();
	    //mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    //mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

	 		
	 		  File f = new File("D:\\knowledgebase\\myproject\\ELKFileUpload\\test.json");
	 		  FileInputStream fis = new FileInputStream(f); byte[] buffer = new byte[10];
	 		  StringBuilder sb = new StringBuilder(); while (fis.read(buffer) != -1) {
	 		 sb.append(new String(buffer)); buffer = new byte[10]; } fis.close();
	 		 
	 
	    String content = sb.toString();
	    String finalStr	=	content.replace("\\", "");//StringEscapeUtils.escapeJava(content);
	    jsondata	= mapper.readValue(content, Root.class);
	    getFitmens(jsondata);
	    processAndSaveInFile(jsondata,fileNameForSave,partType);
//	    Data data1 =	(Data)jsondata.getData()[0];
//	    logger.info(data1.getFitments());
//	    Fit[] fi	=	mapper.readValue(data1.getFitments(), Fit[].class);
//	    logger.info(fi[1]);
//	    logger.info("\n*******************END******************");
	   
	    return null;
	  }
  
  public void getFitmens(Root root) {
	  Data dataList[]	=	root.getData();
	  for (Data data : dataList) {
		String fitmentStr	=	data.getFitments();
		  ObjectMapper mapper	=	new ObjectMapper();
		  try {
			  Fit[] fi	=	mapper.readValue(fitmentStr, Fit[].class);
			  //logger.info(fi[1]);
			  data.setFitments_list(fi);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	  
  }
  public void processAndSaveInFile(Root root,String baseFileName,String partType) {
	  Data dataList[]	=	root.getData();
	  int fileNameCount	=	0;
	  for (Data data : dataList) {
		  try {
			  Fit[] fiList	=	data.getFitments_list();
			  for (Fit fit : fiList) {
				  getDataRow(data,fit,baseFileName+"_"+fileNameCount,partType);
				  fileNameCount++;
			}
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	  
  }
  public void getDataRow(Data data,Fit fit,String fileName,String partType){
	  DataRow dataRow	=	new DataRow();
	  
	  String sheetYearStr	=	fit.Year;
	  String sheetMakeStr	=	fit.Make;
	  String sheetModelStr	=	fit.Model;
	  String descriptionStr	=	fit.Description;
	  
	  if(sheetYearStr==null) {
		  sheetYearStr	=	fit.SystemYear;
	  }
	  if(sheetMakeStr==null) {
		  sheetMakeStr	=	fit.SystemMake;
	  }

	  if(sheetModelStr==null) {
		  sheetModelStr	=	fit.SystemModel;
	  }
	  
	  if(descriptionStr==null) {
		  descriptionStr	=	fit.WebDescription;
	  }

	  String number	=	data.getNumber();
	  String interchange	=	data.getInterchange();
	  String sheetCategory	=	data.getCategory();
	  if(sheetCategory==null) {
		  //String decoded = URLDecoder.decode(partType, "UTF-8");
		  try {
			  sheetCategory	=	URLDecoder.decode(partType, "UTF-8");
		  } catch (Exception e) {
			  sheetCategory	=	partType;
		  }
	  }
//	  String modifiedSheetCategory	=	sheetCategory.replaceAll("|", " ");
	  String modifiedSheetCategory	=		  org.apache.commons.lang.StringUtils.replace(sheetCategory, "|", "");
	  String category	=	modifiedSheetCategory;
	  String sheetYearmakemodel_desc	=	sheetYearStr+" "+sheetMakeStr+" "+sheetModelStr;
	  String sheetYearmakemodel_with_space	=	sheetYearStr+sheetMakeStr+sheetModelStr;
	  String sheetYearmakemodel	=	StringUtils.remove(sheetYearmakemodel_with_space, ' ');
	  
	  if(descriptionStr==null) {
		  descriptionStr	=	"";
	  }
	  String sheetInput	=	category+" "+descriptionStr;
	  
	  dataRow.setSheetCategory(modifiedSheetCategory);
	  dataRow.setCategory(modifiedSheetCategory);
	  dataRow.setInterchange(interchange);
	  dataRow.setNumber(number);
	  dataRow.setSheetInput(sheetInput);
	  dataRow.setSheetMakeStr(sheetMakeStr);
	  dataRow.setSheetModelStr(sheetModelStr);
	  dataRow.setSheetYearmakemodel(sheetYearmakemodel);
	  dataRow.setSheetYearmakemodel_desc(sheetYearmakemodel_desc);
	  dataRow.setSheetYearStr(sheetYearStr);
	  logger.info("\nData Is ************");

	  logger.info(sheetCategory);
	  logger.info(dataRow);

	  try {
		  insertDoc(dataRow);
		  saveToFile(dataRow, fileName);
		  logger.info("\nData Inserted************");
	  } catch (DuplicateKeyException e) {
		  logger.info("Duplicate Key for "+dataRow);
	  } catch (Exception e) {
		  logger.info("Error while saving to Mongo");
		  saveToFile(dataRow, fileName);
		  logger.info("\nData Inserted************");
	  }
	  
//	  String sheetSubCategory	=	null;
//	  String sheetSubCat2	=	null;
//	  String sheetPart	=	null;

  }
  

public void saveToFile(DataRow data,String fileName){
	ObjectMapper mapper	=	new ObjectMapper();  
	try {
		
		String finalFileName	=	StringUtils.remove(fileName, ' ');
		String newFileName	=	StringUtils.remove(finalFileName, '/');
		mapper.writeValue(new File(baseFileDir+newFileName+".json"), data);
		
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }
  
  public void insertDoc(DataRow dataRow) throws Exception {

		clt.insertDoc(dataRow);
  }
  private static String [] fetchPartType(String url) throws IOException, JSONException  {

	  logger.info("Reading fomr URL ..YEAR-MAKE-MODEL - url "+url);  
	  JSONObject json = readJsonFromUrl(url);
  
  
	  logger.info("Response for YEAR MAKE MODEL "+json);
	  if(json==null) {
		  String s[]	=	{"",""};
		  return null;
	  } else {
		  JSONArray s= (JSONArray)json.get("data");
		  return toStringArray(s);
	  }
  }
 

 
  public static String[] toStringArray(JSONArray array) {
   if(array==null)
       return null;

   String[] arr=new String[array.length()];
   for(int i=0; i<arr.length; i++) {
       arr[i]=array.optString(i);
   }
   return arr;
}
  static String replaceSpaces(char[] str)
  {

      // count spaces and find current length
      int space_count = 0, i = 0;
      for (i = 0; i < str.length; i++)
          if (str[i] == ' ')
              space_count++;

      // count spaces and find current length
      while (str[i - 1] == ' ')
      {
          space_count--;
          i--;
      }

      // Find new length.
      int new_length = i + space_count * 2;

      // New length must be smaller than length
      // of string provided.
      if (new_length > 10000)
          return String.valueOf(str);

      // Start filling character from end
      int index = new_length - 1;

      char[] new_str = str;
      str = new char[new_length];

      // Fill rest of the string from end
      for (int j = i - 1; j >= 0; j--)  
      {

          // inserts %20 in place of space
          if (new_str[j] == ' ')  
          {
              str[index] = '0';
              str[index - 1] = '2';
              str[index - 2] = '%';
              index = index - 3;
          }  
           
          else
          {
              str[index] = new_str[j];
              index--;
          }
      }
      return String.valueOf(str);
  }

  private static String encodeURI(String queryString) throws URIException {
	  String str	=	URIUtil.encodeQuery(queryString);
	  return str;
  }
  private static String encodeValue(String value) {
      try {
    	  
          return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
      } catch (UnsupportedEncodingException ex) {
          throw new RuntimeException(ex.getCause());
      }
  }
 
}
