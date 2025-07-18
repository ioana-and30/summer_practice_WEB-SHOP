
function loadProduct(productId, pushHistory=true){
    fetch(`/product_details?productId=${productId}`)
        .then(response=>response.text())
        .then(html=>
        {
            document.getElementById("products-container").innerHTML=html;
            displayStock()

            if(pushHistory) {
                history.pushState({productId: productId}, "", `/home?productId=${productId}`);
            }
        })
        .catch(()=>{
            document.getElementById("products-container").innerHTML="<p>Error loading product.</p>";
        });
}

function showAlertPopUp(message) {
    const popup = document.getElementById("quantity-popup");
    popup.textContent = message;
    popup.classList.add("show");

    setTimeout(() => {
        popup.classList.remove("show");
    }, 3500);
}

function incrementQuantityValue() {
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value);
    const maxValue = parseInt(quantity.getAttribute("max"));
    const errorDiv = document.getElementById("quantity-error");

    if (quantityValue < maxValue) {
        quantityValue ++;
        quantity.value=quantityValue.toString()
    } else {
        showAlertPopUp("The maximum quantity value has been exceeded!")
    }
}

function decrementQuantityValue() {
    const quantity = document.getElementById("quantity");
    let quantityValue = parseInt(quantity.value);
    const errorDiv = document.getElementById("quantity-error");

    if (quantityValue > 1) {
        quantityValue --;
        quantity.value=quantityValue.toString();
    } else {
        showAlertPopUp("The quantity value must be a valid number greater than 1!");
    }
}

function displayStock(){

    const quantity = parseInt(document.getElementById("productQuantity").value)

    if (quantity === 0) {
        document.getElementById("stock-status").textContent = "OUT OF STOCK";
        document.getElementById("stock-status").classList.add("out-of-stock");
        document.getElementById("add-to-cart").disabled=true;
        document.getElementById("quantity").value=0;
    }
}

function getProductIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('productId');
}

window.addEventListener('popstate', (event) => {
    if (event.state && event.state.productId) {
        loadProduct(event.state.productId, false);
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const productIdFromUrl = getProductIdFromUrl();
    if (productIdFromUrl) {
        loadProduct(productIdFromUrl, false);
    }
});
