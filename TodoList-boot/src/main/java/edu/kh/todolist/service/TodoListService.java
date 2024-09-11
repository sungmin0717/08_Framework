package edu.kh.todolist.service;

import java.util.Map;

import edu.kh.todolist.dto.Todo;

public interface TodoListService {

	/** 할일 목록 조회 + 완료된 할 일 개수
	 * 
	 * @return map
	 */
	Map<String, Object> selectTodoList();

<<<<<<< HEAD
	List<Todo> detailUser();


	
	
	
=======
	
	
	
	/**
	 * 할 일 추가
	 * @param todo
	 * @return return result
	 */
	int todoAdd(Todo todo);

	/**
	 * 상세 조회.
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);



	/** 완료 여부 변경
	 * 
	 * @param todoNo
	 * @return
	 */
	int todoComplate(int todoNo);


/** 업데이트
 * 
 * @param todo
 * @return
 */
	int todoUpdate(Todo todo);



/**
 * 삭제
 * @param todoNo
 * @return
 */
int todoDelete(int todoNo);
>>>>>>> ff9ec2f2d3566b43f7c1c8e7c44591ecb3342dd8










<<<<<<< HEAD
	
	

=======
>>>>>>> ff9ec2f2d3566b43f7c1c8e7c44591ecb3342dd8

}
