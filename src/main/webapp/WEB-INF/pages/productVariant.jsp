<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${product.productName}</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productVariant.css">
    </head>

    <body>

        <jsp:include page="/templates/navbar.jsp"/>

        <div class="product-page">

            <!-- LEFT -->
            <div class="product-left">

                <img class="main-image"
                     src="${pageContext.request.contextPath}/${product.imagePath}"
                     alt="${product.productName}"
                     onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/background.jpeg';"
                     >



            </div>

            <!-- CENTER -->
            <div class="product-center">

                <h1>${product.productName}</h1>

                <p class="team-name">${product.teamName}</p>



                <p class="description">${product.description}</p>

                <h2 class="price">
                    Rs. <span id="priceText">${selectedVariant.sellingPrice}</span>
                </h2>

                <form method="post"
                      action="${pageContext.request.contextPath}/order?action=checkout">

                    <input type="hidden" name="productId" value="${product.productId}">

                    <!-- SIZE -->
                    <div class="section">
                        <h3>Select Size</h3>

                        <div class="options">

                            <c:forEach var="v" items="${variants}">

                                <label class="option-card">

                                    <input type="radio"
                                           name="variantId"
                                           value="${v.variantId}"
                                           data-price="${v.sellingPrice}"
                                           onchange="updatePrice(this)"
                                           required
                                           <c:if test="${selectedVariant.variantId == v.variantId}">checked</c:if>
                                           <c:if test="${v.stock == 0}">disabled</c:if>>

                                           <div class="size-name">${v.size}</div>

                                    <span class="stock">
                                        <c:choose>
                                            <c:when test="${v.stock > 0}">In Stock</c:when>
                                            <c:otherwise>Out of Stock</c:otherwise>
                                        </c:choose>
                                    </span>

                                </label>

                            </c:forEach>

                        </div>
                    </div>

                    <!-- CUSTOM -->
                    <div class="section">
                        <h3>Customize Jersey</h3>

                        <input type="text" name="playerName" placeholder="Player Name">
                        <input type="number" name="playerNo" placeholder="Player Number">
                    </div>

                    <!-- QTY -->
                    <div class="section">
                        <h3>Quantity</h3>

                        <input type="number" name="quantity" min="1" value="1" required>
                    </div>
                    
                    <!-- ERROR MESSAGE -->
                    <c:if test="${not empty error}">
                        <div class="error-box">
                            ${error}
                        </div>
                    </c:if>

                    <!-- BUTTONS -->
                    <button type="submit"
                            formaction="${pageContext.request.contextPath}/cart?action=add"
                            class="cart-btn">
                        Add to Cart
                    </button>

                    <button type="submit" class="buy-btn">
                        Buy Now
                    </button>

                </form>

            </div>

            <!-- RIGHT -->
            <div class="product-right">

                <div class="info-card">
                    <h3>Why Buy From Us?</h3>
                    <ul>
                        <li>✔ Premium quality jerseys</li>
                        <li>✔ Authentic club designs</li>
                        <li>✔ Fast delivery in Nepal</li>
                        <li>✔ Easy return policy</li>
                    </ul>
                </div>

                <div class="info-card">
                    <h3>Care Instructions</h3>
                    <p>Wash inside out with cold water. Avoid ironing on print.</p>
                </div>

                <div class="info-card">
                    <h3>Delivery Info</h3>
                    <p>Standard delivery: 2–4 days<br>Cash on delivery available</p>
                </div>

            </div>

        </div>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>
        <script>
            function updatePrice(radio) {
                document.getElementById("priceText").innerText = radio.dataset.price;
            }
        </script>

    </body>
</html>