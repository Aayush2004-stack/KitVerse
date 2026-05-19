<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Checkout - KitVerse</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/main.css">

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/checkout.css">
    </head>

    <body>

        <jsp:include page="/templates/navbar.jsp"/>

        <div class="checkout-container">

            <h1>Checkout</h1>

            <!-- ================= ORDER SUMMARY ================= -->
            <div class="box">

                <div class="title">Order Summary</div>

                <!-- ================= BUY NOW MODE ================= -->
                <c:if test="${not empty product}">

                    <div class="item-card">

                        <div class="row">
                            <span>Product</span>
                            <span>${product.productName}</span>
                        </div>

                        <div class="row">
                            <span>Team</span>
                            <span>${product.teamName}</span>
                        </div>

                        <div class="row">
                            <span>Size</span>
                            <span>${variant.size}</span>
                        </div>

                        <div class="row">
                            <span>Quantity</span>
                            <span>${quantity}</span>
                        </div>

                        <div class="row">
                            <span>Price</span>
                            <span>Rs. ${variant.sellingPrice}</span>
                        </div>

                    </div>

                </c:if>

                <!-- ================= CART MODE ================= -->
                <c:if test="${not empty items}">

                    <c:forEach var="item" items="${items}">

                        <div class="item-card">

                            <div class="row">
                                <span>Product</span>
                                <span>${item.product.productName}</span>
                            </div>

                            <div class="row">
                                <span>Size</span>
                                <span>${item.variant.size}</span>
                            </div>

                            <div class="row">
                                <span>Quantity</span>
                                <span>${item.quantity}</span>
                            </div>

                            <div class="row">
                                <span>Price</span>
                                <span>Rs. ${item.variant.sellingPrice}</span>
                            </div>

                        </div>

                        <hr>

                    </c:forEach>

                </c:if>

                <!-- TOTAL -->
                <div class="row total">
                    <strong>Total Amount</strong>
                    <strong>Rs. ${totalAmt}</strong>
                </div>

            </div>

            <!--  order form  -->
            <div class="box">

                <div class="title">Delivery Information</div>

                <form method="post"
                      action="${pageContext.request.contextPath}/order?action=confirm">

                    <!-- BUY NOW -->
                    <c:if test="${not empty variant}">
                        <input type="hidden" name="variantId" value="${variant.variantId}">
                        <input type="hidden" name="quantity" value="${quantity}">
                    </c:if>

                    <!-- CART -->
                    <c:if test="${not empty items}">
                        <c:forEach var="item" items="${items}">
                            <input type="hidden"
                                   name="variantIds"
                                   value="${item.productVariantId}">
                            <input type="hidden"
                                   name="qty_${item.productVariantId}"
                                   value="${item.quantity}">
                        </c:forEach>
                    </c:if>

                    <input type="hidden" name="totalAmt" value="${totalAmt}">

                    <!-- DELIVERY FIELDS-->

                    <label>Full Delivery Address</label>

                    <textarea name="address"

                              placeholder="Province, City, Ward, Street, Landmark..."

                              required></textarea>

                    <button type="submit" class="checkout-btn">

                        Confirm Order

                    </button>

                </form>

            </div>

        </div>

    </body>
</html>