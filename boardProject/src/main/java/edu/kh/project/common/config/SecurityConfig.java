package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 서버 실행 시 객체로 만들어져서 내부 메서드를 모두 수행
								// 서버에 적용된 설정, Bean생성 시 
public class SecurityConfig {

	@Bean // 메서드에서 반한된 객체를 Sprng르 등록하는 어놑이션
				// -> 서버에 적용될 설정, Bean 설정 시 ㅏ용
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		
		
		return new BCryptPasswordEncoder();
	}
}
