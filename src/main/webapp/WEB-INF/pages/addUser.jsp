<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Add User">

  <div class="container text-center">
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddUser">
      <div class="row g-5">

        <div class="col-md-6 mb-3">
          <h1 class="mb-3">Add User</h1>
          <label for="username">Username</label>

          <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
          <div class="invalid-feedback">
            Username is required
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required>
          <div class="invalid-feedback">
            Email is required
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="password" >Password</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required>
          <div class="invalid-feedback">
            Password is required
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6 mb-3">
          <label for="user_groups">Groups</label>
          <select class="custom-select d-block w-100" id="user_groups" name="user_groups" multiple>

            <u:forEach var="user_group" items="${userGroups}" varStatus="status">
              <option value="${user_group}">${user_group}</option>
            </u:forEach>
          </select>

        </div>
      </div>
      <hr class="mb-4">

      <div class="col-12">
        <button type="submit" class="btn btn-primary">Save</button>
      </div>

    </form>

  </div>
  <script src="/docs/5.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <script src="form-validation.js"></script>
</t:pageTemplate>
