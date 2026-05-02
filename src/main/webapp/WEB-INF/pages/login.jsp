<%-- 
    Document   : login
    Created on : May 1, 2026, 3:45:31 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>KitVerse – Sign In</title>
        <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600&display=swap" rel="stylesheet"/>
        <link rel="stylesheet" href="login.css"/><link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    </head>
    <body>

        <div class="card">

            <!-- Brand -->
            <div class="brand">
                <div class="logo-icon">
                    <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="KitVerse Logo"/>
                </div>
                <span class="brand-name">KitVerse</span>
            </div>

            <h1>Sign in</h1>
            <p class="subtitle">Welcome back. Manage your orders and account.</p>
            <div class="divider"></div>

            <!-- Email Field -->
            <div class="field">
                <label for="email">Email</label>
                <input type="email" id="email" placeholder="you@example.com" autocomplete="email"/>
            </div>

            <!-- Password Field -->
            <div class="field">
                <label for="password">Password</label>
                <div class="input-wrap">
                    <input type="password" id="password" class="pw-field" placeholder="••••••••" autocomplete="current-password"/>
                    <button class="toggle-pw" type="button" onclick="togglePassword(this)" aria-label="Toggle password visibility">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                        <circle cx="12" cy="12" r="3"/>
                        </svg>
                    </button>
                </div>
            </div>

            <button class="btn-login">Login</button>

            <p class="footer">Don't have an account? <a href="#">Register</a></p>
        </div>
    </body>
</html>

