<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Curlsie - Login</title>

    <link rel="stylesheet" href="${contextPath}/static/css/loginPage.css" />

</head>

<body>
<header class="header">
    <h1>Login</h1>
</header>

<main>
<div class="login-container">
    <form id="login-form" action="${contextPath}/login" method="post" autocomplete="off">
        <div>
            <label>Username:</label>
            <label>
                <input type="text" name="username" required autocomplete="off" />
            </label>
        </div>
        <div>
            <label>Password:</label>
            <label>
                <input type="password" name="password" required autocomplete="new-password" />
            </label>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div>
            <button type="submit">Log in</button>
        </div>

        <div class="register-form">
            <a href="${pageContext.request.contextPath}/register">Don`t have an account? Create new account</a>
        </div>

        <c:if test="${param.error != null}">
            <div style="color: red;">Invalid username or password.</div>
        </c:if>
    </form>
</div>

</main>
</body>

<script>
    document.querySelector("form").addEventListener("submit", function () {
        sessionStorage.setItem("justLoggedIn", "true");
    });
</script>
<footer>
    <p id="footer-text"> &copy; 2025 Bran Ioana-Andreea - CreateQ</p>
</footer>
</html>