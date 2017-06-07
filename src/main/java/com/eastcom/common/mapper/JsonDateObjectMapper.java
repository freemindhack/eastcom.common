/*
 * Created on 2013-4-19
 * CustomObjectMapper.java V1.0
 *
 * Copyright Notice =========================================================
 * This file contains proprietary information of Eastcom Technologies Co. Ltd.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 =======================================================
 */
package com.eastcom.common.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 用于Spring MVC的Json日期格式处理
 * 后台的Date类型在使用@ResponseBody返回到前台呈现时，自动转为yyyy-mm-dd hh:mm:ss格式字符串
 * Title: eastcom.common <br>
 * Description: <br>
 * @author <a href="mailto:13507615840@163.com">lukw</a><br>
 * @email:13507615840@163.com	<br>
 * @version 1.0 <br>
 * @creatdate 2017年6月7日 下午9:32:16 <br>
 *
 */
public class JsonDateObjectMapper extends ObjectMapper{

	private static final long serialVersionUID = 3592844643430893817L;
	private String formatter = "yyyy-MM-dd HH:mm:ss";
	
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	
	public JsonDateObjectMapper() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        DateFormat df = new SimpleDateFormat(this.formatter);
        setDateFormat(df);
    }
}
