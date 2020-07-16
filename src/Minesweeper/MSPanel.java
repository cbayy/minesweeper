package Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MSPanel extends JPanel implements ActionListener {

    final int rows = 20;
    final int columns = 20;
    JButton spaces[][];
    int mines[][];

    public MSPanel(){
        this.setLayout(new GridLayout(rows,columns));
        spaces = new JButton[rows][columns];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                spaces[i][j] = new JButton("");
                this.add(spaces[i][j]);
                System.out.println(i*j);
            }
        }


    }

    public void placeMines(){

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
