package com.eastcom.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.eastcom.common.dao.IBaseDao;

/**
 * Service基类
 * 
 */
@Transactional
public abstract class BaseService<T> extends AbstractService implements IBaseService<T> {

	protected IBaseDao<T> baseDao;

	protected IBaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.getBaseDao().findAll();
	}

	@Transactional(readOnly = true)
	public T get(Serializable id) {
		return this.getBaseDao().getById(id);
	}

	public void update(T t) {
		this.getBaseDao().update(t);
	}

	public void delete(T t) {
		this.getBaseDao().delete(t);
	}

	public void delete(List<T> list) {
		this.getBaseDao().delete(list);
	}

	public void deleteById(Serializable id) {
		this.getBaseDao().deleteById(id);
	}

	public Serializable save(T t) {
		return this.getBaseDao().save(t);
	}

	public void saveOrUpdate(T t) {
		this.getBaseDao().saveOrUpdate(t);
	}
	
	public List<T> findByIds(Collection<? extends Serializable> ids){
		return this.getBaseDao().findByIds(ids);
	}
	
	public boolean check(Serializable id ,List<T> list){
		return this.getBaseDao().check(id,list);
	}
}