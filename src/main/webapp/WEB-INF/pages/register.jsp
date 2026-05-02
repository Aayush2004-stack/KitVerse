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
            <p class="subtitle">Join KitVerse to track orders and manage your account.</p>
            <div class="divider"></div>

            <form action="register" method="post">
                <div class="field-row">
                    <div class="field">
                        <label for="firstName">First name</label>
                        <input type="text" name="firstName" id="firstName" placeholder="Asmit"/>
                    </div>
                    <div class="field">
                        <label for="lastName">Last name</label>
                        <input type="text" name="lastName" id="lastName" placeholder="Bastola"/>
                    </div>
                </div>

                <div class="field">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" placeholder="you@example.com"/>
                </div>

                <div class="field">
                    <label for="phone">Phone number</label>
                    <div class="phone-wrap">
                        <span class="phone-prefix">+977</span>
                        <input type="tel" name="phone" id="phone" placeholder="98XXXXXXXX"/>
                    </div>
                </div>

                <div class="field">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="••••••••"/>
                </div>

                <div class="field">
                    <label for="confirmPassword">Confirm password</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="••••••••"/>
                </div>

                <div class="field field-terms">
                    <label class="checkbox-label">
                        <input type="checkbox" name="terms" id="terms"/>
                        <span class="checkmark"></span>
                        <span class="terms-text">
                            I agree to the
                            <a href="#" class="terms-link">Terms of Service</a>
                            and
                            <a href="#" class="terms-link">Privacy Policy</a>
                        </span>
                    </label>
                </div>

                <button class="btn-register" type="submit">Create Account</button>
            </form>

            <p class="login-link">Already have an account? <a href="login.jsp">Sign in</a></p>
        </div>
    </body>
</html>
