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

        <main>
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
                        <img src="${pageContext.request.contextPath}/resources/images/jersey4.jpg" alt="Daniel">
                        <div class="card-content">
                            <h3>Daniel Gurung</h3>
                            <span>Project Coordinator</span>
                            <p>A natural leader who keeps the team focused, organized, and motivated while ensuring every detail aligns with the project vision.</p>
                            <button>Hobby: Traveling</button>
                        </div>
                    </div>

                    <div class="card">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey4.jpg" alt="Nischal">
                        <div class="card-content">
                            <h3>Nischal Pun</h3>
                            <span>UI/UX Designer</span>
                            <p>Creative and detail-oriented, with a strong eye for design and a passion for crafting intuitive user experiences.</p>
                            <button>Hobby: Reading</button>
                        </div>
                    </div>

                    <div class="card">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey3.jpg" alt="Asmit">
                        <div class="card-content">
                            <h3>Asmit Bastola</h3>
                            <span>Prototype Designer</span>
                            <p>Innovative and imaginative, turning ideas into clear visual concepts that balance aesthetics and functionality.</p>
                            <button>Hobby: Gaming</button>
                        </div>
                    </div>

                    <div class="card">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey1.jpg" alt="Aayush">
                        <div class="card-content">
                            <h3>Aayush Bastola</h3>
                            <span>Backend Developer</span>
                            <p>Analytical and dependable, known for solving complex problems and building efficient, robust solutions.</p>
                            <button>Hobby: Football</button>
                        </div>
                    </div>

                    <div class="card single-card">
                        <img src="${pageContext.request.contextPath}/resources/images/jersey2.jpg" alt="Anuma">
                        <div class="card-content">
                            <h3>Anuma Rawal</h3>
                            <span>Full Stack Developer</span>
                            <p>Versatile and dedicated, combining technical expertise with creativity to deliver polished and user-friendly features.</p>
                            <button>Hobby: Photography</button>
                        </div>
                    </div>

                </div>

            </section>
        </main>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>

    </body>
</html>