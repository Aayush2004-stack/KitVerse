<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>${product.productName}</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/main.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/productVariant.css">
    </head>

    <body>

        <jsp:include page="/templates/navbar.jsp"/>

        <div class="product-page">

            <!-- LEFT SIDE -->
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

                <p class="team-name">
                    ${product.teamName}
                </p>

                <p class="description">
                    ${product.description}
                </p>

                <!-- PRICE -->
                <h2 class="price">
                    Rs. <span id="priceText">${selectedVariant.sellingPrice}</span>
                </h2>

                <form method="post"
                      action="${pageContext.request.contextPath}/order?action=checkout">

                    <!-- PRODUCT -->
                    <input type="hidden"
                           name="productId"
                           value="${product.productId}">



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
                                           
                                           <c:if test="${selectedVariant.variantId == v.variantId}">
                                               checked
                                           </c:if>

                                           <c:if test="${v.stock == 0}">
                                               disabled
                                           </c:if>


                                           onchange="updatePrice(this)"

                                           required>

                                    <div class="size-name">
                                        ${v.size}
                                    </div>

                                    <span class="stock">

                                        <c:choose>

                                            <c:when test="${v.stock > 0}">
                                                In Stock
                                            </c:when>

                                            <c:otherwise>
                                                Out of Stock
                                            </c:otherwise>

                                        </c:choose>

                                    </span>

                                </label>

                            </c:forEach>

                        </div>

                    </div>

                    <!-- CUSTOMIZATION -->
                    <div class="section">

                        <h3>Customize Jersey</h3>

                        <input type="text"
                               name="playerName"
                               placeholder="Player Name">

                        <input type="number"
                               name="playerNo"
                               placeholder="Player Number">

                    </div>

                    <!-- QUANTITY -->
                    <div class="section">

                        <h3>Quantity</h3>

                        <input type="number"
                               name="quantity"
                               min="1"
                               value="1"
                               required>

                    </div>

                    <!-- BUTTON -->
                    <button type="submit" class="buy-btn">
                        Buy Now
                    </button>

                </form>

            </div>



        </div>

        <script>

            function updatePrice(radio) {

                document.getElementById("priceText").innerText =
                        radio.dataset.price;

            }

        </script>

    </body>

</html>