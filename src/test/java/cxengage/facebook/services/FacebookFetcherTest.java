package cxengage.facebook.services;

import org.junit.Test;

import cxengage.facebook.page.pojos.FacebookPage;


public class FacebookFetcherTest {
	private String token = "EAACjfdf1m7cBAM38daUZAZAJ3zDvY1qw7aYC0RqRv8t19G0a4H9D2iEKTNtDaaNR9lbAYWOIKOI1j7XxRCb11JHjZCU3045uzAp5ICAnftJ0zVbejkUnxVPQLxj8a1f8favuWU3NKfEqaBaxhk0eg6Nb9Dt112QkFGgBqtjLssprNsH1Tw0ZBF1lYAA777lLpfm6nGKEZCQZDZD";
	@Test
	public void test()  {
		FacebookFetcher facebookFetcher;
		try {
			facebookFetcher = new FacebookFetcher(token,"alrajhibank");
			FacebookPage facebookPage = facebookFetcher.fetchFacebookPageObject();
			System.out.println(facebookPage.toString());
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
//	@Test
//	public void testFetchPermToken() {
//		FacebookFetcher facebookFetcher;
//		try {
//			facebookFetcher = new FacebookFetcher(token,"maref2018");
//			String permtoken = facebookFetcher.fetchUserPermTokenString();
//			System.out.println(permtoken);
//		} catch (Exception e) {
////			e.printStackTrace();
//		}
//	}

}
