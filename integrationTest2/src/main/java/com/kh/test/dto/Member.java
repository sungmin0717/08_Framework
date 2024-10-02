package com.kh.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	
	private int memberNo;
//	private String memberName;
//	private String memberId;
//	private String memberPassword;
	private String name;
	private String address;
	private String age;
	
	
}