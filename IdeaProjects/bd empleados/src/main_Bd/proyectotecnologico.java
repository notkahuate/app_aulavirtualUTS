package main_Bd;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class proyectotecnologico {
    private JTextField textField1;
    private JTextField textField2;
    private JButton SUBIRButton;
    private JButton PDFButton;
     JPanel PROYECT;
    File selectedFile;
     public proyectotecnologico(JFrame tecno,String programa, String estado, String nombre, String tipo, String coodirector,String valor, String nro_consignacion, String entidad, String titulo, String codigoprp){


         PDFButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 JFileChooser fileChooser = new JFileChooser();
                 fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
                 int returnValue = fileChooser.showOpenDialog(null);
                 if (returnValue == JFileChooser.APPROVE_OPTION) {
                     selectedFile = fileChooser.getSelectedFile();
                 }
             }
         });
         SUBIRButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 PreparedStatement ps;
                 try {
                     ps = con.prepareStatement("INSERT INTO proyectogrado (programa,estado,nombre,tipo,coodirector,valor,nro_consignacion,entidad,titulo,codigo_prp,tipo_prp,nombre_empresa,direccion,cel_empresa,correo_Empresa,nro_practica,desarrollo_tec,proyecto,id_usuario_rol,pdf) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );

                     ps.setString(1,programa);
                     ps.setString(2,estado);
                     ps.setString(3,nombre);
                     ps.setString(4,tipo);
                     ps.setString(5,coodirector);
                     ps.setString(6,valor);
                     ps.setString(7,nro_consignacion);
                     ps.setString(8,entidad);
                     ps.setString(9,titulo);
                     ps.setString(10,codigoprp);
                     ps.setString(11,null);
                     ps.setString(12,null);
                     ps.setString(13,null);
                     ps.setString(14,null);
                     ps.setString(15,null);
                     ps.setString(16,null);
                     ps.setString(17,textField1.getText());
                     ps.setString(18,textField2.getText());
                     ps.setString(19,null);
                     FileInputStream file = new FileInputStream(selectedFile);
                     ps.setBinaryStream(20, file, (int) selectedFile.length());
                     ps.executeUpdate();
                     JOptionPane.showMessageDialog(null,"añadido");
                     textField1.setText("");
                     textField2.setText("");
                 } catch (SQLException | FileNotFoundException ex) {
                     throw new RuntimeException(ex);
                 }

                 JFrame frame = new JFrame("login");
                 login loginPanel = new login(frame);
                 frame.setContentPane(loginPanel.vistalogin);
                 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 frame.setMinimumSize(new Dimension(450,300));
                 frame.pack();
                 frame.setVisible(true);

                 tecno.dispose();
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("proyectotecnologico");
        proyectotecnologico prtec = new proyectotecnologico(frame,"", "", "", "", "", "", "", "","","");
        frame.setContentPane(prtec.PROYECT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }
}
