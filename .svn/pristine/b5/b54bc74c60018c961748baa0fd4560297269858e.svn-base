package cxengage.facebook.engine;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;
import cxengage.instagram.services.InstagramFetcher;

public class InstGenesysEngineTest {

	@Test
	public void test() {
		try {
			//TODO request.getSession().getServletContext().getRealPath("WEB-INF/classes/data.json");
			ObjectMapper mapper = new ObjectMapper();

			String src = "{\r\n" + 
					"        \"id\": \"10159831212825333\",\r\n" + 
					"        \"page\": null,\r\n" + 
					"        \"accesstoken\": \"EAACjfdf1m7cBAK81dRnApH7kvz3y499wbfySWpQkk1g0HhicYcwNeQygtU3tZCnXXfZCKQLpXPs6mG1V9ZCW55littZCLw3IXHJalhstvMZCzpQk2CEzj6RdfWuFJ6FQZB7KV7I8VyZAZAQUfVoW3HXGnpMqYT4m5c1KzPGaXhCHO8WD1h0t4M4tYL0ZBfUzPOAbYIjp4SZCsfHwZDZD\",\r\n" + 
					"        \"user\": \"Mamdouh Aref Mohamed\",\r\n" + 
					"        \"startTime\": \"10:10\",\r\n" + 
					"        \"endTime\": \"17:59\",\r\n" + 
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
					"                \"alrajhibank\"\r\n" + 
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
			
			RouterLog routerLog = new RouterLog("C:\\Users\\Mamdouh\\eclipse-workspace\\Inst-Genesys\\src\\main\\webapp\\WEB-INF\\ids.json");
			InstagramFetcher instFetcher = new InstagramFetcher("EAACjfdf1m7cBABIUfjuPeygxPpkjVk7I4klmYD2MbeemT2dqVHogtRSpk8pODQjcERr87gJKyKFejwHaXh5TnwZC9sHMQTdyjQSZBT79jyC7s8m5Wu4bv958IHp1HNVBbbze3Dzn0kp3rapSjqZBQIOnMZC98i77Peihz4ZBqBzfxox0QtRZCcwrBEUJHSJJEZB8ZB3euVZBpZBgZDZD", "maref2018");
			InstGenesysEngine instGenesysEngine = new InstGenesysEngine(instFetcher,routerLog,tenant);
//			instGenesysEngine.start();
//			synchronized(this) {
//				wait(1*60*1000);
//				instGenesysEngine.setActive(false);
//				if(!instGenesysEngine.isAlive())
//					instGenesysEngine.notify();
//				wait(6*60*1000);
//				routerLog.save();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
