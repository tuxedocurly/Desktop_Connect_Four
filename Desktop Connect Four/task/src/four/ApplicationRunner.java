package four;

import javax.swing.*;

public class ApplicationRunner {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ConnectFour board = new ConnectFour();
            board.setVisible(true);
        });
    }
}