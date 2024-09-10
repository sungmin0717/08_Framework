package edu.kh.todolist.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	/** 할일 목록 조회 + 완료된 할 일 개수
	 * 
	 * @return map
	 */
	Map<String, Object> selectTodoList();

	List<Todo> detailUser();


	
	
	










	
	


}
