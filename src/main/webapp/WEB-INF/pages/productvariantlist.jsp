<%-- 
    Document   : productvariantlist
    Created on : May 2, 2026, 5:24:34 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Variants</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productvariantlist.css">
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

            <div class="header">
                <h2>Product Variants</h2>

                <a class="btn-add"
                   href="${pageContext.request.contextPath}/variant?action=new&productid=${productid}">
                    + Add New Variant
                </a>
            </div>

            <div class="table-card">

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Size</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="v" items="${variants}">
                            <tr>
                                <td>${v.variantId}</td>
                                <td><span class="badge">${v.size}</span></td>
                                <td>Rs. ${v.sellingPrice}</td>

                                <td>
                                    <span class="stock">${v.stock}</span>
                                </td>

                                <td class="actions">

                                    <!-- Edit -->
                                    <a class="btn-edit"
                                       href="${pageContext.request.contextPath}/variant?action=edit&variantid=${v.variantId}&productid=${productid}">
                                        Edit
                                    </a>

                                    <!-- Delete -->
                                    <form action="${pageContext.request.contextPath}/variant" method="post">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="variantid" value="${v.variantId}">
                                        <input type="hidden" name="productid" value="${productid}">
                                        <button class="submit" type="submit"
                                                onclick="return confirm('Are you sure you want to delete?');">Delete</button>
                                    </form>

                                    <!-- Stock Update -->
                                    <form action="${pageContext.request.contextPath}/variant" method="post" class="stock-form">
                                        <input type="hidden" name="action" value="stock">
                                        <input type="hidden" name="variantid" value="${v.variantId}">
                                        <input type="hidden" name="productid" value="${productid}">

                                        <input type="number" name="stock" min="1" placeholder="Add stock">
                                        <button class="btn-stock" type="submit">+ Add Stock</button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <a class="btn-back" href="${pageContext.request.contextPath}/product">
                ← Back to Products
            </a>

        </div>

    </body>
</html>