import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StoreList {
	String userid;
	JFrame frame = null;
    public StoreList(String category, List<List<String>> storesList, String userid) {
    	this.userid = userid;
        SwingUtilities.invokeLater(() -> createAndShowGUI(category, storesList));
    }

    private void createAndShowGUI(String category, List<List<String>> storesList) {
        frame = new JFrame(category + " 가게 리스트");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(category + " 가게 리스트", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (List<String> store : storesList) {
            JPanel storePanel = new JPanel(new BorderLayout());
            storePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            storePanel.setBackground(Color.WHITE);

            // 가게 이름 버튼 생성
            JButton storeButton = new JButton(store.get(1));
            storeButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
            storeButton.setFocusPainted(false);
            storeButton.setBorderPainted(false);
            storeButton.setContentAreaFilled(false);

            // 버튼 클릭 시 StoreInfo 열기
            storeButton.addActionListener(e -> {
            	List<String> owner = Select.SelectOwner(store.get(0));
            	openStoreInfo(owner, userid);
            	
            	});

            storePanel.add(storeButton, BorderLayout.CENTER);
            listPanel.add(storePanel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void openStoreInfo(List<String> owner, String userid) {
        SwingUtilities.invokeLater(() -> new StoreInfo(owner, userid).createAndShowGUI());
        frame.dispose();
        
    }

    
}
