play20-qunit
============

Qunit module for Play Framework 2!

How to use ?
------------

* clone the repo
* execute 'play dist' in project-code folder
* add the dependency in your project

	val appDependencies = Seq(
	// Add your project dependencies here,
        "play20-qunit" % "play20-qunit_2.9.1" % "1.0"
    	)
* create the controller

Qunit.java :

	public class Qunit extends Controller {

	    public static Result test(String filename) throws IOException {
		return QunitHelper.test(filename);
	    }

	}

routes

	GET     /qunit/:filename            controllers.Qunit.test(filename: String)

* create the folder 'qunit' at the root
* add tests in folder


domtest.html 

	<div id="injected"></div>

	<script src="/assets/javascripts/bigup.js" type="text/javascript"></script>
	<script src="/assets/javascripts/jquery_tagName.min.js" type="text/javascript"></script>

domtest.js

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

