package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장해 준다
@Getter
public class PrincipalDetail implements UserDetails {
	private User user; //composition

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정 만료 여부 리턴 true: 정상
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//비밀번호 잠김여부 리턴 true: 정상
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 만료여부 리턴 true:정상
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화되어 있는지 리턴 true: 정상
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정이 가진 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();		
		auth.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
		
		return auth;
	}
	
}
