
$(document).ready(function() {

    $("#select-name").select2();
	$("#select-name").select2("focus");
	
	$("#abc").on("keydown", function(event) {
      if (event.key === "Enter") {
        event.preventDefault();
        $("#abc").submit();
      }
    });
    
    $(".js-select2").on('change', function(){
		$("#select-name").select2("focus");
	});

})