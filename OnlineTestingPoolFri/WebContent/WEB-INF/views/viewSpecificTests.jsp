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
	<link href="<c:url value="/resources/css/toglePassword.css"/>" rel="stylesheet" type="text/css" >
	
	<script src="<c:url value="resources/js/sorttable.js"/>"></script>
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	        <div class="jumbotron col-md-6 col-md-offset-3">
	        
	        	<div class="center">
					<c:if test="${param.error != null}">
						<div class="alert alert-danger bold" id="alert-error" role="alert"><fmt:message key='STest.Preview.Error' /></div>
					</c:if>
				</div>
	        
		        <fieldset>					
					<legend class="center"><fmt:message key='STest.Preview.TableLegend' /> <i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
				</fieldset>
		       <table class="table sortable table-striped">
		       	<thead><tr><th></th><th>Názov</th><th>Otvorený od</th><th>Otvorený do:</th><th>Heslo</th><th>Typ</th></tr></thead>
			       <c:forEach var="te" items="${tests}" varStatus="loop">
			       
				   <tr><td></td><td>${te.te_nazov}</td><td>${te.te_datum_zac} ${te.te_cas_zac}</td>
				       <td>${te.te_datum_kon} ${te.te_cas_kon}</td>
				       <td><input readonly type="password" id="heslo_${loop.index}" name="password" class="showpassword togglePassword" value="${te.te_heslo}" /><button id="oko_${loop.index}" onclick="showHide(${loop.index})"><span class="glyphicon glyphicon-eye-open"></span></button></td>
				       <td>${subjectTests[loop.index].nazov}</td></tr>

			       </c:forEach>
		       </table>
		       
	        
	        </div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	<script type="text/javascript">
		MYLIBRARY.init(["tests", "viewTest"]);
   		MYLIBRARY.activeNavbar();
   		
   		function showHide(value) {
   			var d = document;
   			var imput = d.getElementById('heslo_' + value);
   			var oko = d.getElementById('oko_' + value);
   			
   			if (oko.innerHTML === '<span class="glyphicon glyphicon-eye-open"></span>') {
   		        oko.innerHTML = '<span class="glyphicon glyphicon-eye-close"></span>';
   		     	imput.setAttribute('type', 'text');
   		     	
   		      } else {
   		    	oko.innerHTML = '<span class="glyphicon glyphicon-eye-open"></span>';
   		     	imput.setAttribute('type', 'password');
   		      }
   		}
   		
	</script>
</body>
</html>