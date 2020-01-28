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

import Modelo.Coordinador.CoordinadorServicio;
import Modelo.DAO.TipoServicioDAO;
import Modelo.Logica.LogicaServicio;
import Modelo.Logica.ValidationClass;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;


public class ModServicioGUI extends JPanel {
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
	public ModServicioGUI() throws FontFormatException, IOException, ParseException, SQLException {
	
		
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
		/* Creacion de Labels */
		lblidServicio = new JLabel();
		lblidServicio.setBounds(38,115,564,45);
		custom.label(lblidServicio, "ID Servicio.: ", 3);
		// Creacion de Label Nombre
		lblNombreServicio = new JLabel("");
		lblNombreServicio.setBackground(Color.GRAY);
		lblNombreServicio.setBounds(38, 155, 564, 39);
		custom.label(lblNombreServicio, "Nombre:",1);
		// Label  Descripcion
		lblDescripcion = new JLabel("");
		lblDescripcion.setBounds(608, 155, 542, 39);
		custom.label(lblDescripcion, "Descripcion:",1);
		// LAbel Moneda
		lblMoneda = new JLabel();
		lblMoneda.setBounds(38, 255, 350, 53);
		custom.label(lblMoneda, "Precio RD$.:", 1);
		//Label Tipo
		lblTipo = new JLabel();
		lblTipo.setBounds(38,305,275,39);
		custom.label(lblTipo, "Tipo de Servicio:", 1);

		// Label Nombre Tipo
		lblNombreTipo= new JLabel();
		custom.label(lblNombreTipo, "Nuevo Tipo de Servicio:", 1);
		/* Creacion TextFields y ComboBOx */
		// TextField de Nombre
		txtNombre = new JFormattedTextField();
		txtNombre.setBounds(38, 195, 500, 45);
		custom.txt(txtNombre,1,"Ingrese el Nombre del Servicio");
		// TextArea de Descripcion
		txtDescrpcion = new JTextArea();
		txtDescrpcion.setBounds(608, 195, 532, 203);
		custom.txtArea(txtDescrpcion);
		// TextField de Precio
		txtPrecio = new JFormattedTextField();
		txtPrecio.setBounds(185, 255, 275, 45);
		custom.txt(txtPrecio,1,"Ingrese el Precio del Servicio");
		// TextField de Nuevo Tipo
		txtTipo = new JTextField(10);
		custom.txt(txtTipo, 1,"Ingrese el nombre del nuevo Tipo de Servicio");
		//ComboBox Tipo
		// TextField de Descripcion
		cbbtipo = new JComboBox();
		custom.cbb(cbbtipo, "Tipo Servicio",8);
		listadoTipos(cbbtipo);
		cbbtipo.setBounds(38,345,275,45);/* Creacion de Botones */
		// Boton Guardar Cambios
		 btnGuardar = new JButton("");
			btnGuardar.setBounds(175, 525, 245, 74);
			custom.button(btnGuardar, "Guardar Cambios ", "\uf0c7", "solid", 3);
			// Boton Cancelar Cambios
			btnCancelar = new JButton();
			btnCancelar.setBounds(600, 525, 255, 74);
			custom.button(btnCancelar, "Cancelar Cambios", "\uf05e", "solid", 6);
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
			// Background Modificar
			lblBackground = new JLabel();
			lblBackground.setBounds(25, 95, 1120, 420);
			custom.modificacionBG(lblBackground, "Servicio");
		// Insertar al Panel Principal
		add(lblidServicio);
		add(lblNombreServicio);
		add(lblDescripcion);
		add(txtNombre);
		add(lblTipo);
		add(lblMoneda);
		add(txtPrecio);
		add(txtDescrpcion);
		add(cbbtipo);
		add(btnGuardar);
		add(btnCancelar);
		add(background);
		add(lblBackground);
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
			btnGuardar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					servicio = new ServicioVO();
					if (!(cbbtipo.getSelectedIndex()>=0)) 
					{custom.msg("Debe Seleccionar un Tipo de Servicio",7);}
					servicio.setDescripcion(txtDescrpcion.getText());
					servicio.setNombre(txtNombre.getText());
					servicio.setTipoServicio(tipoServicio);
					if (validation.validDouble(txtPrecio.getText()) == false)
					{custom.msg("Formato de Sueldo no valido",6);}
					else {servicio.setPrecio(Double.parseDouble(txtPrecio.getText()));}
					try {
						coordServicio.addServicio(servicio);
					}catch (Exception ex) {
						ex.printStackTrace();
					}}});
			
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(custom.msgConfirm("Desea Cancelar los cambios?", 1) == JOptionPane.YES_OPTION){
						goListado();}
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
			pnl.setTableServ(coordServicio.listaServicio(), COLUMNS,"Servicios",tableServicio);
		} catch (SQLException | FontFormatException | IOException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	public JPanel getServicio(ServicioVO servicio) throws ParseException {
		lblidServicio.setText(lblidServicio.getText() +  String.format("%05d",servicio.getIdServicio()));
		txtDescrpcion.setText(servicio.getDescripcion());
		txtNombre.setText(servicio.getNombre());
		txtPrecio.setText(String.valueOf(servicio.getPrecio()));
		cbbtipo.setSelectedItem(servicio.getTipoServicio().getNombre());
		return this;
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
		JLabel lblidServicio;
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
		JButton btnGuardar;
		JButton btnCancelar;
		JPanel background;
		JLabel lblBackground;
}
