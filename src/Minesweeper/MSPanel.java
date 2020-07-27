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

    public MSPanel(){
        this.setLayout(new GridLayout(rows,columns));
        //spaces.addActionListener(this::actionPerformed);
        JButton but = new JButton();
        //but.addActionListener(this::actionPerformed);

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                spaces[i][j] = new JButton("");
                spaces[i][j].addActionListener(this::actionPerformed);
                this.add(spaces[i][j]);
                mines[i][j] = 0;
            }
        }
        placeMines();
        Random rand = new Random();
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
                        System.out.println(pos + "/" + i + "/" + j);
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



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //for(int i = 0; i < rows; i++) {
          //  for (int j = 0; j < columns; j++)
        System.out.printf("Button pressed");
                if(actionEvent.getSource() == spaces[0][0]){
                    System.out.println("LL");
            }
        //}
    }

}
