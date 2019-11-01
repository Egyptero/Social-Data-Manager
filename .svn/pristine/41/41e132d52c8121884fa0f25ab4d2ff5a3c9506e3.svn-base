package cxengage.facebook.engine;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;

public class FacebookEngineTest {

	@Test
	public void test() {
		try {
			//TODO request.getSession().getServletContext().getRealPath("WEB-INF/classes/data.json");
			ObjectMapper mapper = new ObjectMapper();

			String src = "{\r\n" + 
					"        \"id\": \"10159831212825333\",\r\n" + 
					"        \"page\": null,\r\n" + 
					"        \"accesstoken\": \"EAACjfdf1m7cBAHhRSZCvnpwpYrkhTXmZAgWetk6wgXgc1uRNt8umFuQ6B9S1bmZACs4VnZBAEnZC84g0odRC8GX5C03h5Ptp9GzMaK6tjOAuBVEOE4LuZCARD5JopiEpoJCZBF636vqVUr8vGDGurAP2g9mn452P1wCju3OjtiSZABbZAnWkyjjRkOUk9eX7O1vBFxaZC1Q5hMrQZDZD\",\r\n" + 
					"        \"user\": \"Mamdouh Aref Mohamed\",\r\n" + 
					"        \"startTime\": \"20:10\",\r\n" + 
					"        \"endTime\": \"23:59\",\r\n" + 
					"        \"queueName\": null,\r\n" + 
					"        \"endPoint\": null,\r\n" + 
					"        \"rate\": \"10\",\r\n" + 
					"        \"tenantId\": null,\r\n" + 
					"        \"mediaType\": null,\r\n" + 
					"        \"startDate\": \"2017-11-11\",\r\n" + 
					"        \"facebookData\": {\r\n" + 
					"            \"posts\": true,\r\n" + 
					"            \"comments\": true,\r\n" + 
					"            \"replies\": true,\r\n" + 
					"            \"console\": true,\r\n" + 
					"            \"restAPI\": true,\r\n" + 
					"            \"contactCenter\": true,\r\n" + 
					"            \"pages\": [\r\n" + 
					"                \"218134834875511\"\r\n" + 
					"            ],\r\n" + 
					"            \"contactCenterData\": {\r\n" + 
					"                \"genesysCPData\": {\r\n" + 
					"                    \"queueName\": \"Sample inbound queue\",\r\n" + 
					"                    \"endPoint\": \"http://wesl\",\r\n" + 
					"                    \"tenantId\": \"aasas\",\r\n" + 
					"                    \"mediaType\": \"111\"\r\n" + 
					"                },\r\n" + 
					"                \"ciscoSMData\": {\r\n" + 
					"                    \"host\": \"host\",\r\n" + 
					"                    \"port\": \"113\"\r\n" + 
					"                }\r\n" + 
					"            },\r\n" + 
					"            \"restAPIData\": {\r\n" + 
					"                \"uri\": \"http://18.208.70.80:9160/logstash-facebook/posts\",\r\n" + 
					"                \"methodGet\": false,\r\n" + 
					"                \"methodPost\": true,\r\n" + 
					"                \"inBodyData\": true,\r\n" + 
					"                \"inParameterData\": false,\r\n" + 
					"                \"parameterName\": \"empty\"\r\n" + 
					"            }\r\n" + 
					"        }\r\n" + 
					"    }";
			Tenant tenant = mapper.readValue(src, Tenant.class);
			
			
			RouterLog routerLog = new RouterLog("C:\\Users\\Mamdouh\\eclipse-workspace\\InstGenesys\\src\\main\\webapp\\WEB-INF\\ids.json");			
//			FacebookEngine facebookEngine = new FacebookEngine(routerLog, tenant,true);
			
//			facebookEngine.start();
//			synchronized(this) {
//				wait(1*60*1000);
//				facebookEngine.setActive(false);
//				if(!facebookEngine.isAlive())
//					facebookEngine.notify();
//				wait(6*60*1000);
//				routerLog.save();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
