package com.eastcom.common.entity;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public enum RestrictionType {
	EQORISNULL, LT, LE, GT, GE, LIKE, NE, IN;
	
	public Criterion getCriterion(String propertyName, Object value) {
		switch (this) {
		case EQORISNULL:
			return Restrictions.eqOrIsNull(propertyName, value);
		case LT:
			return Restrictions.lt(propertyName, value);
		case LE:
			return Restrictions.le(propertyName, value);
		case GT:
			return Restrictions.gt(propertyName, value);
		case GE:
			return Restrictions.ge(propertyName, value);	
		case LIKE:
			return Restrictions.like(propertyName, value);
		case NE:
			return Restrictions.ne(propertyName, value);
		case IN:
			return Restrictions.in(propertyName, (Collection<?>)value);
		default:
			return null;
		}
	}
}
