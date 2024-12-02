import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame {
    private JTextField idField;
    private JPasswordField pwdField;

    public UserLogin() {
        setTitle("사용자 로그인");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
            // 입력된 아이디와 비밀번호 확인
            String id = idField.getText();
            String pwd = new String(pwdField.getPassword());

            if (id.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                // 로그인 성공 처리 (현재는 단순히 팝업만 표시)
                JOptionPane.showMessageDialog(null, "로그인 성공!", "확인", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}
