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
	<link href="<c:url value="/resources/css/countdown.css"/>" rel="stylesheet" type="text/css" >
	<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet" type="text/css" >
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	
	<div class="container">
	    <div class="jumbotron row marginTop">
	    	<p class="center lead"><c:out value="${test.te_nazov}"></c:out></p><hr/>
	    	
	    	<div id="countdown" class="navbar-fixed-top right marginRight"><span class="bg-danger">Čas do konca testu: </span>
	    		<span class="bg-danger" id="time"></span></div>
	    	
	    	<form:form id="myForm" method="post" class="form-horizontal" 
	    		action="${pageContext.request.contextPath}/valuateTest?studentTest=${studentTest.ste_id}" commandName="arrStudentQuestion">
	        <c:forEach var="q" items="${questions}" varStatus="i">
	            <p>${i.count}. ${q.ot_znenie}</p> 
	            
	            <c:if test="${q.ot_typ eq 'text'}">
	            	<form:textarea path="sQuestions[${i.index}].sot_textodpoved" rows="5" cols="60"/>
	            	
	            	<form:input type="hidden" path="sQuestions[${i.index}].sot_ot_id" value="${q.ot_id}"/>

	            </c:if>
	            
	            <c:if test="${q.ot_typ eq 'ABCD'}">
	            	<c:forEach var="ans" items="${q.answers}">
				   		<form:radiobutton path="sQuestions[${i.index}].sot_textodpoved" value="${ans.od_id}"/>${ans.od_znenie}<br>
				   		
				   		<form:input type="hidden" path="sQuestions[${i.index}].sot_ot_id" value="${q.ot_id}"/>
					</c:forEach>
				</c:if>
				
				<br/>
	        </c:forEach>
	        
	        <hr/>
	        <div class="form-group">
					<button class="next" type="submit" class="btn btn-info btn-lg confirm"><fmt:message key='STest.Odoslat.BtnSTest' /></button>
			</div>
			</form:form>
	    </div>
	</div>
	
	<input id="cas" type="hidden" value="${test.te_max_minut}">

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	
	<script type="text/javascript">
	
	function startTimer(duration, display) {
	    var timer = duration, minutes, seconds;
	    setInterval(function () {
	        minutes = parseInt(timer / 60, 10);
	        seconds = parseInt(timer % 60, 10);

	        minutes = minutes < 10 ? "0" + minutes : minutes;
	        seconds = seconds < 10 ? "0" + seconds : seconds;

	        display.text(minutes + ":" + seconds);

	        if (--timer < 0) {
	        	document.getElementById("myForm").submit();
	        }
	    }, 1000);
	}
	
	$( document ).ready(function() {
		var timeLeft = parseInt(document.getElementById("cas").value) * 60;
        display = $('#time');
        if (timeLeft != isNaN){
        	document.getElementById("countdown").innerHTML = "<span class='bg-danger'>Čas neobmedzený !</span>";
        } else {
        	startTimer(timeLeft, display);
        }
	});
	</script>

</body>
</html>