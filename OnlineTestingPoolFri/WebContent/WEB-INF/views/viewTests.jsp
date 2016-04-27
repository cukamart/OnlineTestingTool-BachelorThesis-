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
						<legend class="center"><s:message code='Test.Preview.TableLegend'/>
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
				</fieldset>
	        	<table class="table sortable table-striped">
	        		<thead><tr>
	        			<th></th><th><s:message code='Test.Preview.Skrok'/></th><th><s:message code='Test.Preview.PredmId'/></th>
	        			<th><s:message code='Test.Preview.Nazov'/></th><th><s:message code='Test.Preview.Poradie'/></th>
	        			<th><s:message code='Test.Preview.Min'/></th><th><s:message code='Test.Preview.Max'/></th>
	        			<th><s:message code='Test.Preview.Sum'/></th><th><s:message code='Test.Preview.Penale'/></th>
	        		</tr></thead>
	        	<c:forEach var="st" items="${subjectTests}" varStatus="i">
	        	
	        		<c:if test="${subjectTest.id eq st.id}">
	        		
		        		<tr class="success"><td><button id="glyphBtn" type="button" class="glyphicon glyphicon-trash openDialog" data-toggle="modal" 
						        		data-id="${st.id}" data-znenie="${st.nazov}" data-warning="0" data-target="#myModal"></button></td>
						    <td>${st.skrok}</td><td>${st.pr_id}</td><td>${st.nazov}</td><td>${st.por}</td>
		        			<td>${st.min}</td><td>${st.max}</td><td>${st.sum}</td><td>${st.penale}</td></tr>
		        			
	        		</c:if>
	        		
	        		<c:if test="${subjectTest.id ne st.id}">
	        		
	        		<tr><td>
	        				<c:if test="${usageList[i.index] eq false}">
	        					<button id="glyphBtn" type="button" class="glyphicon glyphicon-trash openDialog" data-toggle="modal" 
						        			data-id="${st.id}" data-znenie="${st.nazov}" data-warning="0" data-target="#myModal"></button>
						    </c:if>
						    <c:if test="${usageList[i.index] eq true}">
	        					<button id="glyphBtn" type="button" class="glyphicon red glyphicon-ban-circle openDialog" data-toggle="modal" 
						        			data-id="${st.id}" data-znenie="${st.nazov}" data-warning="1" data-target="#myModal"></button>
						    </c:if>
						</td>
	        			<td>${st.skrok}</td><td>${st.pr_id}</td><td>${st.nazov}</td><td>${st.por}</td>
	        			<td>${st.min}</td><td>${st.sum}</td><td>${st.max}</td><td>${st.penale}</td></tr>
	        			
	        		</c:if>
	        	</c:forEach>
	        	</table>
	        	
	        	<!-- Modalne Okno -->
	        	<form:form method="post"  action="${pageContext.request.contextPath}/deletePredmTest">
	        	   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					 <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
						      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      <h4 class="modal-title" id="myModalLabel"><s:message code='Test.Preview.Vymazat'/></h4>
					      </div>
					      <div class="modal-body">
					      	<div id="qName" class="alert alert-danger"></div>
						 	<input name="testId" type="hidden" id="testId" value=""/>
					      </div>
					      <div class="modal-footer">
					          <button type="button" class="btn btn-info" data-dismiss="modal"><s:message code='Test.Preview.BtnZatvorit'/></button>
							  <input  class="btn btn-danger" type="submit" value="<s:message code='Test.Preview.BtnVymazat'/>"/>
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
	<script type="text/javascript">
		MYLIBRARY.init(["tests", "testPreview"]);
   		MYLIBRARY.activeNavbar();
   		
   	// posunie predmTest modalnemu oknu
   		$(document).on("click", ".openDialog", function () {
   	    	var testId = $(this).data('id');
   	    	var znenie =  $(this).data('znenie');
   	    	var warning =  $(this).data('warning');
   	    	var div = document.getElementById('qName');
   	    	$(".modal-body #testId").val( testId );
   	    	if (warning === 0){
   	    		div.innerHTML = znenie + "<b> - vymaže aj všetky otázky priradené k testu, v prípade, že otázky nechcete vymazať najprv ich preraďte inému testu !</b>";
   	    	} else {
   	    		div.innerHTML = znenie + "<b> - k typu testu sú už priradené aj testy študentov !! Vymazaním vymažete aj všetky testy študentov tohto typu !</b>";
   	    	}
   		});
	</script>
</body>
</html>