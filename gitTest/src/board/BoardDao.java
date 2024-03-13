package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DBConnect;

public class BoardDao {
	private DBConnect db;
	public BoardDao() {
		db = DBConnect.getInstance();
	}
	
	public void insert(Board b) {
		Connection conn = db.conn();
		String sql = "insert into board valuse(seq1.nextval, ? , sysdate,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, b.getWriter());
			pstmt.setString(2, b.getContent());
			pstmt.setString(3, b.getTitle());
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 추가 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public Board select (int num) {
		Connection conn = db.conn();
		String sql = "select * from board where num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Board(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Board> selectByTitle(String title){
		Connection conn = db.conn(); //db연결문
		String sql = "select * from board where title like ? order by num";
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, "%"+title+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { //rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Board(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5))); 
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public ArrayList<Board> selectByWriter(String writer){
		Connection conn = db.conn(); //db연결문
		String sql = "select * from board where writer = ? order by num";
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, writer);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { //rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Board(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5))); 
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public ArrayList<Board> selectAll(){
		Connection conn = db.conn(); //db연결문
		String sql = "select * from board order by num";
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { //rs.next(): 읽을 값이 있으면 true 없으면 false 반환
				list.add(new Board(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5))); 
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	public void update(Board b) {
		Connection conn = db.conn(); //db 연결문
		String sql = "update board set title=? content=? where num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, b.getNum());
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 추가 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void delete(int num) {
		Connection conn = db.conn(); //db 연결문
		String sql = "delete board where num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1,num);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄 삭제 됨");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
