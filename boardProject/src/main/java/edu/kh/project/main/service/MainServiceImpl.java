package edu.kh.project.main.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.main.mapper.MainMapper;
import edu.kh.project.member.dto.Member;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor // 생성자 생성-> 생성자 방식 의존성 주입 수행
public class MainServiceImpl implements MainService{
	
	private final MainMapper mapper;
	
	
	// 전체 회원 조회
	@Override
	public List<Member> selectMemberList() {
		return mapper.selectMemberList();
	}
	
	// 빠른 로그인
	@Override
	public Member directLogin(int memberNo) {
		
		return mapper.directLogin(memberNo);
	}
	
	
	// 비밀번호 초기화
	@Override
	public int resetPw(int memberNo) {
		
		
		
		return mapper.resetPw(memberNo);
	}
	
	// 탈퇴 상태 변경
	@Override
	public int changeStatus(int memberNo) {
		
		
		return mapper.changeStatus(memberNo);
	}
}





