<jsp:directive.page contentType="text/html; charset=ISO-8859-1" /> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container" style="width:100%;padding: 50px 20px 150px 20px">
	<div class="row" style="margin-bottom:15px;">
		<div class="col-sm-4">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
  					<strong>${error}</strong>
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert alert-success">
  					<strong>${msg}</strong>
				</div>
			</c:if>	
		</div>
		<div class="col-sm-offset-6 col-sm-2">
			<a href="trajetcreate" class="btn btn-default">Proposer un parcours</a>
		</div>
	</div>
	<div class="row">
		<div class="col-md-5">
			<div id="map" style="height:500px;width:auto;"></div>
		</div>
		<div class="col-md-7">
			<table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Départ</th>
		                <th>Arrivée</th>
		                <th>Places</th>
		                <th>Date</th>
		                <th>Action</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Départ</th>
		                <th>Arrivée</th>
		                <th>Places</th>
		                <th>Date</th>
		                <th>Action</th>
		            </tr>
		        </tfoot>
		        <tbody>
			        <c:if test="${not empty trajets}">
						<c:forEach var="listValue" items="${trajets}">
							<tr>
								<td id="origin">${listValue.origin}</td>
								<td id="destination">${listValue.destination}</td>
				        		<td>${listValue.placesDispo}</td>
				        		<td>${listValue.depart}</td>
				        		<td><button class="btn btn-default" onclick="openModal(${listValue.id})">Réserver</a></td>
							</tr>
						</c:forEach>		
					</c:if>	
		        </tbody>
		    </table>
		</div>
	</div>
</div>

<%@include file="reservation.jsp" %>

<script>
		var origin;
		var destinataire;
		var directionsService;
  	  	var directionsDisplay;
  	  	
		$(document).ready(function(){
			var table = $('#example').DataTable();
		    
		    $('body').on('click', '#example tbody tr', function () {
		    	var tr = $(this);
		    	origin = tr.find("#origin");
		    	destination = tr.find("#destination");
		    	calculateAndDisplayRoute(directionsService,directionsDisplay,origin.html(),destination.html())
		    } );
		});
		
		function openModal(id){
			$("#trajet_id").val(id);
			$('#myModal').modal('show')
		}
        function init(){
            initMap();
        }
        
        var myLatLng = {lat: 48.866667, lng: 2.333333};
        function initMap() {
        	directionsService = new google.maps.DirectionsService;
      	  	directionsDisplay = new google.maps.DirectionsRenderer;
      	  	var map = new google.maps.Map(document.getElementById('map'), {
      	    	zoom: 6,
      	    	center: myLatLng
      	  	});
      	  	directionsDisplay.setMap(map);
        }
        
        function calculateAndDisplayRoute(directionsService, directionsDisplay, origin, destination) {
        	if(origin && destination){
        	  directionsService.route({
        	    origin: origin,
        	    destination: destination,
        	    travelMode: google.maps.TravelMode.DRIVING
        	  }, function(response, status) {
        	    if (status === google.maps.DirectionsStatus.OK) {
        	      directionsDisplay.setDirections(response);
        	    } else {
        	      window.alert('Aucun résultat');
        	    }
        	  });
        	}
        }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWyHTNe168m9pt0cOiXjlIL9BBUaYT2SI&libraries=geometry,places&callback=init"
        async defer></script>