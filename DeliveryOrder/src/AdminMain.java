import javax.swing.*;
import java.awt.*;

public class AdminMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("관리자 계정 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("가게 관리", createStoreManagePanel());
        tabbedPane.addTab("사용자 관리", createUserManagePanel());
        tabbedPane.addTab("가게 별 매출", createSalesPanel());
        tabbedPane.addTab("문의 사항", createInquiryPanel());

        frame.add(tabbedPane, BorderLayout.CENTER);

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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("가게 별 매출", SwingConstants.CENTER), BorderLayout.NORTH);

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
