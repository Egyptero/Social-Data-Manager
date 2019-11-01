var selectedInteractionId = -1;
var id = '10159831212825333';
var commentId = '17915077519067810';
var parentCommentId;
var userId;
var userName;
var from;
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

$(document).ready(function() {

	// var url_string = window.location.href ;
	// var url = new URL(url_string) ;
	// selectedInteractionId =
	// url.searchParams.get("Interaction.Id") ;
	var demo = getUrlParameter('demo');
	if (demo != null && demo != 'null' && demo != 'undefined')
		ajaxCall("/InstGenesys/rest/InstClientRest/getInteraction", {
			id : id,
			commentId : commentId
		}, renderPage);
	else
		getInteractionById();

	// $("#likeButton").on('click', function() {
	// var myClass = $(this).attr("class");
	// if(myClass == "glyphicon glyphicon-heart"){
	// ajaxCall("http://localhost:8080/mowasat/ist/instagram/deleteLike",
	// {
	// postId : postId
	// }, renderLikes);
	// }else{
	// ajaxCall("http://localhost:8080/mowasat/ist/instagram/addLike",
	// {
	// postId : postId
	// }, renderLikes);
	// }
	// });
	// http://localhost:8080/InstGenesys/rest/InstClientRest/addReply?id=10159831212825333&commentId=17894904979140713&text=%27good%20idea%20for%20me%27
	$("#sendres").click(function(){
		$("#sendres").attr("disabled", true);
		$("#agentres").attr("disabled", true);
		sendResponse();
	});
	$("#agentres").keyup(function(){
		toogleSendResBtn();
	});
	
	$("#agentres").keypress(function(e) {
		if (e.which == 13) {
			$("#sendres").attr("disabled", true);
			$("#agentres").attr("disabled", true);
			e.preventDefault();
			sendResponse();
			
		}
	});

});

function toogleSendResBtn() {
	if($("#agentres").val() != null && $("#agentres").val() != 'null' && $("#agentres").val() != "")
		$("#sendres").removeAttr("disabled");
	else
		$("#sendres").attr("disabled", true);
}

function sendResponse(){
	var comment = $("#agentres").val();
	comment = "@" + from + " " + comment;
	ajaxCall("/InstGenesys/rest/InstClientRest/addReply", {
		id : userId,
		text : comment,
		commentId : parentCommentId
	}, function(response) {
		if (response != null && response != 'null') {
			if (!response.error) {
				addComment(userName, comment, 1);
				$("#agentres").val("");
			} else {
				$("#sendres").removeAttr("disabled");
				$("#agentres").removeAttr("disabled");
				$('#error').text("Can not send your response. Please contact system administrator");
			}
		}
	});
}
function renderPage(res) {
	if (res.instComment == null || res.instComment == 'null' || res.instComment == 'undefined') {
		$('#error').text("Server is not working. Please contact system administrator");
		return;
	}
	parentCommentId = res.instComment.id
	userId = res.id;
	renderUserData(res);
	renderReplies(res);
	renderLikes(res);
}

function renderLikes(res) {
	var instComment = res.instComment;
	var instReply = res.instReply;
	
	if('like_count' in res.instMedia)
		postLikeCount = res.instMedia.like_count;
	else
		postLikeCount = '0';
	
	if('like_count' in instComment)
		commentLikeCount = instComment.like_count;
	else
		commentLikeCount = '0';
	
	if (instReply == null || instReply == 'null') { // We have a comment as a
		$("#likeArea").html("<strong>Post</strong> has <strong>"+postLikeCount+"</strong> like(s)"+
		" and <strong>Comment</strong> has <strong>"+commentLikeCount+"</strong> like(s).");
//				"Post has " + res.instMedia.like_count
//						+ " Like(s) and Comment has "
//						+ res.instComment.like_count + " Like(s)");
	} else {
		if('like_count' in instReply)
			replyLikeCount = instReply.like_count;
		else
			replyLikeCount = '0';

		$("#likeArea").html("<strong>Post</strong> has <strong>"+postLikeCount+"</strong> like(s)"+
		" and <strong>Comment</strong> has <strong>"+commentLikeCount+"</strong> like(s)"+
		" and <strong>Reply</strong> has <strong>"+replyLikeCount+"</strong> like(s).");
	}

	// if (res.info.likes.count > 0) {
	// $("#likeArea").text(res.info.likes.count + " Like(s)");
	// } else {
	// $("#likeArea").text("Be the first one who like this !!!");
	// }

	// if (res.info.user_has_liked) {
	// $("#likeButton").removeClass("glyphicon glyphicon-heart-empty")
	// .addClass("glyphicon glyphicon-heart");
	// } else {
	// $("#likeButton").removeClass("glyphicon glyphicon-heart").addClass(
	// "glyphicon glyphicon-heart-empty");
	// }
}

