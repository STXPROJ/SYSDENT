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

import Modelo.Coordinador.CoordinadorServicio;
import Modelo.DAO.TipoServicioDAO;
import Modelo.Logica.LogicaServicio;
import Modelo.Logica.ValidationClass;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;


public class ServicioGUI extends JPanel {
	private static String[] COLUMNS = new String[] {"ID","Nombre","Descripcion","Tipo Servicio","Precio"	};
	private static String[] tableServicio = new String[] {"Servicio","Servicio"};
	private ArrayList<TipoServicioVO> listaTipoServicio;
	private ComponentCustomization custom = new ComponentCustomization();
	private CoordinadorServicio coordServicio;
	private LogicaServicio logicaServicio;
	private TipoServicioDAO consultaTipo;
	private TipoServicioVO tipoServicio;
	private ServicioVO servicio;
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
	public ServicioGUI() throws FontFormatException, IOException, ParseException, SQLException {
	
		
		/* Customizacion de Panel Principal*/
		setBackground(Color.WHITE);
		setBounds(0, 0, 1160,630 );
		setLayout(null);
		// Iniciar Coordinador Logica y ComboBox
		initCoordLogic();
		// Iniciar Componenetes
		initComponent();
		// Iniiciar Action de Botones
		initActionButton();

	
	}
	
	private void initCoordLogic() throws SQLException {
		/* Set Logica y Coordinador */
		coordServicio = new CoordinadorServicio();
		logicaServicio = new LogicaServicio();
		coordServicio.setLogica(logicaServicio);
		logicaServicio.setCoordinador(coordServicio);
		/* Listas */
		tipoServicio = new TipoServicioVO();
	}
	private void initComponent() throws ParseException, FontFormatException, IOException, SQLException {
		// Nombre
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,148,25,25);
		add(required);
		// Precio
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,253,25,25);
		add(required);
		// Tipo
		required = new JLabel();
		custom.label(required, "*", 3);
		required.setForeground(Color.RED);
		required.setBounds(25,298,25,25);
		add(required);
