package sk.uniza.fri.cuka.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 
 * 
 * @author Martin Cuka
 * Vytvori DelegatingFilterProxy bean ktoreho meno bude springSecurityFilterChain.
 * Ekvivalentna xml konfiguracia:
 * 
 * <filter>
		<display-name>springSecurityFilterChain</display-name>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
 *
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
