package com.shinhan.section05;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component("myCar")

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Car {
	private String model = "ABC";
	private int price = 1000;

}
