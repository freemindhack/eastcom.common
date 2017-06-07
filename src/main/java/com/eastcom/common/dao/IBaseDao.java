package com.eastcom.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.eastcom.common.persistence.PageHelper;

/**
 * DAO支持接口
 * 
 * @author jared
 * @version 2014-01-05
 * @param <T>
 * 
 */
public interface IBaseDao<T> {
	/**
	 * 获取 Session
	 */
	public Session getCurrentSession();

	/**
	 * 强制与数据库同步
	 */
	public void flush();

	/**
	 * 清除缓存数据
	 */
	public void clear();

	/**
	 * 清除指定对象的缓存
	 * 
	 * @param o
	 */
	public void evict(T o);

	/**
	 * 查询所有
	 * 
	 * @return
	 */

	public List<T> findAll();
	
	public Serializable save(T o);

	/**
	 * 更新
	 * 
	 * @param o
	 * @return
	 */
	public void update(T o);

	public void saveOrUpdate(T o);

	public T merge(T o);

	public Long countAll();

	public void delete(List<T> list);

	public void delete(T o);

	public void deleteById(Serializable id);
	
	public void deleteByIds(Collection<? extends Serializable> ids);

	// -------------- HQL Query --------------

	/**
	 * 获取实体
	 * 
	 * @param id
	 * @return
	 */

	public T getById(Serializable id);
	
	public List<T> findByIds(Collection<? extends Serializable> ids);

	public T getUnique(String uniqueProperty, Object value);

	/**
	 * 获取实体
	 * 
	 * @param hqlString
	 * @return
	 */
	public T get(String hqlString);

	/**
	 * 获取实体
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public T get(String hqlString, Map<String, Object> parameter);

	/**
	 * 获取实体
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public T get(String hqlString, Object[] parameter);

	/**
	 * HQL 分页查询
	 * 
	 * @param page
	 * @param hqlString
	 * @return
	 */

	public <E> PageHelper<E> find(PageHelper<E> page, String hqlString);

	/**
	 * HQL 分页查询
	 * 
	 * @param page
	 * @param hqlString
	 * @param parameter
	 * @return
	 */

	public <E> PageHelper<E> find(PageHelper<E> page, String hqlString,
			Map<String, Object> parameter);

	/**
	 * HQL 分页查询
	 * 
	 * @param page
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public <E> PageHelper<E> find(PageHelper<E> page, String hqlString, Object[] parameter);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public <E> List<E> find(String hqlString, int firstResult, int maxResults);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @param parameter
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public <E> List<E> find(String hqlString, Map<String, Object> parameter, int firstResult,
			int maxResults);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @param parameter
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public <E> List<E> find(String hqlString, Object[] parameter, int firstResult, int maxResults);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @return
	 */
	public <E> List<E> find(String hqlString);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> find(String hqlString, Map<String, Object> parameter);

	/**
	 * HQL 查询
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> find(String hqlString, Object[] parameter);

	public Long count(String hqlString);

	public Long count(String hqlString, Map<String, Object> parameter);

	public Long count(String hqlString, Object[] parameter);

	/**
	 * 执行
	 * 
	 * @param hqlString
	 * @return
	 */
	public int execute(String hqlString);

	/**
	 * 执行
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public int execute(String hqlString, Map<String, Object> parameter);

	/**
	 * 执行
	 * 
	 * @param hqlString
	 * @param parameter
	 * @return
	 */
	public int execute(String hqlString, Object[] parameter);

	// -------------- SQL Query --------------

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @return
	 */

	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString);

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString,
			Map<String, Object> parameter);

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString, Object[] parameter);

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @param resultClass
	 * @return
	 */
	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString, Class<?> resultClass);

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString,
			Map<String, Object> parameter, Class<?> resultClass);

	/**
	 * SQL 分页查询
	 * 
	 * @param page
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */

	public <E> PageHelper<E> findBySql(PageHelper<E> page, String sqlString, Object[] parameter,
			Class<?> resultClass);

	public <E> List<E> findBySql(String sqlString, int firstResult, int maxResults);

	public <E> List<E> findBySql(String sqlString, Map<String, Object> parameter, int firstResult,
			int maxResults);

	public <E> List<E> findBySql(String sqlString, Object[] parameter, int firstResult,
			int maxResults);

	/**
	 * SQL 查询
	 * 
	 * @param sqlString
	 * @return
	 */

	public <E> List<E> findBySql(String sqlString);
	
	
	public <E> List<E> findBySql(String sqlString,Class<?> resultClass);

	/**
	 * SQL 查询
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString, Map<String, Object> parameter);

	/**
	 * SQL 查询
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString, Object[] parameter);

	/**
	 * SQL 查询
	 * 
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */

	public <E> List<E> findBySql(String sqlString, Map<String, Object> parameter,
			Class<?> resultClass);

	/**
	 * SQL 查询
	 * 
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString, Object[] parameter, Class<?> resultClass);

	/**
	 * 查询总数
	 * 
	 * @param sqlString
	 * @return
	 */
	public Long countBySql(String sqlString);

	/**
	 * 查询总数
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public Long countBySql(String sqlString, Map<String, Object> parameter);

	/**
	 * 查询总数
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public Long countBySql(String sqlString, Object[] parameter);

	/**
	 * 执行sql
	 * 
	 * @param sqlString
	 * @return
	 */
	public int executeBySql(String sqlString);

	/**
	 * 执行sql
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public int executeBySql(String sqlString, Map<String, Object> parameter);

	/**
	 * 执行sql
	 * 
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public int executeBySql(String sqlString, Object[] parameter);

	/**
	 * 根据表名查询信息
	 * 
	 * @param tableName
	 * @return
	 */
	public <E> List<E> findByNamedQuery(String tableName);

	/**
	 * 根据表名查询信息
	 * 
	 * @param tableName
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findByNamedQuery(String tableName, Map<String, Object> parameter);

	/**
	 * 根据表名查询信息
	 * 
	 * @param tableName
	 * @param parameter
	 * @param resultClass
	 * @return
	 */
	public <E> List<E> findByNamedQuery(String tableName, Map<String, Object> parameter,
			Class<?> resultClass);

	boolean check(Serializable id, List<T> list);
}
