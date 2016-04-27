<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
	<link href="<c:url value="/resources/css/question.css"/>" rel="stylesheet" type="text/css" >
		
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	        <div class="jumbotron col-md-6 col-md-offset-3">
	        
				<p class="lead center"><s:message code='Question.Change.Nazov'/></p>
				
				<div class="col-md-8 col-md-offset-2 bold center">
					<c:if test="${param.question != null}">
						<div class="alert alert-success" role="success"><s:message code='Question.Change.Success' /></div>
					</c:if>
				</div>
				
				<form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/questionChange">
				
					<!-- preradit Z -->
					<div class="form-group">
					    <label for="questionFrom" class="col-sm-3 control-label">
					    	<s:message code='Question.Change.Z'/></label>

					    <div class="col-sm-9 selectContainer">
					        <select name="from" class="form-control" id="questionFrom">
					        	<c:forEach var="st" items="${subjectTests}">
					            	<option value="${st.id}">${st.nazov}</option>
					            </c:forEach>
					        </select>
					    </div>
					</div>
					
					
					<!-- preradit Do -->
					<div class="form-group">
					    <label for="questionTo" class="col-sm-3 control-label">
					    	<s:message code='Question.Change.DO'/></label>

					    <div class="col-sm-9 selectContainer">
					        <select name="to" class="form-control" id="questionTo">
					        	<c:forEach var="st" items="${subjectTests}">
					            	<option value="${st.id}">${st.nazov}</option>
					            </c:forEach>
					        </select>
					    </div>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
					
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-info btn-lg confirm pull-right"><s:message code='Question.Change.Btn'/></button>
						</div>
					</div>
				</form>
				
	        </div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	<script type="text/javascript">
		MYLIBRARY.init(["questions", "questionChange"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>