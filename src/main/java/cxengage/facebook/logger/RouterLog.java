package cxengage.facebook.logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.logger.pojos.RoutedRecord;
import cxengage.facebook.logger.pojos.RouterData;

public class RouterLog {
	private transient Logger logger = Logger.getLogger(RouterLog.class);
	private RouterData routerData;
	private String pageName;

	public RouterLog(String pageName) {
		try {
			setPageName(pageName);
			loadJsonLog();
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (routerData == null) {
				logger.error("Routed data can not be found for page:"+getPageName());
			}
		}
	}

	public void save() {
		try {
			writeJsonLog();
//			writeRouterDataToGDS();
		} catch (JsonGenerationException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


	private void loadJsonLog() throws JsonParseException, JsonMappingException, IOException {
		logger.debug("Loading Routed IDs from Json Log");
		ObjectMapper mapper = new ObjectMapper();
		setRouterData(mapper.readValue(new File(pageName), RouterData.class));
	}

	public String getJsonLog() throws JsonGenerationException, JsonMappingException, IOException {
		logger.debug("Saving Routed IDs to Json Log ids.json");
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(routerData);

	}

	private void writeJsonLog() throws JsonGenerationException, JsonMappingException, IOException {
		if (routerData == null)
			return;
		logger.debug("Saving Routed IDs to Json Log ids.json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(pageName), routerData);

	}

	public void addRoutedID(String type, String id) {

		if (getRouterData() == null)
			setRouterData(new RouterData());
		if (getRouterData().getRouted() == null)
			getRouterData().setRouted(new ArrayList<RoutedRecord>());

		getRouterData().getRouted().add(new RoutedRecord(type, id));
		logger.debug("new " + type + " with id:" + id + " is routed now");
		save();
	}

	public boolean checkID(String id) {
		boolean found = false;
		if (getRouterData() == null)
			return false;
		if (getRouterData().getRouted() == null || getRouterData().getRouted().isEmpty())
			return false;

		for (RoutedRecord routedRecord : getRouterData().getRouted()) {
			if (routedRecord.getId() != null) {
				if (routedRecord.getId().equals(id))
					found = true;
			}
		}
		return found;
	}

	public RouterData getRouterData() {
		return routerData;
	}

	public void setRouterData(RouterData routerData) {
		this.routerData = routerData;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
