package edu.ptit.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = -6854453366748387126L;
	private int id;
	private User user;
	private List<Item> items;
	private String createdDate;
	private int status;
	public Order() {
		super();
	}
	public Order(int id, User user, List<Item> items, String createdDate, int status) {
		super();
		this.id = id;
		this.user = user;
		this.items = items;
		this.createdDate = createdDate;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", items=" + items + ", createdDate=" + createdDate + ", status="
				+ status + "]";
	}
	
}
