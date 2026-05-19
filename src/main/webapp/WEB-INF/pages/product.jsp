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

<!-- FILTER BAR -->
<section class="filter-bar">

    <input type="text"
           placeholder="Search club or country jerseys..."
           class="search-box">

    <select class="filter">
        <option>All</option>
        <option>Club Jerseys</option>
        <option>Country Jerseys</option>
    </select>

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
                    onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/background.jpeg';"
                >

                <div class="card-content">

                    <h3>${product.productName}</h3>

                    <p class="team-name">${product.teamName}</p>

                    <p class="category">${product.category}</p>

                    <p class="description">
                        ${product.description}
                    </p>

                    <a href="${pageContext.request.contextPath}/variant?action=view&productId=${product.productId}"
                       class="view-btn">
                        View Sizes
                    </a>

                </div>

            </div>

        </c:forEach>

    </div>

</section>

<!-- ================= PAGINATION ================= -->
<section class="pagination">

    <!-- PREV -->
    <c:if test="${currentPage > 1}">
        <a class="page-btn"
           href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">
            Prev
        </a>
    </c:if>

    <!-- PAGE NUMBERS -->
    <c:forEach begin="1" end="${totalPages}" var="page">

        <a class="page-btn ${page == currentPage ? 'active' : ''}"
           href="${pageContext.request.contextPath}/product?page=${page}">
            ${page}
        </a>

    </c:forEach>

    <!-- NEXT -->
    <c:if test="${currentPage < totalPages}">
        <a class="page-btn"
           href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">
            Next
        </a>
    </c:if>

</section>

<jsp:include page="/templates/footer.html"/>

</body>
</html>