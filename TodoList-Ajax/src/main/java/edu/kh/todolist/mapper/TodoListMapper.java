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



	/** 할 일 추가
	 * 
	 * @param todo
	 * @return result
	 */
	int todoAdd(Todo todo);


/** 할 일 상세 조회.
 * 
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


/** 삭제
 * 
 * @param todoNo
 * @return
 */
int todoDeletet(int todoNo);


/** 아이디 검색 
 * 
 * @param todoNo
 * @return
 */
String searchTitle(int todoNo);



	
	
}
