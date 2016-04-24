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
                
                    <li id="home"><a href="<c:url value="/sindex"/>"><fmt:message key='Menu.Domov' /></a></li>
                    <li id="test"><a href="<c:url value="/stest"/>"><fmt:message key='Menu.Testy' /></a></li>
                    
       				
                </ul>
                <p id="logout" class="navbar-text navbar-right">
                	<a href="<c:url value="/logout"/>" class="navbar-link"><fmt:message key='Menu.Logout' />
                		<c:out value="${student.st_meno} ${student.st_priezv}"></c:out></a></p>
            </div>
        </div>
    </div>
</header>
	