package edu.ptit.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.model.Item;

/**
 * Dang hoan thien not
 */
@WebServlet("/admin/view-order-details")
public class ViewOrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewOrderDetailsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id")); // id cua order
		
		OrderDAO orderDao = new OrderDAOImpl();
		
		List<Item> items = orderDao.getOrderByOrderId(id);
		request.setAttribute("items", items);
		
		double price = 0D;
		int quantity = 0;
		for(Item item : items) {
			price += item.getPrice() * item.getQuantity();
			quantity += item.getQuantity();
		}
		request.setAttribute("price", price);
		request.setAttribute("quantity", quantity);
		
		
		request.getRequestDispatcher("./jsp/view-order-details.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
