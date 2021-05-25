$(document).ready(function() {
    setInterval(function(){ 
        $.ajax({
	        url: "http://localhost:8081/scores"
	    }).then(function(data) {
	        $('#score').text("Prizes: " + data);
	    });
    }, 1000);
});