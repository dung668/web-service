package edu.ptit.model;

import java.io.Serializable;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 389514189875091641L;
	
	private int id;
	private int customerId;
	private int quantity;
	private double price;
	private String createdDate;
	private int status;
	public String getCreatedDate() {
		return createdDate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getStatus() {
		return status;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", customerId=" + customerId + ", quantity=" + quantity + ", price=" + price
				+ ", createdDate=" + createdDate + ", status=" + status + "]";
	}
	
}
