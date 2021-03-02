package kr.or.ddit.board.main;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;





public class BoardController {

	private Scanner sc = new Scanner(System.in);
	private IBoardService service;		
	int check = 0;
	//생성자
	public BoardController() {
		service = BoardServiceImpl.getInstance();			
	}
	
	public static void main(String[] args) {
		
		new BoardController().start();

	}

	private void start() {
	
		while(true) {
		if(check==0) {
			boardDisplayAll();
		}
		
		System.out.println("매뉴 : 1. 새글작성   2. 게시글보기   3. 검색   0. 작업끝");
		System.out.print("작업선택 >> ");
		
		int input = Integer.parseInt(sc.nextLine());
		
		switch (input) {
		case 1:
			boardInsert();
			break;
		case 2:
			boardDisplay();
			break;
		case 3:
			boardView();
			break;
		case 0:
			System.out.println("시스템을 종료합니다.");
			System.exit(0);
			break;

		default:
			System.out.println("해당 메뉴만 선택하세요");
			break;
		}
		
		}
		
		
	}

	
	
	
	private void boardView() {
		
		
			
			System.out.print("검색할 제목을 입력하세요 : ");
			String title = sc.nextLine();
			
			if(title.equals("")) {
				check = 0;
				return;
			}
			
			List<BoardVO> boardList = service.getSelectBoardList(title);
			
			System.out.println("----------------------------------------");
			System.out.println("NO\t제 목\t\t작성자\t조회수");
			System.out.println("----------------------------------------");
			if(boardList==null || boardList.size() ==0) {
				System.out.println("검색한 게시글 정보가 없습니다.");
			}else {
				for(BoardVO board : boardList) {
					System.out.print(board.getBoard_no()+"\t");
					System.out.print(board.getBoard_title()+"\t\t");
					System.out.print(board.getBoard_writer()+"\t");
					System.out.println(board.getBoard_cnt());
				}
			}
			System.out.println("----------------------------------------");
			check = 1;
			
	}

	private void boardDisplay() {
		
	
			
			System.out.print("열람을 원하는 게시물 번호를 입력하세요 : ");
			int	num = Integer.parseInt(sc.nextLine());
			
			int cnt = service.getBoardCount(num);
			
		if (cnt == 0) {
			System.out.println("없는 게시글 번호입니다");
			check = 0;
			return;
		}

			BoardVO board = service.getBoardList(num);
			
			if(board!=null) {
			System.out.println(board.getBoard_no()+"번글 내용");
			System.out.println("---------------------------------");
			System.out.println("- 제 목 : "+board.getBoard_title());
			System.out.println("- 작성자 : "+board.getBoard_writer());
			System.out.println("- 내 용 : "+board.getBoard_content());
			System.out.println("- 작성일 : "+board.getBoard_date());
			System.out.println("- 조회수 : "+board.getBoard_cnt());
			System.out.println("---------------------------------");
			}else {
				System.out.println("게시글 불러오기 실패");
				return;
			}
		
		
		System.out.println("메뉴 : 1. 수정   2. 삭제   3. 리스트로 가기");
		System.out.print("작업 선택 >> ");
		
		int input = Integer.parseInt(sc.nextLine());
		
		switch (input) {
		case 1:
			boardUpdate(num);
			break;
		case 2:
			boardDelete(num);
			break;
		case 3:
			check = 0;
			break;

		default:
			check = 0;
			break;
		}
		
	}

	
	private void boardDelete(int num) {
		
			
			int cnt = service.deleteBoard(num);
			
			System.out.println();
			if(cnt>0) {
				
				System.out.println("게시글이 삭제 되었습니다.");
			}else {
				System.out.println("게시글 삭제에 실패했습니다.");
			}
			System.out.println();
			
			check = 0;
			
		
	}

	private void boardUpdate(int num) {
		
			
			
			System.out.print("- 제 목 : ");
			String title = sc.nextLine();
			
			System.out.print("- 내 용 : ");
			String content = sc.nextLine();
			
			BoardVO board = new BoardVO();
			board.setBoard_no(num);
			board.setBoard_title(title);
			board.setBoard_content(content);
			
			
			int cnt = service.updateBoard(board);
			System.out.println();
			if(cnt>0) {
				
				System.out.println("게시글이 수정되었습니다.");
			}else {
				System.out.println("게시글 수정에 실패했습니다.");
			}
			System.out.println();
			
			check = 0;
		
	}

	private void boardInsert() {
		System.out.print("- 제 목 : ");
		String title = sc.nextLine();
		
		System.out.print("- 작성자 : ");
		String name = sc.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = sc.nextLine();
		
		
		BoardVO board = new BoardVO();
		
		board.setBoard_title(title);
		board.setBoard_writer(name);
		board.setBoard_content(content);
			
			int cnt = service.insertBoard(board);
			System.out.println();
			if(cnt>0) {
				System.out.println("게시글이 작성되었습니다.");
			}else {
				System.out.println("게시글 작성 실패!");
			}
			System.out.println();
			
			
			check = 0;
		
		
		
	}

	private void boardDisplayAll() {
		List<BoardVO> boardList = null;
		
			boardList = service.getAllBoardList();
			System.out.println("----------------------------------------");
			System.out.println("NO\t제 목\t\t작성자\t조회수");
			System.out.println("----------------------------------------");
			for(BoardVO board : boardList) {
				System.out.print(board.getBoard_no() + "\t");
				System.out.print(board.getBoard_title() + "\t\t");
				System.out.print(board.getBoard_writer() + "\t");
				System.out.println(board.getBoard_cnt());
			}
			System.out.println("----------------------------------------");

			check = 0;
	}
}
