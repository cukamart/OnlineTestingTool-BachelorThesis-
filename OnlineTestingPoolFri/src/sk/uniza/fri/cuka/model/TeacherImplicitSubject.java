package sk.uniza.fri.cuka.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.service.SubjectService;
import sk.uniza.fri.cuka.service.TeacherService;

@Component
public class TeacherImplicitSubject {

	@Autowired
	SubjectService subjectService;

	@Autowired
	TeacherService teacherService;

	@Autowired
	LdapInfo ldapInfo;

	/**
	 * Prida do modelu Predmet a Ucitela. Kedze java predava Objektove typy cez referenciu, 
	 * zmeny v metode budu aj mimo nej (nemusim model zbytocne vracat)
	 */
	public Subject addTeacherAndImplicitSubjectToModel(Model model) {
		Teacher teacher = teacherService.getTeacherByLogin(ldapInfo.getLdapLogin());
		Subject subject = subjectService.getSubjectById(teacher.getUc_pr_id());

		model.addAttribute("teacher", teacher);
		model.addAttribute("subject", subject);
		
		return subject;
	}
}
