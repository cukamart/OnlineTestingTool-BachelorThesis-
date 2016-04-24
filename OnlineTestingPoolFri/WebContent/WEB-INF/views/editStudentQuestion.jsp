<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	    <div class="jumbotron row marginTop col-md-8 col-md-offset-2">
	    	
	    	<form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/editedStudentQuestion">
	    	
	    		<p>Počet bodov: <input name="points" type="number" max="${param.max}" required/> 
	    			<input class="btn btn-success bigMarginLeft" type="submit" value="Uprav Body"/></p>
	    		<input name="studentTestId" type="hidden" value="${studentTestId}"/>
	    		<input name="studentQuestionId" type="hidden" value="${studentQuestionId}"/>
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	    		
	    	</form>
	    	<input class="btn btn-primary" action="action" type="button" value="Späť" onclick="window.history.go(-1); return false;" />
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
</body>
</html>