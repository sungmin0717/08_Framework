package com.kh.test.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {
	
	private BookService service;
	
	@GetMapping("selectAllList")
	public List<Book> selectAllList() {
		return service.selectAllList();
	}
	
}