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

public class visualizarpdf {
     JPanel visu;
    private JTable table1;
    private JButton volverButton;
    private JButton downloadPDFButton;


    public visualizarpdf(JFrame viz, String studentId) {
        connect();
        table_load();

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("login");
                login loginPanel = new login(frame);
                frame.setContentPane(loginPanel.vistalogin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450, 300));
                frame.pack();
                frame.setVisible(true);
                viz.dispose();
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

    public void connect() {
        String url = "jdbc:mysql://localhost/usuarios";
        String user = "root";
        String pass = "";

        JOptionPane.showMessageDialog(null, "conectando.....");

        try {
            con = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "conexión exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    void table_load() {
        try {
            String sql = "SELECT id, programa, estado, nombre FROM proyectogrado";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void downloadPDF(String pdfId) {
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
        String studentId = "current_student_id";
        JFrame frame = new JFrame("visualizarpdf");
        visualizarpdf visualizar = new visualizarpdf(frame, studentId);
        frame.setContentPane(visualizar.visu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450, 300));
        frame.pack();
        frame.setVisible(true);
    }
}
