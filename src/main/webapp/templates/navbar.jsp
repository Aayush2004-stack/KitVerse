<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="navbar">

    <!-- Logo -->
    <div class="nav-logo">
        <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="Logo">
        KitVerse
    </div>

    <!-- NAV LINKS -->
    <nav class="nav-links">

       

        <!-- ADMIN NAV -->
        <c:if test="${not empty sessionScope.user and sessionScope.user.userType == 'admin'}">
            <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/product?action=admin">Products</a>
            <a href="${pageContext.request.contextPath}/order">Orders</a>
        </c:if>

        <!-- USER NAV -->
        <c:if test="${not empty sessionScope.user and sessionScope.user.userType == 'normal'}">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/product">Shop</a>
            <a href="#">Categories</a>
            <a href="#">About</a>
        </c:if>

        <!-- GUEST NAV -->
                
        <c:if test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <a href="${pageContext.request.contextPath}/product">Shop</a>
            <a href="#">Categories</a>
            <a href="#">About</a>
        </c:if>

        <!-- RIGHT SIDE -->
        <c:choose>

            <c:when test="${not empty sessionScope.user}">
                
                <span class="nav-user">
                    Hi, ${sessionScope.user.fullName}
                </span>

                <a href="${pageContext.request.contextPath}/logout" class="nav-btn">
                    Logout
                </a>

            </c:when>

            <c:otherwise>

                <a href="${pageContext.request.contextPath}/login" class="nav-btn">
                    Login
                </a>

            </c:otherwise>

        </c:choose>

    </nav>

</header>