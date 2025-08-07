<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="isLoggedIn" value="${not empty sessionScope.loggedUser}" />
<script>
    const isLoggedIn = ${isLoggedIn};
</script>


<div class="cart-page">
    <section class="cart-section">
        <div class="cart-container">
            <div class="cart-id">Cart ID:
                <c:choose>
                    <c:when test="${isLoggedIn}">
                        ${cart.id}
                    </c:when>
                    <c:otherwise>
                        Anonymous Cart
                    </c:otherwise>
                </c:choose>
            </div>
            <ul class="products-list-cart" id="products-list-cart">
                <c:choose>
                    <c:when test="${isLoggedIn}">
                        <c:forEach var="item" items="${cart.items}">
                            <li class="product-cart" data-id="${item.product.id}" data-stock="${item.product.quantity}">
                                <article class="product">
                                    <div class="product-controls">
                                        <div class="product-image">
                                            <a href="#" class="product-link">
                                                <img src="${item.product.image}" alt="${item.product.name}" class="product-image-cart"/>
                                            </a>
                                        </div>
                                        <div class="product-info">
                                            <div class="product-name">${item.product.name}</div>
                                            <div class="product-id">ID: ${item.product.id}</div>
                                        </div>
                                        <div class="product-details-cart">
                                            <dl class="details-cart">
                                                <dt class="product-price-cart">Price</dt>
                                                <dd><span class="price-value">$${item.product.price}</span></dd>
                                                <dt class="product-quantity-cart">Quantity</dt>
                                                <dd>
                                                    <div class="quantity-buttons">
                                                        <button type="button" id="minus" onclick="decrementQuantityValueCart(this)">−</button>
                                                        <span class="quantity-value">${item.quantity}</span>
                                                        <button type="button" id="plus" onclick="incrementQuantityValueCart(this)">+</button>
                                                    </div>
                                                </dd>
                                                <dt class="product-total-cart">Total</dt>
                                                <dd><span class="total-price-value">$${item.product.price * item.quantity}</span></dd>
                                            </dl>
                                        </div>
                                        <div class="quantity-actions">
                                            <button type="button" id="remove" onclick="removeProduct(this)">Remove</button>
                                        </div>
                                    </div>
                                </article>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <script>
                            document.addEventListener('DOMContentLoaded', function () {
                                displayGuestCart();
                            });
                        </script>
                    </c:otherwise>
                </c:choose>
            </ul>

            <div class="cart-summary">
                <table class="price-summary">
                    <tbody>
                    <tr class="order-value">
                        <th>Subtotal</th>
                        <td class="subtotal-value">
                            <c:choose>
                                <c:when test="${isLoggedIn}">
                                    $${cart.total}
                                </c:when>
                                <c:otherwise>
                                    $0.00
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr class="delivery-value">
                        <th>Delivery fee</th>
                        <td class="delivery-fee-value">$0.00</td>
                    </tr>
                    </tbody>
                    <tfoot aria-live="polite" class="total-cart">
                    <tr>
                        <th>TOTAL</th>
                        <td class="final-total-value">
                            <c:choose>
                                <c:when test="${isLoggedIn}">
                                    $${cart.total}
                                </c:when>
                                <c:otherwise>
                                    $0.00
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    </tfoot>
                </table>
                <button id="clear-cart-btn" class="clear-cart-button" onclick="clearCart()">CLEAR</button>
                <a type="button" class="checkout-button" id="checkout-button">CONTINUE TO CHECKOUT</a>
                <a class="continue-shopping-button" id="continue-shopping-button" href="${contextPath}/categories" >CONTINUE SHOPPING</a>
            </div>
        </div>
    </section>
</div>

<div id="confirm-modal" style="display:none;">
    <div class="confirm-content">
        <p id="confirm-text">Are you sure?</p>
        <div class="confirm-buttons">
            <button id="modal-yes">Yes</button>
            <button id="modal-no">No</button>
        </div>
    </div>
</div>

<div class="popup-message" style="display:none;">
    <div class="popup-text"></div>
    <span class="popup-close">×</span>
</div>

<script src="${contextPath}/static/script/cart.js"></script>
