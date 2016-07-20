<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.css" rel="stylesheet">
	    <link href="css/font-awesome.min.css" rel="stylesheet">
	    <link href="js/datatables/css/datatable.css" rel="stylesheet">
	    <link href="js/datatables/css/bootstrap.css" rel="stylesheet">

	    <!-- Custom styles for this template -->
	    <link href="css/main.css" rel="stylesheet">
	   
	    
   	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js" type="text/javascript"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script src="js/datatables/jquery.dataTables.min.js"></script>
	    <script src="js/moment-with-locales.min.js"></script>
	    <script src="js/bootstrap-datetimepicker.fr.js"></script>
	    <script src="js/bootstrap-datetimepicker.min.js"></script>
</head>

<body> 
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
		
</body>

</html>