import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class StoreInfo {
    private final List<String> owner;
    private String userid;
    ImageIcon storeImage;
    JLabel imageLabel;
    Image image;
    
    public StoreInfo(List<String> owner, String userid) {
        this.owner = owner;
        this.userid = userid;
        
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame(owner.get(4) + " 정보");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new BorderLayout());

        // 상단 이미지 및 가게 정보
        JPanel topPanel = createTopPanel();
        frame.add(topPanel, BorderLayout.NORTH);

        // 메뉴 리스트
        JPanel menuPanel = createMenuPanel();
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가
        frame.add(menuScrollPane, BorderLayout.CENTER);

        // 장바구니 보기 버튼
        JButton viewCartButton = new JButton("장바구니 보기");
        viewCartButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        viewCartButton.setPreferredSize(new Dimension(0, 50)); // 버튼 높이 설정
        viewCartButton.addActionListener(e -> {
        	frame.dispose();
        	openCartWindow();
        });
        frame.add(viewCartButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 추가

        // 이미지 로드
        storeImage = new ImageIcon("images/" + owner.get(5));
        image = storeImage.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 가게 정보
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel storeNameLabel = new JLabel(owner.get(4));
        storeNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        storeNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel storeAddress = new JLabel(owner.get(2));
        storeAddress.setFont(new Font("SansSerif", Font.PLAIN, 18));
        storeAddress.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(storeNameLabel);
        infoPanel.add(Box.createVerticalStrut(10)); // 간격 추가
        infoPanel.add(storeAddress);

        topPanel.add(imageLabel, BorderLayout.NORTH);
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        return topPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        List<List<String>> menus = Select.SelectMenu(owner.get(0));
        if(menus == null ||menus.isEmpty()) {
        	JPanel menuItemPanel = new JPanel();
        	JLabel nonLb = new JLabel("메뉴 정보가 없습니다.");
        	menuItemPanel.add(nonLb);
        	menuPanel.add(menuItemPanel);
        }else {
        for (List<String> menu : menus) {
        	
            JPanel menuItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout으로 정렬
            menuItemPanel.setBackground(Color.WHITE);
            
	            // 메뉴 이름
	            JLabel menuItemLabel = new JLabel(menu.get(2));
	            JLabel menuPrice = new JLabel(menu.get(3));
	            menuItemLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
	            menuItemPanel.add(menuItemLabel);
	            menuItemPanel.add(menuPrice);
	
	            // 수량 선택 스피너
	            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
	            menuItemPanel.add(quantitySpinner);
	
	            // "추가" 버튼
	            JButton addButton = new JButton("추가");
	            addButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
	            addButton.addActionListener(new AddToCartListener(menu.get(0), menu.get(2), quantitySpinner, Integer.parseInt(menu.get(3))));
	            menuItemPanel.add(addButton);
	
	            menuPanel.add(menuItemPanel);
	            menuPanel.add(Box.createVerticalStrut(10)); // 간격 추가
            }
        }

        return menuPanel;
    }

    private void openCartWindow() {
    	List<List<String>> cart = Select.SelectCart(userid);
        SwingUtilities.invokeLater(() -> new Cart(cart, userid).showCartWindow());
    }

    private class AddToCartListener implements ActionListener {
        private final int menuid;
        private final JSpinner quantitySpinner;
        private int price;
        private String menuname;

        public AddToCartListener(String menuid, String menuname, JSpinner quantitySpinner, int price) {
            this.menuid = Integer.parseInt(menuid);
            this.quantitySpinner = quantitySpinner;
            this.price = price;
            this.menuname = menuname;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = (int) quantitySpinner.getValue(); // JSpinner에서 수량 값 가져오기
            String cartItem = menuname + " (수량: " + quantity + ")";
            
            if(Insert.cart(menuid, userid, quantity, price)) {
	            JOptionPane.showMessageDialog(null, cartItem + "이(가) 장바구니에 추가되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
