<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty error}">
    <div class="error-message">
        <p>${error}</p>
    </div>
</c:if>

<div class="products-grid">
    <c:forEach items="${products}" var="product">
        <div class="product-card">
            <img src="${pageContext.request.contextPath}/${product.image}" alt="${product.name}" class="product-image"/>
            <h3 class="product-name">${product.name}</h3>
            <p class="product-price">$${product.price}</p>
            <p class="product-description" >${product.description}</p>
            <button class="add-to-cart">Add to cart</button>
        </div>
    </c:forEach>
</div>
