<%-- 
    Document   : productvariantlist
    Created on : May 2, 2026, 5:24:34 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Product Variants</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productVariantList.css">
    </head>

    <body>
        <jsp:include page="/templates/navbar.jsp"/>

        <div class="page-wrapper">

            <div class="header">
                <h2>Product Variants</h2>
                <c:if test="${not empty error}">
                    <div class="error-message">
                        ${error}
                    </div>
                </c:if>

                <a class="btn-add"
                   href="${pageContext.request.contextPath}/admin/variant?action=new&productId=${productId}">
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
                                       href="${pageContext.request.contextPath}/admin/variant?action=edit&variantId=${v.variantId}&productId=${productId}">
                                        Edit
                                    </a>

                                    <!-- Delete -->
                                    <form action="${pageContext.request.contextPath}/admin/variant" method="post">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="variantId" value="${v.variantId}">
                                        <input type="hidden" name="productId" value="${productId}">
                                        <button class="btn-delete" type="submit"
                                                onclick="return confirm('Are you sure you want to delete?');">Delete</button>
                                    </form>

                                    <!-- Stock Update -->
                                    <form action="${pageContext.request.contextPath}/admin/variant" method="post" class="stock-form">
                                        <input type="hidden" name="action" value="stock">
                                        <input type="hidden" name="variantId" value="${v.variantId}">
                                        <input type="hidden" name="productId" value="${productId}">

                                        <input type="number" name="stock" min="1" placeholder="Add stock">
                                        <button class="btn-stock" type="submit">+ Add Stock</button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <a class="btn-back" href="${pageContext.request.contextPath}/admin/product">
                ← Back to Products
            </a>

        </div>
        <jsp:include page="/templates/footer.html"/>

    </body>
</html>
