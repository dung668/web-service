package edu.ptit.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.model.Item;

@WebServlet({"/report-html","/admin/report-html"})
public class ReportHtmlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportHtmlController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/html");
		response.addHeader("Content-Disposition", "attachment; filename=" + "order.html");
		
		////////////
		int id = Integer.parseInt(request.getParameter("id")); // id cua order
		
		OrderDAO orderDao = new OrderDAOImpl();
		
		List<Item> items = orderDao.getOrderByOrderId(id);
		//request.setAttribute("items", items);
		
		double price = 0D;
		int quantity = 0;
		for(Item item : items) {
			price += item.getPrice() * item.getQuantity();
			quantity += item.getQuantity();
		}
		
		
		////////
		PrintWriter out = response.getWriter();
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Chi tiết hóa đơn</title>");
	    out.println("<meta charset='UTF-8'/>");
	    out.println("<style>");
	    out.println("th, td {padding: 5px;}");
	    out.println("</style>");
	    out.println("</head>");
	    out.println("<body style='text-align:center;'>");
		out.println("<h1>Hóa đơn chi tiết</h1>");
		out.println("<table border='1' style='display: -webkit-inline-box; text-align: center; padding: 10px;'>");
		out.println("<thead><tr>");
		out.println("<th>ID</th>");
		out.println("<th>Tên sản phẩm</th>");
		out.println("<th>Số lượng</th>");
		out.println("<th>Giá bán</th>");
		out.println("<th>Mã hóa đơn</th>");
		out.println("</tr></thead>");
		out.println("<tbody>");
		int i = 1; // con chạy cho id item
		for(Item item : items) {
			out.println("<tr>");
			out.println("<td>" + i++ + "</td>");
			out.println("<td>" + item.getProduct().getName() + "</td>");
			out.println("<td>" + item.getQuantity() + "</td>");
			out.println("<td>" + (int) item.getPrice() + " VNĐ</td>");
			out.println("<td>" + id + "</td>");
			out.println("</tr>");
		}
		out.println("<tr>");
		out.println("<td colspan='3'><strong>Tổng cộng : " + quantity + "</strong></td>");
		out.println("<td colspan='2' style='color: red;'>" + (int) price + " VNĐ</td>");
		out.println("</tr>");
		out.println("</tbody></table>");
		out.println("</body>");
	    out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
