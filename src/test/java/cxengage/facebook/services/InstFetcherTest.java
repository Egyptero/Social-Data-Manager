package cxengage.facebook.services;

import org.junit.Test;

import cxengage.instagram.services.InstagramFetcher;


public class InstFetcherTest {
	private String token = "EAACjfdf1m7cBAApqASXJ7DNYDinuoVXVaB8uWYdR2IKnZAIrGrZChgyZACPvdhU1EKEF2iEwCyZAClNjIbZAxxmBrZBnL9AQwFwNDsTOpOnZC6ZCqOifc3TLEhZAlPbKj5BTTZAZCRaWhBq6H0sfp7vw90dtAHAPhL9mKe46McQFppFwgZDZD";
	@Test
	public void test()  {
		InstagramFetcher instFetcher;
		try {
 			instFetcher = new InstagramFetcher(token,null);
			instFetcher.fetchInstBAObject();
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	@Test
	public void testFetchPermToken() {
		InstagramFetcher instFetcher;
		try {
			instFetcher = new InstagramFetcher(token,null);
			String permtoken = instFetcher.fetchUserPermTokenString();
//			System.out.println(permtoken);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

}
