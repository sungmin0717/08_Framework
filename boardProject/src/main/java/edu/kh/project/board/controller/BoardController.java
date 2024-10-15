package edu.kh.project.board.controller;

import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.dto.Board;
import edu.kh.project.board.dto.Comment;
import edu.kh.project.board.dto.Pagination;
import edu.kh.project.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("board") 
public class BoardController {

	private final BoardService service;
	
	/** 게시글 목록 조회.
	 * @param  boardCode : 게시판 종류 번호
	 * @param cp : 현재 조회하려는 목록의 페이지 번호
	 * 							(필수 아님, 없으면 1)
	 * @param model : forward 시 데이터 전달하는 용도의 객체 (request scope)
	 * 
	 */
	
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(
			@PathVariable("boardCode") int boardCode, // /board/2 요청이 오면 얻어와 매개변수에 집어 넣겟따.
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			// 무조건 제출해야한다 기본값은 cp 근데 없을수도 있어 기본값은 1
			Model model,
			@RequestParam Map<String, Object> paramMap
			) {
		log.debug("paramMap : {}", paramMap);
		
		//서비스 호출 후 결과 반환 받기
		// - 목록 조회인데 Map으로 반환 받는 이유?
		// -> 서비스에서 여러 결과를 만들어내야되는데 
		// 		메서드는 반환을 1개만 할 수 있기 떄문에 
		// 		Map으로 묶어서 반환 받을 예정
		
		Map<String, Object> map = null;
		
		//검색이 아닌 경우 == 일반 목록 조회
		if(paramMap.get("key") == null) {
			
		map = service.selectBoardList(boardCode, cp);
		
		}else { //검색한 경우
			
			//paramMap에 key, query 담겨 있음
			map = service.selectSearchList(boardCode, cp, paramMap);
		}
		
		
		
		
		// map 에 묶여잇는 값 풀어놓기.
		List<Board> boardList = (List<Board>)map.get("boardList");
		Pagination pagination = (Pagination)map.get("pagination");
		
		//정상 조회 되었는지 log 확인
//		for(Board b : boardList) log.debug(b.toString());		
//		
		log.debug(pagination.toString());

		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pagination);
		
		return "board/boardList";
	}
	
	/** 게시글 상세 조회
	 * @param boardCode : 게시판 종류
	 * @param boardNo   : 게시글 번호
	 * @param model     : forward 시 request scope 값 전달 객체
	 * @param ra        : redirect시 request scope 값 전달 객체
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String boardDetail(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			Model model,
			RedirectAttributes ra
			
			) {
		
		// 1) SQL 수행에 필요한 파라미터들 Map으로 묶기
		Map<String, Integer> map = 
				Map.of( "boardCode", boardCode
							 ,"boardNo", boardNo);
		
		// 2) 
		Board board = service.selectDetail(map);
		
		model.addAttribute("board", board);
		
		// 조회된 이미지 목록이 있을 경우
		if(board.getImageList().isEmpty() == false) { // 이미지가 비어 있지 않을 때 
				
			
			// 썸네일 x  -> 0~3 번 인덱스 
			// 썸네일 O  -> 1~4 번 인덱스 
			
		//FOR문 시작 인덱스 지정.
		int start = 0;
			
			//썸네일이 존재하지않을 경우
//			if(board.getThumbnail().get(0).getImgOrder() != 0)
			if(board.getThumbnail() != null) // 썸네일이 있을 경우 1번부터.
				start = 1;
			
				
			model.addAttribute("start", start); // 0 또는 1  
			
			}
		
		return "board/boardDetail";
	}
	
	/** 댓글 목록 조회(비동기)
	 *  
	 *  @param boardNo : 게시글 번호
	 *  @param : foward시 대상에게 데이터를 전달하는 객체
	 *  
	 * @return
	 */
	@GetMapping("commentList")
	public String selectCommentList(
			@RequestParam ("boardNo") int boardNo, //쿼리스트링 전달 받음
			Model model
			
			) {
		
		
		List<Comment> commentList = service.selectCommentList(boardNo);
		
		/* *보통 비동기 통신(AJAX) 방법 
		 * 	 - 요청 -> 응답
		 * 
		 * *forward
		 * - 요청 위임
		 * - 요청에 대한 응답 화면(HTML) 생성을
		 *   템플릿 엔진 jsp,Thymeleaf)이 대신 수행
		 * 
		 * 
		 * -동기식 x ,
		 * 	템플릿 엔진을 이용해서 html코드를 쉽게 생성
		 * 
		 * @ResponsBody
		 * 	- 컨트롤러에서 반환되는 값을
		 * 	응답 본문에 그대로 반환
		 * 		-> 템플릿 엔진 (tyymeleaf)를 이용해서 html 코드를
		 * 					만들어서 반환 x 
		 * 						데이터 있는 그대로를 반환
		 */
		
		//Board 객체 생성
		Board board = Board.builder().commentList(commentList).build();
		
		//"board"라는 key 값으로 생성한 Board 객체를
		// forward 대상인 comment.html로 전달
		model.addAttribute("board", board);
		
		//comment.html에 comment-list 조각 thymeleaf 코드를 해석해서
		// 완전한 hylea 코드로 변환 후 
		// 요청한 곳으로 응답( fetch() API 코드로 html 코드가 반환)
		
		return "board/comment :: comment-list"; // 타임리프 해석해서  js 로 넘겨주고 js 에서 html 로 다시 해석
	}
	
