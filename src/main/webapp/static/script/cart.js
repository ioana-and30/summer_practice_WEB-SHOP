function loadCart(cartId,pushHistory=true){

    if (!cartId) {
        cartId = getCartId();
    }

    fetch(`/cart?cartId=${cartId}`)
        .then(response=>response.text())
        .then(html=>
        {
            const container=document.getElementById("products-container");
            container.innerHTML=html;
            if(pushHistory) {
                history.pushState({cartId:cartId}, "",`/categories?cartId=${cartId}`);
                if (container.querySelector(".cart-id")) {
                    loadCartFromLocalStorage();
                }
            }
        })
        .catch(()=>{
            document.getElementById("products-container").innerHTML="<p>Error loading cart.</p>";
        });
}

document.addEventListener('DOMContentLoaded', () => {

    const urlParams = new URLSearchParams(window.location.search);
    const cartIdFromUrl = urlParams.get('cartId');

    if (cartIdFromUrl) {
        localStorage.setItem("cartId", cartIdFromUrl);
        loadCart(cartIdFromUrl, false);
    }
    const cartLink = document.getElementById("cart-link");
    if (cartLink) {
        cartLink.addEventListener("click", function (e) {
            e.preventDefault();
            loadCart(getCartId());
        });
    }

    const quantityInput = document.getElementById('quantity');
    if (quantityInput) {
        const max = parseInt(quantityInput.getAttribute('max')) || Infinity;

        quantityInput.addEventListener('input', () => {
            let value = parseInt(quantityInput.value) || 1;
            if (value < 1) value = 1;
            if (value > max) value = max;
            quantityInput.value = value;
        });
    }

    updateCartQuantityDisplayed()
});

function getCartId(){
    let cartId=localStorage.getItem("cartId");

    if(!cartId){
        cartId='cart_'+Math.floor(Math.random()*10000);
        localStorage.setItem("cartId",cartId);
    }

    return cartId;
}

function getProductData(productId) {

    let product = document.querySelector(`.product-card[data-product-id='${productId}']`);

    if (!product) {
        product = document.querySelector(`.product-container[data-id='${productId}']`);
    }
    if (!product) return null;

    const quantityInput = document.getElementById("quantity");
    let quantity = 1;

    if (quantityInput) {
        const q = parseInt(quantityInput.value);
        if (!isNaN(q) && q > 0) {
            quantity = q;
        }
    }
    const stockAttr = product.querySelector(".add-to-cart")?.getAttribute("data-stock") || "0";

    return {
        id: productId,
        name: product.querySelector(".product-name").innerHTML,
        price: parseFloat(product.querySelector(".product-price").innerText.replace("$", "")),
        image: product.querySelector(".product-image").getAttribute("src"),
        quantity: quantity,
        stock:stockAttr
    };
}

function getCart() {
    let cart = JSON.parse(localStorage.getItem("cart"));
    if (!cart || !Array.isArray(cart.items)) {
        cart = { cartId: getCartId(), items: [] };
    }
    return cart;
}

function saveCart(cart) {
    localStorage.setItem("cart", JSON.stringify(cart));
}

function getAvailableStockFromProductPage() {
    const quantityInput = document.getElementById("quantity");
    if (!quantityInput) return 0;
    return parseInt(quantityInput.max) || 0;
}

function getAvailableStockFromCategoryPage(productId){

    const productElem = document.querySelector(`.product-card[data-product-id="${productId}"]`);
    if (productElem) {
        const stockAttr = productElem.getAttribute('data-stock') ||
            productElem.querySelector('.add-to-cart').getAttribute('data-stock');
        return stockAttr ? parseInt(stockAttr) : Infinity;
    }
    return 0;
}

function addOrUpdateCartItemProductPage(cart, product) {

    const existingProduct=cart.items.find(p=>p.id===product.id);


    if(existingProduct){
        existingProduct.quantity+=product.quantity;
    }
    else{
        cart.items.push(product)
    }
    return true;
}

function updateCartQuantityDisplayed(){

    const cart = getCart();
    const total = cart.items.reduce((sum, item) => sum + item.quantity, 0);
    document.getElementById("cart-count").innerText = total;
}

function addToCart(productId, type=1) {
    const product = getProductData(productId);
    if (!product) {
        console.error("Product not found. Id:", productId);
        return;
    }

    let availableStock;
    switch(type){
        case 1:
            availableStock = getAvailableStockFromProductPage();
            break;
        case 2:
            availableStock=getAvailableStockFromCategoryPage(productId);
            product.quantity=1;
            break;
    }
    const cart = getCart();
    const existingItem = cart.items.find(item => item.id === productId);
    const currentQuantityInCart = existingItem ? existingItem.quantity : 0;

    if ((currentQuantityInCart + product.quantity) > availableStock) {
        const maxAddable = Math.max(0, availableStock - currentQuantityInCart);
        showPopUp(`You can only add ${maxAddable} more of this product.`, "warning");
        return;
    }

    if(addOrUpdateCartItemProductPage(cart,product)){
        saveCart(cart);
        updateCartQuantityDisplayed();

        showPopUp(`Product ${product.name} added to cart`,"success");
    }
}

function incrementQuantityValueCart(button) {

    const productElem = button.closest('.product-cart');
    const quantityElem = productElem.querySelector('.quantity-value');
    let quantity = parseInt(quantityElem.textContent) || 0;

    const maxValue = parseInt(productElem.getAttribute('data-stock')) || 0;

    if (quantity+1 > maxValue)  {
        showPopUp(`The maximum quantity value has been exceeded! Maximum available: ${maxValue}`, "warning");
        return;
    }

    quantity++;
    quantityElem.textContent=quantity;

    const productId = productElem.querySelector('.product-id').textContent;
    updateCartItemQuantityInLocalStorage(productId, quantity);

    updateCartQuantityDisplayed();
    updateCartTotals();
}

