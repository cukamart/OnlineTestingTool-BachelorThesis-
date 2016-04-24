package sk.uniza.fri.cuka.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.AnswerCategoryDao;
import sk.uniza.fri.cuka.data.entity.AnswerCategory;

@Service
public class AnswerCategoryService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AnswerCategoryDao answerCategoryDao;
	
	public AnswerCategory findById(int acId) {
		Session session = sessionFactory.openSession();
		answerCategoryDao.setSession(session);
		
		AnswerCategory ac = answerCategoryDao.findById(acId);
		
		session.close();
		
		return ac;
	}
}
