package kr.or.ddit.hotel.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.hotel.vo.HotelVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class HotelDaoImpl implements IHotelDao {

	private static HotelDaoImpl dao;
	
	private SqlMapClient smc;
	
	private HotelDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static HotelDaoImpl getInstance() { 
		if(dao==null) {
			dao = new HotelDaoImpl();
		}
		return dao;  
	}
	
	@Override
	public int updateCheckIn(HotelVO hotelVo) {
		

		int cnt = 0;
		try {
			cnt = smc.update("hotel.updateCheckIn",hotelVo);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	
	}

	@Override
	public int updateCheckOut(int roomNo) {
		
		int cnt = 0;
		try {
			cnt = smc.update("hotel.updateCheckOut",roomNo);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<HotelVO> getAllHotelList() {
		
		List<HotelVO> hotelList;
		
		try {
			hotelList = smc.queryForList("hotel.getAllHotelList");
		
		} catch (Exception e) {
			hotelList = null;
			e.printStackTrace();
		}
		
		return hotelList;
		
	}

	@Override
	public int checkRoom(int roomNo) {
		
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("hotel.checkRoom",roomNo);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int checkRoomUser(int roomNo) {
		
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("hotel.checkRoomUser",roomNo);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int checkUserName(String name) {
int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("hotel.checkUserName",name);
		
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}
