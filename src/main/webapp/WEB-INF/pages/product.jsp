<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KitVerse - Products</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

    <jsp:include page="/templates/navbar.jsp"/>

    <!-- PAGE TITLE -->
    <section class="page-title">
        <h1>Football Jerseys</h1>
        <p>Explore premium football kits from top clubs around the world.</p>
    </section>

    <!-- PRODUCT GRID -->
    <section class="product-list">
        <div class="grid">

            <c:forEach var="product" items="${products}">

                <div class="card">

                    <img
                        src="${pageContext.request.contextPath}/${product.imagePath}"
                        class="product-img"
                        alt="${product.productName}"
                    >

                    <div class="card-content">

                        <h3>${product.productName}</h3>

                        <p class="team-name">${product.teamName}</p>

                        <p class="category">${product.category}</p>

                        <p class="description">
                            ${product.description}
                        </p>

                        <a
                            href="${pageContext.request.contextPath}/variant?action=view&productId=${product.productId}"
                            class="view-btn"
                        >
                            View Sizes
                        </a>

                    </div>

                </div>

            </c:forEach>

        </div>
    </section>

    <!-- FOOTER -->
    <footer class="footer">

        <div class="footer-container">

            <div>
                <h3>KitVerse</h3>
                <p>Premium football jerseys designed for true fans.</p>
            </div>

            <div>
                <h4>Quick Links</h4>
                <ul>
                    <li><a href="#">Shop</a></li>
                    <li><a href="#">Categories</a></li>
                    <li><a href="#">About</a></li>
                </ul>
            </div>

            <div>
                <h4>Support</h4>
                <ul>
                    <li><a href="#">Shipping</a></li>
                    <li><a href="#">Returns</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>

        </div>

        <div class="footer-bottom">
            <p>© 2026 KitVerse</p>
        </div>

    </footer>

</body>
</html>