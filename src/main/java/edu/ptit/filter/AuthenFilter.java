package edu.ptit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenFilter
 */
@WebFilter(urlPatterns= {"/account", "/cart", "/invoices", "/add-to-cart", "/order"})
public class AuthenFilter implements Filter {
	
    public AuthenFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		if(session.getAttribute("login_user") == null) 	// neu nguoi dung chua dang nhap
			res.sendRedirect("login");  				// thi chuyen sang trang dang nhap
		else chain.doFilter(request, response);			// neu nguoi dung da dang nhap
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
