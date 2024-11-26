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

        // Create a container panel to hold the content
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        // 1. 가게 사진 (상단)
        ImageIcon storeImage = new ImageIcon("images/store_image.jpg");  // 예시 이미지 파일
        JLabel imageLabel = new JLabel(storeImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 2. 가게 이름과 위치 (그 밑에)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel storeName = new JLabel("교촌 치킨");
        storeName.setFont(new Font("SansSerif", Font.BOLD, 20));  // 가게 이름 폰트 크기
        storeName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeAddress = new JLabel("서울특별시 강남구");
        storeAddress.setFont(new Font("SansSerif", Font.PLAIN, 16));  // 주소 폰트 크기
        storeAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add name and address to the info panel
        infoPanel.add(storeName);
        infoPanel.add(Box.createVerticalStrut(10)); // 간격 추가
        infoPanel.add(storeAddress);

        // 3. 가게에서 파는 음식들과 가격
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Sample menu with prices
        String[] menuItems = {
            "피자 15,000원",
            "버거 8,000원",
            "음료 2,000원",
            "파스타 12,000원",
            "스테이크 25,000원",
            "디저트 5,000원",
            "샐러드 7,000원",
            "수프 6,000원"
        };

        for (String item : menuItems) {
            JLabel menuItem = new JLabel(item);
            menuItem.setFont(new Font("SansSerif", Font.PLAIN, 14));  // 음식 목록 폰트 크기
            menuItem.setAlignmentX(Component.LEFT_ALIGNMENT);
            menuPanel.add(menuItem);
        }

        // Scroll panel for menu items
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setPreferredSize(new Dimension(350, 200));  // 메뉴 영역 크기 확장

        // Add the panels to the container panel
        containerPanel.add(imageLabel, BorderLayout.NORTH);
        containerPanel.add(infoPanel, BorderLayout.CENTER);
        containerPanel.add(menuScrollPane, BorderLayout.SOUTH);

        // Add a border to the container to separate the content
        containerPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // Add container panel to the frame
        frame.add(containerPanel, BorderLayout.CENTER);

        // Show frame
        frame.setVisible(true);
    }
}
