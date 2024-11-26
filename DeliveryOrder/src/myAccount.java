import javax.swing.*;
import java.awt.*;

public class myAccount {
    public static void main(String[] args) {
        JFrame frame = new JFrame("구매 내역");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JButton homeBtn = new JButton("홈");
        homeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(homeBtn);

        // 구매 내역을 리스트 형식으로 표시
        String[] myAccount = {
                "2024-11-01 | ABC 가게 | 45,000원",
                "2024-11-02 | XYZ 가게 | 60,000원",
                "2024-11-03 | 맛있는 가게 | 30,000원"
        };

        JList<String> myAccountList = new JList<>(myAccount);
        JScrollPane scrollPane = new JScrollPane(myAccountList);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 홈 버튼 클릭 시 동작 정의
        homeBtn.addActionListener(e -> {
            System.out.println("홈 버튼 클릭, 메인 페이지로 돌아감");
            frame.dispose();
            // 메인 페이지 클래스를 호출 -> 추가 구현 필요
        });

        frame.setVisible(true);
    }
}
