package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//dao: db 작업 수행
public class ManDao {
	private DBConnect db;
	
	public ManDao() {
		db = DBConnect.getInstance();
	}
	
	public void insert(Man m) {
		Connection conn = db.conn(); //db를 연결시켜주는 메서드 호출
		//내가 실행하고자 하느 쿼리문을 sql 변수에 담았다.
		String sql = "insert into man values(?,?,?,?)";
		//자바에서 sql을 실행할수 있는 객체를 생성하는 방식
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.set ..(?순서 , 값) :?에 들어갈 값을 매칭시켜주는 메서드
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			//sql 실행문
			int cnt = pstmt.executeUpdate(); // insert , update, delete 등의 문장을 실행시키는 문장 select는 다른 메서드로 실행한다
//			pstmt.executeQuery(); select문장을 실행시키는 메서드
			System.out.println(cnt + " 줄 추가됨");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
