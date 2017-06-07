package com.eastcom.common.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.eastcom.common.utils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity支持类
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	@JsonIgnore
	@Transient
	public Map<String, Object> toMap() {
		return BeanUtils.transBean2Map2(this);
	}

}