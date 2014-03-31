$(function() {
	$("#image-source-list a").click(function(){
		var text = $(this).text();
		$("#image-source-button").text(text);
	});
	
	$("#profuct-form").submit(function() {
		if($("#image-source-button").text() == "Local") {
			var imgName = "/assets/images/" + $("#img-name").val(); 
			$("#img-name").val(imgName);
		} 
	});
});