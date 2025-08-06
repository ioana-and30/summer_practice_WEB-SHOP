<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 17.07.2025
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="cart-page">
    <section class="cart-section">
        <div class="cart-container">
            <div class="cart-id">Cart ID: </div>
            <ul class="products-list-cart">
                <li class="product-cart" style="display:none" data-id="" data-stock="${product.quantity}">
                    <article class="product">
                        <div class="product-controls">
                            <div class="product-image">
                                <a href="#" class="product-link"><img src="" alt="Product Image" class="product-image-cart"></a>
                            </div>
                            <div class="product-info">
                                <div class="product-name"></div>
                                <div class="product-id"></div>
                            </div>
                            <div class="product-details-cart">
                                <dl class="details-cart">
                                    <dt class="product-price-cart">Price</dt>
                                    <dd><span class="price-value"></span></dd>
                                    <dt class="product-quantity-cart">Quantity</dt>
                                    <dd><span class="quantity-value" id="cart-quantity-value"></span></dd>
                                    <dt class="product-total-cart">Total</dt>
                                    <dd><span class="total-price-value"></span></dd>
                                </dl>
                            </div>
                            <div class="quantity-actions">
                                <div class="quantity-input-cart">
                                    <button type="button" class="modify-quantity-cart"  onclick="decrementQuantityValueCart(this)">-</button>
                                    <button type="button" class="modify-quantity-cart" onclick="incrementQuantityValueCart(this)">+</button>
                                    <div class="popup-message">
                                        <span class="popup-text"></span>
                                        <button class="popup-close">&times;</button>
                                    </div>
                                </div>
                                <button type="button" class="remove-product-button" onclick="removeProduct(this)">Remove</button>
                            </div>
                        </div>
                    </article>
                </li>
            </ul>
        </div>
    </section>
    <div class="cart-summary">
        <table class="price-summary">
            <tbody>
            <tr class="order-value">
                <th>Order Value</th>
                <td>value</td>
            </tr>
            <tr class="delivery-fee">
                <th>Delivery fee</th>
                <td>value</td>
            </tr>
            </tbody>
            <tfoot aria-live="polite" class="total-cart">
                <tr class="total-cart">
                    <th>TOTAL</th>
                    <td>value</td>
                </tr>
            </tfoot>
        </table>

        <button id="clear-cart-btn" class="clear-cart-button" onclick="clearCart()">CLEAR</button>
        <a type="button" class="checkout-button" id="checkout-button">CONTINUE TO CHECKOUT</a>
        <a class="continue-shopping-button" id="continue-shopping-button" href="${contextPath}/categories" >CONTINUE SHOPPING</a>
    </div>
    <div id="confirm-modal">
        <div class="confirm-content">
            <p id="confirm-text">Are you sure?</p>
            <div class="confirm-buttons">
                <button id="modal-yes">Yes</button>
                <button id="modal-no">No</button>
            </div>
        </div>
    </div>
</div>
