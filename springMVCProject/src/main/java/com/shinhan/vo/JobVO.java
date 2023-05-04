package com.shinhan.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class JobVO {
	private String job_id;
	private String job_title;
	private int min_salary;
	private int max_salary;
}
