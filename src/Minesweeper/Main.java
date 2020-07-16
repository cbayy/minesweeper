package Minesweeper;

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MSPanel());
        frame.setSize(600,600);

        frame.setVisible(true);

    }
}
