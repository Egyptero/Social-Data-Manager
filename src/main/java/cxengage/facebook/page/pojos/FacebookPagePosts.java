package cxengage.facebook.page.pojos;

import java.util.List;

public class FacebookPagePosts {
	private List<FacebookPagePost> data;
	private FacebookPaging paging;
	public List<FacebookPagePost> getData() {
		return data;
	}
	public void setData(List<FacebookPagePost> data) {
		this.data = data;
	}
	public FacebookPaging getPaging() {
		return paging;
	}
	public void setPaging(FacebookPaging paging) {
		this.paging = paging;
	}
	@Override
	public String toString() {
		return "FacebookPagePosts\n [data=" + data + ",paging="+paging+"]";
	}
	

}
