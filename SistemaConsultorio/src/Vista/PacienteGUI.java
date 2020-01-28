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
import javax.swing.border.LineBorder;

import Modelo.Coordinador.CoordinadorPaciente;
import Modelo.Logica.LogicaPaciente;
import Modelo.Logica.ValidationClass;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;

public class PacienteGUI extends JPanel {
private static String[] COLUMNS = new String[] {"ID","Nombre","Sexo","Fecha Nac.","Ingreso",
		"Estado Civil","Ocupacion","Telefono","Balance"};
private static String[] tablePaciente = new String[] {"Paciente","Paciente"};
private ComponentCustomization custom = new ComponentCustomization();
private CoordinadorPaciente coordPaciente;
private LogicaPaciente logicaPaciente;
private PacienteVO paciente;
private ArrayList<EstadoCivilVO> listaestado;
private ArrayList<SexoVO> listaSexo;
private EstadoCivilVO estado;
private SexoVO sexo;
private ValidationClass validation = new ValidationClass();
private Listados pnl;
private ConfirmacionUser confirmaDialog;
private HistorialClinicoDialog historialDialog;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public PacienteGUI() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Set Login y Coord
		initCoordLogic();
		// Iniciar Componentes 
		initComponents();
		/* Accion de Botones */
		initActionButton();	
	}
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordPaciente = new CoordinadorPaciente();
		logicaPaciente = new LogicaPaciente();
		coordPaciente.setLogica(logicaPaciente);
		logicaPaciente.setCoordinador(coordPaciente);
		/* Lista de Detalles y Objetos */
		listaSexo = coordPaciente.listaSexo();
		sexo = new SexoVO();
		estado = new EstadoCivilVO();
	}
	private void initComponents() throws FontFormatException, IOException, ParseException, SQLException {
		// Datos Requeridos
		// Nombre
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,128,25,25);
		add(required);
		// Sexo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(737,128,25,25);
		add(required);
		// Fecha Nacimiento
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,248,25,25);
		add(required);
		// Fecha Ingreso
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(387,248,25,25);
		add(required);
		// Estado
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(737,248,25,25);
		add(required);
		// Telefono
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,368,25,25);
		add(required);
				
		// Creacion de label Nombres Paciente
				 lblNombrePaciente = new JLabel("");
				lblNombrePaciente.setBounds(38, 115, 564, 39);
				custom.label(lblNombrePaciente, "Nombres:",1);
				//label Sexo
				 lblSexo = new JLabel();
				lblSexo.setBounds(750,115,532,39);
				custom.label(lblSexo, "Sexo:", 1);
				// label  Fecha Nacimiento
				 lblFechaNac = new JLabel("");
				lblFechaNac.setBounds(38,235,250,39);
				custom.label(lblFechaNac, "Fecha Nac:",1);
				// label Ingreso
				 lblIngreso = new JLabel();
				lblIngreso.setBounds(400, 235, 215, 39);
				custom.label(lblIngreso, "Ingreso:",1);
				// label estado
				 lblestado = new JLabel();
				lblestado.setBounds(750,235,265,39);
				custom.label(lblestado, "Estado Civil:", 1);
				// label Telefono
				 lblTelefono = new JLabel();
				lblTelefono.setBounds(38,355,265,39);
				custom.label(lblTelefono, "Telefono:", 1);
				// label Ocupacion
				JLabel lblOcupacion = new JLabel();
				lblOcupacion.setBounds(400,355,265,39);
				custom.label(lblOcupacion, "Ocupacion:", 1);
				// Label Nombre estado
				lblNombreEstado = new JLabel();
				custom.label(lblNombreEstado, "Nuevo Estado Civil:",1);
				/* Creacion TextFields y ComboBox */	
				// TextField de Nombre Paciente
				 txtNombrePaciente = new JFormattedTextField("");
				txtNombrePaciente.setBounds(38, 155, 600, 45);
				custom.txt(txtNombrePaciente,1,"Ingrese el Nombre del Paciente");
				// TextField de Fecha
				 txtFecha = new JFormattedTextField(custom.formato("fecha"));
				txtFecha.setBounds(38,275,250,45);
				custom.txt(txtFecha,1,"Ingrese la Fecha de Nacimiento del Paciente");
				// TextField de Ingreso
				 txtIngreso = new JFormattedTextField(custom.formato("fecha"));
				txtIngreso.setBounds(400, 275, 250, 45);
				custom.txt(txtIngreso,1,"Ingrese Fecha de Ingreso del Paciente");
				// TextField de Telefono
				 txtTelefono = new JFormattedTextField(custom.formato("telefono"));
				txtTelefono.setBounds(38, 395, 300, 45);
				custom.txt(txtTelefono,1,"Ingrese el Telefono del Paciente");
				// TextField de Ocupacon
				 txtOcupacion = new JFormattedTextField();
				txtOcupacion.setBounds(400, 395, 300, 45);
				custom.txt(txtOcupacion,1,"Ingrese a que se dedica el Paciente");
				// TextField de Nuevo Estado
				txtEstado = new JTextField(10);
				custom.txt(txtEstado, 1,"Ingrese el nombre del nuevo Estado Civil");
				// Combobox de Sexo
				 cbbSexo = new JComboBox();
				custom.cbb(cbbSexo, "Sexo",4);
				listadoSexo(cbbSexo);
				cbbSexo.setBounds(750,155,85,45);
				// Combobox de estado
				 cbbestado = new JComboBox();
				custom.cbb(cbbestado, "estado",5);
				listadoestado(cbbestado);
				cbbestado.setBounds(750,275,350,45);
				
				/* Creacion de Botones */
				// Boton Agregar Paciente
				 btnAgregar = new JButton("");
				btnAgregar.setBounds(38, 525, 242, 74);
				custom.button(btnAgregar, "Registrar ", "\uf234", "solid", 3);
				// Boton Limpiar Campos	
				 btnClean = new JButton("");
				btnClean.setBounds(760, 525, 242, 74);
				custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
				// Boton Listado Paciente
				btnListado = new JButton();
				btnListado.setBounds(400, 525, 255, 74);
				custom.button(btnListado, "Listado Paciente", "\uf03a", "solid", 6);
				// Boton Estado Civil
				btnEstado = new JButton();
				custom.button(btnEstado, "", "\uf65e", "solid", 3);
				btnEstado.setBorder(null);
				btnEstado.setBackground(null);
				btnEstado.setForeground(new Color(61, 204, 45));
				btnEstado.setToolTipText("Agregar Nuevo Estado Civil");
				// Panel Nuevo Estado Civil
				background = new JPanel();
				custom.confirmacion(background, "Estado Civil");
				background.add(lblNombreEstado);
				background.add(txtEstado);
				background.add(btnEstado);
				background.setBounds(225, 5, 600, 80);
				/* Agregar Componentes */
				add(lblNombrePaciente);
				add(lblFechaNac);
				add(lblIngreso);
				add(lblTelefono);
				add(lblSexo);
				add(lblestado);
				add(lblOcupacion);
				add(txtNombrePaciente);
				add(cbbSexo);
				add(txtFecha);
				add(txtIngreso);
				add(cbbestado);
				add(txtTelefono);
				add(txtOcupacion);
				add(btnAgregar);
				add(btnClean);
				add(btnListado);
				add(background);
	}
	private void initActionButton() {
		/* Acciones de Botones */
		cbbestado.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaestado.size();i++) {
					if (cbbestado.getSelectedItem() != null && 
							cbbestado.getSelectedItem().toString().equals(listaestado.get(i).getNombre())) {
					estado.setNombre(listaestado.get(i).getNombre());
					estado.setIdEstado(listaestado.get(i).getIdEstado());					
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
				addPaciente();
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
			}
		});
		btnEstado.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					confirmacion();
				} catch (FontFormatException | IOException e1) {
					e1.printStackTrace();
				}}});
	}
	private void listadoSexo(JComboBox cbb) throws SQLException {
		if(!coordPaciente.listaSexo().isEmpty()) {
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
	private void listadoestado(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Estado*/
		if(!coordPaciente.listaEstado().isEmpty()) {
		int selected = cbb.getSelectedIndex();
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
		if (!coordPaciente.listaEstado().isEmpty()) {
		listaestado = coordPaciente.listaEstado();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaestado.size();i++) {
	            model.addElement(listaestado.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
		    cbb.setRenderer(new MyComboBox("Estado Civil")); 
	      	cbb.setSelectedIndex(selected); 
	   }}
	}
	private void addPaciente() {
		paciente = new PacienteVO();
		if (!(cbbSexo.getSelectedIndex()>=0)) {
			custom.msg("Debe Seleccionar un Tipo de Sexo",7);}
		else if (!(cbbestado.getSelectedIndex()>=0)) {
			custom.msg("Debe Seleccionar el Estado Civil",7);}
		else {
		paciente.setEstadoCivil(estado);
		paciente.setSexo(sexo);
		paciente.setNombre(txtNombrePaciente.getText());
		paciente.setFechaNac(txtFecha.getText());
		paciente.setFechaIngreso(txtIngreso.getText());
		paciente.setTelefono(txtTelefono.getText());
		try {
			if(coordPaciente.addPaciente(paciente) == true) {
			limpiarCampos();}}
		catch (Exception e1) {
			e1.printStackTrace();
			}}};

	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTablePaciente(coordPaciente.listaPaciente(), COLUMNS,"Pacientes",tablePaciente);
		} catch (FontFormatException | IOException | ParseException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void confirmacion() throws FontFormatException, IOException {
			confirmaDialog = new ConfirmacionUser();
			confirmaDialog.setVisible(true);
			confirmaDialog.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosed(WindowEvent e) {
			      if(confirmaDialog.isConfirmacion() == true) {
			    	  estado = new EstadoCivilVO();
			    	  estado.setNombre(txtEstado.getText());
					  try {
			    		  coordPaciente.addEstado(estado);
			    		  listadoestado(cbbestado);
						  txtEstado.setText("");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}}}});} 
		
	private void limpiarCampos() {
		txtFecha.setText("");
		txtIngreso.setText("");
		txtNombrePaciente.setText("");
		txtOcupacion.setText("");
		txtTelefono.setText("");
		txtTelefono.getFormatter();
		cbbestado.setSelectedIndex(-1);
		cbbSexo.setSelectedIndex(-1);
	}
	public String title() {
		String title = "Nuevo Paciente";
		return title;
	}
	
	/* Declaracion de Componenetes */
	JLabel lblNombrePaciente;
	JLabel lblFechaNac;
	JLabel lblIngreso ;
	JLabel lblSexo;
	JLabel lblestado;
	JLabel lblTelefono;
	JLabel lblOcupacion;
	JLabel lblNombreEstado;
	JPanel background;
	JTextField txtEstado;
	JFormattedTextField txtNombrePaciente;
	JFormattedTextField txtFecha;
	JFormattedTextField txtIngreso;
	JFormattedTextField txtTelefono;
	JFormattedTextField txtOcupacion;
	JComboBox cbbSexo;
	JComboBox cbbestado;
	JButton btnAgregar;
	JButton btnListado;
	JButton btnClean;
	JButton btnEstado;
	JLabel required;
		}

