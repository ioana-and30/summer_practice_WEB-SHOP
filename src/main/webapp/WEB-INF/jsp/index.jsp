<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 07.07.2025
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="isLoggedIn" value="${not empty sessionScope.loggedUser}" />


<html>
<head>
    <title>Curlsie</title>
    <link rel="stylesheet" href="${contextPath}/static/css/common.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/categoryPage.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/productPage.css" />
    <link rel="stylesheet" href="${contextPath}/static/css/cartPage.css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">

</head>

<c:choose>
<c:when test="${not empty sessionScope.loggedUser}">
<body data-user-id="${sessionScope.loggedUser.id}">
</c:when>
<c:otherwise>
<body>
</c:otherwise>
</c:choose>

<header>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <script>
        window.csrfToken = '${_csrf.token}';
        window.csrfHeaderName = '${_csrf.headerName}';
    </script>

    <sec:csrfInput/>

    <c:if test="${not empty error}">
        <div class="error-message">
            <p>${error}</p>
        </div>
    </c:if>

    <script>
        const isLoggedIn = ${isLoggedIn};
    </script>

    <nav class="navbar">
        <ul class="nav-left" id="navbar" >
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
            <sec:authorize access="isAuthenticated()">
                <div class="user-logout-wrapper">
                    <span>Hello, <c:out value="${sessionScope.loggedUser.username}" />!</span>
                    <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button type="submit" id="logout-button" title="Logout" aria-label="Logout">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="#c9a227" viewBox="0 0 24 24" width="24" height="24">
                                <path d="M16 13v-2H7V8l-5 4 5 4v-3zM20 3h-8v2h8v14h-8v2h8a2 2 0 0 0 2-2V5a2 2 0 0 0-2-2z"></path>
                            </svg>
                        </button>
                    </form>
                </div>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <a href="${pageContext.request.contextPath}/login">Login</a>
            </sec:authorize>

            <a href="${contextPath}/cart" id="cart-link">
                <i class="fa-solid fa-cart-shopping" style="--fa-primary-color: #5a4d2b; --fa-secondary-color: #cba952;"></i>
                <span id="cart-count"></span>
            </a>
        </div>
    </nav>
</header>

<main>
   <div id="products-container">

   </div>
    <div id="cart-container" style="display:none;"></div>
</main>

<script src="${contextPath}/static/script/categories.js"></script>
<script src="${contextPath}/static/script/product_details.js"></script>
<script src="${contextPath}/static/script/cart.js"></script>

</body>

<footer>
    <p id="footer-text"> &copy; 2025 Bran Ioana-Andreea - CreateQ</p>
</footer>
</html>
