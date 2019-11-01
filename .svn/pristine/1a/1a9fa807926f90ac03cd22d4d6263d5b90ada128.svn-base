package cxengage.facebook.pojos;

public class InstReply {
	private String timestamp;
	private String text;
	private String id;
	private InstUser user;
	private String username;
	private int like_count;
	private boolean route = false;
	private boolean updated;
	public InstUser getUser() {
		return user;
	}
	public void setUser(InstUser user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "InstReply\n [timestamp=" + timestamp + ", text=" + text + ", id=" + id + ", user=" + user + "]";
	}
	public boolean isRoute() {
		return route;
	}
	public void setRoute(boolean route) {
		this.route = route;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
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
