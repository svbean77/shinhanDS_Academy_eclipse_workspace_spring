package com.shinhan.section04;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component // <bean id="book" class="패키지.Book"/>와 같은 의미 (클래스 첫 글자 소문자로 한 이름을 id로 설정함)

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Book {
	private String title;
	private int price;
	private String kind;
	
}
