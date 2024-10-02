package edu.kh.project.board.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

public interface BoardService {

	/** 게시글 목록 조회.
	 * 
	 * @param boardCode
	 * @param cp
	 * @return Map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);
	
	

}
