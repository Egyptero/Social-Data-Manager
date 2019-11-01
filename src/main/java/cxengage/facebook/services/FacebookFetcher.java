package cxengage.facebook.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;

import cxengage.facebook.page.pojos.FacebookPage;
import cxengage.facebook.page.pojos.FacebookPageComments;
import cxengage.facebook.page.pojos.FacebookPagePosts;
import cxengage.facebook.pojos.InstUser;

public class FacebookFetcher {
	private transient Logger logger = Logger.getLogger(FacebookFetcher.class);
	private FacebookClient facebookClient;
	private String page;
	private FacebookPage facebookPage;
	private String accessToken;

	public FacebookFetcher(String accessToken,String pageID) throws Exception {
		logger.debug("Initiating fetch process for page :"+pageID);
		logger.debug("Facebook Fetcher Initiated with access token : ("+accessToken+")");
		setFacebookClient(new DefaultFacebookClient(accessToken, Version.VERSION_2_11));
		setPage(pageID);
		setAccessToken(accessToken);
	}

	private FacebookClient getFacebookClient() {
		return facebookClient;
	}

	public void renewAccessToken() {
		String newToken = fetchUserPermTokenString();
		setFacebookClient(new DefaultFacebookClient(newToken, Version.VERSION_2_11));
	}
	
	public void setFacebookClient(FacebookClient facebookClient) {
		this.facebookClient = facebookClient;
	}

	public FacebookPage reFetchFacebookPageObject() throws Exception{
			return fetchFacebookPageObject();
	}

	public FacebookPage fetchFacebookPageObject() throws Exception{
		//logger.debug("Fetching Facebook page Object for ID :"+page);
		JsonObject json = getFacebookClient().fetchObject(page, JsonObject.class, Parameter.with("fields", "name,id,posts{story,id,link,comments{message,comments{message,created_time,like_count,admin_creator,comment_count},created_time,like_count,admin_creator,comment_count},created_time,message,name,like_count,comment_count}"));
		//logger.debug("Fetch page object, return json :"+json);
		ObjectMapper mapper = new ObjectMapper();
		facebookPage = mapper.readValue(json.toString(), FacebookPage.class);
		return facebookPage;
	}
	
