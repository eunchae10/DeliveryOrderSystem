import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {
	
	public int LoginUser(String id, String pw) {
		int result = 2;
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			String sql_login_user = "select * from user where userid = ?";
			PreparedStatement pt = conn.prepareStatement(sql_login_user);
			pt.setString(1, id);
			
			ResultSet rs = pt.executeQuery();
			if(rs.next()) {
				String pwdb = rs.getString("pwd");
				if(pwdb.equals(pw)) {
					result = 0;
					
				}else {
					result = 1;
				}
			}else {
				result = 2;
			}
			conn.close();
			System.out.println("연결해제");
			
		} catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return result;
	}	
	public int LoginOwner(String id, String pw) {
		int result = 2;
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			String sql_login_owner = "select * from owner where ownerid = ?";
			PreparedStatement pt = conn.prepareStatement(sql_login_owner);
			pt.setString(1, id);
			
			ResultSet rs = pt.executeQuery();
			if(rs.next()) {
				String pwdb = rs.getString("pwd");
				if(pwdb.equals(pw)) {
					result = 0;
					
				}else {
					result = 1;
				}
			}else {
				result = 2;
			}
			conn.close();
			System.out.println("연결해제");
			
		} catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return result;
	}	
}