package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@Data / getter + setter
//@AllArgsConstructor / 생성자
//@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private  String password;
	private  String email;
}
