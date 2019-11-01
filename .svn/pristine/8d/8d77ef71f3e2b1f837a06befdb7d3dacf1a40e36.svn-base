package cxengage.facebook.engine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;
import cxengage.facebook.page.outcome.pojos.Feed;
import cxengage.twitter.pojos.TwitterSearchResult;
import cxengage.twitter.services.TwitterFetcher;
import twitter4j.QueryResult;
import twitter4j.Status;

public class TwitterEngine extends Engine {
	public enum SearchType {
		Standard, Prem30Day, PremFullArch
	}

	private transient Logger logger = Logger.getLogger(TwitterEngine.class);
	private List<TwitterFetcher> twitterFetchers;
	private int rate;
	private boolean active = true;
	private RouterLog routerLog;
	private EngineStatus status;
	private Tenant tenant;
	private SearchType searchType;

	public TwitterEngine(RouterLog routerLog, Tenant tenant, boolean checkHistory, SearchType searchType)
			throws Exception {
		setStatus(EngineStatus.Initiating);
		setCheckHistory(checkHistory);
		try {
			setRate(tenant.getRate());
		} catch (Exception e) {
			logger.error(e);
			try {
				logger.info("Setting default rate of 10 minutes");
				setRate("10");
			} catch (Exception e1) {
				logger.error(e1);
				setStatus(EngineStatus.Error);
				throw e1;
			}
		}
		try {
			setTenant(tenant);
			setRouterLog(routerLog);
			setSearchType(searchType);
			for (String key : tenant.getTwitterData().getKeys())
				addTwitterFetcher(new TwitterFetcher(tenant.getTwitterData().getToken(),
						tenant.getTwitterData().getTokenSecret(), key));

		} catch (Exception e) {
			logger.error(e);
			setStatus(EngineStatus.Error);
			throw e;
		}
	}

	public void performEngineJob() throws Exception {
		for (TwitterFetcher twitterFetcher : twitterFetchers) {
			logger.debug("Fetch will happen now for search Key:" + twitterFetcher.getSearchKey());
			switch (getSearchType()) {
			case Standard:
				QueryResult result = twitterFetcher.fetchSearchTweets();
				List<Status> tweets = result.getTweets();
				List<Feed> feeds = new ArrayList<Feed>();
				for (Status tweet : tweets) {

//					System.out.println("New Tweet <<<<<<<from:" + tweet.getUser().getScreenName() + ",Text"
//							+ tweet.getText() + ", created at:" + tweet.getCreatedAt() + ",Go Location:"
//							+ tweet.getGeoLocation() + ",id:" + tweet.getId() + ",Retweet Count:"
//							+ tweet.getRetweetCount() + ",user followers:" + tweet.getUser().getFollowersCount()
//							+ ",user language:" + tweet.getUser().getLang() + ",user profile image:"
//							+ tweet.getUser().getBiggerProfileImageURLHttps() + ",user email:"
//							+ tweet.getUser().getEmail() + ",link:" + "https://twitter.com/"
//							+ tweet.getUser().getScreenName() + "/status/" + tweet.getId() + ">>>>>>>");
					Feed feed = new Feed();
					feed.setSource("Twitter");
					feed.setCommentMessage(tweet.getText());
					feed.setPageName(twitterFetcher.getSearchKey());
					feed.setCommentId(Long.toString(tweet.getId()));
					feed.setCommentCreationTime(tweet.getCreatedAt().toString());
					feed.setCommentCommentCount(tweet.getRetweetCount());
					feeds.add(feed);
				}

				reviewQueryResult(feeds);
				while (result != null && result.hasNext() && checkHistory && isActive()) {
					twitterFetcher.setQuery(result.nextQuery());
					result = twitterFetcher.fetchNextSearch();
					tweets = result.getTweets();
					feeds = new ArrayList<Feed>();
					for (Status tweet : tweets) {

//						System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText()
//								+ ", created at:" + tweet.getCreatedAt() + ",Go Location:" + tweet.getGeoLocation()
//								+ ",id" + tweet.getId());
						Feed feed = new Feed();
						feed.setSource("Twitter");
						feed.setCommentMessage(tweet.getText());
						feed.setPageName(twitterFetcher.getSearchKey());
						feed.setCommentId(Long.toString(tweet.getId()));
						feed.setCommentCreationTime(tweet.getCreatedAt().toString());
						feed.setCommentCommentCount(tweet.getRetweetCount());
						feeds.add(feed);
					}

					reviewQueryResult(feeds);
				}
				break;
			case Prem30Day:
				TwitterSearchResult twitterSearchResult = twitterFetcher.fetch30DaysSearch(null);
				reviewQueryResult(twitterSearchResult.getFeeds());
				while (twitterSearchResult.getNext() != null && isActive()) {
					twitterSearchResult = twitterFetcher.fetch30DaysSearch(twitterSearchResult.getNext());
					reviewQueryResult(twitterSearchResult.getFeeds());
				}
				break;
			case PremFullArch:
				TwitterSearchResult twitterSearchResultFull = twitterFetcher.fetchFullArchSearch(null, tenant.getStartDate());
				reviewQueryResult(twitterSearchResultFull.getFeeds());
				while (twitterSearchResultFull.getNext() != null && isActive()) {
					//twitterSearchResultFull = twitterFetcher.fetch30DaysSearch(twitterSearchResultFull.getNext());
					twitterSearchResultFull = twitterFetcher.fetchFullArchSearch(twitterSearchResultFull.getNext(),tenant.getStartDate());
					reviewQueryResult(twitterSearchResultFull.getFeeds());
				}
				break;
			default:
				break;

			}
		}
		setStatus(EngineStatus.Stop);
	}

