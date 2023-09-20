/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import Dao.DaoArea;
import Dao.DaoCargo;
import Dao.DaoDatos;
import Dao.DaoEmpleado;
import Dao.DaoNomina;
import Dao.DaoPago;
import Dao.conexion;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.Datos;
import modelo.RenderImagen;
import modelo.areas;
import modelo.cargo;
import modelo.empleado;
import modelo.nomina;
import modelo.pago;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author HELIO
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    cargo cr=new cargo();
    DaoCargo daoC=new DaoCargo();
    DefaultTableModel modeloCargo=new DefaultTableModel();
    
    areas ar=new areas();
    DaoArea daoA=new DaoArea();
    DefaultTableModel modeloArea=new DefaultTableModel();
    
    empleado em=new empleado();
    DaoEmpleado daoE=new DaoEmpleado();
    DefaultTableModel modeloEmpleado=new DefaultTableModel();
    
    nomina no=new nomina();
    DaoNomina daoN=new DaoNomina();
    DefaultTableModel modeloNomina=new DefaultTableModel();
    
    pago pa=new pago();
    DaoPago daoP=new DaoPago();
    DefaultTableModel modeloPago=new DefaultTableModel();
    
    Datos da=new Datos();
    DaoDatos daoD=new DaoDatos();
    DefaultTableModel modeloDatos=new DefaultTableModel();
    
    String Ruta = "";
    String valor="1";
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        listar();
        listarArea();
        listarEmpleado();
        listarNomina();
        listarPagos();
        modeloDatos.addColumn("ID");
        modeloDatos.addColumn("Empresa");
        modeloDatos.addColumn("RUC");
        modeloDatos.addColumn("Razon Social");
        modeloDatos.addColumn("Telefono");
        modeloDatos.addColumn("Direccion");
        modeloDatos.addColumn("Correo");
        modeloDatos.addColumn("Imagen");
        ListarDatos();
        btnEnviarArea.setEnabled(false);
        btnEnviarCargo.setEnabled(false);
    }
    
    private void ListarDatos(){
        tabladatos.setDefaultRenderer(Object.class, new RenderImagen());
        
        ArrayList datos;
        Object Datos[] =new Object[8];
        datos=daoD.Listar();
        if(datos!=null){
            for(int i=0;i<datos.size();i++){
                da = (Datos) datos.get(i);
                Datos[0]=String.valueOf(da.getId());
                Datos[1]=da.getNombre();
                Datos[2]=da.getRUC();
                Datos[3]=da.getRasonS();
                Datos[4]=da.getTelefono();
                Datos[5]=da.getDireccion();
                Datos[6]=da.getCorreo();
                try{
                    byte[] imagen =da.getImagen();
                    BufferedImage buffer=null;
                    InputStream inputstream=new ByteArrayInputStream(imagen);
                    buffer=ImageIO.read(inputstream);
                    ImageIcon incono=new ImageIcon(buffer.getScaledInstance(60, 60, 0));
                    Datos[7]=new JLabel(incono);
                }catch (Exception e){
                    Datos[7]=new JLabel("SIN IMAGEN");  
                }
                modeloDatos.addRow(Datos);
            }
            tabladatos.setModel(modeloDatos);
            tabladatos.setRowHeight(60);
            tabladatos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(2).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(6).setPreferredWidth(60);
            tabladatos.getColumnModel().getColumn(7).setPreferredWidth(60);
        }
    }

    private void listar(){
        List<cargo> lista=daoC.Listar();
        modeloCargo=(DefaultTableModel) tablacargos.getModel();
        Object[] ob=new Object[3];
        for(int i=0;i<lista.size();i++){
            ob[0]=lista.get(i).getId();
            ob[1]=lista.get(i).getNombre();
            ob[2]=lista.get(i).getPrecio();
            modeloCargo.addRow(ob);
        }
        tablacargos.setModel(modeloCargo);
    }
    private void listarArea(){
        List<areas> lista=daoA.Listar();
        modeloArea=(DefaultTableModel) tablaArea.getModel();
        Object[] ob=new Object[2];
        for(int i=0;i<lista.size();i++){
            ob[0]=lista.get(i).getIdarea();
            ob[1]=lista.get(i).getNomArea();
            modeloArea.addRow(ob);
        }
        tablaArea.setModel(modeloArea);
    }
    private void listarEmpleado(){
        try{
            DefaultTableModel modelo;
            modelo=daoE.listar();
            tablaempleado.setModel(modelo);
        }catch(Exception e){
            
        }
    }
    private void listarNomina(){
        try{
            DefaultTableModel modelo;
            modelo=daoN.listar();
            tablanomina.setModel(modelo);
        }catch(Exception e){
            
        }
    }
    private void listarPagos(){
        try{
            DefaultTableModel modelo;
            modelo=daoP.listar();
            tablapago.setModel(modelo);
        }catch(Exception e){
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnUsuarios = new javax.swing.JButton();
        btnEmpleados = new javax.swing.JButton();
        btnArea = new javax.swing.JButton();
        btnCargo = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        panel = new javax.swing.JTabbedPane();
        phome = new javax.swing.JPanel();
        pusuarios = new javax.swing.JPanel();
        pempleados = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtidempleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnomempleado = new javax.swing.JTextField();
        txtapeempleado = new javax.swing.JTextField();
        txtdoc = new javax.swing.JTextField();
        cbotipodoc = new javax.swing.JComboBox<>();
        txttelefono = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtidareaempleado = new javax.swing.JTextField();
        txtareaempleado = new javax.swing.JTextField();
        btnBuscaArea = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtidcargoempleado = new javax.swing.JTextField();
        txtcargoempleado = new javax.swing.JTextField();
        btnBuscaCarho = new javax.swing.JButton();
        btnregistrarempleado = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaempleado = new javax.swing.JTable();
        btnModificarEmpleado = new javax.swing.JButton();
        btnBuscarEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        pcargo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtidcargo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcargo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablacargos = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEnviarCargo = new javax.swing.JButton();
        txtprecioCargo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        parea = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtidarea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtarea = new javax.swing.JTextField();
        btnregistrarArea = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaArea = new javax.swing.JTable();
        btnModificarArea = new javax.swing.JButton();
        btnEliminarArea = new javax.swing.JButton();
        btnBuscarArea = new javax.swing.JButton();
        btnEnviarArea = new javax.swing.JButton();
        Pnomina = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtdocemopleadoN = new javax.swing.JTextField();
        btnBuscarEN = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtidempleadoN = new javax.swing.JTextField();
        txtnomEmpleadoN = new javax.swing.JTextField();
        txtapeempleadoN = new javax.swing.JTextField();
        txtidcargoN = new javax.swing.JTextField();
        txtcargoN = new javax.swing.JTextField();
        txtprecioN = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        txtcantTrabajo = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtidnomina = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnRegistrarNomina = new javax.swing.JButton();
        btnEditarNomina = new javax.swing.JButton();
        btnBuscarNomina = new javax.swing.JButton();
        btnEliminarNomina = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablanomina = new javax.swing.JTable();
        Ppagos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtidpago = new javax.swing.JTextField();
        txttotalPago = new javax.swing.JTextField();
        btnCalcularPago = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtidempleadop = new javax.swing.JTextField();
        txtempleadop = new javax.swing.JTextField();
        txtapellidop = new javax.swing.JTextField();
        txtidcargop = new javax.swing.JTextField();
        txtcargop = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtdocumentop = new javax.swing.JTextField();
        btnbuscarep = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        fecha1 = new com.toedter.calendar.JDateChooser();
        jLabel37 = new javax.swing.JLabel();
        fecha2 = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        btnregistrarPago = new javax.swing.JButton();
        btnEditarPago = new javax.swing.JButton();
        btnEliminarPago = new javax.swing.JButton();
        btnNuevoPago = new javax.swing.JButton();
        btnGenerarPDFPAGO = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablapago = new javax.swing.JTable();
        Pdatos = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        txtempresa = new javax.swing.JTextField();
        txtruc = new javax.swing.JTextField();
        txtRs = new javax.swing.JTextField();
        txttelefonoE = new javax.swing.JTextField();
        txtdireccionE = new javax.swing.JTextField();
        txtcorreoE = new javax.swing.JTextField();
        btnCargarImagen = new javax.swing.JButton();
        txtIdDato = new javax.swing.JLabel();
        btnEditardatos = new javax.swing.JButton();
        btnRegistrarE = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabladatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(217, 163, 21));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnEmpleados.setText("Empleados");
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });

        btnArea.setText("Area");
        btnArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAreaActionPerformed(evt);
            }
        });

        btnCargo.setText("Cargo");
        btnCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargoActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnArea, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        phome.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout phomeLayout = new javax.swing.GroupLayout(phome);
        phome.setLayout(phomeLayout);
        phomeLayout.setHorizontalGroup(
            phomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );
        phomeLayout.setVerticalGroup(
            phomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        panel.addTab("Home", phome);

        pusuarios.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pusuariosLayout = new javax.swing.GroupLayout(pusuarios);
        pusuarios.setLayout(pusuariosLayout);
        pusuariosLayout.setHorizontalGroup(
            pusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );
        pusuariosLayout.setVerticalGroup(
            pusuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        panel.addTab("Usuarios", pusuarios);

        pempleados.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        jLabel5.setText("ID");

        jLabel6.setText("Nombre:");

        jLabel7.setText("Apellido:");

        jLabel8.setText("Tipo Doc:");

        jLabel9.setText("Documento:");

        jLabel10.setText("Telefono:");

        jLabel11.setText("Correo:");

        cbotipodoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "PASAPORTE", "CARNET" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtapeempleado))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdoc))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(22, 22, 22)
                        .addComponent(cbotipodoc, 0, 163, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(23, 23, 23)
                        .addComponent(txttelefono))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcorreo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnomempleado)
                            .addComponent(txtidempleado))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtidempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnomempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtapeempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cbotipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Area"));

        jLabel12.setText("ID:");

        jLabel13.setText("Area:");

        btnBuscaArea.setText("...");
        btnBuscaArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addComponent(txtidareaempleado))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtareaempleado, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnBuscaArea, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtidareaempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtareaempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscaArea)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Cargo"));

        jLabel14.setText("ID:");

        jLabel15.setText("Cargo:");

        btnBuscaCarho.setText("...");
        btnBuscaCarho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaCarhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnBuscaCarho, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtcargoempleado, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(txtidcargoempleado))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtidcargoempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtcargoempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscaCarho)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnregistrarempleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnregistrarempleado.setText("Registrar");
        btnregistrarempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarempleadoActionPerformed(evt);
            }
        });

        tablaempleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaempleadoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaempleado);

        btnModificarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar (1).png"))); // NOI18N
        btnModificarEmpleado.setText("Modificar");
        btnModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpleadoActionPerformed(evt);
            }
        });

        btnBuscarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscarEmpleado.setText("Buscar");
        btnBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleadoActionPerformed(evt);
            }
        });

        btnEliminarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarEmpleado.setText("Eliminar");
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pempleadosLayout = new javax.swing.GroupLayout(pempleados);
        pempleados.setLayout(pempleadosLayout);
        pempleadosLayout.setHorizontalGroup(
            pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pempleadosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pempleadosLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pempleadosLayout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(pempleadosLayout.createSequentialGroup()
                                .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnEliminarEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnregistrarempleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificarEmpleado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarEmpleado)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        pempleadosLayout.setVerticalGroup(
            pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pempleadosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pempleadosLayout.createSequentialGroup()
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnregistrarempleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pempleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnModificarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarEmpleado)))
                        .addGap(4, 4, 4)
                        .addComponent(btnEliminarEmpleado))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        panel.addTab("Empleados", pempleados);

        pcargo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("ID:");

        jLabel2.setText("Nombre:");

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        tablacargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre Cargo", "Precio"
            }
        ));
        tablacargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablacargosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablacargos);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar (1).png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEnviarCargo.setText("Enviar");
        btnEnviarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCargoActionPerformed(evt);
            }
        });

        jLabel16.setText("Precio:");

        javax.swing.GroupLayout pcargoLayout = new javax.swing.GroupLayout(pcargo);
        pcargo.setLayout(pcargoLayout);
        pcargoLayout.setHorizontalGroup(
            pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcargoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(164, 164, 164))
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(txtidcargo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtprecioCargo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEnviarCargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtcargo, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        pcargoLayout.setVerticalGroup(
            pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcargoLayout.createSequentialGroup()
                .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pcargoLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcargoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtidcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(pcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprecioCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviarCargo))
                    .addGroup(pcargoLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        panel.addTab("Cargo", pcargo);

        parea.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("ID:");

        jLabel4.setText("Nombre:");

        btnregistrarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnregistrarArea.setText("Registrar");
        btnregistrarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarAreaActionPerformed(evt);
            }
        });

        tablaArea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Area"
            }
        ));
        tablaArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAreaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaArea);

        btnModificarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar (1).png"))); // NOI18N
        btnModificarArea.setText("Modificar");
        btnModificarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarAreaActionPerformed(evt);
            }
        });

        btnEliminarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarArea.setText("Eliminar");
        btnEliminarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAreaActionPerformed(evt);
            }
        });

        btnBuscarArea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscarArea.setText("Buscar");
        btnBuscarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAreaActionPerformed(evt);
            }
        });

        btnEnviarArea.setText("Enviar");
        btnEnviarArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarAreaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pareaLayout = new javax.swing.GroupLayout(parea);
        parea.setLayout(pareaLayout);
        pareaLayout.setHorizontalGroup(
            pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pareaLayout.createSequentialGroup()
                .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pareaLayout.createSequentialGroup()
                            .addGap(76, 76, 76)
                            .addComponent(txtidarea, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pareaLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))))
                    .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEnviarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnregistrarArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(txtarea, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pareaLayout.setVerticalGroup(
            pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pareaLayout.createSequentialGroup()
                .addGroup(pareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pareaLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnregistrarArea)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarArea)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarArea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarArea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviarArea))
                    .addGroup(pareaLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );

        panel.addTab("Area", parea);

        Pnomina.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Empleado"));

        jLabel17.setText("Doc:");

        btnBuscarEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscarEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarENActionPerformed(evt);
            }
        });

        jLabel18.setText("Id:");

        jLabel19.setText("Nombre:");

        jLabel20.setText("Apellido:");

        jLabel21.setText("IdCargo:");

        jLabel22.setText("Cargo:");

        jLabel23.setText("Precio Cargo:");

        txtapeempleadoN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapeempleadoNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtdocemopleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(txtidempleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnomEmpleadoN)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txtapeempleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(btnBuscarEN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtprecioN, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtidcargoN)
                                    .addComponent(txtcargoN, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdocemopleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addComponent(btnBuscarEN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtidempleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtnomEmpleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtapeempleadoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtidcargoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtcargoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtprecioN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Nomina"));

        jLabel24.setText("fecha:");

        jLabel25.setText("Cant. Trabajo:");

        jLabel26.setText("Total Pagar:");

        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcular.png"))); // NOI18N
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jLabel27.setText("ID");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcantTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtidnomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24)
                            .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtcantTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnCalcular, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtidnomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Acciones"));

        btnRegistrarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnRegistrarNomina.setText("Registrar");
        btnRegistrarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarNominaActionPerformed(evt);
            }
        });

        btnEditarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar (1).png"))); // NOI18N
        btnEditarNomina.setText("Editar");
        btnEditarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarNominaActionPerformed(evt);
            }
        });

        btnBuscarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscarNomina.setText("Buscar");
        btnBuscarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNominaActionPerformed(evt);
            }
        });

        btnEliminarNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarNomina.setText("Eliminar");
        btnEliminarNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNominaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrarNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarNomina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnEliminarNomina)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarNomina)
                    .addComponent(btnEditarNomina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarNomina)
                    .addComponent(btnEliminarNomina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablanomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablanomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablanominaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablanomina);

        javax.swing.GroupLayout PnominaLayout = new javax.swing.GroupLayout(Pnomina);
        Pnomina.setLayout(PnominaLayout);
        PnominaLayout.setHorizontalGroup(
            PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnominaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PnominaLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 119, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PnominaLayout.setVerticalGroup(
            PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnominaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PnominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PnominaLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        panel.addTab("Nomina", Pnomina);

        Ppagos.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Nomina"));

        jLabel29.setText("Total:");

        jLabel30.setText("IDPago:");

        btnCalcularPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calcular.png"))); // NOI18N
        btnCalcularPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(txttotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidpago, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCalcularPago)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtidpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txttotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(btnCalcularPago)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));

        jLabel31.setText("ID:");

        jLabel32.setText("Nombre:");

        jLabel33.setText("Apellido:");

        jLabel34.setText("IdCargo:");

        jLabel35.setText("Cargo:");

        txtcargop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcargopActionPerformed(evt);
            }
        });

        jLabel38.setText("Doc:");

        btnbuscarep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnbuscarep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(18, 18, 18)
                        .addComponent(txtidcargop))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(28, 28, 28)
                        .addComponent(txtcargop))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtempleadop, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txtidempleadop)
                                    .addComponent(txtdocumentop, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnbuscarep, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtdocumentop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnbuscarep, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(txtidempleadop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtempleadop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtidcargop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtcargop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel36.setText("Fecha Inicio:");

        jLabel37.setText("Fecha Fin:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnregistrarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnregistrarPago.setText("Registrar");
        btnregistrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarPagoActionPerformed(evt);
            }
        });

        btnEditarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar (1).png"))); // NOI18N
        btnEditarPago.setText("Editar");
        btnEditarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPagoActionPerformed(evt);
            }
        });

        btnEliminarPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminarPago.setText("Eliminar");
        btnEliminarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPagoActionPerformed(evt);
            }
        });

        btnNuevoPago.setText("Nuevo");
        btnNuevoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPagoActionPerformed(evt);
            }
        });

        btnGenerarPDFPAGO.setText("PDF");
        btnGenerarPDFPAGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFPAGOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNuevoPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnregistrarPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditarPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerarPDFPAGO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoPago)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminarPago)
                        .addComponent(btnregistrarPago)
                        .addComponent(btnEditarPago)
                        .addComponent(btnGenerarPDFPAGO)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablapago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablapago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapagoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablapago);

        javax.swing.GroupLayout PpagosLayout = new javax.swing.GroupLayout(Ppagos);
        Ppagos.setLayout(PpagosLayout);
        PpagosLayout.setHorizontalGroup(
            PpagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PpagosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(PpagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PpagosLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        PpagosLayout.setVerticalGroup(
            PpagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PpagosLayout.createSequentialGroup()
                .addGroup(PpagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PpagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PpagosLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        panel.addTab("Pagos", Ppagos);

        Pdatos.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setText("RUC:");

        jLabel39.setText("Razon Social:");

        jLabel40.setText("Telefono:");

        jLabel41.setText("Direccion:");

        jLabel42.setText("Correo:");

        jLabel43.setText("Nombre:");

        lblImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCargarImagen.setText("Cargar Imagen");
        btnCargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImagenActionPerformed(evt);
            }
        });

        txtIdDato.setText("2");

        btnEditardatos.setText("Editar");
        btnEditardatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditardatosActionPerformed(evt);
            }
        });

        btnRegistrarE.setText("Registrar");
        btnRegistrarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel43)
                            .addGap(40, 40, 40)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtempresa, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel39)
                                .addComponent(jLabel40)
                                .addComponent(jLabel41)
                                .addComponent(jLabel42))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRs)
                                .addComponent(txttelefonoE)
                                .addComponent(txtdireccionE)
                                .addComponent(txtcorreoE))))
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtIdDato, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegistrarE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditardatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtempresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtRs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txttelefonoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtdireccionE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtcorreoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnCargarImagen)
                                    .addComponent(txtIdDato, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrarE)
                                .addGap(12, 12, 12)
                                .addComponent(btnEditardatos)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tabladatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Empresa", "RUC", "Razon S.", "Telefono", "Direccion", "Correo", "Imagen"
            }
        ));
        tabladatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabladatosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabladatos);

        javax.swing.GroupLayout PdatosLayout = new javax.swing.GroupLayout(Pdatos);
        Pdatos.setLayout(PdatosLayout);
        PdatosLayout.setHorizontalGroup(
            PdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PdatosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(PdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        PdatosLayout.setVerticalGroup(
            PdatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PdatosLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        panel.addTab("Datos", Pdatos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        // TODO add your handling code here:
        panel.setSelectedComponent(pusuarios);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed
        // TODO add your handling code here:
        panel.setSelectedComponent(pempleados);
    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAreaActionPerformed
        // TODO add your handling code here:
        panel.setSelectedComponent(parea);
    }//GEN-LAST:event_btnAreaActionPerformed

    private void btnCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargoActionPerformed
        // TODO add your handling code here:
        panel.setSelectedComponent(pcargo);
    }//GEN-LAST:event_btnCargoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        cr.setNombre(txtcargo.getText());
        cr.setPrecio(Double.parseDouble(txtprecioCargo.getText()));
        if(daoC.insertar(cr)){
            JOptionPane.showMessageDialog(null, "Cargo registrado con exito");
            limpiarDatosCargo();
            limpiarTablaCargo();
            listar();
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el Cargo");
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tablacargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacargosMouseClicked
        // TODO add your handling code here:
        int fila=tablacargos.getSelectedRow();
        txtidcargo.setText(tablacargos.getValueAt(fila, 0).toString());
        txtcargo.setText(tablacargos.getValueAt(fila, 1).toString());
        txtprecioCargo.setText(tablacargos.getValueAt(fila, 2).toString());
    }//GEN-LAST:event_tablacargosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        int fila=tablacargos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un cargo");
        }else{
            cr.setId(Integer.parseInt(txtidcargo.getText()));
            cr.setNombre(txtcargo.getText());
            cr.setPrecio(Double.parseDouble(txtprecioCargo.getText()));
            if(daoC.editar(cr)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosCargo();
                limpiarTablaCargo();
                listar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(!txtidcargo.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el cargo?","Confirmar",2);
            if(confirmacion==0){
                cr.setId(Integer.parseInt(txtidcargo.getText()));
                daoC.elimiar(cr);
                limpiarDatosCargo();
                limpiarTablaCargo();
                listar();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        cr.setId(Integer.parseInt(txtidcargo.getText()));
        if(daoC.Buscar(cr)){
            txtidcargo.setText(cr.getId()+"");
            txtcargo.setText(cr.getNombre());
            txtprecioCargo.setText(cr.getPrecio()+"");
        }else{
            JOptionPane.showMessageDialog(null, "El Cargo No Existe");
            txtidcargo.setText("");
            txtcargo.setText("");
            txtprecioCargo.setText("");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnregistrarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarAreaActionPerformed
        // TODO add your handling code here:
        ar.setNomArea(txtarea.getText());
        if(daoA.insertar(ar)){
            JOptionPane.showMessageDialog(null, "Area registrado con exito");
            limpiarDatosArea();
            limpiarTablaArea();
            listarArea();
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el Area");
        }
    }//GEN-LAST:event_btnregistrarAreaActionPerformed

    private void btnModificarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarAreaActionPerformed
        // TODO add your handling code here:
        int fila=tablaArea.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un Area");
        }else{
            ar.setIdarea(Integer.parseInt(txtidarea.getText()));
            ar.setNomArea(txtarea.getText());
            if(daoA.editar(ar)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosArea();
                limpiarTablaArea();
                listarArea();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnModificarAreaActionPerformed

    private void tablaAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAreaMouseClicked
        // TODO add your handling code here:
        int fila=tablaArea.getSelectedRow();
        txtidarea.setText(tablaArea.getValueAt(fila, 0).toString());
        txtarea.setText(tablaArea.getValueAt(fila, 1).toString());
    }//GEN-LAST:event_tablaAreaMouseClicked

    private void btnEliminarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAreaActionPerformed
        // TODO add your handling code here:
        if(!txtidarea.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el area?","Confirmar",2);
            if(confirmacion==0){
                ar.setIdarea(Integer.parseInt(txtidarea.getText()));
                daoA.elimiar(ar);
                limpiarDatosArea();
                limpiarTablaArea();
                listarArea();
            }
        }
    }//GEN-LAST:event_btnEliminarAreaActionPerformed

    private void btnBuscarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAreaActionPerformed
        // TODO add your handling code here:
        ar.setIdarea(Integer.parseInt(txtidarea.getText()));
        if(daoA.Buscar(ar)){
            txtidarea.setText(ar.getIdarea()+"");
            txtarea.setText(ar.getNomArea());
        }else{
            JOptionPane.showMessageDialog(null, "El Area No Existe");
            txtidarea.setText("");
            txtarea.setText("");
        }
    }//GEN-LAST:event_btnBuscarAreaActionPerformed

    private void btnEnviarAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarAreaActionPerformed
        // TODO add your handling code here:
        txtidareaempleado.setText(txtidarea.getText());
        txtareaempleado.setText(txtarea.getText());
        btnEnviarArea.setEnabled(false);
        panel.setSelectedComponent(pempleados);
    }//GEN-LAST:event_btnEnviarAreaActionPerformed

    private void btnregistrarempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarempleadoActionPerformed
        // TODO add your handling code here:
        em.setNombre(txtnomempleado.getText());
        em.setApellido(txtapeempleado.getText());
        em.setTipodoc(cbotipodoc.getSelectedItem().toString());
        em.setDoc(txtdoc.getText());
        em.setIdArea(Integer.parseInt(txtidareaempleado.getText()));
        em.setIdcargo(Integer.parseInt(txtidcargoempleado.getText()));
        em.setTelefono(txttelefono.getText());
        em.setCorreo(txtcorreo.getText());
        if(daoE.insertar(em)){
            JOptionPane.showMessageDialog(null, "Empleado registrado con exito");
            limpiarDatosEmpleado();
            limpiarTablaEmpleado();
            listarEmpleado();
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el Empleado");
        }
    }//GEN-LAST:event_btnregistrarempleadoActionPerformed

    private void btnBuscaAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaAreaActionPerformed
        // TODO add your handling code here:
        btnEnviarArea.setEnabled(true);
        panel.setSelectedComponent(parea);
        
    }//GEN-LAST:event_btnBuscaAreaActionPerformed

    private void btnEnviarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCargoActionPerformed
        // TODO add your handling code here:
        txtidcargoempleado.setText(txtidcargo.getText());
        txtcargoempleado.setText(txtcargo.getText());
        btnEnviarCargo.setEnabled(false);
        panel.setSelectedComponent(pempleados);
    }//GEN-LAST:event_btnEnviarCargoActionPerformed

    private void btnBuscaCarhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaCarhoActionPerformed
        // TODO add your handling code here:
        panel.setSelectedComponent(pcargo);
        btnEnviarCargo.setEnabled(true);
    }//GEN-LAST:event_btnBuscaCarhoActionPerformed

    private void tablaempleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaempleadoMouseClicked
        // TODO add your handling code here:
        int fila=tablaempleado.getSelectedRow();
        txtidempleado.setText(tablaempleado.getValueAt(fila, 0).toString());
        txtnomempleado.setText(tablaempleado.getValueAt(fila, 1).toString());
        txtapeempleado.setText(tablaempleado.getValueAt(fila, 2).toString());
        cbotipodoc.setSelectedItem(tablaempleado.getValueAt(fila, 3).toString());
        txtdoc.setText(tablaempleado.getValueAt(fila, 4).toString());
        txtidareaempleado.setText(tablaempleado.getValueAt(fila, 5).toString());
        txtareaempleado.setText(tablaempleado.getValueAt(fila, 6).toString());
        txtidcargoempleado.setText(tablaempleado.getValueAt(fila, 7).toString());
        txtcargoempleado.setText(tablaempleado.getValueAt(fila, 8).toString());
        txttelefono.setText(tablaempleado.getValueAt(fila, 9).toString());
        txtcorreo.setText(tablaempleado.getValueAt(fila, 10).toString());
    }//GEN-LAST:event_tablaempleadoMouseClicked

    private void btnModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpleadoActionPerformed
        // TODO add your handling code here:
         int fila=tablaempleado.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
        }else{
            em.setId(Integer.parseInt(txtidempleado.getText()));
            em.setNombre(txtnomempleado.getText());
            em.setApellido(txtapeempleado.getText());
            em.setTipodoc(cbotipodoc.getSelectedItem().toString());
            em.setDoc(txtdoc.getText());
            em.setIdArea(Integer.parseInt(txtidareaempleado.getText()));
            em.setIdcargo(Integer.parseInt(txtidcargoempleado.getText()));
            em.setTelefono(txttelefono.getText());
            em.setCorreo(txtcorreo.getText());
            if(daoE.editar(em)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosEmpleado();
                limpiarTablaEmpleado();
                listarEmpleado();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnModificarEmpleadoActionPerformed

    private void btnBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
        em.setId(Integer.parseInt(txtidempleado.getText()));
        if(daoE.Buscar(em)){
            txtidempleado.setText(em.getId()+"");
            txtnomempleado.setText(em.getNombre());
            txtapeempleado.setText(em.getApellido());
            cbotipodoc.setSelectedItem(em.getTipodoc().toString());
            txtdoc.setText(em.getDoc());
            txttelefono.setText(em.getTelefono());
            txtcorreo.setText(em.getCorreo());
            txtidareaempleado.setText(em.getIdArea()+""); 
            txtidcargoempleado.setText(em.getIdcargo()+"");
            ar.setIdarea(Integer.parseInt(txtidareaempleado.getText()));
            daoA.Buscar(ar);
            txtareaempleado.setText(ar.getNomArea());
            cr.setId(Integer.parseInt(txtidcargoempleado.getText()));
            daoC.Buscar(cr);
            txtcargoempleado.setText(cr.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            limpiarDatosEmpleado();
        }
    }//GEN-LAST:event_btnBuscarEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed
        // TODO add your handling code here:
         if(!txtidempleado.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el empleado?","Confirmar",2);
            if(confirmacion==0){
                em.setId(Integer.parseInt(txtidempleado.getText()));
                daoE.elimiar(em);
                limpiarDatosEmpleado();
                limpiarDatosEmpleado();
                listarEmpleado();
            }
        }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void btnRegistrarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarNominaActionPerformed
        // TODO add your handling code here:
        Calendar cal;
        int d,m,a;
        cal=dateFecha.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900;
        no.setFecha(new Date(a,m,d));
        
        no.setCtrabajo(Integer.parseInt(txtcantTrabajo.getText()));
        no.setTotal(Double.parseDouble(txttotal.getText()));
        no.setIdempleado(Integer.parseInt(txtidempleadoN.getText()));
        no.setIdcargo(Integer.parseInt(txtidcargoN.getText()));
        no.setEstado("pendiente");
        if(daoN.insertar(no)){
            JOptionPane.showMessageDialog(null, "Nomina registrada con exito");
            limpiarDatosNomina();
            limpiarTablaNomina();
             listarNomina();
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar la Nomina");
        }
    }//GEN-LAST:event_btnRegistrarNominaActionPerformed

    private void btnBuscarENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarENActionPerformed
        // TODO add your handling code here:
        em.setDoc(txtdocemopleadoN.getText());
        if(daoE.BuscarEmpleadoN(em)){
            txtidempleadoN.setText(em.getId()+"");
            txtnomEmpleadoN.setText(em.getNombre());
            txtapeempleadoN.setText(em.getApellido());
            txtidcargoN.setText(em.getIdcargo()+"");
            cr.setId(Integer.parseInt(txtidcargoN.getText()));
            daoC.Buscar(cr);
            txtcargoN.setText(cr.getNombre());
            txtprecioN.setText(cr.getPrecio()+"");
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            //limpiarDatosEmpleado();
        }
    }//GEN-LAST:event_btnBuscarENActionPerformed

    private void txtapeempleadoNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapeempleadoNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapeempleadoNActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        double cantidad,precio,total;
        cantidad=Double.parseDouble(txtcantTrabajo.getText());
        precio=Double.parseDouble(txtprecioN.getText());
        total=cantidad*precio;
        txttotal.setText(total+"");
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void tablanominaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablanominaMouseClicked
        // TODO add your handling code here:
         int fila=tablanomina.getSelectedRow();
        txtidnomina.setText(tablanomina.getValueAt(fila, 0).toString());
        dateFecha.setDate(Date.valueOf(tablanomina.getValueAt(fila, 1).toString()));
        txtidempleadoN.setText(tablanomina.getValueAt(fila, 2).toString());
        txtnomEmpleadoN.setText(tablanomina.getValueAt(fila, 3).toString());
        txtapeempleadoN.setText(tablanomina.getValueAt(fila, 4).toString());
        txtdocemopleadoN.setText(tablanomina.getValueAt(fila, 5).toString());
        txtidcargoN.setText(tablanomina.getValueAt(fila, 6).toString());
        txtcargoN.setText(tablanomina.getValueAt(fila, 7).toString());
        txtprecioN.setText(tablanomina.getValueAt(fila, 8).toString());
        txtcantTrabajo.setText(tablanomina.getValueAt(fila, 9).toString());
        txttotal.setText(tablanomina.getValueAt(fila, 10).toString());
    }//GEN-LAST:event_tablanominaMouseClicked

    private void btnEditarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarNominaActionPerformed
        // TODO add your handling code here:
        int fila=tablanomina.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione una nomina");
        }else{
            Calendar cal;
            int d,m,a;
            cal=dateFecha.getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR)-1900;
            no.setFecha(new Date(a,m,d));
            no.setCtrabajo(Integer.parseInt(txtcantTrabajo.getText()));
            no.setTotal(Double.parseDouble(txttotal.getText()));
            no.setIdempleado(Integer.parseInt(txtidempleadoN.getText()));
            no.setIdcargo(Integer.parseInt(txtidcargoN.getText()));
            no.setId(Integer.parseInt(txtidnomina.getText()));
            if(daoN.editar(no)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                 limpiarDatosNomina();
                limpiarTablaNomina();
                listarNomina();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnEditarNominaActionPerformed

    private void btnBuscarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNominaActionPerformed
        // TODO add your handling code here:
        no.setId(Integer.parseInt(txtidnomina.getText()));
        if(daoN.Buscar(no)){
            txtidnomina.setText(no.getId()+"");
            dateFecha.setDate(Date.valueOf(no.getFecha().toString()));
            txtcantTrabajo.setText(no.getCtrabajo()+"");
            txttotal.setText(no.getTotal()+"");
            txtidempleadoN.setText(no.getIdempleado()+"");
            txtidcargoN.setText(no.getIdcargo()+"");
            em.setId(Integer.parseInt(txtidempleadoN.getText()));
            daoE.Buscar(em);
            txtdocemopleadoN.setText(em.getDoc()); 
            txtnomEmpleadoN.setText(em.getNombre());
            txtapeempleadoN.setText(em.getApellido());
            cr.setId(Integer.parseInt(txtidcargoN.getText()));
            daoC.Buscar(cr);
            txtcargoN.setText(cr.getNombre());
            txtprecioN.setText(cr.getPrecio()+"");
        }else{
            JOptionPane.showMessageDialog(null, "La nomina No Existe");
            //limpiarDatosNomina();
        }
    }//GEN-LAST:event_btnBuscarNominaActionPerformed

    private void btnEliminarNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNominaActionPerformed
        // TODO add your handling code here:
         if(!txtidnomina.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar la nomina?","Confirmar",2);
            if(confirmacion==0){
                no.setId(Integer.parseInt(txtidnomina.getText()));
                daoN.elimiar(no);
                limpiarDatosNomina();
                listarNomina();
            }
        }
    }//GEN-LAST:event_btnEliminarNominaActionPerformed

    private void btnregistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarPagoActionPerformed
        // TODO add your handling code here:
        Calendar cal;
        int d,m,a;
        cal=fecha1.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900;
        pa.setFecha1(new Date(a,m,d));
        
        Calendar cal1;
        int d1,m1,a1;
        cal1=fecha2.getCalendar();
        d1=cal1.get(Calendar.DAY_OF_MONTH);
        m1=cal1.get(Calendar.MONTH);
        a1=cal1.get(Calendar.YEAR)-1900;
        pa.setFecha2(new Date(a1,m1,d1));
        
        pa.setIdempleado(Integer.parseInt(txtidempleadop.getText()));
        pa.setTotal(Double.parseDouble(txttotalPago.getText()));
        
        if(daoP.insertar(pa)){
            JOptionPane.showMessageDialog(null, "Pago registrado con exito");
            
        }else{
            JOptionPane.showMessageDialog(null, "Error Al Registrar el pago");
        }
        int ide;
        String f1,f2;
        
        ide=Integer.parseInt(txtidempleadop.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate());
        
        if(daoP.editarN(ide, f1, f2,"pagado")){
            GenerarPDFpagos(ide, f1, f2);
            limpiarTablaPago();
            listarPagos();
        }else{
            JOptionPane.showMessageDialog(null, "Error editar de pendiente a pagado");
        }
    }//GEN-LAST:event_btnregistrarPagoActionPerformed

    private void btnbuscarepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarepActionPerformed
        // TODO add your handling code here:
        em.setDoc(txtdocumentop.getText());
        if(daoE.BuscarEmpleadoN(em)){
            txtidempleadop.setText(em.getId()+"");
            txtempleadop.setText(em.getNombre());
            txtapellidop.setText(em.getApellido());
            txtidcargop.setText(em.getIdcargo()+"");
            cr.setId(Integer.parseInt(txtidcargop.getText()));
            daoC.Buscar(cr);
            txtcargop.setText(cr.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado No Existe");
            //limpiarDatosEmpleado();
        }
    }//GEN-LAST:event_btnbuscarepActionPerformed

    private void txtcargopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcargopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcargopActionPerformed

    private void btnCalcularPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPagoActionPerformed
        // TODO add your handling code here:
        int ide;
        String f1,f2;
        
        ide=Integer.parseInt(txtidempleadop.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate()); 
        
        pa=daoP.Total(ide, f1, f2);
        if(pa.getTotal()!=0.0){
            txttotalPago.setText(pa.getTotal()+"");
        }else{
            JOptionPane.showMessageDialog(null, "Error al Calcular");
        }
    }//GEN-LAST:event_btnCalcularPagoActionPerformed

    private void tablapagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablapagoMouseClicked
        // TODO add your handling code here:
         int fila=tablapago.getSelectedRow();
        txtidpago.setText(tablapago.getValueAt(fila, 0).toString());
        txtidempleadop.setText(tablapago.getValueAt(fila, 1).toString());
        txtempleadop.setText(tablapago.getValueAt(fila, 2).toString());
        txtapellidop.setText(tablapago.getValueAt(fila, 3).toString());
        txtdocumentop.setText(tablapago.getValueAt(fila, 4).toString());
        txtidcargop.setText(tablapago.getValueAt(fila, 5).toString());
        txtcargop.setText(tablapago.getValueAt(fila, 6).toString());
        fecha1.setDate(Date.valueOf(tablapago.getValueAt(fila, 7).toString()));
        fecha2.setDate(Date.valueOf(tablapago.getValueAt(fila, 8).toString()));
        txttotalPago.setText(tablapago.getValueAt(fila, 9).toString());
    }//GEN-LAST:event_tablapagoMouseClicked

    private void btnEditarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPagoActionPerformed
        // TODO add your handling code here:
        int fila=tablapago.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione un Pago");
        }else{
            pa.setIdempleado(Integer.parseInt(txtidempleadop.getText()));
            pa.setTotal(Double.parseDouble(txttotalPago.getText()));
            Calendar cal;
            int d,m,a;
            cal=fecha1.getCalendar();
            d=cal.get(Calendar.DAY_OF_MONTH);
            m=cal.get(Calendar.MONTH);
            a=cal.get(Calendar.YEAR)-1900;
            pa.setFecha1(new Date(a,m,d));
            Calendar cal1;
            int d1,m1,a1;
            cal1=fecha2.getCalendar();
            d1=cal1.get(Calendar.DAY_OF_MONTH);
            m1=cal1.get(Calendar.MONTH);
            a1=cal1.get(Calendar.YEAR)-1900;
            pa.setFecha2(new Date(a1,m1,d1));
            pa.setId(Integer.parseInt(txtidpago.getText()));
            if(daoP.editar(pa)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatosPago();
                limpiarTablaPago();
                listarPagos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnEditarPagoActionPerformed

    private void btnEliminarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPagoActionPerformed
        // TODO add your handling code here:
         if(!txtidpago.getText().isEmpty()){
            int confirmacion=JOptionPane.showConfirmDialog(rootPane, "¿Seguro de eliminar el Pago?","Confirmar",2);
            if(confirmacion==0){
                pa.setId(Integer.parseInt(txtidpago.getText()));
                daoP.elimiar(pa);
                int ide;
                String f1,f2;
                ide=Integer.parseInt(txtidempleadop.getText());
                f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
                f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate()); 
                if(daoP.editarN(ide, f1, f2,"pendiente")){
                limpiarDatosPago();
                limpiarTablaPago();
                listarPagos();
        }else{
            JOptionPane.showMessageDialog(null, "Error editar de pendiente a pagado");
        }
            }
        }
    }//GEN-LAST:event_btnEliminarPagoActionPerformed

    private void btnCargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImagenActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(Ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), 0));
            lblImagen.setIcon(mIcono);
        }
        valor="2";
    }//GEN-LAST:event_btnCargarImagenActionPerformed

    private void btnRegistrarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEActionPerformed
        // TODO add your handling code here:
            da.setNombre(txtempresa.getText());
            da.setRUC(txtruc.getText());
            da.setRasonS(txtRs.getText());
            da.setTelefono(txttelefonoE.getText());
            da.setDireccion(txtdireccionE.getText());
            da.setCorreo(txtcorreoE.getText());
            da.setImagen(getImagen(Ruta));
            daoD.Agregar(da);
            limpiarDatos();
            limpiarTablaDAtos();
            ListarDatos();
    }//GEN-LAST:event_btnRegistrarEActionPerformed

    private void tabladatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabladatosMouseClicked
         int fila=tabladatos.getSelectedRow();
        txtIdDato.setText(tabladatos.getValueAt(fila, 0).toString());
        txtempresa.setText(tabladatos.getValueAt(fila, 1).toString());
        txtruc.setText(tabladatos.getValueAt(fila, 2).toString());
        txtRs.setText(tabladatos.getValueAt(fila, 3).toString());
        txttelefonoE.setText(tabladatos.getValueAt(fila, 4).toString());
        txtdireccionE.setText(tabladatos.getValueAt(fila, 5).toString());
        txtcorreoE.setText(tabladatos.getValueAt(fila, 6).toString());
        da.setId(Integer.parseInt(txtIdDato.getText()));
        if(daoD.BuscarImagen(da)){
            try{
                byte[] imagen =da.getImagen();
                BufferedImage buffer=null;
                InputStream inputstream=new ByteArrayInputStream(imagen);
                buffer=ImageIO.read(inputstream);
                ImageIcon incono=new ImageIcon(buffer.getScaledInstance(180, 180, 0));
                lblImagen.setIcon(incono);
                valor="1";
            }catch (Exception e){
                lblImagen.setText("Error");
            }
        }
    }//GEN-LAST:event_tabladatosMouseClicked

    private void btnEditardatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditardatosActionPerformed
        // TODO add your handling code here:
        int fila=tabladatos.getSelectedRow();
        if(fila==-1){
            JOptionPane.showMessageDialog(null, "Seleccione los datos");
        }else{
            da.setNombre(txtempresa.getText());
            da.setRUC(txtruc.getText());
            da.setRasonS(txtRs.getText());
            da.setTelefono(txttelefonoE.getText());
            da.setDireccion(txtdireccionE.getText());
            da.setCorreo(txtcorreoE.getText());
            if(valor.equals("1")){
                da.setImagen(getImagenEditar());
            }else{
                da.setImagen(getImagen(Ruta));
            }
            da.setId(Integer.parseInt(txtIdDato.getText()));
            if(daoD.editar(da)){
                JOptionPane.showMessageDialog(null, "se modifico con exito");
                limpiarDatos();
                limpiarTablaDAtos();
                ListarDatos();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
            }
        }
    }//GEN-LAST:event_btnEditardatosActionPerformed

    private void btnNuevoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPagoActionPerformed
        // TODO add your handling code here:
        limpiarDatosPago();
    }//GEN-LAST:event_btnNuevoPagoActionPerformed

    private void btnGenerarPDFPAGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFPAGOActionPerformed
        // TODO add your handling code here:
        int ide;
        String f1,f2;
        
        ide=Integer.parseInt(txtidempleadop.getText());
        f1=new SimpleDateFormat("yyyy-MM-dd").format(fecha1.getDate());
        f2=new SimpleDateFormat("yyyy-MM-dd").format(fecha2.getDate());
        GenerarPDFpagos(ide, f1, f2);
    }//GEN-LAST:event_btnGenerarPDFPAGOActionPerformed
    
    private byte[] getImagenEditar() {
        byte[] imagen =da.getImagen();
        try{
            BufferedImage buffer=null;
            InputStream inputstream=new ByteArrayInputStream(imagen);
            buffer=ImageIO.read(inputstream);
        }catch (Exception e){
            
        }
        return imagen;
    }
    
    private byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
    }
    void limpiarDatos(){
        txtempresa.setText("");
         txtruc.setText("");
         txtRs.setText("");
         txttelefonoE.setText("");
        txtdireccionE.setText("");
         txtcorreoE.setText("");
         lblImagen.setText("");
         lblImagen.setIcon(null);
    }
    
    void limpiarDatosCargo(){
        txtidcargo.setText("");
        txtcargo.setText("");
        txtprecioCargo.setText("");
    }
    void limpiarTablaCargo(){
        for(int i=0;i<modeloCargo.getRowCount();i++){
            modeloCargo.removeRow(i);
            i=0-1;
        }
    }
    
    void limpiarDatosArea(){
        txtidarea.setText("");
        txtarea.setText("");
    }
    void limpiarTablaArea(){
        for(int i=0;i<modeloArea.getRowCount();i++){
            modeloArea.removeRow(i);
            i=0-1;
        }
    }
    void limpiarDatosEmpleado(){
        txtidempleado.setText("");
        txtnomempleado.setText("");
        txtapeempleado.setText("");
        txtdoc.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");
        txtidareaempleado.setText("");
        txtareaempleado.setText("");
        txtidcargoempleado.setText("");
        txtcargoempleado.setText("");
    }
    void limpiarDatosNomina(){
            txtidnomina.setText("");
            txtcantTrabajo.setText("");
            txttotal.setText("");
            txtidempleadoN.setText("");
            txtidcargoN.setText("");
            txtdocemopleadoN.setText(""); 
            txtnomEmpleadoN.setText("");
            txtapeempleadoN.setText("");
            txtcargoN.setText("");
            txtprecioN.setText("");
    }
    void limpiarDatosPago(){
            txtidpago.setText("");
            txttotalPago.setText("");
            txtdocumentop.setText("");
            txtidempleadop.setText("");
            txtempleadop.setText("");
            txtapellidop.setText(""); 
            txtidcargop.setText("");
            txtcargop.setText("");
    }
    void limpiarTablaEmpleado(){
        for(int i=0;i<modeloEmpleado.getRowCount();i++){
            modeloEmpleado.removeRow(i);
            i=0-1;
        }
    }
    void limpiarTablaNomina(){
        for(int i=0;i<modeloNomina.getRowCount();i++){
            modeloNomina.removeRow(i);
            i=0-1;
        }
    }
    void limpiarTablaPago(){
        for(int i=0;i<modeloPago.getRowCount();i++){
            modeloPago.removeRow(i);
            i=0-1;
        }
    }
     void limpiarTablaDAtos(){
        for(int i=0;i<modeloDatos.getRowCount();i++){
            modeloDatos.removeRow(i);
            i=0-1;
        }
    }
     
     private Connection conection=new conexion().conectar();
     
    void GenerarPDFpagos(int id,String f1,String f2){
        Map p=new HashMap();
        p.put("idempleado", id);
        p.put("fecha1", f1);
        p.put("fecha2", f2);
        JasperReport report;
        JasperPrint print;
        
        try{
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+
                    "/src/reportes/pagosEmpleado.jrxml");
            print =JasperFillManager.fillReport(report, p,conection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Comprobante de pago");
            view.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            FlatArcOrangeIJTheme.setup();
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pdatos;
    private javax.swing.JPanel Pnomina;
    private javax.swing.JPanel Ppagos;
    private javax.swing.JButton btnArea;
    private javax.swing.JButton btnBuscaArea;
    private javax.swing.JButton btnBuscaCarho;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarArea;
    private javax.swing.JButton btnBuscarEN;
    private javax.swing.JButton btnBuscarEmpleado;
    private javax.swing.JButton btnBuscarNomina;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCalcularPago;
    private javax.swing.JButton btnCargarImagen;
    private javax.swing.JButton btnCargo;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarNomina;
    private javax.swing.JButton btnEditarPago;
    private javax.swing.JButton btnEditardatos;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarArea;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnEliminarNomina;
    private javax.swing.JButton btnEliminarPago;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnEnviarArea;
    private javax.swing.JButton btnEnviarCargo;
    private javax.swing.JButton btnGenerarPDFPAGO;
    private javax.swing.JButton btnModificarArea;
    private javax.swing.JButton btnModificarEmpleado;
    private javax.swing.JButton btnNuevoPago;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarE;
    private javax.swing.JButton btnRegistrarNomina;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnbuscarep;
    private javax.swing.JButton btnregistrarArea;
    private javax.swing.JButton btnregistrarPago;
    private javax.swing.JButton btnregistrarempleado;
    private javax.swing.JComboBox<String> cbotipodoc;
    private com.toedter.calendar.JDateChooser dateFecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTabbedPane panel;
    private javax.swing.JPanel parea;
    private javax.swing.JPanel pcargo;
    private javax.swing.JPanel pempleados;
    private javax.swing.JPanel phome;
    private javax.swing.JPanel pusuarios;
    private javax.swing.JTable tablaArea;
    private javax.swing.JTable tablacargos;
    private javax.swing.JTable tabladatos;
    private javax.swing.JTable tablaempleado;
    private javax.swing.JTable tablanomina;
    private javax.swing.JTable tablapago;
    private javax.swing.JLabel txtIdDato;
    private javax.swing.JTextField txtRs;
    private javax.swing.JTextField txtapeempleado;
    private javax.swing.JTextField txtapeempleadoN;
    private javax.swing.JTextField txtapellidop;
    private javax.swing.JTextField txtarea;
    private javax.swing.JTextField txtareaempleado;
    private javax.swing.JTextField txtcantTrabajo;
    private javax.swing.JTextField txtcargo;
    private javax.swing.JTextField txtcargoN;
    private javax.swing.JTextField txtcargoempleado;
    private javax.swing.JTextField txtcargop;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcorreoE;
    private javax.swing.JTextField txtdireccionE;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txtdocemopleadoN;
    private javax.swing.JTextField txtdocumentop;
    private javax.swing.JTextField txtempleadop;
    private javax.swing.JTextField txtempresa;
    private javax.swing.JTextField txtidarea;
    private javax.swing.JTextField txtidareaempleado;
    private javax.swing.JTextField txtidcargo;
    private javax.swing.JTextField txtidcargoN;
    private javax.swing.JTextField txtidcargoempleado;
    private javax.swing.JTextField txtidcargop;
    private javax.swing.JTextField txtidempleado;
    private javax.swing.JTextField txtidempleadoN;
    private javax.swing.JTextField txtidempleadop;
    private javax.swing.JTextField txtidnomina;
    private javax.swing.JTextField txtidpago;
    private javax.swing.JTextField txtnomEmpleadoN;
    private javax.swing.JTextField txtnomempleado;
    private javax.swing.JTextField txtprecioCargo;
    private javax.swing.JTextField txtprecioN;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttelefonoE;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalPago;
    // End of variables declaration//GEN-END:variables
}
