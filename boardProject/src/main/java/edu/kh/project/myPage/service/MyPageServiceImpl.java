package edu.kh.project.myPage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.dto.Member;
import edu.kh.project.myPage.mapper.MyPageMapper;

@Transactional // 서비스 내 메서드 수행 중 
							 // UnChckException 발생 시 rollback 수행
// 								아니면매서드 종료 시 
@Service
public class MyPageServiceImpl implements MyPageService{

	
	
	@Autowired // 의존성 주입 (DI)
	private  MyPageMapper mapper;
	
	
	
	@Override
	public int updateInfo(Member inputMember) {
		
		// 만약주소가 입력되지 않은 경우 null로 변경
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null);
			// UPDATE 구문 수행 시 EMBER_ADDRESS 컬럼이 null이됨
		}
		
		
		//SQL 수행 후 결과 반환 받기
		return mapper.updateInfo(inputMember);
	}
	
	@Override
	public int checkNickname(String input) {
		
		return mapper.checkNickname(input);
	}
}
