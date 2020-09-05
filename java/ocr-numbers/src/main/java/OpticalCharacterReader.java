import java.util.HashMap;
import java.util.List;

public class OpticalCharacterReader {

    public static final int ROWS = 4;
    public static final int COLS = 3;

    private static final HashMap<String, Character> inputLinesToChar = new HashMap<>();
    static {
        char[][] inputLines = new char[][] {
            " _     _  _     _  _  _  _  _ ".toCharArray(),
            "| |  | _| _||_||_ |_   ||_||_|".toCharArray(),
            "|_|  ||_  _|  | _||_|  ||_| _|".toCharArray(),
            "                              ".toCharArray()
        };
        String[][] numbers = getBlocksFromInputLines(inputLines);
        for (int n = 0; n < numbers[0].length; n++) {
            inputLinesToChar.put(numbers[0][n], (char)(n+(int)'0'));
        }
    }

    private static String[][] getBlocksFromInputLines(char[][] inputLines) {
        int rows = inputLines.length / ROWS;
        int cols = inputLines[0].length / COLS;
        String[][] result = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char[] chars = new char[ROWS * COLS];
                for (int rowOffset = 0; rowOffset < ROWS; rowOffset++) {
                    System.arraycopy(
                        inputLines[r * ROWS + rowOffset],
                        c * COLS,
                        chars,
                        rowOffset * COLS,
                        COLS
                    );
                }
                result[r][c] = new String(chars);
            }
        }

        return result;
    }

    String parse(List<String> inputLines) {
        if (inputLines.size() % ROWS > 0) {
            throw new IllegalArgumentException(
                "Number of input rows must be a positive multiple of " + ROWS
            );
        }

        if (inputLines.get(0).length() % COLS > 0) {
            throw new IllegalArgumentException(
                "Number of input columns must be a positive multiple of " + COLS
            );
        }

        int rows = inputLines.size() / ROWS;
        int cols = inputLines.get(0).length() / COLS;

        // Convert inputLines to 2D character sheet
        char[][] inputLinesChars = new char[inputLines.size()][];
        for (int i = 0; i < inputLines.size(); i++) {
            inputLinesChars[i] = inputLines.get(i).toCharArray();
        }

        // Get number strings in each cell
        String[][] numberStrings = getBlocksFromInputLines(inputLinesChars);

        char[] chars = new char[rows * cols + rows - 1];
        int charIndex = 0;
        // For each cell, row by col, get character (or '?') of number and add to char array
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Character numChar = inputLinesToChar.get(numberStrings[r][c]);
                chars[charIndex++] = (numChar == null) ? '?' : numChar;
            }
            if (r != rows - 1) {
                chars[charIndex++] = ',';
            }
        }

        return new String(chars);
    }
}
