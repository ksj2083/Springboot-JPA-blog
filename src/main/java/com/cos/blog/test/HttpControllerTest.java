package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일) 하려면?
//@Controller 로 해야함

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";

	//인터넷 브라우저 요청은 무조건 get 요청만 가능
	//http://localhost:8080/http/get
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = new Member(1, "ssar", "1234", "email");
		System.out.println(TAG+"getter : "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"getter : "+m.getId());
		return "lombok test 완료";
	}
//	
//	//http://localhost:8080/http/post
//	@PostMapping("/http/Post")
//	public String postTest() {
//		return "post 요청";
//	}
//	
//	//http://localhost:8080/http/put
//	@PutMapping("/http/put")
//	public String putTest() {
//		return "put 요청";
//	}
//	
//	//http://localhost:8080/http/delete
//	@DeleteMapping("/http/delete")
//	public String deleteTest() {
//		return "delete 요청";
//	}
}
