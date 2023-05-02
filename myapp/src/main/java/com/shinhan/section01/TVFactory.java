// f4()
package com.shinhan.section01;

public class TVFactory {
	public static TV makeTV(String brand) {
		if(brand.equals("sam")) {
			return new SamsungTV();
		} else if(brand.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
}
