package kr.or.ddit.member.main;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;


import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.AES256Util;



/*
회원을 관리하는 프로그램 작성하기
(DB시스템의 MYMEMBER테이블 이용)

- 처리조건
1. 아래의 메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
2. '자료추가' 에서는 입력한 회원 ID가 중복되는지 여부를 검사해서 중복되면 다시 입력 받도록 한다.
3. '자료 삭제'는 회원 ID를 입력받아 삭제한다.
4. '자료 수정'은 회원 ID를 제외한 전체 자료를 수정한다.
5. '자료 수정2' 메뉴를 선택하면
	1. 회원이름 수정	2. 회원전화번호 수정		3. 회원주소 수정 	4. 취소 메뉴를 출력하고
	각 부 메뉴에 해당하는 데이터를 수정한다.
6. 회원ID를 추가할때는 암호화해서 추가하고, 화면에 보여줄 경우에는 복호화해서 보여준다.	

메뉴예시)
	-- 작업 선택--
1. 자료추가	 					==> insert (C)
2. 자료삭제						==> delete (D)
3. 자료수정						==> update (U)
4. 전체 자료 출력					==> select (R)
5. 자료수정2
0. 작업 끝.
----------------
작업 번호 >>
*/
public class MemberController{
	
	private Scanner sc = new Scanner(System.in);
	private IMemberService service;		// Service객체가 저장될 변수 선언
	
	
	//생성자
	public MemberController() {
		service = MemberServiceImpl.getInstance();			
	}
	
	public static void main(String[] args) throws Exception {
		new MemberController().start();
	}
	
	private void start() throws Exception {
		while (true) {
			System.out.println("----- 작업 선택 -----");
			System.out.println("1. 자료추가");
			System.out.println("2. 자료삭제");
			System.out.println("3. 자료수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("5. 자료수정2");
			System.out.println("0. 작업 끝.");
			System.out.println("------------------");
			System.out.print("작업 번호 >> ");
			int input = Integer.parseInt(sc.nextLine());
			
			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				display();
				break;
			case 5:
				selectUpdate();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;

			default:
				System.out.println("메뉴에 있는 번호만 선택하세요");
				break;
			}
			
		}
		
	}
	
	private void selectUpdate() {

		System.out.println("수정할 아이디를 입력하세요");
		System.out.print("입력 > ");
		String memId = sc.nextLine();
		int count = service.getMemberCount(memId);
		
		if (count == 0) {
			System.out.println("없는 사용자 ID입니다");
			return;
		} 
		
		System.out.println("1. 회원이름 수정\t2. 회원전화번호 수정\t3. 회원주소 수정\t4. 취소 ");
		
		int input = Integer.parseInt(sc.nextLine());
		
		switch (input) {
		case 1:
			selectUpdateName(memId);
			break;
		case 2:
			selectUpdateTel(memId);
			break;
		case 3:
			selectUpdateAddr(memId);
			break;	

		default:
			break;
		}
		
	}

