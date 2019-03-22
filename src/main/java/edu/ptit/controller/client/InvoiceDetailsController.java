package edu.ptit.controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.model.Category;
import edu.ptit.model.Item;

@WebServlet(urlPatterns= {"/invoice-details"})
public class InvoiceDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InvoiceDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		OrderDAO orderDao = new OrderDAOImpl();
		List<Item> items = orderDao.getOrderByOrderId(id);
		request.setAttribute("items", items);
		
		CategoryDAO categoryDao = new CategoryDAOImpl();
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
		
		double price = 0D;
		int quantity = 0;
		for(Item item : items) {
			price += item.getPrice() * item.getQuantity();
			quantity += item.getQuantity();
		}
		request.setAttribute("price", price);
		request.setAttribute("quantity", quantity);
		
		request.getRequestDispatcher("client/jsp/invoice-details.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
