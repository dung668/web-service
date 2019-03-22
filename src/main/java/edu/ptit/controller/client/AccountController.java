package edu.ptit.controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.model.User;

/**
 * Servlet implementation class AccountController
 */
@WebServlet(urlPatterns= {"/account/*","/tai-khoan/*"})
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AccountController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// neu nguoi dung da dang nhap, lay thong tin nguoi dung
		HttpSession session = request.getSession();
		// lay username dang duoc dang nhap
		String username = session.getAttribute("login_user").toString();
		UserDAO userDao = new UserDAOImpl();
		User user = userDao.getUserByUserName(username); // doi tuong user dang dang nhap
		
		CategoryDAO categoryDao = new CategoryDAOImpl();
		request.setAttribute("categories", categoryDao.getAllCategories());
		
		// truyen thong tin nguoi dung de hien thi tren view
		request.setAttribute("user", user);
		request.getRequestDispatcher("client/jsp/account.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
