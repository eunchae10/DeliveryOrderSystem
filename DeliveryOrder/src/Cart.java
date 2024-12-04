import javax.swing.*;
import java.awt.*;

public class Cart {
    private DefaultListModel<String> cartModel;

    public Cart(DefaultListModel<String> cartModel) {
        this.cartModel = cartModel;
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

        // 장바구니 리스트
        JList<String> cartList = new JList<>(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        centerPanel.add(scrollPane);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // 총 가격 계산
        JLabel totalLabel = new JLabel("총 가격: 계산 중...");
        bottomPanel.add(totalLabel);

        JButton checkoutButton = new JButton("결제하기");
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "결제가 완료되었습니다!", "확인", JOptionPane.INFORMATION_MESSAGE);
            cartModel.clear(); // 결제 완료 후 장바구니 비우기
        });

        bottomPanel.add(checkoutButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        homeBtn.addActionListener(e -> frame.dispose());
        frame.setVisible(true);
    }
}
