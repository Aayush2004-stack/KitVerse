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


            <form action="login" method="post">


                <!-- Email Field -->
                <div class="field">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" placeholder="you@example.com"  required/>
                </div>

                <!-- Password Field -->
                <div class="field">
                    <label for="password">Password</label>
                    <div class="input-wrap">
                        <input type="password" name="password" id="password"  placeholder="••••••••" required />

                    </div>
                </div>

                <button class="btn-login" type="submit">Login</button>
            </form>
            <p style='margin:0px;color:red; display: ${not empty error ? "block" : "none"}'>${error}</p>

            <p class="register">Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></p>
        </div>
    </body>
</html>

