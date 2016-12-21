"use strict";

var numberofpageForRide = 5;
var numberofpageForDrive = 5;
var rootPath = "";
$(function() {
	$("#tabs").tabs();

	loadDriverPosts();
	loadRidePosts();

	// handle comments for the posts
	$(document).on(
			'click',
			'.commentbtn',
			function() {
				postid = $(this).attr('id');
				$.get(rootPath + "postcomments/" + postid)
						.done(commentsSuccess).fail(ajaxfailure);
			});
	$(document).on('click', '.likeaction', function() {
		var postid = $(this).attr('id');
		var postidreal = parseInt(postid.substring(7));
		//post can be retrieved from the database
		var likeObj = {};
		likeObj.postId = postidreal;
		likeObj.userId = 20;
		$.post(rootPath+"likes",JSON.stringify(likeObj)).done(likesuccess).fail(ajaxfailure);
		
	});
	/*$(document).on('click', '.dislikeaction', function() {
		postid = $(this).attr('id');
		console.log(postid);
	});*/

});

function loadDriverPosts() {
	$.get(
			rootPath + "postperpage/5?posttype=\"DRIVE\"").done(perpagesuccessdrive).fail(
			ajaxfailure);
}
function loadRidePosts() {
	$.get(rootPath + "postperpage/5?posttype=\"RIDE\"").done(perpagesuccessride).fail(
			ajaxfailure);
}
function perpagesuccessdrive(data) {
	var posts = JSON.parse(data);
	console.log(posts);
	var wrapper = $('#tabs-2');
	$
			.each(
					posts,
					function(i, post) {
						wrapper.append("<div  id='postdiv" + post.postId + "'"
								+ " class = \"postsection\"></div>");
						var currentDiv = $('#postdiv' + post.postId);
						currentDiv.append("<p><strong>" + post.user.fullName
								+ "</strong>" + " " + post.postId + " "
								+ post.user.city + " " + post.postType
								+ "</p><p>" + post.postText + "</p>");
						currentDiv
								.append("<p> <em style = \"color: lightblue\"> Last updated"
										+ post.dateCreated + "</em></p>");

						var commentbtn = $('<input/>').attr({
							type : "button",
							id : post.postId,
							value : 'comments',
							'class' : 'commentbtn'
						});
						console.log("are we here");
						currentDiv.append(commentbtn);
						currentDiv.append("<div  id='commentdiv" + post.postId
								+ "'" + " class = \"commentsection\"></div>");
						currentDiv.append("<div  id='likediv" + post.postId
								+ "'" + " class = \"likesection\"></div>");
						currentDiv
								.append("<div  id='likecount"
										+ post.postId
										+ "'"
										+ " class = \"likecount\"> "
										+ "<input type = \"button\" class = \"likeaction\" id = 'likebtn"
										+ post.postId
										+ "' value = \"Like\" />"
										+ " </div>");
						wrapper.append(currentDiv);

						$.get(rootPath + "postlikes/" + post.postId).success(
								function(likes) {
									var likesJson = JSON.parse(likes);
									$('#likediv' + post.postId).append(
											"#Likes:" + likesJson.totallikes);
								}).error(function() {
							console.log(error);
						});
					});
}
function perpagesuccessride(data) {
	var posts = JSON.parse(data);
	console.log(posts);
	var wrapper = $('#tabs-1');
	$
			.each(
					posts,
					function(i, post) {
						wrapper.append("<div  id='postdiv" + post.postId + "'"
								+ " class = \"postsection\"></div>");
						var currentDiv = $('#postdiv' + post.postId);
						currentDiv.append("<p><strong>" + post.user.fullName
								+ " " + post.postId + " " + post.user.city
								+ " " + post.postType + "</strong>" + "</p><p>"
								+ post.postText + "</p>");
						currentDiv
								.append("<p> <em style = \"color: lightblue\"> Last updated"
										+ post.dateCreated + "</em></p>");

						var commentbtn = $('<input/>').attr({
							type : "button",
							id : post.postId,
							value : 'comments',
							'class' : 'commentbtn'
						});
						console.log("are we here");
						currentDiv.append(commentbtn);
						currentDiv.append("<div  id='commentdiv" + post.postId
								+ "'" + " class = \"commentsection\"></div>");
						currentDiv.append("<div  id='likediv" + post.postId
								+ "'" + " class = \"likesection\"></div>");
						currentDiv
								.append("<div  id='likecount"
										+ post.postId
										+ "'"
										+ " class = \"likecount\"> "
										+ "<input type = \"button\" class = \"likeaction\" id = 'likebtn"
										+ post.postId
										+ "' value = \"Like\" />"
										+  " </div>");
						wrapper.append(currentDiv);
						$.get(rootPath + "postlikes/" + post.postId).success(
								function(likes) {
									var likesJson = JSON.parse(likes);
									$('#likediv' + post.postId).append(
											"#Likes:" + likesJson.totallikes);
								}).error(function() {
							console.log(error);
						});
					});
}
function commentsSuccess(commentsResult) {
	var comments = JSON.parse(commentsResult);
	var divName = 'commentdiv' + postid;
	var content = "<fieldset><legend><strong>Post comments</strong></legend><ul>";
	$('#' + divName).empty(); // clear if comments button clicked previously
	$.each(comments, function(i, comment) {
		content = content + "<li>" + comment.comment + "</li>";
	});
	content = content + "</ul> </fieldset>";
	$('#' + divName).append(content);
}
function ajaxfailure(error) {
	console.log(error)
}
function likesuccess(data){
	
	var jsonData = JSON.parse(data);
	console.log(jsonData.post.postId);
	updateLikeCount(parseInt(jsonData.post.postId));
	console.log("successfully added a like")
}
function updateLikeCount(postid){
	$('#likediv' + postid).empty();
	$.get(rootPath + "postlikes/" + postid).success(
			function(likes) {
				var likesJson = JSON.parse(likes);
				$('#likediv' + postid).append(
						"#Likes:" + likesJson.totallikes);
			}).error(function() {
		console.log(error);
	});
}

var postid;

/*
 * $(window).scroll( function() { if ($(window).scrollTop() + $(window).height() >
 * $(document) .height() - 100) { numberofPage = numberofPage + 5;
 * $("#tabs-1").empty(); loaduserposts(); } });
 */