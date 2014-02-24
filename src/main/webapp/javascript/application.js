// This is a manifest file that'll be compiled into including all the files listed below.
// Add new JavaScript/Coffee code in separate files in this directory and they'll automatically
// be included in the compiled file accessible from http://example.com/assets/application.js
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// the compiled file.
//
//= require jquery
//= require jquery_ujs
//= require_tree .
$(function() {

	$(".button").button();
	
	$(".button_save").button({
		icons: {
			primary:"ui-icon-check"
		}
	});
	
	$(".button_run").button({
		icons: {
			primary: "ui-icon-gear"
		},
		text: false
	});

	$(".button_plus").button({
		icons: {
			primary: "ui-icon-arrowthick-1-n"
		},
		text: false
	});
	
	$(".button_cancel").button({
		icons: {
			primary:"ui-icon-close"
		}
	});

	$(".button_remove").button({
		icons: {
			primary:"ui-icon-trash"
		},
		text: false
	});

	$(".button_edit").button({
		icons: {
			primary:"ui-icon-pencil"
		},
		text: false
	});
	
	$(".button_back").button({
		icons: {
			primary:"ui-icon-circle-arrow-w"
		}		
	});

	$(".button_new").button({
		icons: {
			primary:"ui-icon-plus"
		}
	});

	$(".button_password").button({
		icons: {
			primary:"ui-icon-key"
		}
	});

	$(".button_print").button({
		icons: {
			primary:"ui-icon-print"
		},
		text: false
	});

	
});

function submitForm() {
	$('form').submit();
}

function forgotMyPassword() {
	$.post("/users/request_password",$('#new_user_session').serialize(),null,'script');
}