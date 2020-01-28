package Vista;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import Modelo.Coordinador.CoordinadorPaciente;
import Modelo.Logica.LogicaPaciente;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CitasVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;

public class ModPacienteGUI extends JPanel {
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
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FontFormatException 
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public ModPacienteGUI() throws FontFormatException, IOException, ParseException, SQLException {
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
		listaestado = coordPaciente.listaEstado();
		listaSexo = coordPaciente.listaSexo();
		sexo = new SexoVO();
		estado = new EstadoCivilVO();
	}
	private void initComponents() throws FontFormatException, IOException, ParseException, SQLException {
		// Creacion de Label IDPACIENTe
		lblIDPaciente = new JLabel();
		lblIDPaciente.setBounds(38, 115, 564, 50);
		custom.label(lblIDPaciente,"",3);
		// Creacion de label Nombres Paciente
		 lblNombrePaciente = new JLabel("");
		lblNombrePaciente.setBounds(38, 175, 564, 39);
		custom.label(lblNombrePaciente, "Nombres:",1);
		//label Sexo
		 lblSexo = new JLabel();
		lblSexo.setBounds(750,175,532,39);
		custom.label(lblSexo, "Sexo:", 1);
		// label  Fecha Nacimiento
		 lblFechaNac = new JLabel("");
		lblFechaNac.setBounds(38,295,250,39);
		custom.label(lblFechaNac, "Fecha Nac:",1);
		// label Ingreso
		 lblIngreso = new JLabel();
		lblIngreso.setBounds(400, 295, 215, 39);
		custom.label(lblIngreso, "Ingreso:",1);
		// label estado
		 lblestado = new JLabel();
		lblestado.setBounds(750,295,265,39);
		custom.label(lblestado, "Estado Civil:", 1);
		// label Telefono
		 lblTelefono = new JLabel();
		lblTelefono.setBounds(38,415,265,39);
		custom.label(lblTelefono, "Telefono:", 1);
		// label Ocupacion
		JLabel lblOcupacion = new JLabel();
		lblOcupacion.setBounds(400,415,265,39);
		custom.label(lblOcupacion, "Ocupacion:", 1);
		// Label Nombre estado
		lblNombreEstado = new JLabel();
		custom.label(lblNombreEstado, "Nuevo Estado Civil:",1);
		/* Creacion TextFields y ComboBox */	
		// TextField de Nombre Paciente
		 txtNombrePaciente = new JFormattedTextField("");
		txtNombrePaciente.setBounds(38, 215, 600, 45);
		custom.txt(txtNombrePaciente,1,"Ingrese el Nombre del Paciente");
		// TextField de Fecha
		 txtFecha = new JFormattedTextField(custom.formato("fecha"));
		txtFecha.setBounds(38,335,250,45);
		custom.txt(txtFecha,1,"Ingrese la Fecha de Nacimiento del Paciente");
		// TextField de Ingreso
		 txtIngreso = new JFormattedTextField(custom.formato("fecha"));
		txtIngreso.setBounds(400, 335, 250, 45);
		custom.txt(txtIngreso,1,"Ingrese Fecha de Ingreso del Paciente");
		// TextField de Telefono
		 txtTelefono = new JFormattedTextField(custom.formato("telefono"));
		txtTelefono.setBounds(38, 455, 300, 45);
		custom.txt(txtTelefono,1,"Ingrese el Telefono del Paciente");
		// TextField de Ocupacon
		 txtOcupacion = new JFormattedTextField();
		txtOcupacion.setBounds(400, 455, 300, 45);
		custom.txt(txtOcupacion,1,"Ingrese a que se dedica el Paciente");
		// TextField de Nuevo Estado
		txtEstado = new JTextField(10);
		custom.txt(txtEstado, 1,"Ingrese el nombre del nuevo Estado Civil");
		// Combobox de Sexo
		 cbbSexo = new JComboBox();
		custom.cbb(cbbSexo, "Sexo",4);
		listadoSexo(cbbSexo);
		cbbSexo.setBounds(750,215,350,45);
		// Combobox de estado
		 cbbestado = new JComboBox();
		custom.cbb(cbbestado, "estado",5);
		listadoestado(cbbestado);
		cbbestado.setBounds(750,335,350,45);
		/* Creacion de Botones */
		// Boton Guardar Cambios
		btnGuardar = new JButton("");
		btnGuardar.setBounds(175, 525, 245, 74);
		custom.button(btnGuardar, "Guardar Cambios ", "\uf0c7", "solid", 3);
		// Boton Cancelar Cambios
		btnCancelar = new JButton();
		btnCancelar.setBounds(600, 525, 255, 74);
		custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
		// Boton Estado Cita
		btnEstado = new JButton();
		custom.button(btnEstado, "", "\uf65e", "solid", 3);
		btnEstado.setBorder(null);
		btnEstado.setBackground(null);
		btnEstado.setForeground(new Color(61, 204, 45));
		btnEstado.setToolTipText("Agregar Nuevo Estado Civil");
		// Panel Nuevo Estado Cita
		background = new JPanel();
		custom.confirmacion(background, "Estado Civil");
		background.add(lblNombreEstado);
		background.add(txtEstado);
		background.add(btnEstado);
		background.setBounds(225, 5, 600, 80);
		
		// Background Modificar
		lblBackground = new JLabel();
		lblBackground.setBounds(25, 100, 1100, 420);
		custom.modificacionBG(lblBackground, "Paciente");
		/* Agregar Componentes */
		add(lblIDPaciente);
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
		add(btnGuardar);
		add(btnCancelar);
		add(background);
		add(lblBackground);
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
					if (cbbSexo.getSelectedItem().toString().equals(listaSexo.get(i).getLetra())) {
					sexo.setGenero(listaSexo.get(i).getGenero());
					sexo.setIdSexo(listaSexo.get(i).getIdSexo());
					};}}});
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paciente = new PacienteVO();
				if (!(cbbSexo.getSelectedIndex()>=0)) {
					custom.msg("Debe Seleccionar un Tipo de Sexo",7);}else {
						paciente.setSexo(sexo);}
				if (!(cbbestado.getSelectedIndex()>=0)) {
					custom.msg("Debe Seleccionar el Estado Civil",7);}else {
						paciente.setEstadoCivil(estado);}		
				paciente.setNombre(txtNombrePaciente.getText());
				paciente.setFechaNac(txtFecha.getText());
				paciente.setFechaIngreso(txtIngreso.getText());
				paciente.setTelefono(txtTelefono.getText());
				try {
					coordPaciente.addPaciente(paciente);}
				catch (Exception e1) {
					e1.printStackTrace();
					}}});
			
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
					goListado();}
				}});
		btnEstado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						confirmacion();
					} catch (FontFormatException | IOException e1) {
						e1.printStackTrace();
					}}});
	}
	/* Llenar ComboBox Doctor*/
	private void listadoSexo(JComboBox cbb) {
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaSexo.size();i++) {
	            model.addElement(listaSexo.get(i).getGenero());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Sexo")); 
      	cbb.setSelectedIndex(-1); 
	}
	private void listadoestado(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Estado*/
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
	   }
	}
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTablePaciente(coordPaciente.listaPaciente(), COLUMNS,"Pacientes",tablePaciente);
		} catch (FontFormatException | IOException | ParseException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public JPanel getPaciente(PacienteVO paciente) throws ParseException {
		txtNombrePaciente.setText(paciente.getNombre());
		txtFecha.setText(paciente.getFechaNac());
		txtIngreso.setText(paciente.getFechaIngreso());
		txtOcupacion.setText(paciente.getOcupacion());
		txtTelefono.setText(paciente.getTelefono());
		cbbSexo.setSelectedItem(paciente.getSexo().getGenero());
		cbbestado.setSelectedItem(paciente.getEstadoCivil().getNombre());
		lblIDPaciente.setText("ID Paciente.: " +  String.format("%05d", paciente.getIdPaciente()));
		return this;
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

	public String title() {
		String title = "Nuevo Paciente";
		return title;
	}
	
	/* Declaracion de Componenetes */
	JLabel lblIDPaciente;
	JLabel lblNombrePaciente;
	JLabel lblFechaNac;
	JLabel lblIngreso ;
	JLabel lblSexo;
	JLabel lblestado;
	JLabel lblTelefono;
	JLabel lblOcupacion;
	JLabel lblNombreEstado;
	JLabel lblBackground;
	JPanel background;
	JTextField txtEstado;
	JFormattedTextField txtNombrePaciente;
	JFormattedTextField txtFecha;
	JFormattedTextField txtIngreso;
	JFormattedTextField txtTelefono;
	JFormattedTextField txtOcupacion;
	JComboBox cbbSexo;
	JComboBox cbbestado;
	JButton btnGuardar;
	JButton btnCancelar;
	JButton btnEstado;
		}

