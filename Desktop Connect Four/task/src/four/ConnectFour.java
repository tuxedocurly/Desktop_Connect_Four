package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFour extends JFrame {
    private final JButton[][] gridButtons;
    private final int[] columnTracker;
    private boolean playerOneTurn = true;

    // Constants for grid size and colors
    private static final int NUM_GRID_COLS = 7;
    private static final int NUM_GRID_ROWS = 6;
    private static final Color DARK_GREEN = new Color(128, 175, 89);
    private static final Color LIGHT_GREEN = new Color(128, 175, 89);
    private static final Color RESET_BUTTON_COLOR = Color.ORANGE;
    private boolean buttonGridColor = false;

    public ConnectFour() {
        // Initialize window properties
        setTitle("Connect Four");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize grid buttons and column tracker
        gridButtons = new JButton[NUM_GRID_ROWS][NUM_GRID_COLS];
        columnTracker = new int[NUM_GRID_COLS];

        // Add the grid panel to the center
        add(createGridPanel(), BorderLayout.CENTER);

        // Add the reset button to the bottom
        add(createResetButtonPanel(), BorderLayout.SOUTH);

        // Set visible after components are added
        setVisible(true);
    }

    // Create the grid panel with buttons
    private JPanel createGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(NUM_GRID_ROWS, NUM_GRID_COLS, 0, 0));

        for (int row = 0, i = NUM_GRID_ROWS; row < NUM_GRID_ROWS; row++, i--) {
            for (int col = 0, c = 'A'; col < NUM_GRID_COLS; col++, c++) {
                columnTracker[col] = NUM_GRID_ROWS - 1;
                gridButtons[row][col] = createGridButton(col, c, i);
                gridPanel.add(gridButtons[row][col]);
            }
        }

        return gridPanel;
    }

    // Create individual buttons for the grid
    private JButton createGridButton(int col, int columnChar, int rowNum) {
        JButton button = new JButton(" ");
        button.setFocusable(false);
        button.setName("Button" + Character.toString(columnChar) + rowNum);
        setButtonBackground(button);  // Set initial button color
        button.addActionListener(new ColumnButtonListener(col, this));
        return button;
    }

    // Method to handle column clicks
    public void handleColumnClick(int column) {
        int row = columnTracker[column];

        if (row >= 0) {
            // Set the button text to "X" or "O"
            gridButtons[row][column].setText(playerOneTurn ? "X" : "O");
            columnTracker[column]--;
            playerOneTurn = !playerOneTurn;

            // Check for win after the move
            if (GameWinChecker.hasFourConsecutive(gridButtons)) {
                System.out.println("We have a winner!");
            }
        } else {
            //JOptionPane.showMessageDialog(this, "This column is full. Choose another column.");
        }
    }

    // Method to reset the game
    private void resetGame() {
        for (int row = 0; row < NUM_GRID_ROWS; row++) {
            for (int col = 0; col < NUM_GRID_COLS; col++) {
                columnTracker[col] = NUM_GRID_ROWS - 1;
                gridButtons[row][col].setText(" ");
                gridButtons[row][col].setEnabled(true);
                setButtonBackground(gridButtons[row][col]);  // Reset button color
            }
        }
        playerOneTurn = true;
    }

    // Helper method to set alternating button background colors
    private void setButtonBackground(JButton button) {
        button.setBackground(buttonGridColor ? DARK_GREEN : LIGHT_GREEN);
        buttonGridColor = !buttonGridColor;
    }

    // Create the reset button panel
    private JPanel createResetButtonPanel() {
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setPreferredSize(new Dimension(100, 30));
        resetButton.setBackground(RESET_BUTTON_COLOR);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetGame());

        JPanel resetButtonPanel = new JPanel();
        resetButtonPanel.add(resetButton);
        return resetButtonPanel;
    }
}
