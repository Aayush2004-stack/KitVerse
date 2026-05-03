<%-- 
    Document   : register
    Created on : May 2, 2026
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>KitVerse – Create Account</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    </head>
    <body>
        <div class="card">
            <div class="brand">
                <div class="logo-icon">
                    <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="KitVerse Logo"/>
                </div>
                <span class="brand-name">KitVerse</span>
            </div>
            <h1>Create account</h1>
            <p class="subtitle">Join KitVerse to Buy, Wear, and Represent premium jersey.</p>
            <div class="divider"></div>

            <form action="register" method="post">
                <div class="field-row">
                    <div class="field">
                        <label for="firstName">First name</label>
                        <input type="text" name="firstName"

                               value="${firstName}" required/>

                        <p style="color:red;">${erFirst}</p>                    
                    </div>
                    <div class="field">
                        <label for="lastName">Last name</label>
                        <input type="text" name="lastName"
                               value="${lastName}" required/>

                        <p style="color:red;">${erLast}</p>
                    </div>
                </div>

                <div class="field">
                    <label for="email">Email</label>
                    <input type="email" name="email"
                           value="${email}" required/>

                    <p style="color:red;">${erEmail}</p>
                </div>

                <div class="field">
                    <label for="phone">Phone number</label>
                    <div class="phone-wrap">
                        <span class="phone-prefix">+977</span>
                        <input type="number" name="phone"
                               value="${phone}" required/>

                        <p style="color:red;">${erPhone}</p>
                    </div>
                </div>

                <div class="field">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="••••••••" required/>
                    <p style="color:red;">${erPass}</p>
                </div>

                <div class="field">
                    <label for="confirmPassword">Confirm password</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="••••••••" required/>
                    <p style="color:red;">${erConfirm}</p>
                </div>


                <button class="btn-register" type="submit">Create Account</button>
            </form>
            <p style='margin:0px;color:red; display: ${not empty error ? "block" : "none"}'>${error}</p>


            <p class="login-link">Already have an account? <a href="${pageContext.request.contextPath}/login">Sign in</a></p>
        </div>
    </body>
</html>
