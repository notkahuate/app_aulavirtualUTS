package main_Bd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JPanel {
    JPanel vistalogin;
    private JTextField textField1;
    private JButton INGRESARButton;
    private JPasswordField passwordField1;
    private JLabel lblimage;

    PreparedStatement ps;
    Connection con;
    public login(JFrame loginFrame){



        connect();
        loadImage();

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {


                    ps = con.prepareStatement("SELECT * FROM users WHERE MAIL=? AND PASS=? AND ROL=1");
                    ps.setString(1,textField1.getText());
                    ps.setString(2,passwordField1.getText());
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()){
                        JFrame frame = new JFrame("ingreso");
                        ingreso ingresopanel = new ingreso(frame);
                        frame.setContentPane(ingresopanel.ingres);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setMinimumSize(new Dimension(450,300));
                        frame.pack();
                        frame.setVisible(true);
                        JOptionPane.showMessageDialog(null,"ingresado");

                        loginFrame.dispose();

                    }
                    else {
                        ps = con.prepareStatement("SELECT * FROM users WHERE MAIL=? AND PASS=? AND ROL=2");
                        ps.setString(1,textField1.getText());
                        ps.setString(2,passwordField1.getText());
                        ResultSet rsT = ps.executeQuery();

                        if(rsT.next()){

                            JFrame frame = new JFrame("manuprofes");
                            manuprofes menpr = new manuprofes(frame);
                            frame.setContentPane(menpr.profes);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setMinimumSize(new Dimension(450,300));
                            frame.pack();
                            frame.setVisible(true);

                            loginFrame.dispose();

                        }
                        else {

                            ps = con.prepareStatement("SELECT * FROM users WHERE MAIL=? AND PASS=? AND ROL=3");
                            ps.setString(1,textField1.getText());
                            ps.setString(2,passwordField1.getText());
                            ResultSet rsA = ps.executeQuery();

                            if(rsA.next()){
                                JFrame frame = new JFrame("ingreso2");
                                ingreso2 ing2 = new ingreso2(frame);
                                frame.setContentPane(ing2.ing2);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setMinimumSize(new Dimension(450,300));
                                frame.pack();
                                frame.setVisible(true);
                                JOptionPane.showMessageDialog(null,"ingresado");

                                loginFrame.dispose();

                            }
                            else {
                                JOptionPane.showMessageDialog(null,"INGRESO NO EXITOSO");

                            }


                        }


                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }



            }
        });
    }
    public void loadImage() {
        String imagePath = "C:\\Users\\kbuen\\IdeaProjects\\bd empleados\\UTSescudo.jpg";
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

    PreparedStatement pst;
    public void connect(){
        String url = "jdbc:mysql://localhost/usuarios";
        String user = "root";
        String pass = "";

        JOptionPane.showMessageDialog(null,"coentando.....");

        try {
            con = DriverManager.getConnection(url,user,pass);
            JOptionPane.showMessageDialog(null,"conexion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }


    }

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
