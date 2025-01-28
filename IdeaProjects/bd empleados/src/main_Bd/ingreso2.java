package main_Bd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ingreso2 {
     JPanel ing2;
    private JButton PROYECTODEGRADOButton;
    private JButton mypdfButton;
    private JLabel lblimage;

    public ingreso2(JFrame ingreso_2){
        loadImage();


        PROYECTODEGRADOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("proyctgrado");
                proyctgrado proyec = new proyctgrado(frame);
                frame.setContentPane(proyec.proyecto);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                ingreso_2.dispose();
            }
        });

        mypdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = "current_student_id"; // Replace with actual student ID
                JFrame frame = new JFrame("visualizarpdf");
                visualizarpdf visualizar = new visualizarpdf(frame, studentId);
                frame.setContentPane(visualizar.visu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450, 300));
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void loadImage() {
        String imagePath = "C:\\Users\\kbuen\\IdeaProjects\\bd empleados\\est.jpg";
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
        JFrame frame = new JFrame("ingreso2");
        ingreso2 ing2 = new ingreso2(frame);
        frame.setContentPane(ing2.ing2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
