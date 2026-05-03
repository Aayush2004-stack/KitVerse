<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KitVerse</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>

<body>


<jsp:include page="/templates/navbar.jsp"/>


<section class="hero">
    <div class="hero-text">
        <h1>Buy. Wear. Represent.</h1>
        <p>Football jerseys for fans who live and breathe the game.</p>
        <a href="#" class="btn-primary">Shop Now</a>
    </div>
</section>


<section class="categories">
    <h2>Explore Categories</h2>

    <div class="category-grid">
        <a href="#" class="card">Club Jerseys</a>
        <a href="#" class="card">National Jerseys</a>
    </div>
</section>


<section class="products">
    <h2>Featured Jerseys</h2>

    <div class="product-grid">

        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="Barcelona">
            <h3>Barcelona Home Kit</h3>
            <p>Rs. 2500</p>
            <a href="#">Add to Cart</a>
        </div>

        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="Real Madrid">
            <h3>Real Madrid Kit</h3>
            <p>Rs. 2700</p>
            <a href="#">Add to Cart</a>
        </div>

        <div class="product-card">
            <img src="${pageContext.request.contextPath}/resources/logo/logo.png" alt="Man United">
            <h3>Manchester United Kit</h3>
            <p>Rs. 2600</p>
            <a href="#">Add to Cart</a>
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