	private boolean reviewQueryResult(List<Feed> feeds) throws ParseException {
		logger.debug("Review tweets");
		 
		if(feeds == null)
			return false;
		if(feeds.size() < 1)
			return false;
		
		if(!getTenant().getTwitterData().isTweets()) {
			logger.debug("You missed to check tweets listen");
			return false;
		}
		
		 if (getTenant().getTwitterData().isConsole()) {
		 new EngineResult(EngineResultType.Console,EngineResultSource.Twitter,
		 feeds, this).run();
		 logger.debug("Twitter Search Batch Started to save");
		 }
		
		 if (getTenant().getTwitterData().isRestAPI()) {
		 new EngineResult(EngineResultType.Rest,EngineResultSource.Twitter,
		 feeds, this).run();
		 logger.debug("Twitter Search Batch Started to save");
		 }

		return true;
	}

	// private boolean createInteraction(String type, String id, String text, String
	// userName) {
	// logger.debug(
	// "===== Routing request for type :(" + type + ") and id:(" + id + ")" + ", and
	// text is:(" + text + ")");
	// // TODO test routing to Genesys
	// try {
	// if (new GenesysClient().submitToGenesys(tenant.getId(), id, text,
	// tenant.getQueueName(),
	// tenant.getEndPoint(), userName, tenant.getTenantId(), tenant.getMediaType()))
	// {
	// logger.info("Interaction created on Genesys");
	// getRouterLog().addRoutedID(type, id);
	// }
	// } catch (MalformedURLException e) {
	// logger.error("Error Creating the interation");
	// logger.error(e);
	// } catch (RemoteException e) {
	// logger.error("Error Creating the interation");
	// logger.error(e);
	// } catch (ServiceException e) {
	// logger.error("Error Creating the interation");
	// logger.error(e);
	// }
	// return true;
	// }

	public List<TwitterFetcher> getTwitterFetchers() {
		return twitterFetchers;
	}

