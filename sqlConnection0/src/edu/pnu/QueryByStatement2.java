package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class QueryByStatement2 {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/world";
	public static final String username = "scott";
	public static final String password = "tiger";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
		System.out.println("=".repeat(80));
		System.out.println(">>Select[0]:Exit");
		System.out.println("[1].select name, countrycode, district, population from city limit 10");
		System.out.println("[2].select * from country");
		int sel = sc.nextInt();
		System.out.println("=".repeat(80));
		if (sel==0) break;
			switch(sel) {
				case 1: Citytest();
					break;
				case 2: Countrytest();
					break;
				default: System.out.println("1 or 2");
		}
}
	sc.close();
}
	
	private static void Countrytest() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name, continent, surfacearea, population, capital from country limit 10");
			while (rs.next()) {
				System.out.println(rs.getString("name")+",");
				System.out.println(rs.getString("continent")+",");
				System.out.println(rs.getString("surfacearea")+",");
				System.out.println(rs.getString("population")+",");
				System.out.println(rs.getString("capital")+",");
			}
			rs.close();
			st.close();
			con.close();
		}catch (Exception e) {
			System.out.println("연결 실패 : "+ e.getMessage());
		}
	}

	private static void Citytest() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name, countrycode, district, population from city limit 10");
			while (rs.next()) {
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