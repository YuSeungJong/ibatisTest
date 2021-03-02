package kr.or.ddit.hotel.dao;

import java.util.List;

import kr.or.ddit.hotel.vo.HotelVO;

public interface IHotelDao {
	public int updateCheckIn(HotelVO hotelVo);
	
	public int updateCheckOut(int roomNo);
	
	public List<HotelVO> getAllHotelList();
	
	public int checkRoom(int roomNo);
	
	public int checkRoomUser(int roomNo);
	
	public int checkUserName(String name);
}
