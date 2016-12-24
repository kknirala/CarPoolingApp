"use strict";

var numberofpageForRide = 10;
var numberofpageForDrive = 10;
var rootPath = "";
var postid;
var userId;
var maxDrivePostIdRequested = 0;
var maxRidePostIdRequested = 0;
var availablenumberofposts;
$(function() {
	$("#tabs").tabs();
	//initialize user id
	userId = $('#useridhidden').val();
    $('#loadingcomponent').hide();
	// could be optimized better
	loadDriverPosts(maxDrivePostIdRequested);
	loadRidePosts(maxRidePostIdRequested);

	//hidden alert
	$('#postnotifier').hide();
	
	//initialize variables
	$.get(rootPath + "postcount").success(function(data){
		var dataObj = JSON.parse(data);
		availablenumberofposts = dataObj.numberofposts;
		console.log("When we start, posts number is: "+availablenumberofposts);
		
	}).error(function(){
		console.log("Error finding number of posts");
	})
	// handle comments for the posts
	$(document).on(
			'click',
			'.commentbtn',
			function() {
				postid = $(this).attr('id');
				$.get(rootPath + "postcomments/" + postid)
						.done(commentsSuccess).fail(ajaxfailure);
			});
	//handle liking action
	$(document).on(
			'click',
			'.likeaction',
			function() {
				var postid = $(this).attr('id');
				var postidreal = parseInt(postid.substring(7));
				var spanToInclude;
				var btnElement = $("#likebtn"+postidreal+ " span");
				if($(btnElement).hasClass("glyphicon-thumbs-up")){
					console.log("Has a thumbs up");
					spanToInclude = "<span class=\"glyphicon likecustomicon glyphicon-thumbs-down\"></span>";
				} else{
					console.log("Has a thumbs down");
					spanToInclude = "<span class=\"glyphicon likecustomicon glyphicon-thumbs-up\"></span>"
				}
				// post can be retrieved from the database
				//disable
				$('#likebtn'+postidreal+' .likecustomicon').remove();
				$('#likebtn'+postidreal).prepend(spanToInclude);
				var likeObj = {};
				likeObj.postId = postidreal;
				likeObj.userId = userId;
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
				postObj.userId = userId;
				postObj.comment = comment;
				postObj.postType = postType;
				if (confirm("Are you sure?")) {
					$.post(rootPath + "posts", JSON.stringify(postObj)).done(
							postsuccess).fail(ajaxfailure);
				}
				$('#myModal').modal('hide');
			});
	// handle new user registration

	$(document).on('click', '#btnsaveuser2', function() {
		// get values from the form
		var fullName = $('#fullname').val();
		var gender = $('input:radio[name=gender]:checked').val();
		var state = $("#state option:selected").text();
		var city = $('#city').val();
		var street = $('#street').val();
		var zip = $('#zip').val();
		var birthYear = $('input[type="date"]').val();
		var email = $('#email').val();
		var pwd = $('#pwd').val();

		// create object and add values

		var userObj = {};
		userObj.fullName = fullName;
		userObj.gender = gender;
		userObj.state = state;
		userObj.city = city;
		userObj.street = street;
		userObj.zipCode = zip;
		userObj.email = email;
		userObj.password = pwd;
		console.log(street);
		//dialogue the user if intends to register
		if (confirm("Are you sure?")) {
			$.post(rootPath + "users", JSON.stringify(userObj)).done(
					usersuccess).fail(ajaxfailure);
		}
		$('#registerationModal').modal('hide');
		window.location.replace("index.jsp");
	});

	// handle adding new comment
	$(document).on(
			'click',
			'.commentbuttonsection',
			function() {
				var postid = $(this).attr('id');
				var postidreal = parseInt(postid.substring(17));
				var commentObj = {};
/*				var userId = 10;
*/				var postId = postidreal;
				var comment = $('#commenttext-' + postidreal).val();
				commentObj.userId = userId;
				commentObj.postId = postId;
				commentObj.comment = comment;
				$.post(rootPath + "comments", JSON.stringify(commentObj)).done(
						commentsuccess).fail(ajaxfailure);
				$('#newcommentDiv' + postidreal).empty(); // clear
				//$('#newcommentDiv' + postidreal).hide(); // hide
				// make the post appear in the comments box
				/* $('#commentdiv'+postidreal).append(comment); */
			});

	// handle when a new comment button is clicked
	$(document).on('click', '.newcomment', function() {
		var postid = $(this).attr('id');
		var postidreal = parseInt(postid.substring(11));
		// show the comment box down
		$('#newcommentDiv' + postidreal).show();
/*		$('#newcommentbutton-'+postidreal).prepend("<span class=\"glyphicon glyphicon-save\"></span>");
*/	});
	
	//handle notification of a new post
	setInterval(checkfornewpost, 130000);
	
	//reload page when new request comes
	$(document).on('click', '#postnotifier', function(){
		$('#postnotifier').hide();
		//reload posts
		loadDriverPosts();
		loadRidePosts();
	})
	
	//age validation section
	
	var agealert;
	
/*	$("#agelimitdiv").datepicker();
*/	$('#dob')
			.on(
					'change',
					function() {

						var agelimit = 18;
						var enteredValue = $(this).val();
						var enteredAge = getAge(enteredValue);
						if (enteredAge < agelimit) {
							// enteredValue.focus();
							$('#agelimitdiv').append("<p> <em style = \"color: red\"> Your age must be above 18 years"+
									 "</em></p>");
							
							agealert.appendTo('#agelimitdiv');
							return false;
						}
					});
	
});

