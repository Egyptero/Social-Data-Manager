package cxengage.facebook.pojos;

public class InstComment {
	private String id;
	private int like_count;
	private String text;
	private String timestamp;
	private InstUser user;
	private String username;
	private InstReplies replies;
	private boolean route = false;
	private boolean updated;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public InstUser getUser() {
		return user;
	}
	public void setUser(InstUser user) {
		this.user = user;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public InstReplies getReplies() {
		return replies;
	}
	public void setReplies(InstReplies replies) {
		this.replies = replies;
	}
	@Override
	public String toString() {
		return "InstComment\n [id=" + id + ", like_count=" + like_count + ", text=" + text + ", timestamp=" + timestamp
				+ ", user=" + user + ", replies=" + replies + "]";
	}
	public boolean isRoute() {
		return route;
	}
	public void setRoute(boolean route) {
		this.route = route;
	}
	public boolean isUpdated() {
		return updated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		InstUser instUser = new InstUser();
		instUser.setUsername(username);
		if(getUser() == null)
			setUser(new InstUser());
		this.username = username;
	}
	
}
