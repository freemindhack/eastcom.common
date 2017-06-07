package com.eastcom.common.mapper;

import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;

/**
 * Dozer 是一个对象转换工具。 Dozer可以在JavaBean到JavaBean之间进行递归数据复制,并且这些JavaBean可以是不同的复杂的类型。
 * 所有的mapping，Dozer将会很直接的将名称相同的fields进行复制，如果field名不同，或者有特别的对应要求，则可以在xml中进行定义。
 * 另一种方案就是使用BeanUtil，但BeanUtil不够很好的灵活性，又时候还不得不手动拷贝。Dozer可以灵活的对对象进行转换，且使用简单。
 * 
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:<br>
 * 
 * 1. 持有Mapper的单例.<br>
 * 2. 返回值类型转换. <br>
 * 3. 批量转换Collection中的所有对象. <br>
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}