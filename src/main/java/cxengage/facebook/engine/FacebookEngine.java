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
import cxengage.facebook.page.pojos.FacebookPage;
import cxengage.facebook.page.pojos.FacebookPageComment;
import cxengage.facebook.page.pojos.FacebookPagePost;
import cxengage.facebook.page.pojos.FacebookPagePosts;
import cxengage.facebook.services.FacebookFetcher;

public class FacebookEngine extends Engine {
	private transient Logger logger = Logger.getLogger(FacebookEngine.class);
	private List<FacebookFetcher> facebookFetchers;
	private List<FacebookPage> facebookPages;
	private int rate;
	private boolean active = true;
	private RouterLog routerLog;
	private EngineStatus status;
	private Tenant tenant;

	public FacebookEngine(RouterLog routerLog, Tenant tenant, boolean checkHistory) throws Exception {
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
			for (String pageId : tenant.getFacebookData().getPages())
				addFacebookFetcher(new FacebookFetcher(tenant.getAccesstoken(), pageId));

		} catch (Exception e) {
			logger.error(e);
			setStatus(EngineStatus.Error);
			throw e;
		}
	}

	public void performEngineJob() throws Exception {
		for (FacebookFetcher facebookFetcher : facebookFetchers) {
			logger.debug("Renew Access Token for page ID:" + facebookFetcher.getPage());
			facebookFetcher.renewAccessToken();
			logger.debug("Fetch will happen now for page ID:" + facebookFetcher.getPage());
			addFacebookPage(facebookFetcher.reFetchFacebookPageObject());
		}
		while (facebookPages != null && facebookPages.size() > 0 && isActive())
			reviewPages();
		setStatus(EngineStatus.Stop);
	}

	private boolean reviewPages() throws ParseException {
		logger.debug("Review Pages new comments and feeds");
		List<FacebookPage> processingList = new ArrayList<FacebookPage>();
		List<Feed> facebookFeeds = new ArrayList<Feed>();
		boolean reachDate = false;

		SimpleDateFormat orgDataSDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fbSDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSSS");
		Date startDate = null;
		Date postDate = null;
		if (getTenant().getStartDate() != null)
			startDate = orgDataSDF.parse(getTenant().getStartDate());
		else {
			startDate = new Date();
			reachDate = true;
		}

		if (getFacebookPages() == null) {
			logger.info("Nothing to load, Bussiness Account Object is null");
			return false;
		}
		// Move the list of pages to processing list.
		for (FacebookPage facebookPage : facebookPages) {
			processingList.add(facebookPage);
		}
		facebookPages = new ArrayList<FacebookPage>();

		for (FacebookPage facebookPage : processingList) {
			// TODO Identify my comments
			if (facebookPage.getPosts() != null)
				if (facebookPage.getPosts().getData() == null || facebookPage.getPosts().getData().isEmpty()) {
					logger.info("No Posts found in the page :" + facebookPage.getName());
				} else {
					logger.info("Review page=" + facebookPage.getName() + " and # of posts is="
							+ facebookPage.getPosts().getData().size());
					for (FacebookPagePost facebookPagePost : facebookPage.getPosts().getData()) {
						Feed facebookFeed = new Feed();
						facebookFeed.setPageId(facebookPage.getId());
						facebookFeed.setPageName(facebookPage.getName());
						facebookFeed.setPostCommentCount(facebookPagePost.getComment_count());
						facebookFeed.setPostCreationTime(facebookPagePost.getCreated_time());
						facebookFeed.setPostId(facebookPagePost.getId());
						facebookFeed.setPostLikeCount(facebookPagePost.getLike_count());
						facebookFeed.setPostLink(facebookPagePost.getLink());
						facebookFeed.setPostMessage(facebookPagePost.getMessage());
						facebookFeed.setPostName(facebookPagePost.getName());
						facebookFeed.setSource("Facebook Page");

						if (checkHistory) {
							facebookPagePost.setRoute(true);
							if (tenant.getFacebookData().isPosts())
								facebookFeeds.add(facebookFeed.clone());

						} else if (!getRouterLog().checkID(facebookPagePost.getId())) { // If Comment was never routed
							facebookPagePost.setRoute(true);
							getRouterLog().addRoutedID(facebookPage.getName() + "_post", facebookPagePost.getId());

							if (tenant.getFacebookData().isPosts())
								facebookFeeds.add(facebookFeed.clone());
						}

						if (facebookPagePost.getComments() != null) { // Comments Found
							if (facebookPagePost.getComments().getData() != null) { // Comments list found
								for (FacebookPageComment facebookPageComment : facebookPagePost.getComments()
										.getData()) {
									facebookFeed.setCommentCommentCount(facebookPageComment.getComment_count());
									facebookFeed.setCommentCreationTime(facebookPageComment.getCreated_time());
									facebookFeed.setCommentId(facebookPageComment.getId());
									facebookFeed.setCommentLikeCount(facebookPageComment.getLike_count());
									facebookFeed.setCommentLink(facebookPageComment.getLink());
									facebookFeed.setCommentMessage(facebookPageComment.getMessage());
									facebookFeed.setCommentName(facebookPageComment.getName());

									if (checkHistory) {
										facebookPageComment.setRoute(true);
										if (tenant.getFacebookData().isComments())
											facebookFeeds.add(facebookFeed.clone());

									} else if (!getRouterLog().checkID(facebookPageComment.getId())) { // If Comment was
																										// never
										// routed
										facebookPageComment.setRoute(true);
										getRouterLog().addRoutedID(facebookPage.getName() + "_comment",
												facebookPageComment.getId());

										if (tenant.getFacebookData().isComments())
											facebookFeeds.add(facebookFeed.clone());
										// createInteraction("comment", instComment.getId(), instComment.getText(),
										// instComment.getUser().getUsername());
									}

									if (facebookPageComment.getComments() != null) { // Replies found
										if (facebookPageComment.getComments().getData() != null) { // Replies list found
											for (FacebookPageComment facebookPageReply : facebookPageComment
													.getComments().getData()) {
												facebookFeed.setReplyCommentCount(facebookPageReply.getComment_count());
												facebookFeed.setReplyCreationTime(facebookPageReply.getCreated_time());
												facebookFeed.setReplyId(facebookPageReply.getId());
												facebookFeed.setReplyLikeCount(facebookPageReply.getLike_count());
												facebookFeed.setReplyLink(facebookPageReply.getLink());
												facebookFeed.setReplyMessage(facebookPageReply.getMessage());
												facebookFeed.setReplyName(facebookPageReply.getName());

												if (checkHistory) {
													facebookPageReply.setRoute(true);
													if (tenant.getFacebookData().isReplies())
														facebookFeeds.add(facebookFeed.clone());
												} else if (!getRouterLog().checkID(facebookPageReply.getId())) { // If
																													// reply
																													// was
													// never routed
													facebookPageReply.setRoute(true);
													getRouterLog().addRoutedID(facebookPage.getName() + "_reply",
															facebookPageReply.getId());

													if (tenant.getFacebookData().isReplies())
														facebookFeeds.add(facebookFeed.clone());
													// createInteraction("reply", instReply.getId(),
													// instReply.getText(),
													// instReply.getUser().getUsername());
												}
											}
										}
									}
								}
							}
						}
						if (facebookPagePost.getCreated_time() != null)
							postDate = fbSDF.parse(facebookPagePost.getCreated_time());

						if (postDate.before(startDate))
							reachDate = true;
					}

					if (checkHistory && !reachDate) {
						if (facebookPage.getPosts().getPaging() != null
								&& facebookPage.getPosts().getPaging().getNext() != null)
							try {
								FacebookPagePosts nextPagePosts = (FacebookPagePosts) new FacebookFetcher(
										tenant.getAccesstoken(), null).fetchNextItems(
												facebookPage.getPosts().getPaging().getNext(), FacebookPagePosts.class);
								FacebookPage nextPage = new FacebookPage();
								nextPage.setPosts(nextPagePosts);
								nextPage.setId(facebookPage.getId());
								nextPage.setName(facebookPage.getName());
								if (nextPage != null)
									facebookPages.add(nextPage);
							} catch (Exception e) {
								logger.debug(e);
							}
					}
					reachDate = false;
				}
		}
		if (getTenant().getFacebookData().isConsole()) {
			new EngineResult(EngineResultType.Console,EngineResultSource.Facebook, facebookFeeds, this).run();
			logger.debug("Facebook Page Batch Started to save");
		}

		if (getTenant().getFacebookData().isRestAPI()) {
			new EngineResult(EngineResultType.Rest,EngineResultSource.Facebook, facebookFeeds, this).run();
			logger.debug("Facebook Page Batch Started to save");
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

	public List<FacebookFetcher> getFacebookFetchers() {
		return facebookFetchers;
	}

	public void addFacebookFetcher(FacebookFetcher facebookFetcher) {
		if (facebookFetchers == null)
			facebookFetchers = new ArrayList<FacebookFetcher>();
		facebookFetchers.add(facebookFetcher);
	}

	public List<FacebookPage> getFacebookPages() {
		return facebookPages;
	}

	public void addFacebookPage(FacebookPage facebookPage) {
		if (facebookPages == null)
			facebookPages = new ArrayList<FacebookPage>();

		facebookPages.add(facebookPage);
		// this.facebookPage = resolvePagingMedia(getFacebookPage(),facebookPage);
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

}
