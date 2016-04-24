package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.RegisteredSubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentDaoImpl;
import sk.uniza.fri.cuka.data.entity.RegisteredSubject;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;

@Service
public class StudentService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentDaoImpl studentDaoImpl;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	@Autowired
	private RegisteredSubjectDaoImpl registeredSubjectDaoImpl;

	public Student getStudentByLogin(String login) {
		Session session = sessionFactory.openSession();
		studentDaoImpl.setSession(session);

		Student student = studentDaoImpl.getStudentByLogin(login);

		session.close();

		return student;
	}

	public List<RegisteredSubject> getStudentsRegisteredSubjectsCurrentSchoolYear(Long stId) {
		Session session = sessionFactory.openSession();
		registeredSubjectDaoImpl.setSession(session);

		List<RegisteredSubject> registeredSubjects = registeredSubjectDaoImpl
				.getStudentsRegisteredSubjectsCurrentSchoolYear(stId, currentSchoolYear.getCurrentSchoolYear());

		session.close();

		return registeredSubjects;
	}

	public Student findById(Long stId) {
		Session session = sessionFactory.openSession();
		studentDaoImpl.setSession(session);

		Student student = studentDaoImpl.findById(stId);

		session.close();

		return student;
	}

	/**
	 * Vrati list konkretnych testov, ktore dany student napisal, ked k tomu pridam .size() tak vlastne zistim pocet studentovych pokusov na
	 * dany test...
	 */
	public List<StudentTest> getStudentTestsByTestId(Long studentId, int testId) {
		Session session = sessionFactory.openSession();
		studentDaoImpl.setSession(session);

		List<StudentTest> studentTests = studentDaoImpl.getStudentTestsByTestId(studentId, testId);

		session.close();

		return studentTests;
	}

	public List<StudentTest> getAllStudentTestsByStudentId(Long studentId) {
		Session session = sessionFactory.openSession();
		studentDaoImpl.setSession(session);
				
		List<StudentTest> studentTests = studentDaoImpl.getAllStudentTestsByStudentId(studentId);
		
		session.close();
		
		return studentTests;
	}

}
