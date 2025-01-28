package main_Bd;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class consultarprp extends consutarpry {
    JPanel consultarprp;
    private JTable table1;
    private JTextField textField1;
    private JButton CONSULTARButton;
    private JButton VOLERButton;
    private JButton downloadPDFButton;

    public consultarprp(JFrame consultprp){
        connect();
        table_load();


        CONSULTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consulta();


            }
        });
        VOLERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("login");
                login loginPanel = new login(frame);
                frame.setContentPane(loginPanel.vistalogin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                consultprp.dispose();
            }
        });
        downloadPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    String pdfId = table1.getValueAt(selectedRow, 0).toString();
                    downloadPDF(pdfId);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un PDF para descargar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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


    public void table_load(){
        Statement pst;
        String sql = "SELECT id, programa, estado, nombre FROM proyectogrado";
        try {
            pst = con.createStatement();
            ResultSet st = pst.executeQuery(sql);
            table1.setModel(DbUtils.resultSetToTableModel(st));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void consulta(){
        PreparedStatement ps;
        String sql = "SELECT id, programa, estado, nombre FROM proyectogrado WHERE id=?";
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

     public void downloadPDF(String pdfId) {
        try {
            String query = "SELECT pdf FROM proyectogrado WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pdfId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                InputStream input = rs.getBinaryStream("pdf");

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("documento.pdf"));
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    FileOutputStream output = new FileOutputStream(fileToSave);

                    byte[] buffer = new byte[1024];
                    while (input.read(buffer) > 0) {
                        output.write(buffer);
                    }

                    input.close();
                    output.close();

                    JOptionPane.showMessageDialog(null, "PDF descargado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al descargar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("consultarprp");
        consultarprp consprp = new consultarprp(frame);
        frame.setContentPane(consprp.consultarprp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
