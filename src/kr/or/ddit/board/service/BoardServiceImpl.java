package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		
		return service;
	}
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardId) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardId);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return dao.getAllBoardList();
	}

	@Override
	public int getBoardCount(int boardId) {
		// TODO Auto-generated method stub
		return dao.getBoardCount(boardId);
	}

	@Override
	public List<BoardVO> getSelectBoardList(String title) {
		// TODO Auto-generated method stub
		return dao.getSelectBoardList(title);
	}

	@Override
	public BoardVO getBoardList(int boardId) {
		// TODO Auto-generated method stub
		return dao.getBoardList(boardId);
	}
	
}
