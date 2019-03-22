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
import javax.servlet.http.HttpSession;

import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.model.User;

@WebFilter(urlPatterns= {"/cart/*", "/account/*", "/invoices/*"})
public class DosFilter implements Filter {

	public DosFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("bắt đầu filter dos"); // you can use logging instead
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();

		if(session.getAttribute("login_user") != null) {
			
			long timeElapsed = System.currentTimeMillis() - (long) session.getAttribute("last_request");
			if(timeElapsed < 1500) // request < 1.5s
				session.setAttribute("spam", true);
			
			if(session.getAttribute("spam").equals(true)) {
				if((int) session.getAttribute("count_spam") < 5) {
					int countSpam = (int) session.getAttribute("count_spam") + 1;
					System.out.println("WARN: Thành viên " + session.getAttribute("login_user") 
									+ " spam lần thứ " + countSpam);
					session.setAttribute("count_spam", countSpam); // gan so lan spam cua user cho session
					chain.doFilter(request, response);
				} else { // Thanh vien trong 1 sesssion co >= 5 lan request trong tgian qua ngan
					System.out.println("WARN: Block thành viên " + session.getAttribute("login_user"));
					String username = session.getAttribute("login_user").toString();
					UserDAO userDao = new UserDAOImpl();
					User spammer = userDao.getUserByUserName(username);
					spammer.setBanned(true); // user bi banned
					userDao.updateUser(spammer);
					session.removeAttribute("login_user"); // dang xuat spammer khoi he thong
					return;
				}
			} else { // neu lan request nay khong phai spam
				
				session.setAttribute("last_request", System.currentTimeMillis());
				chain.doFilter(request, response);
			}
			
		} else chain.doFilter(request, response);
			
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
