package main_Bd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class proyctgrado extends proyectogr{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton VOLVERButton;
    JPanel proyecto;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton SUBIRButton;
    private JComboBox comboBox1;

    public String programa ;
    String estado ;
    String nombre;
    String tipo ;
    String coodirector ;
    String entidad ;
    String titulo ;
    String  codigoprp ;
    String valor;
    String nro_consignacion;

    public static void main(String[] args) {
        JFrame frame = new JFrame("proyctgrado");
        proyctgrado proyec = new proyctgrado(frame);
        frame.setContentPane(proyec.proyecto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(450,300));
        frame.pack();
        frame.setVisible(true);
    }

    public void guardardatos(){
        programa = textField1.getText();
        estado = textField2.getText();
        nombre = textField3.getText();
        tipo = comboBox1.getSelectedItem().toString();
        coodirector = textField5.getText();
        valor = textField6.getText();
        nro_consignacion = textField7.getText();
        entidad = textField8.getText();
        titulo = textField9.getText();
        codigoprp = textField10.getText();
    }

    public proyctgrado(JFrame proyectoframe){

        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("login");
                login loginPanel = new login(frame);
                frame.setContentPane(loginPanel.vistalogin);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(450,300));
                frame.pack();
                frame.setVisible(true);

                proyectoframe.dispose();

            }
        });


        SUBIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardardatos();

                if (tipo.equals("practicas")){

                    JFrame frame = new JFrame("Practicas");
                    Practicas practicas = new Practicas(frame, programa, estado, nombre, tipo, coodirector,valor,nro_consignacion, entidad, titulo, codigoprp);
                    frame.setContentPane(practicas.prac);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setMinimumSize(new Dimension(450,300));
                    frame.pack();
                    frame.setVisible(true);

                    proyectoframe.dispose();


                }
                else {
                    if (tipo.equals("investigacion")){
                        JFrame frame = new JFrame("investigacion");
                        investigacion investiga = new investigacion(frame,programa, estado, nombre, tipo, coodirector,valor,nro_consignacion, entidad, titulo, codigoprp);
                        frame.setContentPane(investiga.INVES);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setMinimumSize(new Dimension(450,300));
                        frame.pack();
                        frame.setVisible(true);
                        proyectoframe.dispose();

                    }
                    else {
                        if (tipo.equals("proyectotecnologico")){
                            JFrame frame = new JFrame("proyectotecnologico");
                            proyectotecnologico prtec = new proyectotecnologico(frame,programa, estado, nombre, tipo, coodirector,valor,nro_consignacion, entidad, titulo, codigoprp);
                            frame.setContentPane(prtec.PROYECT);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setMinimumSize(new Dimension(450,300));
                            frame.pack();
                            frame.setVisible(true);
                            proyectoframe.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"no se encontro tu tipo");
                        }
                    }
                }


            }
        });
    }






}
