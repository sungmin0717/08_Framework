package edu.kh.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.todolist.dto.Todo;
import edu.kh.todolist.service.TodoListService;
import lombok.extern.slf4j.Slf4j;


@Slf4j									// log 필요시 사용해야할 어노테이션
@RequestMapping("todo") // todo 붙은것을 접두사처럼 활용가능
@Controller 						// 컨트롤러 명시 + Bean 등록.
												//				-> (IOC 객체 생성 및 관리를 Spring이 함).
public class TodoListController {

	
	@Autowired // 등록된 Bean 중에서 같은 타입 객체를 주입. (DI)
	private TodoListService service;
	
	
	
	/**
	 * @param todo : 커맨드 객체(제출된 파라미터를 필드에 저장한 DTO 객체)
	 *										-> -> 생략도 가능하다!!
	 * @param ra   : 리다이렉트(재요청) 시 request scope로 값 전달한는 객체
	 * @return
	 */
	@PostMapping("add")
	//@RequestMapping("todo") 생략
	public String todoAdd(
			@ModelAttribute Todo todo,
			//@ModelAttribute : 제출된 파라미터를 DTO 객체 필드에 대입
			RedirectAttributes ra										){ // todoAdd 생성
		
		// 서비스 호출 후 결과 반환 받기
		int result = service.todoAdd(todo);
		
		
		String message = null;
		
		if(result > 0) message = "할일 추가 성공";
		else 					 message = "추가 실패..";
		
		ra.addFlashAttribute("message",message);
		
		return "redirect:/"; //메인페이지 리다이렉트 main 페이지에 / 주소인 곳으로 간다.
	}
	
	
	/**
	 * 
	 * @param todoNo : 조회할 할 일의 PK 번호(@PathVariable 이용)
	 * @param model  : 데이터 전달용 객체 (forward 시 request scope 값 전달)
	 * @param ra     : null일시 메인페이지 변경 
	 * @return
	 */
	@GetMapping("detail/{todoNo}")
//	todoNo 값을 가져와 저장할것. @PathVariable
	public String todoDetail(
			@PathVariable("todoNo") int todoNo,
			Model model,
			RedirectAttributes ra
			) {
		
		Todo todo = service.todoDetail(todoNo);
		// Todo 에 있는 todoNo을 서비스로 끌고가기.
		
		
		
		if(todo == null) {
			// 조회한 결과가 없다면
			
			ra.addFlashAttribute("message", "할일이 존재하지 않습니다.");
			return "redirect:/"; // 메인페이지 소환.
		}
		// 조회 결과가 있을 경우
		model.addAttribute("todo", todo);
		
		return "todo/detail"; // 요청 위임
	}
	
	/** 완료 여부 변경
	 * 
	 * @param todoNo : 쿼리스트링으로 전달된 todoNo 값
	 * @param ra     : 리다이렉트 todoNo
	 * @return
	 */
	@GetMapping("complete")
	public String todoComplate(
			@RequestParam("todoNo") int todoNo,
			RedirectAttributes ra
			) {
		
		int result = service.todoComplate(todoNo);
//		결과가 1,0이기에 int로 todoNo을 서비스에 보낸다
		
		String message = null;
		String path = null;
		
		if(result > 0 ) {
			message = "변경 성공";
			path = "redirect:/todo/detail/" + todoNo; //상세조회.
		}else {  
			message = "할 일이 존재하지 않습니다.";
			path = "redirect:/"; // 메인페이지로 날리기
		}
		
		ra.addFlashAttribute("message", message);
		return path;
	}
	
	/** 수정 화면 전환
	 * 
	 * @param todoNo
	 * @param mode
	 * @param ra
	 * @return
	 */
	@GetMapping("update")
	public String todoUpdate(
		@RequestParam("todoNo") int todoNo,
		Model model,
		RedirectAttributes ra) {
		
		// 상세 조회 서비스 호출
		Todo todo = service.todoDetail(todoNo);
		
		if(todo == null) { // todoNo 일치하는 할 일이 없을 경우
			ra.addFlashAttribute("message", "할 일이 존재하지 않습니다");
			return "redirect:/"; // 메인 페이지
		}
		
		model.addAttribute("todo", todo);
		return "todo/update"; // 수정 화면 forward
	}
	
	
	/** 업데이트 수정 
	 * 
	 * @param model
	 * @param ra
	 * @param todo
	 * @return
	 */
//	입력해야할것이 3개 그러므로 넣어야할것이 3개이다.
	// 우리는 ModelAttribute를 이용해서 Todo에 필드명을 todo로 저장한다
	// 저장한값을 그대로 보낸다. mapper로 그러고난후 mapper에 3개의 갑을 저장.
	
	@PostMapping("update")
	public String update(
			RedirectAttributes ra,
			@ModelAttribute Todo todo
			// 제출되는 파라미터 필드명이 똑같으면 담을수있
			) {
		String path = null;
		String message = null;
		
		int result = service.todoUpdate(todo);
		
		if(result > 0) {	
			message = "수정되었습니다";
			path    = "redirect:/todo/detail/"+todo.getTodoNo();
			//String 문자열에 넣을려면 + 붙여서 todo에getTodoNo을 꺼내와야함.
		}else {
			message = "실패했습니다";
			path    = "redirect:/";
		}
		
		ra.addFlashAttribute("message",message);

		
		
		return path;
	}
//	삭제를 뭘 넣어서 해야하나? int를 가져와 넣어서 delete 를 해야한다
	// 그러기 위해 넣어줄수 있는 requestParam으로 todoNo을 가져와
	// 가장 안성맞춤인 int 로 저장해둔다. Ok
	@GetMapping("delete")
	public String todoDelete(
			@RequestParam("todoNo") int todoNo,
			RedirectAttributes ra
			) {
		
			String message = null;
			String path = null;
		
			int result = service.todoDelete(todoNo);
					
			if(result > 0) {
				message = "삭제되었습니다";
				path = "redirect:/"; //메인페이지
			}else {
				message = "삭제 실패";
				path = "redirect:/todo/detail/" + todoNo;
			}
			ra.addFlashAttribute("message", message);
			
		
		return path;
	}


	
	
	
	
	
	
}
