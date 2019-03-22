
package edu.ptit.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ptit.dao.NotificationDAO;
import edu.ptit.dao.impl.NotificationDAOImpl;
import edu.ptit.model.Notification;

@WebServlet("/admin/notifications")
public class NotificationsManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NotificationsManagementController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NotificationDAO notificationDao = new NotificationDAOImpl();
		List<Notification> notifications = notificationDao.getAllNotifications();
		
		request.setAttribute("notifications", notifications);
		
		request.getRequestDispatcher("jsp/notifications-management.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
