import javax.swing.*;
import java.awt.*;

public class UserMain {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("사용자 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // 상단 "추천 메뉴"
        JLabel titleLabel = new JLabel("추천 메뉴", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);
        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 5, 5, 5));
        menuPanel.setBackground(Color.LIGHT_GRAY);

        // 메뉴 항목 및 이미지 파일
        String[] menuItems = {"치킨", "피자", "중국집", "찜", "분식", "족발보쌈", "한식", "햄버거"};
        String[] imagePaths = {
            "images/chicken.png", "images/pizza.png", "images/chinese.png",
            "images/stew.png", "images/snack.png", "images/pork.png",
            "images/korean.png", "images/burger.png"
        };

        for (int i = 0; i < menuItems.length; i++) {
            // 이미지 불러오기 및 크기 조정
            ImageIcon originalIcon = new ImageIcon(imagePaths[i]);
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH); // 100x100 크기로 조정
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // 버튼 생성
            JButton button = new JButton(menuItems[i], scaledIcon);
            button.setFont(new Font("SansSerif", Font.PLAIN, 16));
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // 텍스트를 이미지 아래에 배치
            menuPanel.add(button);
        }
        frame.add(menuPanel, BorderLayout.CENTER);

        // 하단 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3, 10, 10)); // 1행 3열
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        String[] bottomButtons = {"구매내역", "장바구니", "내정보"};
        for (String label : bottomButtons) {
            JButton button = new JButton(label);
            button.setFont(new Font("SansSerif", Font.BOLD, 16));
            bottomPanel.add(button);
        }
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}