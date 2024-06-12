package edu.pun.dao3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class MemberDao{ // 추상클래스
	public static String url = "jdbc:mysql://localhost:3306/musthave";
	public static String username = "scott";
	public static String password = "tiger";
	
	public abstract void insertMember(Connection con) throws SQLException;
	public abstract void updateMember(Connection con) throws SQLException;
	public abstract void deleteMember(Connection con) throws SQLException;
	public abstract void selectAllMember(Connection con) throws SQLException;
}

class MemberDaoStatement extends MemberDao{ // 구현클래스
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
		Connection con = DriverManager.getConnection(url, username, password)){
		boolean flag = true;
		while(flag) {
			System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/e[X]it : ");
			String s = sc.next().toUpperCase();
			switch(s) {
			case "I" : insertMember(con); break;
			case "U" : updateMember(con); break;
			case "D" : deleteMember(con); break;
			case "S" : selectAllMember(con); break;
			case "X" : flag = false; break;
			}
		}
	    System.out.print("Bye~");
	  } catch (SQLException e) {
          e.printStackTrace();
      }
	}
  
	@Override 
	public void insertMember(Connection con) throws SQLException {
		System.out.println("Insert Data");
		int is = 
		System.out.println("pass : "); String pass = sc.next();
		System.out.println("name : "); String name = sc.next();
		String sql = "insert into member(pass,name) values(?,?)";
		try (PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setString(1, pass);
			psmt.setString(2, name);
			psmt.executeUpdate();
		}
	}
	
	@Override 
	public void updateMember(Connection con) throws SQLException {
		String sql = "update member set pass=?,name=? where id=?";
		System.out.println("Update Data");
		System.out.println("id : "); int id = sc.nextInt();
		System.out.println("pass : "); String pass = sc.next();
		System.out.println("name : "); String name = sc.next();
		try (PreparedStatement psmt = con.prepareStatement(sql)) {
			psmt.setString(1, pass);
            psmt.setString(2, name);
            psmt.setInt(3, id);
			psmt.executeUpdate();
		}
	}
	
	@Override 
	public void deleteMember(Connection con) throws SQLException {
		String sql = "delete from member where id=?";
		System.out.println("Delete Data");
		System.out.println("id : "); int id = sc.nextInt();
		try (PreparedStatement psmt = con.prepareStatement(sql)) {
		psmt.setInt(1, id);
		psmt.executeUpdate();
		}
	}
	
	@Override 
	public void selectAllMember(Connection con) throws SQLException {
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    String sql = "SELECT * FROM member WHERE id=?";
	    System.out.println("조회할 ID를 입력하세요: ");
		int userId = sc.nextInt();
	    try {
	    	ps = con.prepareStatement(sql);
	    	ps.setInt(1, userId);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Password: " + rs.getString("pass"));
	            System.out.println("Name: " + rs.getString("name"));
	        } else {
	            System.out.println("해당 ID의 회원이 존재하지 않습니다.");
	        }
	    } finally {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	    }
	    }
	}


