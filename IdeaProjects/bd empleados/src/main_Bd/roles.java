package main_Bd;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class roles {
    JPanel rols;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton REGISTRARButton;
    private JButton VOLERButton;

    public roles(JFrame rolesframe){
        connect();
        table_load();


        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resgistrar();
                table_load();

            }
        });
        VOLERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("empleados_bd");
                empleados_bd usuarios = new empleados_bd(frame);
                frame.setContentPane(usuarios.EMPLEADO);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                rolesframe.dispose();
            }
        });
    }

    Connection con;
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
    void table_load(){
        Statement pst;
        String sql = "SELECT * FROM roles";
        try {
            pst = con.createStatement();
            ResultSet st = pst.executeQuery(sql);
            table1.setModel(DbUtils.resultSetToTableModel(st));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    void resgistrar(){
        PreparedStatement ps;


        try {
            ps = con.prepareStatement("INSERT INTO roles (id,nombre) VALUES (?,?)");
            ps.setString(1,textField1.getText());
            ps.setString(2,textField2.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"añadido");
            textField1.setText("");
            textField2.setText("");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("roles");
        roles rol = new roles(frame);
        frame.setContentPane(rol.rols);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
