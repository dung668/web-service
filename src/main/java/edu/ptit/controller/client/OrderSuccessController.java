package edu.ptit.controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;

@WebServlet("/success")
public class OrderSuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderSuccessController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// categoryDao
		CategoryDAO categoryDao = new CategoryDAOImpl();
		request.setAttribute("categories", categoryDao.getAllCategories());
		
		request.setAttribute("orderId", request.getParameter("id"));
		System.out.println(request.getParameter("id"));
		// dispatcher ve trang success
		request.getRequestDispatcher("client/jsp/success.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
