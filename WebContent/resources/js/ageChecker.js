
"use strict";

$(function () {
	var enteredValue = $('#dob').val();
	var enteredAge = getAge(enteredValue);
	if( enteredAge < 18 ) {
	    alert("DOB not valid");
	    enteredValue.focus();
	    return false;
	}
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