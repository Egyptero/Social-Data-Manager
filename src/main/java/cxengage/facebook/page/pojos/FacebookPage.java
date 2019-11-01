package cxengage.facebook.page.pojos;

public class FacebookPage {
	private String name;
	private String id;
	private FacebookPagePosts posts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacebookPagePosts getPosts() {
		return posts;
	}
	public void setPosts(FacebookPagePosts posts) {
		this.posts = posts;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FacebookPage\n [name=" + name + ",id="+id+",posts="+posts+"]";
	}

}
