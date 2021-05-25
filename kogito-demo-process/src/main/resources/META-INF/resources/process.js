function startProcess() {
	jQuery.ajax ({
	    url: "http://localhost:8080/WinPrizes",
	    type: "POST",
	    data: "{\"entry\": \{\"username\":\"krisv\"\}\}",
	    dataType: "json",
	    contentType: "application/json",
	    success: function(data) {
			if (false == data["winner"]) {
				$('#winner').prepend(":(<br/>");	
			} else {
	        	$('#winner').prepend("Winner!!!<br/>");
			}
	    },
		error: function(data) {
			alert('Error ' + data);
		}
	});
}