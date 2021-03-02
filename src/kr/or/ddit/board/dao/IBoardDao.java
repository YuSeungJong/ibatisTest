package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public int insertBoard(BoardVO boardVo);
	
	
	public int deleteBoard(int boardId);
	
	
	public int updateBoard(BoardVO boardVo);
	
	
	public List<BoardVO> getAllBoardList();
	
	
	public int getBoardCount(int boardId);
	
	public List<BoardVO> getSelectBoardList(String title);
	
	public BoardVO getBoardList(int boardId);
}
