package com.eastcom.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 业务基类接口（不强制实现）
 */
public interface IBaseService<T> {
	public List<T> findAll();

	public T get(Serializable id);

	public void update(T t);

	public void delete(T t);

	public void deleteById(Serializable id);

	public void delete(List<T> list);

	public Serializable save(T t);

	public void saveOrUpdate(T t);

	public List<T> findByIds(Collection<? extends Serializable> ids);

	public boolean check(Serializable id ,List<T> list);
}
