package sk.uniza.fri.cuka.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;
import sk.uniza.fri.cuka.data.entity.Test;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;
import sk.uniza.fri.cuka.service.ManageService;
import sk.uniza.fri.cuka.service.StudentTestService;
import sk.uniza.fri.cuka.service.TeacherService;
import sk.uniza.fri.cuka.service.TestService;

@Controller
@RequestMapping("/json")
public class JsonServerController {

	private Teacher teacher;

	@Autowired
	private LdapInfo ldapInfo;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private ManageService manageService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private StudentTestService studentTestService;

	// JSON Server - vrati json neopravenych testov na konkretne predmety
	@RequestMapping(value = "/uncheckedTests", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUncheckedTests() {
		teacher = teacherService.getTeacherByLogin(ldapInfo.getLdapLogin());

		List<Subject> subjects = manageService.getSubjectByTeacherIdAndYear(teacher.getUc_id(),
				currentSchoolYear.getCurrentSchoolYear()); // ktore predmety vyucuje ucitel na dany skolsky rok

		Map<String, Object> data = new HashMap<String, Object>();

		for (Subject subject : subjects) {
			List<Test> tests = new ArrayList<>();
			tests.addAll(testService.getAllTestsBySubjectId(subject.getPr_id())); // vsetky vypisane testy na dany predmet

			List<StudentTest> studentTests = new ArrayList<>();

			for (Test t : tests) {
				studentTests.addAll(studentTestService.getAllActiveStudentTestsByTestId(t.getTe_id())); // testy studentov (neopravene)
			}

			data.put(subject.getPr_id(), studentTests.size());
		}

		return data;
	}
}
