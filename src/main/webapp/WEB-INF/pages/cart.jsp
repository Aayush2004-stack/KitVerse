<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Your Cart - KitVerse</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    </head>

    <body>

        <jsp:include page="/templates/navbar.jsp"/>

        <main>

            <!-- HEADER -->
            <section class="cart-header">
                <h1>Your Cart</h1>
                <p>Review your selected jerseys before checkout</p>
            </section>

            <section class="cart-container">

                <c:if test="${empty cartItems}">
                    <div class="empty-cart">
                        <h2>Your cart is empty</h2>
                        <p>Add some jerseys to continue shopping</p>
                    </div>
                </c:if>

                <c:if test="${not empty cartItems}">

                    <form id="cartForm"
                          method="post"
                          action="${pageContext.request.contextPath}/order?action=bulkCheckout">

                        <c:forEach var="item" items="${cartItems}">

                            <c:set var="v" value="${item.variant}" />
                            <c:set var="p" value="${item.product}" />

                            <div class="cart-card">

                                <!-- SELECT -->
                                <div class="select-box">
                                    <input type="checkbox"
                                           class="cart-check"
                                           name="variantIds"
                                           value="${v.variantId}">
                                </div>

                                <!-- IMAGE -->
                                <div class="cart-img">
                                    <img src="${pageContext.request.contextPath}/${p.imagePath}"
                                         alt="${p.productName}"
                                         onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/background.jpeg';">
                                </div>

                                <!-- DETAILS -->
                                <div class="cart-details">
                                    <h3>${p.productName}</h3>
                                    <p class="meta">Size: ${v.size}</p>
                                    <p class="price">Rs. ${v.sellingPrice}</p>
                                </div>

                                <!-- QTY -->
                                <div class="qty-box">
                                    <label>Qty</label>
                                    <input type="number"
                                           name="qty_${v.variantId}"
                                           value="1"
                                           min="1">
                                </div>

                                <!-- CUSTOM -->
                                <div class="custom-box">

                                    <input type="text"
                                           name="playerName_${v.variantId}"
                                           placeholder="Player Name">

                                    <input type="number"
                                           name="playerNo_${v.variantId}"
                                           placeholder="Player No">

                                </div>

                            </div>

                        </c:forEach>

                        <c:if test="${not empty error}">
                            <div class="error-box">${error}</div>
                        </c:if>


                        <div class="cart-footer">
                            <button type="submit"
                                    id="checkoutBtn"
                                    class="checkout-btn"
                                    disabled>
                                Proceed to Checkout
                            </button>
                        </div>

                    </form>

                </c:if>

            </section>

        </main>

        <jsp:include page="/templates/footer.html"/>

        <!-- JS: ENABLE BUTTON ONLY IF SELECTED -->
        <script>
            const checkboxes = document.querySelectorAll(".cart-check");
            const btn = document.getElementById("checkoutBtn");

            function updateButtonState() {
                let anyChecked = false;

                checkboxes.forEach(cb => {
                    if (cb.checked)
                        anyChecked = true;
                });

                btn.disabled = !anyChecked;
                btn.style.opacity = anyChecked ? "1" : "0.5";
                btn.style.cursor = anyChecked ? "pointer" : "not-allowed";
            }

            checkboxes.forEach(cb => {
                cb.addEventListener("change", updateButtonState);
            });

            updateButtonState();
        </script>

    </body>
</html>