<%-- 
    Document   : checkout
    Created on : May 17, 2026, 4:30:02 PM
    Author     : aayushbastola
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Checkout</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/main.css">

    <style>
        .checkout-container {
            max-width: 900px;
            margin: auto;
            padding: 30px;
        }

        .box {
            background: #fff;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        .row {
            display: flex;
            justify-content: space-between;
            margin: 8px 0;
        }

        textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            margin-top: 10px;
        }

        button {
            background: black;
            color: white;
            padding: 12px 20px;
            border: none;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            margin-top: 10px;
        }

        button:hover {
            opacity: 0.9;
        }

        .title {
            font-size: 22px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<jsp:include page="/templates/navbar.jsp"/>

<div class="checkout-container">

    <h1>Checkout</h1>

    <!-- PRODUCT SUMMARY -->
    <div class="box">

        <div class="title">Order Summary</div>

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
            <span>Price</span>
            <span>Rs. ${variant.sellingPrice}</span>
        </div>

        <div class="row">
            <span>Quantity</span>
            <span>${quantity}</span>
        </div>

        <c:if test="${not empty playerName}">
            <div class="row">
                <span>Player Name</span>
                <span>${playerName}</span>
            </div>
        </c:if>

        <c:if test="${not empty playerNo}">
            <div class="row">
                <span>Player No</span>
                <span>${playerNo}</span>
            </div>
        </c:if>

        <hr>

        <div class="row">
            <strong>Total Amount</strong>
            <strong>Rs. ${totalAmt}</strong>
        </div>

    </div>

    <!-- DELIVERY FORM -->
    <div class="box">

        <div class="title">Delivery Information</div>

        <form method="post"
              action="${pageContext.request.contextPath}/order?action=confirm">

            <!-- hidden fields -->
            <input type="hidden" name="variantId" value="${variant.variantId}">
            <input type="hidden" name="quantity" value="${quantity}">
            <input type="hidden" name="totalAmt" value="${totalAmt}">
            <input type="hidden" name="playerName" value="${playerName}">
            <input type="hidden" name="playerNo" value="${playerNo}">

            <label>Delivery Address</label>
            <textarea name="address" placeholder="Enter full delivery address..." required></textarea>

            <button type="submit">
                Confirm Order
            </button>

        </form>

    </div>

</div>

</body>

</html>