package cxengage.facebook.page.pojos;

import java.util.List;

public class FacebookPageComments {
	private List<FacebookPageComment> data;
	private FacebookPaging paging;
	public List<FacebookPageComment> getData() {
		return data;
	}
	public void setData(List<FacebookPageComment> data) {
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
		return "FacebookPageComments\n [data=" + data + ",paging="+paging+"]";
	}
}
