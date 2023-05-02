package com.shinhan.section02;

public class Book {
	private String title;
	private int price;
	private String kind;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + ", kind=" + kind + "]";
	}
	
	
}
