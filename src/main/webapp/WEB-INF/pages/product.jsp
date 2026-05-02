<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KitVerse - Products</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">

    <div class="nav-logo">
        <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="KitVerse Logo" width="30">
        KitVerse
    </div>

    <nav class="nav-links">
        <a href="#">Shop</a>
        <a href="#">Categories</a>
        <a href="#">About</a>
        <a href="${pageContext.request.contextPath}/logout" class="nav-login">Logout</a>
    </nav>

</header>

<!-- PRODUCT GRID -->
<section class="product-list">
    <div class="grid">

        <!-- CARD 1 -->
        <div class="card">
            <input type="checkbox" id="p1">
            <img src="${pageContext.request.contextPath}/resources/products/aayush.png" class="product-img" alt="Barcelona Kit">
            <label for="p1" class="pick-btn"></label>
            <h3>Rs. 2500</h3>
            <p>Barcelona Kit</p>
        </div>

        <!-- CARD 2 -->
        <div class="card">
            <input type="checkbox" id="p2">
            <img src="${pageContext.request.contextPath}/resources/products/asmit.png" class="product-img" alt="Real Madrid Kit">
            <label for="p2" class="pick-btn"></label>
            <h3>Rs. 2700</h3>
            <p>Real Madrid Kit</p>
        </div>

        <!-- CARD 3 -->
        <div class="card">
            <input type="checkbox" id="p3">
            <img src="${pageContext.request.contextPath}/resources/products/nika.jpeg" class="product-img" alt="Manchester United">
            <label for="p3" class="pick-btn"></label>
            <h3>Rs. 2600</h3>
            <p>Manchester United</p>
        </div>

        <!-- CARD 4 -->
        <div class="card">
            <input type="checkbox" id="p4">
            <img src="${pageContext.request.contextPath}/resources/products/anuma.png" class="product-img" alt="PSG Kit">
            <label for="p4" class="pick-btn"></label>
            <h3>Rs. 2550</h3>
            <p>PSG Kit</p>
        </div>

        <!-- CARD 5 -->
        <div class="card">
            <input type="checkbox" id="p5">
            <img src="${pageContext.request.contextPath}/resources/products/daniel.png" class="product-img" alt="Liverpool Kit">
            <label for="p5" class="pick-btn"></label>
            <h3>Rs. 2500</h3>
            <p>Liverpool Kit</p>
        </div>

        <!-- CARD 6 -->
        <div class="card">
            <input type="checkbox" id="p6">
            <img src="${pageContext.request.contextPath}/resources/products/aayush.png" class="product-img" alt="Chelsea Kit">
            <label for="p6" class="pick-btn"></label>
            <h3>Rs. 2400</h3>
            <p>Chelsea Kit</p>
        </div>

        <!-- CARD 7 -->
        <div class="card">
            <input type="checkbox" id="p7">
            <img src="${pageContext.request.contextPath}/resources/products/nika.jpeg" class="product-img" alt="Bayern Munich">
            <label for="p7" class="pick-btn"></label>
            <h3>Rs. 2600</h3>
            <p>Bayern Munich</p>
        </div>

        <!-- CARD 8 -->
        <div class="card">
            <input type="checkbox" id="p8">
            <img src="${pageContext.request.contextPath}/resources/products/anuma.png" class="product-img" alt="AC Milan Kit">
            <label for="p8" class="pick-btn"></label>
            <h3>Rs. 2500</h3>
            <p>AC Milan Kit</p>
        </div>

        <!-- CARD 9 -->
        <div class="card">
            <input type="checkbox" id="p9">
            <img src="${pageContext.request.contextPath}/resources/products/daniel.png" class="product-img" alt="Juventus Kit">
            <label for="p9" class="pick-btn"></label>
            <h3>Rs. 2550</h3>
            <p>Juventus Kit</p>
        </div>

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
