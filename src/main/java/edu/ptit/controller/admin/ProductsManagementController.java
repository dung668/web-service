package edu.ptit.controller.admin;

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

@WebServlet("/admin/products-management")
public class ProductsManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDAO productDao;
    public ProductsManagementController() {
        super();
        productDao = new ProductDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// check action (EDIT / DELETE)
		String action = request.getParameter("action");
		if(action != null) {
			
			if(action.equals("EDIT")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Product prodct = productDao.getProductById(id);
				request.setAttribute("prodct", prodct);
			} else if(action.equals("DELETE")) {
				int id = Integer.parseInt(request.getParameter("id"));
				productDao.removeProduct(id);
			}
			
		}
		
		// Current page
		int pageId = 1;
		if(request.getParameter("pageId") != null)
			pageId = Integer.parseInt(request.getParameter("pageId"));
		
		List<Product> products = productDao.getAllProductsLimit(OrderBy.ID,pageId);
		
		CategoryDAO categoryDao = new CategoryDAOImpl();
		List<Category> categories = categoryDao.getAllCategories();
		
		request.setAttribute("products", products);
		request.setAttribute("categories", categories);
		
		// Pagination
		int nbOfProducts = productDao.countProducts(); // dem so product
		int pages = (int) Math.ceil((double) nbOfProducts / Constants.NUMBER_PER_PAGES); // tinh so trang 
		request.setAttribute("pages", pages);
		
		request.getRequestDispatcher("./jsp/products-management.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// action = ADD / UPDATE
		String action = request.getParameter("action");
		
		int id = request.getParameter("product-id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("product-id"));
		String name = request.getParameter("product-name");
		double price = Double.parseDouble(request.getParameter("product-price"));
		int cateId = Integer.parseInt(request.getParameter("cateId"));
		String details = request.getParameter("product-details");
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		product.setCategory(new CategoryDAOImpl().getCategoryById(cateId));
		product.setDetails(details);
		
		System.out.println(product);
		
		if(action.equals("ADD")) {
			productDao.addProduct(product);
		} else if(action.equals("UPDATE")) {
			productDao.updateProduct(product);
		}
		response.sendRedirect("products-management");
		
	}

}
