package cxengage.facebook.pojos;

import java.util.List;

public class InstBusinessAccount {
	private List<InstMedia> data;
	private InstPaging paging;
	private String id;
	private String pageID;
	private String pageName;
	public List<InstMedia> getData() {
		return data;
	}
	public void setData(List<InstMedia> data) {
		this.data = data;
	}
	public InstPaging getPaging() {
		return paging;
	}
	public void setPaging(InstPaging paging) {
		this.paging = paging;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageID() {
		return pageID;
	}
	public void setPageID(String pageID) {
		this.pageID = pageID;
	}
	@Override
	public String toString() {
		return "InstBusinessAccount\n [data=" + data + ", paging=" + paging + ", id=" + id + ", pageID=" + pageID + "]";
	}
	public InstMedia checkRemoveMediaExist(InstMedia instMedia) {
		InstMedia outCome = null;
		if(data == null)
			return outCome;
		if(data.size() < 1)
			return outCome;
		if(instMedia == null)
			return outCome;
		for(InstMedia currentInstMedia :  data) {
			if(currentInstMedia.getId().equals(instMedia.getId())) 
				outCome = currentInstMedia;
				
		}
		data.remove(outCome);
		return outCome;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
