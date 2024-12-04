import javax.swing.*;
import java.awt.*;

public class StoreMain {
    public StoreMain() {
        JFrame frame = new JFrame("가게 계정 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // 가게 이름 로그인 아이디랑 연동해서 끌고 오기?
        JPanel topPanel = new JPanel();
        JLabel storeLabel = new JLabel("가게 이름");
        storeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(storeLabel);

        // 탭 팬 추가
        JTabbedPane tabbedPane = new JTabbedPane();

        // 각 탭에 패널 추가
        tabbedPane.addTab("메뉴 수정", createMenuEditPanel());
        tabbedPane.addTab("주문 현황", createOrderPanel());
        tabbedPane.addTab("가게 정보", createInfoPanel());

        // 메인 프레임 구성
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
    // 메뉴 수정 패널 생성
    private static JPanel createMenuEditPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("메뉴 수정 화면", SwingConstants.CENTER), BorderLayout.NORTH);

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("주문 현황 화면", SwingConstants.CENTER), BorderLayout.NORTH);

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("가게 정보 화면", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StoreMain::new);
    }
}