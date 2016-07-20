<jsp:directive.page contentType="text/html; charset=ISO-8859-1" /> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" style="width:100%;padding: 50px 20px 150px 20px">
	<div class="row">
		<div class="col-md-offset-5 col-md-7">
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
	</div>
	<div class="row">
		<div class="col-md-5">
			<div id="map" style="height:500px;width:auto;"></div>
		</div>
		<div class="col-md-7">
			<form name='loginForm' action="<c:url value='trajetcreate' />" method='POST' modelAttribute="trajet">
			  <div class="form-group">
			    <label for="name">Nom</label>
			    <input type="text" class="form-control" id="nom" name="nom">
			  </div>
			  <div class="form-group">
			    <label for="origin">Adresse de départ</label>
			    <input type="text" class="form-control" id="origin" name="origin">
			  </div>
			  <div class="form-group">
			    <label for="destination">Adresse d'arrivée</label>
			    <input type="text" class="form-control" id="destination" name="destination">
			  </div>
			  <div class="form-group">
			    <label for="places">Places disponibles</label>
			    <input type="text" class="form-control" id="places" name="places">
			  </div>
			  <div class="form-group">
			    <label for="date">Départ le</label>
			    <input type="datetime" class="form-control" id="datetimepicker" name="depart">
			  </div>
			  <button type="submit" class="btn btn-default">Proposer le trajet</button>
			</form>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
    $('#example').DataTable();
});
$("#datetimepicker").datetimepicker({icons:{time:"fa fa-clock-o",date:"fa fa-calendar",up:"fa fa-arrow-up",down:"fa fa-arrow-down"}});
</script>
    <script>
        function init(){
            initMap();
            initAutocomplete();
        }
        function initMap() {
       		  var directionsService = new google.maps.DirectionsService;
        	  var directionsDisplay = new google.maps.DirectionsRenderer;
        	  var map = new google.maps.Map(document.getElementById('map'), {
        	    zoom: 6,
        	    center: {lat: 46.227638, lng: 2.3522219000000177}
        	  });
        	  directionsDisplay.setMap(map);

            
            var onChangeHandler = function() {
                calculateAndDisplayRoute(directionsService, directionsDisplay);
              };
              document.getElementById('origin').addEventListener('change', onChangeHandler);
              document.getElementById('destination').addEventListener('change', onChangeHandler);

        }
        // This example displays an address form, using the autocomplete feature
        // of the Google Places API to help users fill in the information.

        var origin, destination;
        function initAutocomplete() {
            origin = new google.maps.places.Autocomplete(
                    /** @type {!HTMLInputElement} */(document.getElementById('origin')),
                    {types: ['geocode']});
            destination = new google.maps.places.Autocomplete(
                    /** @type {!HTMLInputElement} */(document.getElementById('destination')),
                   {types: ['geocode']});
        }
        
        function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        	if(document.getElementById('origin').value && document.getElementById('destination').value){
        	  directionsService.route({
        	    origin: document.getElementById('origin').value,
        	    destination: document.getElementById('destination').value,
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

        // [END region_geolocation]
    </script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWyHTNe168m9pt0cOiXjlIL9BBUaYT2SI&libraries=geometry,places&callback=init"
        async defer></script>