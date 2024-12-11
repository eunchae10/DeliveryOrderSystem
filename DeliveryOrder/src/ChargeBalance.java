import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChargeBalance {
    private static int userBalance = 0; // 사용자 잔액
    private static List<BalanceChangeListener> listeners = new ArrayList<>(); // Listeners

    // 중첩 인터페이스 (BalanceChangeListener 추가)
    public interface BalanceChangeListener {
        void onBalanceChanged(int newBalance);
    }

    // Listener 등록 메서드
    public static void addBalanceChangeListener(BalanceChangeListener listener) {
        listeners.add(listener);
    }

    // Listener 알림 메서드
    private static void notifyBalanceChange(int newBalance) {
        for (BalanceChangeListener listener : listeners) {
            listener.onBalanceChanged(newBalance);
        }
    }

    // 잔액 충전 메서드
    public static void charge(JFrame parentFrame, String userid, int selectUserMoney) {
        userBalance = selectUserMoney;
        String amountStr = JOptionPane.showInputDialog(parentFrame, "충전할 금액을 입력하세요:", "잔액 충전", JOptionPane.PLAIN_MESSAGE);
        try {
            int amount = Integer.parseInt(amountStr);
            if (amount > 0) {
                userBalance += amount;
                UpdateInformation.AddUserMoney(userid, userBalance); // 데이터베이스 업데이트
                notifyBalanceChange(userBalance); // UI 업데이트 알림
                JOptionPane.showMessageDialog(parentFrame, amount + "원이 충전되었습니다!\n현재 잔액: " + userBalance + "원", "충전 완료", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "0원 이상을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentFrame, "유효한 숫자를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 현재 잔액 반환
    public static int getUserBalance(String userid) {
        userBalance = Select.SelectUserMoney(userid);
        return userBalance;
    }
}
