package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

public interface Dao<T, ID> {

	public T findById(ID id);

	public List<T> findAll();

	public T create(T entity);

	public void delete(T entity);
	
	public void deleteTable();
}
