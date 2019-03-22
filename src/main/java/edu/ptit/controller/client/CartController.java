package edu.ptit.controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;
import edu.ptit.model.Item;
import edu.ptit.model.Order;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// categoryDao
		CategoryDAO categoryDao = new CategoryDAOImpl();
		request.setAttribute("categories", categoryDao.getAllCategories());
		
		// tinh so luong product trong cart
		Order order = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("order") != null)
			order = (Order) session.getAttribute("order");
		else {
			request.getRequestDispatcher("client/jsp/cart.jsp").forward(request, response);
			return;
		}
		int quantity = 0;
		double price = 0;
		for(Item item : order.getItems()) {
			quantity += item.getQuantity();
			price += item.getQuantity() * item.getPrice();
		}
		request.setAttribute("quantity", quantity);
		request.setAttribute("price", price);
		
		// dieu huong ve view
		request.getRequestDispatcher("client/jsp/cart.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
