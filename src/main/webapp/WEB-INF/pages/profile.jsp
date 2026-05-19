<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>My Profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
</head>

<body>

<jsp:include page="/templates/navbar.jsp"/>

<div class="profile-container">

    <div class="profile-card">

        <h1>My Profile</h1>

        <c:if test="${not empty message}">
            <div class="success">${message}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form method="post"
              action="${pageContext.request.contextPath}/profile?action=updateProfile">

            <!-- FULL NAME (editable) -->
            <div class="form-group">
                <label>Full Name</label>
                <input type="text"
                       name="fullName"
                       value="${sessionScope.user.fullName}"
                       required>
            </div>

            <!-- EMAIL (read only) -->
            <div class="form-group">
                <label>Email</label>
                <input type="text"
                       value="${sessionScope.user.email}"
                       disabled>
            </div>

            <!-- PHONE (read only) -->
            <div class="form-group">
                <label>Phone Number</label>
                <input type="text"
                       value="${sessionScope.user.phnNo}"
                       disabled>
            </div>

            <!-- PASSWORD -->
            <div class="form-group">
                <label>New Password</label>
                <input type="password"
                       name="password"
                       placeholder="Enter new password">
            </div>

            <div class="form-group">
                <label>Confirm Password</label>
                <input type="password"
                       name="confirmPassword"
                       placeholder="Confirm password">
            </div>

            <button type="submit" class="save-btn">
                Update Profile
            </button>

        </form>

    </div>

</div>

</body>
</html>