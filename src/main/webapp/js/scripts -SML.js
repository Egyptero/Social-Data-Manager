// JavaScript Document
//InstGenesys Integration function
var SERVER_URL = "/..";
var FBPAGES = [];
var InstaPAGES = [];
var TwitterKEYS = [];
function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
			.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true : sParameterName[1];
		}
	}
}

function removeTwitterKey(id,searchKey) {
	$(id).remove();
	$(TwitterKEYS).each(function(index) {
		if ((TwitterKEYS[index]) == searchKey) {
			TwitterKEYS.splice(index, 1); // This will remove the object that
			// first
			// name equals to Test1
			if (TwitterKEYS.length == 0)
				$("#TwitterEngineBar").addClass("hidden");
			return false; // This will stop the execution of jQuery each loop.
		}
	});
	updateTenantData();
}

function removeFBPage(pageId) {
	$(pageId).remove();
	$(FBPAGES).each(function(index) {
		if (('#FB_' + FBPAGES[index]) == pageId) {
			FBPAGES.splice(index, 1); // This will remove the object that
			// first
			// name equals to Test1
			if (FBPAGES.length == 0)
				$("#FBEngineBar").addClass("hidden");
			return false; // This will stop the execution of jQuery each loop.
		}
	});
	updateTenantData();
}

function removeInstaPage(pageId) {
	$(pageId).remove();
	$(InstaPAGES).each(function(index) {
		if (('#Insta_' + InstaPAGES[index]) == pageId) {
			InstaPAGES.splice(index, 1); // This will remove the object that
			// first
			// name equals to Test1
			if (InstaPAGES.length == 0)
				$("#InstaEngineBar").addClass("hidden");
			return false; // This will stop the execution of jQuery each loop.
		}
	});
	updateTenantData();
}

function addTwitterKey(searchKey) {
	if (searchKey == null)
		searchKey = $("#twitterSearchKey").val();
	if (searchKey === null || searchKey === '' || searchKey === 'undefined!')
		return;
	// Check if page id existing during the status check
	keyFound = false;
	$(TwitterKEYS).each(function(index) {
		if ((TwitterKEYS[index]) == searchKey) {
			console.log("Key " + searchKey + " was already added before.");
			keyFound = true;
			return false; // This will stop the execution of jQuery each loop.
		}
	});

	if (keyFound) {
		return;
	}

	var generatedId = UUID();
	$("#twitterSearchKeys")
			.append(
					" <span id='Twitter_"
							+ generatedId
							+ "' class='label label-default'>"

							+ " <span onClick=\"removeTwitterKey('#Twitter_"+ generatedId +"','"
							+ searchKey
							+ "');\" class='glyphicon glyphicon-remove' aria-hidden='true'></span> "
							+ searchKey + "</span>");
	$("#twitterSearchKey").val('');
	var jsonData = searchKey;
	TwitterKEYS.push(jsonData);
	$("#TwitterEngineBar").removeClass("hidden");
	// updateTenantData();
}

function addFBPageId(fbPageID) {
	if (fbPageID == null)
		fbPageID = $("#fbPageID").val();
	if (fbPageID === null || fbPageID === '' || fbPageID === 'undefined!')
		return;
	// Check if page id existing during the status check
	pageFound = false;
	$(FBPAGES).each(function(index) {
		if ((FBPAGES[index]) == fbPageID) {
			console.log("Page " + fbPageID + " was already added before.");
			pageFound = true;
			return false; // This will stop the execution of jQuery each loop.
		}
	});

	if (pageFound) {
		return;
	}

	FB
			.api(
					'/' + fbPageID,
					function(response) {
						console.log('Validate Page ID (' + fbPageID + ') : ');
						if (response.hasOwnProperty('error')) {
							alert(response.error.message);
						} else {
							pageFound = false;
							$(FBPAGES)
									.each(
											function(index) {
												if ((FBPAGES[index]) == response.id) {
													alert("Page "
															+ response.name
															+ " was already added before.");
													pageFound = true;
													return false; // This will
													// stop the
													// execution
													// of jQuery
													// each
													// loop.
												}
											});

							if (pageFound) {
								$("#fbPageID").val('');
								return;
							}

							$("#fbPages")
									.append(
											" <span id='FB_"
													+ response.id
													+ "' class='label label-default'>"

													+ " <span onClick=\"removeFBPage('#FB_"
													+ response.id
													+ "');\" class='glyphicon glyphicon-remove' aria-hidden='true'></span> "
													+ response.name + "</span>");
							$("#fbPageID").val('');
							var jsonData = response.id;
							FBPAGES.push(jsonData);
							$("#FBEngineBar").removeClass("hidden");
							// updateTenantData();
						}
					});
}

