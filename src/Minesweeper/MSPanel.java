package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MSPanel extends JPanel implements ActionListener {

    final int rows = 20;
    final int columns = 20;
    final int mineCount = 10;
    JButton spaces[][] = new JButton[rows][columns];
    int mines[][] = new int[rows][columns];
    JPanel grid = new JPanel();
    JLabel minesLabel = new JLabel();


    public MSPanel(){
        this.setLayout(new GridLayout(rows,columns));
        //spaces.addActionListener(this::actionPerformed);
        JButton but = new JButton();
        //but.addActionListener(this::actionPerformed);
        for(int i = 0; i < rows-1; i++){
            for(int j = 0; j < columns; j++){
                spaces[i][j] = new JButton("");
                spaces[i][j].addActionListener(this::actionPerformed);
                this.add(spaces[i][j]);
                mines[i][j] = 0;
            }
        }
        placeMines();
        determineMines();

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //System.out.println(mines[i][j]);
            }
        }

        this.add(grid);
        //Random rand = new Random();
        //System.out.println((int) ((rows * columns) * (rand.nextDouble())) + 1);
    }

    public void placeMines(){
        int mineNum[] = randomMineNum(rows, columns, mineCount);
        int pos = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                for(int g = 0; g < mineNum.length; g++){
                    if(pos == mineNum[g]){
                        mines[i][j] = 9;
                        //System.out.println(pos + "/" + i + "/" + j);
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
                //System.out.println(mine);
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
                //System.out.printf("Button pressed");
                if (actionEvent.getSource() == spaces[i][j]) {
                    //System.out.println(mines[i][j]);
                    int num = mines[i][j];
                    String number = String.valueOf(num);
                    spaces[i][j].setText(number);
                    spaces[i][j].setBackground(Color.white);
                    whiteSpace(i, j);
                }
            }
        }
    }

    public void whiteSpace(int i, int j) {
        String number;
        if (mines[i][j] == 9) {

        } else {
            if(mines[i][j] == 0) {
                if (i > 0 && j > 0) {
                    if (mines[i - 1][j - 1] != 0) {
                        number = String.valueOf(mines[i-1][j-1]);
                        spaces[i - 1][j - 1].setText(number);
                        spaces[i-1][j-1].setBackground(Color.white);
                    }else{
                        spaces[i-1][j-1].setText(String.valueOf(0));
                        spaces[i-1][j-1].setBackground(Color.white);
                        whiteSpace(i-1, j-1);
                    }
                }
                if (i > 0) {
                    if (mines[i - 1][j] != 0) {
                        number = String.valueOf(mines[i-1][j]);
                        spaces[i-1][j].setText(number);
                        spaces[i-1][j].setBackground(Color.white);
                    } else{
                        spaces[i-1][j].setText(String.valueOf(0));
                        spaces[i-1][j].setBackground(Color.white);
                        whiteSpace(i-1, j);
                    }
                }
                if (i > 0 && j < columns - 1) {
                    if (mines[i - 1][j + 1] != 0) {
                        number = String.valueOf(mines[i-1][j+1]);
                        spaces[i-1][j+1].setText(number);
                        spaces[i-1][j+1].setBackground(Color.white);
                    }else{
                        spaces[i-1][j+1].setText(String.valueOf(0));
                        spaces[i-1][j+1].setBackground(Color.white);
                        whiteSpace(i-1,j+1);
                    }
                }
                if (j > 0) {
                    if (mines[i][j - 1] != 0) {
                        number = String.valueOf(mines[i][j-1]);
                        spaces[i][j-1].setText(number);
                        spaces[i][j-1].setBackground(Color.white);
                    }else{
                        spaces[i][j-1].setText(String.valueOf(0));
                        spaces[i][j-1].setBackground(Color.white);
                        whiteSpace(i, j-1);
                    }
                }
                if (j < columns - 1) {
                    if (mines[i][j + 1] != 0) {
                        number = String.valueOf(mines[i][j + 1]);
                        spaces[i][j + 1].setText(number);
                        spaces[i][j + 1].setBackground(Color.white);
                    } else {
                        spaces[i][j + 1].setText(String.valueOf(0));
                        spaces[i][j + 1].setBackground(Color.white);
                        whiteSpace(i, j + 1);
                    }
                }
                if (i < rows - 1 && j > 0) {
                    if (mines[i + 1][j - 1] != 0) {
                        number = String.valueOf(mines[i+1][j-1]);
                        spaces[i+1][j-1].setText(number);
                        spaces[i+1][j-1].setBackground(Color.white);
                    }else{
                        spaces[i+1][j-1].setText(String.valueOf(0));
                        spaces[i+1][j-1].setBackground(Color.white);
                        whiteSpace(i+1, j-1);
                    }
                }

                if (i < rows - 1) {
                    if (mines[i + 1][j] != 0) {
                        number = String.valueOf(mines[i+1][j]);
                        spaces[i+1][j].setText(number);
                        spaces[i+1][j].setBackground(Color.white);

                    }else{
                        spaces[i+1][j].setText(String.valueOf(0));
                        spaces[i+1][j].setBackground(Color.white);
                        whiteSpace(i+1, j);
                    }
                }
                if (i < rows - 1 && j < columns - 1) {
                    if (mines[i + 1][j + 1] != 0) {
                        number = String.valueOf(mines[i+1][j+1]);
                        spaces[i+1][j+1].setText(number);
                        spaces[i+1][j+1].setBackground(Color.white);

                    }else{
                        spaces[i+1][j+1].setText(String.valueOf(0));
                        spaces[i+1][j+1].setBackground(Color.white);
                        whiteSpace(i+1, j+1);
                    }
                }
            }
        }
    }
}
