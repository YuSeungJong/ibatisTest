package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao;	// DAO객체가 저장될 변수 선언
	
	private static MemberServiceImpl service = null;
	
	// 생성자
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service==null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemberCount(memId);
	}

	@Override
	public int selectUpdateName(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.selectUpdateName(memVo);
	}

	@Override
	public int selectUpdateTel(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.selectUpdateTel(memVo);
	}

	@Override
	public int selectUpdateAddr(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.selectUpdateAddr(memVo);
	}

}
