// f1(), f2(), f3(), TVFactory
package com.shinhan.section01;

public class SamsungTV implements TV{
	public SamsungTV() {
		System.out.println("SamsungTV 기본 생성자");
	}
	public SamsungTV(String model) {
		System.out.println("SamsungTV args 있는 생성자");
	}
	
	@Override
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "의 전원 On");
	}

	@Override
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "의 전원 Off");
	}
	
}
