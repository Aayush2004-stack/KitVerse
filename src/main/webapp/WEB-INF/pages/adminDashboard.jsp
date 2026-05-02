<%-- 
    Document   : adminDashboard
    Created on : May 1, 2026, 11:35:45 PM
    Author     : ACER
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminDashboard.css">
    </head>

    <body>

        <!-- HEADER START -->
        <div class="admin-header">

            <div class="logo">
                <h2>KitVerse Admin</h2>
            </div>

            <div class="nav-actions">

                <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/product?action=admin">Products</a>
                <a href="${pageContext.request.contextPath}/order">Orders</a>

                 <a href="${pageContext.request.contextPath}/logout" class="nav-login">Logout</a>

            </div>
        </div>
        <!-- HEADER END -->


        <div class="container">

            <h1>Inventory Dashboard</h1>

            <div class="grid">

                <div class="card">
                    <h3>Total Products</h3>
                    <p>${totalProducts}</p>
                </div>

                <div class="card">
                    <h3>Total Variants</h3>
                    <p>${totalVariants}</p>
                </div>

                <div class="card">
                    <h3>Total Stock</h3>
                    <p>${totalStock}</p>
                </div>

                <div class="card warning">
                    <h3>Low Stock (≤5)</h3>
                    <p>${lowStock}</p>
                </div>

                <div class="card danger">
                    <h3>Out of Stock</h3>
                    <p>${outOfStock}</p>
                </div>

            </div>

        </div>

    </body>
</html>