package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSQL{
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/musthave";
	public static String username = "scott";
	public static String password = "tiger";
	public static void insertUser(member usr) {
		Connection con = null;
        PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			String sql = "insert into member(id,pass,name) values(?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, usr.getId());
			ps.setString(2, usr.getPass());
			ps.setString(3, usr.getName());
			ps.executeUpdate();
			
			con.close();
			System.out.println("Insert Success");
		  } catch (ClassNotFoundException e) {
	            System.out.println("JDBC 드라이버 로드 실패: " + e.getMessage());
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("SQL 오류: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("연결 실패: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                System.out.println("자원 해제 오류: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	
	// 데이터 초기화
//	private int deleteUser(Connection con) throws SQLException {
//		Statement st = con.createStatement();
//		int ret = st.executeUpdate("delete from member"); // where id<5 이런식으로 조건줄수있음
//		st.close();
//		System.out.println("deleted : "+ret);
//		return ret;
//	}
	
	public static void main(String[] args) {
		for (int i=1;i<=10;i++) {
			member usr = new member(1, "pass", "name");
			usr.setId(i);
			usr.setPass("password"+i);
			usr.setName("usr"+i);
			insertUser(usr);
		}
	}
}