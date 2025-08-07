<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 14.07.2025
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div>

    <div class="product-container" data-id="${product_details.id}" data-stock="${product_details.quantity}">
        <div class="product-details-left">
            <img src="${contextPath}/${product_details.image}" alt="${product_details.name}" class="product-image"/>
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
                        <input type="number" id="quantity" class="quantity-text" name="quantity" value="1" min="1" max="${product_details.quantity}" step="1" readonly >
                    </label>
                    <button type="button" class="modify-quantity" onclick="incrementQuantityValue()">+</button>
                </div>

                <button class="add-to-cart" onclick='addToCartFromDetails({
                                            id: ${product_details.id},
                                            name: "${fn:escapeXml(product_details.name)}",
                                            price: ${product_details.price},
                                            image: "${contextPath}/${product_details.image}"}) '>Add to cart
                </button>

            </div>
            <a href="${contextPath}/categories">Continue Shopping</a>
        </div>

        <div class="product-description" >
            <p id="description">DESCRIPTION</p>
            <p>${product_details.longDescription}</p>
        </div>
    </div>

    <div class="popup-message">
        <span class="popup-text"></span>
        <button class="popup-close">&times;</button>
    </div>
</div>
