// JavaScript Document
//InstGenesys Integration function
var SERVER_URL = "/..";
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

function checkInstGenesysStatus() {
	$("#pageid").empty();
	$("#enginestatus").empty();
	$("#enginerate").empty();
	$("#starttime").empty();
	$("#endtime").empty();
	$("#startdate").empty();
	$("#queuename").empty();
	$("#endpoint").empty();
	$("#mediatype").empty();
	$("#tenantid").empty();
	
	
	$("#enginestatus").attr("disabled", "disabled");
	$("#starttime").attr("disabled", "disabled");
	$("#endtime").attr("disabled", "disabled");
	$("#queuename").attr("disabled", "disabled");
	$("#endpoint").attr("disabled", "disabled");
	$("#mediatype").attr("disabled", "disabled");
	$("#tenantid").attr("disabled", "disabled");
	$("#startdate").attr("disabled", "disabled");

	var url = SERVER_URL + "/InstGenesys/rest/InstRest/Status?id="
			+ sessionStorage.userID;
	$.ajax({
		type : 'GET',
		dataType : "xml",
		url : url,
		success : function(xml) {
			var error = $(xml).find("error").text();

			if (error != true) {
				var userID = $(xml).find("id");
				var pageID = $(xml).find("page");
				var token = $(xml).find("token");
				var status = $(xml).find("status");
				var rate = $(xml).find("rate");
				var routedcount = $(xml).find("routedcount");
				var starttime = $(xml).find("starttime");
				var endtime = $(xml).find("endtime");
				var queuename = $(xml).find("queuename");
				var endpoint = $(xml).find("endpoint");
				var tenantid = $(xml).find("tenantid");
				var mediatype = $(xml).find("mediatype");
				var startdate = $(xml).find("startdate");

				if(startdate != null)
					$("#startdate").val(startdate.text());
				if(starttime != null)
					$("#starttime").val(starttime.text());
				if(endtime != null)
					$("#endtime").val(endtime.text());
				if(queuename != null)
					$("#queuename").val(queuename.text());
				if(endpoint != null)
					$("#endpoint").val(endpoint.text());
				if(tenantid != null)
					$("#tenantid").val(tenantid.text());
				if(mediatype != null)
					$("#mediatype").val(mediatype.text());
					
				if (pageID == null) {
				} else {
					$("#pageid").val(pageID.text());

					if (status != null) {
						$("#enginestatus").val(status.text());
						if (status.text() === "Stop"
								|| status.text() === "Unknown"
								|| status.text() === "Error") {
							$("#pageid").removeAttr("disabled");
							$("#enginerate").removeAttr("disabled");
							$("#enginecontrol").removeAttr("disabled");
							$("#starttime").removeAttr("disabled");
							$("#endtime").removeAttr("disabled");
							$("#queuename").removeAttr("disabled");
							$("#endpoint").removeAttr("disabled");
							$("#tenantid").removeAttr("disabled");
							$("#mediatype").removeAttr("disabled");
							$("#startdate").removeAttr("disabled");
							$("#engineicon").attr("src","img/stop.png");
							$("#engineicon").attr("alt","Start");
						} else if (status.text() === "Running"
								|| status.text() === "Initiating"
								|| status.text() === "Sleep") {
							$("#enginerate").attr("disabled", "disabled");
							$("#pageid").attr("disabled", "disabled");
							$("#starttime").attr("disabled", "disabled");
							$("#endtime").attr("disabled", "disabled");
							$("#queuename").attr("disabled", "disabled");
							$("#endpoint").attr("disabled", "disabled");
							$("#tenantid").attr("disabled", "disabled");
							$("#mediatype").attr("disabled", "disabled");
							$("#startdate").attr("disabled", "disabled");
							$("#engineicon").attr("src","img/run.png");
							$("#engineicon").attr("alt","Stop");
						}
					}

					if (rate != null) {
						if (parseInt(rate.text()) > 0)
							$("#enginerate").val(rate.text());
					}
					
					if (routedcount != null) {
						if (parseInt(routedcount.text()) > 0)
							$("#routedcount").val(routedcount.text());
					}

				}

			} else {
				var errorMessage = $(xml).find("errorMessage");
			}
		}

	});
}
function updateTenantData(){
	console.log("Update button clicked");
	console.log("Start time is"+$("#starttime").val());
	console.log("end time is"+$("#endtime").val());
	var url = SERVER_URL
			+ "/InstGenesys/rest/InstRest/Update?id="
			+ sessionStorage.userID + "&page="
			+ $("#pageid").val() + "&token="
			+ sessionStorage.accessToken + "&user="
			+ sessionStorage.userName + "&starttime="
			+ $("#starttime").val() + "&endtime="
			+ $("#endtime").val() + "&queuename="
			+ $("#queuename").val() + "&endpoint="
			+ encodeURIComponent($("#endpoint").val()) + "&rate="
			+ $("#enginerate").val() + "&tenantid="
			+ $("#tenantid").val() + "&mediatype="
			+ $("#mediatype").val() + "&startdate="
			+ $("#startdate").val();
	$.ajax({
		type : 'GET',
		dataType : "xml",
		url : url,
		success : function(xml) {
			var error = $(xml).find("error").text();

			if (error == true) {
				var errorMessage = $(xml).find(
						"errorMessage");
			}
//			checkInstGenesysStatus();
		}

	});
}

$(document).ready(
		
		function() {
			var code = getUrlParameter('code');
			if (code != null && code != 'null' && code != 'undefined'){
				alert(code);
				alert("We should request the access token now");
			}
			
			$("#engineicon").click(
					function() {
						updateTenantData();
						var action = $(this).attr('alt');
						var url = SERVER_URL + "/InstGenesys/rest/InstRest/"
								+ action + "?id=" + sessionStorage.userID;
						$.ajax({
							type : 'GET',
							dataType : "xml",
							url : url,
							success : function(xml) {
								var error = $(xml).find("error").text();

								if (error == true) {
									var errorMessage = $(xml).find(
											"errorMessage");
								}
								checkInstGenesysStatus();
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
		checkInstGenesysStatus();

	} else {
//		sessionStorage.accessToken = "EAACjfdf1m7cBAApqASXJ7DNYDinuoVXVaB8uWYdR2IKnZAIrGrZChgyZACPvdhU1EKEF2iEwCyZAClNjIbZAxxmBrZBnL9AQwFwNDsTOpOnZC6ZCqOifc3TLEhZAlPbKj5BTTZAZCRaWhBq6H0sfp7vw90dtAHAPhL9mKe46McQFppFwgZDZD";
//		sessionStorage.userID = "10159831212825333";
//		sessionStorage.userName = "Mamdouh Aref Mohamed";
//		checkInstGenesysStatus();
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
		version : 'v2.8' // use graph api version 2.8
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
	FB.api('/me', function(response) {
		console.log('Successful login for: ' + response.name);
		document.getElementById('status').innerHTML = 'Thanks for logging in, '
				+ response.name + '!';// + "and Access Token is:" +
		// response.authResponse.accessToken
		sessionStorage.userName = response.name;
	});
}