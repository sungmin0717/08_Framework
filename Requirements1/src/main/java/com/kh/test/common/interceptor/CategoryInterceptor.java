package com.kh.test.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kh.test.service.ProductService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryInterceptor implements HandlerInterceptor{
	
	@Autowired
	private ProductService service;
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		ServletContext application = request.getServletContext();
		
		if(application.getAttribute("categoryList") == null) {
			
			List<Map<String, Object>> categoryList 
				= service.selectCategoryList();

			
			 log.debug(categoryList.toString()); //로그 확인
			
			application.setAttribute("categoryList", categoryList);
			// application 세팅
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	

	
}