"use strict";

$(function () {

	$('.nav-tabs a[data-toggle="tabajax"]').on('shown.bs.tab', function(event){
	    var x = $(event.target).text();         // active tab
	    var y = $(event.relatedTarget).text();  // previous tab
	    var currenttab = $(event.target).attr("href") // activated tab
		  alert("hello");
	});
	
	
});
	