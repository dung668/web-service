package edu.ptit.controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.ProductDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;
import edu.ptit.dao.impl.ProductDAOImpl;
import edu.ptit.model.Category;
import edu.ptit.model.Product;
import edu.ptit.util.Constants;
import edu.ptit.util.OrderBy;

@WebServlet( urlPatterns = { "/shop/*", "/category", "/search" })
public class ShowProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowProductsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// unicode
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// SAP XEP
		OrderBy order = null;
		String orderBy = request.getParameter("orderBy");
		System.out.println(orderBy);
		order = orderBy == null ? OrderBy.ID : OrderBy.valueOf(orderBy); // neu khong truyen vao, mac dinh la orderBy ID
		// PHAN TRANG
		// neu pageId = null thi mac dinh la 1
		int pageId = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		
		// XU LY VIEW
		CategoryDAO categoryDao = new CategoryDAOImpl();
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
		
		// Xu ly action: hien thi tat ca product, hien thi product theo category, theo search
		ProductDAO productDao = new ProductDAOImpl();
		List<Product> products = productDao.getAllProductsLimit(OrderBy.ID, pageId);
		if(request.getRequestURI().contains("/category") && request.getParameter("id") != null) {
			products = productDao.getLimitProductsByCategory(Integer.parseInt(request.getParameter("id")), OrderBy.ID, pageId);
		} else if(request.getRequestURI().contains("/search") && request.getParameter("keyword") != null) {
			products = productDao.getLimitProductsByKeyWord(request.getParameter("keyword"), OrderBy.ID, pageId);
		}
		
		request.setAttribute("products", products);
		
		int nbOfProducts = productDao.countProducts(); // dem so product
		int pages = (int) Math.ceil((double) nbOfProducts / Constants.NUMBER_PER_PAGES); // tinh so trang 
		System.out.println(nbOfProducts + " " + Constants.NUMBER_PER_PAGES + " " + pages);
		request.setAttribute("pages", pages);
		// Dieu huong sang trang jsp 
		request.getRequestDispatcher("/client/jsp/show-products.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
