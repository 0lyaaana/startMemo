package com.startmemoproject.mbs.configurations;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

// 스프링 환경설정 클래스를 지정하는 애노테이션. 스프링 DI(의존성 주입) 컨테이너가 초기화 될 때 빈으로 등록
@Configuration

//요청 URL이 스프링 시큐리티의 제어를 받도록 지정하는 애노테이션
// 스프링 애플리케이션에 웹 보안 설정을 활성화하고, 웹 요청에 대한 보안 정책을 정의할 수 있음
// 요청 URL에 대한 보안 정책을 설정하거나, 로그인, 로그아웃, 권한 관리, 세션 관리 등을 설정할 수 있음
/* URL 접근 제어 (Authorization):
http.authorizeRequests() 메서드를 사용하여 특정 URL 경로에 대한 접근 권한을 설정할 수 있습니다.
예를 들어, /admin/** URL은 ADMIN 권한을 가진 사용자만 접근할 수 있도록 설정할 수 있습니다.
*/
// 비밀번호 암호화 : passwordEncoder() 메서드 사용
/* CSRF 보호 : 기본적으로 스프링 시큐리티는 CSRF 보호를 활성화. 이 보호 기능은 크로스 사이트 요청 위조(CSRF) 공격을 막기 위한 것.
http.csrf().disable()을 사용하여 CSRF 보호를 비활성화할 수도 있습니다. (단, 주의가 필요합니다)
*/
@EnableWebSecurity
public class SecurityConfig {

/* @Configuration이 적용된 클래스의 메서드에 @Bean 애노테이션을 지정하면
* 빈을 생성하는 메소드가 되며 이 메서드 안에서 반환하는 객체는 스프링 DI
* 컨테이너에 의해서 스프링 Bean으로 관리된다. 그리고 이 객체를 주입 받기를
* 원하는 @Autowired, @Inject 등과 같은 애노테이션이 적용된 곳으로 (자동)주입된다.
**/
    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    // SpringSecurityFilterChain이 동작하여 URL 필터가 자동으로 적용
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(
    	/* 스프링 시큐리티를 적용하면 모든 요청 URL에서 인증을 시도하여 cv cv4
    	* 로그인 창이 나타난다. 그래서 다음과 같이 별도로 인증하지 않도록
    	* 설정하면 사이트의 모든 페이지에 접근할 수 있다.
    	**/
    	authorizeHttpRequests ->
    	authorizeHttpRequests.requestMatchers(
    	new AntPathRequestMatcher("/**"))
    	.permitAll()) 
    	
    	/* 스프링 시큐리티가 CSRF 처리시 아래 URL은 예외로 처리하도록 설정
    	* CSRF(cross site request forgery)는 웹 사이트 취약점 공격을 방지하기
    	* 위한 기술로 서버 상태를 변화시킬 수 있는 POST, PUT, PATCH, DELETE
    	* 등의 요청을 보호한다.
    	**/
    	.csrf(csrf -> csrf.ignoringRequestMatchers(
    	new AntPathRequestMatcher("/h2-console/**")))
    	.csrf(csrf -> csrf.disable())
    	// 스프링 시큐리티 로그아웃 기능 설정
    	.logout((logout) -> logout
    	//
    	.logoutUrl("/logout")
    	// 기본 URL은 POST 방식의 /logout
    	.logoutSuccessUrl("/loginForm") // 로그아웃 성공 리다이렉트 페이지
    	.invalidateHttpSession(true)); // 기존 세션 삭제 여부
    	
    	return http.build();
    }

}
