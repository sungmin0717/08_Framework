package edu.kh.project.sse.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import edu.kh.project.member.dto.Member;
import edu.kh.project.sse.dto.Notification;
import edu.kh.project.sse.service.SseService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController //Controller + @responseBody 합친거
@Slf4j

public class SseController {
	
	@Autowired
	private SseService service;
	
	//SseEmitter : 서버로 부터 메시지를 전달 받을
	//							클라이언트 정보를 저장한 객체 == 연결된 클라이언트
	
// ConcurrentHashMap : 멀티스레드 환경에서 동기화를 보장하는 Map
//  > 한번에 많은 요청이 있어도 차례대로 처리
	
	private final Map<String, SseEmitter> emitters
		= new ConcurrentHashMap<>();
	
	@GetMapping("see/connect")
	public SseEmitter sseConnect(
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		//Map에 저장될 Key 값으로 회원 번호 얻어오기
		String clientId = loginMember.getMemberNo() + ""; //String 반환
	
		//SseEmitter 객체 생성
		// -> 연결 대기 시간 10분 설정 (ms 단위)
		SseEmitter emitter = new SseEmitter(10 * 60 * 1000L);
		
		//클라이언트 정보를 Map에 추가
		emitters.put(clientId, emitter);
		
		//클라이언트 연결 종류 시 Map에서 제거
		emitter.onCompletion(() -> emitters.remove(clientId));
		
		//클라이언트 타임 아웃 시 Map에서 제거
		emitter.onTimeout(() -> emitters.remove(clientId));
		

		
		
		return emitter;
	}
	
	@PostMapping("/sse/send")
	//반환할 값이 필요없고 false true가 필여해 void 함
	public void sendNotification(
			@RequestBody Notification notification ,
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		//알림보낸 회원 (현재 로그인한 회원) 번호 추가
		notification.setSendMemberNo(loginMember.getMemberNo());
		
		// 전달 받은 알림 데이터를 DB에 저장하고
		// 알림 받을 회원의 번호
		//  + 해당 회원이 읽지 않은 알림 개수 반환 받는 서비스 호출
		Map<String, Object> map
			= service.insertNotification(notification);
		
		
		//알림을 받을 클라이언트 id == 알림 받을 회원 번호
		String clientId = map.get("receiveMemberNo").toString(); //문지열로 바꿈
		
		//연결된 클라이언트 대기 명당 (imtters)에서 
		// clientId강 일치하는 클라이언트 찾기 
		SseEmitter emitter = emitters.get(clientId);
		
		if(emitter != null) {
			try {
				emitter.send(map);
			} catch (Exception e) {
				emitters.remove(clientId);
			}
		}
		
		
	}
	
	/** 로그인한 회원의 알림 목록 조회
	 * 
	 * @param loginMember
	 * @return
	 */
	@GetMapping("notification")
	public List<Notification> selectNotificationList(
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		int memberNo = loginMember.getMemberNo();
		return service.selectNotificationList(memberNo);
	}
	
	/** 현재 로그인한 회원의 알림중 
	 * 읽지 않은 알림 개수 조회
	 * ("NOTIFICATION".NOTIFICATION_CHECK = 'N')
	 * 
	 * @param param
	 * @return
	 */
	@GetMapping("notification/notReadCheck")
	public int notReadCheck(
			@SessionAttribute("loginMember") Member loginMember
			) {
		
		int memberNo = loginMember.getMemberNo();
		
		int result = service.notReadCheck(memberNo);
		
		System.out.println(result);
		System.out.println(result);
		System.out.println(result);
		System.out.println(result);
		System.out.println(result);
		
		return result;
	}
	
	/**알림 삭제*/
	@DeleteMapping("/notification")
	public void deleteNotification(
			@RequestBody int notificationNo
			) {
		
		service.deleteNotification(notificationNo);
		
		
	}
	
	/** 알림 읽음 여부 변경( n -> y)
	 * 
	 * @param notificationCheck
	 * @return
	 */
	@PutMapping("notification")
	public String updateNotification(
			@RequestBody int notificationCheck
			) {
		
		service.updateNotification(notificationCheck);
		return null;
		
	}
	
	
	
}
