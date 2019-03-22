package edu.ptit.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.model.User;

@WebServlet("/admin/users-management")
public class UsersManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDao;
    public UsersManagementController() {
        super();
        userDao = new UserDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = userDao.getListUser();
		request.setAttribute("users", users);
		
		// check action (EDIT / DELETE)
		String action = request.getParameter("action");
		if(action != null) {
			
			if(action.equals("EDIT")) {
				int id = Integer.parseInt(request.getParameter("id"));
				User user = userDao.getUserByUserId(id);
				request.setAttribute("user", user);
			} else if(action.equals("DELETE")) {
				int id = Integer.parseInt(request.getParameter("id"));
				userDao.removeUser(id);
				response.sendRedirect("users-management");
				return;
			}
			
		}
		
		request.getRequestDispatcher("jsp/users-management.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// action = ADD / UPDATE
		String action = request.getParameter("action");
		
		int id = request.getParameter("user-id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("user-id"));
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		int role = Integer.parseInt(request.getParameter("user-role"));

		User user = new User(id, username, password, fullName, email, address, phone, role);
		
		if(action.equals("ADD")) {
			userDao.addUser(user);
		} else if(action.equals("UPDATE")) {
			userDao.updateUser(user);
		}
		response.sendRedirect("users-management");
		
	}

}
