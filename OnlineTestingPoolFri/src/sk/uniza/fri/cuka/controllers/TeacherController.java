package sk.uniza.fri.cuka.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;
import sk.uniza.fri.cuka.service.ManageService;
import sk.uniza.fri.cuka.service.TeacherService;

@Controller
@RequestMapping("/index")
public class TeacherController {

	@Autowired
	private ManageService manageService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private LdapInfo ldapInfo;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	private Teacher teacher;

	@RequestMapping(method = RequestMethod.GET)
	public String showTindex(Model model) {
		teacher = teacherService.getTeacherByLogin(ldapInfo.getLdapLogin());

		List<Subject> subjects = manageService.getSubjectByTeacherIdAndYear(teacher.getUc_id(),
				currentSchoolYear.getCurrentSchoolYear());

		model.addAttribute("teacher", teacher);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schoolYear", currentSchoolYear.getStringRepresentationOfSchoolYear());

		return "tindex";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String setTeacherSubject(HttpServletRequest request) {
		teacherService.setImplicitSubject(teacher, request.getParameter("subjectId"));

		return "redirect:index";
	}

}
