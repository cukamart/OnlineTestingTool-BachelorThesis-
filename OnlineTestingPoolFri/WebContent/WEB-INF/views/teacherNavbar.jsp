<header>
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
        
            <div class="navbar-header">
            
                <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <a href="<c:url value="/"/>" class="navbar-brand logo"><span class="glyphicon glyphicon-education"></span>
                	<s:message code='Menu.Logo'/></a>
                
            </div>
	
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                
                    <li id="home"><a href="<c:url value="/index"/>"><s:message code='Menu.Domov'/></a></li>
                    
                    <li id="tests" class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><s:message code='Menu.Testy'/><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			            	<li id="testPreview"><a href="<c:url value="/viewTests"/>"><s:message code='Menu.Testy.Prehlad'/></a></li>
			                <li id="testCreate"><a href="<c:url value="/createTest"/>"><s:message code='Menu.Testy.Vytvor'/></a></li>
			                <li role="separator" class="divider"></li>
			                <li id="specificTestCreate"><a href="<c:url value="/createSpecificTest"/>"><s:message code='Menu.Test.Vytvor'/></a></li>
			                <li id="viewTest"><a href="<c:url value="/viewSpecificTests"/>"><s:message code='Menu.Test.Prehlad'/></a></li>
			            </ul>
			        </li>
			        
			        <li id="questions" class="dropdown">
                    	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><s:message code='Menu.Otazky'/><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			            	<li id="questionPreview"><a href="<c:url value="/viewQuestions"/>"><s:message code='Menu.Otazky.Prehlad'/></a></li>
			                <li id="questionCreate"><a href="<c:url value="/createQuestion"/>"><s:message code='Menu.Otazky.Vytvor'/></a></li>
			                <li id="questionChange"><a href="<c:url value="/questionChange"/>"><s:message code='Menu.Otazky.Presun'/></a></li>
			            </ul>
			        </li>
			        
			        <li id="valuation" class="dropdown">
			        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
			            		aria-expanded="false"><s:message code='Menu.Valuation'/><span class="caret"></span></a>
			            <ul class="dropdown-menu scrollable-menu">
			        		<li id="valuationActive"><a href="<c:url value="/listOfStudentTests"/>"><s:message code='Menu.Active.Tests'/></a></li>
			                <li id="valuationArchive"><a href="<c:url value="/archiveTest"/>"><s:message code='Menu.Archive.Tests'/></a></li>
			            </ul>
       				
                </ul>
                <p id="logout" class="navbar-text navbar-right">
                	<a href="<c:url value="/logout"/>" class="navbar-link"><s:message code='Menu.Logout'/>
                		<c:out value="${teacher.uc_meno} ${teacher.uc_priezv}"></c:out></a></p>
            </div>
        </div>
    </div>
</header>
	