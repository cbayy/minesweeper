package Minesweeper;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.util.Random;

public class MSPanel extends JPanel implements ActionListener {

    final int rows = 20;
    final int columns = 20;
    final int mineCount = 50;
    int remainingMines = mineCount;
    JButton spaces[][] = new JButton[rows][columns];
    int mines[][] = new int[rows][columns];
    JLabel minesLabel = new JLabel("Mines: " + remainingMines);


    public MSPanel(){
        this.setLayout(new BorderLayout());
        this.add(minesLabel,  BorderLayout.NORTH);
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(rows,columns));
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                spaces[i][j] = new JButton("");
                spaces[i][j].addActionListener(this::actionPerformed);
                final int fI = i;
                final int fJ = j;
                spaces[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            if (spaces[fI][fJ].getText().equals("F")) {
                                spaces[fI][fJ].setText("");
                                remainingMines++;
                                minesLabel.setText("Mines: " + remainingMines);
                            } else {
                                spaces[fI][fJ].setText("F");
                                remainingMines--;
                                minesLabel.setText("Mines: " + remainingMines);
                            }
                        }
                    }
                });
                grid.add(spaces[i][j]);
                mines[i][j] = 0;
            }
        }
        placeMines();
        determineMines();
        this.add(grid, BorderLayout.CENTER);
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
            int mine = randomMine(rows, columns);
            containsMine = isOccupied(mineNum, mine);
            if(containsMine == false){
                mineNum[k] = mine;
                System.out.println("Not replaced");
            }
            if(containsMine == true){
                while(containsMine == true){
                    mine = randomMine(rows, columns);
                    containsMine = isOccupied(mineNum, mine);
                    System.out.println("REPLACED MINE");
                }

            }
                k++;
        }
        return mineNum;
    }

    public int randomMine(int row, int columns){
        Random random = new Random();
        int mine;
        return mine = (int)(random.nextDouble() * (rows * columns))+1;
    }

    public boolean isOccupied(int mineNum[], int mine) {
        for (int i = 0; i < mineNum.length; i++) {
            if (mine == mineNum[i]) {
                return true;
            }
        }
        return false;
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
