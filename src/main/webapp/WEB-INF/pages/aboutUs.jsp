<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - KitVerse</title>

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/aboutUs.css">
</head>

<body>

<jsp:include page="/templates/navbar.jsp"/>

<section class="about-section">

    <!-- INTRO -->
    <div class="who-we-are">

        <h1>Who We Are</h1>

        <p>
            KitVerse was built for fans who wear their club on their chest.
            We bring premium football jerseys, curated collections, and fast delivery
            so fans can represent their passion everywhere.
        </p>

    </div>

    <h2 class="team-heading">Meet the Team</h2>

    <!-- TEAM -->
    <div class="team-grid">

        <div class="card">
            <img src="https://i.pravatar.cc/100?img=12" alt="">
            <div class="card-content">
                <h3>Daniel Gurung</h3>
                <span>Lead Developer</span>
                <p>Handles architecture, backend systems, and platform stability.</p>
                <button>Hobby: Traveling</button>
            </div>
        </div>

        <div class="card">
            <img src="https://i.pravatar.cc/100?img=15" alt="">
            <div class="card-content">
                <h3>Nischal Pun</h3>
                <span>UI/UX Designer</span>
                <p>Designs clean and user-friendly interfaces for better shopping experience.</p>
                <button>Hobby: Reading</button>
            </div>
        </div>

        <div class="card">
            <img src="https://i.pravatar.cc/100?img=31" alt="">
            <div class="card-content">
                <h3>Asmit Bastola</h3>
                <span>Full Stack Developer</span>
                <p>Builds and connects frontend and backend systems.</p>
                <button>Hobby: Gaming</button>
            </div>
        </div>

        <div class="card">
            <img src="https://i.pravatar.cc/100?img=18" alt="">
            <div class="card-content">
                <h3>Aayush Bastola</h3>
                <span>Product Strategist</span>
                <p>Defines product direction and user experience flow.</p>
                <button>Hobby: Football</button>
            </div>
        </div>

        <div class="card single-card">
            <img src="https://i.pravatar.cc/100?img=20" alt="">
            <div class="card-content">
                <h3>Anuma Rawal</h3>
                <span>Marketing Lead</span>
                <p>Handles branding, marketing, and community engagement.</p>
                <button>Hobby: Photography</button>
            </div>
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
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </div>

        <div>
            <h4>Support</h4>
            <ul>
                <li><a href="#">Shipping</a></li>
                <li><a href="#">Returns</a></li>
            </ul>
        </div>

    </div>

    <div class="footer-bottom">
        <p>© 2026 KitVerse</p>
    </div>
</footer>

</body>
</html>