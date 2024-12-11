import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreMain {
    private String ownerId;

    public StoreMain(String ownerId) {
        this.ownerId = ownerId;

        JFrame frame = new JFrame("가게 계정 메인 페이지");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // 상단 패널: 가게 이름과 로그아웃 버튼 추가
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel storeLabel = new JLabel(getStoreName(ownerId), SwingConstants.CENTER);
        storeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        topPanel.add(storeLabel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        logoutButton.addActionListener(e -> logout(frame));
        topPanel.add(logoutButton, BorderLayout.EAST);

        // 탭 패널: 메뉴 수정, 주문 현황, 가게 정보
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("메뉴 수정", createMenuEditPanel());
        tabbedPane.addTab("주문 현황", createOrderPanel());
        tabbedPane.addTab("가게 정보", createInfoPanel());
        tabbedPane.addTab("메뉴 추가", createAddMenuPanel());

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createMenuEditPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("메뉴 수정 화면", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        // 메뉴 데이터를 가져와 테이블로 표시
        List<String[]> menuList = getMenuList(ownerId);
        String[] columnNames = {"메뉴 이름", "가격", "이미지 경로", "수정"};
        String[][] data = new String[menuList.size()][4];

        for (int i = 0; i < menuList.size(); i++) {
            String[] menu = menuList.get(i);
            data[i][0] = menu[0];
            data[i][1] = menu[1];
            data[i][2] = menu[2];
            data[i][3] = "수정";
        }

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // "수정" 버튼만 클릭 가능
            }
        };

        // 수정 버튼 동작 구현
        table.getColumn("수정").setCellRenderer(new ButtonRenderer());
        table.getColumn("수정").setCellEditor(new ButtonEditor(new JCheckBox(), ownerId, menuList, table));

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("주문 현황 화면", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        // 데이터베이스에서 주문 데이터를 가져와 테이블로 표시
        List<String[]> orderList = getOrderList(ownerId);
        String[] columnNames = {"메뉴 이름", "수량", "총 금액", "주문 날짜"};
        String[][] data = orderList.toArray(new String[0][]);

        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("가게 정보 화면", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        // 가게 정보 표시
        String[] storeInfo = getStoreInfo(ownerId);
        JTextArea infoArea = new JTextArea(String.join("\n", storeInfo));
        infoArea.setEditable(false);
        infoArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        return panel;
    }
    
    private JPanel createAddMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("메뉴 추가");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격 추가

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("메뉴 이름:");
        JTextField nameField = new JTextField(15);

        JLabel priceLabel = new JLabel("가격:");
        JTextField priceField = new JTextField(15);

        JLabel imgLabel = new JLabel("이미지 경로:");
        JTextField imgField = new JTextField(15);
        JButton imgButton = new JButton("파일 선택");
        imgButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지 파일 (JPG, PNG)", "jpg", "png");
            chooser.setFileFilter(filter);
            int result = chooser.showOpenDialog(panel);
            if (result == JFileChooser.APPROVE_OPTION) {
                imgField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(imgLabel);
        inputPanel.add(imgButton);

        panel.add(inputPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격 추가

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("메뉴 추가");
        buttonPanel.add(addButton);

        addButton.addActionListener(e -> {
            String menuName = nameField.getText().trim();
            String priceText = priceField.getText().trim();
            String imgPath = imgField.getText().trim();

            if (menuName.isEmpty() || priceText.isEmpty() || imgPath.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int price = Integer.parseInt(priceText);

                // DB에 메뉴 추가
                addMenuToDatabase(ownerId, menuName, price, imgPath);
                JOptionPane.showMessageDialog(panel, "메뉴가 성공적으로 추가되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);

                // 입력 필드 초기화
                nameField.setText("");
                priceField.setText("");
                imgField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "가격은 숫자로 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(buttonPanel);
        return panel;
    }



    private void addMenuToDatabase(String ownerId, String menuName, int price, String imgPath) {
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO menu (ownerid, menuname, price, menuimg) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, ownerId);
            stmt.setString(2, menuName);
            stmt.setInt(3, price);
            stmt.setString(4, imgPath);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "메뉴 추가 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getStoreName(String ownerId) {
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT storename FROM owner WHERE ownerid = ?")) {
            stmt.setString(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("storename");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "가게 이름";
    }

    private List<String[]> getMenuList(String ownerId) {
        List<String[]> menuList = new ArrayList<>();
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT menuid, menuname, price, menuimg FROM menu WHERE ownerid = ?")) {
            stmt.setString(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                menuList.add(new String[]{
                        rs.getString("menuname"),
                        String.valueOf(rs.getInt("price")),
                        rs.getString("menuimg"),
                        String.valueOf(rs.getInt("menuid")) // 메뉴 ID
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    private List<String[]> getOrderList(String ownerId) {
        List<String[]> orderList = new ArrayList<>();
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT menu, count, total, date FROM storder WHERE ownerid = ?")) {
            stmt.setString(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderList.add(new String[]{
                        rs.getString("menu"),
                        String.valueOf(rs.getInt("count")),
                        String.valueOf(rs.getInt("total")),
                        rs.getString("date")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    private String[] getStoreInfo(String ownerId) {
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM owner WHERE ownerid = ?")) {
            stmt.setString(1, ownerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{
                        "가게 이름: " + rs.getString("storename"),
                        "주소: " + rs.getString("adress"),
                        "카테고리: " + rs.getString("category"),
                        "가게 이미지: " + rs.getString("storeimg")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[]{"가게 정보를 가져올 수 없습니다."};
    }

    private void logout(JFrame parentFrame) {
        int confirm = JOptionPane.showConfirmDialog(parentFrame, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            parentFrame.dispose(); // 현재 프레임 닫기
            SwingUtilities.invokeLater(LoginSignupPage::new); // 로그인 화면으로 이동
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreMain("bbbb")); // 테스트용 ownerId 전달
    }
}

// 테이블의 버튼 렌더러
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

// 테이블의 버튼 에디터
class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private String ownerId;
    private List<String[]> menuList;

    public ButtonEditor(JCheckBox checkBox, String ownerId, List<String[]> menuList, JTable table) {
        super(checkBox);
        this.ownerId = ownerId;
        this.menuList = menuList;
        this.table = table;

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            int menuId = Integer.parseInt(menuList.get(row)[3]);
            String menuName = menuList.get(row)[0];
            String price = menuList.get(row)[1];
            String imgPath = menuList.get(row)[2];

            // 수정 로직 다이얼로그로 구현
            JDialog dialog = new JDialog((Frame) null, "메뉴 수정", true);
            dialog.setLayout(new GridLayout(4, 2, 10, 10));
            dialog.setSize(300, 200);

            dialog.add(new JLabel("메뉴 이름:"));
            JTextField nameField = new JTextField(menuName);
            dialog.add(nameField);

            dialog.add(new JLabel("가격:"));
            JTextField priceField = new JTextField(price);
            dialog.add(priceField);

            dialog.add(new JLabel("이미지 경로:"));
            JTextField imgField = new JTextField(imgPath);
            dialog.add(imgField);

            JButton saveButton = new JButton("저장");
            saveButton.addActionListener(ev -> {
                try {
                    String updatedName = nameField.getText();
                    int updatedPrice = Integer.parseInt(priceField.getText());
                    String updatedImg = imgField.getText();

                    // DB 업데이트
                    updateMenuInDatabase(menuId, updatedName, updatedPrice, updatedImg);

                    // 테이블 갱신
                    table.setValueAt(updatedName, row, 0);
                    table.setValueAt(String.valueOf(updatedPrice), row, 1);
                    table.setValueAt(updatedImg, row, 2);

                    dialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "올바른 입력값을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(saveButton);

            dialog.setVisible(true);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText((value == null) ? "" : value.toString());
        return button;
    }

    private void updateMenuInDatabase(int menuId, String menuName, int price, String menuImg) {
        try (Connection conn = DBconnecter.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE menu SET menuname = ?, price = ?, menuimg = ? WHERE menuid = ?")) {

            stmt.setString(1, menuName);
            stmt.setInt(2, price);
            stmt.setString(3, menuImg);
            stmt.setInt(4, menuId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "메뉴가 성공적으로 수정되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "메뉴 수정 중 오류가 발생했습니다.");
        }
    }
}
