package com.revature.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter{
	//called whenever a request/response pair is passed through the chain when a client requests for a resource at the end of a chain
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			// TODO Auto-generated method stub
			
		}

		//method is called by web container to deploy a filter
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			String myParam = request.getParameter("myParam");
			
			if(!"blockRequest".equals(myParam)) {
				chain.doFilter(request, response);
				return;
			}
			
		}

		// called by web container to remove a filter from active service
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}
}
