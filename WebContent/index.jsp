<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="carpool" uri='carpooling'%>
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
	<nav class="navbar"> <img src="resources/images/icon-carpool.png"
		style="width: 80px; height: 80px; margin-left: 100px; margin-bottom: 10px; margin-top: 10px;"
		alt="carpooling logo"> <input type="hidden" id="useridhidden"
		value="${userId}" />
	<ul class="nav navbar-nav" style="float: right; margin-right: 100px">
		<li lass="nav-item">
		
		<button type="button" id = "postnotifier" class="btn btn-default" aria-label="Left Align">
  <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
</button>
<!-- 
		<input type = "button" id = "postnotifier" /> -->
		<li lass="nav-item">
			<!-- <div id="registerdiv"
					style="margin-top: 15px"> --> <a
			href="http://localhost:8087/CarPoolingApp/weather.jsp"
			target="_blank" class="glyphicon glyphicon-cloud" id="weather"
			data-toggle="modal"> Check weather</a> <!-- </div> -->
		</li>
		<c:if test="${fullname != null}">
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
						<a class="dropdown-item" href="#">Hello <%=session.getAttribute("fullname")%></a>
					</div>
					<div class="editprofile">
						<a class="dropdown-item" href="#">Edit Profile</a>
					</div>
				</div></li>

		</c:if>

		<c:if test="${fullname == null}">

			<li lass="nav-item">
				<!-- <div id="registerdiv"
					style="margin-top: 15px"> --> <a href="#registerationModal"
				class="glyphicon glyphicon-pencil" id="registermodallink"
				data-toggle="modal"> Register</a> <!-- </div> -->
			</li>
			<li lass="nav-item"><a href="login.jsp"><span
					class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</c:if>
		<c:if test="${fullname != null}">
			<li lass="nav-item"><a href="login?logout = true"><span
					class="glyphicon glyphicon-log-out"></span> Login Out</a></li>
		</c:if>
	</ul>


	</nav>

	<!-- 		Catch session and other attributes
 -->
	<%
		String fullname = (String) session.getAttribute("fullname");
		request.setAttribute("fullname", fullname);
		Integer userId = (Integer) session.getAttribute("userId");
		request.setAttribute("userId", userId);
	%>



	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Need a ride?</a></li>
			<li><a href="#tabs-2">Provide a ride?</a></li>

			<!-- 				<li><a href="#tabs-3">Check weather before you plan</a></li>
 -->
		</ul>
		<div id="tabs-1">
			<!--  <a href = "newPost.jsp" class = "glyphicon glyphicon-plus" </a>-->
			<c:if test="${fullname != null}">
				<a id="addnewpostbutton" href="#myModal"
					class="glyphicon glyphicon-pencil" data-toggle="modal"> </a>
			</c:if>

		</div>
		<div id="tabs-2">
			<c:if test="${fullname != null}">
				<a id="addnewpostbutton" href="#myModal"
					class="glyphicon glyphicon-pencil" data-toggle="modal"> </a>
			</c:if>

		</div>

		<!-- 			<div id="tabs-3"></div>
 -->
	</div>

	</div>



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

								<label class="control-label col-sm-2" class="row">Service
									type:</label>
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

	<!-- Registration modal -->
	<div class="modal fade" id="registerationModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<label class="control-label col-sm-2" for="newpost"><strong>New
							user registration</strong></label>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">


					<div class="container">

						<form class="form-horizontal" action="logincontroller"
							method="POST">
							<div class="row">

								<div class="form-group">
									<label class="control-label col-sm-2" for="lastname">Full
										Name:</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="fullname"
											placeholder="Enter full name" required>
									</div>
								</div>

								<label class="control-label col-sm-2" class="row">Gender:</label>
								<div class="form-group">
									<div class="col-sm-2">
										<label class="radio-inline"> <input name="gender"
											id="input-gender-male" value="Male" type="radio" required />Male
										</label>
									</div>
									<div class="col-sm-2">
										<label class="radio-inline"> <input name="gender"
											id="input-gender-female" value="Female" type="radio" required />Female
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="state">State</label>
									<div class="col-sm-5">
										<select class="form-control" id="state" name="state" required>
											<option value="">N/A</option>
											<option value="AK">Alaska</option>
											<option value="AL">Alabama</option>
											<option value="AR">Arkansas</option>
											<option value="AZ">Arizona</option>
											<option value="CA">California</option>
											<option value="CO">Colorado</option>
											<option value="CT">Connecticut</option>
											<option value="DC">District of Columbia</option>
											<option value="DE">Delaware</option>
											<option value="FL">Florida</option>
											<option value="GA">Georgia</option>
											<option value="HI">Hawaii</option>
											<option value="IA">Iowa</option>
											<option value="ID">Idaho</option>
											<option value="IL">Illinois</option>
											<option value="IN">Indiana</option>
											<option value="KS">Kansas</option>
											<option value="KY">Kentucky</option>
											<option value="LA">Louisiana</option>
											<option value="MA">Massachusetts</option>
											<option value="MD">Maryland</option>
											<option value="ME">Maine</option>
											<option value="MI">Michigan</option>
											<option value="MN">Minnesota</option>
											<option value="MO">Missouri</option>
											<option value="MS">Mississippi</option>
											<option value="MT">Montana</option>
											<option value="NC">North Carolina</option>
											<option value="ND">North Dakota</option>
											<option value="NE">Nebraska</option>
											<option value="NH">New Hampshire</option>
											<option value="NJ">New Jersey</option>
											<option value="NM">New Mexico</option>
											<option value="NV">Nevada</option>
											<option value="NY">New York</option>
											<option value="OH">Ohio</option>
											<option value="OK">Oklahoma</option>
											<option value="OR">Oregon</option>
											<option value="PA">Pennsylvania</option>
											<option value="PR">Puerto Rico</option>
											<option value="RI">Rhode Island</option>
											<option value="SC">South Carolina</option>
											<option value="SD">South Dakota</option>
											<option value="TN">Tennessee</option>
											<option value="TX">Texas</option>
											<option value="UT">Utah</option>
											<option value="VA">Virginia</option>
											<option value="VT">Vermont</option>
											<option value="WA">Washington</option>
											<option value="WI">Wisconsin</option>
											<option value="WV">West Virginia</option>
											<option value="WY">Wyoming</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="city">City:</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="city"
											placeholder="Enter city" required>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="street">Street:</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="street"
											placeholder="Enter street" required>
									</div>
								</div>


								<div class="form-group">
									<label class="control-label col-sm-2" for="zip">Zip
										Code:</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="zip"
											placeholder="Enter zip code" required>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="dob">Birth
										Date:</label>
									<div class="col-sm-5">
										<input type="date" class="form-control" id="dob"
											placeholder="Enter your birth date" required>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="email">Email:</label>
									<div class="col-sm-5">
										<input type="email" class="form-control" id="email" required>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-sm-2" for="pwd">Password:</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="pwd" required>
										<!-- pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
												title="password should be at least 6 letters and have at least one capital letter, one small letter,
one number" -->

									</div>
								</div>

								<div class="col-sm-5 text-center">
									<input type="button" id="btnsaveuser2" value="Register" />
								</div>
							</div>
						</form>
					</div>

				</div>
				<!--<div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>-->
			</div>
		</div>
	</div>

	<div class="container">
		<div class="page-header">
			<c:if test="${fullname == null}">
        Be a member <a href="#registerationModal"
					class="glyphicon glyphicon-pencil" id="registermodallink"
					data-toggle="modal"> Register</a>
			</c:if>
		</div>
		<p class="lead">
			Carpool your trip ... cheap price, comfortable drive and ride. |
			<carpool:currentDateTime color='lightblue' size='20' />
		</p>
	</div>


</body>
</html>