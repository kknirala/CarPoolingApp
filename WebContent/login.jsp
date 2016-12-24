<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to access more app features</title>
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
<link href="resources/css/logins.css" type="text/css" rel="stylesheet" />
<!-- <script src="resources/js/tabDataLoader.js" type="text/javascript"></script>
 --></head>
<body>
	<div class="container">
		<div class="headerLogo">
			<a href = "index.jsp" > <img src="resources/images/letsCarpoolbanner.png" class="headbanner" alt="carpool banner"></a>
		</div>
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">Sign in to continue</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="resources/images/account.png"
						alt="">
					<form class="form-signin" action = "login" method = "post">
						<input type="email" class="form-control" name = "username" placeholder="Email"
							required autofocus></br> <input type="password"
							class="form-control" name = "password" placeholder="Password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
												title="password should be at least 6 letters and have at least one capital letter, one small letter,
one number">
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Sign in</button>
						<div class="col-sm-offset-1">
							<label class="checkbox pull-left"> <input type="checkbox"
								value="remember-me" name = "rememberme"> Remember me
							</label>
						</div>
						<a href="#" class="pull-right need-help">Need help? </a><span
							class="clearfix"></span>
					</form>
				</div>
				<div><a href="#registerationModal" class="text-center new-account" data-toggle="modal">Not Registered?
					Register here</a>
				</div>
				 </br>
				</br>
			</div>
			</br>
			</br>

		</div>
	</div>

    <div class="modal fade" id="registerationModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<label class="control-label col-sm-2" for="newpost"><strong>New user registration</strong></label>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">


					<div class="container">

						<form class="form-horizontal" action="logincontroller"
							method="POST">
							<div class="row">
							
									<div class="form-group">
										<label class="control-label col-sm-2" for="lastname">Full Name:</label>
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
												id="input-gender-female" value="Female" type="radio"
												required />Female
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
									<div id = "agelimitdiv" style = "color: red; margin-left: 250px"></div>
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
											<input type="password" class="form-control" id="pwd" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
												title="password should be at least 6 letters and have at least one capital letter, one small letter,
one number" >
												
												
										</div>
									</div>

								<div class="col-sm-5 text-center">
									<input type="button" id="btnsaveuser2" value = "Register"/>
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
</body>
</html>