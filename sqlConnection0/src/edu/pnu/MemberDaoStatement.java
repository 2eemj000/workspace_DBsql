// createStatement 활용
// 동적으로 쿼리 생성가능
// '%s' : string, %d : int(정수), %f : float,double(실수), %c : char(문자)
package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class  MemberDaoStatement{
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
		String sql = String.format("insert into member(pass,name) values('%s','%s')",pass,name);
		try (
			Statement stmt = con.createStatement()){
			return stmt.executeUpdate(sql);
		}
	} 
	
	public static int updateMember(Connection con) throws SQLException {
		System.out.println("Update Data");
		System.out.println("id : "); int id = sc.nextInt();
		System.out.println("pass : "); String pass = sc.next();
		System.out.println("name : "); String name = sc.next();
		String sql = String.format("update member set pass='%s', name='%s' where id=%d", pass,name,id);
		try (
			Statement stmt = con.createStatement()){
			return stmt.executeUpdate(sql);
		}
	} 
	
	private static int deleteMember(Connection con) throws SQLException {
		System.out.println("Delete Data");
		System.out.println("id : "); int id = sc.nextInt();
		String sql = String.format("delete from member where id=%d",id);
		try (
			Statement stmt = con.createStatement()){
			return stmt.executeUpdate(sql);
		}
	}

	private static void selectAllMember(Connection con, int userId) throws SQLException {
		 String sql = String.format("SELECT * FROM member WHERE id=%d", userId);
		  try (Statement stmt = con.createStatement();
		             ResultSet rs = stmt.executeQuery(sql)) {
			  if (rs.next()) {
	                System.out.println("ID: " + rs.getInt("id"));
	                System.out.println("Password: " + rs.getString("pass"));
	                System.out.println("Name: " + rs.getString("name"));
	            } else {
	                System.out.println("해당 ID의 항목이 존재하지 않습니다.");
	            }
	        }
	    }
	}