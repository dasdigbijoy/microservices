package com.springcloud.microservice.netflixzuulapigatewayserver;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	//Here the complete Logic of Filter will be written
	//We want to log for details of every request.
	@Override
	public Object run() throws ZuulException {
		
		HttpServletRequest request =  RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {}  request uri -> {}", request, request.getRequestURI());
		
		
		
		return null;
	}

	//There can be Business Rule to decide whether this filter to be executed or not.
	//For the timebeing we want to execute this for every kind of request.
	//Hence marking as true
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//if you have multiple zuul filters like zuulLoggingFilter,zuulSecurityFilter , etc
	//You can set a priority order between them.Let's set 1 for this.
	@Override
	public int filterOrder() {
		return 1;
	}

	//Here it decides when the filter to be executed.
	//pre/post/error : if the filter be requested previous / after or error 
	//We will use "pre" , as we want to intercept every request.
	@Override
	public String filterType() {
		return "pre";
	}

}
