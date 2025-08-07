function loadCart(pushHistory = true) {
    fetch('/cart')
        .then(response => response.text())
        .then(html => {
            const container = document.getElementById("products-container");
            container.innerHTML = html;

            if (!isLoggedIn) {
                setTimeout(displayGuestCart, 0);
            }

            if (pushHistory) {
                history.pushState({}, "", "/cart");
            }
        })
        .catch(() => {
            document.getElementById("products-container").innerHTML = "<p>Error loading cart.</p>";
        });
}

function getCookieValue(cookieName) {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        cookie = cookie.trim();
        if (cookie.startsWith(cookieName + '=')) {
            return decodeURIComponent(cookie.substring(cookieName.length + 1));
        }
    }
    return null;
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

function addToCartFromCategory(product) {
    if (isLoggedIn === true) {
        const csrfToken = getCookieValue('XSRF-TOKEN');

        fetch('/add', {
            method: 'POST',
            credentials: "same-origin",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            },
            body: JSON.stringify({ productId: product.id, quantity: 1 })
        })
            .then(async response => {
                const text = await response.text();
                try {
                    const data = JSON.parse(text);
                    if (data.error) {
                        showPopUp(data.error, "warning");
                    } else {
                        showPopUp(data.message || "Product added to cart!", "success");
                        loadCartCount();
                    }
                } catch (e) {
                    console.error("Error", text);
                    showPopUp("Server error", "warning");
                }
            });
    } else {
        let cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
        const index = cart.findIndex(item => item.id === product.id);
        const currentQty=index>-1?cart[index].quantity:0;
        let availableStock=getAvailableStockFromCategoryPage(product.id);


        if (currentQty >= availableStock) {
            showPopUp("You've reached the maximum available stock", "warning");
            return;
        }

        const newQty = Math.min(currentQty + 1, availableStock);
        if (index > -1) {
            cart[index].quantity = newQty;
        } else {
            cart.push({ ...product, quantity: 1, stock: availableStock });
        }

        localStorage.setItem("guest_cart", JSON.stringify(cart));
        showPopUp("Product added to cart (guest)!", "success");
        loadCartCount();
    }
}

function addToCartFromDetails(product) {
    const quantityInput = document.getElementById('quantity');
    const quantity = parseInt(quantityInput.value);
    let availableStock = getAvailableStockFromProductPage();

    if (quantity > availableStock) {
        showPopUp("Quantity greater than available stock", "warning");
        return;
    }
    if
    (quantity < 1) {
        showPopUp("Quantity must be greater than 0! ", "warning");
        return;
    }

    if (isLoggedIn === true) {
        const csrfToken = getCookieValue('XSRF-TOKEN');

        fetch('/add', {
            method: 'POST',
            credentials: "same-origin",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            },
            body: JSON.stringify({ productId: product.id, quantity: quantity })
        })
            .then(async response => {
                const text = await response.text();
                try {
                    const data = JSON.parse(text);
                    if (data.error) {
                        showPopUp(data.error, "warning");
                    } else {
                        showPopUp(data.message || "Product added to cart!", "success");
                        loadCartCount();
                    }
                } catch (e) {
                    console.error("Error:", text);
                    showPopUp("Server error.", "warning");
                }
            });
    } else {
        let cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
        const index = cart.findIndex(item => item.id === product.id);
        const currentQty=index>-1?cart[index].quantity:0;


        if (currentQty >= availableStock) {
            showPopUp("You've reached the maximum available stock", "warning");
            return;
        }
        const newQty = Math.min(currentQty + quantity, availableStock);
        if (index > -1) {
            cart[index].quantity = newQty;
        } else {
            cart.push({ ...product, quantity: newQty ,stock:availableStock});
        }
        localStorage.setItem("guest_cart", JSON.stringify(cart));
        showPopUp("Product added to cart (guest)!", "success");
        loadCartCount();
    }
}


function updateCartItem(productId, newQuantity) {
    if (isLoggedIn === true) {
        const csrfToken = getCookieValue('XSRF-TOKEN');
        fetch('/update', {
            method: 'POST',
            credentials: "same-origin",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-XSRF-TOKEN': csrfToken
            },
            body: `productId=${productId}&quantity=${newQuantity}`
        })
            .then(() => {
                updateTotalPrice();
                showPopUp("Quantity updated","success");
                loadCartCount();
            })
            .catch(err => console.error("Error updating cart item:", err));
    } else {

            let cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
            const index = cart.findIndex(item => item.id === parseInt(productId));
            if (index > -1) {
                const stock = cart[index].stock || Infinity;
                cart[index].quantity = Math.min(newQuantity, stock);
                localStorage.setItem("guest_cart", JSON.stringify(cart));
                updateTotalPrice();
                showPopUp("Quantity updated (guest)", "success");
                loadCartCount();
            }
    }
}

