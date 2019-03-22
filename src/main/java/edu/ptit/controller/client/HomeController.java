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
import edu.ptit.util.OrderBy;

@WebServlet(urlPatterns= {"/home", "/trang-chu"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDao = new CategoryDAOImpl();
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
//		BrandDAO branchDao = new BrandDAOImpl();
//		List<Brand> allBranches = branchDao.getAllBrands();
//		request.setAttribute("branches", allBranches);
		ProductDAO productDao = new ProductDAOImpl();
		List<Product> products = productDao.getAllProductsLimit(OrderBy.ID, 1);
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("/client/jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
