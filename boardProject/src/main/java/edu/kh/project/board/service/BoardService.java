package edu.kh.project.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kh.project.board.dto.Board;
import lombok.RequiredArgsConstructor;

public interface BoardService {

	/** 게시글 목록 조회.
	 * 
	 * @param boardCode
	 * @param cp
	 * @return Map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	Board selectDetail(Map<String, Integer> map);

	
	
	/** 조회수 1 증가
	 * 
	 * @param boardNo
	 * @return result
	 */
	int updateReadCount(int boardNo);

	
	/** 게시글 좋아요
	 * 
	 * @param boardNo
	 * @param memberNo
	 * @return
	 */
	Map<String, Object> boardNo(int boardNo, int memberNo);

	/** DB에서 모든 게시판 종류를 조회
	 * 
	 * @return
	 */
	List<Map<String, String>> selectBoardTypeList();
	
	

}
