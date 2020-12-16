package com.servicenow.client;

import java.io.IOException;
import java.net.http.HttpClient;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1. GET method 
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request 
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpget); //hit the GET URL 
		
//		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
//		System.out.println("Status Code:"+statusCode);
//		
//		//String responseString = EntityUtils.toString(closableHttpResponse.getEntity(), "UFT-8");
//		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());
//		
//		JSONObject responseJson = new JSONObject(responseString);
//		System.out.println("Response JSON from API:"+responseJson);
		return closableHttpResponse;
	}

}
