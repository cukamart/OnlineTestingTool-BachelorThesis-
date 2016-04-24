<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
	
	<link href="<c:url value="/resources/css/background.css"/>"
		rel="stylesheet" type="text/css">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
		type="text/css">
		
	<link href="<c:url value="/resources/css/navbar.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet" type="text/css" >
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	    
	    	<div class="col-md-8 col-md-offset-2 center bold">
	    		<c:if test="${param.noTest eq 'true'}">
	        		<div class="alert alert-danger" role="alert"><fmt:message key='Test.Vytvorit.Otazka'></fmt:message></div>
	        	</c:if>
	        </div>
	        
	        <div class="col-md-8 col-md-offset-2 center bold">
	    		<c:if test="${param.noTest eq 'true1'}">
	        		<div class="alert alert-danger" role="alert"><fmt:message key='Test.Vytvorit.STest'></fmt:message></div>
	        	</c:if>
	        </div>
	    
	        <div class="jumbotron col-md-8 col-md-offset-2">

				<form:form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/viewTests" commandName="subjectTest">
				
					<fieldset>					
						<legend class="center"><fmt:message key='Test.Vytvorit.Nadpis' />
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
					</fieldset>
					
					<!-- skolsky rok -->
					<div class="form-group">
						<label for="inputYear" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Test.Vytvorit.LYear' /></label>
						<div class="col-sm-9">
					    	<form:input path="skrok" type="number" class="form-control" id="inputYear" pattern="[0-9]"
					    		placeholder="Zadajte školský rok napr. pre 2015/2016 - 2015"/>
					    	<form:errors path="skrok" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- nazov testu -->
					<div class="form-group">
						<label for="inputName" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Test.Vytvorit.LNazov' /></label>
						<div class="col-sm-9">
					    	<form:input path="nazov" type="text" class="form-control" id="inputName"
					    		placeholder="Názov testu napr. cvičný / 1. zápočet / skúška..."/>
					    	<form:errors path="nazov" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- poradie testu -->
					<div class="form-group">
						<label for="inputPor" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Test.Vytvorit.LPor' /></label>
						<div class="col-sm-9">
					    	<form:input path="por" type="number" pattern="[0-9]" class="form-control" id="inputPor"
					    		placeholder="Poradie testu"/>
					    	<form:errors path="por" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- minimalny pocet bodov -->
					<div class="form-group">
						<label for="inputMin" class="col-sm-3 control-label"><fmt:message key='Test.Vytvorit.LMin' /></label>
						<div class="col-sm-9">
					    	<form:input path="min" type="number" pattern="[0-9]" class="form-control" id="inputMin"
					    		placeholder="Minimálny počet bodov"/>
					    	<form:errors path="min" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- maximalny pocet bodov -->
					<div class="form-group">
						<label for="inputMax" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Test.Vytvorit.LMax' /></label>
						<div class="col-sm-9">
					    	<form:input path="max" type="number" pattern="[0-9]" class="form-control" id="inputMax"
					    		placeholder="Maximálny možný počet bodov"/>
					    	<form:errors path="max" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- suma, priratat k zapoctu ? -->
					<div class="form-group">
						<label for="inputSum" class="col-sm-3 control-label"><fmt:message key='Test.Vytvorit.LSum' /></label>
						<div class="col-sm-9">
					    	<form:checkbox class="checkbox" path="sum" id="inputSum" />
						</div>
					</div>
					
					<!-- penale -->
					<div class="form-group">
						<label for="inputPenale" class="col-sm-3 control-label"><fmt:message key='Test.Vytvorit.LPenale' /></label>
						<div class="col-sm-9">
					    	<form:input path="penale" type="number" step="0.01" class="form-control" id="inputPenale"
					    		placeholder="Koeficient, ktorým sa odpočítajú body, ak študent nedosiahne minimum"/>
					    	<form:errors path="penale" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- ID predmetu (read only) -->
					<div class="form-group">
						<label for="inputSubject" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Test.Vytvorit.LIdPr' /></label>
						<div class="col-sm-9">
					    	<form:input path="pr_id" type="text" class="form-control" id="inputSubject" readonly="true" value="${subject.pr_id}"/>
					    	<form:errors path="pr_id" cssClass="error"></form:errors>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-info btn-lg confirm"><fmt:message key='Test.Vytvorit.BtnVytvorit' /></button>
						</div>
					</div>
					
				</form:form>

	    	</div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	
	<script type="text/javascript">
		MYLIBRARY.init(["tests", "testCreate"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>