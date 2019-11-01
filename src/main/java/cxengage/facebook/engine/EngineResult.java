package cxengage.facebook.engine;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.conf.pojos.RestAPIData;
import cxengage.facebook.page.outcome.pojos.Feed;

public class EngineResult extends Thread {
	private transient Logger logger = Logger.getLogger(EngineResult.class);
	private EngineResultType resultType;
	private EngineResultSource resultSource;
	private List<Feed> feeds;
	private Engine engine;

	public EngineResult(EngineResultType resultType, EngineResultSource resultSource, List<Feed> feeds, Engine engine) {
		this.resultType = resultType;
		this.resultSource = resultSource;
		this.feeds = feeds;
		this.engine = engine;
	}

	@Override
	public void run() {
		try {
			switch (resultType) {
			case Console:
				submitToDrive();
				break;
			case Rest:
				submitToRest();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error(
					"Failed to send the result to the :" + resultType + ", note that feeds source is :" + resultSource);
			logger.error(e);
		}
	}

	private RestAPIData getRestAPIData() {
		RestAPIData restAPIData = null;
		switch (resultSource) {
		case Facebook:
			restAPIData = engine.getTenant().getFacebookData().getRestAPIData();
			break;
		case Instagram:
			restAPIData = engine.getTenant().getInstagramData().getRestAPIData();
			break;
		case Twitter:
			restAPIData = engine.getTenant().getTwitterData().getRestAPIData();
		default:
			break;
		}
		return restAPIData;
	}

	private void submitToRest() throws Exception {
		if (feeds == null) {
			logger.debug("Can not send the batch to rest for the tenant id=" + engine.getTenant().getId()
					+ " as batch is null");
			return;
		}
		if (feeds.isEmpty()) {
			logger.debug("Can not send the batch to rest for the tenant id=" + engine.getTenant().getId()
					+ " as batch is empty");
			return;
		}

		ObjectMapper mapper = new ObjectMapper();
		String input = mapper.writeValueAsString(feeds);
		logger.debug("Submit to rest the batch of # " + feeds.size() + " feeds from source="+resultSource);

		Client client = ClientBuilder.newClient();

		WebTarget resource = client.target(getRestAPIData().getUri());

		if (getRestAPIData().isInParameterData()) {
			resource = resource.queryParam(getRestAPIData().getParameterName(),
					input);
		}

		Builder request = resource.request(MediaType.APPLICATION_JSON);

		request.accept(MediaType.APPLICATION_JSON);

		Response response = null;
		if (getRestAPIData().isMethodPost()
				&& getRestAPIData().isInBodyData())
			response = request.post(Entity.entity(input, MediaType.APPLICATION_JSON));
		else if (getRestAPIData().isMethodGet())
			response = request.get();

		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			logger.debug("Success! " + response.getStatus());
			logger.debug(response.getEntity());
		} else {
			logger.error("ERROR! " + response.getStatus());
			logger.error(response.getEntity());
		}
		logger.info("Batch of (" + feeds.size() + ") records from the source:" + resultSource + "sent to url:"
				+ getRestAPIData().getUri());

	}

	private void submitToDrive() {
		// Define file name
		if (feeds == null) {
			logger.debug("Can not save the batch to drive for the tenant id=" + engine.getTenant().getId()
					+ " as batch is null");
			return;
		}
		if (feeds.isEmpty()) {
			logger.debug("Can not save the batch to drive for the tenant id=" + engine.getTenant().getId()
					+ " as batch is empty");
			return;
		}
		String routerLogFileName = engine.getRouterLog().getPageName();
		String batchFileName = routerLogFileName.replaceFirst(".json", "_batch_" + new Date().getTime() + ".json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(batchFileName), feeds);
		} catch (JsonGenerationException e) {
			logger.debug("Can not save the batch to drive for the tenant id=" + engine.getTenant().getId());
			logger.error(e);
		} catch (JsonMappingException e) {
			logger.debug("Can not save the batch to drive for the tenant id=" + engine.getTenant().getId());
			logger.error(e);
		} catch (IOException e) {
			logger.debug("Can not save the batch to drive for the tenant id=" + engine.getTenant().getId());
			logger.error(e);
		}

		logger.info("Batch of (" + feeds.size() + ") records from the source:" + resultSource + "saved with name:"
				+ batchFileName);
	}

}
