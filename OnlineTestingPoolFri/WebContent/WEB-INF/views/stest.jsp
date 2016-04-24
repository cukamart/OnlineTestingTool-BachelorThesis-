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
	<link href="<c:url value="/resources/css/testTable.css"/>" rel="stylesheet" type="text/css" >
	
	<script src="<c:url value="resources/js/sorttable.js"/>"></script>
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="studentNavbar.jsp"%>
	
	<div class="container-fluid" id="timeContainer">
	    <div class="row marginTop marginLeft">
	    	
	    	<div class="col-md-4 col-md-offset-4">
	    		<c:if test="${param.error != null}">
	        		<div class="alert alert-danger center" id="alert-error" role="alert"><fmt:message key='STest.Chyba' /></div>
	        	</c:if>
	    	</div>
	    	
	        <div class="jumbotron col-md-10 col-md-offset-1">
	        
				<p class="center"><span class="bg-info">Zoznam vypísaných testov na <b>šk.rok ${schoolYear}</b> na predmety:
					<c:forEach var="s" items="${subjects}">
						<b><c:out value="${s.pr_nazov} [${s.pr_id}]"></c:out></b>
					</c:forEach></span>
				</p>
				
				<div class="col-md-6 col-md-offset-3">
				
				<table class="table sortable">
					<tr><th></th><th>Predmet</th><th>Názov Testu</th></tr>
					<c:forEach var="rt" items="${relevantTests}" varStatus="i">
						<c:if test="${rt.te_pokusov ne pokusov[i.index]}">	
						
							<tr data-link="row" class="rowlink openDialog myRow" data-toggle="modal" data-target="#myModal" data-id="${rt.te_id}"
								 data-nazov="${rt.te_nazov}" data-pokusov="${rt.te_pokusov}" data-my="${pokusov[i.index]}">
								 <td><span class="glyphicon glyphicon-menu-right"></span></td>
								 <td>${rt.te_pr_id}</td>
								 <td>${rt.te_nazov}</td></tr>

						</c:if>
					</c:forEach>
				</table>
				</div>	
				
				<!-- Modalne Okno -->
	        	<form:form method="post"  action="${pageContext.request.contextPath}/test">
	        	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					 <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
						      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      <h4 class="modal-title" id="myModalLabel">Heslo k testu - <div id="tName"></div></h4>
					      </div>
					      <div class="modal-body">
					      	<div class="input-group">
					      		<div id="tPokusov"></div>
					      		<fmt:message key='STest.LHeslo' /> <input name="password" type="text" id="password" value=""/>
					      	</div>
						 	<input name="testId" type="hidden" id="testId" value=""/>
					      </div>
					      <div class="modal-footer">
					          <button type="button" class="btn btn-info" data-dismiss="modal"><fmt:message key='STest.BtnBack' /></button>
							  <input  class="btn btn-success" type="submit" value="Potvrdiť"/>
					      </div>
					    </div>
					  </div>
					</div>
				</form:form>
				
	    	</div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	<script type="text/javascript" src="<c:url value="resources/js/fadeOut.js"/>"></script>
	<script type="text/javascript">
		MYLIBRARY.init(["test"]);
   		MYLIBRARY.activeNavbar();
   		
   	// posunie test modalnemu oknu
   		$(document).on("click", ".openDialog", function () {
   	    	var testId = $(this).data('id');
   	    	var nazov =  $(this).data('nazov');
   	    	var pokusov = $(this).data('pokusov');
   	    	var myPokusov = $(this).data('my');
   	    	
   	    	var div = document.getElementById('tName');
   	    	var pok = document.getElementById('tPokusov');
   	    	
   	    	$(".modal-body #testId").val( testId );
   	    	
   	    	if (pokusov > 0) {
   	    		pok.innerHTML = "Počet pokusov: " + pokusov + " počet minutých pokusov: " + myPokusov + "<br />" + "<br />";
   	    	} else {
   	    		pok.innerHTML = "Počet pokusov: neobmedzené" + "<br />" + "<br />";
   	    	}
   	    	
   	    	
   	    	div.innerHTML = nazov;
   	    	
   		});
	</script>
</body>
</html>