import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreList {
    public StoreList(String category) {
        SwingUtilities.invokeLater(() -> createAndShowGUI(category));
    }

    private void createAndShowGUI(String category) {
        JFrame frame = new JFrame(category + " 가게 리스트");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(category + " 가게 리스트", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        ArrayList<String> stores = getStoresForCategory(category);

        for (String store : stores) {
            JPanel storePanel = new JPanel(new BorderLayout());
            storePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            storePanel.setBackground(Color.WHITE);

            JLabel storeLabel = new JLabel(store, SwingConstants.CENTER);
            storeLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
            storePanel.add(storeLabel, BorderLayout.CENTER);

            listPanel.add(storePanel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private ArrayList<String> getStoresForCategory(String category) {
        ArrayList<String> stores = new ArrayList<>();
        switch (category) {
            case "치킨":
                stores.add("교촌치킨");
                stores.add("BHC치킨");
                stores.add("BBQ치킨");
                break;
            case "피자":
                stores.add("피자헛");
                stores.add("도미노피자");
                stores.add("파파존스");
                break;
            // 다른 카테고리 추가
            default:
                stores.add("가게 리스트 없음");
        }
        return stores;
    }
}
