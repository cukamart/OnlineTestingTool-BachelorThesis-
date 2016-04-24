package sk.uniza.fri.cuka.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.enums.Roles;

@Controller
public class LoginController {

	@Autowired
	LdapInfo ldapInfo;

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/whoAmI")
	public String whoAmI() {
		Collection<? extends GrantedAuthority> roles = ldapInfo.getFullRole();

		for (GrantedAuthority role : roles) {

			if (role.toString().equals(Roles.TEACHER.getValue())) {
				return "redirect:index";
			}

			if (role.toString().equals(Roles.STUDENT.getValue())) {
				return "redirect:sindex";
			}
		}
		return "redirect:unknownRole";
	}

	@RequestMapping("/logout")
	public String showLoggedOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "logout";
	}

	@RequestMapping("/unknownRole")
	public String showUnknownRolePage() {
		return "unknownRole";
	}
}
