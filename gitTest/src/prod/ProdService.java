package prod;

import java.util.ArrayList;
import java.util.Scanner;

import board.mem.MemService;

public class ProdService {
	private ProdDao dao;

	public ProdService() {
		dao = new ProdDao();
	}

	public void addProd(Scanner sc) {
		System.out.println("=== 제품 등록 ===");
		System.out.println("제품명을 입력하세요 : ");
		String pname = sc.next();
		System.out.println("가격을 입력하세요 : ");
		int price = sc.nextInt();
		System.out.println("수량을 입력하세요 : ");
		int pcount = sc.nextInt();
		dao.insert(new Prod(0, pname, price, pcount, MemService.loginId));
	}

	public void getProd(Scanner sc) {
		System.out.println("=== 제품 검색 ===");
		System.out.println("상품번호 : ");
		int num = sc.nextInt();
		Prod p = dao.select(num);
		if (p == null) {
			System.out.println("없는 상품 번호");
		} else {
			System.out.println(p);
			if (MemService.loginId.equals(p.getSeller())) { // 로그인한 사람의 글이라면
				System.out.println("1.수정 2.삭제 3.입출고 4.상페 종료");
				int x = sc.nextInt();
				switch (x) {
				case 1:
					editProd(sc, num);
					break;
				case 2:
					delProd(num);
					break;
				case 3:
					editPcount(sc, num);
					break;
				}
			}
		}
	}

	public void getByPname(Scanner sc) {
		System.out.println("=== 제품명으로 검색 ===");
		System.out.println("제품명을 입력하세요 : ");
		String pname = sc.nextLine();
		ArrayList<Prod> list = dao.selectByPname(pname);
		if (list.isEmpty()) {
			System.out.println("검색된 결과 없음");
		} else {
			for (Prod p : list) {
				System.out.println(p);
			}
		}
	}

	public void getByPrice(Scanner sc) {
		System.out.println("=== 가격대로 검색 ===");
		System.out.println("하한가 : ");
		int p1 = sc.nextInt();
		System.out.println("상한가 : ");
		int p2 = sc.nextInt();
		ArrayList<Prod> list = dao.selectByPrice(p1, p2);
		if (list.isEmpty()) {
			System.out.println("검색 결과 없음");
		} else {
			for (Prod p : list) {
				System.out.println(p);
			}
		}
	}

	public void getAll(Scanner sc) {
		System.out.println("=== 전체 검색 ===");
		ArrayList<Prod> list = dao.selectAll();
		if (list.isEmpty()) {
			System.out.println("검색 결과 없음");
		} else {
			for (Prod p : list) {
				System.out.println(p);
			}
		}
	}

	public void editProd(Scanner sc, int num) {
		System.out.println("=== 제품 수정 ===");
		System.out.println("new 제품명을 입력하세요 : ");
		String pname = sc.nextLine();
		System.out.println("new 가격을 입력하세요 : ");
		int price = sc.nextInt();

		dao.update(new Prod(num, pname, price, 0, ""));
	}

	public void editPcount(Scanner sc, int num) {
		System.out.println("=== 입출고 ===");
		System.out.println("1.입고 2.출고");
		int type = sc.nextInt();
		String[] title = { "입고", "출고" };
		if (type < 1 || type > 2) {
			System.out.println("잘못 입력 하셧습니다.");
			return;
		}
		System.out.println(title[type - 1] + "량 : ");
		int pcount = sc.nextInt();
		if (type == 2) {
			pcount = -pcount;
		}
		dao.updatePcount(num, pcount);
	}

	public void delProd(int num) {
		dao.delete(num);
	}

}
