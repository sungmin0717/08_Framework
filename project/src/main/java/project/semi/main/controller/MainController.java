package project.semi.main.controller;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import project.semi.main.service.MainService;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService service;
	
	
}
