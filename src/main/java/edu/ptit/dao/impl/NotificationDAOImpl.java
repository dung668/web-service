

package edu.ptit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ptit.dao.NotificationDAO;
import edu.ptit.model.Notification;
import edu.ptit.util.DBConnection;

public class NotificationDAOImpl implements NotificationDAO {

	@Override
	public Notification addNotification(Notification notification) {
		String sql = "INSERT INTO notification(username, message, id_post, created_at) VALUES (?,?,?,?)";
		try(
			Connection conn = DBConnection.getCon();
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, notification.getUsername());
			ps.setString(2, notification.getMessage());
			ps.setInt(3, notification.getId_post());
			ps.setString(4, notification.getCreated_at());

			ps.executeUpdate();
			return notification;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<Notification> getAllNotifications() {
		List<Notification> list = new ArrayList<>();
		String sql = "SELECT * FROM notification";
		try(
			Connection conn = DBConnection.getCon();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		){
			while(rs.next()) {
				Notification cate = new Notification();
				cate.setId(rs.getInt("id"));
				cate.setUsername(rs.getString("username"));
				cate.setMessage(rs.getString("message"));
				cate.setId_post(rs.getInt("id_post"));
				cate.setCreated_at(rs.getString("created_at"));
				list.add(cate);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
