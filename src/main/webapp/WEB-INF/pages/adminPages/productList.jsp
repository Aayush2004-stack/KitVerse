<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Product List</title>

        <!-- External CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">
    </head>
    <body>
        <jsp:include page="/templates/navbar.jsp"/>

        <h2>Product List</h2>
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>

        <div class="container">

            <!-- Add Product Button -->
            <a href="${pageContext.request.contextPath}/admin/product?action=new" class="add-btn">
                + Add Product
            </a>

            <!-- Product Table -->
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Team</th>
                    <th>Category</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Actions</th>
                </tr>

                <!-- Loop through products -->
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td>${p.productId}</td>
                        <td>${p.productName}</td>
                        <td>${p.teamName}</td>
                        <td>${p.category}</td>
                        <td>${p.description}</td>
                        <td>
                            <img src="${pageContext.request.contextPath}/${p.imagePath}"
                                 onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/resources/images/background.jpeg';"
                                 width="80" height="80"/>
                        </td>


                        <td>
                            <div class="action">

                                <!-- Edit -->
                                <a href="${pageContext.request.contextPath}/admin/product?action=edit&productId=${p.productId}">
                                    Edit
                                </a>

                                <!-- Delete -->
                                <form action="${pageContext.request.contextPath}/admin/product"
                                      method="post"
                                      style="display:inline;">
                                    <input type="hidden" name="action" value="delete"/>
                                    <input type="hidden" name="productId" value="${p.productId}"/>
                                    <button type="submit"
                                            onclick="return confirm('Are you sure you want to delete?');">
                                        Delete
                                    </button>
                                </form>

                                <!-- View Variants -->
                                <a href="${pageContext.request.contextPath}/admin/variant?action=product&productId=${p.productId}">
                                    View Variants
                                </a>

                            </div>
                        </td>
                    </tr>
                </c:forEach>

                <!-- If no products -->
                <c:if test="${empty products}">
                    <tr>
                        <td colspan="7">No products available</td>
                    </tr>
                </c:if>

            </table>

        </div>

        <!-- FOOTER -->
        <jsp:include page="/templates/footer.html"/>
    </body>
</html>
