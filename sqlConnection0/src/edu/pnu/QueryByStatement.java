package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {
	public static void main(String[] args) {
		
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id, name, countrycode, district, population from city limit 10");
					
			while (rs.next()) {
				System.out.println(rs.getString("id")+",");
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("countrycode")+",");
				System.out.println(rs.getString("district")+",");
				System.out.println(rs.getString("population")+"\n");
			}
			rs.close();
			st.close();
			con.close();
		}catch (Exception e) {
			System.out.println("연결 실패 : "+ e.getMessage());
		}
	}
}

//ResultSet Methods
//void afterLast()끝 빈 행으로 커서를 이동함
//void beforeFirst()시작 빈 행으로 커서를 이동함
//boolean next()현재 커서 다음의 레코드 유무를 판단함. true인 경우 커서를 다음으로 이동시킴
//XXX getXXX(String column명) /getXXX(int index)커서가 위치한 레코드의 컬럼 값을 반환함 (XXX 는 데이터 타입)
