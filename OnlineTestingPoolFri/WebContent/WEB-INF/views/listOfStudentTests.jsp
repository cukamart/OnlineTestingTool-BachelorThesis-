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
	
	<script src="<c:url value="resources/js/sorttable.js"/>"></script>
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	        <div class="jumbotron col-md-6 col-md-offset-3">
	        
	        	<fieldset>					
						<legend class="center"><s:message code='Teacher.Valuation.Unchecked'/>
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
				</fieldset>
	        	<table class="table sortable table-striped">
	        		<thead><tr>
	        			<th><s:message code='Teacher.Valuation.Oprav'/>
	        			</th><th><s:message code='Teacher.Valuation.Nazov'/></th><th><s:message code='Teacher.Valuation.Student'/></th>
	        			<th><s:message code='Teacher.Valuation.Body'/></th>
	        		</tr></thead>
	        	<c:forEach var="st" items="${studentTests}">
	        		<tr>
	        			<td>
							<a href="<c:url value="/teacherValidation/${st.ste_id}"/>">
								<span class="glyphicon glyphicon-pencil"></span></a>
					    </td>
	        			<c:forEach var="t" items="${tests}">
	        				<c:if test="${t.te_id eq st.ste_te_id}">
	        					<td>${t.te_nazov}</td>
	        				</c:if>
	        			</c:forEach>
	        			<c:forEach var="s" items="${students}">
	        				<c:if test="${s.st_id eq st.ste_st_id}">
	        					<td>${s.st_meno} ${s.st_priezv}</td>
	        				</c:if>
	        			</c:forEach>
	        			<td>${st.ste_vysledok}</td>
	        		</tr>
	        	</c:forEach>
	        	</table>
	        </div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	<script type="text/javascript">
		MYLIBRARY.init(["valuation", "valuationActive"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>