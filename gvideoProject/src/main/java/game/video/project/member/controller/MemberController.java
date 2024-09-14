package game.video.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

import game.video.project.member.dto.Member;


@Slf4j
@Controller
@RequestMapping("user")
public class MemberController {

	
//  회원가입
		@GetMapping("join")
		public String loginPage(Member member) {
			
			
			
			
			
			
			return "common/user/joinIn";
		}
		
		
		
		// 로그인
		@GetMapping("login")
		public String login() {
			
			
			
			return "user/lgoin/login";
		}
		
		
		
		
		
		
		
}
	
	
	
	
