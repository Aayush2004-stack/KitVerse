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
                <a href="${pageContext.request.contextPath}/product" class="btn-primary">Shop Now</a>
            </div>
        </section>


        <section class="highlights">

            <div class="highlight-card">

                <h3>Premium Quality</h3>

                <p>High-quality football kits designed for comfort and performance.</p>

            </div>

            <div class="highlight-card">

                <h3>Latest Jerseys</h3>

                <p>Explore trending club and national team collections.</p>

            </div>

            <div class="highlight-card">

                <h3>Fast Delivery</h3>

                <p>Quick and reliable delivery across Nepal.</p>

            </div>

        </section>


        <!-- FEATURED PRODUCTS -->

        <section class="featured-products">

            <div class="section-header">
                <div>
                    <h2>Featured Jerseys</h2>
                    <p>Represent your favorite club with style.</p>
                </div>
            </div>

            <!-- SLIDER -->
            <div class="slider">

                <div class="slide-track">

                    <div class="slide">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey1.jpg">
                    </div>

                    <div class="slide">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey2.jpg">
                    </div>

                    <div class="slide">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey3.jpg">
                    </div>

                    <div class="slide">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey4.jpg">
                    </div>


                </div>

            </div>

            <!-- BUTTON -->
            <div class="browse-wrapper">
                <a href="${pageContext.request.contextPath}/product" class="browse-btn">
                    Browse More
                </a>
            </div>

        </section>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>

    </body>
</html>