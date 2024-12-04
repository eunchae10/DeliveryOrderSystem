import javax.swing.*;
import java.awt.*;

public class myAccount {
    public void showMyAccountWindow() {
        JFrame frame = new JFrame("구매 내역");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        homeBtn.addActionListener(e -> frame.dispose());
        
        frame.setVisible(true);
    }
}
