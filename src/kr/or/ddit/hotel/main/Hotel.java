package kr.or.ddit.hotel.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.hotel.service.HotelServiceImpl;
import kr.or.ddit.hotel.service.IHotelService;
import kr.or.ddit.hotel.vo.HotelVO;

public class Hotel {
	private IHotelService service;	
	Scanner sc = new Scanner(System.in);
	
	public Hotel() {
		service = HotelServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Hotel().start();
	}

	private void start() {
		System.out.println("***********************************");
		System.out.println("\t호텔문을 열었습니다. 오서오십시오.");
		System.out.println("***********************************");
		while (true) {
		System.out.println("---------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인    2.체크아웃    3.객실상태    4업무종료");
		System.out.println("---------------------------------------");
		System.out.print("선택>> ");
		int input = Integer.parseInt(sc.nextLine());
		switch (input) {
		case 1:
			checkInRoom();
			break;
		case 2:
			checkOutRoom();
			break;
		case 3:
			displayRoom();
			break;
		case 4:
			System.out.println("***********************************");
			System.out.println("\t호텔문을 닫았습니다.");
			System.out.println("***********************************");
			System.exit(0);
		default:
			System.out.println("없는 메뉴 입니다.");
			break;
		}
		
		}
		
	}

	private void checkOutRoom() {
		System.out.println("-----------------------------");
		System.out.println(" 체크아웃 작업");
		System.out.println("-----------------------------");
		System.out.print("체크아웃 할 방 번호를 입력하세요 : ");
		
		int roomNo = Integer.parseInt(sc.nextLine());

		int cnt = service.checkRoom(roomNo);
		
		if(cnt==0) {
			System.out.println(roomNo + "번 방은 없습니다.");
			return;
		}
		int cnt2 = service.checkRoomUser(roomNo);
		if(cnt2>0) {
			System.out.println("해당 객실은 비어있습니다.");
			return;
		}
		
		int cnt3 = service.updateCheckOut(roomNo);
		
		if(cnt3>0) {
			System.out.println("체크아웃 하였습니다.");
		}else {
			System.out.println("체크아웃에 실패했습니다.");
		}
		
		
	}

	private void checkInRoom() {
		System.out.println("-----------------------------");
		System.out.println(" 체크인 작업");
		System.out.println("-----------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("-----------------------------");
		System.out.print("방 번호 입력 >> ");
		int roomNo = Integer.parseInt(sc.nextLine());
		
		int cnt = service.checkRoom(roomNo);
		
		if(cnt==0) {
			System.out.println(roomNo + "번 방은 없습니다.");
			return;
		}
		int cnt2 = service.checkRoomUser(roomNo);
		if(cnt2==0) {
			System.out.println("해당 객실은 이용중입니다.");
			return;
		}
		
		System.out.print("이용하실 고객 성명을 입력해주세요 : ");
		String name = sc.nextLine();
		
		int cnt4 = service.checkUserName(name);
		
		if(cnt4>0) {
			System.out.println(name+" 이라는 이름은 이미 사용중 입니다.");
			return;
		}
		
		HotelVO hotelVo = new HotelVO();
		hotelVo.setRoom_no(roomNo);
		hotelVo.setRoom_user(name);
		
		
		int cnt3 = service.updateCheckIn(hotelVo);
		
		if(cnt3>0) {
			System.out.println("체크인에 성공했습니다.");
		}else {
			System.out.println("체크인에 실패했습니다.");
		}
	}

	private void displayRoom() {
		System.out.println("------------------------------");
		System.out.println("\t현재 객실 상태");
		System.out.println("------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("------------------------------");
		
		List<HotelVO> hotelList = service.getAllHotelList();
		
		for(int i = 0; i<hotelList.size(); i++) {
			System.out.println(hotelList.get(i).getRoom_no() + "\t"
					+ hotelList.get(i).getRoom_name() + "\t"
					+ hotelList.get(i).getRoom_user());
		}
		
		System.out.println("------------------------------");
	
	}

}

