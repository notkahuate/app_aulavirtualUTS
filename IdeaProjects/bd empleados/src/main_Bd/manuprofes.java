package main_Bd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class manuprofes {
   JPanel profes;
    private JButton INGRESARESTUDIANTEButton;
    private JButton PROYECTOGRADOButton;
    private JLabel lblimage;

    public manuprofes(JFrame menu){

        loadImage();

        INGRESARESTUDIANTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ingresarest");
                ingresarest ingest = new ingresarest(frame);
                frame.setContentPane(ingest.ing2);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);
                menu.dispose();
            }
        });
        PROYECTOGRADOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("consultarprp");
                consultarprp consprp = new consultarprp(frame);
                frame.setContentPane(consprp.consultarprp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);
                menu.dispose();
            }
        });
    }

    public void loadImage() {
        String imagePath = "C:\\Users\\kbuen\\IdeaProjects\\bd empleados\\profe.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo cargar la imagen en " + imagePath);
        } else {
            Image img = imageIcon.getImage();
            Image myimage = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon myicon = new ImageIcon(myimage);
            lblimage.setIcon(myicon);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("manuprofes");
        manuprofes menpr = new manuprofes(frame);
        frame.setContentPane(menpr.profes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
