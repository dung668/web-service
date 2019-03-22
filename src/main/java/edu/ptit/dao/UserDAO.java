package edu.ptit.dao;

import java.util.List;

import edu.ptit.model.User;

public interface UserDAO {
	
	// them
	User addUser(User user);
	// sua
	void updateUser(User user);
	// xoa
	boolean removeUser(int id);
	// kiem tra trung
	boolean isDuplicate(String username, String email);
	// tim user theo username
	User getUserByUserName(String username);
	// tim user theo id
	User getUserByUserId(int id);
	// lay danh sach user
	List<User> getListUser();
	// kiem tra dang nhap
	boolean checkLoginInfo(String username, String password);
	// dem xem co bao nhieu thanh vien
	int countUsers();
	// lay ten user tu id cua user
	String getUserFullNameByIdUser(int id);
	
}
