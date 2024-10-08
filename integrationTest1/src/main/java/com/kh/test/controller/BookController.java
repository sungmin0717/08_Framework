package com.kh.test.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor 
public class BookController {
	
	private final BookService service;
	
	@ResponseBody // @ResponseBody 비동기 요청 코드에 
	// 			컨트롤러 반환 값을 그대로 전달하는 어노테이션
	@GetMapping("selectAllList")
	public List<Book> selectAllList() {
		
		
		return service.selectAllList();
	}
	
}