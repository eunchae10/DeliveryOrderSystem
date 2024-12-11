import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserMain {
    private static String userid;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showLoadingScreen());
    }

    public static void screen(String id) {
        userid = id;
        SwingUtilities.invokeLater(() -> UserMain.main(new String[]{}));
    }

    private static void showLoadingScreen() {
        JFrame loadingFrame = new JFrame("로딩 중...");
        JLabel loadingLabel = new JLabel("메인 화면을 준비 중입니다...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loadingFrame.add(loadingLabel);
        loadingFrame.setSize(300, 200);
        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadingFrame.setVisible(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                prepareMainPage();
                return null;
            }

            @Override
            protected void done() {
                loadingFrame.dispose();
                SwingUtilities.invokeLater(() -> createMainPage());
            }
        }.execute();
    }

    private static void prepareMainPage() {
        try {
            Thread.sleep(1000); // 예제용 딜레이
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createMainPage() {
        JFrame frame = new JFrame("사용자 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700); // 세로 크기 증가
        frame.setLayout(new BorderLayout());

        // 상단 추천 메뉴 라벨
        JLabel titleLabel = new JLabel("추천 메뉴", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 추천 메뉴 아래 사용자 잔액 표시용 패널 추가
        JPanel moneyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬
        int userMoney = Select.SelectUserMoney(userid); // 사용자 잔액 가져오기
        JLabel moneyLabel = new JLabel("잔액: " + userMoney + "원");
        moneyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16)); // 메뉴 버튼과 비슷한 크기
        moneyPanel.add(moneyLabel);

        // ChargeBalance의 리스너 추가
        ChargeBalance.addBalanceChangeListener(new ChargeBalance.BalanceChangeListener() {
            @Override
            public void onBalanceChanged(int newBalance) {
                SwingUtilities.invokeLater(() -> moneyLabel.setText("잔액: " + newBalance + "원"));
            }
        });
        
        // moneyPanel을 "추천 메뉴"와 메뉴 패널 사이에 배치
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(moneyPanel, BorderLayout.NORTH);

        // 추천 메뉴 패널
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 5, 5, 5));
        menuPanel.setBackground(Color.LIGHT_GRAY);

        String[] menuItems = {"치킨", "피자", "중국집", "찜", "분식", "족발보쌈", "한식", "햄버거"};
        String[] imagePaths = {
            "images/chicken.png", "images/pizza.png", "images/chinese.png",
            "images/stew.png", "images/snack.png", "images/pork.png",
            "images/korean.png", "images/burger.png"
        };

        for (int i = 0; i < menuItems.length; i++) {
            ImageIcon scaledIcon = loadImage(imagePaths[i]);
            JButton button = new JButton(menuItems[i], scaledIcon);
            button.setFont(new Font("SansSerif", Font.PLAIN, 16));
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            int index = i; // 람다에서 사용할 변수
            button.addActionListener(e -> {
                List<List<String>> stores = Select.SelectStores(menuItems[index]);
                System.out.println(stores);
                new StoreList(menuItems[index], stores, userid);
            });
            menuPanel.add(button);
        }
        centerPanel.add(menuPanel, BorderLayout.CENTER);
        frame.add(centerPanel, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 4, 10, 10));
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        String[] bottomButtons = {"구매내역", "장바구니", "내정보", "잔액 충전", "로그아웃"};
        for (String label : bottomButtons) {
            JButton button = new JButton(label);
            button.setFont(new Font("SansSerif", Font.BOLD, 16));
            button.addActionListener(e -> {
                switch (label) {
                    case "구매내역":
                        List<String> receipt = Select.SelectReceipt(userid);
                        new myAccount().showMyAccountWindow(receipt); // 구매내역 창 열기
                        break;
                    case "장바구니":
                        List<List<String>> cartModel = Select.SelectCart(userid);
                        new Cart(cartModel, userid).showCartWindow(); // 공통 장바구니 모델 사용
                        break;
                    case "내정보":
                        List<String> user = Select.SelectUser(userid);
                        new MyInfo(user); // 내정보 창 열기
                        break;
                    case "잔액 충전":
                        int selectUserMoney = Select.SelectUserMoney(userid);
                        System.out.println(selectUserMoney);
                        ChargeBalance.charge(frame, userid, selectUserMoney); // 잔액 충전 기능 호출
                        break;
                    case "로그아웃":
                        logout(frame); // 로그아웃 기능 호출
                        break;
                }
            });
            bottomPanel.add(button);
        }
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }



    private static ImageIcon loadImage(String path) {
        try {
            ImageIcon originalIcon = new ImageIcon(path);
            Image scaledImage = originalIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path);
            return new ImageIcon();
        }
    }

    private static void logout(JFrame parentFrame) {
        int confirm = JOptionPane.showConfirmDialog(parentFrame, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            parentFrame.dispose(); // 현재 메인 프레임 닫기
            SwingUtilities.invokeLater(LoginSignupPage::new); // 로그인 화면으로 이동
        }
    }
}
