package sk.uniza.fri.cuka.data.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import sk.uniza.fri.cuka.data.dao.interfaces.Dao;

public class AbstractDao<T, ID extends Serializable> implements Dao<T, ID> {

	@Autowired
	protected SessionFactory sessionFactory;

	private Class<T> persistentClass;

	protected Session session;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		T object = (T) session.get(this.getPersistentClass(), id);

		return object;
	}

	@Override
	public List<T> findAll() {
		List<T> list = this.findByCriteria(session);

		return list;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Session session, Criterion... criterion) {
		Criteria crit = session.createCriteria(this.getPersistentClass());

		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (List<T>) crit.list();
	}

	@Override
	public T create(T entity) {
		session.saveOrUpdate(entity);

		return entity;
	}

	@Override
	public void delete(T entity) {
		session.delete(entity);
	}

	@Override
	public void deleteTable() {
		String deleteTable = "delete " + this.getPersistentClass().getName();

		Query query = session.createQuery(deleteTable);
		query.executeUpdate();
	}

	protected Session getSession() {
		if (this.session == null) {
			this.session = sessionFactory.openSession();
		}
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
