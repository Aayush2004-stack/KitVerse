<%-- 
    Document   : adminDashboard
    Created on : May 1, 2026, 11:35:45 PM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
    </head>

    <body>
        <jsp:include page="/templates/navbar.jsp"/>

        <section class="aContainer">

            <!-- Sales Cards -->
            <div class="aCard revenue">
                <h3>Total Revenue</h3>
                <p class="metric">Rs. ${totalRevenue}</p>
            </div>

            <div class="aCard">
                <h3>Total Orders</h3>
                <p class="metric">${totalOrders}</p>
            </div>

            <div class="aCard warning">
                <h3>Pending Orders</h3>
                <p class="metric">${pendingOrders}</p>
            </div>

            <div class="aCard success">
                <h3>Delivered Orders</h3>
                <p class="metric">${deliveredOrders}</p>
            </div>

            <!-- Inventory Cards -->
            <div class="aCard">
                <h3>Total Products</h3>
                <p class="metric">${totalProducts}</p>
            </div>

            <div class="aCard">
                <h3>Total Variants</h3>
                <p class="metric">${totalVariants}</p>
            </div>

            <div class="aCard warning">
                <h3>Low Stock</h3>
                <p class="metric">${lowStock}</p>
            </div>

            <div class="aCard danger">
                <h3>Out of Stock</h3>
                <p class="metric">${outOfStock}</p>
            </div>

        </section>
        <section class="dashboard">
            <div class="panel">
                <h3>Sales Overview</h3>
                <div class="chartContainer">
                    <canvas id="salesChart"></canvas>
                </div>
            </div>

            <section class="recentTopics">
                <h3>Today's Summary</h3>
                <ul>
                    <li>Today's Orders: ${todayOrders}</li>
                    <li>Today's Revenue: Rs. ${todayRevenue}</li>
                    <li>Cancelled Orders: ${cancelledOrders}</li>
                </ul>
            </section>
        </section>

        <!-- Chart.js CDN -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <script>
            const weeklyRevenue = [
            <c:forEach var="amount" items="${weeklyRevenue}" varStatus="status">
                ${amount}<c:if test="${!status.last}">,</c:if>
            </c:forEach>
            ];

            const ctx = document.getElementById("salesChart");

            new Chart(ctx, {
                type: "line",
                data: {
                    labels: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
                    datasets: [{
                            label: "Sales Revenue (Rs.)",
                            data: weeklyRevenue,
                            borderWidth: 2,
                            tension: 0.3,
                            fill: true
                        }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,

                    plugins: {
                        legend: {
                            position: "top"
                        }
                    },

                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>    </body>
</html>