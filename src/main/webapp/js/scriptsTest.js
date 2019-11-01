// JavaScript Document
//InstGenesys Integration function
var SERVER_URL = "/..";
var FacebookPageData = [];
var FacebookPostsData = [];
var FacebookCommentsData = [];
var FacebookReplysData = [];
var FetchCycle = 0;
var TotalPosts = 0;
var TotalComments = 0;
var TotalReplies = 0;
var FacebookLoader = false;
var pageID = "";
var FacebookPageInsights = [ [ 'Time', 'Posts', 'Comments', 'Fans' ] ]; // Temp
// to
// be
// removed
var FacebookPageFetchInsights = [];

var InstagramPageInsights = [];


function drawFBChart(){
    // Create our data table out of JSON data loaded from server.
	//console.log("Draw the FB Graph");
    var data = new google.visualization.DataTable();
    data.addColumn('datetime', 'Time');
    data.addColumn('number', 'Posts');
    data.addColumn('number', 'Comments');
    data.addColumn('number', 'Replies');
    data.addColumn('number', 'Fans');
    if(FacebookPageFetchInsights.length > 0)
    	data.addRows(FacebookPageFetchInsights);    
    //data.addRows(FacebookPageFetchInsights);

    var options = {'title':'Fetch Analysis','width':'auto',
            'height':'auto'};

      var chart = new google.visualization.BarChart(document.getElementById('barchart_FB'));
      chart.draw(data, options);
}

function startFacebookFetcher() {
	//console.log("Starting fetcher job");
	loadFacebookData();
}
// Function to get Facebook Pages
function getFacebookPages() {
	var pagesArray = [];
	FB.api('/me/accounts', function(response) {
		//console.log("Loading your pages and response is:"
		//		+ JSON.stringify(response));

		$.each(response.data, function(index, val) {

			pagesArray.push({
				name : val.name,
				id : val.id
			});
			var $div = $("<option>", {
				value : val.id,
			}).text(val.name);
			$(".pageselect").append($div);
		});

		// console.log(JSON.stringify(pagesArray));

	});
	return pagesArray;
}

function loadFacebookData() {

	//console.log("Loading Facebook Page Data");
	var e = document.getElementById("pageid");
	if (pageID != e.options[e.selectedIndex].value) {
		pageID = e.options[e.selectedIndex].value;
		FetchCycle = 1;
	} else if (pageID != "")
		FetchCycle += 1;

	$("#totalFetchs").val(FetchCycle);

	//console.log(pageID);
	FB
			.api(
					'/'
							+ pageID
							+ '?fields=category,posts{from,link,comments{comment_count,user_likes,message,comments{comment_count,user_likes,message,created_time,from,like_count},created_time,from,like_count},message,created_time,likes{id,name,pic_crop},admin_creator,permalink_url,picture},instagram_business_account,fan_count',
					function(response) {
						// console.log('Page Data loaded: '
						// + JSON.stringify(response));
						// console.log('Page Category:' + response.category)
						ProcessPageData(response);
						
					});
	if (FacebookLoader) {
		interval = parseInt($("#enginerate").val()) * 60 * 1000;
		setTimeout(loadFacebookData, interval);
	}
	// AnalyseInstagramData();
	// AnalyseFacebookData() ;

}

function ProcessPost(post) {
	outcome = true;
	$.each(FacebookPageData, function(index, pageData) {
		if (pageData.data.hasOwnProperty('posts'))
			if (pageData.data.posts.hasOwnProperty('data')) {
				$.each(pageData.data.posts.data, function(index, val) {
					if (JSON.stringify(val) == JSON.stringify(post)) { // Post
						// Exist
						//console.log("Post is existing");
						outcome = false;
					}
				});
			}
	});
	return outcome;
}

