package com.eastcom.common.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.eastcom.common.utils.SystemUtils;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemUtils.printKeyLoadMessage()) {
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
