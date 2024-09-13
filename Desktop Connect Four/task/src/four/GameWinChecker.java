package four;

import javax.swing.*;
import java.awt.*;

public class GameWinChecker {

    // Direction vectors for horizontal, vertical, and diagonal directions
    private static final int[][] DIRECTIONS = {
            {0, 1},  // Horizontal right
            {1, 0},  // Vertical down
            {1, 1},  // Diagonal down-right
            {1, -1}  // Diagonal down-left
    };

    // Method to check if there are four consecutive same values (either "X" or "O")
    public static boolean hasFourConsecutive(JButton[][] buttons) {
        int rows = buttons.length;
        int cols = buttons[0].length;

        // Loop through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                String value = buttons[row][col].getText();

                if (!value.equals(" ")) {  // Skip empty cells
                    // Check all directions from the current cell
                    for (int[] direction : DIRECTIONS) {
                        if (checkDirection(buttons, row, col, direction[0], direction[1])) {
                            return true;  // Found four consecutive values
                        }
                    }
                }
            }
        }

        // No four consecutive values found
        return false;
    }

    // Helper method to check for four consecutive values in a given direction
    private static boolean checkDirection(JButton[][] buttons, int row, int col, int rowDir, int colDir) {
        String value = buttons[row][col].getText();
        JButton[] winningButtons = new JButton[4];  // Array to store the winning buttons
        winningButtons[0] = buttons[row][col];

        int rows = buttons.length;
        int cols = buttons[0].length;

        // Check the next three buttons in the specified direction
        for (int i = 1; i < 4; i++) {
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;

            if (newRow >= rows || newRow < 0 || newCol >= cols || newCol < 0) {
                return false;  // Out of bounds
            }

            if (!value.equals(buttons[newRow][newCol].getText())) {
                return false;  // Not the same value
            }

            winningButtons[i] = buttons[newRow][newCol];  // Add to winning buttons
        }

        // If we get here, we found four consecutive matching values
        changeColorOfWinningButtons(winningButtons);
        disableAllGridButtons(buttons);
        return true;
    }

    // Method to change the color of the winning buttons
    public static void changeColorOfWinningButtons(JButton... winningButtons) {
        for (JButton button : winningButtons) {
            button.setBackground(Color.RED);
        }
    }

    // Method to disable all buttons in the grid
    private static void disableAllGridButtons(JButton[][] buttons) {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setEnabled(false);
            }
        }
    }
}
