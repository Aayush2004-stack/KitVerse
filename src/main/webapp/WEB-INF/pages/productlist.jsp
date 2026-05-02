<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Product List</title>

        <!-- External CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productlist.css">
    </head>
    <body>

        <h2>Product List</h2>

        <div class="container">

            <!-- Add Product Button -->
            <a href="${pageContext.request.contextPath}/product?action=new" class="add-btn">
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
                            <img src="${pageContext.request.contextPath}/${p.imagePath}" width="80" height="80"/>
                        </td>

                        <td class="action">

                            <!-- Edit -->
                            <a href="${pageContext.request.contextPath}/product?action=edit&productid=${p.productId}">
                                Edit
                            </a>

                            |

                            <!-- Delete (POST request) -->
                            <form action="${pageContext.request.contextPath}/product" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="productid" value="${p.productId}"/>
                                <button type="submit">Delete</button>
                            </form>

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

    </body>
</html>