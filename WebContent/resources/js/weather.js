
var map;
var geoJSON;
var request;
var gettingData = false;
var openWeatherMapKey = "4ca5fe133ba4d60dde178e3b5151e827"
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
                var tempK = parseFloat(data.main.temp);
                var tempC = Math.round((tempK - 273.15) * 100) / 100;
                var tempK_min = parseFloat(data.main.temp_min);
                var tempC_min = Math.round((tempK_min - 273.15) * 100) / 100;
                var tempK_max = parseFloat(data.main.temp_max);
                var tempC_max = Math.round((tempK_max - 273.15) * 100) / 100;
                result.append(
                    "<div id=\"main\">" +
                    "<div>    Weather  in city: <strong>   " + data.name + " </strong></div>" +
                    "<div>    Weather :   " + data.weather[0].main + "<img src=\"" + icon + "\" ></div>" +
                    "<div>    Temperature:" + tempC + "(min : " + tempC_min + ",max : " + tempC_max + ")" + "</div>" +
                    "<div>    Humidity :  " + data.main.humidity + "</div>" +
                    "<div>    Pressure: " + data.main.pressure + "</div>" +
                    "<div>   windSpeed : " + data.wind.speed + "</div>" +
                    "<div>   Geo coord : [" + data.coord.lon + " , " + data.coord.lat + "]</div>" +
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
                var tempK = parseFloat(data.main.temp);
                var tempC = Math.round((tempK - 273.15) * 100) / 100;
                var tempK_min = parseFloat(data.main.temp_min);
                var tempC_min = Math.round((tempK_min - 273.15) * 100) / 100;
                var tempK_max = parseFloat(data.main.temp_max);
                var tempC_max = Math.round((tempK_max - 273.15) * 100) / 100;
                var icon = "http://openweathermap.org/img/w/" + data.weather[0].icon + ".png";
                result.append(
                    "<div id=\"main\">" +
                    "<div>    Weather  in city: <strong>   " + data.name + " </strong></div>" +
                    "<div>    Weather :   " + data.weather[0].main + "<img src=\"" + icon + "\" ></div>" +
                    "<div>    Temperature:" + tempC + "(min : " + tempC_min + ",max : " + tempC_max + ")" + "</div>" +
                    "<div>    Humidity :  " + data.main.humidity + "</div>" +
                    "<div>    Pressure: " + data.main.pressure + "</div>" +
                    "<div>   windSpeed : " + data.wind.speed + "</div>" +
                    "<div>   Geo coord : [" + data.coord.lon + " , " + data.coord.lat + "]</div>" +
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
        calculateRoute($('#txtCity').val(), $('#txtCityDest').val());
    })
})

function showDailyForcast() {

    $('#daily').empty();
    var requestData = $('#txtCity').val();
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast/daily',
        type: 'get',
        data: { q: requestData, units: 'metric', cnt: '7', appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {



            for (var i = 0; i < data.list.length; i++) {
                var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
                var x = data.list[i].dt;
                var date = new Date(parseInt(x) * 1000);
                var dateTime = date.toString();
                var date2 = dateTime.split(' ', 4).join(' ');
                $('#daily').append(

                    "<div id=\"main\">" + "<div title=\"Date\">" + date2 + "</div>" + "<img src=\"" + icon + "\" >" +
                    "<div title=\"Temp\">" + data.list[i].temp.day + "°C</div>"
                    + " <div title=\"Pressure\">" + data.list[i].pressure + "</div>"
                    + "<div title=\"Wind\">" + data.list[i].speed + "m/s</div>"
                    + "</div>"

                );
            }

        }
    });
}

function showDailyForcastDest() {

    $('#daily').empty();
    var requestData = $('#txtCityDest').val();
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast/daily',
        type: 'get',
        data: { q: requestData, units: 'metric', cnt: '7', appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {



            for (var i = 0; i < data.list.length; i++) {
                var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
                var x = data.list[i].dt;
                var date = new Date(parseInt(x) * 10000);
                var dateTime = date.toString();
                var date2 = dateTime.split(' ', 4).join(' ');
                $('#daily').append(

                    "<div id=\"main\">" + "<div title=\"Date\">" + date2 + "</div>" + "<img src=\"" + icon + "\" >" +
                    "<div title=\"Temp\">" + data.list[i].temp.day + "°C</div>"
                    + " <div title=\"Pressure\">" + data.list[i].pressure + "</div>"
                    + "<div title=\"Wind\">" + data.list[i].speed + "m/s</div>"
                    + "</div>"

                );
            }

        }
    });
}


function showHourlyForcast() {
    $('#hourly').empty();
    var requestData = $('#txtCity').val();

    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast',
        type: 'get',
        data: { q: requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {




            for (var i = 0; i < data.list.length; i++) {
                var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
                var tempK = parseFloat(data.list[i].main.temp);
                var tempC = Math.round((tempK - 273.15) * 100) / 100;

                $('#hourly').append(

                    "<div id=\"main\">" + "<div title=\"Date\">" + data.list[i].dt_txt + "</div>" + "<img src=\"" + icon + "\" >" +
                    "<div title=\"Temp\">" + tempC + "°C</div>"
                    + " <div title=\"Description\">" + data.list[i].weather[0].description + "</div>"
                    + " <div title=\"Pressure\">" + data.list[i].main.pressure + "</div>"
                    + " <div title=\"Humidity\">" + data.list[i].main.humidity + "</div>"
                    + "<div title=\"Wind\">" + data.list[i].wind.speed + "m/s</div>"
                    + "</div>"

                );
            }

        }
    });
}
function showHourlyForcastDest() {
    $('#hourly').empty();
    var requestData = $('#txtCityDest').val();

    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/forecast',
        type: 'get',
        data: { q: requestData, appid: openWeatherMapKey },
        dataType: 'json',
        success: function (data) {




            for (var i = 0; i < data.list.length; i++) {
                var icon = "http://openweathermap.org/img/w/" + data.list[i].weather[0].icon + ".png";
                var tempK = parseFloat(data.list[i].main.temp);
                var tempC = Math.round((tempK - 273.15) * 100) / 100;
                $('#hourly').append(

                    "<div id=\"main\">" + "<div title=\"Date\">" + data.list[i].dt_txt + "</div>" + "<img src=\"" + icon + "\" >" +
                    "<div title=\"Temp\">" + tempC + "°C</div>"
                    + " <div title=\"Description\">" + data.list[i].weather[0].description + "</div>"
                    + " <div title=\"Pressure\">" + data.list[i].main.pressure + "</div>"
                    + " <div title=\"Humidity\">" + data.list[i].main.humidity + "</div>"
                    + "<div title=\"Wind\">" + data.list[i].wind.speed + "m/s</div>"
                    + "</div>"

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
        function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
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

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";

}