function ProcessPageData(response) {

	postCount = 0;
	commentCount = 0;
	replyCount = 0;
	timeStamp = new Date();
	
	postDetails = "<div class='col-xs-12'>"
	
	var newPageResponse = response;
	if (response.hasOwnProperty('posts'))
		if (response.posts.hasOwnProperty('data')) {
			$.each(response.posts.data, function(index, post) {
				if (!ProcessPost(post)){ // POST Already Exit , so remove it
					//console.log("We need to remove index ="+index);
					delete newPageResponse.posts.data[index];
				} else {
					TotalPosts += 1;
					postCount += 1;
					
					postDetails += 
						"<hr><div class='row'>"+
				          "<div class='col-xs-9'>";
				    if(post.message != null && post.message != "undefined")
				    	postDetails += "<div class='row'>"+
						    post.message+
						  "</div>";
				    if(post.picture != null && post.picture != "undefined")      
				    	postDetails += "<div class='row'>"+
						  "<img src='"+post.picture+"' class='img-responsive'>"+
						  "</div>";
				          
				    postDetails += "</div>"+
				          "<div class='col-xs-3'>"+
				          	"<a type='button' class='btn btn-info form-control' href='"+post.permalink_url+"' target='new'>Engage</a>"+
				          "</div>"+
				        "</div>";
					
					if (post.hasOwnProperty('comments'))
						if (post.comments.hasOwnProperty('data')) {
							$.each(post.comments.data,
									function(index, comment) {
										TotalComments += 1;
										commentCount += 1;
										if (comment.hasOwnProperty('comments'))
											if (comment.comments
													.hasOwnProperty('data')) {

												$.each(comment.comments.data,
														function(index, reply) {
															TotalReplies += 1;
															replyCount += 1;
														});
											}
									});
						}
				}
			});
		}
	$("#totalPosts").val(TotalPosts);
	$("#totalComments").val(TotalComments);
	$("#totalReplies").val(TotalReplies);
	// Push Response to FacebookPageData
	FacebookPageData.push({
		data : newPageResponse,
		timestamp : timeStamp
	});
	FacebookPageFetchInsights.push([ timeStamp, postCount, commentCount,
			replyCount, response.fan_count ]);
	postDetails += "</div>";
	
	uniqueID = Date.now();
	$("#FBFetchData")
			.prepend( //append
					"<div class='row'>"
							+ "<div class='panel panel-default'>"
							+ "<div class='panel-heading'>"
							+ "<div class='row'>"
							+ "<div class='col-xs-12'>"
							+ "<h4 class='panel-title'>"
							+ "<a data-toggle='collapse' data-parent='#accordion'" + "href='#FetchInsights" + uniqueID + "'><span"
							+ "class='glyphicon glyphicon-plus'></span> Fetch @ " + timeStamp.toLocaleDateString() + "-" + timeStamp.toLocaleTimeString() + "</a>"
							+ "</h4>"
							+ "</div>"
							+ "</div>"
							+ "</div>"
							+ "<div id='FetchInsights"
							+ uniqueID
							+ "' class='panel-collapse collapse'>"
							+ "<div class='panel-body'>"
							+ "<div class='row'>"
							+ "<div class='row'>"
							+ "<div class='col-xs-4 col-xs-offset-4'>"
							+ "<button type='button' class='btn btn-danger'>Route to Call Center</button>"
							+ "</div>"
							+ "</div>"
							+ "<br>"
							+ "<div class='col-xs-4'>"
							+ "<div class='input-group'>"
							+ "<span class='input-group-addon'>Posts</span> <input "
							+ "type='text' class='form-control' placeholder='--' value='"
							+ postCount
							+ "'>"
							+ "</div>"
							+ "</div>"
							+ "<div class='col-xs-4'>"
							+ "<div class='input-group'>"
							+ "<span class='input-group-addon'>Comments</span> <input "
							+ "type='text' class='form-control' placeholder='--' value='"
							+ commentCount
							+ "'>"
							+ "</div>"
							+ "</div>"
							+ "<div class='col-xs-4'>"
							+ "<div class='input-group'>"
							+ "<span class='input-group-addon'>Replies</span> <input "
							+ "type='text' class='form-control' placeholder='--' value='"
							+ replyCount
							+ "'>"
							+ "</div>"
							+ "</div>"
							+ postDetails
							+ "</div>"
							+ "</div>"
							+ "</div>"
							+ "</div>"
							+ "</div>");

	drawFBChart();
	
	// console.log('FB Data Aray: ' + JSON.stringify(FacebookPageData));
	localStorage.setItem('PageDataStorage', JSON.stringify(FacebookPageData));
	
	var obj = JSON.parse(localStorage.getItem('PageDataStorage'));
	
	// console.log('stored FB Data Aray: ' + JSON.stringify(obj));

}