function addComment(_userName, _text, _type) {
	if (_type == 0) {
		var rowElement = $("<div class='row'></div>").appendTo('#history');
		$("<div class='col-md-4'><strong>" + _userName + "</strong></div>")
				.appendTo(rowElement);
		$("<div class='col-md-1'></div>")
		.appendTo(rowElement);
		$("<div class='col-md-7'>" + _text + "</div>").appendTo(
				rowElement);
		var d = $('#history');
		d.scrollTop(d.prop("scrollHeight"));

	} else {
		var rowElement = $("<div class='row' style='margin-left:5px;'></div>").appendTo('#history');
		$("<div class='col-md-4'><strong>" + _userName + "</strong></div>")
				.appendTo(rowElement);
		$("<div class='col-md-1'></div>")
		.appendTo(rowElement);
		$("<div class='col-md-7'>" + _text + "</div>").appendTo(
				rowElement);
		var d = $('#history');
		d.scrollTop(d.prop("scrollHeight"));
	}
}

function renderReplies(res) {
	var instComment = res.instComment;
	var instReply = res.instReply;
	if (instReply == null || instReply == 'null' || instReply == 'undefined') { // We have a comment as a
													// feed
		console.log("We have comment as feed");
		addComment(instComment.user.username, instComment.text, 0);
		from = res.instComment.user.username;

		// Render only received comment
		// for(var i = res.instMedia.comments.data.length; i >=0 ; i--) {
		// var instcomment = res.instMedia.comments.data[i];
		//
		// if(instcomment.user.id == res.businessAccountID)
		// addComment("Me", instcomment.text, 0);
		// else if(instcomment.user.id == res.instUser.id)
		// addComment(res.instUser.name, instcomment.text, 0);
		// else
		// addComment(instcomment.user.id, instcomment.text, 0);
		// }
	} else { // We have reply as feed
		console.log("We have reply as feed");
		addComment(instComment.user.username, instComment.text, 0);
		for (var i = instComment.replies.data.length - 1; i >= 0; i--) {
			var instreply = instComment.replies.data[i]
			console.log(instreply);
//			if (instreply.user.id == res.instUser.id) // My Comment
				

			addComment(instreply.user.username, instreply.text, 1);
		}
		from = res.instReply.user.username;
	}

}

function renderUserData(res) {
	var instMedia = res.instMedia;
	if (instMedia != null) {
		$("#media").attr("src", instMedia.media_url);
		userName = instMedia.owner.username;
	}
	
	var instUser = res.instUser;
	if (instUser != null) {
		$("#username").text(instUser.name);
		$("#userpic").attr("src", instUser.profile_picture_url);
	} else { // In case we can not find user data
		if(res.instReply != null)
			$("#username").text(res.instReply.user.username);
		else if(res.instComment != null)
			$("#username").text(res.instComment.user.username);
	}
}

function ajaxCall(url, data, successFunction) {
	jQuery.ajax({
		url : url,
		type : "get",
		data : data,
	}).then(successFunction);
}

function getInteractionById() {
	var interactionID = getUrlParameter('InteractionId');

	if (interactionID == null || interactionID == 'null' || interactionID == -1 || interactionID == 'undefined') {
		// if (selectedInteractionId == -1) {
		$('#error').text("The interaction id was not found on the URL");
		return;
	}

	genesys.wwe.service.sendMessage({
		request : "interaction.getByInteractionId",
		parameters : [ interactionID ]
	}, function(response) { // success
		console.log("getByInteractionId success: " + JSON.stringify(response));
		postId = response.data.userData.postId;
		commentId = response.data.userData.commentId;
		if (response.data.media == "instagram") {
			ajaxCall("/InstGenesys/rest/InstClientRest/getInteraction", {
				id : postId,
				commentId : commentId
			}, renderPage);

		}
	}, function(data) { // failure
		$('#error').text("Unable to get interaction: id = " + interactionID);
		interactionID = -1;
	});
}