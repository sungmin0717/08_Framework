package edu.kh.project.member.service;

import edu.kh.project.member.dto.Member;

public interface MemberService {

	
	/** 로그인 서비스
	 * 
	 * @param memberEmail
	 * @param memberPw
	 * @return loginMember 또는 null (email,pw 불일치)
	 */
	Member login(String memberEmail, String memberPw);

	
	
	
	/** 회원 가입
	 * 
	 * @param inputMember
	 * @return
	 */
	int signUp(Member inputMember);



/** 이메일 중복 검사
 * 
 * @param email
 * @return count
 */
	int emailCheck(String email);



/** 닉네임 중복 검사
 * 
 * @param nickName
 * @return
 */
int nicknameCheck(String nickname);

}
