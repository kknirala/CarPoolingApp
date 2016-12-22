<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Weather layer</title>
  <style>
    html, body{
      width: 100%;
      height: 100%;
      margin: 10pt;
      padding: 10pt;
      background-image: url("resources/images/Bodybackground.gif");
      
    }
    
   
    #left{
     width: 41%;
     float: left;
    
    }
    #headb{

    float:left;
    height: 110pt;
    width: 50%;
    text-align: left;
    background-color: #f1f1f1;
    
    }
    #heada{
    float:left;
    height: 110pt;
    width: 40%;
    text-align: left;
    background-color: #f1f1f1;
    
    }
    #main{
       border: 2px solid;
      border-radius: 10px;
       width:480px;
       margin: 3px;
       color:white;
       background-image: url("resources/images/main.png");
    }
  
    #right{
     width: 25%;
     float: right;
    }
    #map-canvas {
        width:49%;
        height: 60%;

     }
    .gm-style-iw {
      text-align: center;
    }
  
   #resultDivRight{
     height: 290pt;
      overflow: scroll;
      width: 100%;
      background-color:	gray;
      
   }
    table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 50%;
    }

    td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
    }

    tr:nth-child(even) {
    background-color: #dddddd;
    }
</style>
<!-- <script src="//maps.googleapis.com/maps/api/js" type="text/javascript"></script> -->
 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATNEob3zhjDxeV-IksK-qsnLs7zr9hpKQ&callback=initMap"
  type="text/javascript"></script>
<script type="text/javascript" src="resources/js/tab.js">
</script>
<link rel="stylesheet" href="resources/css/tab.css">
<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<script>
  var map;
  var geoJSON;
  var request;
  var gettingData = false;
  var cityname = $('#useridhidden').val();
  var openWeatherMapKey = "313a7def9e41af1d274649b88401f02a"
  function initialize() {
    var mapOptions = {
      zoom: 12,
      center: new google.maps.LatLng(41.00688, -91.967137)
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    
    google.maps.event.addListener(map, 'idle', checkIfDataRequested);
 
    map.data.addListener('click', function (event) {
      infowindow.setContent(
        "<img src=" + event.feature.getProperty("icon") + ">"
        + "<br /><strong>" + event.feature.getProperty("city") + "</strong>"
        + "<br />" + event.feature.getProperty("temperature") + "&deg;C"
        + "<br />" + event.feature.getProperty("weather")
      );
      infowindow.setOptions({
        position: {
          lat: event.latLng.lat(),
          lng: event.latLng.lng()
        },
        pixelOffset: {
          width: 0,
          height: -15
        }
      });
      infowindow.open(map);
    });
  }

  $(document).ready(function () {
	  $('#txtCity').val("yisemal");
	  var m = $('#useridhidden').val();
	  console.log("m is: "+ m)
	  $('#txtCity').val(m);
    $('#btnSearch').click(function () {
        $('#city').empty();
      var requestData = $('#txtCity').val() + ',' + $('#txtCountry').val();
      var result = $('#city');
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather',
        type: 'get',
        data: { zip: requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
          var icon = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";
            var tempK=parseFloat(data.main.temp);
          var tempC = Math.round( (tempK - 273.15)*100)/100; 
          var tempK_min=parseFloat( data.main.temp_min);
          var tempC_min = Math.round( (tempK_min - 273.15)*100)/100; 
          var tempK_max=parseFloat( data.main.temp_max);
          var tempC_max = Math.round( (tempK_max - 273.15)*100)/100; 
          result.append(
            "<div id=\"main\">" +
            "<div>    Weather  in city: <strong>   " + data.name +" </strong></div>" +
            "<div>    Weather :   " + data.weather[0].main + "<img src=\"" + icon + "\" ></div>" +
            "<div>    Temperature:" + tempC + "(min : " + tempC_min + ",max : " + tempC_max + ")" + "</div>" +
            "<div>    Humidity :  " +  data.main.humidity + "</div>" +
            "<div>    Pressure: "   + data.main.pressure + "</div>" +
            "<div>   windSpeed : "  + data.wind.speed +"</div>" +
            "<div>   Geo coord : [" + data.coord.lon +" , "+data.coord.lat +"]</div>" +
            "</div>"

          )

          var mapOptions = {
            zoom: 12,
            center: new google.maps.LatLng(data.coord.lat, data.coord.lon)
          };
          map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
          google.maps.event.addListener(map, 'idle', checkIfDataRequested);
          showDailyForcast();
          showHourlyForcast();
        
        }
      });
    });
  });
  $(document).ready(function () {
    $('#btnSearchDest').click(function () {
        $('#city').empty();
      var requestData = $('#txtCityDest').val() + ',' + $('#txtCountryDest').val();
      var result = $('#city');
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather',
        type: 'get',
        data: { zip: requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
           var tempK=parseFloat(data.main.temp);
          var tempC = Math.round( (tempK - 273.15)*100)/100; 
          var tempK_min=parseFloat( data.main.temp_min);
          var tempC_min = Math.round( (tempK_min - 273.15)*100)/100; 
          var tempK_max=parseFloat( data.main.temp_max);
          var tempC_max = Math.round( (tempK_max - 273.15)*100)/100; 
          var icon = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";
          result.append(
           "<div id=\"main\">" +
            "<div>    Weather  in city: <strong>   " + data.name +" </strong></div>" +
            "<div>    Weather :   " + data.weather[0].main + "<img src=\"" + icon + "\" ></div>" +
            "<div>    Temperature:" + tempC + "(min : " + tempC_min + ",max : " + tempC_max + ")" + "</div>" +
            "<div>    Humidity :  " +  data.main.humidity + "</div>" +
            "<div>    Pressure: "   + data.main.pressure + "</div>" +
            "<div>   windSpeed : "  + data.wind.speed +"</div>" +
            "<div>   Geo coord : [" + data.coord.lon +" , "+data.coord.lat +"]</div>" +
            "</div>"

          )

          var mapOptions = {
            zoom: 12,
            center: new google.maps.LatLng(data.coord.lat, data.coord.lon)
          };
          map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
          google.maps.event.addListener(map, 'idle', checkIfDataRequested);
          showDailyForcastDest();
          showHourlyForcastDest();
         
        
        }
      });
    });
  });
    $(document).ready(function () {
    $('#btnRoute').click(function () {
     calculateRoute  ($('#txtCity').val() ,$('#txtCityDest').val()); 
    })})

