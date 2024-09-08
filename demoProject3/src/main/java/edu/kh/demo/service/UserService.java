package edu.kh.demo.service;

import java.util.List;

import edu.kh.demo.dto.UserDto;

public interface UserService {

	/** 사용자 이름 조회
	 * 
	 * @param userNo
	 * @return userName
	 */
	String selectUserName(int userNo);

	
	/** 여러 회원 조회.
	 * 
	 * @return userList 반환
	 */
	List<UserDto> selectAll();


	UserDto selectUser(int userNo);


	/** 사용자 정부 수정
	 * 
	 * @param user
	 * @return result
	 */
	int updateUser(UserDto user);

  /** 사용자 삭제
   * 
   * @param userNo
   * @return
   */
	int deleteUser(int userNo);


	/** 유저 추가
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(UserDto user);

}
