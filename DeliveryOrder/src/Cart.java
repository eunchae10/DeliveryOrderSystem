import javax.swing.*;
import java.awt.*;

public class Cart {
    public static void main(String[] args) {
        JFrame frame = new JFrame("장바구니 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JButton homeBtn = new JButton("홈");
        homeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(homeBtn);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // 가게 이름
        JLabel storeNameLabel = new JLabel("가게 이름: ABC 가게");
        storeNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(storeNameLabel);

        // 주문 목록 테이블
        String[] columns = {"주문 상품", "수량", "가격"};
        Object[][] data = {
                {"치킨", 2, "36,000원"},
                {"피자", 1, "20,000원"},
                {"음료수", 3, "9,000원"}
        };
        JTable cartTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(cartTable);
        centerPanel.add(tableScrollPane);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // 총 가격
        JLabel totalLabel = new JLabel("총 가격: 65,000원");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        bottomPanel.add(totalLabel);

        // 결제하기 버튼
        JButton payButton = new JButton("결제하기");
        bottomPanel.add(payButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        homeBtn.addActionListener(e -> {
        	System.out.println("홈 버튼 클릭, 메인 페이지로 돌아감");
            frame.dispose();
            // 메인 페이지 클래스를 호출 -> 추가 구현 필요
        });

        frame.setVisible(true);
    }
}
