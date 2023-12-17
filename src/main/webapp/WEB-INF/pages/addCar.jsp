<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Cars">

    <div class="container text-center">
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
        <div class="row g-5">
            <div class="col-md-6 mb-3">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate" name="licence_plate" placeholder="" value="" required>
                <div class="invalid-feedback">
                License plate is required
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Parking Spot is required
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="owner_id">Owner ID</label>
                <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                    <option value="">Chose...</option>
                    <u:forEach var="user" items="${users}" varStatus="status">
                    <option value="${user.id}">${user.userName}</option>
                    </u:forEach>
                </select>
                <div class="invalid-feedback">Please select a valid owner</div>
            </div>
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>


    </form>

    </div>
    <script src="/docs/5.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="form-validation.js"></script>
</t:pageTemplate>
