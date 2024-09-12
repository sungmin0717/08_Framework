package game.video.project.member.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import game.video.project.member.dto.Member;

@Controller
public class MainController {
	
	
	@RequestMapping("/")
	public String mainPage(
			) {
		
		
		
		return "common/main";
		
	}
	
	
}
