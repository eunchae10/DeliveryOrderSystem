import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnecter {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sampledb"; // 데이터베이스 URL
            String user = "root"; // 데이터베이스 사용자명
            String pw = "1234"; // 데이터베이스 비밀번호
            
            Class.forName("com.mysql.cj.jdbc.Driver");

			// 연결된 객체 가져오기
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");// 드라이버 주소가 잘못됨
		} catch (SQLException e) {
			System.out.println("연결 실패");// url이 잘못됨
		} catch (Exception e) {
			System.out.println("알수 없는 오류");
		}
		return conn;
	}
}
