import javax.swing.*;
import java.awt.*;
import java.util.List;


public class myAccount {
    public void showMyAccountWindow(List<String> receipt) {
        JFrame frame = new JFrame("구매 내역");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JButton homeBtn = new JButton("홈");
        homeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(homeBtn);

        String[] myAccount = receipt.toArray(new String[0]);
        
        JList<String> myAccountList = new JList<>(myAccount);
        
        JScrollPane scrollPane = new JScrollPane(myAccountList);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        homeBtn.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
