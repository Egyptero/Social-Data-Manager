package cxengage.facebook.pojos;

import java.util.List;

public class InstReplies {
	private List<InstReply> data;
	private InstPaging paging;

	public List<InstReply> getData() {
		return data;
	}

	public void setData(List<InstReply> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "InstReplies [data=\n" + data + "]";
	}

	public InstPaging getPaging() {
		return paging;
	}

	public void setPaging(InstPaging paging) {
		this.paging = paging;
	}

	public InstReply checkRemoveReplyExist(InstReply instReply) {
		InstReply outCome = null;
		if(data == null)
			return outCome;
		if(data.size() < 1)
			return outCome;
		if(instReply == null)
			return outCome;
		for(InstReply currentInstReply :  data) {
			if(currentInstReply.getId().equals(instReply.getId()))
				outCome = currentInstReply;
		}
		data.remove(outCome);
		return outCome;
	}
}
