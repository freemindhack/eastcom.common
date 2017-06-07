/**
 * 
 */
package com.eastcom.common.entity;

import java.util.List;

/**
 * @Project DataQuality
 * @author KenZZ
 * @Email <a href="mailto:zhuzhikun@eastcom-sw.com">zhuzhikun@eastcom-sw.com</a>
 * @CreateDate 2011-6-21
 */
public class PageEntity<T>
{
	private List<T> list;
	private int totalCount;
	
	public PageEntity()
	{
		
	}
	
	public PageEntity(List<T> list, int totalCount)
	{
		this.list = list;
		this.totalCount = totalCount;
	}
	
	public PageEntity(List<T> list)
	{
		this.list = list;
		this.totalCount = list.size();
	}
	
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount()
	{
		return totalCount;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list)
	{
		this.list = list;
	}
	/**
	 * @return the list
	 */
	public List<T> getList()
	{
		return list;
	}
}
