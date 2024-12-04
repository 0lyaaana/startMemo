package com.startmemoproject.mbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.startmemoproject.mbs.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	/* 회원 로그인 처리에 필요한 PasswordEncoder 객체를 의존성 주입하도록 설정
	*
	* 회원 비밀번호 암호화 처리를 위해서 스프링 시큐리티의 PasswordEncoder를 사용
	* 회원 로그인 요청시 DB 테이블에 암호화 되어 저장된 비밀번호와 사용자가
	* 입력한 일반 문자열 비밀번호를 비교하는데 사용되고 회원가입과 회원 정보 수정에서
	* 사용자가 입력한 비밀번호를 암호화 인코딩하여 저장하는데도 사용된다.
	* com.springbootstudy.bbs.configurations.SecurityConfig 클래스에서 스프링 빈을
	* 생성해 반환하는 @Bean을 적용한 메서드를 정의했기 때문에 여기로 주입된다.
	**/
	@Autowired
	private PasswordEncoder passwordEncoder;
	
}