function addInstaPageId(instaPageID) {
	if (instaPageID == null)
		instaPageID = $("#instaPageID").val();
	if (instaPageID === null || instaPageID === ''
			|| instaPageID === 'undefined!')
		return;
	// Check if page id existing during the status check
	pageFound = false;
	$(InstaPAGES).each(function(index) {
		if ((InstaPAGES[index]) == instaPageID) {
			console.log("Page " + instaPageID + " was already added before.");
			pageFound = true;
			return false; // This will stop the execution of jQuery each loop.
		}
	});

	if (pageFound) {
		return;
	}

	FB
			.api(
					'/' + instaPageID,
					function(response) {
						console
								.log('Validate Page ID (' + instaPageID
										+ ') : ');
						if (response.hasOwnProperty('error')) {
							alert(response.error.message);
						} else {
							pageFound = false;
							$(InstaPAGES)
									.each(
											function(index) {
												if ((InstaPAGES[index]) == response.id) {
													alert("Page "
															+ response.name
															+ " was already added before.");
													pageFound = true;
													return false; // This will
													// stop the
													// execution
													// of jQuery
													// each
													// loop.
												}
											});

							if (pageFound) {
								$("#instaPageID").val('');
								return;
							}

							$("#instaPages")
									.append(
											" <span id='Insta_"
													+ response.id
													+ "' class='label label-default'>"

													+ " <span onClick=\"removeInstaPage('#Insta_"
													+ response.id
													+ "');\" class='glyphicon glyphicon-remove' aria-hidden='true'></span> "
													+ response.name + "</span>");
							$("#instaPageID").val('');
							var jsonData = response.id;
							InstaPAGES.push(jsonData);
							$("#InstaEngineBar").removeClass("hidden");
							// updateTenantData();
						}
					});
}

