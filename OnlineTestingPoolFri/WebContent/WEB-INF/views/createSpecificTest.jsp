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

				<form:form method="post" class="form-horizontal" action="${pageContext.request.contextPath}/viewSpecificTests" commandName="test">
				
					<fieldset>					
						<legend class="center"><fmt:message key='STest.Vytvorit.Nadpis' />
							<i><c:out value="${subject.pr_nazov} [${subject.pr_id}]"></c:out></i></legend>
					</fieldset>
					
					<!-- nazov testu -->
					<div class="form-group">
						<label for="inputName" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='Test.Vytvorit.LNazov' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_nazov" type="text" class="form-control" id="inputName"
					    		placeholder="Názov testu napr. 1. zapocet - 5ZI011 pondelok"/>
					    	<form:errors path="te_nazov" cssClass="error"></form:errors>
						</div>
					</div>
					
					<!-- ku ktoremu typu testu patri -->
					<div class="form-group">
					    <label for="inputTestType" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='STest.Vytvorit.Typ'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="te_typ" class="form-control" id="inputTestType">
					            <c:forEach var="st" items="${subjectTests}">
					            	<option value="${st.id}">${st.nazov}</option>
					            </c:forEach>
					        </form:select>
					    </div>
					</div>
					
					<!-- pocet pokusov na test -->
					<div class="form-group">
						<label data-toggle="pokus" title='<fmt:message key='STest.Vytvorit.ToolTip.Pokus'></fmt:message>'
							for="inputPokus" class="col-sm-3 control-label"> <span class="glyphicon glyphicon-question-sign"></span>
							<fmt:message key='STest.Vytvorit.Pokus' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_pokusov" type="number" class="form-control" id="inputPokus" pattern="[0-9]"
					    		placeholder="Počet pokusov na test"/>
						</div>
					</div>
					
					<!-- datum zaciatok den -->
					<div class="form-group">
						<label for="inputDateStart" class="col-sm-3 control-label">
							<fmt:message key='STest.Vytvorit.DatumZac' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_datum_zac" type="date" class="form-control" id="inputDateStart"/>
						</div>
					</div>
					
					<!-- datum zaciatok cas -->
					<div class="form-group">
						<label for="inputDateStartTime" class="col-sm-3 control-label">
							<fmt:message key='STest.Vytvorit.DatumZacTime' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_cas_zac" type="time" class="form-control" id="inputDateStartTime"/>
						</div>
					</div>
					
					<!-- datum koniec den -->
					<div class="form-group">
						<label for="inputDateKoniec" class="col-sm-3 control-label">
							<fmt:message key='STest.Vytvorit.DatumKon' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_datum_kon" type="date" class="form-control" id="inputDateKoniec"/>
						</div>
					</div>
					
					<!-- datum koniec cas -->
					<div class="form-group">
						<label for="inputDateKoniecTime" class="col-sm-3 control-label">
							<fmt:message key='STest.Vytvorit.DatumKonTime' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_cas_kon" type="time" class="form-control" id="inputDateKoniecTime"/>
						</div>
					</div>
					
					<!-- max minut na test -->
					<div class="form-group">
						<label for="inputMaxMin" class="col-sm-3 control-label">
							<fmt:message key='STest.Vytvorit.MaxMinut' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_max_minut" type="number" class="form-control" id="inputMaxMin" pattern="[0-9]"
					    		placeholder="Zadajte koľko minút je na test"/>
						</div>
					</div>
					
					<!-- 1 spravna -->
					<div class="form-group">
					    <label data-toggle="oneCorrect" title='<fmt:message key='STest.Vytvorit.ToolTip.Jedna'></fmt:message>' 
					    	for="inputJednaSpravna" class="col-sm-3 control-label">
					    	<span class="glyphicon glyphicon-question-sign"></span> <span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='STest.Vytvorit.JednaSpravna'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="te_1_spravna" class="form-control" id="inputJednaSpravna">
					            <option value="A">ÁNO</option>
					            <option value="N">NIE</option>
					        </form:select>
					    </div>
					</div>
					
					<!-- zaporne body -->
					<div class="form-group">
					    <label for="inputZaporne" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='STest.Vytvorit.ZaporneBody'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="te_zapor_body" class="form-control" id="inputZaporne">
					            <option value="N">NIE</option>
					            <option value="A">ÁNO</option>
					        </form:select>
					    </div>
					</div>
					
					<!-- otázka celok -->
					<div class="form-group">
					    <label data-toggle="oneCorrect" title='<fmt:message key='STest.Vytvorit.ToolTip.Celok'></fmt:message>'
					    	for="inputCelok" class="col-sm-3 control-label">
					    	<span class="glyphicon glyphicon-question-sign"></span> <span class="glyphicon glyphicon-asterisk"></span>
					    	<fmt:message key='STest.Vytvorit.OtazkaCelok'></fmt:message></label>
					    <div class="col-sm-9 selectContainer">
					        <form:select path="te_otazka_celok" class="form-control" id="inputCelok">
					            <option value="A">ÁNO</option>
					            <option value="N">NIE</option>
					        </form:select>
					    </div>
					</div>
					
					<!-- heslo -->
					<div class="form-group">
						<label for="inputPass" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
							<fmt:message key='STest.Vytvorit.Password' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_heslo" type="text" class="form-control" id="inputPass" maxlength="20"
					    		placeholder="Heslo ktorým študenti sprístupnia test..."/>
						</div>
						
					</div>
					
					<!-- pouzi skup otazok (read only) -->
					<div class="form-group">
						<label for="inputSkuptazok" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='STest.Vytvorit.SkupOtazok' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_pouz_skup_otazok" type="text" class="form-control" id="inputSkuptazok" readonly="true" value="N"/>
						</div>
					</div>
					
					<!-- ID predmetu (read only) -->
					<div class="form-group">
						<label for="inputSubject" class="col-sm-3 control-label"><span class="glyphicon glyphicon-asterisk"></span>
						<fmt:message key='Test.Vytvorit.LIdPr' /></label>
						<div class="col-sm-9">
					    	<form:input path="te_pr_id" type="text" class="form-control" id="inputSubject" readonly="true" value="${subject.pr_id}"/>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-info btn-lg confirm"><fmt:message key='Test.Vytvorit.BtnVytvorit' /></button>
						</div>
					</div>
					
				</form:form>

	    	</div>
	    </div>
	</div>
				

	<%@ include file="scripts.jsp"%>
	<script type="text/javascript" src="<c:url value="resources/js/active.js"/>"></script>
	
	<script type="text/javascript">
		MYLIBRARY.init(["tests", "specificTestCreate"]);
   		MYLIBRARY.activeNavbar();
	</script>
</body>
</html>