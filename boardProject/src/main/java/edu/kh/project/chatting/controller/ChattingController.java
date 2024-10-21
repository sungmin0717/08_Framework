package edu.kh.project.chatting.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import edu.kh.project.chatting.dto.ChattingRoom;
import edu.kh.project.chatting.dto.Message;
import edu.kh.project.chatting.service.ChattingService;
import edu.kh.project.member.dto.Member;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("chatting") // 채팅으로 시작하는 요청을 처리하겠다
@RequiredArgsConstructor
public class ChattingController {
	
	private final ChattingService service;
	
	// /chatting //공통주소를 뺀 나머지 주소를 써야하는데
	// chatting은 처리한 주소니 그냥 빈칸을 쓰면 된다.
	
	/** 채팅페이지 전환
	 * 
	 * @return
	 */
	@GetMapping("")
	public String getMethodName(
			@SessionAttribute("loginMember") Member loginMember,
			Model model
			
			) {
		
		List<ChattingRoom> roomList
			= service.selectRoomList(loginMember.getMemberNo());
		
		model.addAttribute("roomList", roomList);
		
		return "chatting/chatting";
	}
	
	/** 채팅 상대 검색 
	 * 
	 * 
	 * @param query : 상대 닉네임 또는 이메일
	 * @param loginMember : 로그인한 회원 정보
	 * @return
	 */
	@GetMapping("selectTarget") //요청 주소 쿼리스트링 안들어감
	@ResponseBody
	public List<Member> selectTarget( // list 반환
			@RequestParam("query") String query,
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		
		return service.selectTarget(query, loginMember.getMemberNo());
	}
	
	
	
	/** 채팅방 입장(처음 채팅이면 채팅방 생성 (insert))
	 * 
	 * @param targetNo 회원 번호
	 * @param loginMember
	 * @return 두 회원이 포함된 채팅방 번호
	 */
	@ResponseBody
	@PostMapping("enter")
	public int chattingEnter(
			@RequestBody int targetNo,
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		int chattingNo = service.chattingEnter
				(targetNo, loginMember.getMemberNo());
		
		return chattingNo;
	}
	
	
	
	/** 로그인한 회원이 참여한 채팅방 목록 조회
	 * 
	 * @param loginMember
	 * @return
	 */
	@GetMapping("roomList")
	@ResponseBody
	public List<ChattingRoom> selectRoomList(
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		
		
		return service.selectRoomList(loginMember.getMemberNo());
	}
	
	/** 특정 채팅방의 메시지 모두 조회
	 * 
	 * @param chattingNo
	 * @param loginMember
	 * @return
	 */
	@GetMapping("selectMessage")
	@ResponseBody
	public List<Message> selectMessage(
			@RequestParam("chattingNo") int chattingNo,
			@SessionAttribute("loginMember") Member loginMember
) {
		
		return service.selectMessage(chattingNo, loginMember.getMemberNo());
	}
	
	
	
	
	/** 패팅 읽음 표시
	 * 
	 * @param chattingNo
	 * @param loginMember
	 * @return
	 */
	@PutMapping("updateReadFlag")
	@ResponseBody
	//json 형태로 받는다면 map으로 만들어야한다.
	public int updateReadFlag(
			@RequestBody int chattingNo,
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		return service.updateReadFlag(chattingNo, loginMember.getMemberNo());
	}
	
	

}
