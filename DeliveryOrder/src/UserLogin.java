import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame {
    private JTextField idField;
    private JPasswordField pwdField;
    private HomeMain parentPage;

    public UserLogin(HomeMain parentPage) {
    	this.parentPage = parentPage;
        setTitle("사용자 로그인");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel("사용자 로그인", JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.LIGHT_GRAY);
        c.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("아이디: "));
        idField = FixedTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("비밀번호: "));
        pwdField = FixedPwdField();
        formPanel.add(pwdField);

        c.add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new LoginButtonListener());
        btnPanel.add(loginButton);

        c.add(btnPanel, BorderLayout.SOUTH);

        setSize(350, 250);
        setLocationRelativeTo(null); // 화면 중앙
        setVisible(true);
    }
    
    private JTextField FixedTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(130, 18));
        return textField;
    }

    private JPasswordField FixedPwdField() {
        JPasswordField pwdField = new JPasswordField();
        pwdField.setPreferredSize(new Dimension(130, 18));
        return pwdField;
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String pwd = new String(pwdField.getPassword());

            if (id.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "로그인 성공!", "확인", JOptionPane.INFORMATION_MESSAGE);
                
                // 부모 창 닫기
                if (parentPage != null) {
                    parentPage.dispose();
                }

                dispose(); // 현재 로그인 창 닫기
                SwingUtilities.invokeLater(() -> UserMain.main(new String[]{})); // UserMain 창 열기
            }
        }
    }

    public static void main(String[] args) {
        new UserLogin(null);
    }
}
