<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
	
	<script src="<c:url value="resources/js/sorttable.js"/>"></script>
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="studentNavbar.jsp"%>
	
	<div class="container-fluid" id="timeContainer">
	    <div class="row marginTop marginLeft">

	        <div class="jumbotron col-md-10">
				<p><b><c:out value="${student.st_meno} ${student.st_priezv}, "></c:out>
					<s:message code='Student.SIndex.Vysledky'/></b></p>
				
				<c:if test="${fn:length(studentTests) eq 0}">
					<p><s:message code='SIndex.ZiadneTesty'/></p>
				</c:if>
				
				
				<c:if test="${fn:length(studentTests) ne 0}">
				
					<fieldset>					
						<legend class="center"><s:message code='Student.SIndex.TableLegend'/></legend>
					</fieldset>
					
	        		<table class="table sortable table-striped">
	        			<thead><tr><th><s:message code='Student.SIndex.Predmet'/></th><th><s:message code='Student.SIndex.Nazov'/></th>
	        			<th><s:message code='Student.SIndex.Body'/></th><th><s:message code='Student.SIndex.Max'/></th>
	        				<th><s:message code='Student.SIndex.Vypracovane'/></th><th><s:message code='Student.SIndex.Stav'/></th>
	        			</tr></thead>
	        			
					<c:forEach var="st" items="${studentTests}" varStatus="i">
						<tr>
							<td>${tests[i.index].te_pr_id}</td><td>${tests[i.index].te_nazov}</td>
							<td class="bold">${st.ste_vysledok}</td><td class="bold">${st.ste_plnypocet}</td>
							<td><c:set var="myDate" value="${st.ste_koniec}"/>${fn:substring(myDate, 0, 19)}</td>
							<td>
								<c:if test="${st.ste_kontrola eq 'false'}">
									<s:message code='Student.SIndex.Neopraveny' />
								</c:if>
								<c:if test="${st.ste_kontrola eq 'true'}">
									<s:message code='Student.SIndex.Opraveny' />
								</c:if>
							</td>		
						</tr>
	    			</c:forEach>
	    			
	    			</table>
	    		</c:if>
				
	    	</div>
	    		    	
	    	<div class="col-md-2 center">
	    		<p><s:message code='SIndex.SchoolYear' /><c:out value=" ${schoolYear}"></c:out></p>
	    		
	            <jsp:useBean id="date" class="java.util.Date" />
				<p><fmt:formatDate type="both" value="${date}" /></p>
				
				<p class="underline"><s:message code='SIndex.ZapisanePredmety' /></p>
				
				<c:forEach var="s" items="${subjects}">
        			<p><i><c:out value="${s.pr_nazov}"></c:out></i></p>
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