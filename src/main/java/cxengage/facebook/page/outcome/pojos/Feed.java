package cxengage.facebook.page.outcome.pojos;

public class Feed {
	private String pageId;
	private String pageName;
	private String postId;
	private String postMessage;
	private String postCreationTime;
	private int postCommentCount;
	private int postLikeCount;
	private String postLink;
	private String postName;
	private String postMediaUrl;
	private String postUserName;
	private String postUserID;
	private String commentId;
	private String commentMessage;
	private String commentCreationTime;
	private int commentCommentCount;
	private int commentLikeCount;
	private String commentLink;
	private String commentName;
	private String commentUserName;
	private String commentUserID;
	private String commentUserGeo;
	private String replyId;
	private String replyMessage;
	private String replyCreationTime;
	private int replyCommentCount;
	private int replyLikeCount;
	private String replyLink;
	private String replyName;
	private String replyUserName;
	private String replyUserID;
	private String source;
	
	
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostMessage() {
		return postMessage;
	}
	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}
	public String getPostCreationTime() {
		return postCreationTime;
	}
	public void setPostCreationTime(String postCreationTime) {
		this.postCreationTime = postCreationTime;
	}
	public String getPostLink() {
		return postLink;
	}
	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getCommentMessage() {
		return commentMessage;
	}
	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}
	public String getCommentCreationTime() {
		return commentCreationTime;
	}
	public void setCommentCreationTime(String commentCreationTime) {
		this.commentCreationTime = commentCreationTime;
	}
	public String getCommentLink() {
		return commentLink;
	}
	public void setCommentLink(String commentLink) {
		this.commentLink = commentLink;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	public String getReplyCreationTime() {
		return replyCreationTime;
	}
	public void setReplyCreationTime(String replyCreationTime) {
		this.replyCreationTime = replyCreationTime;
	}
	public String getReplyLink() {
		return replyLink;
	}
	public void setReplyLink(String replyLink) {
		this.replyLink = replyLink;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public int getPostCommentCount() {
		return postCommentCount;
	}
	public void setPostCommentCount(int postCommentCount) {
		this.postCommentCount = postCommentCount;
	}
	public int getPostLikeCount() {
		return postLikeCount;
	}
	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	public int getCommentCommentCount() {
		return commentCommentCount;
	}
	public void setCommentCommentCount(int commentCommentCount) {
		this.commentCommentCount = commentCommentCount;
	}
	public int getCommentLikeCount() {
		return commentLikeCount;
	}
	public void setCommentLikeCount(int commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}
	public int getReplyCommentCount() {
		return replyCommentCount;
	}
	public void setReplyCommentCount(int replyCommentCount) {
		this.replyCommentCount = replyCommentCount;
	}
	public int getReplyLikeCount() {
		return replyLikeCount;
	}
	public void setReplyLikeCount(int replyLikeCount) {
		this.replyLikeCount = replyLikeCount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPostMediaUrl() {
		return postMediaUrl;
	}
	public void setPostMediaUrl(String postMediaUrl) {
		this.postMediaUrl = postMediaUrl;
	}
	
	public String getPostUserName() {
		return postUserName;
	}
	public void setPostUserName(String postUserName) {
		this.postUserName = postUserName;
	}
	public String getPostUserID() {
		return postUserID;
	}
	public void setPostUserID(String postUserID) {
		this.postUserID = postUserID;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentUserID() {
		return commentUserID;
	}
	public void setCommentUserID(String commentUserID) {
		this.commentUserID = commentUserID;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
	public String getReplyUserID() {
		return replyUserID;
	}
	public void setReplyUserID(String replyUserID) {
		this.replyUserID = replyUserID;
	}
	public Feed clone() {
		Feed feed = new Feed();
		feed.setCommentCommentCount(getCommentCommentCount());
		feed.setCommentCreationTime(getCommentCreationTime());
		feed.setCommentId(getCommentId());
		feed.setCommentLikeCount(getCommentLikeCount());
		feed.setCommentLink(getCommentLink());
		feed.setCommentMessage(getCommentMessage());
		feed.setCommentName(getCommentName());
		feed.setCommentUserName(getPostUserName());
		feed.setCommentUserID(getPostUserID());
		feed.setCommentUserGeo(getCommentUserGeo());
		
		feed.setPageId(getPageId());
		feed.setPageName(getPageName());
		feed.setPostCommentCount(getPostCommentCount());
		feed.setPostCreationTime(getPostCreationTime());
		feed.setPostId(getPostId());
		feed.setPostLikeCount(getPostLikeCount());
		feed.setPostLink(getPostLink());
		feed.setPostMediaUrl(getPostMediaUrl());
		feed.setPostMessage(getPostMessage());
		feed.setPostName(getPostName());
		feed.setPostUserName(getPostUserName());
		feed.setPostUserID(getPostUserID());
		
		feed.setReplyCommentCount(getReplyCommentCount());
		feed.setReplyCreationTime(getReplyCreationTime());
		feed.setReplyId(getReplyId());
		feed.setReplyLikeCount(getReplyLikeCount());
		feed.setReplyLink(getReplyLink());
		feed.setReplyMessage(getReplyMessage());
		feed.setReplyName(getReplyName());
		feed.setReplyUserName(getPostUserName());
		feed.setReplyUserID(getPostUserID());
		
		feed.setSource(getSource());
		return feed;
	}
	public String getCommentUserGeo() {
		return commentUserGeo;
	}
	public void setCommentUserGeo(String commentUserGeo) {
		this.commentUserGeo = commentUserGeo;
	}
}
