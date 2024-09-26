package edu.kh.project.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.dto.Member;

@Mapper // 인터페이스 상속 클래스 생성 + 클래스를 Bean 등록
public interface MainMapper {

	/** 전체 회원 조회
	 * @return list
	 */
	List<Member> selectMemberList();

	
	
	
	/** 빠른로그인
	 * 
	 * @param memberNo
	 * @return
	 */
	Member directLogin(int memberNo);



/** 비밀번호 초기화
 * 
 * @param memberNo
 * @return
 */
	int resetPw(int memberNo);




int changeStatus(int memberNo);

}
