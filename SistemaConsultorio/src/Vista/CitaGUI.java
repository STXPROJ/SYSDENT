package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modelo.Coordinador.CoordCita;
import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.DAO.EstadoCitaDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaCita;
import Modelo.Logica.ValidationClass;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.PacienteVO;


public class CitaGUI extends JPanel {
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
private BuscadorDialog buscadorDialog;
	public CitaGUI() throws FontFormatException, IOException, ParseException, SQLException {
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Set Login y Coord
		initCoordLogic();
		// Iniciar Componentes 
		initComponents();
		/* Accion de Botones */
		initButtons();

	}   
	
	
	private void initCoordLogic() {
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
		// Datos Requeridos
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		// Paciente
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,187,25,25);
		add(required);
		// Fecha
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,308,25,25);
		add(required);
		// Hora		
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,428,25,25);
		add(required);
		// Estado
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(590,308,25,25);
		add(required);
		// Doctor
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(590,428,25,25);
		add(required);
		// Creacion de Label NoCita
		lblCitaNo = new JLabel();
		lblCitaNo.setBounds(38, 115, 564, 50);
		custom.label(lblCitaNo, "No. Cita.: " + String.format("%010d", coordCrud.selectTopID(tableCita[0])+1),3);
		// Creacion de Label idPaciente
		 lblidPaciente = new JLabel();
		lblidPaciente.setBounds(38, 175, 564, 39);
		custom.label(lblidPaciente, "ID de Paciente:",1);
		// Label  Paciente
		lblPaciente = new JLabel("");
		lblPaciente.setBounds(608, 175, 542, 39);
		custom.label(lblPaciente, "Nombre del Paciente:",1);
		// Label Nombre Paciente
		lblNombre = new JLabel("");
		lblNombre.setBounds(608, 225, 532, 39);
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
		// TextField de idPaciente
		txtidPaciente= new JFormattedTextField();
		txtidPaciente.setBounds(38, 215, 215, 45);
		custom.txt(txtidPaciente,1,"Ingrese el ID del Paciente");
		// TextField de Fecha
		txtFecha= new JFormattedTextField(custom.formato("fechanow"));
		txtFecha.setBounds(38, 335, 215, 45);
		custom.txt(txtFecha,1,"Ingrese la Fecha de la Cita");
		// TextField de Nuevo Estado
		txtEstado = new JTextField(10);
		custom.txt(txtEstado, 1,"Ingrese el nombre del nuevo Estado de Cita");
		/* Creacion de ComboBox y Spinner*/
		// Spinner 
		spHora= new JSpinner();
		spHora.setBounds(38, 455, 215, 45);
		custom.spinner(spHora);
		// Combobox
		cbbEstado= new JComboBox();
		custom.cbb(cbbEstado, "Estado Cita",1);
		fillCbbEstado(cbbEstado);
		cbbEstado.setBounds(608,335,350,45);
		// Combobox Doctor
		cbbDoctor= new JComboBox();
		custom.cbb(cbbDoctor, "Doctor:",2);
		fillCbbDoctor(cbbDoctor);
		cbbDoctor.setBounds(608,455,350,45);
		/* Creacion de Botones */
		// Boton Buscar Paciente
		btnSearch = new JButton();
		btnSearch.setBounds(263, 202, 75, 66);
		custom.button(btnSearch, "", "\uf002", "solid",5);
		btnSearch.setToolTipText("Buscar por id del Paciente");
		// Boton Buscador Dialog
		btnBuscador = new JButton();
		btnBuscador.setBounds(333, 202, 75, 66);
		custom.button(btnBuscador, "", "\uf00e", "solid",9);
		btnBuscador.setToolTipText("Abrir Listado de Pacientes");
		// Boton Agendar Cita
		btnCita = new JButton();
		btnCita.setBounds(38, 555, 275, 74);
		custom.button(btnCita, "Agendar Cita", "\uf073", "regular", 3);
		// Boton Limpiar Campos	
		btnClean= new JButton();
		btnClean.setBounds(760, 555, 242, 74);
		custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
		// Boton Listado Cita		
		btnListado= new JButton();
		btnListado.setBounds(400, 555, 275, 74);
		custom.button(btnListado, "Listado Cita", "\uf03a", "solid", 6);
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
		/* Agregar componentes */
		add(lblCitaNo);
		add(lblidPaciente);
		add(lblPaciente);
		add(lblEstado);
		add(lblHora);
		add(lblNombre);
		add(lblFecha);
		add(lblDoctor);
		add(background);
		add(txtidPaciente);
		add(txtFecha);
		add(cbbEstado);
		add(spHora);
		add(cbbDoctor);
		add(btnSearch);
		add(btnCita);
		add(btnClean);
		add(btnBuscador);
		add(btnListado);
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
		cbbEstado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i =0;i<listaEstado.size();i++) {
					if (cbbEstado.getSelectedItem()!= null &&
							cbbEstado.getSelectedItem().toString().equals(listaEstado.get(i).getNombre()) && 
							cbbEstado.getSelectedIndex()>=0) {
					estado = new EstadoCitaVO();
					estado.setNombre(listaEstado.get(i).getNombre());
					estado.setIdEstado(listaEstado.get(i).getIdEstado());	

					};}}});
		btnCita.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				addCita();
				}});		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPaciente();			
			}});
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			public void actionPerformed(ActionEvent arg0) {
				
				confirmacion();
			}});
		btnBuscador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					goBuscador();
				} catch (FontFormatException | IOException | ParseException | SQLException e) {
					e.printStackTrace();
				}}});
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
	        cbb.setRenderer(new MyComboBox("Doctor/a")); 
      	cbb.setSelectedIndex(-1); 
	}
	}
	private void fillCbbEstado(JComboBox cbb) throws SQLException {
		/* Llenar ComboBox Estado*/
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
	private void limpiarCampos() {
		txtidPaciente.setText("");
		txtFecha.setText("");
		lblNombre.setText("");
		spHora.getModel();
		cbbDoctor.setSelectedIndex(-1);
		cbbEstado.setSelectedIndex(-1);
	}
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			revalidate();
			repaint();
			add(pnl.getPanel());
			pnl.setTableCita(coordinadorCita.listaCita(), COLUMNS,"Citas",tableCita);
		} catch (FontFormatException | IOException | ParseException | SQLException e) {
			e.printStackTrace();}
	}
	public String title() {
		String title = "Agendar Cita";
		return title;
	}
	private void setNumCita() {
		lblCitaNo.setText("No. Cita.: " + String.format("%010d", coordCrud.selectTopID(tableCita[0])+1));
	}
	private void getPaciente() {
		if (!(validation.validDigit(txtidPaciente.getText()) == false)) {
			paciente = coordinadorCita.buscarPaciente(Integer.parseInt(txtidPaciente.getText()));
			if(paciente!=null) {
			lblNombre.setText(paciente.getNombre());
			lblNombre.setSize(lblNombre.getPreferredSize());}
		else {
			txtidPaciente.setText("");}
		}
		else {
			custom.msg("id del paciente", 5);
			txtidPaciente.setText("");
		}}
	private void goBuscador() throws FontFormatException, IOException, ParseException, SQLException {
	buscadorDialog = new BuscadorDialog();
	buscadorDialog.setVisible(true);
	buscadorDialog.setTablePaciente();
	buscadorDialog.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosed(WindowEvent e) {
	    	txtidPaciente.setText(String.valueOf(buscadorDialog.getIdTable()));	
	    	 	getPaciente();
				}});
	} 
	private void addCita() {
		// Datos Cita
		cita = new CitasVO();
		cita.setFechaProgramada(txtFecha.getText());
		cita.setHoraCita(format.format(spHora.getValue()));
		cita.setPaciente(paciente);
		cita.setEstado(estado);
		if(!(cbbDoctor.getSelectedIndex()>=0)) {
			custom.msg("Debe elegir un Doctor para el paciente", 6);
		}
		else if(!(cbbEstado.getSelectedIndex()>=0)) {
			custom.msg("Debe elegir el Estado de la Cita", 6);
		}
		else {
			cita.setEmpleado(empleado);
			try {
				if(coordinadorCita.addCita(cita) == true) {
				limpiarCampos();
				setNumCita();}
			} catch (ParseException e1) {
				custom.msg("", 1);
			}}
	}
	public CitaGUI getPanel() {
		return this;
}
	/* Declaracion Componentes  */
		JLabel lblCitaNo;
		JLabel lblidPaciente;
		JLabel lblPaciente;
		JLabel lblNombre;
		JLabel lblEstado;
		JLabel lblFecha;
		JLabel lblHora ;
		JLabel lblDoctor;
		JLabel lblNombreEstado;
		JPanel background;
		JTextField txtEstado;
		JFormattedTextField txtidPaciente;
		JFormattedTextField txtFecha;
		JSpinner spHora ;
		JComboBox cbbEstado;
		JComboBox cbbDoctor;
		JButton btnSearch;
		JButton btnBuscador;
		JButton btnCita;
		JButton btnClean; 
		JButton btnListado;
		JButton btnEstado;
		JLabel required;
}
