package cxengage.facebook.conf.pojos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TenantTest {

	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		String src = "{" + 
				"	\"id\": \"123553422233\"," + 
				"	\"page\": \"maref2018\"," + 
				"	\"accesstoken\": \"EAACjfdf1m7cBAMacIApAgYdBOZC3z1BrQ1PORZB5ZBVk4gEp48W4zKHrFhQg5PwA0MTZCqiBFZBeda8rH9IMWYEglK2FQiNTcl4VZAET1aFOaySUdbFev9BMJBaWjH6kdBrW8Fi9UzH68GpWENdWxMR0TolMsiy7htAbSW6TDPpZBVKZAd3bazWPZAh7NZCv641DhbujZAqU7INpQZDZD\"," + 
				"	\"user\": null," + 
				"	\"startTime\": null," + 
				"	\"endTime\": null," + 
				"	\"queueName\": null," + 
				"	\"endPoint\": null," + 
				"	\"rate\": null," + 
				"	\"tenantId\": null," + 
				"	\"mediaType\": null," + 
				"	\"startDate\": null," + 
				"	\"facebookData\": {" + 
				"		\"posts\": true," + 
				"		\"comments\": true," + 
				"		\"replies\": true," + 
				"		\"console\": true," + 
				"		\"pages\": [" + 
				"			\"maref2018\"," + 
				"			\"alrajhibank\"" + 
				"		]," + 
				"		\"contactCenterData\": {" + 
				"			\"genesysCPData\": null," + 
				"			\"ciscoSMData\": {" + 
				"				\"host\": \"SM Host\"," + 
				"				\"port\": \"SM Port\"" + 
				"			}" + 
				"		}" + 
				"	}" + 
				"}" + 
				"";
		Tenant tenant = mapper.readValue(src, Tenant.class);
		
		
		String outcome = mapper.writeValueAsString(tenant);
		System.out.println(outcome);
		
	}
	
	@Test
	public void testFBData() throws JsonProcessingException {
		FacebookData facebookData = new FacebookData();
		facebookData.setComments(true);
		facebookData.setPosts(true);
		facebookData.setReplies(true);
		facebookData.setConsole(true);
		List<String> pages = new ArrayList<String>();
		pages.add("maref2018");
		pages.add("alrajhibank");
		facebookData.setPages(pages);
		CiscoSMData ciscoSMData = new CiscoSMData();
		ciscoSMData.setHost("SM Host");
		ciscoSMData.setPort("SM Port");
		ContactCenterData contactCenterData = new ContactCenterData();
		contactCenterData.setCiscoSMData(ciscoSMData);
		facebookData.setContactCenterData(contactCenterData);
		
		ObjectMapper mapper = new ObjectMapper();
		String output = mapper.writeValueAsString(facebookData);
		System.out.println(output);
	}

}
