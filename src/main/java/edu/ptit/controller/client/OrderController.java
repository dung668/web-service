package edu.ptit.controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.UserDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.dao.impl.UserDAOImpl;
import edu.ptit.model.Order;
import edu.ptit.model.User;

@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderDAO orderDao;
    public OrderController() {
        super();
        orderDao = new OrderDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order"); // chi con truong hop truy cap truc tiep
		String userName = session.getAttribute("login_user").toString(); // filter loc user roi, nen neu co thi la da dang nhap
		UserDAO userDao = new UserDAOImpl();
		User user = userDao.getUserByUserName(userName);
		int id = 0; // order id, lay de in hoa don
		if(order == null) {
			response.sendRedirect("./"); // neu nguoi dung chua mua hang, ve trang chu
			return;
		}else {
			order.setUser(user);
			id = orderDao.addOrder(order);
		}
		session.removeAttribute("order"); // sau khi thanh cong, xoa session order di
		// dieu huong ve view hien thi
		//request.getRequestDispatcher("client/jsp/static/success.html").forward(request, response);
		response.sendRedirect("./success?id=" + id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
