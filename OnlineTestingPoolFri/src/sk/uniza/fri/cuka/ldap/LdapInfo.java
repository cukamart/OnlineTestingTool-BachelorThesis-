package sk.uniza.fri.cuka.ldap;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LdapInfo {

	/**
	 * 
	 * @return vrati meno prihlaseneho
	 */
	public String getLdapLogin(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	/**
	 * 
	 * @return vrati aku rolu ma prihlaseny (viac informacii)
	 */
	public Collection<? extends GrantedAuthority> getFullRole() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
	/**
	 * 
	 * @return vrati aku rolu ma prihlaseny
	 */
	public SimpleGrantedAuthority getRole() {
		return (SimpleGrantedAuthority) getFullRole().iterator().next();
	}
	
	
}
