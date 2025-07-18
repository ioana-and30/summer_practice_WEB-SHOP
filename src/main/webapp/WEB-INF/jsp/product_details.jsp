<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 14.07.2025
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <div class="product-container" data-id="${product_details.id}">
        <div class="product-details-left">
            <img src="${pageContext.request.contextPath}/${product_details.image}" alt="${product_details.name}" class="product-image"/>
        </div>

        <div class="product-details-right">
            <h3 class="product-name">${product_details.name}</h3>
            <p id="stock-status" class="in-stock">IN STOCK</p>
            <input type="hidden" id="productQuantity" value="${product_details.quantity}" />

            <div class="price-box">
                <p class="product-price">$${product_details.price}</p>

                <div class="quantity-input">
                    <button type="button" class="modify-quantity"  onclick="decrementQuantityValue()">-</button>
                    <label for="quantity">
                        <input type="number" id="quantity" class="quantity-text" name="quantity" value="1" min="1" max="${product_details.quantity}" step="1" >
                    </label>
                    <button type="button" class="modify-quantity" onclick="incrementQuantityValue()">+</button>
                    <div id="quantity-popup" class="quantity-popup"></div>
                </div>

                <button type="submit" class="add-to-cart" id="add-to-cart">Add to cart</button>
            </div>
            <a href="${pageContext.request.contextPath}/categories">Continue Shopping</a>
        </div>

        <div class="product-description" >
            <p id="description">DESCRIPTION</p>
            <p>${product_details.longDescription}</p>
        </div>
    </div>

</div>
