<%-- 
    Document   : productvariantadd
    Created on : May 2, 2026, 5:24:45 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Variant</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productvariantadd.css">
    </head>

    <body>
        <!-- ADMIN HEADER -->
        <div class="admin-header">

            <div class="logo">
                <h2>KitVerse Admin</h2>
            </div>

            <div class="nav-actions">

                <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/product">Products</a>
                <a href="${pageContext.request.contextPath}/order">Orders</a>

                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="logout-btn">Logout</button>
                </form>

            </div>
        </div>
        <!-- END HEADER -->

        <div class="page-wrapper">

            <div class="form-card">

                <h2>
                    <c:choose>
                        <c:when test="${variant != null}">Edit Variant</c:when>
                        <c:otherwise>Add Variant</c:otherwise>
                    </c:choose>
                </h2>

                <c:if test="${error != null}">
                    <div class="error-box">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/variant" method="post">

                    <input type="hidden" name="action"
                           value="${variant != null ? 'update' : 'add'}"/>

                    <input type="hidden" name="productid" value="${productid}"/>

                    <c:if test="${variant != null}">
                        <input type="hidden" name="variantid" value="${variant.variantId}"/>
                    </c:if>

                    <!-- SIZE -->
                    <div class="form-group">
                        <label>Size</label>
                        <select name="size" required>
                            <option value="S" ${variant != null && variant.size == 'S' ? 'selected' : ''}>S</option>
                            <option value="M" ${variant != null && variant.size == 'M' ? 'selected' : ''}>M</option>
                            <option value="L" ${variant != null && variant.size == 'L' ? 'selected' : ''}>L</option>
                            <option value="XL" ${variant != null && variant.size == 'XL' ? 'selected' : ''}>XL</option>
                            <option value="2XL" ${variant != null && variant.size == '2XL' ? 'selected' : ''}>2XL</option>
                        </select>
                    </div>

                    <!-- PRICE -->
                    <div class="form-group">
                        <label>Price</label>
                        <input type="number" step="0.01" name="sellingPrice"
                               value="${variant != null ? variant.sellingPrice : ''}"
                               required>
                    </div>

                    <!-- STOCK (only add mode) -->
                    <!-- STOCK -->
                    <div class="form-group">
                        <label>Stock</label>
                        <input type="number" name="stock"
                               value="${variant != null ? variant.stock : ''}"
                               min="0" required>
                    </div>

                    <!-- BUTTON -->
                    <button type="submit" class="btn-primary">
                        <c:choose>
                            <c:when test="${variant != null}">Update Variant</c:when>
                            <c:otherwise>Add Variant</c:otherwise>
                        </c:choose>
                    </button>

                </form>

                <a class="back-link"
                   href="${pageContext.request.contextPath}/variant?action=product&productid=${productid}">
                    ← Back to Variants
                </a>

            </div>
        </div>

    </body>
</html>