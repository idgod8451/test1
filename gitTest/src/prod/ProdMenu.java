package prod;

import java.util.Scanner;

import board.mem.MemService;

public class ProdMenu {
	private MemService mservice;
	private ProdService pservice;

	
	public ProdMenu() {
		mservice = new MemService();
		pservice = new ProdService();
	}
	
	// 상위 메뉴
		public void run(Scanner sc) {
			while (true) {
				System.out.println("종료는 0");
				int m = sc.nextInt();
				if (m == 0) {
					break;
				}
				if (MemService.loginId == null) {
					runMemLogout(sc);
				} else {
					runMemLogin(sc);
				}
			}
		}

		// 로그인 안했을때 메뉴
		public void runMemLogout(Scanner sc) {
			boolean flag = true;
			while(flag) {
				System.out.println("1.로그인 2.회원가입 3.종료");
				int m = sc.nextInt();
				switch(m) {
				case 1:
					flag = !mservice.login(sc);
					break;
				case 2:
					mservice.addMem(sc);
					break;
				case 3:
					flag = false;
					break;
				}
			}
		}

		// 로그인 했을때 메뉴
		public void runMemLogin(Scanner sc) {
			boolean flag = true;
			while(flag) {
				System.out.println("1.내정보확인 2.내정보수정 3.로그아웃 4.탈퇴 5.상품메뉴 6.종료");
				int m = sc.nextInt();
				switch(m) {
				case 1:
					mservice.printMem(sc);
					break;
				case 2:
					mservice.editMem(sc);
					break;
				case 3:
					mservice.logout();
					return;
				case 4:
					mservice.delMem();
					return;
				case 5:
					runProd(sc);
					break;
				case 6:
					flag = false;
					break;
				}
			}
		}

		// 게시판 메뉴  1.글작성(제목, 내용만 입력받음) 2.번호로검색 3.제목(like)으로검색 4.작성자로검색 5.전체목록 
		//6.수정(글번호로 찾아서 제목/내용 수정) 7.글번호로삭제 8.종료
		public void runProd(Scanner sc) {
			boolean flag = true;
			while(flag) {
				System.out.println("1.상품등록 2.번호로검색 3.상품명으로검색 4.가격대로검색 5.전체목록 6.종료");
				int m = sc.nextInt();
				switch(m) {
				case 1:
					pservice.addProd(sc);
					break;
				case 2:
					pservice.getProd(sc);
					break;
				case 3:
					pservice.getByPname(sc);
					break;
				case 4:
					pservice.getByPrice(sc);
					break;
				case 5:
					pservice.getAll(sc);
					break;
				case 6:
					flag = false;
					break;
				}
			}
			
		}
}
