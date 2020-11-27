package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MSPanel extends JPanel implements ActionListener {

    final int rows = 20;
    final int columns = 20;
    final int mineCount = 50;
    JButton spaces[][] = new JButton[rows][columns];
    int mines[][] = new int[rows][columns];
    JLabel minesLabel = new JLabel();


    public MSPanel(){
        this.setLayout(new GridLayout(rows,columns));
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                spaces[i][j] = new JButton("");
                spaces[i][j].addActionListener(this::actionPerformed);
                this.add(spaces[i][j]);
                mines[i][j] = 0;
            }
        }
        placeMines();
        determineMines();
    }

    public void placeMines(){
        int mineNum[] = randomMineNum(rows, columns, mineCount);
        int pos = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                for(int g = 0; g < mineNum.length; g++){
                    if(pos == mineNum[g]){
                        mines[i][j] = 9;
                    }
                }
                pos++;
            }
        }
    }

    public int[] randomMineNum(int rows, int columns, int mineCount){
        int mineNum[] = new int[mineCount];
        Boolean containsMine = false;
        int k = 0;
        while(k < mineCount){
            containsMine = false;
            Random random = new Random();
            int mine = (int)(random.nextDouble() * (rows * columns))+1;
            for(int i = 0; i < mineNum.length; i++) {
                if (mine == mineNum[i]) {
                    containsMine = true;
                }
            }
            if(containsMine == false){
                mineNum[k] = mine;
            }
                k++;
        }
        return mineNum;
    }

    //Adds numbers to the positions around mines
    public void determineMines(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int mineCount = 0;
                if(mines[i][j] == 9){

                }else {
                    if(i > 0 && j > 0) {
                        if (mines[i - 1][j - 1] == 9) {
                            mineCount++;
                        }
                    }
                    if(i > 0) {
                        if (mines[i - 1][j] == 9) {
                            mineCount++;
                        }
                    }
                    if(i > 0 && j < columns-1) {
                        if (mines[i - 1][j + 1] == 9) {
                            mineCount++;
                        }
                    }
                    if(j > 0) {
                        if (mines[i][j - 1] == 9) {
                            mineCount++;
                        }
                    }
                    if(j < columns-1) {
                        if (mines[i][j + 1] == 9) {
                            mineCount++;
                        }
                    }
                    if(i < rows-1 && j > 0) {
                        if (mines[i + 1][j - 1] == 9) {
                            mineCount++;
                        }
                    }
                    if(i < rows-1) {
                        if (mines[i + 1][j] == 9) {
                            mineCount++;
                        }
                    }
                    if(i < rows-1 && j < columns-1) {
                        if (mines[i + 1][j + 1] == 9) {
                            mineCount++;
                        }
                    }
                        mines[i][j] = mineCount;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (actionEvent.getSource() == spaces[i][j]) {
                    revealTile(i, j);
                }
            }
        }
    }

    public void revealTile(int i, int j) {
        String number = String.valueOf(mines[i][j]);
        spaces[i][j].setText(number);
        spaces[i][j].setBackground(Color.WHITE);

        if (mines[i][j] == 0) {
            revealNeighbours(i, j);
        }
    }

    public void revealNeighbours(int i, int j) {
        int xMin = Math.max(0, j - 1);
        int xMax = Math.min(columns - 1, j + 1);
        int yMin = Math.max(0, i - 1);
        int yMax = Math.min(rows - 1, i + 1);

        for (int col = xMin; col <= xMax; col++) {
            for (int row = yMin; row <= yMax; row++) {
                // TODO:
                // Have a system for checking if a tile is revealed.
                // Currently, it is checked by testing to see if the button is blank (unrevealed)
                // My recommendation:
                // Separate the logic of the game into a new class, "GameState" for example.
                // In this class, have your LOGIC fields (everything that's not buttons, JPanels, etc.)
                // and when an action is performed on this JPanel, communicate that to the logic class.
                // For displaying to the screen, have this class query the GameLogic instance.
                // That way, the game doesn't care that the rendering implementation uses swing or buttons, and
                // could be replaced with JavaFX with no issues
                if (!(col == j && row == i) && spaces[row][col].getText().isBlank()) {
                    revealTile(row, col);
                }
            }
        }
    }
}
