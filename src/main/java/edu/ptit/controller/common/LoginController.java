package edu.ptit.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dungnd.facebook.FacebookUtil;
import com.restfb.types.User;

import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.util.Common;
import edu.ptit.util.Constants;

@WebServlet(urlPatterns= {"/dang-nhap", "/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    public LoginController() {
        super();
        userDao = new UserDAOImpl();
    }
    // user login by facebook
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Response from Facebook
		String code = request.getParameter("code");
		
		if(code == null || code.isEmpty()) {
			request.getRequestDispatcher("/client/jsp/login.jsp").forward(request, response);
		} else {
			// user accept login facebook
			String accessToken = FacebookUtil.getToken(code);
			User user = FacebookUtil.getUserInfo(accessToken);
			HttpSession session = request.getSession();
			
			String facebookId = user.getId();
			
			if(userDao.isFacebookIdExits(facebookId)) { // if facebookid exits
				
				if(userDao.isFacebookLinkedToAccount(facebookId)) {
					
					edu.ptit.model.User loginUser = userDao.findUserByFacebookId(facebookId);
					session.setAttribute("login_user", loginUser.getUsername());				
					
					// dosfilter
					session.setAttribute("spam", false);
					session.setAttribute("count_spam", 0);
					session.setAttribute("last_request", System.currentTimeMillis());
					
					response.sendRedirect(loginUser.getRole() == 0 ? "home" : "admin/home");
					
				} else {
					session.setAttribute("facebookId", facebookId);
					response.sendRedirect("update-information");
				}
				
			} else { // user havent synch facebook with user account
				
				edu.ptit.model.User loginUser = new edu.ptit.model.User();
				loginUser.setEmail(user.getEmail());
				loginUser.setFacebookId(user.getId());
				loginUser.setFullName(user.getName());
				userDao.addUser(loginUser);
				
				session.setAttribute("facebookId", facebookId);
				
				response.sendRedirect("update-information");
				
			}
			
			
		}
		
		
	}

	// login with system account
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
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
