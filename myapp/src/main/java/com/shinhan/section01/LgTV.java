// f1(), f2(), f3(), TVFactory
package com.shinhan.section01;

public class LgTV implements TV{
	@Override
	public void powerOn() {
		System.out.println(this.getClass().getSimpleName() + "의 전원 On~~");
	}

	@Override
	public void powerOff() {
		System.out.println(this.getClass().getSimpleName() + "의 전원 Off~~");
	}

}
