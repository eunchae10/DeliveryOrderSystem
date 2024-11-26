import javax.swing.*;
import java.awt.*;

public class StoreList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreList().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("가게 리스트");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());
        
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        JLabel foodLabel = new JLabel("음식 이름");
        foodLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
//        JTextField foodField = new JTextField(20); // 음식 이름 입력 필드
//        foodField.setEditable(false); // 나중에 데이터베이스와 연결될 부분이므로, 현재는 비활성화

        topPanel.add(foodLabel);
//        topPanel.add(foodField);

        DefaultListModel<String> storeListModel = new DefaultListModel<>();
        JList<String> storeList = new JList<>(storeListModel);
        JScrollPane scrollPane = new JScrollPane(storeList);
        
        // 추후에 데이터베이스 연동 후 변경될 내용
        storeListModel.addElement("교촌치킨");
        storeListModel.addElement("BHC 치킨");
        storeListModel.addElement("60계 치킨");

        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(containerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
