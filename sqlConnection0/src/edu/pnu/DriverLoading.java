package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverLoading {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("로딩 성공");
		
		Connection con = // con은 객체
				DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "scott", "tiger");
				
		System.out.println("연결 성공");
		con.createStatement( ); // con의 method
//		[Statement의 메서드]
//		ResultSet executeQuery(String sql) - select : 테이블형식의 결과가 나옴
//		int executeUpdate(String sql)	- select를 제외한 나머지 질의 (insert, update, delete 등) : 해당 data의 count 결과가 나옴
//		boolean execute(String sql) - 모든 질의

	}
}
