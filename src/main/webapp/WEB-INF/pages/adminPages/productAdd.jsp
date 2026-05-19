<%-- 
    Document   : productadd
    Created on : May 2, 2026, 1:52:56 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Add / Edit Product</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productAdd.css">
    </head>
    <body>
        <jsp:include page="/templates/navbar.jsp"/>

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
            <form action="${pageContext.request.contextPath}/admin/product"
                  method="post"
                  enctype="multipart/form-data">

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
                <label>Product Image:</label>
                <input type="file" name="image" accept="image/*" required />

                <c:if test="${product != null}">
                    <input type="hidden" name="existingImage"
                           value="${product.imagePath}" />
                </c:if>

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
                <a href="${pageContext.request.contextPath}/admin/product">← Back to List</a>
            </div>

        </div>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>
    </body>
</html>