function showConfirmModal(message, onYes) {
    const modal = document.getElementById("confirm-modal");
    const textElem = document.getElementById("confirm-text");
    const yesBtn = document.getElementById("modal-yes");
    const noBtn = document.getElementById("modal-no");

    textElem.textContent = message;
    modal.style.display = "flex";

    yesBtn.onclick = null;
    noBtn.onclick = null;

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

let productToRemove=null;

function removeProduct(buttonElem) {
    const productElem = buttonElem.closest('.product-cart');
    if (!productElem) return;

    const productId = productElem.dataset.id;
    productToRemove = productElem;

    showConfirmModal("Are you sure you want to remove this product?", () => {
        if (isLoggedIn === true) {
            const csrfToken = getCookieValue('XSRF-TOKEN');
            fetch('/remove', {
                method: 'POST',
                credentials: "same-origin",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-XSRF-TOKEN': csrfToken
                },
                body: `productId=${productId}`
            })
                .then(() => {
                    productToRemove.remove();
                    updateTotalPrice();
                    showPopUp("Product removed", "success");
                    loadCartCount();
                    productToRemove = null;
                })
                .catch(err => {
                    showPopUp("Error removing product", "warning");
                    console.error(err);
                    productToRemove = null;
                });
        } else {

            let cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
            cart = cart.filter(item => String(item.id) !== productId);
            console.log("Cart after removal:", cart);
            localStorage.setItem("guest_cart", JSON.stringify(cart));
            productToRemove.remove();
            updateTotalPrice();
            showPopUp("Product removed (guest)", "success");
            loadCartCount();
            productToRemove = null;
        }
    });
}

function clearCart() {
    showConfirmModal("Are you sure you want to clear the entire cart?", () => {
        actuallyClearCart();
        loadCartCount();
    });
}


async function actuallyClearCart() {

    const modal = document.getElementById('confirm-modal');
    modal.style.display = 'none';

    if (isLoggedIn === true) {
        const csrfToken = getCookieValue('XSRF-TOKEN');
        const items = [...document.querySelectorAll('.product-cart')];
        try {
            await Promise.all(items.map(item => {
                const productId = item.dataset.id;
                return fetch('/remove', {
                    method: 'POST',
                    credentials: "same-origin",
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        'X-XSRF-TOKEN': csrfToken
                    },
                    body: `productId=${productId}`
                });
            }));
            items.forEach(item => item.remove());
            updateTotalPrice();
            showPopUp("Cart cleared", "success");
            loadCartCount();
        } catch (e) {
            showPopUp("Error clearing cart", "warning");
            console.error(e);
        }
    } else {
        localStorage.removeItem("guest_cart");
        document.querySelectorAll('.product-cart').forEach(item => item.remove());
        updateTotalPrice();
        showPopUp("Cart cleared (guest)", "success");
        loadCartCount();
    }
}

function incrementQuantityValueCart(button) {
    const container = button.closest('.product-cart');
    const productId = container.dataset.id;
    const stock = parseInt(container.dataset.stock);
    const quantityElement = container.querySelector('.quantity-value');
    let quantity = parseInt(quantityElement.textContent);

    if (quantity < stock) {
        quantity++;
        quantityElement.textContent = quantity;
        updateCartItem(productId, quantity);
        loadCartCount();
    } else {
        showPopUp("Stock limit reached","warning");
    }
}

function decrementQuantityValueCart(button) {
    const container = button.closest('.product-cart');
    const productId = container.dataset.id;
    const quantityElement = container.querySelector('.quantity-value');
    let quantity = parseInt(quantityElement.textContent);

    if (quantity > 1) {
        quantity--;
        quantityElement.textContent = quantity;
        updateCartItem(productId, quantity);
        loadCartCount();
    } else {
        removeProduct(button);
    }
}

function updateTotalPrice() {
    let subtotal = 0;
    const items = document.querySelectorAll('.product-cart');
    items.forEach(item => {
        const price = parseFloat(item.querySelector('.price-value').textContent.replace('$', ''));
        const quantity = parseInt(item.querySelector('.quantity-value').textContent);
        const totalItem = price * quantity;
        item.querySelector('.total-price-value').textContent = `$${totalItem.toFixed(2)}`;
        subtotal += totalItem;
    });

    const deliveryFee = subtotal >= 150 ? 0 : 15;

    const deliveryField = document.querySelector('.delivery-value td');
    if (deliveryField) {
        deliveryField.textContent = deliveryFee === 0 ? "FREE" : `$${deliveryFee.toFixed(2)}`;
    }

    const finalTotal = subtotal + deliveryFee;
    document.querySelectorAll('.order-value td').forEach(td => {
        td.textContent = `$${subtotal.toFixed(2)}`;
    });
    document.querySelectorAll('.total-cart td').forEach(td => {
        td.textContent = `$${finalTotal.toFixed(2)}`;
    });

}

