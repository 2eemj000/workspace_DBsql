package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QueryByStatement3 {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/world";
	public static final String username = "scott";
	public static final String password = "tiger";

	public static void main(String[] args) {
		Connection con;
		Statement st;
		try {
			con = getConnection();
			st = con.createStatement(); //()안에 빈칸, 근데, conn.prepareStatement(queryString)이고 index는 0이 아니라 1부터 시작
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("=".repeat(80));
				System.out.println(">>Select[0]:Exit");
				System.out.println("1. 수도와 함께 국가 이름을 검색합니다.");
				System.out.println("2. 세계에서 인구가 가장 많은 상위 5개 도시의 이름과 인구를 검색합니다.");
				System.out.println("3. 미국(USA)에 있는 모든 도시의 이름을 검색합니다.");
				System.out.println("4. 중국에서 사용되는 모든 언어의 이름을 검색합니다.");
				System.out.println("5. 인구가 100만 명 이상인 유럽 국가의 이름을 검색합니다.");
				System.out.println("6. 1900년 이후 독립한 국가의 이름을 검색합니다.");
				System.out.println("7. 영어가 공용어가 아니고 국민총생산(GNP)이 1,000보다 큰 국가의 이름을 검색합니다.");
				int sel = sc.nextInt();
				System.out.println("=".repeat(80));
				if (sel == 0)
					break;
				switch (sel) {
				case 1:test1(st);break;
				case 2:test2(st);break;
				case 3:test3(st);break;
				case 4:test4(st);break;
				case 5:test5(st);break;
				case 6:test6(st);break;
				case 7:test7(st);break;
				default:
					System.out.println("1/2/3/4/5/6/7");
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
			}
		}

	private static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= count; i++) {
				System.out.println(rs.getString(i) + ((i == count) ? "" : ","));
			}
			System.out.println();
		}
		rs.close();
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(url, username, password);
	}

	private static void test1(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select c.name, ci.name "
				+"from city ci, country c "
				+"where c.capital = ci.id "
				+"limit 5";
		printResultSet(st.executeQuery(sql));
	}

	private static void test2(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select name, population "
				+"from city "
				+"order by population desc "
				+"limit 5";
		printResultSet(st.executeQuery(sql));
	}
	
	private static void test3(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select c.name, ci.name "
				+"from city ci, country c "
				+"where c.name = 'united states' "
				+"and c.code = ci.countrycode "
				+"limit 5";
		printResultSet(st.executeQuery(sql));
	}
	
	private static void test4(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select c.code, c.name, cl.language "
				+"from country c, countrylanguage cl "
				+"where name = 'china' "
				+"and c.code = cl.countrycode "
		   		+"limit 5";
		printResultSet(st.executeQuery(sql));
	}
	
	private static void test5(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select code, name "
				+"from country "
				+"where continent = 'europe' "
				+"and population >= 1000000";
		printResultSet(st.executeQuery(sql));
	}
	
	private static void test6(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select code, name "
				+"from country "
				+"where indepyear >= 1900";
		printResultSet(st.executeQuery(sql));
	}
	
	private static void test7(Statement st) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select  c.name, cl.language, cl.isofficial "
				+"from countrylanguage cl, country c "
				+"where cl.language = 'English' and cl.isofficial = 'F' "
				+"and cl.countrycode = c.code "
				+"and c.gnp>1000";
		printResultSet(st.executeQuery(sql));
	}
}
