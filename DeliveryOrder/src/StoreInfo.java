import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreInfo {
    private DefaultListModel<String> cartModel = new DefaultListModel<>(); // 항상 초기화

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreInfo().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Main frame
        JFrame frame = new JFrame("가게 정보");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());

        // 이미지 로드
        ImageIcon storeImage = new ImageIcon("images/store_image.png");
        Image image = storeImage.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 가게 정보 패널
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

        // 메뉴 패널
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
            JPanel menuItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout으로 정렬

            // 메뉴 이름
            JLabel menuItemLabel = new JLabel(item);
            menuItemLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            menuItemPanel.add(menuItemLabel);

            // 수량 선택 스피너 (위/아래 버튼 제공)
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1)); // 초기값 1, 최소값 1, 최대값 99, 증가값 1
            menuItemPanel.add(quantitySpinner);

            // "추가" 버튼 (맨 오른쪽)
            JButton addButton = new JButton("추가");
            menuItemPanel.add(addButton);

            addButton.addActionListener(new AddToCartListener(item, quantitySpinner));

            menuPanel.add(menuItemPanel);
            menuPanel.add(Box.createVerticalStrut(5)); // 간격 추가
        }

        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setPreferredSize(new Dimension(350, 300));

        // 장바구니 보기 버튼
        JButton viewCartButton = new JButton("장바구니 보기");
        viewCartButton.addActionListener(e -> openCartWindow());

        containerPanel.add(imageLabel, BorderLayout.NORTH);
        containerPanel.add(infoPanel, BorderLayout.CENTER);
        containerPanel.add(menuScrollPane, BorderLayout.SOUTH);

        frame.add(containerPanel, BorderLayout.CENTER);
        frame.add(viewCartButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private class AddToCartListener implements ActionListener {
        private final String item;
        private final JSpinner quantitySpinner;

        public AddToCartListener(String item, JSpinner quantitySpinner) {
            this.item = item;
            this.quantitySpinner = quantitySpinner;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = (int) quantitySpinner.getValue(); // JSpinner에서 수량 값 가져오기
            String cartItem = item + " (수량: " + quantity + ")";
            cartModel.addElement(cartItem);
            JOptionPane.showMessageDialog(null, cartItem + "이(가) 장바구니에 추가되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openCartWindow() {
        SwingUtilities.invokeLater(() -> new Cart(cartModel).showCartWindow());
    }
}
