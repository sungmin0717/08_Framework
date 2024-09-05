package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller // 컨트롤러 역할 요청 응답 제어 + Bean 등록
public class MainController {
	
	// "/" 요청 (== lcocalhost, 최상위) 시 
	//매핑아여 처리하는 메서드
	// -> index.html로 응답하는것이 아닌
	//
	//해당 메서드에서 요청 처리/응답 수행.
	
	// 장점 : JAVA를 거쳐서 메인 페이지가 보여지
	// 			-> 추가 세팅 값, DB조회 값을 위임된 html에서 출력가능
	// 				== 메인 페이지에서 부터 DB 조회 값이 보여지게돈다.
	
	@RequestMapping("/")
	public String mainPage() {
		
		//사용하는 템플릿 엔진 : Thyeleaf
		//Thymeleaf를 사영하는 프로젝트에서 forward시
		//제공하는 접두사 : classpath:/templates/
		//제공하는 접미사 : .html
		
		// classpath:/templates/main.html
		return "common/main";
	}

}
