package cxengage.facebook.conf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.conf.pojos.Conf;
import cxengage.facebook.conf.pojos.Tenant;

public class Configurator {
	private transient Logger logger = Logger.getLogger(Configurator.class);
	private static Configurator configurator;
	private Conf conf;
	private String confFile;

	private Configurator(String confFile) {
		setConfFile(confFile);
		try {
			loadJsonConf();
		} catch (JsonParseException e) {
			logger.error(e);
		} catch (JsonMappingException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);		}
	}

	public static Configurator getInstance(String confFile) {
		if (configurator == null)
			configurator = new Configurator(confFile);
		return configurator;
	}
	
	private void loadJsonConf() throws JsonParseException, JsonMappingException, IOException {
		logger.debug("Loading Configuration from Json Conf");
		ObjectMapper mapper = new ObjectMapper();
		setConf(mapper.readValue(new File(confFile), Conf.class));
	}

	private void writeJsonConf() throws JsonGenerationException, JsonMappingException, IOException {
		if(getConf() == null)
			return;
		logger.debug("Saving Configuration to Json :"+confFile);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File(confFile), getConf());
	}

	public void save() {
		try {
//			writeConfToGDS();
			writeJsonConf();
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

	public Tenant checkTenant(String id) {
		Tenant result = null;
		if(id == null)
			return null;
		if(getConf() == null)
			return null;
		if(getConf().getTenants() == null || getConf().getTenants().isEmpty())
			return null;
		for(Tenant tenant : getConf().getTenants()) {
			if(tenant != null && tenant.getId() != null)
				if(tenant.getId().equals(id))
					result = tenant;
		}
		return result;
	}
	
	@Deprecated
	public void addTenant(String id, String page, String accessToken,String user,String startTime,String endTime, String queueName, String endPoint, String rate,String tenantId,String mediaType, String startDate) {
		logger.debug("New Tenant with the following page:"+page);
		logger.debug("New Tenant with the following schedule , start:"+startTime+" & end:"+endTime);
		if(getConf() == null)
			setConf(new Conf());
		if(getConf().getTenants() == null)
			getConf().setTenants(new ArrayList<Tenant>());
		getConf().getTenants().add(new Tenant(id,page,accessToken,user, startTime,endTime,queueName,endPoint,rate,tenantId,mediaType,startDate));
		save();
	}
	
	public Tenant updateTenant(Tenant tenant) {
		if(tenant == null)
			return null;
		if(getConf() == null)
			setConf(new Conf());
		if(getConf().getTenants() == null)
			getConf().setTenants(new ArrayList<Tenant>());
		Tenant existingTenant = checkTenant(tenant.getId());
		if(existingTenant == null && !tenant.isRemoveTenant())
			addTenant(tenant);
		else { //Update Existing Tenant
			if(tenant.getId() != null)
				existingTenant.setId(tenant.getId());
			if(tenant.getAccesstoken() != null)
				existingTenant.setAccesstoken(tenant.getAccesstoken());
			if(tenant.getFacebookData() != null)
				existingTenant.setFacebookData(tenant.getFacebookData());
			if(tenant.getInstagramData() != null)
				existingTenant.setInstagramData(tenant.getInstagramData());
			if(tenant.getTwitterData() != null)
				existingTenant.setTwitterData(tenant.getTwitterData());
			if(tenant.getStartTime() != null)
				existingTenant.setStartTime(tenant.getStartTime());
			if(tenant.getEndTime() != null)
				existingTenant.setEndTime(tenant.getEndTime());
			if(tenant.getStartDate() != null)
				existingTenant.setStartDate(tenant.getStartDate());
			if(tenant.getRate() != null)
				existingTenant.setRate(tenant.getRate());
			if(tenant.getNotificationList() != null)
				existingTenant.setNotificationList(tenant.getNotificationList());			
			if(tenant.getUser() != null)
				existingTenant.setUser(tenant.getUser());
			if(tenant.getEndPoint() != null)
				existingTenant.setEndPoint(tenant.getEndPoint());
			if(tenant.getPage() != null)
				existingTenant.setPage(tenant.getPage());
			if(tenant.getMediaType() != null)
				existingTenant.setMediaType(tenant.getMediaType());
			if(tenant.getQueueName() != null)
				existingTenant.setQueueName(tenant.getQueueName());
			if(tenant.getTenantId() != null)
				existingTenant.setTenantId(tenant.getTenantId());
						
			removeTenantByID(tenant.getId());
			if(!tenant.isRemoveTenant())
				addTenant(existingTenant);
			
			return existingTenant;
		}
		return tenant;

	}
	
	public void addTenant(Tenant tenant) {
		logger.debug("New Tenant with the following details:"+tenant.toString());
		if(getConf() == null)
			setConf(new Conf());
		if(getConf().getTenants() == null)
			getConf().setTenants(new ArrayList<Tenant>());
		getConf().getTenants().add(tenant);
		save();
	}
		
	public void removeTenantByID(String id) {
		Tenant tenant = checkTenant(id);
		if(tenant != null)
			getConf().getTenants().remove(tenant);
		save();
	}
	
	public Conf getConf() {
		return conf;
	}

	public void setConf(Conf conf) {
		this.conf = conf;
		
	}

	public String getConfFile() {
		return confFile;
	}

	public void setConfFile(String confFile) {
		this.confFile = confFile;
	}

}
