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
	    <div class="jumbotron row marginTop col-md-8 col-md-offset-2">
	    
	    	<c:if test="${param.confirm != null}">
	    		<form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/editedStudentQuestion">
	    		<div class="alert alert-info" role="alert">
					<p>Naozaj chcete priradiť <b>${param.points}</b> bodov ?</p>
					<p>Maximálny počet bodov za otázku je <b>${question.ot_body}</b>, aj napriek tomu body priradiť ?</p><br/>
					<input name="studentTestId" type="hidden" value="${studentTestId}"/>
	    			<input name="studentQuestionId" type="hidden" value="${studentQuestionId}"/>
	    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    			<input type="hidden" name="points" value="${param.points}"/>
	    			<input type="hidden" name="confirm" value="true"/>
	    			<input type="hidden" name="uc_text" value="${ucText}"/>
					<input class="btn btn-success" type="submit" value='Priradiť body'/> <input class="btn btn-primary pull-right" action="action" type="button" value="<s:message code='StudentQuestion.Spat'/>" onclick="window.history.go(-1); return false;" />
				</div>
				</form>
			</c:if>
	    	
	    	<c:if test="${param.confirm == null}">
	    	<form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/editedStudentQuestion">
	    		<div class="alert alert-info" role="alert">
					<p><span class="glyphicon glyphicon-question-sign">
						</span><c:out value=" ${question.ot_znenie}"></c:out></p><hr/>
						<p><span><c:out value="MAX: ${question.ot_body}"></c:out></span></p>
				</div>
	    		<p><s:message code='StudentQuestion.Body'/><input name="points" type="number" value="${studentQuestion.sot_body_new}" required/></p>
	    			<p>Komentár: &nbsp;&nbsp;&nbsp;&nbsp;<textarea rows="4" cols="40" name="uc_text">${studentQuestion.sot_uc_text}</textarea></p>
	    			<p><input class="btn btn-success pull-left" type="submit" value="<s:message code='StudentQuestion.Uprav'/>"/></p>
	    		<input name="studentTestId" type="hidden" value="${studentTestId}"/>
	    		<input name="studentQuestionId" type="hidden" value="${studentQuestionId}"/>
	    		<input type="hidden" name="confirm" value="false"/>
	    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	    		
	    	</form>
	    	<input class="btn btn-primary pull-right" action="action" type="button" value="<s:message code='StudentQuestion.Spat'/>" onclick="window.history.go(-1); return false;" />
	    	</c:if>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
</body>
</html>