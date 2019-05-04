package com.dungnd.facebook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.restfb.types.User;

import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.util.Constants;

@WebServlet("/fb-login")
public class LoginFacebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao;
	
    public LoginFacebookController() {
        super();
        userDao = new UserDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Response from Facebook
		String code = request.getParameter("code");
		
		if(code == null || code.isEmpty()) // user dont accept login with facebook
			response.sendRedirect("home");
		else {
			
			String accessToken = FacebookUtil.getToken(code);
			User user = FacebookUtil.getUserInfo(accessToken);
			HttpSession session = request.getSession();
			
			String facebookId = user.getId();
			
			if(!userDao.isFacebookIdExits(facebookId)) { // if facebook account dont exits in this system
				
				edu.ptit.model.User usr = new edu.ptit.model.User();
				usr.setFullName(user.getName());
				if(user.getEmail() != null) usr.setEmail(user.getEmail()); // maybe user only has mobile phone linked with fb account
				usr.setFacebookId(facebookId);
				
				userDao.addUser(usr); // add facebook account user
				
				session.setAttribute("facebookId", facebookId);
				response.sendRedirect("update-information");
				
				return;
				
			}
			
			if(userDao.isFacebookLinkedToAccount(facebookId)) { // check facebook account was linked to system account 
				
				edu.ptit.model.User usr = userDao.findUserByFacebookId(facebookId);
				session.setAttribute("login_user", usr.getUsername());
				
				// dosfilter
				session.setAttribute("spam", false);
				session.setAttribute("count_spam", 0);
				session.setAttribute("last_request", System.currentTimeMillis());
				
				response.sendRedirect(usr.getRole() == Constants.USER_ROLE ? "home" : "admin/home");
				
			} else {
				
				session.setAttribute("facebookId", facebookId);
				response.sendRedirect("update-information");

			}
			
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