function checkTenantStatus() {
	// $("#pageid").empty();
	// $("#enginestatus").empty();
	// $("#enginerate").empty();
	// $("#starttime").empty();
	// $("#endtime").empty();
	// $("#startdate").empty();
	// $("#queuename").empty();
	// $("#endpoint").empty();
	// $("#mediatype").empty();
	// $("#tenantid").empty();

	// $("#enginestatus").attr("disabled", "disabled");
	// $("#starttime").attr("disabled", "disabled");
	// $("#endtime").attr("disabled", "disabled");
	// $("#queuename").attr("disabled", "disabled");
	// $("#endpoint").attr("disabled", "disabled");
	// $("#mediatype").attr("disabled", "disabled");
	// $("#tenantid").attr("disabled", "disabled");
	// $("#startdate").attr("disabled", "disabled");

	var url = SERVER_URL + "/InstGenesys/rest/InstRest/StatusVer13?id="
			+ sessionStorage.userID;
	$
			.ajax({
				type : 'GET',
				dataType : "json",
				url : url,
				success : function(data) {
					if (!data.error) {
						var userID = data.tenant.id;
						var pageID = data.tenant.page;
						// var token = data.tenant.accesstoken;
						var status = data.status;
						var facebookStatus = data.facebookStatus;
						var facebookHistoryStatus = data.facebookHistoryStatus;
						var instagramStatus = data.instagramStatus;
						var instagramHistoryStatus = data.instagramHistoryStatus;
						var twitterStatus = data.twitterStatus
						var twitterHistoryWeekStatus = data.twitterWeekHistoryStatus;
						var twitterHistoryMonthStatus = data.twitterMonthHistoryStatus;
						var twitterHistoryFullStatus = data.twitterFullHistoryStatus;

						// var queuename = data.queuename;
						// var endpoint = data.endpoint;
						// var tenantid = data.tenantid;
						// var mediatype = data.mediatype;
						$("#TwitterEnginestatus").empty();
						$("#TwitterEnginestatus").append(
								'<Strong>' + twitterStatus + '</Strong>');

						$("#InstaEnginestatus").empty();
						$("#InstaEnginestatus").append(
								'<Strong>' + instagramStatus + '</Strong>');

						$("#FBEnginestatus").empty();
						$("#FBEnginestatus").append(
								'<Strong>' + facebookStatus + '</Strong>');
						if (data.tenant != null) { // We have a tenant
							if (data.tenant.twitterData != null
									&& data.tenant.twitterData.id != null) {
								sessionStorage.twitterUserID = data.tenant.twitterData.id;
								sessionStorage.twitterUserName = data.tenant.twitterData.userName;
								sessionStorage.twitterProfilePic = data.tenant.twitterData.profilePicUrl;
								sessionStorage.twitterToken = data.tenant.twitterData.token;
								sessionStorage.twitterTokenSecret = data.tenant.twitterData.tokenSecret;
								welcomeTwitterTenant();
								if (data.tenant.twitterData.tweets)
									$("#TwitterTweets").attr("checked",
											"checked");
								if (data.tenant.twitterData.console)
									$("#TwitterConsole").attr("checked",
											"checked");
								if (data.tenant.twitterData.restAPI)
									$("#TwitterRestAPI").attr("checked",
											"checked");
								if (data.tenant.twitterData.contactCenter)
									$("#TwitterContactCenter").attr("checked",
											"checked");
								if (data.tenant.twitterData.keys != null) {
									$(data.tenant.twitterData.keys)
											.each(
													function(index) {
														addTwitterKey(data.tenant.twitterData.keys[index]);
													});
								}
								if (data.tenant.twitterData.contactCenterData != null) { // We
									// have
									// contact
									// center
									// Data
									if (data.tenant.twitterData.contactCenterData.genesysCPData != null) { // We
										// have
										// Genesys
										// Pure
										// Enagage
										// Data
										$("#TwitterQueuename")
												.val(
														data.tenant.twitterData.contactCenterData.genesysCPData.queueName);
										$("#TwitterEndpoint")
												.val(
														data.tenant.twitterData.contactCenterData.genesysCPData.endPoint);
										$("#TwitterMediatype")
												.val(
														data.tenant.twitterData.contactCenterData.genesysCPData.mediaType);
										$("#TwitterTenantid")
												.val(
														data.tenant.twitterData.contactCenterData.genesysCPData.tenantId);
									}

									if (data.tenant.twitterData.contactCenterData.ciscoSMData != null) { // We
										// have
										// Cisco
										// Social
										// Miner
										// Data
										$("#TwitterSocialMinerHost")
												.val(
														data.tenant.twitterData.contactCenterData.ciscoSMData.host);
										$("#TwitterSocialMinerPort")
												.val(
														data.tenant.twitterData.contactCenterData.ciscoSMData.port);
									}
								}
								if (data.tenant.twitterData.restAPIData != null) { // We
									// have
									// a
									// rest
									// API
									// Data
									$("#TwitterRestURL")
											.val(
													data.tenant.twitterData.restAPIData.uri);
									if (data.tenant.twitterData.restAPIData.methodGet)
										$("#TwitterRadioGET").attr("checked",
												"checked");
									if (data.tenant.twitterData.restAPIData.methodPost)
										$("#TwitterRadioPOST").attr("checked",
												"checked");
									if (data.tenant.twitterData.restAPIData.inBodyData)
										$("#TwitterRadioBody").attr("checked",
												"checked");
									if (data.tenant.twitterData.restAPIData.inParameterData)
										$("#TwitterRadioParameter").attr(
												"checked", "checked");
									$("#TwitterParamName")
											.val(
													data.tenant.twitterData.restAPIData.parameterName)
								}
							}

							if (data.tenant.instagramData != null) { // We
								// have
								// a
								// facebook
								// field
								// data
								if (data.tenant.instagramData.posts)
									$("#InstaPosts").attr("checked", "checked");
								if (data.tenant.instagramData.comments)
									$("#InstaComments").attr("checked",
											"checked");
								if (data.tenant.instagramData.replies)
									$("#InstaReplies").attr("checked",
											"checked");
								if (data.tenant.instagramData.console)
									$("#InstaConsole").attr("checked",
											"checked");
								if (data.tenant.instagramData.restAPI)
									$("#InstaRestAPI").attr("checked",
											"checked");
								if (data.tenant.instagramData.contactCenter)
									$("#InstaContactCenter").attr("checked",
											"checked");
								if (data.tenant.instagramData.pages != null) {
									$(data.tenant.instagramData.pages)
											.each(
													function(index) {
														addInstaPageId(data.tenant.instagramData.pages[index]);
													});
								}
								if (data.tenant.instagramData.contactCenterData != null) { // We
									// have
									// contact
									// center
									// Data
									if (data.tenant.instagramData.contactCenterData.genesysCPData != null) { // We
										// have
										// Genesys
										// Pure
										// Enagage
										// Data
										$("#InstaQueuename")
												.val(
														data.tenant.instagramData.contactCenterData.genesysCPData.queueName);
										$("#InstaEndpoint")
												.val(
														data.tenant.instagramData.contactCenterData.genesysCPData.endPoint);
										$("#InstaMediatype")
												.val(
														data.tenant.instagramData.contactCenterData.genesysCPData.mediaType);
										$("#InstaTenantid")
												.val(
														data.tenant.instagramData.contactCenterData.genesysCPData.tenantId);
									}

									if (data.tenant.instagramData.contactCenterData.ciscoSMData != null) { // We
										// have
										// Cisco
										// Social
										// Miner
										// Data
										$("#InstaSocialMinerHost")
												.val(
														data.tenant.instagramData.contactCenterData.ciscoSMData.host);
										$("#InstaSocialMinerPort")
												.val(
														data.tenant.instagramData.contactCenterData.ciscoSMData.port);
									}
								}
								if (data.tenant.instagramData.restAPIData != null) { // We
									// have
									// a
									// rest
									// API
									// Data
									$("#InstaRestURL")
											.val(
													data.tenant.instagramData.restAPIData.uri);
									if (data.tenant.instagramData.restAPIData.methodGet)
										$("#InstaRadioGET").attr("checked",
												"checked");
									if (data.tenant.instagramData.restAPIData.methodPost)
										$("#InstaRadioPOST").attr("checked",
												"checked");
									if (data.tenant.instagramData.restAPIData.inBodyData)
										$("#InstaRadioBody").attr("checked",
												"checked");
									if (data.tenant.instagramData.restAPIData.inParameterData)
										$("#InstaRadioParameter").attr(
												"checked", "checked");
									$("#InstaParamName")
											.val(
													data.tenant.instagramData.restAPIData.parameterName)
								}
							}

							if (data.tenant.facebookData != null) { // We have a
								// facebook
								// field
								// data
								if (data.tenant.facebookData.posts)
									$("#FBPosts").attr("checked", "checked");
								if (data.tenant.facebookData.comments)
									$("#FBComments").attr("checked", "checked");
								if (data.tenant.facebookData.replies)
									$("#FBReplies").attr("checked", "checked");
								if (data.tenant.facebookData.console)
									$("#FBConsole").attr("checked", "checked");
								if (data.tenant.facebookData.restAPI)
									$("#FBRestAPI").attr("checked", "checked");
								if (data.tenant.facebookData.contactCenter)
									$("#FBContactCenter").attr("checked",
											"checked");
								if (data.tenant.facebookData.pages != null) {
									$(data.tenant.facebookData.pages)
											.each(
													function(index) {
														addFBPageId(data.tenant.facebookData.pages[index]);
													});
								}
								if (data.tenant.facebookData.contactCenterData != null) { // We
									// have
									// contact
									// center
									// Data
									if (data.tenant.facebookData.contactCenterData.genesysCPData != null) { // We
										// have
										// Genesys
										// Pure
										// Enagage
										// Data
										$("#FBQueuename")
												.val(
														data.tenant.facebookData.contactCenterData.genesysCPData.queueName);
										$("#FBEndpoint")
												.val(
														data.tenant.facebookData.contactCenterData.genesysCPData.endPoint);
										$("#FBMediatype")
												.val(
														data.tenant.facebookData.contactCenterData.genesysCPData.mediaType);
										$("#FBTenantid")
												.val(
														data.tenant.facebookData.contactCenterData.genesysCPData.tenantId);
									}

									if (data.tenant.facebookData.contactCenterData.ciscoSMData != null) { // We
										// have
										// Cisco
										// Social
										// Miner
										// Data
										$("#FBSocialMinerHost")
												.val(
														data.tenant.facebookData.contactCenterData.ciscoSMData.host);
										$("#FBSocialMinerPort")
												.val(
														data.tenant.facebookData.contactCenterData.ciscoSMData.port);
									}
								}
								if (data.tenant.facebookData.restAPIData != null) { // We
									// have
									// a
									// rest
									// API
									// Data
									$("#FBRestURL")
											.val(
													data.tenant.facebookData.restAPIData.uri);
									if (data.tenant.facebookData.restAPIData.methodGet)
										$("#FBRadioGET").attr("checked",
												"checked");
									if (data.tenant.facebookData.restAPIData.methodPost)
										$("#FBRadioPOST").attr("checked",
												"checked");
									if (data.tenant.facebookData.restAPIData.inBodyData)
										$("#FBRadioBody").attr("checked",
												"checked");
									if (data.tenant.facebookData.restAPIData.inParameterData)
										$("#FBRadioParameter").attr("checked",
												"checked");
									$("#FBParamName")
											.val(
													data.tenant.facebookData.restAPIData.parameterName)
								}
							}
							if (data.tenant.startDate != null)
								$("#startdate").val(data.tenant.startDate);
							if (data.tenant.startTime != null)
								$("#starttime").val(data.tenant.startTime);
							if (data.tenant.endTime != null)
								$("#endtime").val(data.tenant.endTime);

							if (data.tenant.rate != null) {
								if (parseInt(data.tenant.rate) > 0)
									$("#enginerate").val(data.tenant.rate);
							}
						}
						if (facebookStatus === "Stop"
								|| facebookStatus === "Unknown"
								|| facebookStatus === "Error") {
							$("#FBEngineButton").val("Start");
							$("#FBEngineButton").removeClass("btn-success");
							$("#FBEngineButton").addClass("btn-danger");
						} else if (facebookStatus === "Running"
								|| facebookStatus === "Initiating"
								|| facebookStatus === "Sleep") {
							$("#FBEngineButton").val("Stop");
							$("#FBEngineButton").removeClass("btn-danger");
							$("#FBEngineButton").addClass("btn-success");
						}

						if (facebookHistoryStatus === "Stop"
								|| facebookHistoryStatus === "Unknown"
								|| facebookHistoryStatus === "Error") {
							$("#FBEngineHistoryButton").attr('alt', 'Start');
							$("#FBEngineHistoryButton").val(
									"Start Retreive History");
							$("#FBEngineHistoryButton").removeClass(
									"btn-success");
							$("#FBEngineHistoryButton").addClass("btn-danger");
						} else if (facebookHistoryStatus === "Running"
								|| facebookHistoryStatus === "Initiating"
								|| facebookHistoryStatus === "Sleep") {
							$("#FBEngineHistoryButton").attr('alt', 'Stop');
							$("#FBEngineHistoryButton").val(
									"Stop History (Running)");
							$("#FBEngineHistoryButton").removeClass(
									"btn-danger");
							$("#FBEngineHistoryButton").addClass("btn-success");
						}

						if (instagramStatus === "Stop"
								|| instagramStatus === "Unknown"
								|| instagramStatus === "Error") {
							$("#InstaEngineButton").val("Start");
							$("#InstaEngineButton").removeClass("btn-success");
							$("#InstaEngineButton").addClass("btn-danger");
						} else if (instagramStatus === "Running"
								|| instagramStatus === "Initiating"
								|| instagramStatus === "Sleep") {
							$("#InstaEngineButton").val("Stop");
							$("#InstaEngineButton").removeClass("btn-danger");
							$("#InstaEngineButton").addClass("btn-success");
						}

						if (instagramHistoryStatus === "Stop"
								|| instagramHistoryStatus === "Unknown"
								|| instagramHistoryStatus === "Error") {
							$("#InstaEngineHistoryButton").attr('alt', 'Start');
							$("#InstaEngineHistoryButton").val(
									"Start Retreive History");
							$("#InstaEngineHistoryButton").removeClass(
									"btn-success");
							$("#InstaEngineHistoryButton").addClass(
									"btn-danger");
						} else if (instagramHistoryStatus === "Running"
								|| instagramHistoryStatus === "Initiating"
								|| instagramHistoryStatus === "Sleep") {
							$("#InstaEngineHistoryButton").attr('alt', 'Stop');
							$("#InstaEngineHistoryButton").val(
									"Stop History (Running)");
							$("#InstaEngineHistoryButton").removeClass(
									"btn-danger");
							$("#InstaEngineHistoryButton").addClass(
									"btn-success");
						}

						if (twitterStatus === "Stop"
								|| twitterStatus === "Unknown"
								|| twitterStatus === "Error") {
							$("#TwitterEngineButton").val("Start");
							$("#TwitterEngineButton")
									.removeClass("btn-success");
							$("#TwitterEngineButton").addClass("btn-danger");
						} else if (twitterStatus === "Running"
								|| twitterStatus === "Initiating"
								|| twitterStatus === "Sleep") {
							$("#TwitterEngineButton").val("Stop");
							$("#TwitterEngineButton").removeClass("btn-danger");
							$("#TwitterEngineButton").addClass("btn-success");
						}

						if (twitterHistoryFullStatus === "Stop"
								|| twitterHistoryFullStatus === "Unknown"
								|| twitterHistoryFullStatus === "Error") {
							$("#TwitterEngineHistoryButtonfull").attr('alt',
									'Start');
							$("#TwitterEngineHistoryButtonfull").val(
									"Premium Search (Full)");
							$("#TwitterEngineHistoryButtonfull").removeClass(
									"btn-success");
							$("#TwitterEngineHistoryButtonfull").addClass(
									"btn-danger");
						} else if (twitterHistoryFullStatus === "Running"
								|| twitterHistoryFullStatus === "Initiating"
								|| twitterHistoryFullStatus === "Sleep") {
							$("#TwitterEngineHistoryButtonfull").attr('alt',
									'Stop');
							$("#TwitterEngineHistoryButtonfull").val(
									"Stop Premium Search (Full)");
							$("#TwitterEngineHistoryButtonfull").removeClass(
									"btn-danger");
							$("#TwitterEngineHistoryButtonfull").addClass(
									"btn-success");
						}

						if (twitterHistoryMonthStatus === "Stop"
								|| twitterHistoryMonthStatus === "Unknown"
								|| twitterHistoryMonthStatus === "Error") {
							$("#TwitterEngineHistoryButton30days").attr('alt',
									'Start');
							$("#TwitterEngineHistoryButton30days").val(
									"Premium Search (30days)");
							$("#TwitterEngineHistoryButton30days").removeClass(
									"btn-success");
							$("#TwitterEngineHistoryButton30days").addClass(
									"btn-danger");
						} else if (twitterHistoryMonthStatus === "Running"
								|| twitterHistoryMonthStatus === "Initiating"
								|| twitterHistoryMonthStatus === "Sleep") {
							$("#TwitterEngineHistoryButton30days").attr('alt',
									'Stop');
							$("#TwitterEngineHistoryButton30days").val(
									"Stop Premium Search (30days)");
							$("#TwitterEngineHistoryButton30days").removeClass(
									"btn-danger");
							$("#TwitterEngineHistoryButton30days").addClass(
									"btn-success");
						}

						if (twitterHistoryWeekStatus === "Stop"
								|| twitterHistoryWeekStatus === "Unknown"
								|| twitterHistoryWeekStatus === "Error") {
							$("#TwitterEngineHistoryButton7days").attr('alt',
									'Start');
							$("#TwitterEngineHistoryButton7days").val(
									"Standard Search (7days)");
							$("#TwitterEngineHistoryButton7days").removeClass(
									"btn-success");
							$("#TwitterEngineHistoryButton7days").addClass(
									"btn-danger");
						} else if (twitterHistoryWeekStatus === "Running"
								|| twitterHistoryWeekStatus === "Initiating"
								|| twitterHistoryWeekStatus === "Sleep") {
							$("#TwitterEngineHistoryButton7days").attr('alt',
									'Stop');
							$("#TwitterEngineHistoryButton7days").val(
									"Stop Standard Search (7days)");
							$("#TwitterEngineHistoryButton7days").removeClass(
									"btn-danger");
							$("#TwitterEngineHistoryButton7days").addClass(
									"btn-success");
						}

						if (data.routedcount != null) {
							// if (parseInt(data.routedcount) > 0)
							// $("#routedcount").val(data.routedcount);
						}

						// if (queuename != null)
						// $("#queuename").val(queuename.text());
						// if (endpoint != null)
						// $("#endpoint").val(endpoint.text());
						// if (tenantid != null)
						// $("#tenantid").val(tenantid.text());
						// if (mediatype != null)
						// $("#mediatype").val(mediatype.text());

						// if (pageID == null) {
						// } else {
						// $("#pageid").val(pageID.text());
						//
						// if (status != null) {
						// $("#enginestatus").val(status.text());
						// if (status.text() === "Stop"
						// || status.text() === "Unknown"
						// || status.text() === "Error") {
						// $("#pageid").removeAttr("disabled");
						// $("#enginerate").removeAttr("disabled");
						// $("#enginecontrol").removeAttr("disabled");
						// $("#starttime").removeAttr("disabled");
						// $("#endtime").removeAttr("disabled");
						// $("#queuename").removeAttr("disabled");
						// $("#endpoint").removeAttr("disabled");
						// $("#tenantid").removeAttr("disabled");
						// $("#mediatype").removeAttr("disabled");
						// $("#startdate").removeAttr("disabled");
						// $("#engineicon")
						// .attr("src", "img/stop.png");
						// $("#engineicon").attr("alt", "Start");
						// } else if (status.text() === "Running"
						// || status.text() === "Initiating"
						// || status.text() === "Sleep") {
						// $("#enginerate").attr("disabled",
						// "disabled");
						// $("#pageid").attr("disabled", "disabled");
						// $("#starttime")
						// .attr("disabled", "disabled");
						// $("#endtime").attr("disabled", "disabled");
						// $("#queuename")
						// .attr("disabled", "disabled");
						// $("#endpoint").attr("disabled", "disabled");
						// $("#tenantid").attr("disabled", "disabled");
						// $("#mediatype")
						// .attr("disabled", "disabled");
						// $("#startdate")
						// .attr("disabled", "disabled");
						// $("#engineicon").attr("src", "img/run.png");
						// $("#engineicon").attr("alt", "Stop");
						// }
						// }
						//
						// }

					} else {
						console.log(data.errorMessage);
					}
				}

			});
}
function updateTenantData() {
	console.log("Update button clicked");
	console.log("Start time is" + $("#starttime").val() + " ,End time is"
			+ $("#endtime").val());

	var JSONObject = {
		"id" : sessionStorage.userID,
		"accesstoken" : sessionStorage.accessToken,
		"user" : sessionStorage.userName,
		// "page":$("#pageid").val(),
		"startTime" : $("#starttime").val(),
		"endTime" : $("#endtime").val(),
		"startDate" : $("#startdate").val(),
		"rate" : $("#enginerate").val(),

		// TODO Start of Genesys Configuration , should move this part in the
		// Genesys Configuration Section
		// "queueName":$("#queuename").val(),
		// "endPoint":encodeURIComponent($("#endpoint").val()),
		// "tenantId":$("#tenantid").val(),
		// "mediaType":$("#mediatype").val(),
		// TODO End of Genesys Configuration

		"facebookData" : {
			"posts" : $("#FBPosts").is(':checked'),
			"comments" : $("#FBComments").is(':checked'),
			"replies" : $("#FBReplies").is(':checked'),
			"console" : $("#FBConsole").is(':checked'),
			"restAPI" : $("#FBRestAPI").is(':checked'),
			"contactCenter" : $("#FBContactCenter").is(':checked'),
			"pages" : FBPAGES,
			"restAPIData" : {
				"uri" : $("#FBRestURL").val(),
				"methodGet" : $("#FBRadioGET").is(':checked'),
				"methodPost" : $("#FBRadioPOST").is(':checked'),
				"inBodyData" : $("#FBRadioBody").is(':checked'),
				"inParameterData" : $("#FBRadioParameter").is(':checked'),
				"parameterName" : $("#FBParamName").val()
			},
			"contactCenterData" : {
				"genesysCPData" : {
					"queueName" : $("#FBQueuename").val(),
					"endPoint" : $("#FBEndpoint").val(),
					"tenantId" : $("#FBMediatype").val(),
					"mediaType" : $("#FBTenantid").val()
				},
				"ciscoSMData" : {
					"host" : $("#FBSocialMinerHost").val(),
					"port" : $("#FBSocialMinerPort").val()
				}
			}
		},
		"instagramData" : {
			"posts" : $("#InstaPosts").is(':checked'),
			"comments" : $("#InstaComments").is(':checked'),
			"replies" : $("#InstaReplies").is(':checked'),
			"console" : $("#InstaConsole").is(':checked'),
			"restAPI" : $("#InstaRestAPI").is(':checked'),
			"contactCenter" : $("#InstaContactCenter").is(':checked'),
			"pages" : InstaPAGES,
			"restAPIData" : {
				"uri" : $("#InstaRestURL").val(),
				"methodGet" : $("#InstaRadioGET").is(':checked'),
				"methodPost" : $("#InstaRadioPOST").is(':checked'),
				"inBodyData" : $("#InstaRadioBody").is(':checked'),
				"inParameterData" : $("#InstaRadioParameter").is(':checked'),
				"parameterName" : $("#InstaParamName").val()
			},
			"contactCenterData" : {
				"genesysCPData" : {
					"queueName" : $("#InstaQueuename").val(),
					"endPoint" : $("#InstaEndpoint").val(),
					"tenantId" : $("#InstaMediatype").val(),
					"mediaType" : $("#InstaTenantid").val()
				},
				"ciscoSMData" : {
					"host" : $("#InstaSocialMinerHost").val(),
					"port" : $("#InstaSocialMinerPort").val()
				}
			}
		},
		"twitterData" : {
			"id" : sessionStorage.twitterUserID,
			"userName" : sessionStorage.twitterUserName,
			"profilePicUrl" : sessionStorage.twitterProfilePic,
			"token" : sessionStorage.twitterToken,
			"tokenSecret" : sessionStorage.twitterTokenSecret,
			"tweets" : $("#TwitterTweets").is(':checked'),
			"console" : $("#TwitterConsole").is(':checked'),
			"restAPI" : $("#TwitterRestAPI").is(':checked'),
			"contactCenter" : $("#TwitterContactCenter").is(':checked'),
			"keys" : TwitterKEYS,
			"restAPIData" : {
				"uri" : $("#TwitterRestURL").val(),
				"methodGet" : $("#TwitterRadioGET").is(':checked'),
				"methodPost" : $("#TwitterRadioPOST").is(':checked'),
				"inBodyData" : $("#TwitterRadioBody").is(':checked'),
				"inParameterData" : $("#TwitterRadioParameter").is(':checked'),
				"parameterName" : $("#TwitterParamName").val()
			},
			"contactCenterData" : {
				"genesysCPData" : {
					"queueName" : $("#TwitterQueuename").val(),
					"endPoint" : $("#TwitterEndpoint").val(),
					"tenantId" : $("#TwitterMediatype").val(),
					"mediaType" : $("#TwitterTenantid").val()
				},
				"ciscoSMData" : {
					"host" : $("#TwitterSocialMinerHost").val(),
					"port" : $("#TwitterSocialMinerPort").val()
				}
			}
		}

	};

	// console.log("My Tenant Data: ", JSON.stringify(JSONObject));
	var url = SERVER_URL + "/InstGenesys/rest/InstRest/UpdateVer13";
	$.ajax({
		type : 'post',
		dataType : 'json',
		// async: false,
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(JSONObject),
		url : url,
		success : function(data) {
			if (data != null && data.hasOwnProperty('error')) {
				if (data.error) {// Error Result
					console.log(data.errorMessage);
				}
			}
			checkTenantStatus();
		}

	});
}

