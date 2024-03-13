package jdbc;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManDao dao = new ManDao();
		dao.insert(new Man("aaa","111","백승훈","idgod@fma.com"));
	}

}
