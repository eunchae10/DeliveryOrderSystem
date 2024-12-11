import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreLogin extends JFrame {
    private JTextField idField;
    private JPasswordField pwdField;
    private LoginSignupPage parentPage;
    DTBL d = new DTBL(); // DTBL 클래스에서 userid 설정

    public StoreLogin(LoginSignupPage parentPage) {
        this.parentPage = parentPage;

        setTitle("가게 로그인");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(10, 10));

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel("가게 로그인", JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titlePanel.add(textLabel, BorderLayout.CENTER);
        titlePanel.setBackground(Color.LIGHT_GRAY);
        c.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("아이디: "));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("비밀번호: "));
        pwdField = new JPasswordField();
        formPanel.add(pwdField);

        c.add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new LoginButtonListener());
        btnPanel.add(loginButton);

        c.add(btnPanel, BorderLayout.SOUTH);

        setSize(350, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public StoreLogin(HomeMain homeMain) {
		// TODO Auto-generated constructor stub
	}

	private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText().trim();
            String pwd = new String(pwdField.getPassword()).trim();

            login l = new login();
            int num = l.LoginOwner(id, pwd);

            if (id.isEmpty() || pwd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            } else {
                if (num == 0) {
                    if ("admin".equals(id)) { // admin ID 체크
                        JOptionPane.showMessageDialog(null, "관리자 로그인 성공!", "확인", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // 현재 로그인 창 닫기
                        SwingUtilities.invokeLater(() -> AdminMain.main(new String[]{})); // AdminMain 실행
                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 성공!", "확인", JOptionPane.INFORMATION_MESSAGE);
                        d.setUserid(id); // 성공한 사용자의 ID 설정
                        if (parentPage != null) {
                            parentPage.dispose(); // 부모 창 닫기
                        }
                        dispose(); // 현재 로그인 창 닫기

                        // StoreMain 실행, id 전달
                        SwingUtilities.invokeLater(() -> new StoreMain(id));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    public static void main(String[] args) {
    	new StoreLogin((LoginSignupPage) null); // 테스트용
    }
}
