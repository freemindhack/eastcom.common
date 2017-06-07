package com.eastcom.common.dao;

import java.util.List;
import java.util.Map;

import com.eastcom.common.exception.DaoException;

public interface JdbcDao {
	public List<Map<String, Object>> executeQuery(String sql, Object[] params);

	public void excuteUpdate(String sql, final List<Object[]> params) throws DaoException;

	public void execute(String sql);

	public List<Map<String, Object>> executeQuery(String sql);

	public <T> List<T> executeQuery(String sql, Class<T> t, Object[] params);

	public List<Map<String, Object>> executeQuery(String sql, Object[] params, int[] paramTypes);
}
