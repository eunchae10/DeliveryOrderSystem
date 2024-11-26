import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StoreInfo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreInfo().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Main frame
        JFrame frame = new JFrame("가게 정보");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        ImageIcon storeImage = new ImageIcon("images/store_image.png");  // 예시 이미지 파일
        Image image = storeImage.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);  // 이미지 크기 조정
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);  // 이미지 가운데 정렬

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel storeName = new JLabel("교촌 치킨");
        storeName.setFont(new Font("SansSerif", Font.BOLD, 20));
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeAddress = new JLabel("서울특별시 강남구");
        storeAddress.setFont(new Font("SansSerif", Font.PLAIN, 16));
        storeAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(storeName);
        infoPanel.add(Box.createVerticalStrut(10)); // 간격 추가
        infoPanel.add(storeAddress);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        String[] menuItems = {
            "교촌 오리지날 19,000원",
            "교촌 레드 20,000원",
            "교촌 허니 19,000원",
            "교촌 살살치킨 20,000원",
            "교촌 소이살살 20,000원",
            "칩카사바 2,000원",
            "국물맵떡 9,000원",
            "치즈볼 3,500원"
        };

        for (String item : menuItems) {
            JLabel menuItem = new JLabel(item);
            menuItem.setFont(new Font("SansSerif", Font.PLAIN, 14));
            menuItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            menuPanel.add(menuItem);
        }

        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setPreferredSize(new Dimension(350, 200));

        containerPanel.add(imageLabel, BorderLayout.NORTH);
        containerPanel.add(infoPanel, BorderLayout.CENTER);
        containerPanel.add(menuScrollPane, BorderLayout.SOUTH);

        containerPanel.setBorder(new LineBorder(Color.BLACK, 2));

        frame.add(containerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
