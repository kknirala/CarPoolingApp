<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Carpooling App</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<link rel="stylesheet" href="resources/css/main.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="resources/js/tabDataLoader.js" type="text/javascript"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Car Polling System</a>
		</div>
		<ul class="nav navbar-nav navbar-right">

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false"> <span
					class="glyphicon glyphicon-user"></span> Profile <span
					class="caret"></span></a>
				<div class="dropdown-menu">
					<div class="viewprofile">
						<a class="dropdown-item" href="#">View Profile</a>
					</div>
					<div class="editprofile">
						<a class="dropdown-item" href="#">Edit Profile</a>
					</div>
				</div></li>
			<li><a href="#"> Register</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Login</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
					Login Out</a></li>
		</ul>
		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Need a ride?</a></li>
				<li><a href="#tabs-2">Provide a ride?</a></li>
				<li><a href="#tabs-3">Check weather before you plan</a></li>

			</ul>
			<div id="tabs-1">
				<!--  <a href = "newPost.jsp" class = "glyphicon glyphicon-plus" </a>-->
				<a href="#myModal" class="btn btn-lg btn-primary"
					data-toggle="modal"> Add new Post</a>

			</div>
			<div id="tabs-2"></div>
			<div id="tabs-3">
				<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse
					potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque
					rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante.
					Class aptent taciti sociosqu ad litora torquent per conubia nostra,
					per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim
					commodo pellentesque. Praesent eu risus hendrerit ligula tempus
					pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a,
					lacus.</p>
				<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at,
					semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra
					justo vitae neque. Praesent blandit adipiscing velit. Suspendisse
					potenti. Donec mattis, pede vel pharetra blandit, magna ligula
					faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque.
					Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi
					lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean
					vehicula velit eu tellus interdum rutrum. Maecenas commodo.
					Pellentesque nec elit. Fusce in lacus.</p>
			</div>
		</div>

	</div>
	</nav>


	<!--New post Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<label class="control-label col-sm-2" for="newpost"><strong>New
							Post </strong></label>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">


					<div class="container">

						<form class="form-horizontal" action="logincontroller"
							method="POST">
							<div class="row">

								<label class="control-label col-sm-2" class="row">Service type:</label>
								<div class="form-group">
									<div class="col-sm-2">
										<label class="radio-inline"> <input name="carpoolType"
											id="input-gender-male" value="DRIVE" type="radio" required />I
											am driving
										</label>
									</div>
									<div class="col-sm-2">
										<label class="radio-inline"> <input name="carpoolType"
											id="input-gender-female" value="RIDE" type="radio" required />I
											need a ride
										</label>


									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="comment">Description:</label>
									<div class="col-sm-5">
										<textarea class="form-control" rows="5" id="comment"></textarea>
									</div>
								</div>
								<div class="col-sm-5 text-center">
									<!--                                     <button type="submit" id="button" class="btn btn-default">Submit</button> -->
									<input type="button" id="btnnewpost" value="add post" />
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	
	</div>


</body>
</html>