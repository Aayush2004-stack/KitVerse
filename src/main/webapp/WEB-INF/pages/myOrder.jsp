<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>My Orders</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myOrder.css">
    </head>

    <body>

        <jsp:include page="/templates/navbar.jsp"/>
        <main>

            <div class="orders-container">

                <h1 class="title">My Orders</h1>

                <c:if test="${empty orders}">
                    <div class="empty">
                        <h3>No orders found</h3>
                        <p>You haven’t placed any orders yet.</p>
                    </div>
                </c:if>

                <c:forEach var="order" items="${orders}">

                    <div class="order-card">

                        <div class="row">
                            <span>Order ID</span>
                            <span>${order.orderId}</span>
                        </div>

                        <div class="row">
                            <span>Total Amount</span>
                            <span>Rs. ${order.totalAmt}</span>
                        </div>

                        <div class="row">
                            <span>Address</span>
                            <span>${order.address}</span>
                        </div>

                        <div class="row">
                            <span>Status</span>
                            <span class="status">${order.status}</span>
                        </div>
                        <div class="row">
                            <span>Order Date</span>
                            <span class="status">${order.createdAt}</span>
                        </div>

                    </div>

                </c:forEach>

            </div>
        </main>
        <jsp:include page="/templates/footer.html"/>

    </body>
</html>