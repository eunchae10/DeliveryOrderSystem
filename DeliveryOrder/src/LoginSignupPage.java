import javax.swing.*;
import java.awt.*;

public class LoginSignupPage extends JFrame {

    public LoginSignupPage() {
        setTitle("Login & Sign Up");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel("Login & Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(220, 220, 220));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(2, 1, 15, 15));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        loginPanel.setBackground(Color.LIGHT_GRAY);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(2, 1, 15, 15));
        signUpPanel.setBorder(BorderFactory.createTitledBorder("Sign Up"));
        signUpPanel.setBackground(Color.LIGHT_GRAY);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonBackground = Color.DARK_GRAY;
        Color buttonForeground = Color.WHITE;

        // 로그인 버튼
        JButton userLoginButton = createStyledButton("User Login", buttonFont, buttonBackground, buttonForeground);
        JButton storeLoginButton = createStyledButton("Store Login", buttonFont, buttonBackground, buttonForeground);

        // 회원가입 버튼
        JButton userSignUpButton = createStyledButton("User Sign Up", buttonFont, buttonBackground, buttonForeground);
        JButton storeSignUpButton = createStyledButton("Store Sign Up", buttonFont, buttonBackground, buttonForeground);

        // 버튼 동작 추가
        userLoginButton.addActionListener(e -> new UserLogin(this)); // UserLogin 창 열기
        storeLoginButton.addActionListener(e -> new StoreLogin(this)); // StoreLogin 창 열기
        userSignUpButton.addActionListener(e -> new UserRegister()); // UserRegister 창 열기
        storeSignUpButton.addActionListener(e -> new StoreRegister()); // StoreRegister 창 열기

        // 버튼 추가
        loginPanel.add(userLoginButton);
        loginPanel.add(storeLoginButton);

        signUpPanel.add(userSignUpButton);
        signUpPanel.add(storeSignUpButton);

        contentPanel.add(loginPanel);
        contentPanel.add(signUpPanel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSignupPage::new);
    }
}
