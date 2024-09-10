package edu.kh.todolist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.mapper.TodoListMapper;


@Transactional // 내부 메서드 수행 후 트랜잭션 처리 수행
							// - 예외 발생 시 rollback, 아님 commit
@Service //서비스 역할임을 명시
public class TodoListServiceImpl implements TodoListService{

	@Autowired	// 등록된 bean 중에서 같은 타입을 얻어와 대입(DI)
	private TodoListMapper mapper;
	
	
	//) 할 일 목록 조회.
	@Override
	public Map<String, Object> selectTodoList() {
		
		List<Todo> todoList = mapper.selectTodoList();
		
		//2 완료된 할 일 개수 조회
		
		int completeCount = mapper.selectCompleteCount();
		
		//3 map 객체 생성 후 조회 결과 담기
		Map<String, Object> map= new HashMap<>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
	}
	
	
	// 할 일 추가
	@Override
	public int todoAdd(Todo todo) {
		
		
		
		return mapper.todoAdd(todo);
	}
	// 할 일 상세 조회.
	@Override
	public Todo todoDetail(int todoNo) {
		return mapper.todoDetail(todoNo);
	}
	
//	완료 여부 변경
	@Override
	public int todoComplate(int todoNo) {
		return mapper.todoComplate(todoNo);
	}


	// 업데이트
	@Override
	public int todoUpdate(Todo todo) {
		
		
		return mapper.todoUpdate(todo);
	}
	//삭제
@Override
public int todoDelete(int todoNo) {
	return mapper.todoDeletet(todoNo);
}

@Override
public String searchTitle(int todoNo) {
	return mapper.searchTitle(todoNo);
}


//전체 조회.
@Override
public int totalCount() {
	return mapper.totalCount();
}

//완료된 할 일 값 조회.
@Override
public int getCompleteCount() {
	return mapper.selectCompleteCount();
}

//할일 전체 조회
@Override
public List<Todo> getTodoList() {
	return mapper.selectTodoList();
}


}
