function validateFirstName() {
    const input = document.getElementById("firstName");
    const error = document.getElementById("firstName-error");
    const value = input.value.trim();

    if (value === "" || value.length < 3) {
        input.classList.add("input-error");
        error.textContent = "First name must be at least 3 characters.";
        return false;
    }
    input.classList.remove("input-error");
    input.classList.add("input-valid");
    error.textContent = "";
    return true;
}

function validateLastName() {
    const input = document.getElementById("lastName");
    const error = document.getElementById("lastName-error");
    const value = input.value.trim();

    if (value === "" || value.length < 3) {
        input.classList.add("input-error");
        error.textContent = "Last name must be at least 3 characters.";
        return false;
    }
    input.classList.remove("input-error");
    input.classList.add("input-valid");
    error.textContent = "";
    return true;
}

async function checkEmailExists(email) {

    const response = await fetch(`/check-email?email=${encodeURIComponent(email)}`);
    const data = await response.json();
    return data.count;
}

async function validateEmail() {
    const input = document.getElementById("email");
    const error = document.getElementById("email-error");
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(input.value.trim())) {
        input.classList.add("input-error");
        error.textContent = "Email format must be: name@example.com";
        return false;
    }

    try {
        const count = await checkEmailExists(input.value.trim());

        if (count >= 2) {
            input.classList.add("input-error");
            error.textContent = "Max 2 users allowed with the same email.";
            return false;
        }

        input.classList.remove("input-error");
        input.classList.add("input-valid");
        error.textContent = "";
        return true;
    } catch (err) {
        return false;
    }
}

async function checkUsernameExists(username) {

    const response = await fetch(`/check-username?username=${encodeURIComponent(username)}`);
    const data = await response.json();
    return data.count;
}

async function validateUsername() {
    const input = document.getElementById("username");
    const error = document.getElementById("username-error");
    const usernameRegex = /^[a-zA-Z0-9_]{3,30}$/;

    if (!usernameRegex.test(input.value.trim())) {
        input.classList.add("input-error");
        error.textContent = "Username must be 3-30 characters, letters/numbers/underscore.";
        return false;
    }
    try {
        const count = await checkUsernameExists(input.value.trim());

        if (count > 0) {
            input.classList.add("input-error");
            error.textContent = "This username is already taken.";
            return false;
        }

        input.classList.remove("input-error");
        input.classList.add("input-valid");
        error.textContent = "";
        return true;

    } catch (err) {
        return false;
    }
}

function isGood(password) {
    const password_strength = document.getElementById("password-text");

    if (password.length === 0) {
        password_strength.innerHTML = "";
        return false;
    }

    const rules = {
        length: password.length >= 8,
        upper: /[A-Z]/.test(password),
        lower: /[a-z]/.test(password),
        number: /[0-9]/.test(password),
        special: /[$@!%*#?&]/.test(password)
    };


    document.getElementById("rule-length").style.color = rules.length ? "green" : "red";
    document.getElementById("rule-upper").style.color = rules.upper ? "green" : "red";
    document.getElementById("rule-lower").style.color = rules.lower ? "green" : "red";
    document.getElementById("rule-number").style.color = rules.number ? "green" : "red";
    document.getElementById("rule-special").style.color = rules.special ? "green" : "red";

    const passed = Object.values(rules).filter(Boolean).length;
    let strength = "";
    switch (passed) {
        case 0:
        case 1:
        case 2:
            strength = "<small class='progress-bar bg-danger' style='width: 40%'>Weak</small>";
            break;
        case 3:
            strength = "<small class='progress-bar bg-warning' style='width: 60%'>Medium</small>";
            break;
        case 4:
            strength = "<small class='progress-bar bg-success' style='width: 100%'>Strong</small>";
            break;

    }
    password_strength.innerHTML = strength;

    return passed >= 3;
}

function checkPasswordMatch() {
    const password = document.getElementById("password").value.trim();
    const confirmInput = document.getElementById("confirmPassword");
    const confirmValue = confirmInput.value.trim();
    const error = document.getElementById("match-error");

    if (!confirmValue) {
        error.textContent = "";
        confirmInput.classList.remove("input-error", "input-valid");
        return false;
    }

    if (confirmValue !== password) {
        error.textContent = "Passwords do not match.";
        confirmInput.classList.add("input-error");
        confirmInput.classList.remove("input-valid");
        return false;
    } else {
        error.textContent = "";
        confirmInput.classList.remove("input-error");
        confirmInput.classList.add("input-valid");
        return true;
    }
}

async function validateForm() {

    const password = document.getElementById("password").value.trim();

    const first = validateFirstName();
    const last = validateLastName();
    const email = await validateEmail();
    const user = validateUsername();
    const strengthOk = isGood(password)
    const match = checkPasswordMatch();

    return first && last && email && user && strengthOk && match;
}

document.getElementById("register-form").addEventListener("submit", async function(event) {
    event.preventDefault();

    const isValid = await validateForm();
    if (isValid) {
        this.submit();
    }
});