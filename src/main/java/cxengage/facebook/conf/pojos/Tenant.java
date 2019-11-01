package cxengage.facebook.conf.pojos;

public class Tenant {
	private String id;
	private String page; // Should move to instagram section later on
	private String accesstoken; 
	private String user;
	private String startTime;
	private String endTime;
	private String queueName; // Should move in contact center under each channel
	private String endPoint; // Should move in contact center under each channel
	private String rate;
	private String tenantId; // Should move in contact center under each channel
	private String mediaType; // Should move in contact center under each channel
	private String startDate;
	private FacebookData facebookData;
	private InstagramData instagramData;
	private TwitterData twitterData;
	private String engineStatus;
	private String notificationList; // The email notification list in case of error.
	private boolean removeTenant;
	
	public Tenant() {
		
	}
	
	@Deprecated
	public Tenant(String id, String page , String accesstoken,String user,String starttime, String endtime, String queueName, String endPoint, String rate, String tenantId, String mediaType,String startDate) {
		setAccesstoken(accesstoken);
		setId(id);
		setPage(page);
		setUser(user);
		setStartTime(starttime);
		setEndTime(endtime);
		setQueueName(queueName);
		setEndPoint(endPoint);
		setRate(rate);
		setTenantId(tenantId);
		setMediaType(mediaType);
		setStartDate(startDate);
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public boolean ready() {
		if(id == null)
			return false;
		if(accesstoken == null)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String outCome = "{id:\""+id+"\",page:\""+page+"\",accesstoken:\""+accesstoken+"\",user:\""+user+"\",startTime:\""+startTime+"\",endTime:\""+endTime+
				"\",queueName:\""+queueName+"\",endPoint:\""+endPoint+"\",rate:\""+rate+"\",tenantId:\""+tenantId+"\",mediaType:\""+mediaType+"\",startDate:\""+startDate+"\"}";				
		return outCome;
	}

	public FacebookData getFacebookData() {
		return facebookData;
	}

	public void setFacebookData(FacebookData facebookData) {
		this.facebookData = facebookData;
	}

	public InstagramData getInstagramData() {
		return instagramData;
	}

	public void setInstagramData(InstagramData instagramData) {
		this.instagramData = instagramData;
	}

	public TwitterData getTwitterData() {
		return twitterData;
	}

	public void setTwitterData(TwitterData twitterData) {
		this.twitterData = twitterData;
	}

	public String getEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(String engineStatus) {
		this.engineStatus = engineStatus;
	}

	public String getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(String notificationList) {
		this.notificationList = notificationList;
	}

	public boolean isRemoveTenant() {
		return removeTenant;
	}

	public void setRemoveTenant(boolean removeTenant) {
		this.removeTenant = removeTenant;
	}
	
}
