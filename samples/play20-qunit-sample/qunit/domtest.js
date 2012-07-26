$(document).ready(function() {

	module("Test with injected html");

	test("I can get the injected element", function() {
		var value = $("#injected");
		equal(value.size(), 1, "Ok, great!");
	});


	module("Test with injected html & js");

	test("I can get the the tag name", function() {
		var value = $("#injected").bigUp();
		equal(value, "bigUp", "Ok, great!");
	});


	module("Test with injected html & js compiled with google closure");

	test("I can get the the tag name", function() {
		var value = $("#injected").tagName();
		equal(value, "div", "Ok, very great!");
	});


});
