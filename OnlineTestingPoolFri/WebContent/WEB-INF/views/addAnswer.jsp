<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	        <div class="jumbotron col-md-8 col-md-offset-2">
				
				<!-- Zadanie otazky -->
				<div class="alert alert-info" role="alert">
					<p><span class="glyphicon glyphicon-question-sign">
						</span><c:out value=" ${question.ot_znenie}"></c:out></p>
				</div>
				
				<!-- Warning otazka nema odpoved -->
				<c:if test="${fn:length(answers) eq 0}">
					<div class="alert alert-danger" role="alert">
						<p><span class="glyphicon glyphicon-exclamation-sign">
							</span>&nbsp;<fmt:message key='Question.Vytvorit.Warning'></fmt:message></p>
					</div>
				</c:if>
				
				<!-- Vypise mozne odpovede -->
				<c:if test="${fn:length(answers) ne 0}">
					<ol class="list-group">
						<c:forEach var="ans" items="${answers}">
						
							<!-- nespravna odpoved -->
							<c:if test="${ans.od_sk_id eq 0}">
								<li class="list-group-item list-group-item-danger"><c:out value="${ans.od_znenie}"></c:out></li>
							</c:if>
							
							<!-- spravna odpoved -->
							<c:if test="${ans.od_sk_id eq 1}">
								<li class="list-group-item list-group-item-success"><c:out value="${ans.od_znenie}"></c:out></li>
							</c:if>
							
						</c:forEach>
					</ol>
				</c:if>
				
				<form:form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/addingAnswer" commandName="answer">
				
					<fieldset>					
						<legend class="center"><fmt:message key='Answer.Vytvorit.Nadpis' /></legend>
					</fieldset>
					
					<!-- znenie odpovede -->
					<div class="form-group">
						<label for="inputAnswer" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Answer.Vytvorit.LZnenie' /></label>
						<div class="col-sm-9">
					    	<form:input path="od_znenie" type="text" class="form-control" id="inputAnswer"
					    		placeholder="Možná odpoveď k otázke..."/>
					    	<form:errors path="od_znenie" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- spravna / nespravna -->
					<div class="form-group">
					    <label for="inputAnswerType" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='Answer.Vytvorit.LOdpoved'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="od_sk_id" class="form-control" id="inputAnswerType">
					            <option value="1">správne</option>
					            <option value="0">nesprávne</option>
					        </form:select>
					        <form:errors path="od_sk_id" cssClass="error"></form:errors>
					    </div>
					</div>
					
					<!-- aktivna (read only) -->
					<div class="form-group">
						<label for="inputActive" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Answer.Vytvorit.LAktivna' /></label>
						<div class="col-sm-9">
					    	<form:input path="od_aktivna" type="text" class="form-control" id="inputActive" readonly="true" value="A"/>
					    	<form:errors path="od_aktivna" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- ID otazky (read only) -->
					<div class="form-group">
						<label for="inputIdQuestion" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Answer.Vytvorit.LIdOt' /></label>
						<div class="col-sm-9">
					    	<form:input path="od_ot_id" type="number" class="form-control" id="inputIdQuestion" readonly="true" value="${question.ot_id}"/>
					    	<form:errors path="od_ot_id" cssClass="error"></form:errors>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<div class="pull-right">
								<button type="submit" class="btn btn-info btn-lg confirm"><fmt:message key='Answer.Vytvorit.BtnVytvorit' /></button>
								<a class="btn btn-info btn-lg btn-success confirm bigMarginLeft" href="<c:url value="/createQuestion"/>">
								<fmt:message key='Answer.Vytvorit.BtnNova' /></a>
							</div>
						</div>
					</div>
					
				</form:form>

	    	</div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	
	<script type="text/javascript">
		MYLIBRARY.init(["questions", "questionCreate"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>