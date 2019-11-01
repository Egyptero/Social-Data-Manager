package cxengage.facebook.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cxengage.facebook.conf.Configurator;
import cxengage.facebook.conf.pojos.Tenant;
import cxengage.facebook.engine.InstGenesysEngine;
import cxengage.facebook.pojos.InstComment;
import cxengage.facebook.pojos.InstMedia;
import cxengage.facebook.pojos.InstReply;
import cxengage.facebook.pojos.InstUser;
import cxengage.facebook.rest.outcome.Outcome;
import cxengage.instagram.services.InstagramFetcher;

@Path("/InstClientRest")
public class InstClientRest {

	private transient Logger logger = Logger.getLogger(InstRest.class);
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/getInteraction")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInteraction(@QueryParam("id") String id, @QueryParam("commentId") String commentId) {
		String finalOutcome = null;
		logger.info("New Comment / Reply request with the id");
		Outcome outcome = new Outcome();
		outcome.setError(false);
		outcome.setId(id);
		outcome.setCommentId(commentId);
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant != null) {
			outcome.setPage(tenant.getPage());
			outcome.setToken(tenant.getAccesstoken());
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find organization account, Please contact system admin");
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);

		if (instGenesysEngine == null) { // In case engine is not working find data your way
			// TODO we can save the InstMedia in the page json file and retreive the
			// information back , however for now , we will put a condition that engine
			// should be running
			// RouterLog routerLog = new
			// RouterLog(request.getSession().getServletContext().getRealPath("WEB-INF/" +
			// tenant.getPage() + ".json"));
			outcome.setError(true);
			outcome.setErrorMessage("Engine is not running, Please turn it on.");
			ObjectMapper mapper = new ObjectMapper();
			try {
				finalOutcome = mapper.writeValueAsString(outcome);
			} catch (JsonProcessingException e) {
				logger.error(e);
			}
			return finalOutcome;

		} else {
			// Search for the full media item
			if (instGenesysEngine.getInstBusinessAccount() != null) {
				outcome.setBusinessAccountID(instGenesysEngine.getInstBusinessAccount().getId());
				for (InstMedia instMedia : instGenesysEngine.getInstBusinessAccount().getData()) {
					if (instMedia.getComments() != null) {
						for (InstComment instComment : instMedia.getComments().getData()) {
							if (instComment.getId().equals(commentId)) {
								outcome.setInstMedia(instMedia);
								outcome.setCommentText(instComment.getText());
								outcome.setInstComment(instComment);
								try {
									InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
									outcome.setInstUser(instFetcher.fetchUserDetails(instComment.getUser().getId()));
								} catch (Exception e) {
									InstUser instUser = new InstUser();
									instUser.setUsername(instComment.getUsername());
									outcome.setInstUser(instUser);
									logger.warn("Can not load user media file, will use the user name as :"+instComment.getUsername());
								}
							}
							if (instComment.getReplies() != null) {
								for (InstReply instReply : instComment.getReplies().getData()) {
									if (instReply.getId().equals(commentId)) {
										outcome.setInstMedia(instMedia);
										outcome.setCommentText(instReply.getText());
										outcome.setInstComment(instComment);
										outcome.setInstReply(instReply);
										try {
											InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
											outcome.setInstUser(
													instFetcher.fetchUserDetails(instReply.getUser().getId()));
										} catch (Exception e) {
											InstUser instUser = new InstUser();
											instUser.setUsername(instReply.getUsername());
											outcome.setInstUser(instUser);
											logger.warn("Can not load user media file, will use the user name as :"+instReply.getUsername());
										}
									}
								}
							}
						}
					}
				}
			}

		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalOutcome = mapper.writeValueAsString(outcome);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return finalOutcome;
	}

	@GET
	@Path("/addReply")
	@Produces(MediaType.APPLICATION_JSON)
	public String addReply(@QueryParam("id") String id, @QueryParam("commentId") String commentId, @QueryParam("text") String text) {
		logger.info("Add Reply to the id:" + id);
		String finalOutcome = null;
		Outcome outcome = new Outcome();
		outcome.setError(false);
		// TODO Replies should be done on comment level
		// TODO Find comments ID
		// TODO add reply to comment
		outcome.setId(id);
		outcome.setCommentId(commentId);
		
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant != null) {
			outcome.setPage(tenant.getPage());
			outcome.setToken(tenant.getAccesstoken());
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find organization account, Please contact system admin");
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);

		if (instGenesysEngine == null) { // In case engine is not working find data your way
			// TODO we can save the InstMedia in the page json file and retreive the
			// information back , however for now , we will put a condition that engine
			// should be running
			// RouterLog routerLog = new
			// RouterLog(request.getSession().getServletContext().getRealPath("WEB-INF/" +
			// tenant.getPage() + ".json"));
			outcome.setError(true);
			outcome.setErrorMessage("Engine is not running, Please turn it on.");
			ObjectMapper mapper = new ObjectMapper();
			try {
				finalOutcome = mapper.writeValueAsString(outcome);
			} catch (JsonProcessingException e) {
				logger.error(e);
			}
			return finalOutcome;

		} else {
			if (instGenesysEngine.getInstBusinessAccount() != null) {
				for (InstMedia instMedia : instGenesysEngine.getInstBusinessAccount().getData()) {
					if (instMedia.getComments() != null) {
						for (InstComment instComment : instMedia.getComments().getData()) {
							if (instComment.getId().equals(commentId)) {
								try {
									InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
									String outcomeID = instFetcher.postReply(instComment.getId(), text);
									if(outcomeID == null) {
										outcome.setError(true);
										outcome.setErrorMessage("Can not post response");
									} else
										outcome.setGeneratedid(outcomeID);
								} catch (Exception e) {
									logger.warn("Can not post user comment");
									outcome.setError(true);
									outcome.setErrorMessage("Can not post response");
								}
							}
							if (instComment.getReplies() != null) {
								for (InstReply instReply : instComment.getReplies().getData()) {
									if (instReply.getId().equals(commentId)) {
										try {
											InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
											String outcomeID = instFetcher.postReply(instComment.getId(), text);
											if(outcomeID == null) {
												outcome.setError(true);
												outcome.setErrorMessage("Can not post response");
											} else
												outcome.setGeneratedid(outcomeID);
										} catch (Exception e) {
											logger.warn("Can not post user comment");
											outcome.setError(true);
											outcome.setErrorMessage("Can not post response");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalOutcome = mapper.writeValueAsString(outcome);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return finalOutcome;

	}

	@GET
	@Path("/deleteReply")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteReply(@QueryParam("id") String id, @QueryParam("commentId") String commentId) {
		logger.info("Delete Reply to the id:" + id);
		String finalOutcome = null;
		Outcome outcome = new Outcome();
		outcome.setError(false);
		// TODO Replies should be done on comment level
		// TODO Find comments ID
		// TODO add reply to comment
		outcome.setId(id);
		outcome.setCommentId(commentId);
		
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant != null) {
			outcome.setPage(tenant.getPage());
			outcome.setToken(tenant.getAccesstoken());
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find organization account, Please contact system admin");
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);

		if (instGenesysEngine == null) { // In case engine is not working find data your way
			// TODO we can save the InstMedia in the page json file and retreive the
			// information back , however for now , we will put a condition that engine
			// should be running
			// RouterLog routerLog = new
			// RouterLog(request.getSession().getServletContext().getRealPath("WEB-INF/" +
			// tenant.getPage() + ".json"));
			outcome.setError(true);
			outcome.setErrorMessage("Engine is not running, Please turn it on.");
			ObjectMapper mapper = new ObjectMapper();
			try {
				finalOutcome = mapper.writeValueAsString(outcome);
			} catch (JsonProcessingException e) {
				logger.error(e);
			}
			return finalOutcome;

		} else {
			try {
				InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
				outcome.setError(!instFetcher.deleteComment(commentId));
			} catch (Exception e) {
				logger.warn("Can not post user comment");
				outcome.setError(true);
				outcome.setErrorMessage("Can not post response");
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalOutcome = mapper.writeValueAsString(outcome);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return finalOutcome;

	}

	@GET
	@Path("/hideReply")
	@Produces(MediaType.APPLICATION_JSON)
	public String hideReply(@QueryParam("id") String id, @QueryParam("commentId") String commentId) {
		logger.info("Delete Reply to the id:" + id);
		String finalOutcome = null;
		Outcome outcome = new Outcome();
		outcome.setError(false);
		// TODO Replies should be done on comment level
		// TODO Find comments ID
		// TODO add reply to comment
		outcome.setId(id);
		outcome.setCommentId(commentId);
		
		Configurator configurator = (Configurator) request.getSession().getServletContext()
				.getAttribute("configurator");
		if (configurator == null)
			configurator = Configurator
					.getInstance(request.getSession().getServletContext().getRealPath("WEB-INF/tenants.json"));
		Tenant tenant = configurator.checkTenant(id);
		if (tenant != null) {
			outcome.setPage(tenant.getPage());
			outcome.setToken(tenant.getAccesstoken());
		} else {
			outcome.setError(true);
			outcome.setErrorMessage("Can not find organization account, Please contact system admin");
		}
		InstGenesysEngine instGenesysEngine = (InstGenesysEngine) request.getSession().getServletContext()
				.getAttribute(id);

		if (instGenesysEngine == null) { // In case engine is not working find data your way
			// TODO we can save the InstMedia in the page json file and retreive the
			// information back , however for now , we will put a condition that engine
			// should be running
			// RouterLog routerLog = new
			// RouterLog(request.getSession().getServletContext().getRealPath("WEB-INF/" +
			// tenant.getPage() + ".json"));
			outcome.setError(true);
			outcome.setErrorMessage("Engine is not running, Please turn it on.");
			ObjectMapper mapper = new ObjectMapper();
			try {
				finalOutcome = mapper.writeValueAsString(outcome);
			} catch (JsonProcessingException e) {
				logger.error(e);
			}
			return finalOutcome;

		} else {
			try {
				InstagramFetcher instFetcher = new InstagramFetcher(tenant.getAccesstoken(), null);
				outcome.setError(!instFetcher.hideComment(commentId));
			} catch (Exception e) {
				logger.warn("Can not post user comment");
				outcome.setError(true);
				outcome.setErrorMessage("Can not post response");
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			finalOutcome = mapper.writeValueAsString(outcome);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return finalOutcome;

	}

}
