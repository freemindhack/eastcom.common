package com.eastcom.common.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.eastcom.common.exception.DaoException;

public abstract class SpringJdbcDao extends JdbcTemplate implements JdbcDao {

	// private final DateFormat df = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	protected JdbcTemplate getJdbcTemplate() {
		return this;
	}

	public List<Map<String, Object>> executeQuery(String sql) {
		return this.queryForList(sql);
	}

	public List<Map<String, Object>> executeQuery(String sql, Object[] params) {
		return this.queryForList(sql, params);
	}

	public <T> List<T> executeQuery(String sql, Class<T> t, Object[] params) {
		return this.queryForList(sql, params, t);
	}

	public List<Map<String, Object>> executeQuery(String sql, Object[] params, int[] paramTypes) {
		return this.queryForList(sql, params, paramTypes);
	}

	/*
	 * protected String getOracleSqlString(Object object){ if(object == null){
	 * return "null"; }else if(object instanceof String){
	 * 
	 * String s = (String) object;
	 * 
	 * s.replaceAll("'", "''");
	 * 
	 * s = "'" + s + "'";
	 * 
	 * s = s.replaceAll("\n", "' || chr(10) || chr(13) || '");
	 * 
	 * return s; }else if(object instanceof Integer){ return object+""; }else
	 * if(object instanceof Boolean){ boolean b = (Boolean)object; if(b){ return
	 * "1"; }else{ return "0"; } }else if(object instanceof java.util.Date){
	 * return "to_date('" + df.format((java.util.Date)object) +
	 * "','yyyy-mm-dd hh24:mi:ss')"; }else{ return "'" + object + "'"; } }
	 */

	public void excuteUpdate(String sql, final List<Object[]> params) throws DaoException {
		if (params == null || params.size() == 0) {
			return;
		}
		@SuppressWarnings("unused")
		int[] r = this.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@SuppressWarnings("null")
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				Object[] objects = params.get(i);

				for (int j = 0; j < objects.length; j++) {

					if (objects[j] instanceof String) {
						ps.setString(j + 1, objects[j] == null ? null : (String) objects[j]);
					} else if (objects[j] instanceof Integer) {
						ps.setInt(j + 1, objects[j] == null ? 0 : ((Integer) objects[j]).intValue());
					} else if (objects[j] instanceof Date) {
						ps.setTimestamp(j + 1, new Timestamp(((Date) objects[j]).getTime()));
					} else if (objects[j] instanceof BigDecimal) {
						ps.setFloat(j + 1,
								objects[j] == null ? 0 : ((BigDecimal) objects[j]).floatValue());
					} else if (objects[j] instanceof Double) {
						ps.setDouble(j + 1, objects[j] == null ? 0 : (Double) objects[j]);
					} else if (objects[j] instanceof Float) {
						ps.setFloat(j + 1, objects[j] == null ? 0 : (Float) objects[j]);
					} else if (objects[j] instanceof Short) {
						ps.setShort(j + 1, objects[j] == null ? 0 : (Short) objects[j]);
					} else if (objects[j] instanceof Timestamp) {
						ps.setTimestamp(j + 1, (Timestamp) objects[j]);
					} else if (objects[j] instanceof Character) {
						ps.setString(j + 1, objects[j] == null ? null : objects[j].toString());
					} else if (objects[j] instanceof Byte) {
						ps.setByte(j + 1, objects[j] == null ? null : (Byte) objects[j]);
					} else if (objects[j] instanceof Boolean) {
						int param = 0;
						Boolean value = (Boolean) objects[j];
						if (value = null) {

						} else if (value.booleanValue() == true) {

						} else if (value.booleanValue() == false) {
							param = 1;
						}
						ps.setInt(j + 1, param);
					} else {
						ps.setString(j + 1, objects[j] == null ? null : objects[j].toString());
					}
				}
			}

			public int getBatchSize() {
				return params.size();
			}
		});
	}

}
