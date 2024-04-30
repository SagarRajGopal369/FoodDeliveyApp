/**
 * 
 */
function updateQuantity(itemId, action) {
    // Select the quantity input field for the specific item using its itemId
    var quantityInput = document.querySelector('input[name="quantity_' + itemId + '"]');
    
    // Get the current quantity value and parse it as an integer
    var currentQuantity = parseInt(quantityInput.value);

    // Update the quantity based on the specified action (increase or decrease)
    if (action === 'increase') {
        quantityInput.value = currentQuantity + 1;
    } else if (action === 'decrease' && currentQuantity >1) {
        quantityInput.value = currentQuantity - 1;
    }
	 console.log('itemId:', itemId);
	console.log('action:', action);
	console.log('currentQuantity:', currentQuantity);


    // You might want to send an AJAX request to update the quantity on the server
    // For example, you can use the itemId and updated quantity
    // var xhr = new XMLHttpRequest();
    // xhr.open('POST', 'UpdateQuantityServlet', true);
    // xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    // xhr.send('itemId=' + itemId + '&quantity=' + quantityInput.value);
}