function loadDriverPosts(maxDrivePostIdRequested) {
	$('#loadingcomponent').show();
	$.get(rootPath + "postperpage/"+ maxDrivePostIdRequested +"?posttype=DRIVE").done(
			perpagesuccessdrive).fail(ajaxfailure).always(hideAjaxLoader);
}
function loadRidePosts(maxRidePostIdRequested) {
	$('#loadingcomponent').show();
	$.get(rootPath +"postperpage/"+ maxRidePostIdRequested +"?posttype=RIDE")
			.done(perpagesuccessride).fail(ajaxfailure).always(hideAjaxLoader);
}
function perpagesuccessdrive(data) {
	var posts = JSON.parse(data);
	var wrapper = $('#tabs-2');
	var postssize = posts.length;
	let drivepostid;
	//if no posts returned upon scrolling, do nothing
	if(postssize <= 0){
		return;
	}
	$
			.each(
					posts,
					function(i, post) {
						drivepostid = post.postId;
						wrapper.append("<div  id='postdiv" + drivepostid + "'"
								+ " class = \"postsection\"></div>");
						var currentDiv = $('#postdiv' + drivepostid);
						currentDiv.append("<p><strong> Drive with " + post.user.fullName
								+ "</strong>"
								+ "</p><p>" + post.postText + "</p>");
						currentDiv
								.append("<p> <em style = \"color: lightblue\"> Last updated"
										+ post.dateCreated + "</em></p>");

						var commentbtn = $('<button/>').attr({
							type : "button",
							id : drivepostid,
							value : 'commentsdd',
							'class' : 'btn btn-default commentbtn',
							'aria-label':'Left Align'
						});
						commentbtn.prepend("<span class=\"glyphicon glyphicon-comment\"></span>");
						var deletebtn = $('<input/>').attr({
							type : "button",
							id : "delete-" + drivepostid,
							value : 'delete',
							'class' : 'deletebtn'
						});
						var deletebtn = $('<button/>').attr({
							type : "button",
							id : "delete-" + drivepostid,
							value : 'delete',
							'class' : 'btn btn-default deletebtn',
							'aria-label':'Left Align'
						});
						deletebtn.prepend("<span class=\"glyphicon glyphicon-remove\"></span>");
						var newcommentbtn = $('<button/>').attr({
							type : "button",
							id : "newcomment-" + drivepostid,
							value : 'New comment',
							'class' : 'btn btn-default newcomment',
							'aria-label':'Left Align'
						});
						newcommentbtn.prepend("<span class=\"glyphicon glyphicon-plus\"></span>");

						// make the above button a link for the modal to work
						var commentBoxHolder = "<"
						var commentBox = "<div  id='newcommentDiv"
								+ drivepostid
								+ "'"
								+ " class = \"newcommentsection\">"
								+ ""
								+ "<textarea rows = 3  id = 'commenttext-"
								+ drivepostid
								+ "'"
								+ " class = \"commenttextsection\"></textarea><br>"
								+ "<button value = \"Add comment\" aria-label=\"Left Align\" id = 'newcommentbutton-"
								+ drivepostid + "'"
								+ " class = \"commentbuttonsection btn btn-default\">"
								+ "<span class=\"likecustomicon glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>"
								+"</button>"
								+ "</div>";
						currentDiv.append(commentbtn);
						currentDiv.append(deletebtn);
						currentDiv.append(newcommentbtn);
						currentDiv.append(commentBox);
						currentDiv.append("<div  id='commentdiv" + drivepostid
								+ "'" + " class = \"commentsection\"></div>");
						currentDiv.append("<div  id='likediv" + drivepostid
								+ "'" + " class = \"likesection\"></div>");
						currentDiv
								.append("<div  id='likecount"
										+ drivepostid
										+ "'"
										+ " class = \"likecount\"> "
										+ "<button type = \"button\" class = \"likeaction btn btn-default\" aria-label=\"Left Align\"id = 'likebtn"
										+ drivepostid + "' value = \"Like\" >"
										+ "<span class=\"glyphicon likecustomicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span>"
										+"</button>"
										+ " </div>");
						currentDiv.append("<hr>");
						wrapper.append(currentDiv);
						$.get(rootPath + "postlikes/" + drivepostid).success(
								function(likes) {
									var likesJson = JSON.parse(likes);
									$('#likediv' + drivepostid).append(
											likesJson.totallikes);
								}).error(function(error) {
							console.log(error);
						});
						maxDrivePostIdRequested = drivepostid;
					});
	
	$('.newcommentsection').hide(); // hide after all is loaded
	if(userId.length == 0){
		$('.deletebtn').remove();
		$('.newcomment').remove();
		$('.likeaction').remove();
		$('.btn-primary').remove();
		$('#myModal').remove();
	}
}
function perpagesuccessride(data) {
	var posts = JSON.parse(data);
	var wrapper = $('#tabs-1');
	let ridepostid;
	var postsSize = posts.length;
	//if no posts returned upon scrolling, do nothing
	if(postsSize <= 0){
		console.log("Posts size is: "+ postsSize);
		console.log("maxRidePostIdRequested is: "+ maxRidePostIdRequested);
		return;
	}
	$
			.each(
					posts,
					function(i, post) {
						ridepostid = post.postId;
						wrapper.append("<div  id='postdiv" + ridepostid + "'"
								+ " class = \"postsection\"></div>");
						var currentDiv = $('#postdiv' + ridepostid);
						currentDiv.append("<p><strong> Ride needed by  " + post.user.fullName
								+  "</strong>" + "</p><p>"
								+ post.postText + "</p>");
						currentDiv
								.append("<p> <em style = \"color: lightblue\"> Last updated"
										+ post.dateCreated + "</em></p>");

						var commentbtn = $('<button/>').attr({
							type : "button",
							id : ridepostid,
							value : 'commentsdd',
							'class' : 'btn btn-default commentbtn',
							'aria-label':'Left Align'
						});
						commentbtn.prepend("<span class=\"glyphicon glyphicon-comment\"></span>");
						var deletebtn = $('<button/>').attr({
							type : "button",
							id : "delete-" + ridepostid,
							value : 'delete',
							'class' : 'btn btn-default deletebtn',
							'aria-label':'Left Align'
						});
						deletebtn.prepend("<span class=\"glyphicon glyphicon-remove\"></span>");
						var newcommentbtn = $('<button/>').attr({
							type : "button",
							id : "newcomment-" + ridepostid,
							value : 'New comment',
							'class' : 'btn btn-default newcomment',
							'aria-label':'Left Align'
						});
						newcommentbtn.prepend("<span class=\"glyphicon glyphicon-plus\"></span>");

						// make the above button a link for the modal to work

						var commentBox = "<div  id='newcommentDiv"
								+ ridepostid
								+ "'"
								+ " class = \"newcommentsection\">"
								+ ""
								+ "<textarea rows = 3  id = 'commenttext-"
								+ ridepostid
								+ "'"
								+ " class = \"commenttextsection\"></textarea><br>"
								+ "<button value = \"Add comment\" aria-label=\"Left Align\" id = 'newcommentbutton-"
								+ ridepostid + "'"
								+ " class = \"commentbuttonsection btn btn-default\">"
								+ "<span class=\"likecustomicon glyphicon glyphicon-ok\" aria-hidden=\"true\"></span>"
								+"</button>"
								+ "</div>";

						currentDiv.append(commentbtn);
						currentDiv.append(deletebtn);
						currentDiv.append(newcommentbtn);
						currentDiv.append(commentBox);
						currentDiv.append("<div  id='commentdiv" + ridepostid
								+ "'" + " class = \"commentsection\"></div>");
						currentDiv.append("<div  id='likediv" + ridepostid
								+ "'" + " class = \"likesection\"></div>");
						currentDiv
								.append("<div  id='likecount"
										+ ridepostid
										+ "'"
										+ " class = \"likecount\"> "
										+ "<button type = \"button\" class = \"likeaction btn btn-default\" aria-label=\"Left Align\"id = 'likebtn"
										+ ridepostid + "' value = \"Like\" >"
										+ "<span class=\"glyphicon likecustomicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span>"
										+"</button>"
										
										+ " </div>");
						currentDiv.append("<hr>");
						wrapper.append(currentDiv);
						$.get(rootPath + "postlikes/" + ridepostid).success(
								function(likes) {
									var likesJson = JSON.parse(likes);
									$('#likediv' + ridepostid).append(
											likesJson.totallikes);
								}).error(function() {
							console.log(error);
						});
						maxRidePostIdRequested = ridepostid;
					});
	
	$('.newcommentsection').hide(); // hide after all is loaded

	//authenticate some components, could be optimized better
	if(userId.length == 0){
		$('.deletebtn').remove();
		$('.newcomment').remove();
		$('.likeaction').remove();
		$('.btn-primary').remove();
		$('#myModal').remove();
	}
	//	
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
		$('#likediv' + postid).append(likesJson.totallikes);
	}).error(function() {
		console.log(error);
	});
}
function postdeletesuccess() {
	console.log("post deleted successfully");
}
function postsuccess(data) {
	console.log("post added successfully");
}
function commentsuccess() {
	console.log("comment added successfully");
}
function usersuccess(){
	console.log("user added successfully");
	window.opener.location = "index.jsp";
	window.close();
}
function checkfornewpost(){
	//send api call
	$.get(rootPath + "postcount").success(function(data){
		var dataObj = JSON.parse(data);
		var receivedPosts = dataObj.numberofposts;
		if(availablenumberofposts < receivedPosts){
			console.log("We have got a new post");
			$('#postnotifier').show();
			$('#postnotifier').val("You have new post!");
			availablenumberofposts = receivedPosts;
		}
		
	}).error(function(){
		console.log("Error finding number of posts");
	})
	
}
//show ajax loader
function hideAjaxLoader(){
	$('#loadingcomponent').delay(2000).hide();
}

$(window).scroll(function() {
	if ($(window).scrollTop() == $(document).height() - $(window).height()) {
		//retrieve 
		loadDriverPosts(maxDrivePostIdRequested);
		loadRidePosts(maxRidePostIdRequested);
	}
});
//validating age
function getAge(DOB) {
	var today = new Date();
	var birthDate = new Date(DOB);
	var age = today.getFullYear() - birthDate.getFullYear();
	var m = today.getMonth() - birthDate.getMonth();
	if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
		age--;
	}
	return age;
}
