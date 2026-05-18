<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Contact Us - KitVerse</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactUs.css">
</head>

<body>

<!-- DEFAULT NAVBAR -->
<jsp:include page="/templates/navbar.jsp"/>

<!-- CONTACT SECTION -->
<section class="contact-section">

    <div class="contact-container">

        <!-- FORM -->
        <div class="contact-card">

            <h1>Let's Talk</h1>

            <p>
                Have questions about jerseys, orders, or delivery?
                Send us a message and our team will help you.
            </p>

            <form>

                <div class="row">
                    <input type="text" placeholder="First Name" required>
                    <input type="text" placeholder="Last Name" required>
                </div>

                <input type="email" placeholder="Your Email" required>
                <input type="text" placeholder="Subject">

                <textarea placeholder="Your Message" required></textarea>

                <button type="submit">Send Message</button>

            </form>

        </div>

        <!-- INFO -->
        <div class="contact-card info-card">

            <h2>Get in Touch</h2>

            <p><strong>Email:</strong> support@kitverse.com</p>
            <p><strong>Phone:</strong> +977-98XXXXXXXX</p>
            <p><strong>Location:</strong> Pokhara, Nepal</p>
            <p><strong>Hours:</strong> Sun - Fri (9 AM - 6 PM)</p>

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