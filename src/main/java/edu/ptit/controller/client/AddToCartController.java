package edu.ptit.controller.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ptit.dao.ProductDAO;
import edu.ptit.dao.impl.ProductDAOImpl;
import edu.ptit.model.Item;
import edu.ptit.model.Order;
import edu.ptit.model.Product;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDAO productDao;
    
    public AddToCartController() {
        super();
        productDao = new ProductDAOImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quantity = 1;
		int id;
		
		if(request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			Product product = productDao.getProductById(id);
			if(product != null) {
				quantity = Integer.parseInt(request.getParameter("quantity"));
				HttpSession session = request.getSession();
				if(session.getAttribute("order") == null) { // chua co order nao
					Order order = new Order();
					List<Item> listItems = new ArrayList<Item>();
					Item item = new Item();
					item.setQuantity(quantity);
					item.setProduct(product);
					item.setPrice(product.getPrice());
					listItems.add(item);
					order.setItems(listItems);
					session.setAttribute("order", order);
				} else { // neu order da ton tai trong session
					Order order = (Order) session.getAttribute("order");
					List<Item> listItems = order.getItems();
					boolean isProductExist = false;
					for(Item i : listItems) {
						if(i.getProduct().getId() == id) {
							i.setQuantity(i.getQuantity() + quantity);
							isProductExist = true;
						}
					}
					if(isProductExist == false) {
						Item item = new Item();
						item.setProduct(product);
						item.setQuantity(quantity);
						item.setPrice(product.getPrice());
						listItems.add(item);
					}
					session.setAttribute("order", order);
				}
				response.sendRedirect("cart");
			} else response.sendRedirect("./");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		
		/*
		HttpSession session = request.getSession();
		
		int productId = 0;

		productId = Integer.parseInt(request.getParameter("id"));
		// lay so luong san pham
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		// User da dang nhap, check xem da co session mua hang chua
		Order order = null;
		if(session.getAttribute("cart") == null) { // neu day la lan dau add to cart
			order = new Order();
			session.setAttribute("cart", order);
			List<Item> lItem = new ArrayList<>();
			lItem.add(new Item(id, product, quantity, price))
		}
		else order = (Order) session.getAttribute("cart");
		
		// xem product da co trong cart chua
		Iterator<Item> itr = order.getItems().iterator();
		while(itr.hasNext()) {
			Item i = itr.next();
			if(i.getProduct().getId() == productId) {
				i.setQuantity(i.getQuantity() + quantity);
			}
		}
		
		// lay product trong db theo id
		ProductDAO productDao = new ProductDAOImpl();
		Product product = productDao.getProductById(productId);
		
		//String user = session.getAttribute("login_user").toString();
		
		*/
	}

}
