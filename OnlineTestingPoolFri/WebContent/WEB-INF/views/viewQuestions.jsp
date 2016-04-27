<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	<script src="<c:url value="resources/js/sorttable.js"/>"></script>
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	        <div class="jumbotron col-md-8 col-md-offset-2">
	        
	        	<fieldset>					
						<legend class="center"><s:message code='Question.Preview.TableLegend' />
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
				</fieldset>
	        	<table class="table sortable table-striped">
	        		<thead><tr><th></th>
	        		<th><s:message code='Question.Preview.Znenie'/></th>
	        			<th><s:message code='Question.Preview.Odpoved'/></th>
	        			<th><s:message code='Question.Preview.Test'/></th>
	        			<th><s:message code='Question.Preview.Body'/></th></tr></thead>
	        		
			        	<c:forEach var="q" items="${questions}" varStatus="stIndex">
				        	<tr>
				        		
					        	<td>
						        	<button id="glyphBtn" type="button" class="glyphicon glyphicon-trash openDialog" data-toggle="modal" 
						        		data-id="${q.ot_id}" data-znenie="${q.ot_znenie}" data-target="#myModal"></button>
					        	</td>
					        	
					        	<td>${q.ot_znenie}</td>
					        	<td>
					        			
							       <c:forEach var="ans" items="${q.answers}" varStatus="loop">
							       
								       <c:if test="${q.answers[loop.index].answerCategory.sko_id eq 1}">
								           <span class="correct"><c:out value="${loop.index+1}"></c:out> ${q.answers[loop.index].od_znenie}<br/></span>
								       </c:if>
								       
								       <c:if test="${q.answers[loop.index].answerCategory.sko_id eq 0}">
								           <span class="inCorrect"><c:out value="${loop.index+1}"></c:out> ${q.answers[loop.index].od_znenie}<br/></span>
								       </c:if>
								       
							       </c:forEach>
					        			
					        	</td>
					        	
					        	<td><c:out value="${subjectTests[stIndex.index].nazov}"></c:out><br/></td>
					        	<td><c:out value="${q.ot_body}"></c:out></td>
				        	</tr>
			        	</c:forEach>
	        	
	        	</table>
	        	
	        	<!-- Modalne Okno -->
	        	<form:form method="post"  action="${pageContext.request.contextPath}/deleteQuestion">
	        	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					 <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
						      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						      <h4 class="modal-title" id="myModalLabel"><s:message code='Question.Preview.Vymazat'/></h4>
					      </div>
					      <div class="modal-body">
					      	<div id="qName" class="alert alert-info"></div>
						 	<input name="questionId" type="hidden" id="questionId" value=""/>
					      </div>
					      <div class="modal-footer">
					          <button type="button" class="btn btn-info" data-dismiss="modal"><s:message code='Question.Preview.Zatvorit'/></button>
							  <input  class="btn btn-danger" type="submit" value="<s:message code='Question.Preview.BtnVymazat'/>"/>
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
		MYLIBRARY.init(["questions", "questionPreview"]);
   		MYLIBRARY.activeNavbar();
   		
   		// posunie otazku modalnemu oknu
   		$(document).on("click", ".openDialog", function () {
   	    	var questionId = $(this).data('id');
   	    	var znenie =  $(this).data('znenie');
   	    	var div = document.getElementById('qName');
   	    	$(".modal-body #questionId").val( questionId );
   	    	div.innerHTML = znenie;
   		});

	</script>
</body>
</html>