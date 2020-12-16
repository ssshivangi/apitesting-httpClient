package com.servicenow.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.servicenow.base.TestBase;
import com.servicenow.client.RestClient;
import com.servicenow.util.TestUtil;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closableHttpResponse;	
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
	}
	
	/////////////////////////
	//TestCase- 1
	//API call= GET
	//Description= Search for repositories with "github" in the name and sort it by the highest combined number of reactions and comments and order decreasingly
	//Validation= Status code validation, Name validation 
	////////////////////////
		
	@Test
	public void getRepoGithub() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("searchGithubURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
				 
		String full_name = TestUtil.getValueByJPath(responseJson, "/items[0]/full_name");
		boolean match = full_name.contains("github");
		Assert.assertEquals(match, true, "Name doesn't contain github");
		
	}
	
	/////////////////////////
	//TestCase- 2
	//API call= GET
	//Description= Search for public repositories owned by GitHub and sorted by ascending author date.
	//Validation= Status code validation, Name validation, scope validation 
	////////////////////////
	
	@Test
	public void getPublicRepo() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("seachPublicRepoURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
		
		//get the value from JSON array
		String full_name = TestUtil.getValueByJPath(responseJson, "/items[0]/full_name");
		boolean match = full_name.contains("github");
		Assert.assertEquals(match, true, "Name doesn't contain github");
		
		String scope = TestUtil.getValueByJPath(responseJson, "/items[0]/private");
		Assert.assertEquals(Boolean.parseBoolean(scope), false, "Repository is private");
		
	}
	
	/////////////////////////
	//TestCase- 3
	//API call= GET
	//Description= Search for repositories matches repositories with exactly 500 stars, that are written in Java and sorted by most recently updated date and order decreasingly.
	//Validation= Status code validation, validation of no. of starts to repository, Validation of language  
	////////////////////////
	
	@Test
	public void getRepoStarts() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("searchStartCountRepoURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
		
		//get the value from JSON array
		String stars_count = TestUtil.getValueByJPath(responseJson, "/items[1]/stargazers_count");
		Assert.assertEquals(Integer.parseInt(stars_count), 500, "Repository doesn't contain 500 starts");
		
		String language = TestUtil.getValueByJPath(responseJson, "/items[1]/language");
		Assert.assertEquals(language, "Java", "Language is not in Java");
		
	}
	
	/////////////////////////
	//TestCase- 4
	//API call= GET
	//Description= Search for repositories with 500 followers mentioning the word "node" and sorted by committer date and order descending.
	//Validation= Status code validation, Validation of no. of followers of repository  
	////////////////////////
	
	@Test
	public void getRepoNoOfFollowers() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("seachFollowersRepoURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
		
		//get the value from JSON array
		String followers = TestUtil.getValueByJPath(responseJson, "/items[1]/watchers_count");
		Assert.assertEquals(Integer.parseInt(followers), 500, "Repository doesn't contain 500 followers");
		
	}
	
	/////////////////////////
	//TestCase- 5
	//API call= GET
	//Description= Search for repositories that are 30 MB and sorted by most thumbs up reactions.
	//Validation= Status code validation, Validation of repository size of 30MB  
	////////////////////////
	
	@Test
	public void getRepoSize() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("seachRepoSizeURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
		
		//get the value from JSON array
		String size = TestUtil.getValueByJPath(responseJson, "/items[1]/size");
		Assert.assertEquals(Integer.parseInt(size), 30000, "Repository size is not 30 MB");
		
	}
	
	/////////////////////////
	//TestCase- 6
	//API call= GET
	//Description= Search for repositories mentioning "cucumber" in the repository's README file and sorted by most thumbs down reactions.
	//Validation= Status code validation 
	////////////////////////
	
	@Test
	public void getRepoWithWordInReadMe() throws ClientProtocolException, IOException{
		apiUrl =prop.getProperty("searchReadmeURL");		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closableHttpResponse = restClient.get(url);
		
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code:"+statusCode);
		Assert.assertEquals(statusCode, 200, "Status code is not 200");
		
		String responseString = EntityUtils.toString(closableHttpResponse.getEntity());		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API:"+responseJson);
		
	}
	
	

}