//board/{boardCode}/{boardNo}/goToList?limit=10
	/** 현재 게시글이 포함된 목록의 페이지로 리다이렉트
	 * @param boardCode
	 * @param boardNo
	 * @param paramMap : 요청 파라미터가 모두 담긴 Map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}/goToList")
	public String goToList(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@RequestParam Map<String, Object> paramMap//페이지 리밋 10
			) throws UnsupportedEncodingException {
			
		
		// paramMap에 boardCode, boardNo 추가
		paramMap.put("boardCode", boardCode);
		paramMap.put("boardNo", boardNo);
		
		
		//현재 게시글이 속해있는 페이지 번호 조회하는 서비스
		int cp = service.getCurrentPage(paramMap);
		
		
		
		//목록 조회 리다이렉트
		String url = "redirect:/board/" + boardCode + "?cp=" + cp;
		
		// 검색인 경우 쿼리스트링 추가
				
		if(paramMap.get("key") != null) {
			
			// URLEncoder.encode("문자열", "UTF-8")
			// - UTF-8 형태의 "문자열"을
			//   URL이 인식할 수 있는 형태(application/x-www-from-urlencoded)로 변환
			String query 
				= URLEncoder.encode(paramMap.get("query").toString(), "UTF-8");
			url += "&key=" + paramMap.get("key") 
					+ "&query="+ query;
		}
		
		
		return url;
	}
	
	//@ExceptionHandler(예외 클래스.class)
	// -> 해당 예외 발생 시 아래 작성된 메서드가 수행 되게하는 어노테이션
	
	// -Class 레벨 : 클래스에서 발생하는 예외를 다 잡아서 처리
	//  	-> 동작하려는 Controller클래스에 작성
	// - Global레벨 : 프로젝트 전체에서 발생하는 예외를 잡아서 처리
	// 		-> @ControllerAdvice가 작성된 클래스에 작성
	// 
	
	/** Boardcontroller에서 발생하는 예외를 
	 * 한 번에 잡아서 처리하는 메서드(클래스 레벨)
	 * 
	 * 
	 * @return
	 */
//	@ExceptionHandler(Exception.class)
	public String boardExceptionHandler(
			Exception e,
			Model model
			) {
		model.addAttribute("e", e);
		model.addAttribute("errorMessage", "게시글 관련 오류 발생");
		
		
		
		return "error/500"; 
	}
		
	
	
	
	
	
}
