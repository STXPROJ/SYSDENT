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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modelo.Coordinador.CoordCita;
import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaCita;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.PacienteVO;
import Vista.ComponentCustomization;
import Vista.Listados;


public class ModCitaGUI extends JPanel {
private static String[]COLUMNS = new String[] {"ID Cita","Fecha Cita","Programada","Hora Cita","Paciente","Estado",
			"Doctor"};
private static String[] tableCita = new String[] {"AgendarCita","cita"};
private SimpleDateFormat format = new SimpleDateFormat("h:m a");
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();
private CoordCita coordinadorCita;	
private LogicaCita logicaCita;
private PacienteVO paciente;
private ArrayList<EmpleadoVO> listaEmpleado;
private ArrayList<CitasVO> listaCitas;
private ArrayList<EstadoCitaVO> listaEstado;
private EmpleadoVO empleado;
private EstadoCitaVO estado;
private Listados pnl;
private CoordinadorCRUD coordCrud;
private LogicaCRUD logicaCrud;
private CitasVO cita;
private ConfirmacionUser confirmaDialog;

	public ModCitaGUI() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Set Login y Coord
		initCoordLogin();
		// Iniciar Componentes 
		initComponents();
		/* Accion de Botones */
		initButtons();

	}   
	
	
	private void initCoordLogin() {
		/* Set Logica y Coordinador */ 
		coordinadorCita = new CoordCita();
		logicaCita = new LogicaCita();
		coordinadorCita.setLogicaCita(logicaCita);
		logicaCita.setCoordCita(coordinadorCita);
		coordCrud = new CoordinadorCRUD();
		logicaCrud = new LogicaCRUD();
		coordCrud.setLogica(logicaCrud);
		logicaCita.setCoordCita(coordinadorCita);
		paciente = new PacienteVO();
	}
	private void initComponents() throws ParseException, FontFormatException, IOException, SQLException {
		lblCitaNo = new JLabel();
		lblCitaNo.setBounds(38, 115, 564, 39);
		custom.label(lblCitaNo, "No. Cita.: " + String.format("%010d", coordCrud.selectTopID(tableCita[0])+1),3);

		// Label  Paciente
		lblPaciente = new JLabel("");
		lblPaciente.setBounds(38, 175, 542, 39);
		custom.label(lblPaciente, "Nombre del Paciente:",1);
		// Label Nombre Paciente
		lblNombre = new JLabel("");
		lblNombre.setBounds(38, 225, 532, 39);
		custom.label(lblNombre, "",2);
		// Label Estado Cita
		lblEstado = new JLabel("");
		lblEstado.setBounds(608,295,532,39);
		custom.label(lblEstado, "Estado de Cita:", 1);
		// Label Fecha
		lblFecha= new JLabel();
		lblFecha.setBounds(38, 295, 215, 39);
		custom.label(lblFecha, "Fecha:",1);
		// Label Hora
		lblHora = new JLabel();
		lblHora.setBounds(38,415,215,39);
		custom.label(lblHora, "Hora:", 1);
		// Label Doctor
		lblDoctor = new JLabel();
		lblDoctor.setBounds(608,415,215,39);
		custom.label(lblDoctor, "Doctor:",1);
		// Label Nombre estado
		lblNombreEstado = new JLabel();
		custom.label(lblNombreEstado, "Nuevo Estado de Cita:",1);
		/* Creacion TextFields y ComboBOx */
		// TextField de Fecha
		txtFecha= new JFormattedTextField(custom.formato("fechanow"));
		txtFecha.setBounds(38, 335, 215, 53);
		custom.txt(txtFecha,1,"Ingrese la Fecha de la Cita");
		// TextField de Nuevo Estado
		txtEstado = new JTextField(10);
		custom.txt(txtEstado, 1,"Ingrese el nombre del nuevo Estado de Cita");
		/* Creacion de ComboBox y Spinner*/
		// Spinner 
		spHora= new JSpinner();
		spHora.setBounds(38, 455, 215, 53);
		custom.spinner(spHora);
		// Combobox
		cbbEstado= new JComboBox();
		custom.cbb(cbbEstado, "Estado Cita",1);
		fillCbbEstado(cbbEstado);
		cbbEstado.setBounds(608,335,350,39);
		// Combobox Doctor
		cbbDoctor= new JComboBox();
		custom.cbb(cbbDoctor, "Doctor:",2);
		fillCbbDoctor(cbbDoctor);
		cbbDoctor.setBounds(608,455,350,39);
		/* Creacion de Botones */
		// Boton Agendar Cita
		btnGuardar = new JButton();
		btnGuardar.setBounds(175, 555, 275, 74);
		custom.button(btnGuardar, "Guardar Cambios", "\uf0c7", "regular", 3);
		// Boton Listado Cita		
		btnCancelar= new JButton();
		btnCancelar.setBounds(600, 555, 275, 74);
		custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
		// Boton Estado Cita
		btnEstado = new JButton();
		custom.button(btnEstado, "", "\uf65e", "solid", 3);
		btnEstado.setBorder(null);
		btnEstado.setBackground(null);
		btnEstado.setForeground(new Color(61, 204, 45));
		btnEstado.setToolTipText("Agregar Nuevo Estado de Cita");
		// Panel Nuevo Estado Cita
		background = new JPanel();
		custom.confirmacion(background, "Estado de Cita");		
		background.add(lblNombreEstado);
		background.add(txtEstado);
		background.add(btnEstado);
		background.setBounds(225, 5, 600, 80);
		// Background Modificar
		lblBackground = new JLabel();
		lblBackground.setBounds(25, 100, 1000, 430);
		custom.modificacionBG(lblBackground, "Cita");
		
		/* Agregar componentes */
		add(lblCitaNo);
		add(lblPaciente);
		add(lblEstado);
		add(lblHora);
		add(lblNombre);
		add(lblFecha);
		add(lblDoctor);
		add(txtFecha);
		add(cbbEstado);
		add(spHora);
		add(cbbDoctor);
		add(btnGuardar);
		add(btnCancelar);
		add(background);
		add(lblBackground);
	}
	private void initButtons() {
		cbbDoctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaEmpleado.size();i++) {
					if (cbbDoctor.getSelectedItem()!= null &&
							cbbDoctor.getSelectedItem().toString().equals(listaEmpleado.get(i).getNombre()) && 
							cbbDoctor.getSelectedIndex()>=0) {
					empleado = new EmpleadoVO();
					empleado.setNombre(listaEmpleado.get(i).getNombre());
					empleado.setIdEmpleado(listaEmpleado.get(i).getIdEmpleado());	

					};}}});
	/*	btnGuardar.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Datos Cita
				cita = new CitasVO();
				cita.setFechaProgramada(txtFecha.getText());
				cita.setHoraCita(format.format(spHora.getValue()));
				cita.setPaciente(paciente);
				cita.setEstado((String) cbbEstado.getSelectedItem());
				if(!(cbbDoctor.getSelectedIndex()>=0)) {
					custom.msg("Debe elegir un Doctor para el paciente", 6);
				}
				else {
					cita.setEmpleado(empleado);
					try {
						coordinadorCita.addCita(cita);
		
						setNumCita();
					} catch (ParseException e1) {
						custom.msg("", 1);
					}}}});		*/

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
				goListado();}
			}});
		btnEstado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmacion();
			}});
	}
	
	
	private void fillCbbDoctor(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Doctor*/
		if (!coordinadorCita.empleadoComboBox().isEmpty()) {
		listaEmpleado = coordinadorCita.empleadoComboBox();
		
		 DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaEmpleado.size();i++) {
	            model.addElement(listaEmpleado.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
	        cbb.setRenderer(new MyComboBox("Doctor")); 
      	cbb.setSelectedIndex(-1); 
	}
	}
	private void fillCbbEstado(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Doctor*/
		int selected = cbb.getSelectedIndex();
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
		if (!coordinadorCita.estadoComboBox().isEmpty()) {
		listaEstado = coordinadorCita.estadoComboBox();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaEstado.size();i++) {
	            model.addElement(listaEstado.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
		    cbb.setRenderer(new MyComboBox("Estado de la Cita")); 
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
		//	pnl.setTableCita(this,coordinadorCita.listaCita(), COLUMNS,"Citas",tableCita);
		} catch (FontFormatException | IOException | ParseException | SQLException e) {
			e.printStackTrace();}
	}
	public String title() {
		String title = "Modificar Cita";
		return title;
	}
	private void setNumCita() {
		lblCitaNo.setText("No. Cita.: " + String.format("%010d", coordCrud.selectTopID(tableCita[0])+1));
	}
	public JPanel getCita(CitasVO cita) throws ParseException {
		lblCitaNo.setText("No. Cita.: " + String.format("%010d", cita.getIdCita()));
		lblNombre.setText(cita.getPaciente().getNombre());
		lblNombre.setSize(lblNombre.getPreferredSize());
		txtFecha.setText(cita.getFechaProgramada());
		cbbEstado.setSelectedItem(cita.getEstado().getNombre());
		cbbDoctor.setSelectedItem(cita.getEmpleado().getNombre());
		spHora.setValue(format.parse(cita.getHoraCita()));
		return this;
	}
	private void confirmacion() {
		try {
			confirmaDialog = new ConfirmacionUser();
			confirmaDialog.setVisible(true);
			confirmaDialog.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosed(WindowEvent e) {
			      if(confirmaDialog.isConfirmacion() == true) {
			    	  estado = new EstadoCitaVO();
			    	  estado.setNombre(txtEstado.getText());
			    	  try {
						coordinadorCita.addEstado(estado);
						fillCbbEstado(cbbEstado);
						txtEstado.setText("");
					} catch (ParseException | SQLException e1 ) {
					} 	}}});
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();}
	}
	/* Declaracion Componentes  */
		JLabel lblBackground;
		JLabel lblCitaNo;
		JLabel lblPaciente;
		JLabel lblNombre;
		JLabel lblEstado;
		JLabel lblFecha;
		JLabel lblHora ;
		JLabel lblDoctor;
		JLabel lblNombreEstado;
		JTextField txtEstado;
		JFormattedTextField txtFecha;
		JSpinner spHora ;
		JComboBox cbbEstado;
		JComboBox cbbDoctor;
		JButton btnGuardar; 
		JButton btnCancelar;
		JButton btnEstado;
		JPanel background;
}
