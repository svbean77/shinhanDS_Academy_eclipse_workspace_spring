package com.shinhan.section02;

// 4개가 다 같은 말! 
// VO (Value Object)
// DTO (Date Transfer Object)
// POJO (Plain Old Java Object)
// Bean (Spring이 부르는 객체 -> new Car() 하면 객체임 (지금은 정의))
public class Car {
	private String model;
	private int price;
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
		System.out.println("Car의 arg 2개 생성자");
	}
	
	public Car() {
		System.out.println("Car의 default 생성자");
	}

	public String getModel() {
		System.out.println("Car의 getter: model");
		return model;
	}

	public void setModel(String model) {
		System.out.println("Car의 setter: model");
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", price=" + price + "]";
	}
	
	
}
