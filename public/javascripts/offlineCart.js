$(function(){
	function getTableRowString(product, quantity) {
		var tableRow = '';
		 tableRow += '	<tr class="cart-item">';
		 tableRow += '	<td>';
		 tableRow += '	  <a href="/products/' + product.id + '"><img src="' + product.image + '" class="img-rounded"></a> <a href="/products/' + product.id + '"><span>' + product.name + '</span></a>';
		 tableRow += '	</td>';
		 tableRow += '	<td>';
		 tableRow += '		<span>' + quantity + '</span>';
		 tableRow += '	</td>';
		 tableRow += '	<td>';
		 tableRow += '		' + product.price + ':-';
		 tableRow += '	</td>';
		 tableRow += '	<td class="trash-item-in-cart" id="' + product.id + '">';
		 tableRow += '		<a><i class="fa fa-trash-o" id="' + product.id + '"></i></a>';
		 tableRow += '	</td>';
		 tableRow += '</tr>';	
		 return tableRow;
	}
	
	function getProductsFromLocal() {
		var stringifiedProducts = localStorage.getItem('products');
		console.log(stringifiedProducts );
		if (stringifiedProducts == undefined || $.isEmptyObject(stringifiedProducts)) {
			return {};
		}
		return JSON.parse(stringifiedProducts);
	}
	
	function populateCart() {
		$(".cart-item").remove();
		var products = getProductsFromLocal();
		var totalQuantity = 0;
		var totalSum = 0;
		var requestCounter = Object.keys(products).length;
		for (var id in products) {
			totalQuantity += products[id];
			$.get("/products/raw/" + id, function( data ) {
				var tableRowString = getTableRowString(data, products[data.id]);
				$(".total-row").before(tableRowString);
				totalSum += data.price * products[data.id];
				requestCounter --;
				if (requestCounter == 0) {
					continuePopulateCart(totalQuantity, totalSum);	
				}
			});
		}	
		
	}
	
	function continuePopulateCart(totalQuantity, totalSum) {
		$("#items-in-cart").text(totalQuantity);
		$(".total-quantity-cell").text(totalQuantity);
		$(".total-sum-cell").text(totalSum + ":-");
		initTrashButtons();
	}
	
	populateCart();
	
	$("#add-to-cart-form").submit(function( event ){
		event.preventDefault();
		var productId = $("#add-to-cart-form input").val();
		var quantity = $(".product-quantity-select").val();
		
		var products = getProductsFromLocal();
		if (products == null) {
			products = {}
		}
		var oldQuantity = products[productId]; 
		if (oldQuantity == undefined) {
			oldQuantity = 0;
		}
		products[productId] = oldQuantity + parseInt(quantity);
		localStorage.setItem("products", JSON.stringify(products));
		populateCart();
		return false;
	});
	
	function initTrashButtons() {
		$(".trash-item-in-cart").click(function() {
			var productId = $(this).attr("id");
			var products = getProductsFromLocal();
			console.log(products);
			delete products[productId];
			console.log(products);
			localStorage.setItem("products", JSON.stringify(products));
			populateCart();
		});	
	}
	
	function localStorageProductsIsEmpty() {
		var products = getProductsFromLocal();
		return $.isEmptyObject(products);
	}
	
	$("#login-form").submit(function( event ) {
		if (!localStorageProductsIsEmpty()) {
			$(this).append($("<input>").attr({"type":"hidden","name":"local-cart"}).val("yes"));
		}
	});
	
	$("#keep-old-cart-button").click(function(){
		localStorage.setItem("products", JSON.stringify({}));
		$(location).attr('href', "/cart");
	});
	
	$("#keep-new-cart-button").click(function(){
		$.post("/cart/clear", function() {
			postLocalCartToServer();			
		});
	});
	
	$("#merge-carts-button").click(function() {
		postLocalCartToServer();
	}) 
	
	function postLocalCartToServer() {
		var products = getProductsFromLocal();
		var requestCounter = Object.keys(products).length;
		for (var id in products) {
			$.post("/add-product-to-cart", {"product": id, "quantity": products[id]}, function(){
				requestCounter--;
				if (requestCounter == 0) {
					localStorage.setItem("products", JSON.stringify({}));
					$(location).attr('href', "/cart");
				}
			});
		}
	}
	
});


//<button id="keep-old-cart-button" type="button" class="btn btn-default">Keep old cart</button>
//<button id="keep-new-cart-button" type="button" class="btn btn-primary">Keep new cart</button>
//<button id="merge-carts-button" type="button" class="btn btn-success">Merge the carts</button>





