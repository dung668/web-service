package edu.ptit.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.model.OrderDTO;

/**
 * Servlet implementation class OrdersManagementController
 */
@WebServlet({"/admin/orders-management","/admin/change-order-status"})
public class OrdersManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDao;   
    public OrdersManagementController() {
        super();
        orderDao = new OrderDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 0;
		if(request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id")); // id cua user
		
		List<OrderDTO> orders = null;
		
		if(id > 0) { // neu co order id
			orders = orderDao.getOrdersByUserId(id);
			UserDAO userDao = new UserDAOImpl();
			String buyer = userDao.getUserFullNameByIdUser(id);
			request.setAttribute("buyer", buyer);
		}
		else orders = orderDao.getAllOrders();
		
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("./jsp/orders-management.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		// ajax change order status
		String value = request.getParameter("status");
		String orderid = request.getParameter("id");
		if(value != null && orderid != null) {
			
			int id = Integer.parseInt(orderid);
			int status = Integer.parseInt(value);
			
			orderDao.updateOrderStatus(id, status);
		}
		
		List<OrderDTO> orders = orderDao.getAllOrders();
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("./jsp/table-order.jsp").forward(request, response);
	}

}
