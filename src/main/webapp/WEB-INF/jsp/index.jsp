<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 07.07.2025
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Curlsie</title>
    <link rel="stylesheet" href="${contextPath}/static/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/categoryPage.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/productPage.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/cartPage.css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

</head>
<body>

<header>
    <c:if test="${not empty error}">
        <div class="error-message">
            <p>${error}</p>
        </div>
    </c:if>
    <nav class="navbar">
        <ul class="nav-left" id="navbar">
            <c:forEach items="${categories}" var="category">
                <li>
                    <a href="#" class="category-link" data-id="${category.id}">
                            ${category.name}
                    </a>
                </li>
            </c:forEach>
        </ul>

        <div class="nav-center">
            <img src="${contextPath}/images/logo.png" alt="Logo" class="logo">
        </div>

        <div class="nav-right">
            <a href="/account">Account</a>
            <a href="#" id="cart-link">
                <i class="fa-solid fa-cart-shopping" style="--fa-primary-color: #5a4d2b; --fa-secondary-color: #cba952;"></i>
                <span id="cart-count"></span>
            </a>
        </div>
    </nav>
</header>

<main>
   <div id="products-container">

   </div>
</main>

<script src="${contextPath}/static/script/categories.js"></script>
<script src="${contextPath}/static/script/product_details.js"></script>
<script src="${contextPath}/static/script/cart.js"></script>

</body>

<footer>
    <p id="footer-text"> &copy; 2025 Bran Ioana-Andreea - CreateQ</p>
</footer>
</html>
