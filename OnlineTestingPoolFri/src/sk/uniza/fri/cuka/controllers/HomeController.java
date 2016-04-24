package sk.uniza.fri.cuka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.uniza.fri.cuka.ldap.LdapInfo;

@Controller
public class HomeController {

	@Autowired
	LdapInfo ldapInfo;

	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", ldapInfo.getLdapLogin());
		model.addAttribute("simpleRole", ldapInfo.getFullRole());

		return "home";
	}
}
