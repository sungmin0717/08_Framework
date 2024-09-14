package game.video.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/") // "/" 요청 매핑(method 가리지 않음)
	public String mainPage() {

		
		return "common/main";
	}
}
