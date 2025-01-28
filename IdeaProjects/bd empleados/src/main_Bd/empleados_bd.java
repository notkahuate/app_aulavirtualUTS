package main_Bd;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

public class empleados_bd {
    private  JTextField textField1;
    private  JTextField textField2;
    private  JTextField textField3;
    private  JTextField textField4;
    private JButton REGISTRARButton;
    private JButton BORRARButton;
    private JTable table1;
     JPanel EMPLEADO;
    private JButton CONSULTARButton;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox1;
    private JButton ROLESButton;
    private JButton VOLVERButton;


    public empleados_bd(JFrame empleado_bdframe){
        connect();
        table_load();

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resgistrar();
                table_load();



            }
        });

        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              borrar();
                table_load();
            }
        });

        CONSULTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consulta();


            }
        });
        ROLESButton.addComponentListener(new ComponentAdapter() {
        });
        ROLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("roles");
                roles rol = new roles(frame);
                frame.setContentPane(rol.rols);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                empleado_bdframe.dispose();
            }
        });
        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ingreso");
                ingreso ingresopanel = new ingreso(frame);
                frame.setContentPane(ingresopanel.ingres);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);
                
                empleado_bdframe.dispose();
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
    void borrar(){
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setString(1,textField1.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"borrado");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    void resgistrar(){
        PreparedStatement ps;


        try {
            ps = con.prepareStatement("INSERT INTO users (NOMBRE,APELLIDO,ROL,MAIL,PASS,TELEFONO) VALUES (?,?,?,?,?,?)");
            ps.setString(1,textField2.getText());
            ps.setString(2,textField3.getText());
            ps.setString(3,comboBox1.getSelectedItem().toString());
            ps.setString(4,textField4.getText());
            ps.setString(5,textField5.getText());
            ps.setString(6,textField6.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"añadido");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField6.setText("");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    void table_load(){
        Statement pst;
        String sql = "SELECT * FROM users";
        try {
            pst = con.createStatement();
            ResultSet st = pst.executeQuery(sql);
            table1.setModel(DbUtils.resultSetToTableModel(st));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void consulta(){
        PreparedStatement ps;
        String sql = "SELECT * FROM users WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,textField1.getText());
            ResultSet st = ps.executeQuery();
            JOptionPane.showMessageDialog(null,"consulta");
            textField1.setText("");
            table1.setModel(DbUtils.resultSetToTableModel(st));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }




    public static void main(String[] args) {
        JFrame frame = new JFrame("empleados_bd");
        empleados_bd usuarios = new empleados_bd(frame);
        frame.setContentPane(usuarios.EMPLEADO);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
