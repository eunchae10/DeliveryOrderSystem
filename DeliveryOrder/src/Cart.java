import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cart {
    private List<List<String>> cartModel;
    private int totalPrice;
    private String userid;
    private int userMoney;
    
    public Cart(List<List<String>> cartModel, String userid) {
        this.cartModel = cartModel;
        this.userid = userid;
        userMoney = Select.SelectUserMoney(userid);
    }

    public void showCartWindow() {
        JFrame frame = new JFrame("장바구니 페이지");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        JButton homeBtn = new JButton("홈");
        homeBtn.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(homeBtn);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel cartTitle = new JLabel("장바구니");
        cartTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        centerPanel.add(cartTitle);

        // 잔액 표시 추가
        int userMoney = Select.SelectUserMoney(userid); // 사용자 ID를 기반으로 잔액 가져오기
        JLabel moneyLabel = new JLabel("잔액: " + userMoney + "원");
        moneyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16)); // 잔액 표시 폰트
        centerPanel.add(moneyLabel);

        // ChargeBalance의 리스너 추가
        ChargeBalance.addBalanceChangeListener(new ChargeBalance.BalanceChangeListener() {
            @Override
            public void onBalanceChanged(int newBalance) {
                SwingUtilities.invokeLater(() -> moneyLabel.setText("잔액: " + newBalance + "원"));
            }
        });
        
        DefaultListModel<String> ct = new DefaultListModel<String>();
        for (List<String> cart : cartModel) {
            // 장바구니 리스트
            String menuName = cart.get(6);  // 메뉴 이름
            String quantityStr = cart.get(3); // 수량
            String priceStr = cart.get(7);    // 단가
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            totalPrice += price * quantity;
            ct.addElement(menuName + " x" + quantity + " - " + (price * quantity) + "원");
        }
        JList<String> cartList = new JList<>(ct);
        JScrollPane scrollPane = new JScrollPane(cartList);
        centerPanel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // 총 가격 계산
        JLabel totalLabel = new JLabel("총 가격: " + totalPrice);
        bottomPanel.add(totalLabel);

        JButton checkoutButton = new JButton("결제하기");
        checkoutButton.addActionListener(e -> {
        	if (totalPrice > userMoney) {
                JOptionPane.showMessageDialog(frame, "잔액이 부족합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = true;
            for (List<String> cart : cartModel) {
                success &= Insert.receipt(Integer.parseInt(cart.get(0)), userid) &&
                           Insert.storder(Integer.parseInt(cart.get(0)), "odr");
                if (success) {
                    Deleted.DeletedCart(Integer.parseInt(cart.get(0)));
                } else {
                    JOptionPane.showMessageDialog(frame, "결제 처리 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            UpdateInformation.AddUserMoney(userid, userMoney - totalPrice);
        	System.out.println(userMoney - totalPrice);
        	System.out.println(Select.SelectUserMoney(userid));
        	cartModel.clear();
        	frame.dispose();

            // 새 창 열기
            new Cart(cartModel, userid).showCartWindow();
        });

        bottomPanel.add(checkoutButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        homeBtn.addActionListener(e -> frame.dispose());
        frame.setVisible(true);
    }
}
