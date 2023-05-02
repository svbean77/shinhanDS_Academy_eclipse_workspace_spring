package com.shinhan.section01;

public class TVUser {

	public static void main(String[] args) {
		f1();
		f2();
		// f1(), f2()는 객체가 변하면 함수도 변경 -> 좋지 않은 방법!
		f3();
		// 생성 객체만 변할 수 있도록 interface를 사용! -> 위의 방법보다는 낫지만 이것도 좋은 방법이 아니야..
		// TVUser가 SamsungTV, LgTV에 의존적이기 때문에 좋은 방법이 아니야!!
		
		f4();
		// TVUser가 TVFactory에만 의존 -> 변경 사항이 생기면(ex. 생성자 바뀜) Factory만 바꾸면 됨!
		// -> 하지만 어차피 공장도 수정을 해야하는 거 아니야? => 완전 의존을 삭제하는 방법이 Spring framework 사용!
	}

	private static void f4() {
		TV tv = TVFactory.makeTV("sam");
		tv.powerOn();
		tv.powerOff();
	}

	private static void f3() {
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.powerOff();
	}

	private static void f2() {
		LgTV tv = new LgTV();
		tv.powerOn();
		tv.powerOff();
	}

	private static void f1() {
		SamsungTV tv = new SamsungTV();
		tv.powerOn();
		tv.powerOff();
	}

}
