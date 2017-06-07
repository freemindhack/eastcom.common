package com.eastcom.common.bean.easyui;

import java.util.List;

/**
 * DataGrid模型<br>
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:28:10 <br>
 *
 */
@SuppressWarnings("rawtypes")
public class DataGrid implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long total = 0L;// 总记录数
	private List rows;// 每行记录
	private List footer;// 表格统计信息

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
