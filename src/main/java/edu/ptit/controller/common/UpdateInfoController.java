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
import edu.ptit.model.User;
import edu.ptit.util.Common;

@WebServlet("/update-information")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    public UpdateInfoController() {
        super();
        userDao = new UserDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Object o = session.getAttribute("facebookId");
		
		if(o == null) {
			User user = userDao.getUserByUserName(session.getAttribute("login_user").toString());
			request.setAttribute("user", user);
		}

		request.getRequestDispatcher("/client/jsp/update-info.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phone-number");
		
		if(userDao.isDuplicate(username, null)) {
			response.sendRedirect("update-information?errorMess=duplicate");
			return;
		}
		
		HttpSession session = request.getSession();
		User user = null; 
		
		if(session.getAttribute("facebookId") != null)
			user = userDao.findUserByFacebookId(session.getAttribute("facebookId").toString());
		else user = userDao.getUserByUserName(session.getAttribute("login_user").toString());
		
		if(username != null) user.setUsername(username);
		
		if(user.getPassword() == null || Common.MD5(password) != user.getPassword())
			user.setPassword(password);
		
		if(address != null) user.setAddress(address);
		
		if(phoneNumber != null) user.setPhone(phoneNumber);
		
		userDao.updateUser(user);
		
		session.removeAttribute("facebookId");
		
		session.setAttribute("login_user", username);
		
		// dosfilter
		session.setAttribute("spam", false);
		session.setAttribute("count_spam", 0);
		session.setAttribute("last_request", System.currentTimeMillis());
		
		response.sendRedirect("home");
	}

}