function updateCartCount(count) {
    const countElem = document.getElementById('cart-count');
    countElem.textContent = count > 0 ? count : "";
}

function loadCartCount() {
    if (isLoggedIn === true) {
        fetch('/cart/count', {
            credentials: "include"})
            .then(res => res.json())
            .then(data => {
                updateCartCount(data.count);
            })
            .catch(() => updateCartCount(0));
    } else {
        const cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
        const totalCount = cart.reduce((sum, item) => sum + item.quantity, 0);
        updateCartCount(totalCount);
    }
}

document.addEventListener('DOMContentLoaded', loadCartCount);

function displayGuestCart() {
    const cart = JSON.parse(localStorage.getItem("guest_cart")) || [];
    const container = document.getElementById("products-list-cart");

    if (cart.length === 0) {
        container.innerHTML = `<li><p>Your cart is empty.</p></li>`;
        updateTotalPrice();
        return;
    }

    let html = "";

    cart.forEach(item => {
        const totalPrice = (item.price * item.quantity).toFixed(2);

        html += `
            <li class="product-cart" data-id="${item.id}" data-stock="${item.stock}">
                <article class="product">
                    <div class="product-controls">
                        <div class="product-image">
                            <a href="#" class="product-link">
                                <img src="${item.image}" alt="${item.name}" class="product-image-cart"/>
                            </a>
                        </div>
                        <div class="product-info">
                            <div class="product-name">${item.name}</div>
                            <div class="product-id">ID: ${item.id}</div>
                        </div>
                        <div class="product-details-cart">
                            <dl class="details-cart">
                                <dt class="product-price-cart">Price</dt>
                                <dd><span class="price-value">$${item.price.toFixed(2)}</span></dd>
                                <dt class="product-quantity-cart">Quantity</dt>
                                <dd>
                                    <div class="quantity-buttons">
                                        <button type="button" id="minus" onclick="decrementQuantityValueCart(this)">−</button>
                                        <span class="quantity-value">${item.quantity}</span>
                                        <button type="button" id="plus" onclick="incrementQuantityValueCart(this)">+</button>
                                    </div>
                                </dd>
                                <dt class="product-total-cart">Total</dt>
                                <dd><span class="total-price-value">$${totalPrice}</span></dd>
                            </dl>
                        </div>
                        <div class="quantity-actions">
                            <button type="button" id="remove" onclick="removeProduct(this)">Remove</button>
                        </div>
                    </div>
                </article>
            </li>
        `;
    });

    container.innerHTML = html;
    updateTotalPrice();
}

document.addEventListener("DOMContentLoaded", function() {
    loadCartCount();

    const cartLink = document.getElementById("cart-link");
    if (cartLink) {
        cartLink.addEventListener("click", function(e) {
            e.preventDefault();
            loadCart();
        });
    }
});

async function mergeGuestCartWithDatabaseCart(dbCart = []) {
    const guestCart = JSON.parse(localStorage.getItem("guest_cart")) || [];

    const mergedCartMap = new Map();

    dbCart.forEach(item => {
        mergedCartMap.set(item.id, { ...item });
    });

    guestCart.forEach(item => {
        if (mergedCartMap.has(item.id)) {
            mergedCartMap.get(item.id).quantity += item.quantity;
        } else {
            mergedCartMap.set(item.id, { ...item });
        }
    });

    const mergedCart = Array.from(mergedCartMap.values());

    const csrfToken = getCookieValue('XSRF-TOKEN');

    const response = await fetch('/cart/merge', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(mergedCart)
    });

    if (response.ok) {
        localStorage.removeItem("guest_cart");
        console.log("Cart merged successfully.");
        loadCart();
        loadCartCount();
    } else {
        console.error("Failed to merge cart");
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const guestCart = localStorage.getItem("guest_cart");
    const justLoggedIn = sessionStorage.getItem("justLoggedIn");

    if (guestCart && justLoggedIn) {
        fetch('/cart/json')
            .then(res => res.json())
            .then(async dbCart => {
                await mergeGuestCartWithDatabaseCart(dbCart);
                sessionStorage.removeItem("justLoggedIn");
            });
    }
});