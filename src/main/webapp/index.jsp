<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

   <t:pageTemplate pageTitle="Parking Lot">
       <h1>Welcome to our parking lot!</h1>
       <div class="container text-center">

           <c:forEach var="car" items="${cars}">
               <div class="row">
                   <div class="col">
                           ${car.licensePlate}
                   </div>
                   <div class="col">
                           ${car.parkingSpot}
                   </div>
                   <div class="col">
                           ${car.ownerName}
                   </div>
               </div>
           </c:forEach>
       </div>
   </t:pageTemplate>
