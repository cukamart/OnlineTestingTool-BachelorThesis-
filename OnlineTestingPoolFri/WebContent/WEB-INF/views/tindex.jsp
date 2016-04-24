<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid" id="timeContainer">
	    <div class="row marginTop marginLeft">

	        <div class="jumbotron col-md-10">
				<p><b><c:out value="${teacher.uc_meno} ${teacher.uc_priezv}, "></c:out><fmt:message key='TIndex.Predmety' /></b></p>
				<c:forEach var="subject" items="${subjects}">
				
					<c:if test="${subject.pr_id eq teacher.uc_pr_id}">
	        			<p class="red underline"><b><c:out value="${subject.pr_nazov}"></c:out></b></p>
	        		</c:if>
	        		
	        		<c:if test="${subject.pr_id ne teacher.uc_pr_id}">
	        			<form:form method="post">
	        				<p><button type="submit" name="subjectId" value="${subject.pr_id}" class="btn-link"><c:out value="${subject.pr_nazov}"></c:out></button></p>
	        			</form:form>
	        		</c:if>
	        		
	    		</c:forEach>
	    	</div>
	    	
	    	<div class="col-md-2 center">
	    		<p><fmt:message key='SIndex.SchoolYear' /><c:out value=" ${schoolYear}"></c:out></p>
	    		
	            <jsp:useBean id="date" class="java.util.Date" />
				<p><fmt:formatDate type="both" value="${date}" /></p>
				
				<p><fmt:message key='TIndex.ImplPredmet' /></p>
				
				<c:forEach var="subject" items="${subjects}">
					<c:if test="${subject.pr_id eq teacher.uc_pr_id}">
        				<p><b><c:out value="${subject.pr_nazov}"></c:out></b></p>
        			</c:if>
				</c:forEach>
	        </div>
	        
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	<script type="text/javascript">
		MYLIBRARY.init(["home"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>