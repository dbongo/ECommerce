$(function() {
	function getProductsFromLocal() {
		var stringifiedProducts = localStorage.getItem('products');
		console.log(stringifiedProducts );
		if (stringifiedProducts == undefined || $.isEmptyObject(stringifiedProducts)) {
			return {};
		}
		return JSON.parse(stringifiedProducts);
	}
	
	function postLocalCartToServer() {
		var products = getProductsFromLocal();
		var requestCounter = Object.keys(products).length;
		for (var id in products) {
			$.post("/cart/add", {"product": id, "quantity": products[id]}, function(){
				requestCounter--;
				if (requestCounter == 0) {
					localStorage.setItem("products", JSON.stringify({}));
					location.reload();
				}
			});
		}
	}
	postLocalCartToServer();
});