	public void addTwitterFetcher(TwitterFetcher twitterFetcher) {
		if (twitterFetchers == null)
			twitterFetchers = new ArrayList<TwitterFetcher>();
		twitterFetchers.add(twitterFetcher);
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	// TODO temp removal
	// private InstBusinessAccount resolvePagingMedia(InstBusinessAccount
	// currentInstBusinessAccount, InstBusinessAccount newInstBusinessAccount) {
	// if(newInstBusinessAccount.getData() == null)
	// return newInstBusinessAccount;
	//
	// if(currentInstBusinessAccount.getData() == null)
	// currentInstBusinessAccount.setData(newInstBusinessAccount.getData());
	//
	// for (InstMedia instMedia : newInstBusinessAccount.getData()) {
	// InstMedia currentMedia =
	// currentInstBusinessAccount.checkRemoveMediaExist(instMedia);
	// if(currentMedia == null)
	// currentMedia = instMedia;
	// currentMedia.setUpdated(false);
	// currentMedia = resolvePagingComment(currentMedia, instMedia);
	// if(currentMedia.isUpdated()) {
	// currentMedia.setUpdated(false);
	// }
	// currentInstBusinessAccount.getData().add(currentMedia);
	// }
	//
	// boolean changeFound = true;
	// while (changeFound) {
	// changeFound = false;
	// if (newInstBusinessAccount.getPaging() != null) { // We have some pages,let
	// us deep dive
	// if (newInstBusinessAccount.getPaging().getNext() != null) { // We have next
	// page , let us get it.
	// InstBusinessAccount nextInstBusinessAccount = null;
	// try {
	// nextInstBusinessAccount = (InstBusinessAccount) instFetcher
	// .fetchNextItems(newInstBusinessAccount.getPaging().getNext(),
	// InstBusinessAccount.class);
	// // TODO check if all new Media are new
	// } catch (Exception e) {
	// logger.warn(e);
	// }
	// if (nextInstBusinessAccount != null) {
	// for (InstMedia instMedia : nextInstBusinessAccount.getData()) {
	// InstMedia currentMedia =
	// currentInstBusinessAccount.checkRemoveMediaExist(instMedia);
	// if(currentMedia == null)
	// currentMedia = instMedia;
	// currentMedia.setUpdated(false);
	// currentMedia = resolvePagingComment(currentMedia, instMedia);
	// if(currentMedia.isUpdated()) {
	// changeFound = true;
	// currentMedia.setUpdated(false);
	// }
	// currentInstBusinessAccount.getData().add(currentMedia);
	// }
	// currentInstBusinessAccount.setPaging(nextInstBusinessAccount.getPaging());
	// newInstBusinessAccount = currentInstBusinessAccount;
	// }
	// }
	// }
	// }
	// return currentInstBusinessAccount;
	//
	// }
	// private InstComment resolvePagingReply (InstComment currentInstComment
	// ,InstComment newInstComment) {
	// if(newInstComment.getReplies() == null)
	// return newInstComment;
	//
	// if(currentInstComment.getReplies() == null) {
	// currentInstComment.setReplies(newInstComment.getReplies());
	// currentInstComment.setUpdated(true);
	// }
	//
	// for (InstReply instReply : newInstComment.getReplies().getData()) {
	// InstReply currentReply =
	// currentInstComment.getReplies().checkRemoveReplyExist(instReply);
	// if(currentReply == null) {
	// currentReply = instReply;
	// currentInstComment.setUpdated(true);
	// }
	// currentReply.setUpdated(false);
	// currentInstComment.getReplies().getData().add(currentReply);
	// }
	//
	// boolean changeFound = true;
	// while (changeFound) {
	// changeFound = false;
	// if (newInstComment.getReplies().getPaging() != null) { // We have some
	// pages,let us deep dive
	// if (newInstComment.getReplies().getPaging().getNext() != null) { // We have
	// next page , let us get it.
	// InstReplies nextInstReplies = null;
	// try {
	// nextInstReplies = (InstReplies) instFetcher
	// .fetchNextItems(newInstComment.getReplies().getPaging().getNext(),
	// InstReplies.class);
	// // TODO check if all new Media are new
	// } catch (Exception e) {
	// logger.warn(e);
	// }
	// if (nextInstReplies != null) {
	// for (InstReply instReply : nextInstReplies.getData()) {
	// InstReply currentReply =
	// currentInstComment.getReplies().checkRemoveReplyExist(instReply);
	// if(currentReply == null) {
	// currentReply = instReply;
	// changeFound = true;
	// currentInstComment.setUpdated(true);
	// }
	// currentReply.setUpdated(false);
	// currentInstComment.getReplies().getData().add(currentReply);
	// }
	// currentInstComment.getReplies().setPaging(nextInstReplies.getPaging());
	// newInstComment = currentInstComment;
	// }
	// }
	// }
	// }
	// return currentInstComment;
	// }
	// private InstMedia resolvePagingComment (InstMedia currentInstMedia ,InstMedia
	// newInstMedia) {
	// if(newInstMedia.getComments() == null)
	// return newInstMedia;
	//
	// if(currentInstMedia.getComments() == null) {
	// currentInstMedia.setComments(newInstMedia.getComments());
	// currentInstMedia.setUpdated(true);
	// }
	//
	// for (InstComment instComment : newInstMedia.getComments().getData()) {
	// InstComment currentComment =
	// currentInstMedia.getComments().checkRemoveCommentExist(instComment);
	// if(currentComment == null)
	// currentComment = instComment;
	// currentComment.setUpdated(false);
	// currentComment = resolvePagingReply(currentComment, instComment);
	// if(currentComment.isUpdated()) {
	// currentComment.setUpdated(false);
	// currentInstMedia.setUpdated(true);
	// }
	// currentInstMedia.getComments().getData().add(currentComment);
	// }
	//
	// boolean changeFound = true;
	// while (changeFound) {
	// changeFound = false;
	// if (newInstMedia.getComments().getPaging() != null) { // We have some
	// pages,let us deep dive
	// if (newInstMedia.getComments().getPaging().getNext() != null) { // We have
	// next page , let us get it.
	// InstComments nextInstComments = null;
	// try {
	// nextInstComments = (InstComments) instFetcher
	// .fetchNextItems(newInstMedia.getComments().getPaging().getNext(),
	// InstComments.class);
	// // TODO check if all new Media are new
	// } catch (Exception e) {
	// logger.warn(e);
	// }
	// if (nextInstComments != null) {
	// for (InstComment instComment : nextInstComments.getData()) {
	// InstComment currentComment =
	// currentInstMedia.getComments().checkRemoveCommentExist(instComment);
	// if(currentComment == null)
	// currentComment = instComment;
	// currentComment.setUpdated(false);
	// currentComment = resolvePagingReply(currentComment, instComment);
	// if(currentComment.isUpdated()) {
	// changeFound = true;
	// currentComment.setUpdated(false);
	// currentInstMedia.setUpdated(true);
	// }
	// currentInstMedia.getComments().getData().add(currentComment);
	// }
	// currentInstMedia.getComments().setPaging(nextInstComments.getPaging());
	// newInstMedia = currentInstMedia;
	// }
	// }
	// }
	// }
	// return currentInstMedia;
	//
	// }

	public int getRate() {
		return rate;
	}

	public void setRate(String rate) throws Exception {
		logger.debug("Setting fetch rate in minutes :" + rate + " minutes");
		try {
			this.rate = Integer.parseInt(rate);
		} catch (Exception e) {
			throw new Exception("Rate is not defined");
		}

		if (this.rate < 5)
			throw new Exception("Rate should be 5 minutes or more");
		else if (this.rate > 180)
			throw new Exception(
					"Routing rate is too slow, it should be less than 180 minutes not like:" + rate + " minutes");
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public RouterLog getRouterLog() {
		return routerLog;
	}

	private void setRouterLog(RouterLog routerLog) throws Exception {
		if (routerLog == null) {
			setStatus(EngineStatus.Error);
			throw new Exception("Engine Can not start without logger");
		}
		this.routerLog = routerLog;
	}

	public EngineStatus getStatus() {
		return status;
	}

	public void setStatus(EngineStatus status) {
		this.status = status;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

}
