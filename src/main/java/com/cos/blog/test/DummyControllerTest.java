package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id :"+id;
	}
	
	@Transactional //더티체킹, 함수 종료 후 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json데이터를 요청 => Java Object (메시지 컨버터의 Jackson라이브러리가 자동으로 변환해준다..)
		System.out.println();
		
		//영속화
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	 	
		//userRepository.save(user);
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> Pagingusers = userRepository.findAll(pageable);
		
		List<User> users =  Pagingusers.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달받을 수 있음
	//http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//repository는 user을 Optional 타입으로 가져온다.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
			}
		});
		
		//요청 : 웹브라우저, 반환 : user객체
		//따라서 웹브라우저가 이해할 수 있는 언어로 변환(json)
		//스프링부트 -> ㅡ> MessageConverter가 응답시에 자동 작동
		//만약 리턴이 자바오브젝트라면 컨버터가 Jackson 라이브러리를 호출해서
		//user 오브젝트를 변환해서 브라우저에게 던져준다.
		return user;
	} 
	
	//http://localhost:8000/blog/dummy/join 요청
	//http의 body에 username, password, emial 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
	System.out.println("username : " + user.getUsername());
	System.out.println("password : " + user.getPassword());
	System.out.println("email : " + user.getEmail());
	
	user.setRole(RoleType.USER);
	userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
}
