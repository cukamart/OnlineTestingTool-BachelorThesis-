<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

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
	<link href="<c:url value="/resources/css/lang.css"/>" rel="stylesheet" type="text/css" >
		
	<title>Skúšací systém FRI</title>
</head>

<body>
	
	<div id="language">
		<a href="<c:url value="/?language=sk"/>"><img src="resources/images/slovakiaFlag.jpg" /></a>
		<a href="<c:url value="/?language=en"/>"><img src="resources/images/englandFlag.jpg" /></a><br/>
		<c:set var="localeCode" value="${pageContext.response.locale}" />
		<span class="bold"><s:message code="Home.Lang"/><c:out value=" ${localeCode}"></c:out></span>
	</div>
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
		
			<h1 class="center title"><s:message code="Home.Nazov"/></h1>
			
			<div class="center">
			
				<sec:authorize access="isAnonymous()">
					<p class="lead">
						<a href="<c:url value="/login"/>"><s:message code="Home.Login"/></a>
					</p>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('ucitelia')">
					<p class="lead">
						<a href="<c:url value="/index"/>"><s:message code="Home.Enter"/></a>
					</p>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('studenti')">
					<p class="lead">
						<a href="<c:url value="/sindex"/>"><s:message code="Home.Enter"/></a>
					</p>
				</sec:authorize>
		    
				<sec:authorize access="isAuthenticated()">
					<c:url var="logoutUrl" value="/logout" />
					
					<form action="${logoutUrl}" id="logout" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<p class="lead center">
						<a href="#" onclick="document.getElementById('logout').submit();"><s:message code="Home.Logout"/>
							[<b><c:out value="${name}"></c:out></b>]</a>
					</p>
				</sec:authorize>
			</div>
			
		</div>
	</div>
	
	<%@ include file="scripts.jsp" %>
</body>
</html>