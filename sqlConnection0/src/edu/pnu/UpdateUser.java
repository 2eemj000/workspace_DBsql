package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateUser{
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/musthave";
	public static String username = "scott";
	public static String password = "tiger";
	public static void UpdateUser(member usr) {
		Connection con = null;
        PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			String sql = "update member set pass=?,name=? where id=?;";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, usr.getPass());
			ps.setString(2, usr.getName());
			ps.setInt(3, usr.getId());
			ps.executeUpdate();
			
			System.out.println("Update Success");
			selectUser(con, usr);
			con.close();
		
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
	
	// update된 항목만 출력해주도록
	private static void selectUser(Connection con, member usr) {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT * FROM member WHERE id=?";
	        ps = con.prepareStatement(sql); // SQL 쿼리를 미리 컴파일
	        ps.setInt(1, usr.getId()); // usr.getId() 메서드를 통해 회원 객체의 ID를 가져와서 SQL 쿼리의 매개변수로 설정
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Password: " + rs.getString("pass"));
	            System.out.println("Name: " + rs.getString("name"));
	        }
	    } catch (SQLException e) {
	    	System.out.println("SQL 오류: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
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
			member usr = new member();
			usr.setId(8);
			usr.setPass("newpass");
			usr.setName("newname");
			UpdateUser(usr);
		}
	}