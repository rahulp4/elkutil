package com.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.name.service.client.vo.UserVO;
//import com.name.service.module.dto.ReSTResponseDTO;

public class ReSTUtil {
		static final String USER_AGENT = "Mozilla/5.0";
	
		public static String sendPost(String postJSONString,String restURL) throws Exception {
			
			
			int http_response_code	=	200;
			String url = restURL;//"http://localhost:8081/securedwebiot/onboardusersv2";
			//String url = "http://rahulp:password1@localhost:8081/securedwebiot/response";
			//String url = "https://cwdcpwoe8j.execute-api.us-east-1.amazonaws.com/onboardusers";
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			post.setHeader("User-Agent", USER_AGENT);
			
			post.addHeader("content-type", "application/json");
			String str	=	"1fd0748f43709cb08803:e4fd5698e6";
			String coded	=	Base64.encodeBase64String(str.getBytes());
			post.setHeader("Authorization","Basic " + coded);
			
//			UserVO demo	=	new UserVO();
//			demo.setEmail("rahulgpoddar@yahoo.com");
//			demo.setName("Ghanshyam");
//			demo.setMiddle_name("Poddar");
//			demo.setMobileNo("+917709001336");
//			demo.setSub("d23717ff-b9cf-43fa-b1b9-def6dc047116");

			ObjectMapper mapper = new ObjectMapper();
			//mapper.writeValueAsString(demo);
			String jsonData = postJSONString;

//			String strJSON	=	getPostData(demo);
			StringEntity userEntity = new StringEntity(jsonData);
			post.setEntity(userEntity);
			
			
			//post.setEntity(new UrlEncodedFormEntity(urlParameters));


			HttpResponse response = client.execute(post);
			http_response_code	=	response.getStatusLine().getStatusCode();
			BufferedReader rd = new BufferedReader(
	                        new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

//			System.out.println("Done with response code as "+http_response_code);
//			System.out.println("Done with response Result "+result);
			return result.toString();
		}


		public static String  sendGet(String url) {
			String output	=	"";
			String returnOutput	=	"";
			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet(url);
				getRequest.addHeader("accept", "application/json");

				HttpResponse response = httpClient.execute(getRequest);

				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					   + response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(
		                         new InputStreamReader((response.getEntity().getContent())));
				StringBuffer result = new StringBuffer();

				//System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					//System.out.println(output);
					result.append(output); 
				}

				httpClient.getConnectionManager().shutdown();
				returnOutput	=	result.toString();	
				
			  } catch (ClientProtocolException e) {
			
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }

			return returnOutput;

			}

	
}
