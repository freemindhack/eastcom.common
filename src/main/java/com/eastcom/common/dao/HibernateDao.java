package com.eastcom.common.dao;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.eastcom.common.entity.BaseEntity;

public abstract class HibernateDao<T extends BaseEntity<T>> extends HibernateTemplate {

}
