package game.video.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import game.video.project.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
@Autowired
private MemberService service; //의존성 주입
	
	
	@PostMapping("/login")
	public String login() {
		
		
		
		
		return null;
	}
	
}
