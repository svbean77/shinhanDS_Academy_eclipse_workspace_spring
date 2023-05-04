package com.shinhan.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO, VO, JavaBeans: data를 저장해 전송하기 위함
@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminVO {
	private String email;
	private String manager_name;
	private String pass;
	private String pic;
	
	// 사진 넣는다고 고쳐서 이전에 있던 애들이 다 에러 -> 그러니 얘한테 3개짜리를 만들어주자
	public AdminVO(String email, String manager_name, String pass) {
		this.email = email;
		this.manager_name = manager_name;
		this.pass = pass;
	}

}
