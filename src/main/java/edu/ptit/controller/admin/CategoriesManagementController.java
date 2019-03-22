package edu.ptit.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.CategoryDAO;
import edu.ptit.dao.impl.CategoryDAOImpl;
import edu.ptit.model.Category;

@WebServlet("/admin/categories-management")
public class CategoriesManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDAO categoryDao;
    public CategoriesManagementController() {
        super();
        categoryDao = new CategoryDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Category> categories = categoryDao.getAllCategories();
		request.setAttribute("categories", categories);
		
		// check action (EDIT / DELETE)
		String action = request.getParameter("action");
		if(action != null) {
			
			if(action.equals("EDIT")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Category category = categoryDao.getCategoryById(id);
				request.setAttribute("category", category);
			} else if(action.equals("DELETE")) {
				int id = Integer.parseInt(request.getParameter("id"));
				categoryDao.removeCategory(id);
				response.sendRedirect("categories-management");
				return;
			}
			
		}
		
		request.getRequestDispatcher("./jsp/categories-management.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		// action = ADD / UPDATE
		String action = request.getParameter("action");
		
		int id = request.getParameter("category-id").isEmpty() ? 0 : Integer.parseInt(request.getParameter("category-id"));
		
		String name = request.getParameter("category-name");
		
		Category category = new Category(id, name);
		
		if(action.equals("ADD")) {
			categoryDao.addCategory(category);
		} else if(action.equals("UPDATE")) {
			categoryDao.updateCategory(category);
		}
		
		response.sendRedirect("categories-management");
		
	}

}
