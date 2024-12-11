import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Insert {
	// user 회원가입
	public static boolean UserLoginSingup(List<String> user) {
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
		
			String insert_user = "insert into user values (?,?,?,?,?)";
			PreparedStatement pt = conn.prepareStatement(insert_user);
			pt.setString(1, user.get(0));
			pt.setString(2, user.get(1));
			pt.setString(3, user.get(3));
			pt.setString(4, user.get(2));
			pt.setInt(5, 0);
			
			
			pt.executeUpdate(); // SQL 실행
            System.out.println("데이터 삽입 완료");

            conn.close();
            System.out.println("연결 해제");
            return true;
        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}	
	
	// owner 회원가입
	public static boolean OwnerLoginSingup(List<String> owner) {
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
		
			String insert_owner = "insert into owner values (?,?,?,?,?,?,?)";
			PreparedStatement pt = conn.prepareStatement(insert_owner);
			pt.setString(1, owner.get(0));
			pt.setString(2, owner.get(1));
			pt.setString(3, owner.get(2));
			pt.setString(4, owner.get(3));
			pt.setString(5, owner.get(4));
			pt.setString(6, null);
			pt.setString(7, owner.get(5));
			
			pt.executeUpdate(); // SQL 실행
            System.out.println("데이터 삽입 완료");

            conn.close();
            System.out.println("연결 해제");
            return true;

        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}
	
	// 메뉴 추가 
	public static boolean MenuInsert(List<String> menu) {
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
		
			String insert_menu = "insert into menu (ownerid, menuname, price, foodimg) values (?,?,?,?)";
			PreparedStatement pt = conn.prepareStatement(insert_menu);
			
			pt.setString(1, menu.get(0));
			pt.setString(2, menu.get(1));
			pt.setInt(3, Integer.parseInt(menu.get(2)));
			pt.setString(4, menu.get(3));
			
			pt.executeUpdate(); // SQL 실행
            System.out.println("데이터 삽입 완료");

            conn.close();
            System.out.println("연결 해제");
            return true;

        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}
	
	// 장바구니 담기
	public static boolean cart(int menuid, String userid, int count, int price) {
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
		
			String insert_cart = "insert into cart (userid, menuid, count, total, date) "
					+ "values (?,?,?,?,?)";
			PreparedStatement pt = conn.prepareStatement(insert_cart);
			
			pt.setString(1, userid);
			pt.setInt(2, menuid);
			pt.setInt(3, count);
			pt.setInt(4, count*price);
			pt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			
			pt.executeUpdate(); // SQL 실행
            System.out.println("데이터 삽입 완료");

            conn.close();
            System.out.println("연결 해제");
            return true;
        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}

	// 주문하기
	public static boolean storder(int cartid, String check) {
		List<Integer> cart = new ArrayList<>();
		List<String> menu = new ArrayList<>();
		String date = null;
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String selectCart = "select * from cart where cartid = ?";
			PreparedStatement pt = conn.prepareStatement(selectCart);
			pt.setInt(1, cartid);
			ResultSet rs = pt.executeQuery();
			if(rs.next()) {
				cart.add(rs.getInt("menuid"));
				cart.add(rs.getInt("count"));
				cart.add(rs.getInt("total"));
				java.sql.Timestamp timestamp = rs.getTimestamp("date");
                String formattedDate = timestamp != null 
                    ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp) 
                    : "null";
                date = formattedDate;
			}
			
			String selectMenu = "select * from menu where menuid = ?";
			PreparedStatement pt2 = conn.prepareStatement(selectMenu);
			pt2.setInt(1, cart.get(0));
			
			ResultSet rs2 = pt2.executeQuery();
			
			if(rs2.next()) {
				menu.add(rs2.getString("ownerid"));
				menu.add(rs2.getString("menuname"));
				menu.add(rs2.getString("price"));
			}
			
			String insertReceipt = "insert into storder"
					+ "(ownerid, menu, price, count, total, checked, date)"
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement pt3 = conn.prepareStatement(insertReceipt);
			pt3.setString(1, menu.get(0));
			pt3.setString(2, menu.get(1));
			pt3.setInt(3, Integer.parseInt(menu.get(2)));
			pt3.setInt(4, cart.get(1));
			pt3.setInt(5, cart.get(2));
			pt3.setString(6, check);
			if (date != null) {
			    java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(date);
			    pt3.setTimestamp(7, sqlTimestamp);
			} else {
			    pt3.setNull(7, java.sql.Types.TIMESTAMP); // 날짜가 없는 경우 null로 설정
			}
			pt3.executeUpdate();
			
			System.out.println("데이터 삽입 완료");
			
			conn.close();
			return true;
        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}
	
	// 주문하기
	public static boolean receipt(int cartid, String userid) {
		List<String> menu = new ArrayList<>();
		List<Integer> cart = new ArrayList<>();
		String storename = null;
		String date = null;
		
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String selectcart = "select * from cart where cartid = ?";
			PreparedStatement pt = conn.prepareStatement(selectcart);
			pt.setInt(1, cartid);
			ResultSet rs = pt.executeQuery();
			
			if(rs.next()) {
				cart.add(rs.getInt("menuid"));
				cart.add(rs.getInt("count"));
				cart.add(rs.getInt("total"));
				java.sql.Timestamp timestamp = rs.getTimestamp("date");
                String formattedDate = timestamp != null 
                    ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp) 
                    : "null";
                date = formattedDate;
			}
			
			
			String selectMenu = "select * from menu where menuid = ?";
			PreparedStatement pt2 = conn.prepareStatement(selectMenu);
			pt2.setInt(1, cart.get(0));
			
			ResultSet rs2 = pt2.executeQuery();
			
			if(rs2.next()) {
				menu.add(rs2.getString("ownerid"));
				menu.add(rs2.getString("menuname"));
				menu.add(rs2.getString("price"));
				menu.add(rs2.getString("menuimg"));
			}
			
			String selectStorename = "select storename from owner where ownerid = ?";
			PreparedStatement pt3 = conn.prepareStatement(selectStorename);
			pt3.setString(1, menu.get(0));
			ResultSet rs3 = pt3.executeQuery();
			if(rs3.next()) {
				storename = rs3.getString("storename");
			}
			
			String insertReceipt = "insert into receipt"
					+ "(userid, storename, menuname, menuimg, count, price, total, date)"
					+ "values (?,?,?,?,?,?,?,?)";
			PreparedStatement pt4 = conn.prepareStatement(insertReceipt);
			pt4.setString(1, userid);
			pt4.setString(2, storename);
			pt4.setString(3, menu.get(1));
			pt4.setString(4, menu.get(3));
			pt4.setInt(5, cart.get(1));
			pt4.setInt(6, Integer.parseInt(menu.get(2)));
			pt4.setInt(7, cart.get(2));
			if (date != null) {
			    java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(date);
			    pt4.setTimestamp(8, sqlTimestamp);
			} else {
			    pt4.setNull(8, java.sql.Types.TIMESTAMP); // 날짜가 없는 경우 null로 설정
			}
			pt4.executeUpdate();
			
			System.out.println("데이터 삽입 완료");
			
			conn.close();
			return true;
        } catch (Exception e) {
            System.out.println("DB 연결 오류");
            e.printStackTrace();
            return false;
        }
	}
	public static void main(String[] args) {
		if(Insert.storder(7, "odr")) {
			System.out.println("됨");
		}
		else {
			System.out.println("안됨");
		}
	}
}
