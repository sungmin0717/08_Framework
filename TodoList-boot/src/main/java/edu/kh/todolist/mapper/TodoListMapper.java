package edu.kh.todolist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.dto.Todo;

@Mapper
public interface TodoListMapper {

	
	/** 할일 목록 조회
	 * 
	 * @return todoList
	 */
	List<Todo> selectTodoList();

	
	
	/** 완료된 할 일 개수 조회
	 * 
	 * @return completeCount
	 */
	int selectCompleteCount();

}
