package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.BuildedSqlMapClient;


public class MemberDaoImpl implements IMemberDao {
	private SqlMapClient smc; // iBatis용 sqlMapClient객체 변수 선언

	
	// 1번
	private static MemberDaoImpl dao = null;
	
	// 2번
	// DAO의 생성자에서 iBatis환경 설정돠 sqlMapClient객체를 생성한다.
	private MemberDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static MemberDaoImpl getInstance() { 
		if(dao==null) {
			dao = new MemberDaoImpl();
		}
		return dao;  
	}
	
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		
		int cnt = 0; // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj==null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
		
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		try {
			cnt = smc.delete("mymember.deleteMember", memId);
			
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = null; 
		try {
			memList = smc.queryForList("mymember.getAllMemeber");
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} 
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int cnt = 0;
		try {
			cnt = (int)smc.queryForObject("mymember.countMember", memId);
			
			
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int selectUpdateName(MemberVO memVo) {
	
		int cnt = 0;
		try {
			cnt = (int)smc.update("mymember.updateMemberName", memVo);

			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int selectUpdateTel(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			cnt = (int)smc.update("mymember.updateMemberTel", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int selectUpdateAddr(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			cnt = (int)smc.update("mymember.updateMemberAddr", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}
	
}
