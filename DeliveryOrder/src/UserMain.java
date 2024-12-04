import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

public class UserMain {
    public static void main(String[] args) {
        // 초기 로딩 화면 표시
        SwingUtilities.invokeLater(() -> showLoadingScreen());
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

        // 백그라운드 작업 시작
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // 메인 화면 준비 작업 (이미지 로딩 등)
                prepareMainPage();
                return null;
            }

            @Override
            protected void done() {
                loadingFrame.dispose(); // 로딩 화면 닫기
                SwingUtilities.invokeLater(() -> createMainPage());
            }
        }.execute();
    }

    private static void prepareMainPage() {
        // 이미지 파일 로딩이나 기타 초기화 작업
        // 로딩이 느리다면 이곳에서 백그라운드 로직으로 수행
        try {
            Thread.sleep(1000); // 예제용 딜레이, 실제 이미지 로딩 작업 등 삽입
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createMainPage() {
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
            // 이미지 로딩을 비동기 처리 (느린 로딩 방지)
            ImageIcon scaledIcon = loadImage(imagePaths[i]);
            JButton button = new JButton(menuItems[i], scaledIcon);
            button.setFont(new Font("SansSerif", Font.PLAIN, 16));
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM); // 텍스트를 이미지 아래에 배치
            int index = i;
            button.addActionListener(e -> new StoreList(menuItems[index]));
            menuPanel.add(button);
        }
        frame.add(menuPanel, BorderLayout.CENTER);

        // 하단 패널
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3, 10, 10)); // 1행 3열
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        String[] bottomButtons = {"구매내역", "장바구니", "내정보"};
        DefaultListModel<String> cartModel = new DefaultListModel<>();
        for (String label : bottomButtons) {
            JButton button = new JButton(label);
            button.setFont(new Font("SansSerif", Font.BOLD, 16));
            button.addActionListener(e -> {
            	switch (label) {
            		case "구매내역":
            			new myAccount().showMyAccountWindow();
            			break;
            		case "장바구니":
            			new Cart(cartModel).showCartWindow();
            			break;
            		case "내정보":
            			new MyInfo();
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
            return new ImageIcon(); // 실패 시 빈 아이콘 반환
        }
    }
}
