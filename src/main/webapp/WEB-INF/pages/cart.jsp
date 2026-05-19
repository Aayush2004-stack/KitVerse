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

        
        <form method="post"
                      action="${pageContext.request.contextPath}/order?action=bulkCheckout">

            <c:forEach var="item" items="${cartItems}">

                <c:set var="v" value="${item.variant}" />
                <c:set var="p" value="${item.product}" />

                <div class="cart-card">

                    <!-- SELECT ITEM -->
                    <div class="select-box">
                        <input type="checkbox"
                               name="variantIds"
                               value="${v.variantId}">
                    </div>

                    <!-- IMAGE -->
                    <div class="cart-img">
                        <img src="${pageContext.request.contextPath}/${p.imagePath}"
                             alt="${p.productName}">
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

                </div>

            </c:forEach>

            <div class="cart-footer">
                <button type="submit" class="checkout-btn">
                    Proceed to Checkout
                </button>
            </div>

        </form>

    </c:if>

</section>

</body>
</html>