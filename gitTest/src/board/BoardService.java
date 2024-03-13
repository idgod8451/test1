package board;

import java.util.ArrayList;
import java.util.Scanner;

import board.mem.MemService;

public class BoardService {
	private BoardDao dao;

	public BoardService() {
		dao = new BoardDao();
	}

	public void addBoard(Scanner sc) {// 글작성 메서드
		System.out.println("=== 글작성 ===");
		System.out.println("title : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.println("content : ");
//		sc.nextLine();
		String content = sc.nextLine();
		dao.insert(new Board(0, MemService.loginId, null, content, title));

	}

	public void getByNum(Scanner sc) {
		System.out.println("=== 번호로 검색 ===");
		System.out.println("num : ");
		int num = sc.nextInt();
		Board b = dao.select(num);
		if (b == null) {
			System.out.println("없는 글 번호입니다.");
		} else {
			System.out.println(b);
			if(MemService.loginId.equals(b.getWriter())) { //로그인한 사람의 글이라면
				System.out.println("1.수정 2.삭제 3.상세페이지종료");
				int x = sc.nextInt();
				switch(x) {
				case 1:
					editBoard(sc, num);
					break;
				case 2:
					delBoard(num);
					break;
				}
			}
		}
	}

	public void getByTitle(Scanner sc) {
		System.out.println("=== 제목으로 검색 ===");
		System.out.println("title : ");
		String title = sc.next();
		ArrayList<Board> list = dao.selectByTitle(title);
		if (list.isEmpty()) {
			System.out.println("없는 글 제목입니다.");
		} else {
			for (Board b : list) {
				System.out.println(b);
			}
		}
	}
	
	
	public void getByWriter(Scanner sc) {
		System.out.println("=== 작성자로 검색 ===");
		System.out.println("writer : ");
		String writer = sc.next();
		ArrayList<Board> list = dao.selectByWriter(writer);
		if (list.isEmpty()) {
			System.out.println("검색 결과가 없다.");
		} else {
			for (Board b : list) {
				System.out.println(b);
			}
		}
	}
	
	
	
	public void getAll() {
		System.out.println("=== 글 목록 ===");
		ArrayList<Board> list = dao.selectAll();
		if (list.isEmpty()) {
			System.out.println("검색 결과가 없다.");
		} else {
			for (Board b : list) {
				System.out.println(b);
			}
		}
	}
	
	
	public void editBoard(Scanner sc,int num) {
		System.out.println("=== 수정 ===");
		System.out.println("new title : ");
		sc.nextLine();
		String title = sc.nextLine();
		System.out.println("new content : ");
		String content = sc.nextLine();
		dao.update(new Board(num,"",null,content,title));
	}
	
	
	public void delBoard(int num) {
		System.out.println("=== 삭제 ===");
		dao.delete(num);
		System.out.println("해당 글이 삭제 되었습니다.");
	}

}
