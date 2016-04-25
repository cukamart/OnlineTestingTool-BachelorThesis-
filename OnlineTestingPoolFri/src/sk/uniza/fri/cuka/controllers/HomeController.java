package sk.uniza.fri.cuka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.uniza.fri.cuka.ldap.LdapInfo;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	LdapInfo ldapInfo;

	@RequestMapping(method = RequestMethod.GET)
	public String showHome(Model model) {
		model.addAttribute("name", ldapInfo.getLdapLogin());

		return "home";
	}
}
