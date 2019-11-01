package cxengage.facebook.engine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;
import cxengage.facebook.page.outcome.pojos.Feed;
import cxengage.facebook.pojos.InstBusinessAccount;
import cxengage.facebook.pojos.InstComment;
import cxengage.facebook.pojos.InstMedia;
import cxengage.facebook.pojos.InstReply;
import cxengage.instagram.services.InstagramFetcher;

public class InstagramEngine extends Engine {
	private transient Logger logger = Logger.getLogger(InstagramEngine.class);
	private List<InstagramFetcher> instFetchers;
	private List<InstBusinessAccount> instBusinessAccounts;
	private int rate;
	private boolean active = true;
	private RouterLog routerLog;
	private EngineStatus status;
	private Tenant tenant;

	public InstagramEngine(RouterLog routerLog, Tenant tenant, boolean checkHistory) throws Exception {
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

			for (String pageId : tenant.getInstagramData().getPages())
				addInstFetcher(new InstagramFetcher(tenant.getAccesstoken(), pageId));

		} catch (Exception e) {
			logger.error(e);
			setStatus(EngineStatus.Error);
			throw e;
		}
	}

	public void performEngineJob() throws Exception {
		for (InstagramFetcher instFetcher : instFetchers) {
			logger.debug("Renew Access Token for page ID:" + instFetcher.getPage());
			instFetcher.renewAccessToken();
			logger.debug("Fetch will happen now for page ID:" + instFetcher.getPage());
			addInstBusinessAccount(instFetcher.reFetchInstBAObject());
		}
		while (instBusinessAccounts != null && instBusinessAccounts.size() > 0 && isActive())
			reviewBAs();
		setStatus(EngineStatus.Stop);
	}


	private boolean reviewBAs() throws Exception{

		logger.debug("Review Instagram BA new comments and feeds");
		List<InstBusinessAccount> processingList = new ArrayList<InstBusinessAccount>();
		List<Feed> instagramFeeds = new ArrayList<Feed>();
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

		if (getInstBusinessAccounts() == null) {
			logger.info("Nothing to load, Bussiness Account Object is null");
			return false;
		}
		// Move the list of pages to processing list.
		for (InstBusinessAccount instBusinessAccount : instBusinessAccounts) {
			processingList.add(instBusinessAccount);
		}
		instBusinessAccounts = new ArrayList<InstBusinessAccount>();
		for (InstBusinessAccount instBusinessAccount : processingList) {

			if (instBusinessAccount.getData() == null || instBusinessAccount.getData().isEmpty()) {
				logger.info("No Media found to load ");
				return false;
			} else {
				for (InstMedia instMedia : instBusinessAccount.getData()) {
					Feed instagramFeed = new Feed();
					instagramFeed.setPageId(instBusinessAccount.getPageID());
					instagramFeed.setPageName(instBusinessAccount.getPageName());
					instagramFeed.setPostCommentCount(instMedia.getComments_count());
					instagramFeed.setPostCreationTime(instMedia.getTimestamp());
					instagramFeed.setPostId(instMedia.getId());
					instagramFeed.setPostLikeCount(instMedia.getLike_count());
					instagramFeed.setPostLink(instMedia.getPermalink());
					instagramFeed.setPostMessage(instMedia.getCaption());
					instagramFeed.setPostMediaUrl(instMedia.getMedia_url());
					instagramFeed.setSource("Instagram");

					if (checkHistory) {
						instMedia.setRoute(true);
						if (tenant.getInstagramData().isPosts())
							instagramFeeds.add(instagramFeed.clone());

					} else if (!getRouterLog().checkID(instMedia.getId())) { // If Comment was never routed
						instMedia.setRoute(true);
						getRouterLog().addRoutedID(instBusinessAccount.getPageName() + "_post", instMedia.getId());

						if (tenant.getInstagramData().isPosts())
							instagramFeeds.add(instagramFeed.clone());
					}

					if (instMedia.getComments() != null) { // Comments Found
						if (instMedia.getComments().getData() != null) { // Comments list found
							for (InstComment instComment : instMedia.getComments().getData()) {

								instagramFeed.setCommentCreationTime(instComment.getTimestamp());
								instagramFeed.setCommentId(instComment.getId());
								instagramFeed.setCommentLikeCount(instComment.getLike_count());
								instagramFeed.setCommentMessage(instComment.getText());

								if (checkHistory) {
									instComment.setRoute(true);
									if (tenant.getInstagramData().isComments())
										instagramFeeds.add(instagramFeed.clone());

								} else if (!getRouterLog().checkID(instComment.getId())) { // If Comment was
																							// never
									// routed
									instComment.setRoute(true);
									getRouterLog().addRoutedID(instBusinessAccount.getPageName() + "_comment",
											instComment.getId());

									if (tenant.getInstagramData().isComments())
										instagramFeeds.add(instagramFeed.clone());
									// createInteraction("comment", instComment.getId(), instComment.getText(),
									// instComment.getUser().getUsername());
								}

								if (instComment.getReplies() != null) { // Replies found
									if (instComment.getReplies().getData() != null) { // Replies list found
										for (InstReply instReply : instComment.getReplies().getData()) {

											instagramFeed.setReplyCreationTime(instReply.getTimestamp());
											instagramFeed.setReplyId(instReply.getId());
											instagramFeed.setReplyLikeCount(instReply.getLike_count());
											instagramFeed.setReplyMessage(instReply.getText());

											if (checkHistory) {
												instReply.setRoute(true);
												if (tenant.getInstagramData().isReplies())
													instagramFeeds.add(instagramFeed.clone());
											} else if (!getRouterLog().checkID(instReply.getId())) { // If
																										// reply
																										// was
												// never routed
												instReply.setRoute(true);
												getRouterLog().addRoutedID(instBusinessAccount.getPageName() + "_reply",
														instReply.getId());

												if (tenant.getInstagramData().isReplies())
													instagramFeeds.add(instagramFeed.clone());
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

					if (instMedia.getTimestamp() != null)
						postDate = fbSDF.parse(instMedia.getTimestamp());

					if (postDate.before(startDate))
						reachDate = true;
				}
				if (checkHistory && !reachDate) {
					if (instBusinessAccount.getPaging() != null
							&& instBusinessAccount.getPaging().getNext() != null)
						try {
							InstBusinessAccount nextInstaBusinessAccount = (InstBusinessAccount) new InstagramFetcher(
									tenant.getAccesstoken(), null).fetchNextItems(
											instBusinessAccount.getPaging().getNext(), InstBusinessAccount.class);
							nextInstaBusinessAccount.setId(instBusinessAccount.getId());
							nextInstaBusinessAccount.setPageName(instBusinessAccount.getPageName());
							if (nextInstaBusinessAccount != null)
								instBusinessAccounts.add(nextInstaBusinessAccount);
						} catch (Exception e) {
							logger.debug(e);
						}
				}
				reachDate = false;

			}
		}

		if (getTenant().getInstagramData().isConsole()) {
			new EngineResult(EngineResultType.Console, EngineResultSource.Instagram, instagramFeeds, this).run();
			logger.debug("Instagram Batch Started to save");
		}

		if (getTenant().getInstagramData().isRestAPI()) {
			new EngineResult(EngineResultType.Rest, EngineResultSource.Instagram, instagramFeeds, this).run();
			logger.debug("Instagram Batch Started to save");
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

	public List<InstagramFetcher> getInstFetchers() {
		return instFetchers;
	}

	public void addInstFetcher(InstagramFetcher instFetcher) {
		if (instFetchers == null)
			instFetchers = new ArrayList<InstagramFetcher>();
		instFetchers.add(instFetcher);
	}

	public List<InstBusinessAccount> getInstBusinessAccounts() {
		return instBusinessAccounts;
	}

	public void addInstBusinessAccount(InstBusinessAccount instBusinessAccount) {

		if (instBusinessAccounts == null)
			instBusinessAccounts = new ArrayList<InstBusinessAccount>();

		instBusinessAccounts.add(instBusinessAccount);
	}

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

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
