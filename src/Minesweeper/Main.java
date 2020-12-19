package Minesweeper;

import javax.swing.*;

public class Main {
    public static void main(String args[]){
        JFrame frame = new JFrame("Minesweeper");
        createNewFrame(frame);
    }

    public static void createNewFrame(JFrame frame2){
        frame2.dispose();
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MSPanel());
        frame.setSize(1200,1200);

        frame.setVisible(true);
    }
}
