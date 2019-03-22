package edu.ptit.util;

public enum OrderBy {
	
	ID("id"), NAME("name"), PRICE("price");
	
	private String something;
	private OrderBy(String something) {this.something = something;}
	public String getSomething() {return this.something;}
	public void setSomething(String something) {this.something = something;}
	
}
