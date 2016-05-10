<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	
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
	<link href="<c:url value="/resources/css/logout.css"/>" rel="stylesheet" type="text/css" >

<title>Logout Page</title>
</head>
<body>
	
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-info" role="alert" style="top: 30%; position: fixed; left: calc(50% - 161px); font-size: x-large;">
	  			<p><s:message code='Logout.Nazov'/> <a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home"></span></a></p>
	  			<p style="font-size: 20%;" id="download"></p>
			</div>
		</div>
	</div>
	
	<%@ include file="scripts.jsp" %>
	<script>
		var ctx = "${pageContext.request.contextPath}"
		var logoutMsg = document.getElementById("download");
		var counter = 5;
		var newElement = document.createElement("p");
		newElement.innerHTML = "<fmt:message key='Logout.Redirect'></fmt:message><fmt:message key='Logout.Seconds'></fmt:message>";
		var id;

		logoutMsg.parentNode.replaceChild(newElement, logoutMsg);

		id = setInterval(function() {
		    counter--;
		    if(counter < 0) {
		    	location.href = ctx + "/";
		        clearInterval(id);
		    } else {
		        newElement.innerHTML = "<s:message code='Logout.Redirect'/>" 
		        	+ counter.toString() + " <s:message code='Logout.Seconds'/>";
		    }
		}, 1000);

	</script>
</body>
</html>