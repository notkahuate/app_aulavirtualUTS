package main_Bd;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ingresarest {
    private JTable table1;
    private JButton CONSULTARButton;
    private JButton BORRARButton;
    private JButton REGISTRARButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox1;
    JPanel ing2;
    private JButton VOLVERButton;

    Connection con;

    public ingresarest(JFrame ingresarest){
        connect();
        table_load();
        CONSULTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consulta();
            }
        });
        BORRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrar();
                table_load();
            }
        });
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resgistrar();
                table_load();
            }
        });
        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("manuprofes");
                manuprofes menpr = new manuprofes(frame);
                frame.setContentPane(menpr.profes);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                ingresarest.dispose();
            }
        });
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
    void borrar(){
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setString(1,textField1.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"borrado");
            textField1.setText("");


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
            ps.setString(4,textField5.getText());
            ps.setString(5,textField6.getText());
            ps.setString(6,textField7.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"añadido");
            textField2.setText("");
            textField3.setText("");
            textField5.setText("");
            textField6.setText("");
            textField7.setText("");
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
        JFrame frame = new JFrame("ingresarest");
        ingresarest ingest = new ingresarest(frame);
        frame.setContentPane(ingest.ing2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
