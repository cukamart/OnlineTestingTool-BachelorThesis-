package sk.uniza.fri.cuka.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.SubjectDao;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@Repository("subjectDao")
public class SubjectDaoImpl extends AbstractDao<Subject, String>implements SubjectDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectTest> getSubjectTestsForSubjectById(String subjectId) {
		Query query = session.createQuery("from SubjectTest st INNER JOIN st.subject s WHERE s.pr_id = :subjectId");
		query.setParameter("subjectId", subjectId);

		List<Object[]> objs = query.list();

		List<SubjectTest> subjectTests = new ArrayList<>();

		for (Object[] object : objs) {
			subjectTests.add((SubjectTest) object[0]);
		}

		return subjectTests;
	}
}