/* Creacion de Labels */
		// Creacion de Label Nombre
		lblNombreServicio = new JLabel("");
		lblNombreServicio.setBackground(Color.GRAY);
		lblNombreServicio.setBounds(38, 135, 564, 39);
		custom.label(lblNombreServicio, "Nombre:",1);
		// Label  Descripcion
		lblDescripcion = new JLabel("");
		lblDescripcion.setBounds(608, 135, 542, 39);
		custom.label(lblDescripcion, "Descripcion:",1);
		// LAbel Moneda
		lblMoneda = new JLabel();
		lblMoneda.setBounds(38, 235, 350, 53);
		custom.label(lblMoneda, "Precio RD$.:", 1);
		//Label Tipo
		lblTipo = new JLabel();
		lblTipo.setBounds(38,285,275,39);
		custom.label(lblTipo, "Tipo de Servicio:", 1);

		// Label Nombre Tipo
		lblNombreTipo= new JLabel();
		custom.label(lblNombreTipo, "Nuevo Tipo de Servicio:", 1);
		/* Creacion TextFields y ComboBOx */
		// TextField de Nombre
		txtNombre = new JFormattedTextField();
		txtNombre.setBounds(38, 175, 500, 45);
		custom.txt(txtNombre,1,"Ingrese el Nombre del Servicio");
		// TextArea de Descripcion
		txtDescrpcion = new JTextArea();
		txtDescrpcion.setBounds(608, 175, 532, 203);
		custom.txtArea(txtDescrpcion);
		// TextField de Precio
		txtPrecio = new JFormattedTextField();
		txtPrecio.setBounds(185, 235, 275, 45);
		custom.txt(txtPrecio,1,"Ingrese el Precio del Servicio");

		// TextField de Nuevo Tipo
		txtTipo = new JTextField(10);
		custom.txt(txtTipo, 1,"Ingrese el nombre del nuevo Tipo de Servicio");
		//ComboBox Tipo
		// TextField de Descripcion
		cbbtipo = new JComboBox();
		custom.cbb(cbbtipo, "Tipo Servicio",8);
		listadoTipos(cbbtipo);
		cbbtipo.setBounds(38,325,275,45);
		
		/* Creacion Botones  */
		// Boton Agregar servicio
		btnservicio = new JButton("");
		btnservicio.setBounds(38, 555, 275, 74);
		custom.button(btnservicio, "Agregar servicio", "\uf65e", "solid", 3);
		// Boton Limpiar Campos	
		btnClean = new JButton("");
		btnClean.setBounds(760, 555, 242, 74);
		custom.button(btnClean, "Limpiar Campos", "\uf51a", "solid", 4);
		// Boton Listado servicio
		btnListado = new JButton();
		btnListado.setBounds(400, 555, 275, 74);
		custom.button(btnListado, "Listado servicio", "\uf03a", "solid", 6);
		// Boton Tipo
		btnTipo = new JButton();
		custom.button(btnTipo, "", "\uf65e", "solid", 3);
		btnTipo.setBorder(null);
		btnTipo.setBackground(null);
		btnTipo.setForeground(new Color(61, 204, 45));
		btnTipo.setToolTipText("Agregar Nuevo Tipo de Servicio");
		// Panel Nuevo Tipo
		background = new JPanel();
		custom.confirmacion(background, "Tipo de Servicio");		
		background.add(lblNombreTipo);
		background.add(txtTipo);
		background.add(btnTipo);
		background.setBounds(225, 5, 600, 80);
		// Insertar al Panel Principal
		add(lblNombreServicio);
		add(lblDescripcion);
		add(txtNombre);
		add(lblTipo);
		add(lblMoneda);
		add(txtPrecio);
		add(txtDescrpcion);
		add(cbbtipo);
		add(btnservicio);
		add(btnClean);
		add(btnListado);
		add(background);
	}
	
	private void initActionButton() {
		cbbtipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i =0;i<listaTipoServicio.size();i++) {
					if (cbbtipo.getSelectedItem()!= null &&
							cbbtipo.getSelectedItem().toString().equals(listaTipoServicio.get(i).getNombre())) {
					tipoServicio.setNombre(listaTipoServicio.get(i).getNombre());
					tipoServicio.setIdTipo(listaTipoServicio.get(i).getIdTipo());}
				}}});
			btnservicio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				addServicio();
				}});
			
			btnListado.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					goListado();
				}
			});
			btnClean.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					limpiarCampos();
				}});
			btnTipo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						confirmacion();
					} catch (FontFormatException | IOException e1) {
						e1.printStackTrace();
					}}});
	}
	
	/* Llenar ComboBox Tipo Servicio*/
	private void listadoTipos(JComboBox cbb) throws SQLException {
		if(!coordServicio.listaTipo().isEmpty()) {
		int selected = cbb.getSelectedIndex();
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
		if (!coordServicio.listaTipo().isEmpty()) {
		listaTipoServicio = coordServicio.listaTipo();
	        // Borrar Datos Viejos
	        model.removeAllElements();
	        for (int i=0;i<listaTipoServicio.size();i++) {
	            model.addElement(listaTipoServicio.get(i).getNombre());
	        }
	        // setting model with new data
	        cbb.setModel(model);
		    cbb.setRenderer(new MyComboBox("Tipo de Servicio")); 
	      	cbb.setSelectedIndex(selected); }
	   }
	}
		
	private void goListado() {
		try {
			pnl = new Listados();
			removeAll();
			add(pnl.getPanel());
			revalidate();
			repaint();
			pnl.setTableServ(coordServicio.listaServicio(), COLUMNS,"Servicios",tableServicio);
		} catch (SQLException | FontFormatException | IOException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	private void limpiarCampos() {
		txtDescrpcion.setText("");
		txtNombre.setText("");
		txtPrecio.setText("");
		cbbtipo.setSelectedIndex(-1);
	}
	private void addServicio() {
		servicio = new ServicioVO();
		if (!(cbbtipo.getSelectedIndex()>=0)) 
		{custom.msg("Debe Seleccionar un Tipo de Servicio",7);}
		servicio.setDescripcion(txtDescrpcion.getText());
		servicio.setNombre(txtNombre.getText());
		servicio.setTipoServicio(tipoServicio);
		if (validation.validDouble(txtPrecio.getText()) == false)
		{custom.msg("Formato de Precio no valido",6);}
		else {servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));
		try {
			if(coordServicio.addServicio(servicio) == true) {
				limpiarCampos();
			};
		}catch (Exception ex) {
			ex.printStackTrace();
		}}
	}
	private void confirmacion() throws FontFormatException, IOException {
		confirmaDialog = new ConfirmacionUser();
		confirmaDialog.setVisible(true);
		confirmaDialog.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosed(WindowEvent e) {
		      if(confirmaDialog.isConfirmacion() == true) {
		    	  tipoServicio = new TipoServicioVO();
		    	  tipoServicio.setNombre(txtTipo.getText());
				  try {
		    		  coordServicio.addTipo(tipoServicio);
		    		  listadoTipos(cbbtipo);
					  txtTipo.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}}}});} 
	  
	public String title() {
		String title = "Nuevo servicio";
		return title;
	}
	/* Declarar Componenetes */
	 	JLabel lblNombreServicio;
		JLabel lblDescripcion;
		JLabel lblTipo;
		JLabel lblMoneda;
		JLabel lblNombreTipo;
		JTextField txtTipo;
		JFormattedTextField txtNombre;
		JFormattedTextField txtPrecio;
		JTextArea txtDescrpcion;
		JComboBox cbbtipo;
		JButton btnTipo;
		JButton btnservicio;
		JButton btnClean;
		JButton btnListado;
		JPanel background;
		JLabel required;
}
