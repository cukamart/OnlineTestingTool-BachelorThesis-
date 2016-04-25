package sk.uniza.fri.cuka.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.uniza.fri.cuka.model.LoggedUser;

@Controller
public class LoginController {

	@Autowired
	LoggedUser loggedUser;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/whoAmI", method = RequestMethod.GET)
	public String whoAmI() {
		return "redirect:" + loggedUser.whereToGo();
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
