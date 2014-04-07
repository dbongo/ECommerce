$(function(){
	$(".trash-product").click(function(){
		$.ajax({
			url: '/cart/remove/' + $(this).attr("id"),
			type: 'DELETE'
		})
		.always(function(){
			location.reload();
		});
	});
});