function ProcessInstagramData() {

}
function AnalyseFacebookData() {

	var pageID = 521520704531774;

	FB
			.api(
					'/'
							+ pageID
							+ '?fields=category,posts{from,link,comments{comment_count,user_likes,message,comments{comment_count,user_likes,message,created_time,from,like_count},created_time,from,like_count},message,created_time,likes{id,name,pic_crop}},instagram_business_account,fan_count',
					function(response) {
						console.log('Page Data loaded: '
								+ JSON.stringify(response));
						console.log('Page Category:' + response.category)
						var PageData = response;
						console.log('data ' + JSON.stringify(PageData));
						var TimeStamp = Date.now();
						console.log('Time ' + JSON.stringify(TimeStamp));
						var LikesNumber = PageData.fan_count;
						console.log('Likes ' + JSON.stringify(LikesNumber));
						var PageID = PageData.id;
						console.log('id ' + JSON.stringify(PageID));

						var PostsNumber = 0;
						var CommentsNumber = 0;
						var postsLikes = 0;
						$.each((PageData.posts).data, function(index, val) {

							PostsNumber = PostsNumber + 1;
							postsLikes = postsLikes + val.likes;
							if (val.comments != null) {

								$.each((val.comments).data, function(index,
										commentval) {

									CommentsNumber = CommentsNumber
											+ commentval.comment_count + 1;

								});
							}
						});
						console.log('posts ' + PostsNumber.toString());

						console.log('comments ' + CommentsNumber.toString());

						console.log('postsLikes  ' + postsLikes.toString());
						FacebookPageInsights.push([ TimeStamp, PostsNumber,
								CommentsNumber, LikesNumber ]);

						FacebookPageInsights.push([ TimeStamp + 30,
								PostsNumber + 1, CommentsNumber, LikesNumber ]);

						FacebookPageInsights.push([ TimeStamp + 20,
								PostsNumber, CommentsNumber + 3, LikesNumber ]);

						FacebookPageInsights.push([ TimeStamp + 10,
								PostsNumber + 2, CommentsNumber,
								LikesNumber + 1 ]);

						Initchart();

					});

}
function AnalyseInstagramData() {
	var pageID = 521520704531774;
	var InstaID = 17841408446075474;

	FB
			.api(
					'/'
							+ InstaID
							+ '/media?fields=id,media_type,media_url,timestamp,permalink,like_count,owner{id,username,profile_picture_url},caption,comments_count,comments{id,like_count,text,timestamp,username,user{id,username},replies{username,user{id,username},text}}',
					function(response) {
						var PostsNumber = 0;
						var postsLikes = 0;
						var CommentsNumber = 0;
						$.each(response.data, function(index, val) {

							PostsNumber = PostsNumber + 1;
							postsLikes = postsLikes + val.like_count;
							CommentsNumber = CommentsNumber
									+ val.comments_count + 1;
						});
						console.log('posts ' + PostsNumber.toString());

						console.log('comments ' + CommentsNumber.toString());

						console.log('postsLikes  ' + postsLikes.toString());
						InstagramPageInsights.push({
							time : Date.now(),
							postsnumber : PostsNumber,
							commentsnumber : CommentsNumber,
							postslikes : postsLikes
						});

					});

}

