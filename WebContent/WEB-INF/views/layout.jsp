<%@ page contentType="text/html;charset=UTF-8"%>
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

	    <!-- Custom styles for this template -->
	    <link href="css/main.css" rel="stylesheet">
</head>

<body> 
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
		<!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
</body>

</html>