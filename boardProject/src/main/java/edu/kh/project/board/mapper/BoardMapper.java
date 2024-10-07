package edu.kh.project.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.dto.Board;

@Mapper
public interface BoardMapper {

	
	/** 게시글 전체 개수 조회
	 *  조건 : 삭제 되지 않은 글만 카운트
	 * 
	 * @param boardCode
	 * @return
	 */
	int getListCount(int boardCode);

	
	
	/** 지정된 페이지 분량의 게시글 목록 조회
	 * 
	 * @param boardCode
	 * @param rowBound
	 * @return boardList
	 */ 
	List<Board> selectBoardList(int boardCode, RowBounds rowBound);



	Board selectDetail(Map<String, Integer> map);

}
