package main_Bd;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        login loginPanel = new login(frame);
        frame.setContentPane(loginPanel.vistalogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
