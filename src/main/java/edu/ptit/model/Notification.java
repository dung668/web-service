
package edu.ptit.model;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 2228084488009117637L;
	private int id;
	private String username, message, created_at; // id / user
	int id_post;
	
	public Notification() {
		super();
		
	}
	
	public Notification(int id, String username, String message, int id_post, String created_at) {
		super();
		this.id = id;
		this.username = username;
		this.message = message;
		this.id_post = id_post;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", username=" + username + ", message=" + message + ", id_post=" + id_post
				+ ", created_at=" + created_at + "]";
	}

	
	
}

