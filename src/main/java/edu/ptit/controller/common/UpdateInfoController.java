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
import edu.ptit.util.Constants;

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
//			request.setAttribute("errorMess", Constants.DUPLICATE_USERNAME);
//			request.getRequestDispatcher("update-information").forward(request, response);
			response.sendRedirect("update-information?errorMess=duplicate");
			return;
		}
		
		HttpSession session = request.getSession();
		User user = userDao.findUserByFacebookId(session.getAttribute("facebookId").toString());
		
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(address);
		user.setPhone(phoneNumber);
		
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
