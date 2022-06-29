//<![CDATA[
'use strict'
let HOME = (function ($) {
	return {

	}
}(jQuery));

$(document).ready(function () {
	'use strict'

	$('#loginForm').form({
		fields: {
			userName: {
				identifier: 'userName',
				rules: [
				  {
					type   : 'empty',
					prompt : 'Please enter your name !!!'
				  }
				]
			  },
			  passWord: {
				identifier: 'passWord',
				rules: [
				  {
					type   : 'empty',
					prompt : 'Please select at least two skills'
				  },
				  {
					type   : 'minLength[6]',
					prompt : 'Please select at least two skills'
				  }
				]
			  }
		},
		inline: true,
		on: 'blur'
	});

	$('#login').on('click', _.debounce(function (event) {
		event.preventDefault();

		if ($('#loginForm').form('validate form')) {
			alert('pass validation');
		}


	}, 300, true));



});
//]]>