package main_Bd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ingreso {
   JPanel ingres;
    private JButton REGISTRARButton;
    private JButton PROYECTOSDEGRADOButton;
    private JLabel lblimage;

    public  ingreso(JFrame ingresoframe){

        loadImage();

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("empleados_bd");
                empleados_bd usuarios = new empleados_bd(frame);
                frame.setContentPane(usuarios.EMPLEADO);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);
                ingresoframe.dispose();

            }
        });
        PROYECTOSDEGRADOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("consultarprp");
                consultarprp consprp = new consultarprp(frame);
                frame.setContentPane(consprp.consultarprp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                ingresoframe.dispose();
            }
        });
    }

    public void loadImage() {
        String imagePath = "C:\\Users\\kbuen\\IdeaProjects\\bd empleados\\administrativo.png";
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
        JFrame frame = new JFrame("ingreso");
        ingreso ingresopanel = new ingreso(frame);
        frame.setContentPane(ingresopanel.ingres);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
