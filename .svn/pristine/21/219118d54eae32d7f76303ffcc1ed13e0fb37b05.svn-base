package cxengage.facebook.pojos;

import java.util.List;

public class InstComments {
	private List<InstComment> data;
	private InstPaging paging;

	public List<InstComment> getData() {
		return data;
	}

	public void setData(List<InstComment> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "InstComments\n [data=" + data + "]";
	}

	public InstPaging getPaging() {
		return paging;
	}

	public void setPaging(InstPaging paging) {
		this.paging = paging;
	}
	public InstComment checkRemoveCommentExist(InstComment instComment) {
		InstComment outCome = null;
		if(data == null)
			return outCome;
		if(data.size() < 1)
			return outCome;
		if(instComment == null)
			return outCome;
		for(InstComment currentInstComment :  data) {
			if(currentInstComment.getId().equals(instComment.getId()))
				outCome = currentInstComment;
		}
		data.remove(outCome);
		return outCome;
	}

}
