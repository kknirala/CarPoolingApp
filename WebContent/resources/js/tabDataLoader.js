"use strict";

var numberofpageForRide = 10;
var numberofpageForDrive = 10;
var rootPath = "";
var postid;
$(function() {
	$("#tabs").tabs();

	// could be optimized better
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
	$(document).on(
			'click',
			'.likeaction',
			function() {
				var postid = $(this).attr('id');
				var postidreal = parseInt(postid.substring(7));
				// post can be retrieved from the database
				var likeObj = {};
				likeObj.postId = postidreal;
				likeObj.userId = 20;
				$.post(rootPath + "likes", JSON.stringify(likeObj)).done(
						likesuccess).fail(ajaxfailure);

			});

	// handles delete post operation
	$(document).on('click', '.deletebtn', function() {
		var postid = $(this).attr('id');
		var postidreal = parseInt(postid.substring(7));
		var likeObjToDelete = {};
		likeObjToDelete.postId = postidreal;
		// make ajax request
		if (confirm("Are you sure?")) {
			$.ajax({
				url : rootPath + "posts/" + postidreal,
				type : 'DELETE',
				success : postdeletesuccess,
				error : ajaxfailure
			});

			$('#postdiv' + postidreal).remove();
		}
	});

	// add new post handler

	$(document).on(
			'click',
			'#btnnewpost',
			function() {
				var postObj = {};
				var postType = $("input[name=carpoolType]:checked").val();
				var comment = $('#comment').val();
				var userId = 10;
				postObj.userId = 10;
				postObj.comment = comment;
				postObj.postType = postType;
				if (confirm("Are you sure?")) {
				$.post(rootPath + "posts", JSON.stringify(postObj)).done(
						postsuccess).fail(ajaxfailure);
				}
				$('#myModal').modal('hide');
			});

	// handle adding new comment
	$(document).on(
			'click',
			'.commentbuttonsection',
			function() {
				var postid = $(this).attr('id');
				var postidreal = parseInt(postid.substring(17));
				var commentObj = {};
				var userId = 10;
				var postId = postidreal;
				var comment = $('#commenttext-' + postidreal).val();
				commentObj.userId = userId;
				commentObj.postId = postId;
				commentObj.comment = comment;
				$.post(rootPath + "comments", JSON.stringify(commentObj)).done(
						commentsuccess).fail(ajaxfailure);
				$('#newcommentDiv' + postidreal).empty(); // clear
				$('#newcommentDiv' + postidreal).hide(); // hide
				// make the post appear in the comments box
				/* $('#commentdiv'+postidreal).append(comment); */
			});

	// handle when a new comment button is clicked
	$(document).on('click', '.newcomment', function() {
		var postid = $(this).attr('id');
		var postidreal = parseInt(postid.substring(11));
		// show the comment box down
		$('#newcommentDiv' + postidreal).show();

	});

	/*
	 * $(document).on('click', '.dislikeaction', function() { postid =
	 * $(this).attr('id'); console.log(postid); });
	 */
	/*
	 * $(document).on('load', '.newcommentsection', function(){
	 * $('.newcommentsection').hide(); });
	 */

});

function loadDriverPosts() {
	$.get(rootPath + "postperpage/5?posttype=\"DRIVE\"").done(
			perpagesuccessdrive).fail(ajaxfailure);
}
function loadRidePosts() {
	$.get(rootPath + "postperpage/5?posttype=\"RIDE\"")
			.done(perpagesuccessride).fail(ajaxfailure);
}
function perpagesuccessdrive(data) {
	var posts = JSON.parse(data);
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
						var deletebtn = $('<input/>').attr({
							type : "button",
							id : "delete-" + post.postId,
							value : 'delete',
							'class' : 'deletebtn'
						});
						var deletebtn = $('<input/>').attr({
							type : "button",
							id : "delete-" + post.postId,
							value : 'delete',
							'class' : 'deletebtn'
						});
						var newcommentbtn = $('<input/>').attr({
							type : "button",
							id : "newcomment-" + post.postId,
							value : 'New comment',
							'class' : 'newcomment'
						});

						// make the above button a link for the modal to work

						var commentBox = "<div  id='newcommentDiv"
								+ post.postId
								+ "'"
								+ " class = \"newcommentsection\">"
								+ ""
								+ "<textarea rows = 3  id = 'commenttext-"
								+ post.postId
								+ "'"
								+ " class = \"commenttextsection\"></textarea><br>"
								+ "<input type = 'button' value = \"Add comment\" id = 'newcommentbutton-"
								+ post.postId + "'"
								+ " class = \"commentbuttonsection\"/>"
								+ "</div>";
						currentDiv.append(commentbtn);
						currentDiv.append(deletebtn);
						currentDiv.append(newcommentbtn);
						currentDiv.append(commentBox);
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
										+ post.postId + "' value = \"Like\" />"
										+ " </div>");
						currentDiv.append("<hr>");
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
	$('.newcommentsection').hide(); // hide after all is loaded
}
function perpagesuccessride(data) {
	var posts = JSON.parse(data);
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
						var deletebtn = $('<input/>').attr({
							type : "button",
							id : "delete-" + post.postId,
							value : 'delete',
							'class' : 'deletebtn'
						});

						var newcommentbtn = $('<input/>').attr({
							type : "button",
							id : "newcomment-" + post.postId,
							value : 'New comment',
							'class' : 'newcomment'
						});

						// make the above button a link for the modal to work

						var commentBox = "<div  id='newcommentDiv"
								+ post.postId
								+ "'"
								+ " class = \"newcommentsection\">"
								+ ""
								+ "<textarea rows = 3  id = 'commenttext-"
								+ post.postId
								+ "'"
								+ " class = \"commenttextsection\"></textarea><br>"
								+ "<input type = 'button' value = \"Add comment\" id = 'newcommentbutton-"
								+ post.postId + "'"
								+ " class = \"commentbuttonsection\"/>"
								+ "</div>";

						currentDiv.append(commentbtn);
						currentDiv.append(deletebtn);
						currentDiv.append(newcommentbtn);
						currentDiv.append(commentBox);
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
										+ post.postId + "' value = \"Like\" />"
										+ " </div>");
						currentDiv.append("<hr>");
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
	$('.newcommentsection').hide(); // hide after all is loaded
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
function likesuccess(data) {

	var jsonData = JSON.parse(data);
	updateLikeCount(parseInt(jsonData.post.postId));
	console.log("successfully added a like")
}
function updateLikeCount(postid) {
	$('#likediv' + postid).empty();
	$.get(rootPath + "postlikes/" + postid).success(function(likes) {
		var likesJson = JSON.parse(likes);
		$('#likediv' + postid).append("#Likes:" + likesJson.totallikes);
	}).error(function() {
		console.log(error);
	});
}
function postdeletesuccess() {
	// remove the element

	console.log("post deleted successfully");
}
function postsuccess(data) {
	console.log("post added successfully");
}
function commentsuccess() {
	console.log("comment added successfully");
	// hide the box after we successfulyl add the comment

}



/* $(window).scroll( function() { 
	 if ($(window).scrollTop() + $(window).height() >
 $(document) .height() - 100) { numberofpageForRide = numberofpageForRide + 10;
 $("#tabs-1").empty(); loadRidePosts(); 
 } });*/
 $(window).scroll(function() {
	    if($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	/*$("#tabs-1").empty();*/ loadRidePosts(); 
	    }
	});
 