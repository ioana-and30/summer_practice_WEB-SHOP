
function loadProduct(productId, pushHistory=true){
    fetch(`/product_details?productId=${productId}`)
        .then(response=>response.text())
        .then(html=>
        {
            document.getElementById("products-container").innerHTML=html;
            displayStock()

            if(pushHistory) {
                history.pushState({productId: productId}, "", `/categories?productId=${productId}`);
            }
        })
        .catch(()=>{
            document.getElementById("products-container").innerHTML="<p>Error loading product.</p>";
        });
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
        showPopUp("The maximum quantity value has been exceeded!","warning")
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
        showPopUp("The quantity value must be a valid number greater than 1!","warning");
    }
}

function displayStock(){

    const quantityInput = document.getElementById("productQuantity");
    if (!quantityInput) return;

    const quantity = parseInt(quantityInput.value);

    if (quantity === 0) {
        const stockStatus = document.getElementById("stock-status");
        if (stockStatus) {
            stockStatus.textContent = "OUT OF STOCK";
            stockStatus.classList.remove("in-stock");
            stockStatus.classList.add("out-of-stock");
        }

        const addToCartBtn = document.getElementById("add-to-cart");
        if (addToCartBtn) addToCartBtn.disabled = true;

        const qtyField = document.getElementById("quantity");
        if (qtyField) {
            qtyField.value = 1;
            qtyField.disabled = true;
        }

        const buttons = document.getElementsByClassName("modify-quantity");
        for (const btn of buttons) btn.disabled = true;
    }
}

function getProductIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('productId');
}
function showPopUp(message, type="success") {
    const popups = document.querySelectorAll(".popup-message");

    popups.forEach(popup => {
        const textElement = popup.querySelector(".popup-text");
        const closeBtn = popup.querySelector(".popup-close");

        if (textElement) {
            textElement.textContent = message;
        }

        popup.classList.remove("success", "warning", "show", "hide");
        popup.classList.add("show", type);

        if (closeBtn) {
            closeBtn.onclick = () => {
                popup.classList.remove("show", type);
                popup.classList.add("hide");
            };
        }

        setTimeout(() => {
            popup.classList.remove("show", type);
            popup.classList.add("hide");
        }, 3500);
    });
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
