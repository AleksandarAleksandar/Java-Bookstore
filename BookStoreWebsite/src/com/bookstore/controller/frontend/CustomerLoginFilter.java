package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;


@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
	private static final String[] loginRequiredURLs = {
			"/view_profile", "/edit_profile", "/update_profile", "/write_review","/checkout" , "place_order", 
			"/view_orders", "/shwow_order_detail"
	};
	
    public CustomerLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if(path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean logedIn = session != null && session.getAttribute("loggedCustomer") != null;
		
		String requestURL = httpRequest.getRequestURL().toString();
		System.out.println("Path: " + path);
		System.out.println("Login: " + logedIn);
		
		if(!logedIn && isLoginRequired(requestURL)) {
			String queryString = httpRequest.getQueryString();
			String redirectURL = requestURL;
			if(queryString != null) {
				redirectURL = redirectURL.concat("?").concat(queryString);
			}
			
			session.setAttribute("redirectURL", requestURL);
			
			String loginPage = "frontend/login.jsp";
			RequestDispatcher dispacher= httpRequest.getRequestDispatcher(loginPage);
			dispacher.forward(httpRequest, response);
			
		}else {
			chain.doFilter(request, response);
		}
	}
	private boolean isLoginRequired(String requestURL) {
		for(String loginRequiredURL: loginRequiredURLs) {
			if(requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
