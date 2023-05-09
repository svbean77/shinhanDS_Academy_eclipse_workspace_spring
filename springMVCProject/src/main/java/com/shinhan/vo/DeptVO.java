package com.shinhan.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter @Getter
@ToString
public class DeptVO {
	private Integer department_id;
	private String department_name;
	private Integer manager_id;
	private Integer location_id;
}
