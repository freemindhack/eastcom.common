package com.eastcom.common.config;

import java.util.Map;

import com.eastcom.common.utils.PropertiesLoader;
import com.eastcom.common.utils.StringUtils;
import com.google.common.collect.Maps;

/**
 * 全局配置类
 */
public class PropertyUtils {

	private static PropertyUtils config = new PropertyUtils();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("config.properties");

	/**
	 * 重载资源属性文件
	 * @param propertiesLoader
	 */
	public static void setPropertiesLoader(PropertiesLoader propertiesLoader) {
		PropertyUtils.propertiesLoader = propertiesLoader;
	}

	/**
	 * 获取当前实例对象
	 * @return
	 */
	public static PropertyUtils getInstance() {
		return config;
	}

	/**
	 * 获取配置
	 */	
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	public static String getConfig(String key, String defaultValue) {
		String value = map.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key, defaultValue);
			map.put(key, value);
		}
		return value;
	}

	// ///////////////////////////////////////////////////////

	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}

}