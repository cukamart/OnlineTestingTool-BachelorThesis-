<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
		integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
		crossorigin="anonymous">
	
	<!-- Optional theme -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
		integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
		crossorigin="anonymous">
	
	<link href="<c:url value="/resources/css/background.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" type="text/css" >
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	
	<div class="container">
	    <div class="jumbotron row marginTop col-md-6 col-md-offset-3 center">
	    
	    	<div class="alert alert-info" role="alert">
 				 <p><span class="glyphicon glyphicon-info-sign"></span>
 				 	<s:message code='SValuateTest.View'/><b>${studentTest.ste_vysledok}/${studentTest.ste_plnypocet}</b></p>
			</div>
			
			<a class="btn btn-success" href="<c:url value="/sindex"/>"><s:message code='SValuateTest.BtnHome'/></a>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
</body>
</html>