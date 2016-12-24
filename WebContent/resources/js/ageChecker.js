'use strict'

$(document)
		.ready(
				function() {
					var agealert;
					
/*					$("#agelimitdiv").datepicker();
*/					$('#dob')
							.on(
									'change',
									function() {

										var agelimit = 18;
										var enteredValue = $(this).val();
										console.log("current age is"+enteredValue);
										var enteredAge = getAge(enteredValue);
										if (enteredAge < agelimit) {
											alert("DOB not valid");
											// enteredValue.focus();
											agealert = ("<p> <em style = \"color: red\"> Your age must be above 18 years"
													+ "</em></p>");
											agealert.appendTo('#agelimitdiv');
											return false;
										}
									});

				});

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