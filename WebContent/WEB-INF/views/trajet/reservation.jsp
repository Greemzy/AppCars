<jsp:directive.page contentType="text/html; charset=ISO-8859-1" /> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
  	<form name='loginForm' action="<c:url value='reservation' />" method='POST' modelAttribute="reservation">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Réservation du trajet <span></span></h4>
	      </div>
	      <div class="modal-body">
	        
				  <div class="form-group">
				    <label for="name">Nombre d'occupants</label>
				    <input type="integer" class="form-control" id="nom" name="place">
				  </div>
				  <input type="hidden" name="trajet_id" id="trajet_id" value="">
				
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
	        <button type="submit" class="btn btn-primary">Reserver</button>
	      </div>
	    </div>
    </form>
  </div>
</div>