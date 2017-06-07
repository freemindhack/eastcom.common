package com.eastcom.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 数据Entity类
 * 
 * @author jared
 * @version 2013-05-28
 */
@MappedSuperclass
public abstract class DataEntity<T> extends IdEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Date createDate;// 创建日期
	protected Date updateDate;// 更新日期
	protected String remarks; // 备注

	public DataEntity() {
		super();
	}

	public DataEntity(String id) {
		super(id);
	}
	
	@JsonIgnore
	@Transient
	public void preInsert() {
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}

	@JsonIgnore
	@Transient
	public void preUpdate() {
		this.updateDate = new Date();
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}