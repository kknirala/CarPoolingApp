<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Weather layer</title>
<!-- <script src="//maps.googleapis.com/maps/api/js" type="text/javascript"></script> -->
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/weather.js">
</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATNEob3zhjDxeV-IksK-qsnLs7zr9hpKQ&callback=initMap"
  type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/weather.css">
</script>


<body>
	<%
		String fullname = (String) session.getAttribute("fullname");
		request.setAttribute("fullname", fullname);
		Integer userId = (Integer) session.getAttribute("userId");
		request.setAttribute("userId", userId);
		String city = (String) session.getAttribute("city");
		request.setAttribute("city", city);
	%>
	<input type="hidden" id="useridhidden" value="${city}" />
	<div id="heada">
		<form>
			<fieldset>
				<legend>
					<label>
					<strong>Source Address</strong></label>
				</legend>
				<br>
				<label>
				<strong>City &nbsp &nbsp &nbsp &nbsp</strong></label>
				<input type="text" id="txtCity" size="30"><br>
				<br>
				<label>
				<strong>Country </strong></label>
				<input type="text" "text" id="txtCountry" size="30"><br>
				<br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input
					type="button" id='btnSearch' value="search">
			</fieldset>
		</form>
	</div>
	<div id="headb">
		<form>
			<fieldset>
				<legend>
					<label><strong>Destination Address</strong></label>
				</legend>
				<br>
				<label>
				<strong>City &nbsp &nbsp &nbsp &nbsp</strong></label>
				<input type="text" id="txtCityDest" size="30"><br>
				<br>
				<label>
				<strong>Country </strong></label>
				<input type="text" id="txtCountryDest" size="30"><br>
				<br> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input
					type="button" id='btnSearchDest' value="search"> &nbsp
				&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input type="button"
					id='btnRoute' value="Route">
			</fieldset>
		</form>
	</div>
	<div id="left">


		<div id="resultDivRight">
			<ul class="tab">
				<li><a href="javascript:void(0)" class="tablinks"
					onclick="openCity(event, 'city')" id="defaultOpen">Today</a></li>
				<li><a href="javascript:void(0)" class="tablinks"
					onclick="openCity(event, 'daily')">Daily</a></li>
				<li><a href="javascript:void(0)" class="tablinks"
					onclick="openCity(event, 'hourly')">Hourly</a></li>

			</ul>

			<div id="city" class="tabcontent">
				<span onclick="this.parentElement.style.display='none'"
					class="topright">x</span>

				<p>No Today Data</p>
			</div>

			<div id="daily" class="tabcontent">
				<span onclick="this.parentElement.style.display='none'"
					class="topright">x</span>

				<p>No Daily Data</p>

			</div>

			<div id="hourly" class="tabcontent">
				<span onclick="this.parentElement.style.display='none'"
					class="topright">x</span>
				<p>No Hourly Data</p>
			</div>
		</div>

	</div>
	<div style="height: 100%; width: 100%;">
		<div id="map-canvas"></div>
	</div>
</body>
</html>