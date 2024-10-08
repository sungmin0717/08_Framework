package edu.kh.project.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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


/** 조회 수 1 증가
 * 
 * @param boardNo
 * @return reuslt
 */
	int updateReadCount(int boardNo);


/** 좋아요 누른적 있어? 검시
 * 
 * @param boardNo
 * @param memberNo
 * @return result
 */
int checkBoardLike(@Param("boardNo") int boardNo, 
										@Param("memberNo")int memberNo);




/** 좋아요 테이블에 삽입
 * 
 * @param boardNo
 * @param memberNo
 * @return
 */
int insertBoardLike(@Param("boardNo")  int boardNo, 
		  @Param("memberNo") int memberNo);

/** 좋아요 테이블에서 삭제
 * 
 * @param boardNo
 * @param memberNo
 * @return
 */
int deleteBoardLike(@Param("boardNo")int boardNo, 
										@Param("memberNo")int memberNo);



/** 좋아요 개수 조회
 * 
 * @param boardNo
 * @return count
 */
int getLikeCount(int boardNo);


/** DB에서 모든 게시판 조회
 * 
 * @return
 */
List<Map<String, String>> selectBoardTypeList();

}
