import javax.swing.*;
import java.awt.*;

public class StoreMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("가게 계정 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // 메뉴 패널
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 4, 5, 5));

        JButton btnMenuEdit = new JButton("메뉴 수정");
        JButton btnOrder = new JButton("주문 현황");
        JButton btnInfo = new JButton("가게 정보");
        JButton btnInquiry = new JButton("1:1 문의");        

        menuPanel.add(btnMenuEdit);
        menuPanel.add(btnOrder);
        menuPanel.add(btnInfo);
        menuPanel.add(btnInquiry);
        
        JPanel menuEditPanel = createMenuEditPanel();
        JPanel orderPanel = createOrderPanel();
        JPanel infoPanel = createInfoPanel();
        JPanel inquiryPanel = createInquiryPanel();
        
        mainPanel.add(menuEditPanel, "MenuEdit");
        mainPanel.add(orderPanel, "Order");
        mainPanel.add(infoPanel, "Info");
        mainPanel.add(inquiryPanel, "Inquiry");
        
     // 메뉴 버튼에 액션 리스너 추가
        btnMenuEdit.addActionListener(e -> cardLayout.show(mainPanel, "MenuEdit"));
        btnOrder.addActionListener(e -> cardLayout.show(mainPanel, "Order"));
        btnInfo.addActionListener(e -> cardLayout.show(mainPanel, "Info"));
        btnInquiry.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        
        
        // 가게 이름
        JPanel topPanel = new JPanel();
        JLabel storeLabel = new JLabel("가게 이름");
        // 가게 이름 추후에 데이터베이스에서 가게 정보 테이블에서 이름 가져오도록 변경하기
        storeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(storeLabel);

        // 메인 프레임 구성
        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
    // 메뉴 수정 패널 생성
    private static JPanel createMenuEditPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("메뉴 수정 화면"));
        
        // 메뉴 리스트 테이블 -> 데이터베이스에서 가져오는 거로 수정 필요
        String[] columns = {"메뉴 이름", "가격"};
        Object[][] data = {
                {"치킨", "18,000원"},
                {"피자", "20,000원"}
        };
        JTable menuTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(menuTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel btnPanel = new JPanel();
        JButton deleteBtn = new JButton("삭제");
        JButton updateBtn = new JButton("수정");
        JButton addBtn = new JButton("추가");
        
        btnPanel.add(deleteBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(addBtn);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // 주문 현황 패널
    private static JPanel createOrderPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("주문 현황 화면"));
        
        // 주문 리스트 테이블 -> 데이터베이스에서 가져오는 거로 수정 필요
        String[] columns = {"사용자 아이디", "메뉴 이름", "가격"};
        Object[][] data = {
                {"user1", "치킨", "18,000원"},
                {"user2", "피자", "20,000원"}
        };
        
        JTable orderTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(orderTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        JButton cancelBtn = new JButton("취소");
        JButton acceptBtn = new JButton("수락");
        
        btnPanel.add(cancelBtn);
        btnPanel.add(acceptBtn);
        
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // 가게 정보 패널
    private static JPanel createInfoPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("가게 정보 화면"));
        return panel;
    }
    
 // 1:1 문의 사항 패널
    private static JPanel createInquiryPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("1:1 문의 사항 화면"));
        return panel;
    }
}
