import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Select {
	// user 정보 가지고 오기
	public static  List<String> SelectUser(String id) {
		List<String> infor = new ArrayList<String>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_id = "select * from user where userid = ?";
			PreparedStatement pt = conn.prepareStatement(select_id);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();
			
			if(rs.next()) {
				infor.add(rs.getString("userid"));
				infor.add(rs.getString("pwd"));
				infor.add(rs.getString("adress"));
				infor.add(rs.getString("name"));
				infor.add(rs.getString("money"));
			}
			System.out.println(infor);
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return infor;
	}
	// user들 id 가져오기
	public List<String> SelectUsers(){
		List<String> users = new ArrayList<String>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_users = "select userid from user";
			PreparedStatement pt = conn.prepareStatement(select_users);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				users.add(rs.getString("userid"));
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return users;
	}
	
	// owner 정보 가지고 오기
	public static List<String> SelectOwner(String id){
		List<String> infor = new ArrayList<String>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_id = "select * from owner where ownerid = ?";
			PreparedStatement pt = conn.prepareStatement(select_id);
			pt.setString(1, id);
			ResultSet rs = pt.executeQuery();
			
			if(rs.next()) {
				infor.add(rs.getString("ownerid"));
				infor.add(rs.getString("pwd"));
				infor.add(rs.getString("adress"));
				infor.add(rs.getString("name"));
				infor.add(rs.getString("storename"));
				infor.add(rs.getString("storeimg"));
				infor.add(rs.getString("category"));
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return infor;
	}
	
	// owner들 id 가져오기
	public List<String> SelectOwners(){
		List<String> owners = new ArrayList<String>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_owners = "select ownerid from owner";
			PreparedStatement pt = conn.prepareStatement(select_owners);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				owners.add(rs.getString("ownerid"));
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return owners;
	}

	// 가게들 이름 가지고 오기
	public static List<List<String>> SelectStores(String category){
		List<List<String>> stores = new ArrayList<>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_stores = "select storename, ownerid from owner where category = ?";
			PreparedStatement pt = conn.prepareStatement(select_stores);
			pt.setString(1, category);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				List<String> store = new ArrayList<>();
				store.add(rs.getString("ownerid"));
				store.add(rs.getString("storename"));
				stores.add(store);
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return stores;
	}	
	
	// 해당 가게 메뉴와 가격 가지고 오기 리스트에 배열로 해서
	public static List<List<String>> SelectMenu(String ownerid){
		List<List<String>> menu = new ArrayList<>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_owners = "select menuid, menuname, price, menuimg from menu where ownerid = ?";
			PreparedStatement pt = conn.prepareStatement(select_owners);
			pt.setString(1, ownerid);
			
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				List<String> menu2 = new ArrayList<String>();
				menu2.add(rs.getString("menuid"));
				menu2.add(ownerid);
				menu2.add(rs.getString("menuname"));
				menu2.add(rs.getString("price"));
				menu2.add(rs.getString("menuimg"));
				System.out.println(menu2);
				menu.add(menu2);
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return menu;
	}
	
	// 장바구니 불러오기
	public static List<List<String>> SelectCart(String userid) {
	    List<List<String>> menu = new ArrayList<>();
	    try (Connection conn = DBconnecter.getConnection()) { // try-with-resources 사용
	        String query = 
	            "SELECT c.cartid, c.menuid, c.count, c.total, c.date, m.menuname, m.price " +
	            "FROM cart c " +
	            "JOIN menu m ON c.menuid = m.menuid " +
	            "WHERE c.userid = ?";
	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, userid);

	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    List<String> cart = new ArrayList<>();
	                    cart.add(rs.getString("cartid"));
	                    cart.add(userid);
	                    cart.add(rs.getString("menuid"));
	                    cart.add(rs.getString("count"));
	                    cart.add(rs.getString("total"));

	                    // date 변환
	                    java.sql.Timestamp timestamp = rs.getTimestamp("date");
	                    String formattedDate = timestamp != null 
	                        ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp) 
	                        : "null";
	                    cart.add(formattedDate);

	                    cart.add(rs.getString("menuname"));
	                    cart.add(rs.getString("price"));
	                    menu.add(cart);
	                }
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("DB 연결 오류: " + e.getMessage());
	    }
	    return menu;
	}
	public static List<String> SelectReceipt(String userid){
		List<String> receipt = new ArrayList<String>();
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_receipt = "select * from receipt where userid = ?";
			PreparedStatement pt = conn.prepareStatement(select_receipt);
			pt.setString(1, userid);
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				// date 변환
                java.sql.Timestamp timestamp = rs.getTimestamp("date");
                String formattedDate = timestamp != null 
                    ? new SimpleDateFormat("yy-MM-dd HH:mm").format(timestamp) 
                    : "null";
				String rpt = formattedDate + " || "  + rs.getString("storename") + " || " +
                    rs.getString("menuname") + " || " + "수량 : " + rs.getString("count") +
                    " || " + "총 금액 : " + rs.getString("total");
				receipt.add(rpt);
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return receipt;
	}
	
	public static int SelectUserMoney(String userid) {
		int userMoney = 0;
		try {
			Connection conn = DBconnecter.getConnection();
			System.out.println("DB 연결");
			
			String select_user_money = "select money from user where userid = ?";
			PreparedStatement pt = conn.prepareStatement(select_user_money);
			pt.setString(1, userid);
			ResultSet rs = pt.executeQuery();
			
			
			if(rs.next()) {
				userMoney = rs.getInt("money");
			}
			conn.close();
			System.out.println("연결해제");
		}catch (Exception e) {
			System.out.println("DB 연결 오류");
		}
		return userMoney;
	}
	public static void main(String[] args) {
		int userMoney = SelectUserMoney("aaaa");
		System.out.println(userMoney);
	}
}

