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
	
	<title>Skúšací systém FRI</title>
</head>

<body>

	<%@ include file="teacherNavbar.jsp"%>
	
	<div class="container-fluid">
	    <div class="row paddingTopNavbar marginTop">
	        <div class="jumbotron col-md-8 col-md-offset-2">
				
				<form:form id="productForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/addAnswer" commandName="question">
				
					<fieldset>					
						<legend class="center"><fmt:message key='Question.Vytvorit.Nadpis' />
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
					</fieldset>
					
					<!-- znenie -->
					<div class="form-group">
						<label for="inputQuestion" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Question.Vytvorit.LZadanie' /></label>
						<div class="col-sm-9">
					    	<form:textarea path="ot_znenie" type="text" class="form-control" id="inputQuestion"
					    		placeholder="Zadajte znenie otazky"/>
					    	<form:errors path="ot_znenie" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- typ_otazky -->
					<div class="form-group">
					    <label for="inputQuestionType" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='Question.Vytvorit.LTyp'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="ot_typ" class="form-control" id="inputQuestionType">
					            <option value="ABCD">ABCD</option>
					            <option value="text">text</option>
					        </form:select>
					        <form:errors path="ot_typ" cssClass="error"></form:errors>
					    </div>
					</div>

					<!-- body -->
					<div class="form-group">
						<label for="inputPoints" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Question.Vytvorit.LBody' /></label>
						<div class="col-sm-9">
					    	<form:input path="ot_body" type="number" pattern="[0-9]" class="form-control" id="inputPoints"
					    		placeholder="Počet bodov za správne zodpovedanú otázku"/>
					    	<form:errors path="ot_body" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- ku ktoremu testu patri -->
					<div class="form-group">
					    <label for="inputTestType" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='Question.Vytvorit.LTest'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="ot_pr_te_id" class="form-control" id="inputTestType">
					            <c:forEach var="st" items="${subjectTests}">
					            	<option value="${st.id}">${st.nazov}</option>
					            </c:forEach>
					        </form:select>
					        <form:errors path="ot_pr_te_id" cssClass="error"></form:errors>
					    </div>
					</div>
					
					<!-- ID predmetu (read only) -->
					<div class="form-group">
						<label for="inputSubject" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Question.Vytvorit.LIdPr' /></label>
						<div class="col-sm-9">
					    	<form:input path="ot_pr_id" type="text" class="form-control" id="inputSubject" readonly="true" value="${subject.pr_id}"/>
					    	<form:errors path="ot_pr_id" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- je otazka aktina ? -->
					<div class="form-group">
						<label for="inputActive" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Question.Vytvorit.LActive' /></label>
						<div class="col-sm-9">
					    	<form:input path="ot_aktivna" type="text" class="form-control" id="inputActive" readonly="true" value="A"/>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-info btn-lg confirm"><fmt:message key='Question.Vytvorit.BtnVytvorit' /></button>
						</div>
					</div>
										
				</form:form>

	    	</div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	
	<script type="text/javascript">
		MYLIBRARY.init(["questions", "questionCreate"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>