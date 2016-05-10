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
	<link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet" type="text/css" >

<title>Login Page</title>
</head>
<body onload='document.f.username.focus();'>
	
	<div class="container">
		<div class="col-md-6 col-md-offset-3">
		
		<div class="center">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger" id="alert-error" role="alert"><s:message code='Login.Chyba'/></div>
			</c:if>
		</div>
        <form name='f' action="<c:url value="/login"/>" method='POST'>
        
            <h2><s:message code='Login.Nazov'/> <a href="<c:url value="/"/>"><span class="glyphicon glyphicon-home"></span></a></h2>
            
            <div class="input-group">
	            <span class="input-group-addon">
	            	<span class="glyphicon glyphicon-user glyphlock"></span>
	            </span>
	            <input type='text' name='username' class="form-control" 
	            	placeholder="<s:message code='Login.Meno'/>" required autofocus>
            </div>
            
            <div class="input-group">
	            <span class="input-group-addon">
	            	<span class="glyphicon glyphicon-lock glyphlock"></span>
	            </span>
	            <input type='password' name='password' class="form-control" 
	            	placeholder="<s:message code='Login.Heslo'/>" required>
            </div>
            
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> <s:message code='Login.Zapamataj'/>
                </label>
            </div>
            
            <div class="center">
            <button class="btn btn-lg btn-primary" type="submit" name="submit"
            	value="Login"><s:message code='Login.Prihlasenie'/></button>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
		</div>
    </div>
	
	<%@ include file="scripts.jsp" %>
	
	<script type="text/javascript" src="<c:url value="resources/js/fadeOut.js"/>"></script>
</body>
</html>