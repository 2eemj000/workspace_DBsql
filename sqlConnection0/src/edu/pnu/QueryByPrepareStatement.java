package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryByPrepareStatement{
	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password); // 데이터베이스에 연결을 설정하고 Connection 객체를 반환
			
			// city 테이블에서 name이 ?(즉, 매개변수)와 일치하는 행을 선택하는 쿼리
			// PreparedStatement pt = con.prepareStatement("select id, name, countrycode, district, population from city where name=?");
			PreparedStatement pt = con.prepareStatement("select id, name, countrycode, district, population from city where name like ? and population>?");
			// pt.setString(1,"Seoul"); // 첫 번째 매개변수 (?)에 Seoul 값을 설정 (0이 아니라 1부터 시작)
			pt.setString(1, "S%"); // 쿼리문에서 "?"이렇게 안쓰니까 데이터타입을 String으로 넣어줘야 자동으로 ""으로 감싸줌
			pt.setInt(2,9000000); // 데이터타입 맞추기
			ResultSet rs = pt.executeQuery(); // 쿼리를 실행하고 결과를 ResultSet 객체로 반환
			
			while(rs.next()) {
				System.out.print(rs.getInt("id")+',');
				System.out.print(rs.getString("name")+',');
				System.out.print(rs.getString("countrycode")+',');
				System.out.print(rs.getString("district")+',');
				System.out.println(rs.getInt("population"));
			}
			rs.close(); //  ResultSet 객체를 닫음
			pt.close(); // PreparedStatement 객체를 닫음
			con.close(); // Connection 객체를 닫음
		} catch (Exception e) {
			System.out.println("연결실패 : "+ e.getMessage());
		}
	}
}