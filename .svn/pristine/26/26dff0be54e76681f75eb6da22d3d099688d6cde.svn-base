package cxengage.instagram.services;

import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.InstagramClient;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.comments.CommentData;
import org.jinstagram.entity.comments.MediaCommentsFeed;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InstFetcherWithInstagramLive {
	private final static String CLIENT_SECRET = "eb4f5bac90dd44b7b2283d369f647ab8";
	private final static String ACCESS_TOKEN = "6243317004.f724248.ca0295e6379c49998f00743b5445062b";
//	private static Map<String, String> invocations;
//	static {
//		invocations = InvocationFile.readInvocations();
//	}

	public static void main(String[] args) {
		checkUpdates();
	}

	public static void checkUpdates() {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET, "127.0.0.1");

		InstagramClient instagram = new Instagram(accessToken);

		try {

			MediaFeed mediaFeed = instagram.getUserRecentMedia();
			List<MediaFeedData> mediaFeeds = mediaFeed.getData();
			boolean insert = false;
			for (MediaFeedData mediaData : mediaFeeds) {
				MediaCommentsFeed feed = instagram.getMediaComments(mediaData.getId());
				List<CommentData> commentsList = feed.getCommentDataList();
//				String lastUpdate = invocations.get(mediaData.getId());
				for (CommentData commentData : commentsList) {
					System.out.println(commentData.toString());
//					if (!commentData.getCreatedTime().equals(lastUpdate)) {
//						lastUpdate = commentData.getCreatedTime();
//						GenesysClient.submitToGenesys(mediaData.getId(), commentData.getId(), commentData.getText());
//					}
				}
//				invocations.put(mediaData.getId(), lastUpdate);
			}
//			if (insert) {
//				InvocationFile.writeInvocations(invocations);
//			}
		} catch (InstagramException e) {
			e.printStackTrace();
		}
	}

	public static String getFeed(String mediaId, String commentId) throws InstagramException {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET);
		InstagramClient instagram = new Instagram(accessToken);
		String postString = "";
		MediaInfoFeed mediaFeed = instagram.getMediaInfo(mediaId);
		MediaFeedData mediaData = mediaFeed.getData();
		MediaCommentsFeed feed = instagram.getMediaComments(mediaData.getId());
		List<CommentData> commentsList = feed.getCommentDataList();
		CommentData commentData = null;
		for (CommentData commentDataTemp : commentsList) {
			if (commentDataTemp.getId().equals(commentId)) {
				commentData = commentDataTemp;
			}
		}
		System.out.println(commentData.toString()+ mediaData.toString()+ instagram.getCurrentUserInfo().getData());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//postString = gson.toJson(post);
		return commentData.toString()+ mediaData.toString()+ instagram.getCurrentUserInfo().getData().toString();
	}

	public static void addComment(String mediaId, String comment) throws InstagramException {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET);
		InstagramClient instagram = new Instagram(accessToken);
		instagram.setMediaComments(mediaId, comment);
	}

	public static void deleteComment(String mediaId, String commentId) throws InstagramException {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET);
		InstagramClient instagram = new Instagram(accessToken);
		instagram.deleteMediaCommentById(mediaId, commentId);
	}

	public static String addLike(String mediaId) throws InstagramException {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET);
		InstagramClient instagram = new Instagram(accessToken);
		instagram.setUserLike(mediaId);
		System.out.println(instagram.getMediaInfo(mediaId).getData().toString());
		return instagram.getMediaInfo(mediaId).getData().toString();
	}

	public static String deleteLike(String mediaId) throws InstagramException {
		Token accessToken = new Token(ACCESS_TOKEN, CLIENT_SECRET);
		InstagramClient instagram = new Instagram(accessToken);
		instagram.deleteUserLike(mediaId);
		System.out.println(instagram.getMediaInfo(mediaId).getData().toString());
		//Post post = new Post(null, instagram.getMediaInfo(mediaId).getData(), null);
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return instagram.getMediaInfo(mediaId).getData().toString();
	}
}
