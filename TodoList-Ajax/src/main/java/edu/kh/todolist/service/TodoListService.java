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




//검색 아이디 찾기
String searchTitle(int todoNo);



/** 전체 할 일 개수 조회.
 * 
 * @return
 */
int totalCount();




/** 완료된 할 일 개수 조회
 * 
 * @return completeCount
 */
int getCompleteCount();



/** 할일 전체 조회.
 * 
 * @return
 */
List<Todo> getTodoList();















}
