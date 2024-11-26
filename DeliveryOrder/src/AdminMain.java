import javax.swing.*;
import java.awt.*;

public class AdminMain {
    public static void main(String[] args) {
        // 프레임 설정
        JFrame frame = new JFrame("관리자 계정 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // CardLayout을 사용한 패널 전환
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // 메뉴 패널
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 4, 5, 5));

        JButton btnStoreManage = new JButton("가게 관리");
        JButton btnUserManage = new JButton("사용자 관리");
        JButton btnStoreSales = new JButton("가게 별 매출");
        JButton btnInquiry = new JButton("문의 사항");

        menuPanel.add(btnStoreManage);
        menuPanel.add(btnUserManage);
        menuPanel.add(btnStoreSales);
        menuPanel.add(btnInquiry);

        // 각 패널 생성
        JPanel storeManagePanel = createStoreManagePanel();
        JPanel userManagePanel = createUserManagePanel();
        JPanel salesPanel = createSalesPanel();
        JPanel inquiryPanel = createInquiryPanel();

        // 카드 레이아웃에 각 패널 추가
        mainPanel.add(storeManagePanel, "StoreManage");
        mainPanel.add(userManagePanel, "UserManage");
        mainPanel.add(salesPanel, "StoreSales");
        mainPanel.add(inquiryPanel, "Inquiry");

        // 메뉴 버튼에 액션 리스너 추가
        btnStoreManage.addActionListener(e -> cardLayout.show(mainPanel, "StoreManage"));
        btnUserManage.addActionListener(e -> cardLayout.show(mainPanel, "UserManage"));
        btnStoreSales.addActionListener(e -> cardLayout.show(mainPanel, "StoreSales"));
        btnInquiry.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));

        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // 가게 관리 패널
    private static JPanel createStoreManagePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("가게 관리 화면"));
        return panel;
    }

    // 사용자 관리 패널
    private static JPanel createUserManagePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("사용자 관리 화면"));
        return panel;
    }

    // 문의 사항 패널
    private static JPanel createInquiryPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("문의 사항 화면"));
        return panel;
    }

    // 가게별 매출 패널
    private static JPanel createSalesPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("가게 별 매출"));
        
        // 가게별 매출을 나타낼 테이블 -> 추후 데이터베이스에서 불러오게 수정 필요
        String[] columns = {"가게 이름", "매출 금액"};
        Object[][] data = {
                {"가게 A", "1,500,000원"},
                {"가게 B", "2,200,000원"},
                {"가게 C", "850,000원"}
        };
        
        JTable salesTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        
        return panel;
    }
}
