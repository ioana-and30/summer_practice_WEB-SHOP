<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Curlsie - Register</title>

    <link rel="stylesheet" href="${contextPath}/static/css/registerPage.css" />

</head>

<body>
<header class="header">
    <h1>Create an Account</h1>
</header>

<main>
    <div th:if="${emailError}" class="error">
        <p th:text="${emailError}"></p>
    </div>

    <div class="register-container">
        <form action="${contextPath}/register" id="register-form" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div>
                <label for="firstName">First Name:</label>
                <input type="text" name="firstName" id="firstName" maxlength="30" required oninput="validateFirstName()" />
                <div id="firstName-error" class="error-message"></div>
            </div>

            <div>
                <label for="lastName">Last Name:</label>
                <input type="text" name="lastName" id="lastName" maxlength="30" required oninput="validateLastName()" />
                <div id="lastName-error" class="error-message"></div>
            </div>

            <div>
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" th:th:field="*{email}" required oninput="validateEmail()" />
                <div id="email-error" class="error-message"></div>
            </div>

            <div>
                <label for="username">Username:</label>
                <input type="text" name="username" id="username" maxlength="30" required oninput="validateUsername()" />
                <div id="username-error" class="error-message"></div>
            </div>

            <div>
                <label for="password">Password:</label>
                <input id="password" type="password" value="" name="password" class="form-control" onkeyup="isGood(this.value)" />
                <small class="help-block" id="password-text"></small>

                <ul id="password-rules">
                    <li id="rule-length">✓ At least 8 characters</li>
                    <li id="rule-upper">✓ At least one uppercase letter</li>
                    <li id="rule-lower">✓ At least one lowercase letter</li>
                    <li id="rule-number">✓ At least one number</li>
                    <li id="rule-special">✓ At least one special character ($@!%*#?&)</li>
                </ul>
            </div>

            <div>
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required oninput="checkPasswordMatch()" />
                <div id="match-error" class="error-message"></div>
            </div>

            <div>
                <button type="submit">Register</button>
            </div>

            <div class="popup-message" id="popupMessage">
                <span class="popup-text"></span>
                <button class="popup-close" onclick="closePopup()">&times;</button>
            </div>
        </form>
    </div>
</main>

<script src="${contextPath}/static/script/register.js"></script>

</body>

<footer>
    <p id="footer-text"> &copy; 2025 Bran Ioana-Andreea - CreateQ</p>
</footer>
</html>
