package com.eastcom.common.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * oracle sql 通用处理方法
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:36:30 <br>
 *
 */
public class OracleSqlUtils {

	/**
	 * 当In 条件超过1000的处理方法
	 * 
	 * @param ids
	 * @param count
	 *            in条件最多处理数
	 * @param field
	 *            字段名
	 * @return
	 */
	public static String getOracleSQLIn(List<String> ids, int count, String field) {
		count = Math.min(count, 1000);
		int len = ids.size();
		int size = len % count;
		if (size == 0) {
			size = len / count;
		} else {
			size = (len / count) + 1;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int fromIndex = i * count;
			int toIndex = Math.min(fromIndex + count, len);
			String productId = StringUtils.defaultIfEmpty(
					StringUtils.join(ids.subList(fromIndex, toIndex), "','"), "");
			if (i != 0) {
				builder.append(" or ");
			}
			builder.append(field).append(" in ('").append(productId).append("')");
		}
		return StringUtils.defaultIfEmpty(builder.toString(), field + " in ('')");
	}

	public static String getSqlCondition(Set<String> params) {

		if (Collections3.isEmpty(params)) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		Iterator<String> paramsIterator = params.iterator();
		result.append("?");
		paramsIterator.next();

		while (paramsIterator.hasNext()) {
			result.append(" ,? ");
			paramsIterator.next();
		}
		return result.toString();
	}

	public static String getSqlCondition(Set<String> params, String connectStr) {
		if (Collections3.isEmpty(params)) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		Iterator<String> paramsIterator = params.iterator();
		result.append("?");
		paramsIterator.next();
		int i = 1;
		while (paramsIterator.hasNext()) {
			result.append(" ,? ");
			paramsIterator.next();

			i++;
			if (i > 990) {
				result.append(connectStr);
				i = 1;
			}

		}
		return result.toString();

	}

	public static String getCollectToString(Collection<String> collection, String splitFlag) {

		if (Collections3.isEmpty(collection)) {
			return null;
		}
		if (StringUtils.isBlank(splitFlag)) {
			splitFlag = ",";
		}
		StringBuilder result = new StringBuilder();

		Iterator<String> paramsIterator = collection.iterator();
		result.append(paramsIterator.next());

		while (paramsIterator.hasNext()) {
			result.append(splitFlag);
			result.append(paramsIterator.next());
		}
		return result.toString();

	}

	/**
	 * 生成IN 查询条件，排除空Integer,比如params=[1,2],那么生成的查询条件是1,2
	 * 
	 * @author lizhiyan
	 * @param alarmLevels
	 * @return
	 * @date 2014-01-20
	 */
	public static String getSqlConditionByCollection(List<Integer> alarmLevels) {
		if (Collections3.isEmpty(alarmLevels)) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		if (alarmLevels != null) {
			for (Integer param : alarmLevels) {
				if (param != null) {
					buffer.append(param).append(",");
				}
			}
		}

		String condition = buffer.toString();
		if (condition.endsWith(",")) {
			condition = condition.substring(0, condition.length() - 1);
		}

		return condition;
	}

	/**
	 * 生成IN 查询条件，排除空字符串,比如params=['1','2',''],那么生成的查询条件是'1','2'
	 * 
	 * @author lizhiyan
	 * @param params
	 * @return
	 */
	public static String getSqlConditionByCollection(Collection<String> params) {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuffer buffer = new StringBuffer();
		if (params != null) {
			for (String param : params) {
				if (param != null && !"".equals(param.trim())) {
					buffer.append("'").append(param).append("',");
				}
			}
		}

		String condition = buffer.toString();
		if (condition.endsWith(",")) {
			condition = condition.substring(0, condition.length() - 1);
		}

		return condition;

	}
}
