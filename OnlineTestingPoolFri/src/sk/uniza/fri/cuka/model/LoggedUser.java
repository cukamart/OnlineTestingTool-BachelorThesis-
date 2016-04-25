package sk.uniza.fri.cuka.model;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.enums.Roles;

@Component
public class LoggedUser {

	@Autowired
	LdapInfo ldapInfo;

	public String whereToGo() {
		Collection<? extends GrantedAuthority> roles = ldapInfo.getFullRole();

		for (GrantedAuthority role : roles) {

			if (role.toString().equals(Roles.TEACHER.getValue())) {
				return "index";
			}

			if (role.toString().equals(Roles.STUDENT.getValue())) {
				return "sindex";
			}
		}
		
		return "unknownRole";
	}
}
