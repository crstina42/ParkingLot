<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<t:pageTemplate pageTitle="Users">

    <h1> Users</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Users">


        <u:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">

            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser">Add User</a>
            </u:if>

        <div class="container text-center">
        <u:forEach var="user" items="${users}">
            <div class="row">
                <div class="col">
                        ${user.userName}
                </div>

                <div class="col">
                        ${user.email}
                </div>
            </div>
        </u:forEach>
    </div>
        </form>

</t:pageTemplate>