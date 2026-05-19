<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="navbar">

    <!-- LOGO -->
    <div class="nav-logo">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="Logo"></a>
        KitVerse

    </div>

    <!-- NAVIGATION -->
    <nav class="nav-links">

        <!-- ADMIN NAV -->
        <c:if test="${not empty sessionScope.user and sessionScope.user.userType == 'admin'}">

            <div class="admin-badge">
                ADMIN PANEL
            </div>

            <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/product?action=admin">Products</a>
            <a href="${pageContext.request.contextPath}/admin/orders">Manage Orders</a>

            <!-- ADMIN DROPDOWN -->
            <div class="profile-dropdown">

                <img
                    src="${pageContext.request.contextPath}/resources/images/default-user.png"
                    class="profile-icon"
                    alt="Admin">

                <div class="dropdown-menu">
                    <a href="#">Profile</a>
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </div>

            </div>

        </c:if>

        <!--NORMAL USER NAV -->
        <c:if test="${not empty sessionScope.user and sessionScope.user.userType == 'normal'}">

            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/product">Shop</a>
            <a href="${pageContext.request.contextPath}/cart">Cart</a>
            <a href="${pageContext.request.contextPath}/contactUs">Contact Us</a>
            <a href="${pageContext.request.contextPath}/aboutUs">About Us</a>

            <!-- USER DROPDOWN -->
            <div class="profile-dropdown">

                <img
                    src="${pageContext.request.contextPath}/resources/images/default-user.png"
                    class="profile-icon"
                    alt="User">

                <div class="dropdown-menu">
                    <a href="#">Profile</a>
                    <a href="#">My Orders</a>
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </div>

            </div>

        </c:if>

        <!-- GUEST NAV -->
        <c:if test="${empty sessionScope.user}">

            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/product">Shop</a>
            <a href="${pageContext.request.contextPath}/contactUs">Contact Us</a>
            <a href="${pageContext.request.contextPath}/aboutUs">About Us</a>

            <a href="${pageContext.request.contextPath}/login" class="nav-btn">
                Login
            </a>

        </c:if>

    </nav>

</header>