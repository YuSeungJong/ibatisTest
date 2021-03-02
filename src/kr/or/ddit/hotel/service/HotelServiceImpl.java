package kr.or.ddit.hotel.service;

import java.util.List;


import kr.or.ddit.hotel.dao.HotelDaoImpl;
import kr.or.ddit.hotel.dao.IHotelDao;
import kr.or.ddit.hotel.vo.HotelVO;

public class HotelServiceImpl implements IHotelService {

	private IHotelDao dao;
	
	private static HotelServiceImpl service;
	
	private HotelServiceImpl() {
		dao = HotelDaoImpl.getInstance();
	}
	
	public static HotelServiceImpl getInstance() {
		if(service == null) {
			service = new HotelServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public int updateCheckIn(HotelVO hotelVo) {
		return dao.updateCheckIn(hotelVo);
	}

	@Override
	public int updateCheckOut(int roomNo) {
		return dao.updateCheckOut(roomNo);
	}

	@Override
	public List<HotelVO> getAllHotelList() {
		return dao.getAllHotelList();
	}

	@Override
	public int checkRoom(int roomNo) {
		return dao.checkRoom(roomNo);
	}

	@Override
	public int checkRoomUser(int roomNo) {
		return dao.checkRoomUser(roomNo);
	}

	@Override
	public int checkUserName(String name) {
		return dao.checkUserName(name);
	}
	
}
