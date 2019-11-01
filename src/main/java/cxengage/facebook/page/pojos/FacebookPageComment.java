package cxengage.facebook.page.pojos;

public class FacebookPageComment {
	private String id;
	private String link;
	private String created_time;
	private String message;
	private String name;
	private boolean route = false;
	private int like_count;
	private int comment_count;
	private FacebookPageComments comments;
	@Override
	public String toString() {
		return "FacebookPagePost\n [message="+message+",name=" + name + ",id="+id+",link="+link+",created_time="+created_time+",like_count="+like_count+",comment_count="+comment_count+",comments="+comments+"]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacebookPageComments getComments() {
		return comments;
	}
	public void setComments(FacebookPageComments comments) {
		this.comments = comments;
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

	public int getComment_count() {
		return comment_count;
	}

	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

}
