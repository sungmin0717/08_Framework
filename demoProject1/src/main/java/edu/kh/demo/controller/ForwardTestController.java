package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Controller : 어떤 요청을 받고, 어떻게 응답할지 제어하는 역할
// - 어떤 요청을 처리할지 주소 매핑.
// - 요청 처리된 결과에 따라 응답하는 방법(forward, redirect, 값등)

// * instance : 개발자가 new를 이용해 만든 객체
// * bean     : Java(Spring)이 만든 객체

// @Controller 어노테이션
// 1. Controller 임을 명시
// 2. 클래스에 작성된 내용대로 구현 -> 객체 생성
// 			-> 스프링이 객체 생성 == Bean 등록/ Bean 생성




@Controller
public class ForwardTestController {

	// 기존 Servlet
	// - @WebServlet("요청 주소") -> 클래스명 위에 작성
	// == 해당 클래스를 "요청주소" 를 매핑해서 처리하는 클래스
	// -> 클래스별로 요청 주소 1개만 처리 가능
	
	
	/** Spring Contriller*
	 * 
	 * - @RequestMapping("요청주소") -> 클래스명, 메서드명 위에 작성
	 * 	또는 @GetMapping("요청수조") -> 메서드명 위에 작성
	 * 	또는 @PostMapping("요청수조")-> 메서드명 위에 작성
	 * 
	 * 
	 * @RequestMapping("요청주소") 뜻
	 * - 요청주소를 처리할 클래스/메서드를 매핑하는 어노테이션
	 * 
	 * 1) 클래스 위에 작성하는경우
	 * 	-공통 주소를 매핑.
	 * 	ex) /todo/select, /todo/insert/
	 * 1) 메서드 위에 작성하는경우
	 * 			-"요청 주소"로 요청을 받는 겨우
	 * 
	 */
	
	//Controller 메서드 작성 방법
	
	// 1) 접근 제한자는 무조건 public 
	
	// 2) 반환형은 대부분 String
	// 		(ModelAndView 또는 Ajax 사용 시 달라질 수 있음)
	
	// - 왜 String 해야하는데? 너가 뭔데?
	// 	-> Controller 메서드에서 반환되는 문자열이
	//  	forward할 html파일의 경로가 되기 때문!!!!!
	
	
	
	// 3) 메서드명은 의미있게 마음대로 작성
	
	// 4) 배개변수는 필요한만큼 마움댜로
	//  -> Arguments Resolver 참조
	
	// 5) 매핑할 요총 주소를 @requestMapingg
	
	
	/*String Boot Controller에서
	 * 특수한 경우를 제외하고
	 * 매핑 주소 제일 앞에 "/를 적지안는다"
	 * 
	 * */
	
	
	
	@RequestMapping("forward") // /forward 요청 매핑 (GET/POST가리지 않음)
	public String forwardTest() {
		System.out.println("/forward 매핑됐는지 확인");
		
		
		/*	Thymeleaf : 템플릿 엔진(JSP 대신 사용)
		 * 
		 * - Thymeleaf 사용 시 접두사, 접미사가 제공됨.
		 * 
		 * - 접두사(prefix) : classpath:/tempates/
		 * - 접미사(suffix) : .html
		 * 
		 * * Controller 메서드에서 반환되는 문자열에
		 * 	앞, 뒤에 접두사,접미사가 붙어서 
		 * forward할 html 파일의 경로 형태가 된다.!!!
		 */

		//classpath:/tempates/forward.html
		return "forward";
		
	}
	
}
