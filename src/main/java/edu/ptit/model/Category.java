package edu.ptit.model;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = -7998084488009117637L;
	private int id;
	private String name;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
