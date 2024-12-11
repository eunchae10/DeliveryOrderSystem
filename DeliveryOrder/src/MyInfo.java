import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyInfo extends JFrame {	
    public MyInfo(List<String> user) {
        // 프레임 설정
        setTitle("내정보");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 메인 패널 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 사용자 정보 패널
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("이름:");
        JTextField nameField = new JTextField(user.get(3)); // 기본값 예시

        JLabel idLabel = new JLabel("아이디:");
        JTextField idField = new JTextField(user.get(0));
        idField.setEditable(false); // 수정 불가

        JLabel passwordLabel = new JLabel("비밀번호:");
        JPasswordField passwordField = new JPasswordField(user.get(1)); // 기본값 예시

        JLabel addressLabel = new JLabel("주소:");
        JTextField addressField = new JTextField(user.get(2)); // 기본값 예시

        infoPanel.add(nameLabel);
        infoPanel.add(nameField);
        infoPanel.add(idLabel);
        infoPanel.add(idField);
        infoPanel.add(passwordLabel);
        infoPanel.add(passwordField);
        infoPanel.add(addressLabel);
        infoPanel.add(addressField);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JButton updateButton = new JButton("수정");
        updateButton.addActionListener(e -> {
        	List<String> updateUser = new ArrayList<String>();
            
        	updateUser.add(idField.getText());
        	updateUser.add(new String(passwordField.getPassword()));
        	updateUser.add(addressField.getText());
        	updateUser.add(nameField.getText());
        	
        	if(nameField.getText().isEmpty() || idField.getText().isEmpty() || 
			addressField.getText().isEmpty() || passwordField.getPassword().length == 0 ) {
        		JOptionPane.showMessageDialog(null, "입력되지 않은 곳이 있습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        	}else {
        		UpdateInformation up = new UpdateInformation();
            	up.UpdateUser(updateUser);
                JOptionPane.showMessageDialog(this, "정보가 수정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                dispose();
        	}
        });

        buttonPanel.add(updateButton);

        // 메인 패널에 정보 패널과 버튼 패널 추가
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }
}