function showDailyForcast()
{

  $('#daily').empty();
   var requestData = $('#txtCity').val();
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast/daily',
        type: 'get',
        data: {q:requestData,units:'metric',cnt:'7', appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
     
        
          
         for(var i=0;i<data.list.length;i++){
           var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
           var x=data.list[i].dt;
           var date = new Date(parseInt(x)*1000);
           var dateTime = date.toString();
          var date2 = dateTime.split(' ', 4).join(' ');
            $('#daily').append(  
                
                "<div id=\"main\">"+"<div title=\"Date\">"+date2+"</div>"+"<img src=\"" + icon + "\" >"+
                "<div title=\"Temp\">"+data.list[i].temp.day+"°C</div>"
                +" <div title=\"Pressure\">"+data.list[i].pressure+"</div>"
                +"<div title=\"Wind\">"+data.list[i].speed+"m/s</div>"  
                +"</div>"
               
              );
         }
          
        }
      });
}

function showDailyForcastDest()
{

  $('#daily').empty();
   var requestData = $('#txtCityDest').val();
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast/daily',
        type: 'get',
        data: {q:requestData,units:'metric',cnt:'7', appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
        
         
          
         for(var i=0;i<data.list.length;i++){
           var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
           var x=data.list[i].dt;
           var date = new Date(parseInt(x)*1000);
           var dateTime = date.toString();
          var date2 = dateTime.split(' ', 4).join(' ');
            $('#daily').append(  
         
               "<div id=\"main\">"+"<div title=\"Date\">"+date2+"</div>"+"<img src=\"" + icon + "\" >"+
                "<div title=\"Temp\">"+data.list[i].temp.day+"°C</div>"
                +" <div title=\"Pressure\">"+data.list[i].pressure+"</div>"
                +"<div title=\"Wind\">"+data.list[i].speed+"m/s</div>"  
                +"</div>"
               
              );
         }
          
        }
      });
}

  
function showHourlyForcast()
{
   $('#hourly').empty();
   var requestData = $('#txtCity').val();
     
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast',
        type: 'get',
        data: {q:requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
          
         
      
          
         for(var i=0;i<data.list.length;i++){
           var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
          var tempK=parseFloat(data.list[i].main.temp);
          var tempC = Math.round( (tempK - 273.15)*100)/100; 
           
            $('#hourly').append(  
                
                "<div id=\"main\">"+"<div title=\"Date\">"+data.list[i].dt_txt+"</div>"+"<img src=\"" + icon + "\" >"+
                "<div title=\"Temp\">"+tempC+"°C</div>"
                +" <div title=\"Description\">"+data.list[i].weather[0].description+"</div>"
                +" <div title=\"Pressure\">"+data.list[i].main.pressure+"</div>"
                +" <div title=\"Humidity\">"+data.list[i].main.humidity+"</div>"
                +"<div title=\"Wind\">"+data.list[i].wind.speed+"m/s</div>"  
                +"</div>"
               
              );
         }
          
        }
      });
}
function showHourlyForcastDest()
{
   $('#hourly').empty();
   var requestData = $('#txtCityDest').val();
     
      $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast',
        type: 'get',
        data: {q:requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {
          
         
      
          
         for(var i=0;i<data.list.length;i++){
           var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
          var tempK=parseFloat(data.list[i].main.temp);
          var tempC = Math.round( (tempK - 273.15)*100)/100; 
            $('#hourly').append(  
              
                "<div id=\"main\">"+"<div title=\"Date\">"+data.list[i].dt_txt+"</div>"+"<img src=\"" + icon + "\" >"+
                "<div title=\"Temp\">"+tempC+"°C</div>"
                +" <div title=\"Description\">"+data.list[i].weather[0].description+"</div>"
                +" <div title=\"Pressure\">"+data.list[i].main.pressure+"</div>"
                +" <div title=\"Humidity\">"+data.list[i].main.humidity+"</div>"
                +"<div title=\"Wind\">"+data.list[i].wind.speed+"m/s</div>"  
                +"</div>"
               
              );
         }
          
        }
      });
}
//************************************************************************
function calculateRoute(from, to) {
        // Center initialized to Naples, Italy
        var myOptions = {
          zoom: 10,
          center: new google.maps.LatLng(40.84, 14.25),
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        // Draw the map
        var mapObject = new google.maps.Map(document.getElementById("map-canvas"), myOptions);

        var directionsService = new google.maps.DirectionsService();
        var directionsRequest = {
          origin: from,
          destination: to,
          travelMode: google.maps.DirectionsTravelMode.DRIVING,
          unitSystem: google.maps.UnitSystem.METRIC
        };
        directionsService.route(
          directionsRequest,
          function(response, status)
          {
            if (status == google.maps.DirectionsStatus.OK)
            {
              new google.maps.DirectionsRenderer({
                map: mapObject,
                directions: response
              });
            }
            else
              $("#error").append("Unable to retrieve your route<br />");
          }
        );
      }
 
  var checkIfDataRequested = function () {
    // Stop extra requests being sent
    while (gettingData === true) {
      request.abort();
      gettingData = false;
    }
    getCoords();
  };
  // Get the coordinates from the Map bounds
  var getCoords = function () {
    var bounds = map.getBounds();
    var NE = bounds.getNorthEast();
    var SW = bounds.getSouthWest();
    getWeather(NE.lat(), NE.lng(), SW.lat(), SW.lng());
  };
  // Make the weather request
  var getWeather = function (northLat, eastLng, southLat, westLng) {
    gettingData = true;
    var requestString = "http://api.openweathermap.org/data/2.5/box/city?bbox="
      + westLng + "," + northLat + "," //left top
      + eastLng + "," + southLat + "," //right bottom
      + map.getZoom()
      + "&cluster=yes&format=json"
      + "&APPID=" + openWeatherMapKey;
    request = new XMLHttpRequest();
    request.onload = proccessResults;
    request.open("get", requestString, true);
    request.send();
  };
  // Take the JSON results and proccess them
  var proccessResults = function () {
    console.log(this);
    var results = JSON.parse(this.responseText);
    if (results.list.length > 0) {
      resetData();
      for (var i = 0; i < results.list.length; i++) {
        geoJSON.features.push(jsonToGeoJson(results.list[i]));
      }
      drawIcons(geoJSON);
    }
  };
  var infowindow = new google.maps.InfoWindow();
  // For each result that comes back, convert the data to geoJSON
  var jsonToGeoJson = function (weatherItem) {
    var feature = {
      type: "Feature",
      properties: {
        city: weatherItem.name,
        weather: weatherItem.weather[0].main,
        temperature: weatherItem.main.temp,
        min: weatherItem.main.temp_min,
        max: weatherItem.main.temp_max,
        humidity: weatherItem.main.humidity,
        pressure: weatherItem.main.pressure,
        windSpeed: weatherItem.wind.speed,
        windDegrees: weatherItem.wind.deg,
        windGust: weatherItem.wind.gust,
        icon: "http://openweathermap.org/img/w/"
        + weatherItem.weather[0].icon + ".png",
        coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
      },
      geometry: {
        type: "Point",
        coordinates: [weatherItem.coord.lon, weatherItem.coord.lat]
      }
    };
    // Set the custom marker icon
    map.data.setStyle(function (feature) {
      return {
        icon: {
          url: feature.getProperty('icon'),
          anchor: new google.maps.Point(25, 25)
        }
      };
    });
    // returns object
    return feature;
  };
  // Add the markers to the map
  var drawIcons = function (weather) {
    map.data.addGeoJson(geoJSON);
    // Set the flag to finished
    gettingData = false;
  };
  // Clear data layer and geoJSON
  var resetData = function () {
    geoJSON = {
      type: "FeatureCollection",
      features: []
    };
    map.data.forEach(function (feature) {
      map.data.remove(feature);
    });
  };
  google.maps.event.addDomListener(window, 'load', initialize);

</script>
</head>

<body>
 <% String fullname = (String)session.getAttribute("fullname");
	 request.setAttribute("fullname", fullname);
	 Integer userId = (Integer)session.getAttribute("userId");
	 request.setAttribute("userId", userId);
	 String city = (String)session.getAttribute("city");
	 request.setAttribute("city", city);
	 %>
	 <input type = "hidden" id = "useridhidden" value="${city}" />
  <div id="heada">
    <form>
  <fieldset>
    <legend><lable><strong>Source Address</strong></lable></legend>
    <br>
    <lable><strong>City &nbsp &nbsp &nbsp &nbsp</strong></lable><input type="text" id="txtCity"  size="30"><br><br>
   <lable><strong>Country </strong></lable><input type="text" placeholder = "Type country code/name here" "text" id="txtCountry" size="30"><br><br>
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input type="button" id='btnSearch' value="search">
  </fieldset>
</form>
      </div>
  <div id="headb"><form>
  <fieldset>
    <legend><label><strong>Destination Address</strong></label></legend>
    <br>
    <lable><strong>City &nbsp &nbsp &nbsp &nbsp</strong></lable><input type="text" placeholder = "Type destination city here" id="txtCityDest"  size="30"><br><br>
   <lable><strong>Country </strong></lable><input type="text" id="txtCountryDest" placeholder = "Type country code/name here" size="30"><br><br>
    &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <input type="button" id='btnSearchDest' value="search">
      &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp<input type="button" id='btnRoute' value="Route">
  </fieldset>
</form>
</div>
  <div id="left">
    

   <div id="resultDivRight">
   <ul class="tab">
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'city')" id="defaultOpen">Today</a></li>
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'daily')">Daily</a></li>
  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'hourly')">Hourly</a></li>
  
</ul>

<div id="city" class="tabcontent">
  <span onclick="this.parentElement.style.display='none'" class="topright">x</span>
  
      <p>No Today Data</p>
</div>

<div id="daily" class="tabcontent">
  <span onclick="this.parentElement.style.display='none'" class="topright">x</span>

  <p>No Daily Data</p>
  
</div>

<div id="hourly" class="tabcontent">
  <span onclick="this.parentElement.style.display='none'" class="topright">x</span>
  
  
  <p>No Hourly Data</p>
</div></div>

 </div>
 </div>
 <div style="height:100%; width: 100%;"> 
    <div id="map-canvas"></div>
  </div>
    
  </div>
  
 
</body>
</html>