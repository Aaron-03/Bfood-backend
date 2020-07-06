package com.app.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class DaoImpl<T, ID extends Serializable> implements Dao<T, ID> {

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getDao().findById(id);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> read() {
		// return Lists.newArrayList(getDao().findAll());
		List<T> lst = new ArrayList<>();
		getDao().findAll().forEach(obj -> lst.add(obj));
		return lst;
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);

	}

	public abstract CrudRepository<T, ID> getDao();

}
