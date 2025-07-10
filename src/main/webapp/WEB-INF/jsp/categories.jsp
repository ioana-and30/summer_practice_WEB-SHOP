<%--
  Created by IntelliJ IDEA.
  User: BRAN IOANA ANDREEA
  Date: 07.07.2025
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css" />

</head>
<body>

<header>
    <nav>
        <ul id="navbar">
            <c:forEach items="${categories}" var="category">
                <li>
                    <a href="#" class="category-link" data-id="${category.id}">
                            ${category.name}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</header>

<main>
   <div id="products-container">

   </div>
</main>

<script src="${pageContext.request.contextPath}/static/script/categories.js"></script>

</body>

<footer>
    <p id="footer-text">Bran Ioana-Andreea - CreateQ</p>
</footer>
</html>
