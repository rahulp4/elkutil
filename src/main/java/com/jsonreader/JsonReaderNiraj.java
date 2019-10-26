package com.jsonreader;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReaderNiraj {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      System.out.println(jsonText);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
 
  public static String readJsonFromUrlReturnStr(String url) throws IOException, JSONException {
   InputStream is = new URL(url).openStream();
   try {
     BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
     String jsonText = readAll(rd);
     System.out.println(jsonText);
    return jsonText;
   } finally {
     is.close();
   }
 }

  public static void main(String[] args) throws IOException, JSONException {
 
 String make="KIA";
 String year="2010";
 String model="SORENTO";
// String test ="Axle Parts";
// System.out.println(replaceSpaces(test.toCharArray()));
 String url = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year="+2017+"&make="+make+"&model="+model;
 int i=0;
    for(String str:fetchPartType(url) ) {
    i++;
    if(str.contains("Axl") && i==3) {
    System.out.println("before "+str);
    str=str.replaceAll("\\|", "%7C");
    System.out.println("After "+str);
    search(replaceSpaces(str.toCharArray()), make,model,year);
    //https://www.lkqonline.com/api/catalog/0/product?catalogId=0&year=2017&make=ACURA&model=MDX&category=Axle%20%2F%20Suspension%7CAxle%20Parts%7CAxle%20Shaft&sort=closestFirst&skip=0&take=12&latitude=41.85003&longitude=-87.65005
    //https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2017&make=ACURA&model=MDX&category=Axle%20/%20Suspension%7CAxle%20Parts%7CAxle%20Shaft
   
    }
    }
   
    //https://www.lkqonline.com/api/catalog/0/ProductOption?year=2017&make=ACURA&model=MDX&partType=Engine%20Compartment%7CEngine%20Assembly
   
   // System.out.println(json.get("id"));
  }
  private static String encodeURI(String queryString) throws URIException {
	  String str	=	URIUtil.encodeQuery(queryString);
	  return str;
  }

  private static String [] search(String partType,String make,String model,String year) throws IOException, JSONException  {
 
 String srcURL ="https://www.lkqonline.com/api/catalog/0/product?catalogId=0&year="+year+"&make="+make+"&model="+model+"&category="+partType+"&skip=0&take=30";
 System.out.println(srcURL);

 //String tobeEncoded	=	year+"&make="+make+"&model="+model;
 String tobeEncoded	=	"year="+year+"&make="+make+"&model="+model+"&category="+partType+"&skip=0&take=30";
 		
 String encoded	=	encodeURI(tobeEncoded);
 //String url	=	"https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year=2010&make=HYUNDAI&model=SANTA FE";;
  //String srcURL = "https://www.lkqonline.com/api/catalog/0/productcategory?catalogId=0&name=Category&year="+encoded;
 
	  
	  String url_WORK = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";
	  
	  String url_1 = "https://www.lkqonline.com/api/catalog/0/ProductOption?year=2010&make=KIA&model=BORREGO&partType=Axle / Suspension|Axle Parts|Carrier Assembly";

	  String uri	=	"year="+year+"&make="+make+"&model="+model+"&partType="+partType;

	  String srcURL2 ="https://www.lkqonline.com/api/catalog/0/ProductOption?"+encodeURI(uri);
	  srcURL	=	srcURL2;
 
 String data = readJsonFromUrlReturnStr(srcURL);
   System.out.println(data);
  // JSONArray s= (JSONArray)json.get("data");
   return null;
 }

  private static String [] fetchPartType(String url) throws IOException, JSONException  {

  JSONObject json = readJsonFromUrl(url);
   System.out.println(json);
   JSONArray s= (JSONArray)json.get("data");
   return toStringArray(s);
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
 
 
}
