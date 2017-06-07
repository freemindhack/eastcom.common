package com.eastcom.common.utils;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:35:03 <br>
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class CompositeComparator implements Comparator {

	/**
	 * 中文习惯
	 */
	public RuleBasedCollator collator4Cn = (RuleBasedCollator) Collator
			.getInstance(java.util.Locale.CHINA);
	/**
	 * 英文习惯
	 */
	public RuleBasedCollator collator4En = (RuleBasedCollator) Collator
			.getInstance(java.util.Locale.ENGLISH);;

	/**
	 * comparators
	 */
	private List comparators = new LinkedList();

	/**
	 * 默认构造器
	 */
	public CompositeComparator() {
	}

	/**
	 * 获得所有的比较器
	 * 
	 * @return
	 */
	public List getComparators() {
		return comparators;
	}

	/**
	 * 添加比较器
	 * 
	 * @param comparatorArray
	 */

	public void addComparators(Comparator[] comparatorArray) {
		if (comparatorArray == null) {
			return;
		}
		for (int i = 0; i < comparatorArray.length; i++) {
			comparators.add(comparatorArray[i]);
		}
	}

	/**
	 * 比较两个对象
	 */
	public int compare(Object o1, Object o2) {
		for (Iterator iterator = comparators.iterator(); iterator.hasNext();) {
			Comparator comparator = (Comparator) iterator.next();
			int result = comparator.compare(o1, o2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
