/**
 * @Project:myspring
 * @Package:com.zhuyuhua.myspring.interceptor 
 * @FileName:SpringMVCWebRequestInterceptor.java 
 * @Date:2014-2-18 下午4:20:19 
 * @Version V1.0.0
 * Copyright(c)ShenZhen Expressway Engineering Consultants Co.,Ltd 
 */
package com.zhuyuhua.myspring.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/** 
 * @ClassName:SpringMVCWebRequestInterceptor 
 * @Desc:TODO
 * @Author:joe
 * @Date:2014-2-18 下午4:20:19 
 * @Since:V 1.0 
 */
public class SpringMVCWebRequestInterceptor implements WebRequestInterceptor
{
	/** 
	 * @Fileds:日志记录
	 * 
	 */
	private static final Logger logger = LogManager
			.getLogger(SpringMVCWebRequestInterceptor.class);

	/**
	 * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
	 */
	public void preHandle(WebRequest request) throws Exception {
		logger.debug("preHandle");
		// 这个是放到request范围内的，所以只能在当前请求中的request中获取到
		request.setAttribute("request", "request", WebRequest.SCOPE_REQUEST);

		// 这个是放到session范围内的，如果环境允许的话它只能在局部的隔离的会话中访问，否则就是在普通的当前会话中可以访问
		request.setAttribute("session", "session", WebRequest.SCOPE_SESSION);

		// 如果环境允许的话，它能在全局共享的会话中访问，否则就是在普通的当前会话中访问
		request.setAttribute("globalSession", "globalSession",
				WebRequest.SCOPE_GLOBAL_SESSION);
	}

	/**
	 * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
	 * 这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。
	 */
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		logger.debug("postHandle");
		for (String key : model.keySet()) {
			logger.debug("key=" + key);
		}
		model.put("name3", "value3");
		model.put("name1", "name1");
	}

	/**
	 * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
	 */
	public void afterCompletion(WebRequest request, Exception ex)
			throws Exception {
		logger.debug("afterCompletion");

	}
}
