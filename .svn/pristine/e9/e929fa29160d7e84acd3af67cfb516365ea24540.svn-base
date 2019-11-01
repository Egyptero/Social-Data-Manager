package cxengage.facebook.engine;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.logger.RouterLog;
import cxengage.facebook.pojos.InstBusinessAccount;
import cxengage.facebook.pojos.InstComment;
import cxengage.facebook.pojos.InstComments;
import cxengage.facebook.pojos.InstMedia;
import cxengage.facebook.pojos.InstReplies;
import cxengage.facebook.pojos.InstReply;
import cxengage.genesys.GenesysClient;
import cxengage.instagram.services.InstagramFetcher;
import cxengage.tools.email.SendEmail;

public class InstGenesysEngine extends Thread {
	private transient Logger logger = Logger.getLogger(InstGenesysEngine.class);
	private InstagramFetcher instFetcher;
	private InstBusinessAccount instBusinessAccount;
	private int rate;
	private boolean active = true;
	private RouterLog routerLog;
	private EngineStatus status;
	private Tenant tenant;

	public InstGenesysEngine(InstagramFetcher instFetcher, RouterLog routerLog, Tenant tenant) throws Exception {
		setStatus(EngineStatus.Initiating);
		try {
			setRate(tenant.getRate());
		} catch (Exception e) {
			logger.error(e);
			try {
				logger.info("Setting default rate of 10 minutes");
				setRate("10");
			} catch (Exception e1) {
				logger.error("Issue in setting rate");
				logger.error(e1);
				setStatus(EngineStatus.Error);
				throw e1;
			}
		}
		try {
			setTenant(tenant);
			setRouterLog(routerLog);
			setInstFetcher(instFetcher);
		} catch (Exception e) {
			logger.error("Issue in Fetcher");
			logger.error(e);
			setStatus(EngineStatus.Error);
			throw e;
		}

	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public InstGenesysEngine(RouterLog routerLog, Tenant tenant) throws Exception {
		setStatus(EngineStatus.Initiating);

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
			setInstFetcher(new InstagramFetcher(tenant.getAccesstoken(), tenant.getPage()));
		} catch (Exception e) {
			logger.error(e);
			setStatus(EngineStatus.Error);
			throw e;
		}
	}

	@Override
	public void run() {
		logger.debug("InstGenesys Engine will start now");
		if (getStatus() == EngineStatus.Error) {
			logger.debug("Engine Error");
			setActive(false);
		}
		while (active) {
			try {
				if (checkWorkingHours()) {
					setStatus(EngineStatus.Running);
					logger.debug("Renew Access Token");
					instFetcher.renewAccessToken();
					logger.debug("Fetch will happen now");
					setInstBusinessAccount(instFetcher.reFetchInstBAObject());
					reviewBAObject();
				} else {
					setStatus(EngineStatus.Sleep);
					logger.debug("Outside working hours");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug("Engine Error");
				logger.error(e);
				if(getTenant().getPage() != null)
					SendEmail.getInstance().sendErrorMail("InstGenesys Engine Error for page :"+getTenant().getPage(), e.getStackTrace(),e.getMessage(), getTenant());
				
				setStatus(EngineStatus.Error);
				setActive(false);
				return;
			}
			synchronized (this) {
				try {
					setStatus(EngineStatus.Sleep);
					wait(rate * 60 * 1000);
				} catch (InterruptedException e) {
					logger.debug("Session was interrupted and engine will continue");
				}
			}
		}
		logger.debug("InstGenesys Engine will stop now");
		setStatus(EngineStatus.Stop);
	}

	private boolean checkWorkingHours() {
		if (tenant.getStartTime() == null || tenant.getEndTime() == null) {
			logger.warn("No working hours are defined, engine will keep working day and night !!!");
			return true;
		}
		if (tenant.getStartTime().isEmpty() || tenant.getEndTime().isEmpty()) {
			logger.warn("No working hours are defined, engine will keep working day and night !!!");
			return true;
		}
		DateFormat dateFormatter = new SimpleDateFormat("HH:mm");
		;
		try {
			Date startDateAbs = dateFormatter.parse(tenant.getStartTime());
			Date endDateAbs = dateFormatter.parse(tenant.getEndTime());
			Date startDate = new Date();
			Date endDate = new Date();
			Date nowDate = new Date();
			startDate.setHours(startDateAbs.getHours());
			startDate.setMinutes(startDateAbs.getMinutes());
			startDate.setSeconds(startDateAbs.getSeconds());

			endDate.setHours(endDateAbs.getHours());
			endDate.setMinutes(endDateAbs.getMinutes());
			endDate.setSeconds(endDateAbs.getSeconds());
			if (endDate.getHours() < 12 && nowDate.getHours() >= 12) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(endDate);
				cal.add(Calendar.DATE, 1);
				endDate = cal.getTime();
			}

			if (startDate.getHours() >= 12 && nowDate.getHours() < 12) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(startDate);
				cal.add(Calendar.DATE, -1);
				startDate = cal.getTime();
			}

			if (nowDate.after(startDate) && nowDate.before(endDate))
				return true;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Start Time is:" + tenant.getStartTime() + " , end time is:" + tenant.getEndTime());
		return false;
	}

