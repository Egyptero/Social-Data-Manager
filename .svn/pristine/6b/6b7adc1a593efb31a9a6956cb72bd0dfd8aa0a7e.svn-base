package cxengage.facebook.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import cxengage.facebook.conf.Configurator;
import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.engine.Engine;
import cxengage.facebook.engine.EngineStatus;
import cxengage.facebook.engine.FacebookEngine;
import cxengage.facebook.engine.InstGenesysEngine;
import cxengage.facebook.engine.InstagramEngine;
import cxengage.facebook.engine.TwitterEngine;
import cxengage.facebook.logger.RouterLog;
import cxengage.facebook.rest.outcome.Outcome;
import cxengage.instagram.services.InstagramFetcher;
import cxengage.tools.ManualDataLoader;

@Path("/InstRest")
public class InstRest {
	
	private transient Logger logger = Logger.getLogger(InstRest.class);
	@Context
	private HttpServletRequest request;
	@GET
	@Path("/ManualDataLoad")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome manualLoadData() {
		Outcome outcome = new Outcome();
		outcome.setError(false);
		ManualDataLoader manualDataLoader = new ManualDataLoader();
		manualDataLoader.start();
		return outcome;
	}
	
	@GET
	@Path("/StartHistoryVer13")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome startHistoryVer13(@QueryParam("id") String id,@QueryParam("type") String type,@QueryParam("searchType") String searchType) {
		logger.debug("Start Getting History Method is invoked and will try to start engine of type:"+type);
		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
			return outcome;
		}
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find your information");
			return outcome;
		}
		
		if(tenant.getStartDate() == null) {
			outcome.setError(true);
			outcome.setErrorMessage("You need to define start date first");
			return outcome;
		}
			
		outcome.setTenant(tenant);
		String engineIDRef = id+"_"+type+"History";
		if(type.equals("Twitter"))
			engineIDRef = id+"_"+type+"History"+"_"+searchType;
		
		Engine engine = (Engine) request.getSession().getServletContext()
				.getAttribute(engineIDRef);
		if (engine == null) {
			RouterLog routerLog = new RouterLog(
					request.getSession().getServletContext().getRealPath("WEB-INF/" + id + "_"+type+"History.json"));
			try {
				if(type.equals("FB"))
					engine = new FacebookEngine(routerLog,tenant,true);
				else if(type.equals("Insta"))
					engine = new InstagramEngine(routerLog,tenant,true);
				else if(type.equals("Twitter")) {
					if(searchType.equals("standard"))
						engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.Standard);
					else if(searchType.equals("30days"))
						engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.Prem30Day);
					else if(searchType.equals("full"))
						engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.PremFullArch);
						
				}

				request.getSession().getServletContext().setAttribute(engineIDRef, engine);
				engine.start();
			} catch (Exception e) {
				logger.error(e);
				outcome.setError(true);
				outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
				return outcome;
			}
		} else {
			if (engine.getStatus() == EngineStatus.Stop
					|| engine.getStatus() == EngineStatus.Error
					|| engine.getStatus() == EngineStatus.Unknown) {
				request.getSession().getServletContext()
				.removeAttribute(engineIDRef);
				RouterLog routerLog = new RouterLog(
						request.getSession().getServletContext().getRealPath("WEB-INF/" + tenant.getId() + "_"+type+"History.json"));
				try {
					if(type.equals("FB"))
						engine = new FacebookEngine(routerLog,tenant,true);
					else if(type.equals("Insta"))
						engine = new InstagramEngine(routerLog,tenant,true);
					else if(type.equals("Twitter")) {
						if(searchType.equals("standard"))
							engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.Standard);
						else if(searchType.equals("30days"))
							engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.Prem30Day);
						else if(searchType.equals("full"))
							engine= new TwitterEngine(routerLog, tenant, true,TwitterEngine.SearchType.PremFullArch);
							
					}

					request.getSession().getServletContext().setAttribute(engineIDRef, engine);
					engine.start();
				} catch (Exception e) {
					logger.error(e);
					outcome.setError(true);
					outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
					return outcome;
				}
			} else {
				outcome.setError(true);
				outcome.setErrorMessage("Can not start historical engine as current status is:" + engine.getState());
			}
		}
		//outcome.setFacebookStatus(getFBEngineStatus(id+"_FB"));
		//outcome.setRate(getEngineRate(id+"_FB"));
		return outcome;
	}
	
	@GET
	@Path("/StartVer13")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome startVer13(@QueryParam("id") String id,@QueryParam("type") String type) {
		logger.debug("Start Method is invoked and will try to start engine of type:"+type);
		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
			return outcome;
		}
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find your information");
			return outcome;
		}
		outcome.setTenant(tenant);
		String engineIDRef = id+"_"+type;
		if(type.equals("Twitter"))
			engineIDRef = id+"_"+type+"_StandardRealTime";

		Engine engine = (Engine) request.getSession().getServletContext()
				.getAttribute(engineIDRef);
		if (engine == null) {
			RouterLog routerLog = new RouterLog(
					request.getSession().getServletContext().getRealPath("WEB-INF/" + id + "_"+type+".json"));
			try {
				if(type.equals("FB"))
					engine = new FacebookEngine(routerLog,tenant,false);
				else if(type.equals("Insta"))
					engine = new InstagramEngine(routerLog,tenant,false);
				else if(type.equals("Twitter"))
					engine= new TwitterEngine(routerLog, tenant, false,TwitterEngine.SearchType.Standard);

				request.getSession().getServletContext().setAttribute(engineIDRef, engine);
				engine.start();
			} catch (Exception e) {
				logger.error(e);
				outcome.setError(true);
				outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
				return outcome;
			}
		} else {
			if (engine.getStatus() == EngineStatus.Stop
					|| engine.getStatus() == EngineStatus.Error
					|| engine.getStatus() == EngineStatus.Unknown) {
				request.getSession().getServletContext()
				.removeAttribute(engineIDRef);
				RouterLog routerLog = new RouterLog(
						request.getSession().getServletContext().getRealPath("WEB-INF/" + tenant.getId() + "_"+type+".json"));
				try {
					
					if(type.equals("FB"))
						engine = new FacebookEngine(routerLog,tenant,false);
					else if(type.equals("Insta"))
						engine = new InstagramEngine(routerLog,tenant,false);
					else if(type.equals("Twitter"))
						engine= new TwitterEngine(routerLog, tenant, false,TwitterEngine.SearchType.Standard);
					
					request.getSession().getServletContext().setAttribute(engineIDRef, engine);
					engine.start();
				} catch (Exception e) {
					logger.error(e);
					outcome.setError(true);
					outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
					return outcome;
				}
			} else {
				outcome.setError(true);
				outcome.setErrorMessage("Can not start engine as current status is:" + engine.getState());
			}
		}
		//outcome.setFacebookStatus(getFBEngineStatus(id+"_FB"));
		//outcome.setRate(getEngineRate(id+"_FB"));
		return outcome;
	}

	@GET
	@Path("/Start")
	@Produces(MediaType.APPLICATION_XML)
	@Deprecated
	public Outcome start(@QueryParam("id") String id) {
		logger.debug("Start Method is invoked and will try to start engine");
		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
			return outcome;
		}
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find the user information");
			return outcome;
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		if (instGenesysEngine == null) {
			RouterLog routerLog = new RouterLog(
					request.getSession().getServletContext().getRealPath("WEB-INF/" + tenant.getPage() + ".json"));
			try {
				instGenesysEngine = new InstGenesysEngine(routerLog,tenant);
				request.getSession().getServletContext().setAttribute(id, instGenesysEngine);
				instGenesysEngine.start();
			} catch (Exception e) {
				logger.error(e);
				outcome.setError(true);
				outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
				return outcome;
			}
		} else {
			if (instGenesysEngine.getStatus() == EngineStatus.Stop
					|| instGenesysEngine.getStatus() == EngineStatus.Error
					|| instGenesysEngine.getStatus() == EngineStatus.Unknown) {
				request.getSession().getServletContext()
				.removeAttribute(id);
				RouterLog routerLog = new RouterLog(
						request.getSession().getServletContext().getRealPath("WEB-INF/" + tenant.getPage() + ".json"));
				try {
					instGenesysEngine = new InstGenesysEngine(routerLog,tenant);
					request.getSession().getServletContext().setAttribute(id, instGenesysEngine);
					instGenesysEngine.start();
				} catch (Exception e) {
					logger.error(e);
					outcome.setError(true);
					outcome.setErrorMessage("Can not initated Engine :" + e.getMessage());
					return outcome;
				}
			} else {
				outcome.setError(true);
				outcome.setErrorMessage("Can not start engine as current status is:" + instGenesysEngine.getState());
			}
		}
		outcome.setStatus(getEngineStatus(id));
		outcome.setRate(getEngineRate(id));
		return outcome;
	}

	@GET
	@Path("/StopVer13")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome stopVer13(@QueryParam("id") String id,@QueryParam("type") String type) {
		logger.debug("Stop Method is invoked and will try to stop");

		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
		}
		String engineIDRef = id+"_"+type;
		if(type.equals("Twitter"))
			engineIDRef = id+"_"+type+"_StandardRealTime";

		Engine engine = (Engine) request.getSession().getServletContext()
				.getAttribute(engineIDRef);
		if (engine != null) {
			if (engine.isActive()) {
				logger.debug("Setting the engine status to inactive for id:" + id);
				engine.setActive(false);
			}
			if (engine.getStatus() == EngineStatus.Sleep) {
				synchronized (this) {
					logger.debug("Notify engine for id:" + id);
					engine.interrupt();
				}
			} else if (engine.getStatus() == EngineStatus.Error)
				engine.setStatus(EngineStatus.Stop);
				
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find engine instance");
			return outcome;
		}
		//outcome.setFacebookStatus(getEngineStatus(id+"_FB"));
		//outcome.setRate(getEngineRate(id));
		return outcome;
	}

	@GET
	@Path("/StopHistoryVer13")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome stopHistoryVer13(@QueryParam("id") String id,@QueryParam("type") String type,@QueryParam("searchType") String searchType) {
		logger.debug("Stop Method is invoked and will try to stop for type:"+type);

		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
		}
		String engineIDRef = id+"_"+type+"History";
		if(type.equals("Twitter"))
			engineIDRef = id+"_"+type+"History"+"_"+searchType;

		Engine engine = (Engine) request.getSession().getServletContext().getAttribute(engineIDRef);
		if (engine != null) {
			if (engine.isActive()) {
				logger.debug("Setting the engine status to inactive for id:" + id);
				engine.setActive(false);
			}
			if (engine.getStatus() == EngineStatus.Sleep) {
				synchronized (this) {
					logger.debug("Notify engine for id:" + id);
					engine.interrupt();
				}
			} else if (engine.getStatus() == EngineStatus.Error)
				engine.setStatus(EngineStatus.Stop);
				
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find engine instance");
			return outcome;
		}
		//outcome.setFacebookStatus(getEngineStatus(id+"_FB"));
		//outcome.setRate(getEngineRate(id));
		return outcome;
	}

	@GET
	@Path("/Stop")
	@Produces(MediaType.APPLICATION_XML)
	@Deprecated
	public Outcome stop(@QueryParam("id") String id) {
		logger.debug("Stop Method is invoked and will try to stop");

		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		if (instGenesysEngine != null) {
			if (instGenesysEngine.isActive()) {
				logger.debug("Setting the engine status to inactive for id:" + id);
				instGenesysEngine.setActive(false);
			}
			if (instGenesysEngine.getStatus() == EngineStatus.Sleep) {
				synchronized (this) {
					logger.debug("Notify engine for id:" + id);
					instGenesysEngine.interrupt();
				}
			} else if (instGenesysEngine.getStatus() == EngineStatus.Error)
				instGenesysEngine.setStatus(EngineStatus.Stop);
				
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find engine instance");
			return outcome;
		}
		outcome.setStatus(getEngineStatus(id));
		outcome.setRate(getEngineRate(id));
		return outcome;
	}

	@GET
	@Path("/StatusVer13")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome statusVer13(@QueryParam("id") String id) {
		logger.debug("Status Method is invoked");
		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id, please login to your account first");
			return outcome;
		}
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);

		if (tenant != null) {
			outcome.setRate(getEngineRate("unknown id",tenant)); // To force Ready from Tenant 
			outcome.setTenant(tenant);
		} else {
			//outcome.setRate(getEngineRate(id+"_FB")); 
		}
		request.getSession().getServletContext().setAttribute("configurator", configurator);
		outcome.setStatus(getEngineStatus(id)); // Default is instagram
		outcome.setFacebookStatus(getEngineStatusVer13(id+"_FB"));
		outcome.setFacebookHistoryStatus(getEngineStatusVer13(id+"_FBHistory"));
		outcome.setInstagramStatus(getEngineStatusVer13(id+"_Insta"));
		outcome.setInstagramHistoryStatus(getEngineStatusVer13(id+"_InstaHistory"));
		outcome.setTwitterStatus(getEngineStatusVer13(id+"_Twitter_StandardRealTime"));
		outcome.setTwitterWeekHistoryStatus(getEngineStatusVer13(id+"_TwitterHistory_standard"));
		outcome.setTwitterMonthHistoryStatus(getEngineStatusVer13(id+"_TwitterHistory_30days"));
		outcome.setTwitterFullHistoryStatus(getEngineStatusVer13(id+"_TwitterHistory_full"));
		//outcome.setRoutedcount(getEngineRoutedCount(id)); // Default is instagram
		return outcome;
	}
	
	@GET
	@Path("/Status")
	@Produces(MediaType.APPLICATION_XML)
	@Deprecated
	public Outcome status(@QueryParam("id") String id) {
		logger.debug("Status Method is invoked");
		Outcome outcome = new Outcome();
		outcome.setError(false);
		if (id == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Missing user id");
		}
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);

		outcome.setId(id);
		if (tenant != null) {
			outcome.setPage(tenant.getPage());
			outcome.setToken(tenant.getAccesstoken());
			outcome.setStarttime(tenant.getStartTime());
			outcome.setEndtime(tenant.getEndTime());
			outcome.setQueuename(tenant.getQueueName());
			outcome.setEndpoint(tenant.getEndPoint());
			outcome.setRate(getEngineRate(id,tenant));
			outcome.setTenantid(tenant.getTenantId());
			outcome.setMediatype(tenant.getMediaType());
			outcome.setStartdate(tenant.getStartDate());
		} else
			outcome.setRate(getEngineRate(id));
		request.getSession().getServletContext().setAttribute("configurator", configurator);
		outcome.setStatus(getEngineStatus(id));
		
		outcome.setRoutedcount(getEngineRoutedCount(id));
		return outcome;
	}

	@POST
	@Path("/UpdateVer13")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Outcome updateVer13(Tenant tenant) {
		Outcome outcome = new Outcome();
		outcome.setError(false);
		logger.debug("New Update Method is invoked");
		if(tenant == null) {
			outcome.setError(true);
			outcome.setErrorMessage("Update function error. can not accept null tenant");
			return outcome;
		}

		
		//logger.debug("Tenant Update for the tenant info:\n"+tenant.toString());
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		// Update Token with Permanent Token
		if (tenant.getAccesstoken() != null) {
			try {
				InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
				String permToken = instFetcher.fetchUserPermTokenString();
				if (permToken != null)
					tenant.setAccesstoken(permToken);

			} catch (Exception e) {
				logger.error(e);
				outcome.setError(true);
				outcome.setTenant(tenant);
				outcome.setErrorMessage("Update function error. Can not generate permnant token");
				return outcome;
			}
		}
		//configurator.removeTenantByID(tenant.getId());
		if(tenant.ready())
			tenant = configurator.updateTenant(tenant);
		else {
			outcome.setError(true);
			outcome.setTenant(tenant);
			outcome.setErrorMessage("Can not add tenant, please check the parameters");
			return outcome;
		}
		request.getSession().getServletContext().setAttribute("configurator",configurator);
		outcome.setTenant(tenant);
		return outcome;
	}

	@GET
	@Path("/Update")
	@Produces(MediaType.APPLICATION_XML)
	@Deprecated
	public Outcome update(@QueryParam("id") String id, @QueryParam("page") String page,
			@QueryParam("token") String token, @QueryParam("user") String user, 
			@QueryParam("starttime") String starttime, @QueryParam("endtime") String endtime, 
			@QueryParam("queuename") String queuename, @QueryParam("endpoint") String endpoint,
			@QueryParam("rate") String rate,
			@QueryParam("tenantid") String tenantid,
			@QueryParam("mediatype") String mediatype,
			@QueryParam("startdate") String startdate) {
//		starttime = "'"+starttime+"'";
//		endtime = "'"+endtime+"'";

		logger.debug("Update Method is invoked");
		logger.debug("Engine Start Time is:"+starttime+" Engine End Time is:"+endtime);
		Outcome outcome = new Outcome();
		outcome.setError(false);
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		// Update Token with Perm Token
		if (token != null) {
			try {
				InstagramFetcher instFetcher = new InstagramFetcher(token, null);
				String permToken = instFetcher.fetchUserPermTokenString();
				if (permToken != null)
					token = permToken;

			} catch (Exception e) {
				logger.error(e);
				outcome.setError(false);
				outcome.setErrorMessage("Can not get perm token");
			}
		}

		Tenant tenant = configurator.checkTenant(id);
		if (tenant == null) {
			if (page != null)
				configurator.addTenant(id, page, token, user, starttime, endtime,queuename,endpoint,rate,tenantid,mediatype,startdate);
			else
				configurator.addTenant(id, "Please update page ID", token, user, starttime, endtime,queuename,endpoint,rate,tenantid,mediatype,startdate);
		} else {
			configurator.getConf().getTenants().remove(tenant);
			configurator.addTenant(id, page, token, user, starttime, endtime,queuename,endpoint,rate,tenantid,mediatype,startdate);
		}
		request.getSession().getServletContext()
		.setAttribute("configurator",configurator);
		outcome.setId(id);
		outcome.setPage(page);
		outcome.setToken(token);
		outcome.setStatus(getEngineStatus(id));
		outcome.setRate(getEngineRate(id, rate));
		outcome.setRoutedcount(getEngineRoutedCount(id));
		outcome.setEndtime(endtime);
		outcome.setStarttime(starttime);
		outcome.setQueuename(queuename);
		outcome.setEndpoint(endpoint);
		outcome.setTenantid(tenantid);
		outcome.setMediatype(mediatype);
		outcome.setStartdate(startdate);

		return outcome;
	}
	
	@GET
	@Path("/Tenants")
	@Produces(MediaType.APPLICATION_JSON)
	public Outcome getTenantsStatus() {
		Outcome outcome = new Outcome();
		outcome.setError(false);
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));

		List<Tenant> tenants = configurator.getConf().getTenants();
		for(Tenant tenant : tenants) {
			tenant.setEngineStatus(getEngineStatus(tenant.getId()));
		}
		outcome.setTenants(tenants);
		// Now set the engine status of each tenant
		
		return outcome;
	}

	private String getEngineStatus(String id) {
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		String outcome = "Unknown";
		if (instGenesysEngine == null)
			outcome = "Unknown";
		else {
			switch (instGenesysEngine.getStatus()) {
			case Running:
				outcome = "Running";
				break;
			case Error:
				outcome = "Error";
				break;
			case Initiating:
				outcome = "Initiating";
				break;
			case Sleep:
				outcome = "Sleep";
				break;
			case Stop:
				outcome = "Stop";
				break;
			default:
				break;
			}
		}
		return outcome;

	}

	private String getEngineStatusVer13(String id) {
		Engine engine = (Engine) request.getSession().getServletContext()
				.getAttribute(id);
		String outcome = "Unknown";
		if (engine == null)
			outcome = "Unknown";
		else {
			switch (engine.getStatus()) {
			case Running:
				outcome = "Running";
				break;
			case Error:
				outcome = "Error";
				break;
			case Initiating:
				outcome = "Initiating";
				break;
			case Sleep:
				outcome = "Sleep";
				break;
			case Stop:
				outcome = "Stop";
				break;
			default:
				break;
			}
		}
		return outcome;

	}

	@Deprecated
	private String getFBEngineStatus(String id) {
		FacebookEngine facebookEngine = (FacebookEngine) request.getSession().getServletContext()
				.getAttribute(id);
		String outcome = "Unknown";
		if (facebookEngine == null)
			outcome = "Unknown";
		else {
			switch (facebookEngine.getStatus()) {
			case Running:
				outcome = "Running";
				break;
			case Error:
				outcome = "Error";
				break;
			case Initiating:
				outcome = "Initiating";
				break;
			case Sleep:
				outcome = "Sleep";
				break;
			case Stop:
				outcome = "Stop";
				break;
			default:
				break;
			}
		}
		return outcome;

	}

	@Deprecated
	private String getTwitterEngineStatus(String id) {
		TwitterEngine twitterEngine = (TwitterEngine) request.getSession().getServletContext()
				.getAttribute(id);
		String outcome = "Unknown";
		if (twitterEngine == null)
			outcome = "Unknown";
		else {
			switch (twitterEngine.getStatus()) {
			case Running:
				outcome = "Running";
				break;
			case Error:
				outcome = "Error";
				break;
			case Initiating:
				outcome = "Initiating";
				break;
			case Sleep:
				outcome = "Sleep";
				break;
			case Stop:
				outcome = "Stop";
				break;
			default:
				break;
			}
		}
		return outcome;

	}

	@Deprecated
	private String getInstaEngineStatus(String id) {
		InstagramEngine instagramEngine = (InstagramEngine) request.getSession().getServletContext()
				.getAttribute(id);
		String outcome = "Unknown";
		if (instagramEngine == null)
			outcome = "Unknown";
		else {
			switch (instagramEngine.getStatus()) {
			case Running:
				outcome = "Running";
				break;
			case Error:
				outcome = "Error";
				break;
			case Initiating:
				outcome = "Initiating";
				break;
			case Sleep:
				outcome = "Sleep";
				break;
			case Stop:
				outcome = "Stop";
				break;
			default:
				break;
			}
		}
		return outcome;

	}

	private int getEngineRate(String id) {
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		int outcome = -1;
		if (instGenesysEngine != null)
			outcome = instGenesysEngine.getRate();
		return outcome;

	}

	private int getEngineRate(String id, Tenant tenant) {
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		int outcome = -1;
		if (instGenesysEngine == null && tenant != null) {
			try {
				outcome = Integer.parseInt(tenant.getRate());
			}catch(Exception e) {
				logger.warn("Error while reading rate and value is:"+tenant.getRate());
			}
		} else
			outcome = instGenesysEngine.getRate();
		return outcome;

	}

	private int getEngineRate(String id, String rate) {
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		int outcome = -1;
		if (instGenesysEngine == null) {
			try {
				outcome = Integer.parseInt(rate);
			}catch(Exception e) {
				logger.warn("Error while reading rate");
			}
		} else
			outcome = instGenesysEngine.getRate();
		return outcome;

	}

	private int getEngineRoutedCount(String id) {
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);
		int outcome = -1;
		if (instGenesysEngine == null)
			outcome = -1;
		else {
			if(instGenesysEngine.getRouterLog() != null)
				if(instGenesysEngine.getRouterLog().getRouterData() != null)
					if(instGenesysEngine.getRouterLog().getRouterData().getRouted() != null)
						outcome = instGenesysEngine.getRouterLog().getRouterData().getRouted().size();
		}
		return outcome;

	}

}