$(document).ready(

function() {
	// Load Charts and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

    // Draw the pie chart .
    google.charts.setOnLoadCallback(drawFBChart);	
	
	var code = getUrlParameter('code');
	if (code != null && code != 'null' && code != 'undefined') {
		alert(code);
		alert("We should request the access token now");
	}

	$("#engineicon").click(function() {
		// updateTenantData();
		var action = $(this).attr('alt');
		if (action == "Start") { // we need to start engine
			FacebookLoader = true;
			startFacebookFetcher();
			$("#engineicon").attr("src", "img/run.png");
			$("#engineicon").attr("alt", "Stop");
		} else if (action == "Stop") {
			FacebookLoader = false;
			$("#engineicon").attr("src", "img/stop.png");
			$("#engineicon").attr("alt", "Start");
		}
		// var url = SERVER_URL + "/InstGenesys/rest/InstRest/"
		// + action + "?id=" + sessionStorage.userID;
		// $.ajax({
		// type : 'GET',
		// dataType : "xml",
		// url : url,
		// success : function(xml) {
		// var error = $(xml).find("error").text();
		//
		// if (error == true) {
		// var errorMessage = $(xml).find(
		// "errorMessage");
		// }
		// checkInstGenesysStatus();
		// }
		//
		// });
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
		// checkInstGenesysStatus();

	} else {
		// sessionStorage.accessToken =
		// "EAACjfdf1m7cBAApqASXJ7DNYDinuoVXVaB8uWYdR2IKnZAIrGrZChgyZACPvdhU1EKEF2iEwCyZAClNjIbZAxxmBrZBnL9AQwFwNDsTOpOnZC6ZCqOifc3TLEhZAlPbKj5BTTZAZCRaWhBq6H0sfp7vw90dtAHAPhL9mKe46McQFppFwgZDZD";
		// sessionStorage.userID = "10159831212825333";
		// sessionStorage.userName = "Mamdouh Aref Mohamed";
		// checkInstGenesysStatus();
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
		getFacebookPages();

	});
}
// ///////////////////////////////////////////////////////////
function Initchart() {
	// Load the Visualization API and the piechart package.
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(loadChart);
}
function loadChart() {
	// Set chart options
	var options = {
		width : 400,
		height : 400,
		vAxis : {
			minValue : 0,
			maxValue : 1000
		},
		hAxis : {
			viewWindow : {
				min : 1,
				max : 5
			}
		},
		curveType : 'none', // or 'function'
		pointSize : 5,
		series : {
			0 : {
				color : 'Blue'
			},
			1 : {
				color : 'Orange'
			},
			3 : {
				color : 'Red'
			}
		},
		animation : {
			duration : 1000,
			easing : 'linear'
		}
	};

	// Create the data table.
	console.log(JSON.stringify(FacebookPageInsights));
	var data = new google.visualization.arrayToDataTable(FacebookPageInsights);

	// Instantiate our chart
	var chart = new google.visualization.LineChart(document
			.getElementById('google_chart_div'));

	// Define button
	var button = document.getElementById('next');

	function drawChart() {
		// Disabling the button while the chart is drawing.
		button.disabled = true;
		google.visualization.events.addListener(chart, 'ready', function() {
			button.disabled = false;
		});
		// Draw chart
		chart.draw(data, options);
	}

	button.onclick = function() {
		data.removeRow(0);
		data.insertRows(data.getNumberOfRows(), [ [ new Date() + '',
				Math.floor((Math.random() * (1400 - 600)) + 600), 900 ] ]);
		// options.hAxis.viewWindow.min += 1;
		// options.hAxis.viewWindow.max += 1;
		drawChart();

	};
	drawChart();
}

// /////////////UNUSED////////////////
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

				if (startdate != null)
					$("#startdate").val(startdate.text());
				if (starttime != null)
					$("#starttime").val(starttime.text());
				if (endtime != null)
					$("#endtime").val(endtime.text());
				if (queuename != null)
					$("#queuename").val(queuename.text());
				if (endpoint != null)
					$("#endpoint").val(endpoint.text());
				if (tenantid != null)
					$("#tenantid").val(tenantid.text());
				if (mediatype != null)
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
							$("#engineicon").attr("src", "img/stop.png");
							$("#engineicon").attr("alt", "Start");
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
							$("#engineicon").attr("src", "img/run.png");
							$("#engineicon").attr("alt", "Stop");
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
function updateTenantData() {
	console.log("Update button clicked");
	console.log("Start time is" + $("#starttime").val());
	console.log("end time is" + $("#endtime").val());
	var url = SERVER_URL + "/InstGenesys/rest/InstRest/Update?id="
			+ sessionStorage.userID + "&page=" + $("#pageid").val() + "&token="
			+ sessionStorage.accessToken + "&user=" + sessionStorage.userName
			+ "&starttime=" + $("#starttime").val() + "&endtime="
			+ $("#endtime").val() + "&queuename=" + $("#queuename").val()
			+ "&endpoint=" + encodeURIComponent($("#endpoint").val())
			+ "&rate=" + $("#enginerate").val() + "&tenantid="
			+ $("#tenantid").val() + "&mediatype=" + $("#mediatype").val()
			+ "&startdate=" + $("#startdate").val();
	$.ajax({
		type : 'GET',
		dataType : "xml",
		url : url,
		success : function(xml) {
			var error = $(xml).find("error").text();

			if (error == true) {
				var errorMessage = $(xml).find("errorMessage");
			}
			// checkInstGenesysStatus();
		}

	});
}
