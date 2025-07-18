<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty error}">
    <div class="error-message">
        <p>${error}</p>
    </div>
</c:if>

<div class="products-section">
    <div class="sort-bar">
        <form id="sortForm" method="get" action="${pageContext.request.contextPath}/home">
            <input type="hidden" name="categoryId" value="${param.categoryId}"/>
            <label for="sortSelect"></label>
            <select name="sort" id="sortSelect" onchange="this.form.submit()">
                <option value="" disabled selected >Sort by price</option>
                <option value="none" >No sort</option>
                <option value="asc" ${param.sort == 'asc' ? 'selected' : ''}>Low to High</option>
                <option value="desc" ${param.sort == 'desc' ? 'selected' : ''}>High to Low</option>
        </select>
        </form>
    </div>

    <div class="products-grid">
        <c:forEach items="${products}" var="product">
            <div class="product-card" data-product-id="${product.id}" onclick="loadProduct(${product.id})">
                <img src="${pageContext.request.contextPath}/${product.image}" alt="${product.name}" class="product-image"/>
                <h3 class="product-name">${product.name}</h3>
                <p class="product-price">$${product.price}</p>
                <p class="product-description">${product.shortDescription}</p>
                <button class="add-to-cart">Add to cart</button>
            </div>
        </c:forEach>
    </div>
</div>


