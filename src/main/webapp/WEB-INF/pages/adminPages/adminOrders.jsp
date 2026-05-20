<%-- 
    Document   : adminOrders
    Created on : May 19, 2026, 10:04:14 AM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Orders</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminOrders.css">
    </head>
    <body>
        <jsp:include page="/templates/navbar.jsp"/>
        <div class="orders-container">
            <div class="page-header">
                <h1>Order Management</h1>
                <p>View and manage customer orders.</p>
            </div>

            <div class="table-wrapper">
                <table class="orders-table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Customer ID</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                            <th>Address</th>
                            <th>Created At</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty orders}">
                                <c:forEach var="order" items="${orders}">
                                    <tr>
                                        <td>${order.orderId}</td>
                                        <td>${order.customerId}</td>
                                        <td>Rs. ${order.totalAmt}</td>
                                        <td>
                                            <span class="status-badge ${order.status.toLowerCase()}">
                                                ${order.status}
                                            </span>
                                        </td>
                                        <td>${order.address}</td>
                                        <td>${order.createdAt}</td>
                                        <td>
                                            <c:if test="${order.status eq 'PENDING'}">
                                                <div class="action-buttons">
                                                    <form method="post" action="${pageContext.request.contextPath}/admin/orders">
                                                        <input type="hidden" name="orderId" value="${order.orderId}">
                                                        <input type="hidden" name="action" value="approve">
                                                        <button type="submit" class="btn btn-approve">
                                                            Approve
                                                        </button>
                                                    </form>

                                                    <form method="post" action="${pageContext.request.contextPath}/admin/orders"
                                                          onsubmit="return confirm('Cancel this order?');">
                                                        <input type="hidden" name="orderId" value="${order.orderId}">
                                                        <input type="hidden" name="action" value="cancel">
                                                        <button type="submit" class="btn btn-cancel">
                                                            Cancel
                                                        </button>
                                                    </form>
                                                </div>
                                            </c:if>

                                            <c:if test="${order.status ne 'PENDING'}">
                                                <span class="no-action">No actions</span>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <tr>
                                    <td colspan="7" class="empty-state">
                                        No orders found.
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>
