package cxengage.facebook.logger.pojos;

public class RoutedRecord {
	private String type;
	private String id;
	public RoutedRecord() {
		
	}
	public RoutedRecord(String type, String id) {
		setId(id);
		setType(type);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
