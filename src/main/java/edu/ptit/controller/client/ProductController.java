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

@WebServlet(urlPatterns= {"/product/*","/san-pham/*"})
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = 1;
		if(request.getParameter("id") != null)
			id = Integer.parseInt(request.getParameter("id"));
		
		ProductDAO productDao = new ProductDAOImpl();
		Product product = productDao.getProductById(id);
		if(product == null) { // neu id product sai
			response.sendRedirect("error");
			return;
		}
		// neu san pham tim thay trong csdl
		request.setAttribute("product", product);
		
		// lay danh sach category
		CategoryDAO categoryDao = new CategoryDAOImpl();
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
		
		// chuyen huong ve view
		request.getRequestDispatcher("client/jsp/product.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
