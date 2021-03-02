package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class BoardDaoImpl implements IBoardDao{

	private static BoardDaoImpl dao = null;
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		
		smc = BuildedSqlMapClient.getSqlMapClient();
		
	}
	
	public static BoardDaoImpl getInstance() { 
		if(dao==null) {
			dao = new BoardDaoImpl();
		}
		return dao;  
	}
	
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		
		int cnt = 0;
		try {
			Object obj = smc.insert("jdbcboard.insertBoard", boardVo);
			if(obj==null) {
				cnt=1;
			}
			
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardId) {
		
		int cnt = 0;
		try {
			cnt = smc.delete("jdbcboard.deleteBoard", boardId);
			
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		
		int cnt = 0;
		try {
			cnt = smc.update("jdbcboard.updateBoard",boardVo);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		
		
		List<BoardVO> boardList = null;
		try {


			boardList = smc.queryForList("jdbcboard.getAllBoard");
			

		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public int getBoardCount(int boardId) {
		
		int cnt = 0;
		try {
			cnt = (int)smc.queryForObject("jdbcboard.getBoardCount",boardId);
			
			
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<BoardVO> getSelectBoardList(String title) {
	
		
		List<BoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("jdbcboard.getSearchBoard",title);
			
			
			
			
			
		} catch (Exception e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
		
	}

	@Override
	public BoardVO getBoardList(int boardId) {
		
		BoardVO boardUser = null;
		try {
			
			smc.update("jdbcboard.setCountIncrement",boardId);
		
			boardUser = (BoardVO)smc.queryForObject("jdbcboard.getBoard",boardId);
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return boardUser;
	}
	
}
