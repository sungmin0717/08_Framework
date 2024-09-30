package com.kh.test.service;

import java.awt.print.Book;
import java.util.List;

public interface BookService {

	/** 책 목록 전체 조회
	 * 
	 * @return
	 */
	List<Book> selectAllList();

}