function decrementQuantityValueCart(button) {
    const productElem = button.closest('.product-cart');
    const quantityElem = productElem.querySelector('.quantity-value');

    let quantity = parseInt(quantityElem.textContent) || 0;

    if (quantity > 1) {
        quantity--;
        quantityElem.textContent = quantity;

        const productId = productElem.querySelector('.product-id').textContent;
        updateCartItemQuantityInLocalStorage(productId, quantity);

        updateCartQuantityDisplayed()
        updateCartTotals();
    } else {
        showPopUp("Quantity cannot go below 1!", "warning");
    }
}
function updateCartItemQuantityInLocalStorage(productId, newQuantity) {

    const cart = getCart();
    const pid = Number(productId);
    const product = cart.items.find(item => Number(item.id) === pid);

    if (product) {
        product.quantity = newQuantity;
        saveCart(cart);
        console.log(`Updated ${product.name} (ID: ${pid}) to quantity ${newQuantity}`);
    } else {
        console.warn(`Product with ID ${pid} not found in cart`, cart.items);
    }
}

function addProductsInJSP(items) {
    const productList = document.querySelector(".products-list-cart");
    const template = productList.querySelector(".product-cart");

    productList.innerHTML = "";

    items.forEach(item => {
        const productElem = template.cloneNode(true);
        productElem.style.display = "";

        productElem.setAttribute('data-stock', item.stock);
        productElem.querySelector(".product-name").textContent=item.name;
        productElem.querySelector(".product-id").textContent=item.id;
        productElem.querySelector(".product-image-cart").src = item.image;
        productElem.querySelector(".price-value").textContent = `$${item.price.toFixed(2)}`;
        productElem.querySelector(".quantity-value").textContent = item.quantity;
        productElem.querySelector(".total-price-value").textContent = `$${(item.price * item.quantity).toFixed(2)}`;

        const quantityInput = productElem.querySelector(".quantity-text");
        if (quantityInput) {
            quantityInput.value = item.quantity;
        }

        productList.appendChild(productElem);
    });
}

function updateCartTotals()
{
    const productElems = document.querySelectorAll(".product-cart");
    let orderValue = 0;
    let delivery=0;

    productElems.forEach(product => {
        const price = parseFloat(product.querySelector(".price-value").textContent.replace('$', '')) || 0;
        const quantity = parseInt(product.querySelector(".quantity-value").textContent) || 0;
        const total = price * quantity;

        const totalElem = product.querySelector(".total-price-value");
        if (totalElem) {
            totalElem.textContent = `$${total.toFixed(2)}`;
        }

        orderValue += total;
    });

    orderValue = parseFloat(orderValue.toFixed(2));

    if(orderValue>=250){
        delivery=0.00;
    }
    else{
        delivery=10.99
    }

    const totalWithDelivery = orderValue + delivery;

    document.querySelector(".order-value td").textContent = `$${orderValue.toFixed(2)}`;
    document.querySelector(".total-cart td").textContent = `$${totalWithDelivery.toFixed(2)}`;
    document.querySelector(".delivery-fee td").textContent=`$${delivery.toFixed(2)}`;
}

function loadCartFromLocalStorage(){

    const cart=getCart();

    document.querySelector(".cart-id").textContent=`Cart ID: ${cart.cartId}`;

    const orderValueElem=document.querySelector(".order-value td");
    const totalValueElem=document.querySelector(".total-cart td");
    const deliveryFeeElem=document.querySelector(".delivery-fee td");

    if (!cart.items.length) {
        document.querySelector(".products-list-cart").innerHTML = "<li>Your cart is empty.</li>";
        orderValueElem.textContent = "$0.00";
        totalValueElem.textContent = "$0.00";
        deliveryFeeElem.textContent="$10.99";
        return;
    }
    addProductsInJSP(cart.items);
    updateCartTotals();
}

function showConfirmModal(message, onYes) {
    const modal = document.getElementById("confirm-modal");
    const textElem = document.getElementById("confirm-text");
    const yesBtn = document.getElementById("modal-yes");
    const noBtn = document.getElementById("modal-no");

    textElem.textContent = message;
    modal.style.display = "flex";

    modal.onclick = (e) => {
        if (e.target === modal) e.stopPropagation();
    };

    yesBtn.onclick = () => {
        modal.style.display = "none";
        if (typeof onYes === "function") onYes();
    };

    noBtn.onclick = () => {
        modal.style.display = "none";
    };
}

function removeProduct(buttonElem){

    const productElem = buttonElem.closest('.product-cart');
    if (!productElem) return;

    const productId = productElem.querySelector('.product-id').textContent;

    showConfirmModal("Are you sure you want to remove this product?", () => {
        productElem.remove();

        const productIdNum = Number(productId);

        const cart = getCart();
        cart.items = cart.items.filter(item => item.id !== productIdNum);
        saveCart(cart);

        updateCartTotals();
        updateCartQuantityDisplayed();
    });
}

function clearCart()
{
    localStorage.removeItem('cart');

    const productsList = document.querySelector('.products-list-cart');
    if (productsList) {
        const productItems = productsList.querySelectorAll('li.product-cart:not([style*="display:none"])');
        productItems.forEach(item => item.remove());
    }

    updateCartItemQuantityInLocalStorage();
    updateCartQuantityDisplayed();
    updateCartTotals();

    showPopUp("The cart is empty", "success");
}