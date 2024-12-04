import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMain extends JFrame {

    public HomeMain() {
        // JFrame 설정
        setTitle("메인 홈 페이지");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 위치

        // 메인 컨테이너 패널
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220)); // 밝은 회색 배경

        // 상단 제목 패널
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY); // 진한 회색 배경
        JLabel titleLabel = new JLabel("Delivery Order System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE); // 흰색 텍스트
        titlePanel.add(titleLabel);

        // 로그인 및 회원가입 패널
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2, 10, 10)); // 1행 2열, 간격
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(220, 220, 220)); // 밝은 회색 배경

        // 로그인 패널
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 1, 15, 15)); // 2행 1열, 간격 설정
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login")); // 구분선과 제목
        loginPanel.setBackground(Color.LIGHT_GRAY); // 패널 배경색

        // 회원가입 패널
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(2, 1, 15, 15)); // 2행 1열, 간격 설정
        signUpPanel.setBorder(BorderFactory.createTitledBorder("Sign Up")); // 구분선과 제목
        signUpPanel.setBackground(Color.LIGHT_GRAY); // 패널 배경색

        // 공통 버튼 스타일
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonBackground = Color.DARK_GRAY; // 버튼 배경색
        Color buttonForeground = Color.WHITE; // 버튼 텍스트 색

        // 로그인 버튼 생성
        JButton userLoginButton = createStyledButton("User Login", buttonFont, buttonBackground, buttonForeground);
        JButton storeLoginButton = createStyledButton("Store Login", buttonFont, buttonBackground, buttonForeground);

        // 회원가입 버튼 생성
        JButton userSignUpButton = createStyledButton("User Sign Up", buttonFont, buttonBackground, buttonForeground);
        JButton storeSignUpButton = createStyledButton("Store Sign Up", buttonFont, buttonBackground, buttonForeground);

        // 버튼 리스너 추가
        userLoginButton.addActionListener(e -> new UserLogin(this));
        storeLoginButton.addActionListener(e -> new StoreLogin(this));
        userSignUpButton.addActionListener(e -> new UserRegister());
        storeSignUpButton.addActionListener(e -> new StoreRegister());

        // 버튼을 각각의 패널에 추가
        loginPanel.add(userLoginButton);
        loginPanel.add(storeLoginButton);

        signUpPanel.add(userSignUpButton);
        signUpPanel.add(storeSignUpButton);

        // 메인 컨텐츠 패널에 로그인과 회원가입 패널 추가
        contentPanel.add(loginPanel);
        contentPanel.add(signUpPanel);

        // 메인 패널에 제목과 내용 패널 추가
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // JFrame에 메인 패널 추가
        add(mainPanel);

        setVisible(true);
    }

    // 스타일이 적용된 버튼 생성
    private JButton createStyledButton(String text, Font font, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false); // 포커스 테두리 제거
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 버튼 테두리 추가
        return button;
    }

    public static void main(String[] args) {
        // 프로그램 실행
        SwingUtilities.invokeLater(HomeMain::new);
    }
}