	private void selectUpdateAddr(String memId) {
		System.out.println("수정할 주소을 입력하세요");
		System.out.print("입력 > ");
		
		String memAddr = sc.nextLine();
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_addr(memAddr);
		int cnt = service.selectUpdateAddr(memVo);
		
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 주소 수정 완료!");
		}else {
			System.out.println("회원 주소 수정 실패!");
		}
		System.out.println();
		
	}

	private void selectUpdateTel(String memId) {

		System.out.println("수정할 번호을 입력하세요");
		System.out.print("입력 > ");
		
		String memtel = sc.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_tel(memtel);
		
		int cnt = service.selectUpdateTel(memVo);
		
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 번호 수정 완료!");
		}else {
			System.out.println("회원 번호 수정 실패!");
		}
		System.out.println();
		
	}

	private void selectUpdateName(String memId) {

		System.out.println("수정할 이름을 입력하세요");
		System.out.print("입력 > ");
		
		String memName = sc.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		
		
		int cnt = service.selectUpdateName(memVo);
		
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 이름 수정 완료!");
		}else {
			System.out.println("회원 이름 수정 실패!");
		}
		System.out.println();
		
	}

	private void display() throws Exception {
		AES256Util aes256 = new AES256Util();
			List<MemberVO> memList = service.getAllMemberList();
			
			System.out.println("-----------------------------------");
			System.out.println("회원ID\t회원이름\t전화번호\t\t주소");
			System.out.println("-----------------------------------");
			if(memList == null || memList.size()==0) {
				System.out.println("회원 정보가 하나도 없습니다.");
			}else {
				
				for(MemberVO memVo : memList) {
					System.out.print(aes256.decrypt(memVo.getMem_id())+"\t");
					System.out.print(memVo.getMem_name()+"\t");
					System.out.print(memVo.getMem_tel()+"\t");
					System.out.println(memVo.getMem_addr());
				}
				System.out.println("-----------------------------------");
				
				System.out.println("아무키나 누르면 돌아갑니다");
				
				String a = sc.nextLine();
			}
	}

	private void update() throws Exception {
		AES256Util aes256 = new AES256Util();
			System.out.print("수정할 아이디를 입력하세요 : ");
			String memId = sc.nextLine();
		
			int count = service.getMemberCount(aes256.encrypt(memId));
			
			if (count == 0) {
				System.out.println("없는 사용자 ID입니다");
				return;
			} 
			
			System.out.print("수정할 이름을 입력하세요 : ");
			String memName = sc.nextLine();
			
			System.out.print("수정할 전화번호 입력하세요 : ");
			String memTel = sc.nextLine();
			
			System.out.print("수정할 주소를 입력하세요 : ");
			String memAddr = sc.nextLine();
		
			// 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
			MemberVO memVo = new MemberVO();
			
			memVo.setMem_id(aes256.encrypt(memId));
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			
			// Service 객체의 데이터를 수정하는 메서드 호출하기
			int cnt = service.updateMember(memVo);
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 정보 수정 완료!");
			}else {
				System.out.println("회원 정보 수정 실패!");
			}
			System.out.println();
		
	}

	private void delete() throws Exception {
		AES256Util aes256 = new AES256Util();
			System.out.print("삭제할 아이디를 입력하세요 : ");
			String memId = sc.nextLine();
			
			int count = service.getMemberCount(aes256.encrypt(memId));
			
			
			if (count == 0) {
				System.out.println("없는 사용자 ID입니다");
				return;
			} 
			
			int cnt = service.deleteMember(aes256.encrypt(memId));
			
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 삭제 완료!");
			}else {
				System.out.println("회원 삭제 실패!");
			}
			System.out.println();
			
		
		
	}

	private void insert() throws Exception {
		AES256Util aes256 = new AES256Util();
		
			String memId = "";
			while (true) {
				System.out.print("아이디를 입력하세요 : ");
				memId = sc.nextLine();

				if (memId.equals("")) {
					System.out.println("빈 내용은 입력할수 없습니다.");
					System.out.println("다시 입력하세요");
					continue;
				}
				
				int count = service.getMemberCount(aes256.encrypt(memId));
				
				if (count == 1) {
					System.out.println("이미 등록된 이름입니다.");
					System.out.println("다시 입력하세요");
				} else {
					break;
				}

			}
			
			System.out.print("이름을 입력하세요 : ");
			String memName = sc.nextLine();
			
			System.out.print("전화번호 입력하세요 : ");
			String memTel = sc.nextLine();
			
			System.out.print("주소를 입력하세요 : ");
			String memAddr = sc.nextLine();
			
			//입력한 회원 정보를 저장할 MemberVO객체 생성
			MemberVO memVo = new MemberVO();
			
			// 입력한 데이터를 MemberVo 객체에 저장한다.
			memVo.setMem_id(aes256.encrypt(memId));
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			// Service객체에서 회원 정보를 추가하는 메서드 호출하기
			int cnt = service.insertMember(memVo);
			
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 가입 성공!");
			}else {
				System.out.println("회원 가입 실패!");
			}
			System.out.println();
			
		
		}
	
}
