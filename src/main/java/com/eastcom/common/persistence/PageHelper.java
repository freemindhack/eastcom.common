package com.eastcom.common.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.eastcom.common.utils.CookieUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分页帮助类<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:32:41 <br>
 *
 */
public class PageHelper<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int page;// 当前页
	private int rows;// 每页显示记录数,值为“-1”表示不进行分页查询
	private String sort;// 排序字段
	private String order;// asc/desc

	private Long count = 0L;// 总记录数，设置为“-1”表示不查询总数

	private List<T> list = new ArrayList<T>();

	public PageHelper() {
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码大小
	 */
	public PageHelper(HttpServletRequest request, HttpServletResponse response) {
		this(request,response,null);
	}
	
	/**
	 * 构造方法
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码大小
	 * @param defaultRows
	 *            如果前端没有传行记录数，而设置了此默认记录数，则采用此值
	 */
	public PageHelper(HttpServletRequest request, HttpServletResponse response, Integer defaultRows) {
		this(request,response,defaultRows,true);
	}
	
	/**
	 * 构造方法
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码大小
	 * @param defaultRows
	 *            如果前端没有传行记录数，而设置了此默认记录数，则采用此值
	 * @param defaultCount
	 *            设置是否查询总数,true 查询，false 不查询
	 */
	public PageHelper(HttpServletRequest request, HttpServletResponse response, Integer defaultRows,
			Boolean isCount) {
		// 设置页码参数（传递page参数，来记住页码）
		String no = request.getParameter("page");
		if (StringUtils.isNumeric(no)) {
			CookieUtils.setCookie(response, "page", no);
			this.setPage(Integer.parseInt(no));
		}
		// 设置页面大小参数（传递rows参数，来记住页码大小）
		String size = request.getParameter("rows");
		if (StringUtils.isNumeric(size)) {
			CookieUtils.setCookie(response, "rows", size);
			this.setRows(Integer.parseInt(size));
		} else if (defaultRows != null) {
			//如果前端没有传行记录数，而设置了此默认记录数，则采用此值
			this.rows = defaultRows;
		}
		// 设置排序参数
		String sort = request.getParameter("sort");
		if (StringUtils.isNotBlank(sort)) {
			this.setSort(sort);
		}
		// 设置排序方式
		String order = request.getParameter("order");
		if (StringUtils.isNotBlank(order)) {
			this.setOrder(order);
		}
		// 设置是否查询总数
		if (!isCount) {
			this.count = -1L;
		}
	}
	
	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 */
	public PageHelper(int page, int rows) {
		this(page, rows, 0);
	}

	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 * @param count
	 *            数据条数
	 */
	public PageHelper(int page, int rows, long count) {
		this(page, rows, count, new ArrayList<T>());
	}

	/**
	 * 构造方法
	 * 
	 * @param page
	 *            当前页码
	 * @param rows
	 *            分页大小
	 * @param count
	 *            数据条数
	 * @param list
	 *            本页数据对象列表
	 */
	public PageHelper(int page, int rows, long count, List<T> list) {
		this.setCount(count);
		this.setPage(page);
		this.setRows(rows);
		this.setList(list);
	}

	/**
	 * 构造方法
	 * @param list
	 *       数据对象列表
	 * @param count
	 *       数据条数
	 */
	public PageHelper(List<T> list, long count) {
		this.setList(list);
		this.setCount(count);
	}

	/**
	 * 分页是否有效
	 * 
	 * @return this.rows==-1
	 */
	@JsonIgnore
	public boolean isDisabled() {
		return this.rows == -1;
	}

	@JsonIgnore
	public boolean isNotCount() {
		return this.count == -1;
	}

	/**
	 * 获取 Hibernate FirstResult
	 */
	@JsonIgnore
	public int getFirstResult() {
		int firstResult = (getPage() - 1) * getRows();
		if (firstResult >= getCount()) {
			firstResult = 0;
		}
		return firstResult;
	}

	@JsonIgnore
	public int getLastResult() {
		int lastResult = getFirstResult() + getMaxResults();
		if (lastResult > getCount()) {
			lastResult = getCount().intValue();
		}
		return lastResult;
	}

	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults() {
		return getRows();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageHelper [page=" + page + ", rows=" + rows + ", sort=" + sort + ", order="
				+ order + "]";
	}

}
