package co.yedam.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

		//싱글톤 클래스 만드는 법..DataSource 줄여서 관례적으로 DAO 객체
		private static DataSource dataSource = new DataSource();
		private DataSource() {}
		
		private static String driver = "oracle.jdbc.OracleDriver"; //문자열안공백X
		private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
		private static String user = "hr";
		private static String password = "1234";
		
		private static Connection conn;
		
		public static DataSource getInstance() {
			return dataSource;
		}
		public Connection getConnection() {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,password);
//				System.out.println("DB 연결성공");
			}catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	}


