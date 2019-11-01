package cxengage.facebook.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Path("/TwitterRest")
public class TwitterRest {
	private transient Logger logger = Logger.getLogger(TwitterRest.class);
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/Login")
	public Response login() {

		Twitter twitter = new TwitterFactory().getInstance();
        request.getSession().setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/Callback");

            RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            return Response.temporaryRedirect(new URI(requestToken.getAuthenticationURL())).build();

        } catch (TwitterException | URISyntaxException e) {
        	logger.error(e);
            return Response.status(400).build();
        }
		
	}
	
	@GET
	@Path("/Callback")
	public Response callback() {
        Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

            User user = twitter.showUser(twitter.getId());
            String userName = user.getName();
            String profilePicUrl = user.getProfileImageURL();
            String screenName = user.getScreenName();
            String userId = Long.toString(user.getId());
            String token = accessToken.getToken();
            String tokenSecret = accessToken.getTokenSecret();
            
            //request.getSession().getServletContext().setAttribute(userId+"_twitter", twitter); // Store the twitter object with the ID
            //request.getSession().getServletContext().setAttribute(userId+"_access_token", accessToken); // Store the Access Token
            //request.getSession().getServletContext().setAttribute(userId+"_oauth_verifier", verifier); // Store the Verifier
            //request.getSession().getServletContext().setAttribute(userId+"_request_token", requestToken); // Store the Request Token
            
            request.getSession().removeAttribute("requestToken");
            request.getSession().removeAttribute("oauth_verifier");
            request.getSession().removeAttribute("twitter");
            
            String urlData = "/SDRAccount.html?accessToken="+token+"&tokenSecret="+tokenSecret+"&twitterid="+userId+"&userName="+URLEncoder.encode(userName, "UTF-8")+"&profilePicUrl="+URLEncoder.encode(profilePicUrl, "UTF-8")+"&screenName="+URLEncoder.encode(screenName, "UTF-8");
            logger.debug("Context Path="+request.getContextPath());
            return Response.temporaryRedirect(new URI(request.getContextPath() + urlData)).build();
        } catch (TwitterException | URISyntaxException | UnsupportedEncodingException e) {
        	logger.error(e);
        	e.printStackTrace();
            return Response.status(400).build();
        }
	}
	

}