function UUID() {
	return 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
		return v.toString(16);
	});
}

$(document)
		.ready(

				function() {
					$("#FBConsoleTable").DataTable();
					$("#InstaConsoleTable").DataTable();
					$("#TwitterConsoleTable").DataTable();

					var twitterid = getUrlParameter('twitterid');
					if (twitterid != null && twitterid != 'null'
							&& twitterid != 'undefined') { // Twitter is
						// authenticated
						// alert("Twitter ID is="+twitterid+", screen
						// Name="+getUrlParameter('screenName')+", user
						// Name="+getUrlParameter('userName')+",
						// profilePicUrl="+getUrlParameter('profilePicUrl'));
						sessionStorage.twitterUserID = twitterid;
						sessionStorage.twitterScreenName = getUrlParameter('screenName');
						sessionStorage.twitterUserName = getUrlParameter(
								'userName').replace(/\+/g, ' ');
						sessionStorage.twitterProfilePic = getUrlParameter('profilePicUrl');
						sessionStorage.twitterToken = getUrlParameter('accessToken');
						sessionStorage.twitterTokenSecret = getUrlParameter('tokenSecret');

						// Logged into your app and Facebook.
						welcomeTwitterTenant();
						// checkTenantStatus();
					}

					$("#TwitterLoginButton")
							.click(
									function() {
										alert("Twitter Login Clicked");
										var url = SERVER_URL
												+ "/InstGenesys/rest/TwitterRest/Login";

										$.ajax({
											type : 'GET',
											url : url,
											success : function(response) {
											}

										});
									});

					$("#TwitterEngineButton").click(
							function() {
								updateTenantData();
								var action = $(this).val() + "Ver13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID
										+ "&type=Twitter";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#InstaEngineButton")
							.click(
									function() {
										updateTenantData();
										var action = $(this).val() + "Ver13";
										var url = SERVER_URL
												+ "/InstGenesys/rest/InstRest/"
												+ action + "?id="
												+ sessionStorage.userID
												+ "&type=Insta";
										$.ajax({
											type : 'GET',
											dataType : "json",
											url : url,
											success : function(data) {
												if (data.error) {
													alert(data.errorMessage);
												}
												checkTenantStatus();
											}

										});
									});

					$("#FBEngineButton").click(
							function() {
								updateTenantData();
								var action = $(this).val() + "Ver13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID + "&type=FB";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#TwitterEngineHistoryButton7days").click(
							function() {
								updateTenantData();
								var action = $(this).attr('alt')
										+ "HistoryVer13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID
										+ "&type=Twitter&searchType=standard";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#TwitterEngineHistoryButton30days").click(
							function() {
								updateTenantData();
								var action = $(this).attr('alt')
										+ "HistoryVer13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID
										+ "&type=Twitter&searchType=30days";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#TwitterEngineHistoryButtonfull").click(
							function() {
								updateTenantData();
								var action = $(this).attr('alt')
										+ "HistoryVer13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID
										+ "&type=Twitter&searchType=full";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#FBEngineHistoryButton").click(
							function() {
								updateTenantData();
								var action = $(this).attr('alt')
										+ "HistoryVer13";
								var url = SERVER_URL
										+ "/InstGenesys/rest/InstRest/"
										+ action + "?id="
										+ sessionStorage.userID + "&type=FB";
								$.ajax({
									type : 'GET',
									dataType : "json",
									url : url,
									success : function(data) {
										if (data.error) {
											alert(data.errorMessage);
										}
										checkTenantStatus();
									}

								});
							});

					$("#InstaEngineHistoryButton")
							.click(
									function() {
										updateTenantData();
										var action = $(this).attr('alt')
												+ "HistoryVer13";
										var url = SERVER_URL
												+ "/InstGenesys/rest/InstRest/"
												+ action + "?id="
												+ sessionStorage.userID
												+ "&type=Insta";
										$.ajax({
											type : 'GET',
											dataType : "json",
											url : url,
											success : function(data) {
												if (data.error) {
													alert(data.errorMessage);
												}
												checkTenantStatus();
											}

										});
									});

					$("#engineicon")
							.click(
									function() {
										updateTenantData();
										var action = $(this).attr('alt');
										var url = SERVER_URL
												+ "/InstGenesys/rest/InstRest/"
												+ action + "?id="
												+ sessionStorage.userID;
										$
												.ajax({
													type : 'GET',
													dataType : "xml",
													url : url,
													success : function(xml) {
														var error = $(xml)
																.find("error")
																.text();

														if (error == true) {
															var errorMessage = $(
																	xml)
																	.find(
																			"errorMessage");
														}
														checkTenantStatus();
													}

												});
									});
				});
function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {

		console.log("Access Token is:" + response.authResponse.accessToken);
		console.log("User ID is:" + response.authResponse.userID);
		sessionStorage.accessToken = response.authResponse.accessToken;
		sessionStorage.userID = response.authResponse.userID;

		// Logged into your app and Facebook.
		welcomeTenant();
		checkTenantStatus();

	} else {
		// sessionStorage.accessToken =
		// "EAACjfdf1m7cBAApqASXJ7DNYDinuoVXVaB8uWYdR2IKnZAIrGrZChgyZACPvdhU1EKEF2iEwCyZAClNjIbZAxxmBrZBnL9AQwFwNDsTOpOnZC6ZCqOifc3TLEhZAlPbKj5BTTZAZCRaWhBq6H0sfp7vw90dtAHAPhL9mKe46McQFppFwgZDZD";
		// sessionStorage.userID = "10159831212825333";
		// sessionStorage.userName = "Mamdouh Aref Mohamed";
		// checkTenantStatus();
	}
}

// This function is called when someone finishes with the Login
// Button. See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '1833042753424607',
		cookie : true, // enable cookies to allow the server to access
		// the session
		xfbml : true, // parse social plugins on this page
		version : 'v2.12' // use graph api version 2.8
	});

	// Now that we've initialized the JavaScript SDK, we call
	// FB.getLoginStatus(). This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide. They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	// your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful. See statusChangeCallback() for when this call is made.
function welcomeTenant() {
	console.log('Welcome!  Fetching your information.... ');

	FB.api('/me', 'GET', {
		"fields" : "id,name,picture"
	}, function(response) {
		console.log('Successful login for: ' + response.name);
		$("#fbstatus").empty();
		// response.authResponse.accessToken
		$("#fbName").empty();
		$("#fbName").append('<strong>' + response.name + '</strong>');
		$("#fbPic").attr("src", response.picture.data.url);
		$("#fbName").removeClass("hidden");
		$("#fbPic").removeClass("hidden");

		$("#instastatus").empty();

		$("#instaName").empty();
		$("#instaName").append('<strong>' + response.name + '</strong>');
		$("#instaPic").attr("src", response.picture.data.url);
		$("#instaName").removeClass("hidden");
		$("#instaPic").removeClass("hidden");

		// $("#fbPic").empty();
		// $("#fbPic").append(response.profile_pic);
		sessionStorage.userName = response.name;
	});
}

function welcomeTwitterTenant() {
	$("#twitterstatus").empty();

	$("#twitterName").empty();
	$("#twitterName").append(
			'<strong>' + sessionStorage.twitterUserName + '</strong>');
	$("#twitterPic").attr("src", sessionStorage.twitterProfilePic);
	$("#twitterName").removeClass("hidden");
	$("#twitterPic").removeClass("hidden");
}