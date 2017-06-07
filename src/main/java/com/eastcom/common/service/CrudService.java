package com.eastcom.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.eastcom.common.dao.IBaseDao;
import com.eastcom.common.entity.BaseEntity;

/**
 * Service基类
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends IBaseDao<T>, T extends BaseEntity<T>> extends
		AbstractService implements IBaseService<T> {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public T get(Serializable id) {
		return dao.getById(id);
	}

	public void update(T t) {
		dao.update(t);
	}

	public void delete(T t) {
		dao.delete(t);
	}

	public void delete(List<T> list) {
		dao.delete(list);
	}

	public void deleteById(Serializable id) {
		dao.deleteById(id);
	}

	public Serializable save(T t) {
		return dao.save(t);
	}

	public void saveOrUpdate(T t) {
		dao.saveOrUpdate(t);
	}

	public List<T> findByIds(Collection<? extends Serializable> ids) {
		return dao.findByIds(ids);
	}

}
