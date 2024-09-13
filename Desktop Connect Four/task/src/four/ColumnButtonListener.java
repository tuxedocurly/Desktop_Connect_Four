package four;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColumnButtonListener implements ActionListener {

    private int column;
    private ConnectFour board;

    public ColumnButtonListener(int col, ConnectFour board) {
        this.column = col;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Delegate the action to the board to handle the column click
        board.handleColumnClick(column);
    }
}
