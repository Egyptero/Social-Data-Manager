package cxengage.twitter.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.page.outcome.pojos.Feed;
import cxengage.twitter.pojos.TwitterSearchResult;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterFetcher {
	private transient Logger logger = Logger.getLogger(TwitterFetcher.class);
	private Twitter twitter;
	private String searchKey;
	private Query query;
	private QueryResult result;
	private String accessToken;
	private String accessTokenSecret;
	private String fromDate;
	private String toDate;
	private String maxResult;
	
	private String key;
	
	
	public TwitterFetcher(String accessToken, String accessTokenSecret, String key) throws Exception {
		logger.debug("Initiating fetch process for twitter key :"+key);
		logger.debug("Twitter Fetcher Initiated with access token : ("+accessToken+")");
		
		setAccessToken(accessToken);
		setAccessTokenSecret(accessTokenSecret);
		setKey(key);
		
		resolveKey(key);
		setTwitter(new TwitterFactory().getInstance(loadAccessToken()));
	}

	private void resolveKey(String key) {
		//TODO check if it is query
		if(key.contains(";") || key.contains(">")) { // We have a query
			String[] queryParams = key.split(";");
			for(String queryParam : queryParams) {
				String[] keyValue = queryParam.split(">");
				if(keyValue[0].equalsIgnoreCase("query")) 
					setSearchKey(keyValue[1]);
				else if(keyValue[0].equalsIgnoreCase("maxresults"))
					setMaxResult(keyValue[1]);
				else if(keyValue[0].equalsIgnoreCase("fromdate"))
					setFromDate(keyValue[1]);
				else if(keyValue[0].equalsIgnoreCase("todate"))
					setToDate(keyValue[1]);
			}
			
		} else
			setSearchKey(key);	
	}

	public QueryResult fetchSearchTweets() throws Exception{
		 query = new Query(getSearchKey());
		 logger.debug("Result Query is:"+query);
		 return fetchNextSearch();
	}
	
	public QueryResult fetchNextSearch() throws Exception{
		if(query == null)
			return null;
		QueryResult queryResult = null;
		try {
			queryResult = twitter.search(query);
		} catch (TwitterException e) {
				int resetTime = twitter.getRateLimitStatus().get(0).getResetTimeInSeconds();
				synchronized(this) {
					logger.debug("Rate limit is their, we have to wait for ="+(resetTime+3)+" Seconds");
					wait((resetTime+3)*1000);
				}
				fetchNextSearch();
		}
		if(queryResult != null)
			setResult(queryResult);
        return getResult();
	}
	
	public TwitterSearchResult fetch30DaysSearch(String next) throws Exception{
		
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer("z1dgIoeG9SWCQbdmJcuPFvORp",
				"qWc2DkeY6oEJMwXoU75UvgWoZLPLb2fF3aA8QYEvAOdihu88NW");
		oAuthConsumer.setTokenWithSecret(getAccessToken(), getAccessTokenSecret());

		String query = "{\"query\":\""+getSearchKey()+"\"}";
		if(next != null)
			query = "{\"query\":\""+getSearchKey()+"\",\"next\":\""+next+"\"}";
		
		StringEntity entity = new StringEntity(query,
                ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(
				"https://api.twitter.com/1.1/tweets/search/30day/dev.json");
		httpPost.setEntity(entity);

		oAuthConsumer.sign(httpPost);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		List<Feed> tweets = null;
		TwitterSearchResult twitterSearchResult = new TwitterSearchResult();
		if(statusCode == 200) {
			JSONObject json = new JSONObject(IOUtils.toString(httpResponse.getEntity().getContent()));
			JSONArray array = json.getJSONArray("results");
			tweets = new ArrayList<Feed>(array.length());

			for (int i = 0; i < array.length(); i++) {
                JSONObject tweet = array.getJSONObject(i);
                ObjectMapper objectMapper = new ObjectMapper();
                Feed feed = new Feed();
                feed.setSource("Twitter");
                feed.setCommentMessage(tweet.getString("text"));
                feed.setPageName(getSearchKey());
                feed.setCommentId(tweet.getString("id_str"));
                feed.setCommentCreationTime(tweet.getString("created_at"));
                feed.setCommentCommentCount(tweet.getInt("retweet_count"));
                tweets.add(feed);
            }
			twitterSearchResult.setFeeds(tweets);
			if(json.has("next"))
				twitterSearchResult.setNext(json.getString("next"));
			
		}
		return twitterSearchResult;
	}
	
	public TwitterSearchResult fetchFullArchSearch(String next,String fromDate) throws Exception{
		
		OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer("z1dgIoeG9SWCQbdmJcuPFvORp",
				"qWc2DkeY6oEJMwXoU75UvgWoZLPLb2fF3aA8QYEvAOdihu88NW");
		oAuthConsumer.setTokenWithSecret(getAccessToken(), getAccessTokenSecret());

		//Converting the date from the format yyyy-mm-dd to yyyymmdd0000
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd0000");
		
		String query = "{\"query\":\""+getSearchKey()+"\"";
		
		if(getFromDate() != null) {
			Date fromDateOrg = new SimpleDateFormat("yyyy-mm-dd").parse(getFromDate());
			String fromDateString = simpleDateFormat.format(fromDateOrg);
			query += ",\"fromDate\":\""+fromDateString+"\"";
		} else if(fromDate != null) {
			Date fromDateOrg =new SimpleDateFormat("yyyy-mm-dd").parse(fromDate);
			String fromDateString = simpleDateFormat.format(fromDateOrg);
			query += ",\"fromDate\":\""+fromDateString+"\"";
		} else
			return new TwitterSearchResult(); // You need to define start date
		
		if(getToDate() != null) {
			Date toDateOrg = new SimpleDateFormat("yyyy-mm-dd").parse(getToDate());
			String toDateString = simpleDateFormat.format(toDateOrg);
			query += ",\"toDate\":\""+toDateString+"\"";
		}
		
		if(getMaxResult() != null) {
			query += ",\"maxResults\":\""+getMaxResult()+"\"";
		}
		
		if(next != null)
			query += ",\"next\":\""+next+"\"";
		
		query += "}";
		
		
		
		logger.debug("Fetch Full Archive and query is :"+query);
		StringEntity entity = new StringEntity(query,
                ContentType.APPLICATION_JSON);
		HttpPost httpPost = new HttpPost(
				"https://api.twitter.com/1.1/tweets/search/fullarchive/devFull.json");
		httpPost.setEntity(entity);

		oAuthConsumer.sign(httpPost);

		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpPost);

		int statusCode = httpResponse.getStatusLine().getStatusCode();
		List<Feed> tweets = null;
		TwitterSearchResult twitterSearchResult = new TwitterSearchResult();
		if(statusCode == 200) {
			JSONObject json = new JSONObject(IOUtils.toString(httpResponse.getEntity().getContent()));
			JSONArray array = json.getJSONArray("results");
			tweets = new ArrayList<Feed>(array.length());

			for (int i = 0; i < array.length(); i++) {
                JSONObject tweet = array.getJSONObject(i);
                ObjectMapper objectMapper = new ObjectMapper();
                Feed feed = new Feed();
                feed.setSource("Twitter");
                feed.setCommentMessage(tweet.getString("text"));
                feed.setPageName(getSearchKey().replaceAll(":", "-"));
                feed.setCommentId(tweet.getString("id_str"));
                feed.setCommentCreationTime(tweet.getString("created_at"));
                feed.setCommentCommentCount(tweet.getInt("retweet_count"));
                
                	
                JSONObject user = tweet.getJSONObject("user");	
                feed.setCommentLink("https://twitter.com/"+user.getString("screen_name") + "/status/" + tweet.getString("id_str"));
                feed.setCommentUserName(user.getString("name"));
                feed.setCommentUserID(user.getString("id_str"));
                
                tweets.add(feed);
            }
			twitterSearchResult.setFeeds(tweets);
			if(json.has("next"))
				twitterSearchResult.setNext(json.getString("next"));
			
		}
		return twitterSearchResult;
	}
	
	private AccessToken loadAccessToken(){
	    return new AccessToken(getAccessToken(), getAccessTokenSecret());
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public Twitter getTwitter() {
		return twitter;
	}


	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public QueryResult getResult() {
		return result;
	}

	public void setResult(QueryResult result) {
		this.result = result;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

}
