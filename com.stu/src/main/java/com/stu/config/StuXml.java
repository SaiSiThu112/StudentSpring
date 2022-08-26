package com.stu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class StuXml extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] a = {StuDispatcher.class};
		return a;
	}

	@Override
	protected String[] getServletMappings() {
		String[] b = {"/"};
		return b;
	}

}