	private boolean reviewBAObject() {
		logger.debug("Review new comments and feeds");
		if (getInstBusinessAccount() == null) {
			logger.info("Nothing to load, Bussiness Account Object is null");
			return false;
		}

		// TODO Identify my comments
		if (getInstBusinessAccount().getData() == null || getInstBusinessAccount().getData().isEmpty()) {
			logger.info("No Media found");
			return false;
		}
		String myID = getInstBusinessAccount().getId();
		
		// logger.debug("My Business Account ID:"+getInstBusinessAccount());
		for (InstMedia instMedia : getInstBusinessAccount().getData()) {
			if (instMedia.getComments() != null) { // Comments Found
				if (instMedia.getComments().getData() != null) { // Comments list found
					for (InstComment instComment : instMedia.getComments().getData()) {
						logger.debug("comment is="+instComment.toString());
						
						if (instComment.getUser().getId() == null) {// If a new user comment not mine //Check if ID is avail
							if (!getRouterLog().checkID(instComment.getId())) { // If Comment was never routed
								instComment.setRoute(true);
								// getRouterLog().addRoutedID("comment", instComment.getId());
								createInteraction("comment", instComment.getId(), instComment.getText(),
										instComment.getUsername());
							}
						}
						if (instComment.getReplies() != null) { // Replies found
							if (instComment.getReplies().getData() != null) { // Replies list found
								for (InstReply instReply : instComment.getReplies().getData()) {
									if (instReply.getUser().getId() == null) { // If a new user reply not mine
										if (!getRouterLog().checkID(instReply.getId())) { // If reply was never routed
											instReply.setRoute(true);
											// getRouterLog().addRoutedID("reply", instReply.getId());
											createInteraction("reply", instReply.getId(), instReply.getText(),
													instReply.getUsername());
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	private boolean createInteraction(String type, String id, String text, String userName) {
		logger.debug(
				"===== Routing request for type :(" + type + ") and id:(" + id + ")" + ", and text is:(" + text + ") and user is:("+userName+")");
		// TODO test routing to Genesys
		try {
			if (new GenesysClient().submitToGenesys(tenant.getId(), id, text, tenant.getQueueName(),
					tenant.getEndPoint(), userName, tenant.getTenantId(), tenant.getMediaType())) {
				logger.info("Interaction created on Genesys");
				getRouterLog().addRoutedID(type, id);
			}
		} catch (MalformedURLException e) {
			logger.error("Error Creating the interation");
			logger.error(e);
		} catch (RemoteException e) {
			logger.error("Error Creating the interation");
			logger.error(e);
		} catch (ServiceException e) {
			logger.error("Error Creating the interation");
			logger.error(e);
		}
		return true;
	}

	public InstagramFetcher getInstFetcher() {
		return instFetcher;
	}

	public void setInstFetcher(InstagramFetcher instFetcher) {
		this.instFetcher = instFetcher;
	}

	public InstBusinessAccount getInstBusinessAccount() {
		return instBusinessAccount;
	}

	public void setInstBusinessAccount(InstBusinessAccount instBusinessAccount) {
		//if (getInstBusinessAccount() == null)
			this.instBusinessAccount = instBusinessAccount;
			logger.debug(instBusinessAccount.toString());
		//this.instBusinessAccount = resolvePagingMedia(getInstBusinessAccount(),instBusinessAccount);
	}

	private InstBusinessAccount resolvePagingMedia(InstBusinessAccount currentInstBusinessAccount, InstBusinessAccount newInstBusinessAccount) {
		if(newInstBusinessAccount.getData() == null)
			return newInstBusinessAccount;
		
		if(currentInstBusinessAccount.getData() == null) 
			currentInstBusinessAccount.setData(newInstBusinessAccount.getData());

		for (InstMedia instMedia : newInstBusinessAccount.getData()) {
			InstMedia currentMedia = currentInstBusinessAccount.checkRemoveMediaExist(instMedia);
			if(currentMedia == null)
				currentMedia = instMedia;
			currentMedia.setUpdated(false);
			currentMedia = resolvePagingComment(currentMedia, instMedia);
			if(currentMedia.isUpdated()) {
				currentMedia.setUpdated(false);
			}
			currentInstBusinessAccount.getData().add(currentMedia);
		}

		boolean changeFound = true;
		while (changeFound) {
			changeFound = false;
			if (newInstBusinessAccount.getPaging() != null) { // We have some pages,let us deep dive
				if (newInstBusinessAccount.getPaging().getNext() != null) { // We have next page , let us get it.
					InstBusinessAccount nextInstBusinessAccount = null;
					try {
						nextInstBusinessAccount = (InstBusinessAccount) instFetcher
								.fetchNextItems(newInstBusinessAccount.getPaging().getNext(), InstBusinessAccount.class);
						// TODO check if all new Media are new
					} catch (Exception e) {
						logger.warn(e);
					}
					if (nextInstBusinessAccount != null) {
						for (InstMedia instMedia : nextInstBusinessAccount.getData()) {
							InstMedia currentMedia = currentInstBusinessAccount.checkRemoveMediaExist(instMedia);
							if(currentMedia == null)
								currentMedia = instMedia;
							currentMedia.setUpdated(false);
							currentMedia = resolvePagingComment(currentMedia, instMedia);
							if(currentMedia.isUpdated()) {
								changeFound = true;
								currentMedia.setUpdated(false);
							}
							currentInstBusinessAccount.getData().add(currentMedia);
						}
						currentInstBusinessAccount.setPaging(nextInstBusinessAccount.getPaging());
						newInstBusinessAccount = currentInstBusinessAccount;
					}
				}
			}
		}
		return currentInstBusinessAccount;

	}
	private InstComment resolvePagingReply (InstComment currentInstComment ,InstComment newInstComment) {
		if(newInstComment.getReplies() == null)
			return newInstComment;
		
		if(currentInstComment.getReplies() == null) {
			currentInstComment.setReplies(newInstComment.getReplies());
			currentInstComment.setUpdated(true);
		}

		for (InstReply instReply : newInstComment.getReplies().getData()) {
			InstReply currentReply = currentInstComment.getReplies().checkRemoveReplyExist(instReply);
			if(currentReply == null) {
				currentReply = instReply;
				currentInstComment.setUpdated(true);
			}
			currentReply.setUpdated(false);
			currentInstComment.getReplies().getData().add(currentReply);
		}

		boolean changeFound = true;
		while (changeFound) {
			changeFound = false;
			if (newInstComment.getReplies().getPaging() != null) { // We have some pages,let us deep dive
				if (newInstComment.getReplies().getPaging().getNext() != null) { // We have next page , let us get it.
					InstReplies nextInstReplies = null;
					try {
						nextInstReplies = (InstReplies) instFetcher
								.fetchNextItems(newInstComment.getReplies().getPaging().getNext(), InstReplies.class);
						// TODO check if all new Media are new
					} catch (Exception e) {
						logger.warn(e);
					}
					if (nextInstReplies != null) {
						for (InstReply instReply : nextInstReplies.getData()) {
							InstReply currentReply = currentInstComment.getReplies().checkRemoveReplyExist(instReply);
							if(currentReply == null) {
								currentReply = instReply;
								changeFound = true;
								currentInstComment.setUpdated(true);
							}
							currentReply.setUpdated(false);
							currentInstComment.getReplies().getData().add(currentReply);
						}
						currentInstComment.getReplies().setPaging(nextInstReplies.getPaging());
						newInstComment = currentInstComment;
					}
				}
			}
		}
		return currentInstComment;
	}
	private InstMedia resolvePagingComment (InstMedia currentInstMedia ,InstMedia newInstMedia) {
		if(newInstMedia.getComments() == null)
			return newInstMedia;
		
		if(currentInstMedia.getComments() == null) {
			currentInstMedia.setComments(newInstMedia.getComments());
			currentInstMedia.setUpdated(true);
		}

		for (InstComment instComment : newInstMedia.getComments().getData()) {
			InstComment currentComment = currentInstMedia.getComments().checkRemoveCommentExist(instComment);
			if(currentComment == null)
				currentComment = instComment;
			currentComment.setUpdated(false);
			currentComment = resolvePagingReply(currentComment, instComment);
			if(currentComment.isUpdated()) {
				currentComment.setUpdated(false);
				currentInstMedia.setUpdated(true);
			}
			currentInstMedia.getComments().getData().add(currentComment);
		}

		boolean changeFound = true;
		while (changeFound) {
			changeFound = false;
			if (newInstMedia.getComments().getPaging() != null) { // We have some pages,let us deep dive
				if (newInstMedia.getComments().getPaging().getNext() != null) { // We have next page , let us get it.
					InstComments nextInstComments = null;
					try {
						nextInstComments = (InstComments) instFetcher
								.fetchNextItems(newInstMedia.getComments().getPaging().getNext(), InstComments.class);
						// TODO check if all new Media are new
					} catch (Exception e) {
						logger.warn(e);
					}
					if (nextInstComments != null) {
						for (InstComment instComment : nextInstComments.getData()) {
							InstComment currentComment = currentInstMedia.getComments().checkRemoveCommentExist(instComment);
							if(currentComment == null)
								currentComment = instComment;
							currentComment.setUpdated(false);
							currentComment = resolvePagingReply(currentComment, instComment);
							if(currentComment.isUpdated()) {
								changeFound = true;
								currentComment.setUpdated(false);
								currentInstMedia.setUpdated(true);
							}
							currentInstMedia.getComments().getData().add(currentComment);
						}
						currentInstMedia.getComments().setPaging(nextInstComments.getPaging());
						newInstMedia = currentInstMedia;
					}
				}
			}
		}
		return currentInstMedia;

	}

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
