package Vista;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Modelo.Coordinador.CoordinadorEmpleado;
import Modelo.Logica.LogicaEmpleado;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CargoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.SexoVO;

public class EmpleadoGUI extends JPanel {
private static String[] COLUMNS = new String[] {"ID","Nombre","Apellido","Sexo","Ingreso",
		"Cedula","Cargo","Sueldo","Telefono"};
private static String[] tableEmpleado = new String[] {"Empleado","Empleado"};
private ComponentCustomization custom = new ComponentCustomization();
private CoordinadorEmpleado coordEmpleado;
private LogicaEmpleado logicaEmpleado;
private EmpleadoVO empleado;
private ArrayList<CargoVO> listaCargo;
private ArrayList<SexoVO> listaSexo;
private CargoVO cargo;
private SexoVO sexo;
private ValidationClass validation = new ValidationClass();
private Listados pnl;
private ConfirmacionUser confirmaDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public EmpleadoGUI() throws FontFormatException, IOException, ParseException, SQLException {
	
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		/* Set Coordinador y logica y combobox */
		initCoordLogic();
		// Iniciar componenetes
		initComponents();
		// iniciar acciones botones
		initActionButton();
		
	}
	/* Llenar ComboBox Doctor*/
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordEmpleado = new CoordinadorEmpleado();
		logicaEmpleado = new LogicaEmpleado();
		coordEmpleado.setLogica(logicaEmpleado);
		logicaEmpleado.setCoordinador(coordEmpleado);
		/* Lista de Detalles y Objetos */
		listaSexo = coordEmpleado.listaSexo();
		sexo = new SexoVO();
		cargo = new CargoVO();
	}
	private void initComponents() throws FontFormatException, IOException, ParseException, SQLException {
		// Nombre
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,103,25,25);
		add(required);
		// Apellido
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(387,103,25,25);
		add(required);
		// Cedula
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(737,103,25,25);
		add(required);
		// Sexo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(1027,103,25,25);
		add(required);
		// Fecha Ingreso
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,213,25,25);
		add(required);
		// Telefono
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(387,213,25,25);
		add(required);
		// Cargo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(737,213,25,25);
		add(required);	
		// Sueldo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,413,25,25);
		add(required);	
		// Creacion de label Nombres Empleado
				lblNombreEmpleado = new JLabel("");
				lblNombreEmpleado.setBounds(38, 90, 564, 39);
				custom.label(lblNombreEmpleado, "Nombres:",1);
				// Creacion de label Apellidos Empleado
				lblApellido = new JLabel("");
				lblApellido.setBounds(400, 90, 564, 39);
				custom.label(lblApellido, "Apellidos:",1);
				//label Cedula
				lblCedula = new JLabel();
				lblCedula.setBounds(750,90,532,39);
				custom.label(lblCedula, "Cedula:", 1);
				//label Sexo
				lblSexo = new JLabel();
				lblSexo.setBounds(1040,90,532,39);
				custom.label(lblSexo, "Sexo:", 1);
				// label  Fecha
				lblFechaIngreso = new JLabel("");
				lblFechaIngreso.setBounds(38, 200, 564, 39);
				custom.label(lblFechaIngreso, "Ingreso.:",1);
				// label Telefono
				lblTelefono = new JLabel();
				lblTelefono.setBounds(400,200,265,39);
				custom.label(lblTelefono, "Telefono:", 1);
				// label Cargo
				lblCargo = new JLabel();
				lblCargo.setBounds(750,200,265,39);
				custom.label(lblCargo, "Cargo:", 1);
				// label Correo
				lblCorreo = new JLabel();
				lblCorreo.setBounds(38, 300, 215, 39);
				custom.label(lblCorreo, "Correo:",1);
				// label Dirccion
				lblDireccion = new JLabel();
				lblDireccion.setBounds(598,300,265,39);
				custom.label(lblDireccion, "Direccion:", 1);
				// label Sueldo
				lblSueldo = new JLabel();
				lblSueldo.setBounds(38,400,265,39);
				custom.label(lblSueldo, "Sueldo:", 1);
				// LAbel Moneda
				lblMoneda = new JLabel();
				lblMoneda.setBounds(38, 440, 350, 53);
				custom.label(lblMoneda, "RD$.:", 1);
				// Label Nombre Cargo
				lblNombreCargo = new JLabel();
				custom.label(lblNombreCargo, "Nuevo Cargo:",1);
				
				/* Creacion TextFields y ComboBox */			
				// TextField de Nombre Empleado
				txtNombreEmpleado = new JFormattedTextField("");
				txtNombreEmpleado.setBounds(38, 130, 300, 45);
				custom.txt(txtNombreEmpleado,1,"Ingrese el Nombre del Empleado");
				// TextField de Apellido Empleado
				txtApellido = new JFormattedTextField("");
				txtApellido.setBounds(400, 130, 300, 45);
				custom.txt(txtApellido,1,"Ingrese los Apellidos del Empleado");
				// TextField de Cedula
				txtCedula = new JFormattedTextField(custom.formato("cedula"));
				txtCedula.setBounds(750, 130, 250, 45);
				custom.txt(txtCedula,1,"Ingrese la Cedula del Empleado");
				// TextField de Fecha
				txtFecha = new JFormattedTextField(custom.formato("fecha"));
				txtFecha.setBounds(38, 240, 215, 45);
				custom.txt(txtFecha,1,"Ingrese la Fecha de Nacimiento del Empleado");
				// TextField de Telefono
				txtTelefono = new JFormattedTextField(custom.formato("telefono"));
				txtTelefono.setBounds(400, 240, 300, 45);
				custom.txt(txtTelefono,1,"Ingrese el Telefono del Empleado");
				// TextField de Correo
				txtCorreo = new JFormattedTextField("");
				txtCorreo.setBounds(38, 340, 525, 45);
				custom.txt(txtCorreo,1,"Ingrese el correo del Empleado");
				// Text Area de Direccion
				txtDireccion = new JTextArea();
				txtDireccion.setBounds(598,340,532,165);
				custom.txtArea(txtDireccion);
				// TextField de Sueldo
				txtSueldo = new JFormattedTextField();
				txtSueldo.setBounds(110, 440, 300, 45);
				custom.txt(txtSueldo,1,"Ingrese el Sueldo Devengado por el Empleado");
				// TextField de Nuevo Cargo
				txtCargo = new JTextField(10);
				custom.txt(txtCargo, 1,"Ingrese el nombre del nuevo Cargo de Empleado");
				// Combobox de Sexo
				cbbSexo = new JComboBox();
				custom.cbb(cbbSexo, "Sexo",4);
				listadoSexo(cbbSexo);
				cbbSexo.setBounds(1040,130,85,39);
				// Combobox de Cargo
				cbbCargo = new JComboBox();
				custom.cbb(cbbCargo, "Cargo",5);
				listadoCargo(cbbCargo);
				cbbCargo.setBounds(750,240,350,39);

				
				/* Creacion de Botones */
				// Boton Agregar Empleado
				btnAgregar = new JButton("");
				btnAgregar.setBounds(38, 525, 242, 74);
				custom.button(btnAgregar, "Registrar ", "\uf234", "solid", 3);
				// Boton Limpiar Campos	
				btnClean = new JButton("");
				btnClean.setBounds(760, 525, 242, 74);
				custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
				// Boton Listado Empleado
				btnListado = new JButton();
				btnListado.setBounds(400, 525, 255, 74);
				custom.button(btnListado, "Listado Empleado", "\uf03a", "solid", 6);
				// Boton Cargo
				btnCargo = new JButton();
				custom.button(btnCargo, "", "\uf65e", "solid", 3);
				btnCargo.setBorder(null);
				btnCargo.setBackground(null);
				btnCargo.setForeground(new Color(61, 204, 45));
				btnCargo.setToolTipText("Agregar Nuevo Cargo");
				// Panel Nuevo Cargo
				background = new JPanel();
				custom.confirmacion(background, "Cargo");		
				background.add(lblNombreCargo);
				background.add(txtCargo);
				background.add(btnCargo);
				background.setBounds(225, 5, 600, 80);
			
				/* Agregar Componentes */
				add(lblNombreEmpleado);
				add(lblApellido);
				add(lblFechaIngreso);
				add(lblCorreo);
				add(lblTelefono);
				add(lblCedula);
				add(lblSexo);
				add(lblCargo);
				add(lblDireccion);
				add(lblSueldo);
				add(lblMoneda);
				add(txtNombreEmpleado);
				add(txtApellido);
				add(txtCedula);
				add(cbbSexo);
				add(txtFecha);
				add(txtTelefono);
				add(cbbCargo);
				add(txtCorreo);
				add(txtDireccion);
				add(txtSueldo);
				add(btnAgregar);
				add(btnClean);
				add(btnListado);
				add(background);
	}
	private void initActionButton() {
		/* Acciones de Botones */
		cbbCargo.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaCargo.size();i++) {
					if (cbbCargo.getSelectedItem() != null &&
							cbbCargo.getSelectedItem().toString().equals(listaCargo.get(i).getNombre())) {
					cargo.setNombre(listaCargo.get(i).getNombre());
					cargo.setIdCargo(listaCargo.get(i).getIdCargo());					
					};}	}});
		cbbSexo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaSexo.size();i++) {
					if (cbbSexo.getSelectedItem() != null &&
							cbbSexo.getSelectedItem().toString().equals(listaSexo.get(i).getLetra())) {
					sexo.setGenero(listaSexo.get(i).getGenero());
					sexo.setIdSexo(listaSexo.get(i).getIdSexo());
					};}}});
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addEmpleado();
		}});
			
		btnListado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goListado();
			}});
		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}});
		btnCargo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					confirmacion();
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}}});
	}
	private void listadoSexo(JComboBox cbb) throws SQLException {
		if(!coordEmpleado.listaSexo().isEmpty()) {
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaSexo.size();i++) {
	            model.addElement(listaSexo.get(i).getLetra());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Sexo")); 
      	cbb.setSelectedIndex(-1); }
	}
	private void listadoCargo(JComboBox cbb) throws SQLException {
		if(!coordEmpleado.listaCargo().isEmpty()) {
		int selected = cbb.getSelectedIndex();
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
		 if (!coordEmpleado.listaCargo().isEmpty()) {
		listaCargo = coordEmpleado.listaCargo();
		// Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaCargo.size();i++) {
	            model.addElement(listaCargo.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Cargo")); 
    	cbb.setSelectedIndex(selected); }
	}}
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTableEmpleado(coordEmpleado.listaEmpleado(), COLUMNS,"Empleados",tableEmpleado);
		} catch (FontFormatException | IOException | ParseException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	private void limpiarCampos() {
		txtApellido.setText("");
		txtCedula.setText("");
		txtCorreo.setText("");
		txtDireccion.setText("");
		txtFecha.setText("");
		txtNombreEmpleado.setText("");
		txtSueldo.setText("");
		txtTelefono.setText("");
		cbbCargo.setSelectedIndex(-1);
		cbbSexo.setSelectedIndex(-1);
	}
	private void addEmpleado() {
		empleado = new EmpleadoVO();
		if (!(cbbSexo.getSelectedIndex()>=0)) {
			custom.msg("Debe Seleccionar un Tipo de Sexo",7);}else {
				empleado.setSexo(sexo);}
		if (!(cbbCargo.getSelectedIndex()>=0)) {
			custom.msg("Debe Seleccionar el Cargo",7);}else {
			empleado.setCargo(cargo);}		
		empleado.setCedula(txtCedula.getText());
		empleado.setCorreo(txtCorreo.getText());
		empleado.setDireccion(txtDireccion.getText());
		empleado.setFechaIngreso(txtFecha.getText());
		empleado.setNombre(txtNombreEmpleado.getText());
		empleado.setApellido(txtApellido.getText());
		empleado.setTelefono(txtTelefono.getText());
		empleado.setCargo(cargo);
		if (validation.validDouble(txtSueldo.getText()) == false)
		{custom.msg("Formato de Sueldo no valido",6);}
		else {empleado.setSueldo(Double.parseDouble(txtSueldo.getText()));
		try {
			if(coordEmpleado.addEmpleado(empleado) == true) {
			limpiarCampos();}}
		catch (Exception e1) {
			e1.printStackTrace();
			}}
	}
	private void confirmacion() throws FontFormatException, IOException {
			confirmaDialog = new ConfirmacionUser();
			confirmaDialog.setVisible(true);
			confirmaDialog.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosed(WindowEvent e) {
			    	 if(confirmaDialog.isConfirmacion() == true) {
				    	  cargo = new CargoVO();
				    	  cargo.setNombre(txtCargo.getText());
						  try {
				    		  coordEmpleado.addCargo(cargo);
				    		  listadoCargo(cbbCargo);
							  txtCargo.setText("");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}}}});} 
	public String title() {
		String title = "Nuevo Empleado";
		return title;
	}
	/* Declaracion de Componentes */
	JLabel lblNombreEmpleado;
	JLabel lblApellido;
	JLabel lblFechaIngreso;
	JLabel lblCorreo;
	JLabel lblCedula;
	JLabel lblSexo;
	JLabel lblTelefono;
	JLabel lblCargo;
	JLabel lblDireccion;
	JLabel lblSueldo;
	JLabel lblMoneda;
	JLabel lblNombreCargo;
	JTextField txtCargo;
	JFormattedTextField txtNombreEmpleado;
	JFormattedTextField txtApellido;
	JFormattedTextField txtFecha;
	JFormattedTextField txtCorreo;
	JFormattedTextField txtCedula;
	JComboBox cbbSexo;
	JFormattedTextField txtTelefono;
	JComboBox cbbCargo;
	JFormattedTextField txtSueldo;
	JTextArea txtDireccion;
	JButton btnCargo;
	JButton btnAgregar;
	JButton btnClean;
	JButton btnListado;
	JPanel background;
	JLabel required;
		}

