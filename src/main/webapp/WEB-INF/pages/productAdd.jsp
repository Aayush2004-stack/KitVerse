<%-- 
    Document   : productadd
    Created on : May 2, 2026, 1:52:56 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Add / Edit Product</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAdd.css">
    </head>
    <body>
        <!-- ADMIN HEADER -->
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
        <!-- END HEADER -->

        <h2 style="text-align:center;">
            <c:choose>
                <c:when test="${product != null}">
                    Edit Product
                </c:when>
                <c:otherwise>
                    Add Product
                </c:otherwise>
            </c:choose>
        </h2>

        <div class="form-container">

            <!-- Error Message -->
            <c:if test="${not empty error}">
                <p style="color:red; text-align:center;">${error}</p>
            </c:if>

            <!-- Form -->
            <form action="${pageContext.request.contextPath}/product" method="post">

                <!-- Hidden fields -->
                <input type="hidden" name="action"
                       value="${product != null ? 'update' : 'add'}"/>

                <c:if test="${product != null}">
                    <input type="hidden" name="productId" value="${product.productId}"/>
                </c:if>

                <!-- Product Name -->
                <label>Product Name:</label>
                <input type="text" name="productName"
                       value="${product.productName}" required />

                <!-- Team Name -->
                <label>Team Name:</label>
                <input type="text" name="teamName"
                       value="${product.teamName}" required />

                <!-- Category -->
                <label>Category:</label>
                <select name="category">
                    <option value="club">Club</option>
                    <option value="country">Country</option>
                </select>

                <!-- Description -->
                <label>Description:</label>
                <textarea name="description" rows="4" required>${product.description}</textarea>

                <!-- Image Path -->
                <label>Image Path:</label>
                <input type="text" name="imagePath"
                       value="${product.imagePath}" required />

                <!-- Submit -->
                <button type="submit">
                    <c:choose>
                        <c:when test="${product != null}">
                            Update Product
                        </c:when>
                        <c:otherwise>
                            Add Product
                        </c:otherwise>
                    </c:choose>
                </button>

            </form>

            <!-- Back Button -->
            <div style="text-align:center; margin-top:15px;">
                <a href="${pageContext.request.contextPath}/product?action=admin">← Back to List</a>
            </div>

        </div>

    </body>
</html>
