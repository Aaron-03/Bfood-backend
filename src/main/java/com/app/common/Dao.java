package com.app.common;

import java.io.Serializable;
import java.util.List;

public interface Dao<T,ID extends Serializable> {
	
	T save(T entity);
	T get(ID id);
	List<T> read();
	void delete(ID id);

}
