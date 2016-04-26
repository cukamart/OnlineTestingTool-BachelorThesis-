<header>
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
        
            <div class="navbar-header">
            
                <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <a href="<c:url value="/"/>" class="navbar-brand logo"><span class="glyphicon glyphicon-education"></span> <fmt:message key='Menu.Logo' /></a>
                
            </div>
	
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                
                    <li id="home"><a href="<c:url value="/index"/>"><fmt:message key='Menu.Domov' /></a></li>
                    
                    <li id="tests" class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><fmt:message key='Menu.Testy' /><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			            	<li id="testPreview"><a href="<c:url value="/viewTests"/>"><fmt:message key='Menu.Testy.Prehlad' /></a></li>
			                <li id="testCreate"><a href="<c:url value="/createTest"/>"><fmt:message key='Menu.Testy.Vytvor' /></a></li>
			                <li role="separator" class="divider"></li>
			                <li id="specificTestCreate"><a href="<c:url value="/createSpecificTest"/>"><fmt:message key='Menu.Test.Vytvor' /></a></li>
			                <li id="viewTest"><a href="<c:url value="/viewSpecificTests"/>"><fmt:message key='Menu.Test.Prehlad' /></a></li>
			            </ul>
			        </li>
			        
			        <li id="questions" class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><fmt:message key='Menu.Otazky' /><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			            	<li id="questionPreview"><a href="<c:url value="/viewQuestions"/>"><fmt:message key='Menu.Otazky.Prehlad' /></a></li>
			                <li id="questionCreate"><a href="<c:url value="/createQuestion"/>"><fmt:message key='Menu.Otazky.Vytvor' /></a></li>
			                <li id="questionChange"><a href="<c:url value="/questionChange"/>"><fmt:message key='Menu.Otazky.Presun' /></a></li>
			            </ul>
			        </li>
			        
			        <li id="valuation" class="dropdown">
			        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><fmt:message key='Menu.Valuation' /><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			        		<li id="valuationActive"><a href="<c:url value="/listOfStudentTests"/>"><fmt:message key='Menu.Active.Tests' /></a></li>
			                <li id="valuationArchive"><a href="<c:url value="/archiveTest"/>"><fmt:message key='Menu.Archrive.Test' /></a></li>
			            </ul>
       				
                </ul>
                <p id="logout" class="navbar-text navbar-right">
                	<a href="<c:url value="/logout"/>" class="navbar-link"><fmt:message key='Menu.Logout' />
                		<c:out value="${teacher.uc_meno} ${teacher.uc_priezv}"></c:out></a></p>
            </div>
        </div>
    </div>
</header>
	