	public Object fetchNextItems(String next,Class<?> cls) throws Exception {

		FacebookPageComments facebookPageComments;
		FacebookPagePosts facebookPagePosts;
		FacebookPage facebookPage;
		Object outComeObject = null;
		
		if(next == null)
			return null;
		else {
			next = java.net.URLDecoder.decode(next, "UTF-8");
		}
		
		String text = next.substring(next.indexOf("?") + 1);
		String[] blocks = text.split("&");
		
		List<Parameter> parameters = new ArrayList<Parameter>();
		
		for(String block : blocks) {
			String[] keyVal = block.split("=");
			
			if(keyVal.length == 2 && !keyVal[0].equals("access_token")) 
				parameters.add(Parameter.with(keyVal[0], keyVal[1]));
		}
		
		
		String firstPartOfUrl = next.substring(0, next.indexOf("?")).replaceAll("https://", "").replaceAll("http://", "");
		String id = firstPartOfUrl.split("/")[2];
		String suffix = firstPartOfUrl.split("/")[3];
		
		JsonObject json = null;
		if(parameters.size() == 1)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0));
		else if(parameters.size() == 2)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1));
		else if(parameters.size() == 3)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2));
		else if(parameters.size() == 4)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
		else if(parameters.size() == 5)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4));
		else if(parameters.size() == 6)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), parameters.get(5));
		else if(parameters.size() == 7)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), parameters.get(5), parameters.get(6));
		else if(parameters.size() == 8)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), parameters.get(5), parameters.get(6), parameters.get(7));
		else if(parameters.size() == 9)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), parameters.get(5), parameters.get(6), parameters.get(7), parameters.get(8));
		else if(parameters.size() == 10)
			json = getFacebookClient().fetchObject(id+"/"+suffix, JsonObject.class, parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3), parameters.get(4), parameters.get(5), parameters.get(6), parameters.get(7), parameters.get(8), parameters.get(9));
		else if(parameters.size() > 10)
			throw new Exception("Unsupported parameters size");
		
		logger.debug("Fetch extra comments, return json is not null");
		
		ObjectMapper mapper = new ObjectMapper();
		if(cls == FacebookPageComments.class) {
			facebookPageComments = mapper.readValue(json.toString(), FacebookPageComments.class);
			outComeObject = facebookPageComments;
		}else if(cls == FacebookPagePosts.class) {
			facebookPagePosts = mapper.readValue(json.toString(), FacebookPagePosts.class);
			outComeObject = facebookPagePosts;
		} else if(cls == FacebookPage.class) {
			facebookPage = mapper.readValue(json.toString(), FacebookPage.class);
			outComeObject = facebookPage;
		}
		
		if(outComeObject == null)
			logger.error("Can not find extra page");
		
		return outComeObject;
	}
	
	@Deprecated
	public List<String> fetchInstMediaIDs(String businessAccountID) throws Exception {
		logger.debug("Fetching Media IDs for Account ID :"+businessAccountID);
		List<String> mediaIDs = new ArrayList<String>();
		JsonObject json = getFacebookClient().fetchObject(businessAccountID + "/media", JsonObject.class);
		logger.debug("Fetch Media ids, return json :"+json);
		if (json != null) {
			JsonArray mediaIDArray = (JsonArray) json.get("data");
			if (mediaIDArray != null && !mediaIDArray.isEmpty()) {
				for (JsonValue value : mediaIDArray.values()) {
					if (value.asObject().get("id") != null)
						mediaIDs.add(value.asObject().get("id").asString());
				}
			}
		}
		return mediaIDs;
	}

	public JsonObject fetchInstMediaCommentsJson(String mediaID) throws Exception {
		logger.debug("Fetching Media Comments for Media ID :"+mediaID);
		JsonObject json = getFacebookClient().fetchObject(mediaID + "/comments", JsonObject.class);
		logger.debug("Fetch Media comments, return json :"+json);
		return json;
	}
	public String fetchUserPermTokenString() {
		JsonObject json = fetchUserPermToken();
		if(json != null)
			return json.getString("access_token",null);
		else
			return null;
	}
	public JsonObject fetchUserPermToken() {
		logger.debug("Fetching User Permenant Access Token");
		JsonObject json = getFacebookClient().fetchObject("oauth/access_token", 
				JsonObject.class,
				Parameter.with("grant_type", "fb_exchange_token"),
				Parameter.with("client_id", "1833042753424607"), //179760889437111
				Parameter.with("client_secret", "a1101723a50590ea6bedb2e409a12b2f"), //a9637aa369699251222a07ab50a3f4d4
				Parameter.with("fb_exchange_token", getAccessToken()));
		logger.debug("Fetch Media comments, return json :"+json);
		return json;
	}
	public InstUser fetchUserDetails(String id) throws Exception {
		InstUser instUser = null;
		logger.debug("Fetching user details ID :"+id);
		JsonObject json = getFacebookClient().fetchObject(id, JsonObject.class, Parameter.with("fields", "id,name,profile_picture_url"));
		logger.debug("Fetch user details object, return json :"+json);
		ObjectMapper mapper = new ObjectMapper();
		instUser = mapper.readValue(json.toString(), InstUser.class);
		return instUser;
		
	}

	public String postReply(String id,String text) throws Exception {
		logger.debug("Post reply to comment ID :"+id);
		JsonObject json = getFacebookClient().publish(id+"/replies", JsonObject.class, Parameter.with("message", text));
		logger.debug("Post reply to comment ID, return json :"+json);
		return json.getString("id", null);
		
	}

	public boolean deleteComment(String id) throws Exception {
		logger.debug("delete comment ID :"+id);
		boolean outcome = getFacebookClient().deleteObject(id);
		logger.debug("Delete comment outcome :"+outcome);
		return outcome;
		
	}

	public boolean hideComment(String id) throws Exception {
		logger.debug("Hide comment ID :"+id);
		JsonObject json = getFacebookClient().publish(id, JsonObject.class, Parameter.with("hide", "true"));
		logger.debug("Hide comment ID, return json :"+json);
		return json.getBoolean("success", false);
		
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
