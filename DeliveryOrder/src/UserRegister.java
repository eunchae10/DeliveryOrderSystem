import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserRegister extends JFrame {
    private JTextField nameField, idField, addField;
    private JPasswordField pwdField, confirmPwdField;
    private boolean checkPw = false;

    public UserRegister() {
        setTitle("사용자 회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel("사용자 회원가입", JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.LIGHT_GRAY);
        c.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("이름: "));
        nameField = FixedTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("아이디: "));
        idField = FixedTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("비밀번호: "));
        pwdField = FixedPwdField();
        formPanel.add(pwdField);

        formPanel.add(new JLabel("비밀번호 확인: "));
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPwdField = FixedPwdField();
        JButton confirmBtn = new JButton("확인");
        confirmBtn.addActionListener(new CheckPasswordListener());
        confirmPanel.add(confirmPwdField, BorderLayout.CENTER);
        confirmPanel.add(confirmBtn, BorderLayout.EAST);
        formPanel.add(confirmPanel);

        formPanel.add(new JLabel("주소: "));
        addField = FixedTextField();
        formPanel.add(addField);

        c.add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton signupBtn = new JButton("회원가입");
        signupBtn.addActionListener(new SignupButtonListener());
        btnPanel.add(signupBtn);

        c.add(btnPanel, BorderLayout.SOUTH);

        setSize(450, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField FixedTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(150, 25));
        return textField;
    }

    private JPasswordField FixedPwdField() {
        JPasswordField pwdField = new JPasswordField();
        pwdField.setPreferredSize(new Dimension(150, 25));
        return pwdField;
    }

    private class CheckPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pwd = new String(pwdField.getPassword());
            String confirmPwd = new String(confirmPwdField.getPassword());

            if (pwd.equals(confirmPwd)) {
                JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
                checkPw = true;
            } else {
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SignupButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 모든 필드가 입력되었는지 확인
        	if (nameField.getText().isEmpty() || idField.getText().isEmpty() || 
            	addField.getText().isEmpty() || pwdField.getPassword().length == 0 ||
                confirmPwdField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "정보 입력을 완료해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                // 비밀번호와 비밀번호 확인 값 비교
                String pwd = new String(pwdField.getPassword());
                
                if (checkPw) {
                	List<String> user = new ArrayList<>();
                	user.add(idField.getText());
                	user.add(pwd);
                	user.add(nameField.getText());
                	user.add(addField.getText());
                	
                	if(Insert.UserLoginSingup(user))
                    JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
                	dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호 일치 확인이 필요합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new UserRegister();
    }
}
