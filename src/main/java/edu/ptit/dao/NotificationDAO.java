

package edu.ptit.dao;

import java.util.List;

import edu.ptit.model.Notification;

public interface NotificationDAO {
	
	// them
	Notification addNotification(Notification notification);
	
	// lay danh sach
	List<Notification> getAllNotifications();
	
}
