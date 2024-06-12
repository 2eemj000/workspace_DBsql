// prepareStatement 활용
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberDaoPreparedStatement{
	public static Scanner sc = new Scanner(System.in);
	public static String url = "jdbc:mysql://localhost:3306/musthave";
	public static String username = "scott";
	public static String password = "tiger";
	
	public static void main(String[] args) throws Exception{
		Connection con = DriverManager.getConnection(url, username, password);
		boolean flag = true;
		while(flag) {
			System.out.print("[I]nsert/[U]pdate/[D]elete/[S]elect/e[X]it : ");
			String s = sc.next().toUpperCase();
			switch(s) {
			case "I" : insertMember(con); break;
			case "U" : updateMember(con); break;
			case "D" : deleteMember(con); break;
			case "S" :  System.out.println("조회할 ID를 입력하세요: ");
            			int userId = sc.nextInt();
            			selectAllMember(con, userId); break;
			case "X" : flag = false; break;
			}
		}
	    System.out.print("Bye~");
	    con.close();
        sc.close();
	}
	
	public static int insertMember(Connection con) throws SQLException {
		System.out.println("Insert Data");
		System.out.println("pass : "); String pass = sc.next();
		System.out.println("name : "); String name = sc.next();
		try (
			PreparedStatement psmt = con.prepareStatement("insert into member(pass,name) values(?,?)")){
			// 변수타입맞춰서 set변경
			// ? 순서 맞춰서 1부터 index시작
			psmt.setString(1, pass);
			psmt.setString(2, name);
			return psmt.executeUpdate();
		}
	} 
	
	public static int updateMember(Connection con) throws SQLException {
		System.out.println("Update Data");
		System.out.println("id : "); int id = sc.nextInt();
		System.out.println("pass : "); String pass = sc.next();
		System.out.println("name : "); String name = sc.next();
		try (PreparedStatement psmt = con.prepareStatement("update member set pass=?,name=? where id=?")){
			psmt.setString(1, pass);
            psmt.setString(2, name);
            psmt.setInt(3, id);
			return psmt.executeUpdate();
		}
	} 
	
	private static int deleteMember(Connection con) throws SQLException {
		System.out.println("Delete Data");
		System.out.println("id : "); int id = sc.nextInt();
		try (PreparedStatement psmt = con.prepareStatement("delete from member where id=?")){
			psmt.setInt(1, id);
			return psmt.executeUpdate();
		}
	}

	private static void selectAllMember(Connection con, int userId) throws SQLException {
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        String sql = "SELECT * FROM member WHERE id=?";
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
  