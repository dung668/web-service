package edu.ptit.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.util.Common;
import edu.ptit.util.Constants;

@WebServlet(urlPatterns= {"/dang-nhap", "/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/client/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO userDao = new UserDAOImpl();
		boolean result = userDao.checkLoginInfo(username, Common.MD5(password));
		System.out.println(username + " " + password + " " + result);
		if(result == true) {
			HttpSession session = request.getSession();
			session.setAttribute("login_user", username);
			// dosfilter
			session.setAttribute("spam", false);
			session.setAttribute("count_spam", 0);
			session.setAttribute("last_request", System.currentTimeMillis());
			
			// chuyen huong neu la admin
			if(userDao.getUserByUserName(username).getRole() == Constants.ADMIN_ROLE) {
				response.sendRedirect("admin/home");
				return;
			}
			
			response.sendRedirect("home");
		}
		else {
			request.setAttribute("errorMess", Constants.WRONG_USERNAME_OR_PASSWORD);
			request.getRequestDispatcher("/client/jsp/login.jsp").forward(request, response);
		}
		
	}

}
