package org.shinhan.section06;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class License {
	private String title;
	private int year;
	
}
