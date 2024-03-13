package prod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBConnect;

public class ProdDao {
	private DBConnect db;

	public ProdDao() {
		db = DBConnect.getInstance();
	}

	public void insert(Prod p) {
		Connection conn = db.conn(); // db연결하는 코드
		String sql = "insert into prod valuse(productnum.nextval, ?, ?, ?, ?)"; // SQL문 작성하여 삽입
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPname());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getPcount());
			pstmt.setString(4, p.getSeller());
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "제품 추가 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public Prod select(int num) {
		Connection conn = db.conn(); // db연결하는 코드
		String sql = "select * from prod where pnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// 객체 정보 담아서 리턴
				// num, 제품명,가격,수량,판매자 순서
				return new Prod(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<Prod> selectByPname(String pname) {
		Connection conn = db.conn(); // db연결하는 코드
		String sql = "select * from prod where pname like ? order by num";
		ArrayList<Prod> list = new ArrayList<Prod>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + pname + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Prod(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<Prod> selectByPrice(int p1, int p2) {
		Connection conn = db.conn(); // db연결하는 코드
		String sql = "select * from prod where price between ? and ? order by num";
		ArrayList<Prod> list = new ArrayList<Prod>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p1);
			pstmt.setInt(2, p2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Prod(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<Prod> selectAll() {
		Connection conn = db.conn(); // db연결문
		String sql = "select * from Prod order by num";
		ArrayList<Prod> list = new ArrayList<Prod>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) { // rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Prod(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	public void update(Prod p) {
		Connection conn = db.conn(); // db 연결문
		String sql = "update prod set pname=?, price=? where num=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPname());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getPnum());
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 수정완료 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void delete(int num) {
		Connection conn = db.conn(); // db 연결문

		String sql = "delete prod where num=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 삭제 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
	public void updatePcount(int num,int pcount) {
		Connection conn = db.conn(); // db 연결문
		String sql = "update prod set pcount=pcount+? where num=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pcount);
			pstmt.setInt(2, num);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 입출고 처리됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
