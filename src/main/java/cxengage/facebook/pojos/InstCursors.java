package cxengage.facebook.pojos;

public class InstCursors {
	private String before;
	private String after;
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	@Override
	public String toString() {
		return "InstCursors\n [before=" + before + ", after=" + after + "]";
	}
	
}
