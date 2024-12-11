import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StoreRegister extends JFrame {
    private JTextField nameField, SnameField, addField, idField;
    private JPasswordField pwdField, confirmPwdField;
    private JComboBox<String> typeComboBox;
    private boolean checkPw = false;

    public StoreRegister() {
        setTitle("가게 회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        // 상단 제목 패널
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel("가게 회원가입", JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.LIGHT_GRAY);
        c.add(titlePanel, BorderLayout.NORTH);

        // 입력 폼 패널
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10)); // 7행 2열로 변경

        formPanel.add(new JLabel("아이디: "));
        idField = FixedTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("비밀번호: "));
        pwdField = FixedPwdField();
        formPanel.add(pwdField);

        formPanel.add(new JLabel("비밀번호 확인: "));
        JPanel confirmPanel = new JPanel(new BorderLayout());
        confirmPwdField = FixedPwdField();
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new CheckPasswordListener());
        confirmPanel.add(confirmPwdField, BorderLayout.CENTER);
        confirmPanel.add(confirmButton, BorderLayout.EAST);
        formPanel.add(confirmPanel);

        formPanel.add(new JLabel("가게 주소: "));
        addField = FixedTextField();
        formPanel.add(addField);

        formPanel.add(new JLabel("이름: "));
        nameField = FixedTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("가게 이름: "));
        SnameField = FixedTextField();
        formPanel.add(SnameField);

        // JComboBox 추가
        formPanel.add(new JLabel("카테고리: "));
        String[] businessTypes = {"치킨", "피자", "중국집", "찜", "분식", "족발보쌈", "한식", "햄버거"};
        typeComboBox = new JComboBox<>(businessTypes);
        typeComboBox.setPreferredSize(new Dimension(150, 25));
        formPanel.add(typeComboBox);

        c.add(formPanel, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel btnPanel = new JPanel();
        JButton signupBtn = new JButton("회원가입");
        signupBtn.addActionListener(new SignupButtonListener());
        btnPanel.add(signupBtn);

        c.add(btnPanel, BorderLayout.SOUTH);

        setSize(500, 450);
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
            // 모든 필드와 선택 값 확인
        	if (idField.getText().isEmpty() || nameField.getText().isEmpty() || 
            		SnameField.getText().isEmpty() || addField.getText().isEmpty() || 
            		pwdField.getPassword().length == 0 || confirmPwdField.getPassword().length == 0 ||
            		typeComboBox.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "정보 입력을 완료해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                // 비밀번호와 비밀번호 확인 값 비교
                String pwd = new String(pwdField.getPassword());
                String confirmPwd = new String(confirmPwdField.getPassword());

                if (checkPw) {
                	List<String> owner = new ArrayList<>();
                	owner.add(idField.getText());
                	owner.add(pwd);
                	owner.add(addField.getText());
                	owner.add(nameField.getText());
                	owner.add(SnameField.getText());
                	owner.add((String)typeComboBox.getSelectedItem());
                	
                	if(Insert.OwnerLoginSingup(owner))
                    JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
                	dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호 일치 확인이 필요합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new StoreRegister();
    }
}
