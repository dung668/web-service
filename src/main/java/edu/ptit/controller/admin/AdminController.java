package edu.ptit.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.ProductDAO;
import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.dao.impl.ProductDAOImpl;
import edu.ptit.dao.impl.UserDAOImpl;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns= {"/admin/home","/admin"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// In case user request through url /admin, auto send redirect to /admin/home
		if(request.getRequestURI().substring(request.getContextPath().length()).equals("/admin")) {
			response.sendRedirect("admin/home");
			return;
		}
		
		UserDAO userDao = new UserDAOImpl();
		ProductDAO productDao = new ProductDAOImpl();
		OrderDAO orderDao = new OrderDAOImpl();
		
		int numberOfUsers = userDao.countUsers();
		int numberOfProducts = productDao.countProducts();
		int numberOfOrders = orderDao.countOrders();
		
		request.setAttribute("numberOfUsers", numberOfUsers);
		request.setAttribute("numberOfProducts", numberOfProducts);
		request.setAttribute("numberOfOrders", numberOfOrders);
		
		// dieu huong ve view
		request.getRequestDispatcher("/admin/jsp/index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
