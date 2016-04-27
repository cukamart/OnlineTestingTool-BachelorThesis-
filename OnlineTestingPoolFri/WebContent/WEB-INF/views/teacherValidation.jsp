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
	<link href="<c:url value="/resources/css/question.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/navbar.css"/>" rel="stylesheet" type="text/css" >
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container">
	    <div class="jumbotron row marginTop col-md-8 col-md-offset-2">
	    
	    	<c:forEach var="q" items="${questions}" varStatus="i">
	    		<p>${q.ot_znenie}</p>
	    		
	    		<c:forEach var="ans" items="${q.answers}" varStatus="loop">
							       
					<c:if test="${q.answers[loop.index].answerCategory.sko_id eq 1}">
					    <span class="correct"><c:out value="${loop.index+1}"></c:out> ${q.answers[loop.index].od_znenie}<br/></span>
					</c:if>
					
					<c:if test="${q.answers[loop.index].answerCategory.sko_id eq 0}">
					    <span class="inCorrect"><c:out value="${loop.index+1}"></c:out> ${q.answers[loop.index].od_znenie}<br/></span>
					</c:if>
				</c:forEach>
				
				<br/>
				<c:if test="${q.ot_typ eq 'text'}">
					<b><span class="bg-info"><s:message code='Teacher.Validation.SAnswer'/> ${studentQuestions[i.index].sot_textodpoved}</span><br/>
						<a href="<c:url value="/editStudentQuestion/${studentTest.ste_id}/${studentQuestions[i.index].sot_ot_id}"/>">
							<span class="glyphicon glyphicon-pencil"></span>
							<s:message code='Teacher.Validation.Body'/> ${studentQuestions[i.index].sot_body} / ${q.ot_body}</a></b><br/>
				</c:if>
				<c:if test="${q.ot_typ eq 'ABCD'}">
					<b><span class="bg-info"><s:message code='Teacher.Validation.SAnswer'/> ${answers[i.index]}</span><br/>
						<a href="<c:url value="/editStudentQuestion/${studentTest.ste_id}/${studentQuestions[i.index].sot_ot_id}?max=${q.ot_body}"/>">
							<span class="glyphicon glyphicon-pencil"></span>
							<s:message code='Teacher.Validation.Body'/> ${studentQuestions[i.index].sot_body} / ${q.ot_body}</a></b><br/>
				</c:if>
	    		<hr/>
	    	</c:forEach>
	    	
	    	<a class="btn btn-info btn-lg confirm pull-right" href="<c:url value="/checkedTest?studTestId=${studentTest.ste_id}"/>"><s:message code='Teacher.Validation.BtChecked'/></a>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
</body>
</html>